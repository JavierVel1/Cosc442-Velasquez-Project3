import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

/**
 * Tests for VendingMachineItem, based only on the documented contract:
 * precondition price >= 0; postcondition name/price are set to the arguments;
 * a VendingMachineException is thrown if price < 0.
 */
class VendingMachineItemTest1 {

	private static final double DELTA = 1e-9;

	@ParameterizedTest(name = "name=\"{0}\", price={1}")
	@CsvSource({
			"Chips,1.25",
			"Gum,0.0",              // boundary: lowest legal price
			"Soda,0.01",            // boundary: just above zero
			"Gold Bar,1000000.0"    // large value
	})
	@DisplayName("Valid construction stores the given name and price")
	void constructor_validArguments_storesNameAndPrice(String name, double price) {
		// Arrange: inputs supplied by the parameters

		// Act
		VendingMachineItem item = new VendingMachineItem(name, price);

		// Assert
		assertEquals(name, item.getName());
		assertEquals(price, item.getPrice(), DELTA);
	}

	@ParameterizedTest(name = "price={0}")
	@ValueSource(doubles = { -0.01, -1.0, -50.0 })   // -0.01 is the boundary just below zero
	@DisplayName("Negative price is rejected with VendingMachineException")
	void constructor_negativePrice_throws(double price) {
		// Arrange
		Executable act = () -> new VendingMachineItem("Invalid", price);

		// Act & Assert
		assertThrows(VendingMachineException.class, act);
	}
}
