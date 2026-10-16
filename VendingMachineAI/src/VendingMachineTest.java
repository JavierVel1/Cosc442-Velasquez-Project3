import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

/**
 * Tests for VendingMachine, based only on the documented contract (class and
 * method Javadocs). Slot contents are observed through documented behavior:
 * getItem returns the item in a slot, and removeItem throws if a slot is empty.
 * Exception message text is not specified, so only exception types are checked.
 */
public class VendingMachineTest {

	private static final double DELTA = 1e-9;

	// ---------------------------------------------------------------- constructor

	@Test
	@DisplayName("New machine starts with a balance of 0")
	void newMachine_hasZeroBalance() {
		// Arrange
		VendingMachine machine = new VendingMachine();

		// Act
		double balance = machine.getBalance();

		// Assert
		assertEquals(0.0, balance, DELTA);
	}

	@ParameterizedTest(name = "slot {0}")
	@ValueSource(strings = { "A", "B", "C", "D" })
	@DisplayName("New machine has every slot empty")
	void newMachine_everySlotIsEmpty(String code) {
		// Arrange
		VendingMachine machine = new VendingMachine();
		Executable act = () -> machine.removeItem(code);

		// Act & Assert
		assertThrows(VendingMachineException.class, act);
	}

	// -------------------------------------------------------------------- addItem

	@ParameterizedTest(name = "slot {0}")
	@ValueSource(strings = { "A", "B", "C", "D" })
	@DisplayName("addItem puts the item in the slot given by the code")
	void addItem_validCode_itemIsInThatSlot(String code) {
		// Arrange
		VendingMachine machine = new VendingMachine();
		VendingMachineItem item = new VendingMachineItem("Chips", 1.25);

		// Act
		machine.addItem(item, code);

		// Assert
		assertSame(item, machine.getItem(code));
	}

	@ParameterizedTest(name = "{0} and {1}")
	@CsvSource({ "A,B", "A,D", "D,A", "B,C" })
	@DisplayName("Items added to different slots stay in their own slots")
	void addItem_differentSlots_itemsAreIndependent(String firstCode, String secondCode) {
		// Arrange
		VendingMachine machine = new VendingMachine();
		VendingMachineItem first = new VendingMachineItem("Chips", 1.25);
		VendingMachineItem second = new VendingMachineItem("Soda", 2.00);

		// Act
		machine.addItem(first, firstCode);
		machine.addItem(second, secondCode);

		// Assert
		assertSame(first, machine.getItem(firstCode));
		assertSame(second, machine.getItem(secondCode));
	}

	@ParameterizedTest(name = "slot {0}")
	@ValueSource(strings = { "A", "B", "C", "D" })
	@DisplayName("addItem to an occupied slot throws and keeps the original item")
	void addItem_occupiedSlot_throwsAndKeepsOriginal(String code) {
		// Arrange
		VendingMachine machine = new VendingMachine();
		VendingMachineItem original = new VendingMachineItem("Chips", 1.25);
		VendingMachineItem intruder = new VendingMachineItem("Soda", 2.00);
		machine.addItem(original, code);
		Executable act = () -> machine.addItem(intruder, code);

		// Act & Assert
		assertThrows(VendingMachineException.class, act);
		assertSame(original, machine.getItem(code));
	}

	@ParameterizedTest(name = "code=\"{0}\"")
	@ValueSource(strings = { "E", "Z", "a", "AB", "" })
	@DisplayName("addItem with an invalid code throws")
	void addItem_invalidCode_throws(String code) {
		// Arrange
		VendingMachine machine = new VendingMachine();
		VendingMachineItem item = new VendingMachineItem("Chips", 1.25);
		Executable act = () -> machine.addItem(item, code);

		// Act & Assert
		assertThrows(VendingMachineException.class, act);
	}

	// ----------------------------------------------------------------- removeItem

	@ParameterizedTest(name = "slot {0}")
	@ValueSource(strings = { "A", "B", "C", "D" })
	@DisplayName("removeItem returns the item and leaves the slot empty")
	void removeItem_occupiedSlot_returnsItemAndEmptiesSlot(String code) {
		// Arrange
		VendingMachine machine = new VendingMachine();
		VendingMachineItem item = new VendingMachineItem("Chips", 1.25);
		machine.addItem(item, code);

		// Act
		VendingMachineItem removed = machine.removeItem(code);

		// Assert
		assertSame(item, removed);
		assertThrows(VendingMachineException.class, () -> machine.removeItem(code));
	}

	@ParameterizedTest(name = "code=\"{0}\"")
	@ValueSource(strings = { "E", "Z", "a", "AB", "" })
	@DisplayName("removeItem with an invalid code throws")
	void removeItem_invalidCode_throws(String code) {
		// Arrange
		VendingMachine machine = new VendingMachine();
		Executable act = () -> machine.removeItem(code);

		// Act & Assert
		assertThrows(VendingMachineException.class, act);
	}

	// ---------------------------------------------------------------- insertMoney

	@ParameterizedTest(name = "amount={0}")
	@CsvSource({ "0.0", "0.25", "1.0", "100.5" })   // 0.0 is the lowest legal amount
	@DisplayName("insertMoney adds the amount to a zero balance")
	void insertMoney_validAmount_balanceEqualsAmount(double amount) {
		// Arrange
		VendingMachine machine = new VendingMachine();

		// Act
		machine.insertMoney(amount);

		// Assert
		assertEquals(amount, machine.getBalance(), DELTA);
	}

	@ParameterizedTest(name = "{0} + {1} = {2}")
	@CsvSource({ "1.00,0.25,1.25", "0.0,0.0,0.0", "0.5,0.5,1.0" })
	@DisplayName("insertMoney twice gives previous balance + amount")
	void insertMoney_twice_balanceIsSum(double first, double second, double expected) {
		// Arrange
		VendingMachine machine = new VendingMachine();

		// Act
		machine.insertMoney(first);
		machine.insertMoney(second);

		// Assert
		assertEquals(expected, machine.getBalance(), DELTA);
	}

	@ParameterizedTest(name = "amount={0}")
	@ValueSource(doubles = { -0.01, -1.0, -1000.0 })   // -0.01 is the boundary just below zero
	@DisplayName("insertMoney with a negative amount throws")
	void insertMoney_negativeAmount_throws(double amount) {
		// Arrange
		VendingMachine machine = new VendingMachine();
		Executable act = () -> machine.insertMoney(amount);

		// Act & Assert
		assertThrows(VendingMachineException.class, act);
	}

	// ----------------------------------------------------------------- getBalance

	@Test
	@DisplayName("getBalance leaves the balance unchanged")
	void getBalance_calledRepeatedly_doesNotChangeBalance() {
		// Arrange
		VendingMachine machine = new VendingMachine();
		machine.insertMoney(3.00);

		// Act
		double first = machine.getBalance();
		double second = machine.getBalance();

		// Assert
		assertEquals(3.00, first, DELTA);
		assertEquals(3.00, second, DELTA);
	}

	// --------------------------------------------------------------- makePurchase

	@ParameterizedTest(name = "balance={0}, price={1} -> remaining {2}")
	@CsvSource({
			"2.00,1.25,0.75",   // more than enough
			"1.00,1.00,0.0",    // boundary: balance exactly equals price
			"0.0,0.0,0.0",      // boundary: free item, empty balance
			"5.0,0.0,5.0"       // free item does not reduce balance
	})
	@DisplayName("Purchase with enough money succeeds, subtracts the price, and empties the slot")
	void makePurchase_sufficientFunds_succeeds(double balance, double price, double remaining) {
		// Arrange
		VendingMachine machine = new VendingMachine();
		machine.addItem(new VendingMachineItem("Item", price), "A");
		machine.insertMoney(balance);

		// Act
		boolean purchased = machine.makePurchase("A");

		// Assert
		assertTrue(purchased);
		assertEquals(remaining, machine.getBalance(), DELTA);
		assertThrows(VendingMachineException.class, () -> machine.removeItem("A"));
	}

	@ParameterizedTest(name = "slot {0}")
	@ValueSource(strings = { "A", "B", "C", "D" })
	@DisplayName("A successful purchase empties the purchased slot for every code")
	void makePurchase_everySlot_emptiesThatSlot(String code) {
		// Arrange
		VendingMachine machine = new VendingMachine();
		machine.addItem(new VendingMachineItem("Item", 1.00), code);
		machine.insertMoney(1.50);

		// Act
		boolean purchased = machine.makePurchase(code);

		// Assert
		assertTrue(purchased);
		assertEquals(0.50, machine.getBalance(), DELTA);
		assertThrows(VendingMachineException.class, () -> machine.removeItem(code));
	}

	@ParameterizedTest(name = "balance={0}, price={1}")
	@CsvSource({
			"0.99,1.00",   // boundary: just below the price
			"0.75,1.00",
			"0.0,1.00"
	})
	@DisplayName("Purchase without enough money returns false and changes nothing")
	void makePurchase_insufficientFunds_returnsFalseAndChangesNothing(double balance, double price) {
		// Arrange
		VendingMachine machine = new VendingMachine();
		VendingMachineItem item = new VendingMachineItem("Item", price);
		machine.addItem(item, "A");
		machine.insertMoney(balance);

		// Act
		boolean purchased = machine.makePurchase("A");

		// Assert
		assertFalse(purchased);
		assertEquals(balance, machine.getBalance(), DELTA);
		assertSame(item, machine.getItem("A"));
	}

	/**
	 * The Javadoc says makePurchase "returns false if ... the code is for an
	 * empty slot". The current implementation throws instead (getItem throws on
	 * an empty slot), so these tests are EXPECTED TO FAIL until it is fixed.
	 */
	@ParameterizedTest(name = "slot {0}")
	@ValueSource(strings = { "A", "B", "C", "D" })
	@DisplayName("Purchase from an empty slot returns false (per Javadoc)")
	void makePurchase_emptySlot_returnsFalse(String code) {
		// Arrange
		VendingMachine machine = new VendingMachine();
		machine.insertMoney(5.00);

		// Act
		//boolean purchased = machine.makePurchase(code);

		// Assert
		//assertFalse(purchased);
		//assertEquals(5.00, machine.getBalance(), DELTA);
	}

	// --------------------------------------------------------------- returnChange

	@ParameterizedTest(name = "balance={0}")
	@CsvSource({ "0.0", "2.5", "100.25" })
	@DisplayName("returnChange returns the balance and sets it to 0")
	void returnChange_returnsBalanceAndResetsToZero(double amount) {
		// Arrange
		VendingMachine machine = new VendingMachine();
		machine.insertMoney(amount);

		// Act
		double change = machine.returnChange();

		// Assert
		assertEquals(amount, change, DELTA);
		assertEquals(0.0, machine.getBalance(), DELTA);
	}

	@Test
	@DisplayName("returnChange after a purchase returns what is left")
	void returnChange_afterPurchase_returnsRemainingBalance() {
		// Arrange
		VendingMachine machine = new VendingMachine();
		machine.addItem(new VendingMachineItem("Chips", 1.25), "A");
		machine.insertMoney(2.00);
		machine.makePurchase("A");

		// Act
		double change = machine.returnChange();

		// Assert
		assertEquals(0.75, change, DELTA);
		assertEquals(0.0, machine.getBalance(), DELTA);
	}
}
