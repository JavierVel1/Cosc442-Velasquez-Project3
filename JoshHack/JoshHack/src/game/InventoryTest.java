package game;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.awt.Color;
import org.junit.jupiter.api.Test; 
public class InventoryTest {
    @Test
    void testRemoveVaild() {
        Inventory inventory = new Inventory(1);
        Color red = new Color(255, 0, 0); 
        Item item = new Item('A', red, "Chest", "A gold chest");
        //act 
        inventory.add(item);
        inventory.remove(item);
        //assert 
        assertFalse(inventory.contains(item));
    }


        @Test
    void testAddValidCase() {
        //Arrange
        Inventory inventory = new Inventory(1);
        Color red = new Color(255, 0, 0); 
        Item item = new Item('A', red, "Chest", "A gold chest");
        //act 
        inventory.add(item);
        //assert 
        assertTrue(inventory.contains(item));
        

    }

    @Test
    void testAddInvalidCase() {
        //Arrange
        Inventory inventory = new Inventory(1);
        Color red = new Color(255, 0, 0); 
        Color blue = new Color(0, 0, 255); 
        Item item = new Item('A', red, "Chest", "A gold chest");
        Item item2 = new Item('B', blue, "Sword", "A pointy sword");
        //act 
        inventory.add(item);
        inventory.add(item2);
        //assert 
        assertFalse(inventory.contains(item2)); 
    }

    @Test
    void testisFullValidCase() {
        //Arrange
        Inventory inventory = new Inventory(2);
        Color red = new Color(255, 0, 0); 
        Color blue = new Color(0, 0, 255); 
        Item item = new Item('A', red, "Chest", "A gold chest");
        Item item2 = new Item('B', blue, "Sword", "A pointy sword");
        //act 
        inventory.add(item);
        inventory.add(item2);
        //assert 
        assertTrue(inventory.isFull()); 
    }

    @Test
    void testisFullInvalidCase() {
        //Arrange
        Inventory inventory = new Inventory(3);
        Color red = new Color(255, 0, 0); 
        Color blue = new Color(0, 0, 255); 
        Item item = new Item('A', red, "Chest", "A gold chest");
        Item item2 = new Item('B', blue, "Sword", "A pointy sword");
        //act 
        inventory.add(item);
        inventory.add(item2);
        //assert 
        assertFalse(inventory.isFull()); 
    }


    @Test
    void testContainValid() {
        //Arrange
        Inventory inventory = new Inventory(3);
        Color red = new Color(255, 0, 0); 
        Color blue = new Color(0, 0, 255); 
        Item item = new Item('A', red, "Chest", "A gold chest");
        Item item2 = new Item('B', blue, "Sword", "A pointy sword");
        //act 
        inventory.add(item);
        inventory.add(item2);
        //assert 
        assertTrue(inventory.contains(item2)); 
    }

    @Test
    void testContainInvalid() {
        //Arrange
        Inventory inventory = new Inventory(1);
        Color red = new Color(255, 0, 0); 
        Color blue = new Color(0, 0, 255); 
        Item item = new Item('A', red, "Chest", "A gold chest");
        Item item2 = new Item('B', blue, "Sword", "A pointy sword");
        //act 
        inventory.add(item);
        inventory.add(item2);
        //assert 
        assertFalse(inventory.contains(item2)); 
    }

    
}
