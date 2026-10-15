import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class VendingMachineTest {

    @Test
    void testAddItemEmptySlot() {
        // Arrange
        VendingMachine vend = new VendingMachine();
        VendingMachineItem item = new VendingMachineItem("Chips", 4); 
        VendingMachineItem item2 = new VendingMachineItem("Cookie", 3); 
        //Act
        vend.addItem(item, "A");
        vend.addItem(item2, "B");
        //assert
        assertEquals("Chips", vend.getItem("A").getName());
        assertEquals(4, vend.getItem("A").getPrice(), 0.0001); 
        assertEquals("Cookie", vend.getItem("B").getName()); 
        assertEquals(3, vend.getItem("B").getPrice(), 0.0001); 
    }

    @Test
    void testAddItemFilledSlot() {
        // Arrange
        VendingMachine vend = new VendingMachine();
        VendingMachineItem item = new VendingMachineItem("Chips", 4); 
        VendingMachineItem item2 = new VendingMachineItem("Cookie", 3); 
        //Act
        vend.addItem(item, "A");
        //assert
        assertThrows(VendingMachineException.class, () -> vend.addItem(item2, "A"));
    }

    @Test
    void testInsertMoneyValidCase() {
        // Arrange
        VendingMachine vend = new VendingMachine();
        //Act 
        vend.insertMoney(1);
        vend.insertMoney(10.30);
        vend.insertMoney(0.0);
        //assert
        assertEquals(11.30, vend.getBalance(), 0.0001);
    }


    @ParameterizedTest
    @ValueSource(doubles = {-10.24, -1})
    void testInsertMoneyInvalidCase(double amount) {
        // Arrange
        VendingMachine vend = new VendingMachine();
        //Act and assert
        assertThrows(VendingMachineException.class, () -> vend.insertMoney(amount));
    }

        @ParameterizedTest
    @ValueSource(doubles = {1, 0.5, 0.001, 0, -0.001, -0.5, -1})
    void testInsertMoneyBoundaryCase(double amount) {
        // Arrange
        VendingMachine vend = new VendingMachine();
        //Act and assert
        assertThrows(VendingMachineException.class, () -> vend.insertMoney(amount));
    }

    @Test
    void testMakePurchaseValidCase() {
        // Arrange
        VendingMachine vend = new VendingMachine();
        VendingMachineItem item = new VendingMachineItem("Chips", 4); 
        VendingMachineItem item2 = new VendingMachineItem("Cookie", 3); 
        VendingMachineItem item3 = new VendingMachineItem("Pizza", 11); 
        //Act
        vend.addItem(item, "A");
        vend.addItem(item2, "B");
        vend.addItem(item2, "C");
        vend.insertMoney(10.0);
        //Assert
        assertTrue(vend.makePurchase("A"));
        assertTrue(vend.makePurchase("B"));
    }

    @Test
    void testMakePurchaseInvalidCase() {
        // Arrange
        VendingMachine vend = new VendingMachine();
        VendingMachineItem item = new VendingMachineItem("Chips", 4); 
        VendingMachineItem item2 = new VendingMachineItem("Cookie", 3); 
        VendingMachineItem item3 = new VendingMachineItem("Pizza", 11); 
        //Act
        vend.addItem(item, "A");
        vend.addItem(item2, "B");
        vend.addItem(item3, "C");
        vend.insertMoney(10.0);
        //Assert
        assertFalse(vend.makePurchase("C"));
        assertFalse(vend.makePurchase("D"));
    }


    @Test
    void testRemoveItemValidCase() {
        // Arrange
        VendingMachine vend = new VendingMachine();
        VendingMachineItem item = new VendingMachineItem("Chips", 4); 
        VendingMachineItem item2 = new VendingMachineItem("Cookie", 3); 
        VendingMachineItem item3 = new VendingMachineItem("Pizza", 11); 
        //Act
        vend.addItem(item, "A");
        vend.addItem(item2, "B");
        vend.addItem(item3, "C");
        vend.removeItem("A");
        vend.removeItem("B");
        vend.removeItem("C");
        //Assert
        assertThrows(VendingMachineException.class, () ->  vend.getItem("A"));
        assertThrows(VendingMachineException.class, () ->  vend.getItem("B"));
        assertThrows(VendingMachineException.class, () ->  vend.getItem("C"));
    }
    
    @Test
     void testRemoveItemInvalidCase() {
        // Arrange
        VendingMachine vend = new VendingMachine();
        //Act and Assert
        assertThrows(VendingMachineException.class, () ->  vend.removeItem("A"));

    }

    @Test
    void testGetItemValidcase(){
        // Arrange
        VendingMachine vend = new VendingMachine();
        VendingMachineItem item = new VendingMachineItem("Chips", 4); 
        VendingMachineItem item2 = new VendingMachineItem("Cookie", 3); 
        //Act
        vend.addItem(item, "A");
        vend.addItem(item2, "B");
        //assert
        assertEquals("Chips", vend.getItem("A").getName());
        assertEquals(4, vend.getItem("A").getPrice(), 0.0001); 
        assertEquals("Cookie", vend.getItem("B").getName()); 
        assertEquals(3, vend.getItem("B").getPrice(), 0.0001); 
    }

    @Test
    void testGetItemInvalidcase(){
        // Arrange
        VendingMachine vend = new VendingMachine();

        //Act

        //assert
        assertThrows(VendingMachineException.class, () ->  vend.getItem("Chip"));
        assertThrows(VendingMachineException.class, () ->  vend.getItem("A")); 
    }
}
