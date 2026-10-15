1. First Bug: 
•	The observed failure

•	The test that exposed it
  void testAddItemEmptySlot() 
•	The source-code fault that caused it
    for (int i = 0; i <= NUM_SLOTS; i++) 
•	How you diagnosed the fault
I looked it up on google the error message because i thought there was something wrong with the test method. 
It told me it had do something with Arrays. It told me the error was on line 13 which was VendingMachine vend = new VendingMachine(); 
•	The correction you made
    for (int i = 0; i < NUM_SLOTS; i++) 


2. Second Bug: 
•	The observed failure
VendingMachineException: Invalid amount.  Amount must be >= 0
        at VendingMachine.insertMoney(VendingMachine.java:162)
        at VendingMachineTest.testInsertMoneyValidCase(VendingMachineTest.java:45)
        at java.base/java.lang.reflect.Method.invoke(Method.java:565)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1604)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1604)
•	The test that exposed it
void testInsertMoneyValidCase()
•	The source-code fault that caused it
if (amount < 1)
•	How you diagnosed the fault
Honestly, I saw this error when i was studying the code to be able to understand it. I read the javadocs and knew this was a bug.
•	The correction you made
if (amount < 0)

3. Third Bug: 
•	The observed failure
java.lang.AssertionError: expected VendingMachineException to be thrown, but nothing was thrown
        at org.junit.Assert.assertThrows(Assert.java:1028)
        at org.junit.Assert.assertThrows(Assert.java:981)
        at VendingMachineTest.testGetItemInvalidcase(VendingMachineTest.java:161)
        at java.base/java.lang.reflect.Method.invoke(Method.java:565)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1604)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1604)
•	The test that exposed it
void testGetItemInvalidcase()
•	The source-code fault that caused it
protected VendingMachineItem getItem(String code) throws VendingMachineException {
		int slotIndex = getSlotIndex(code);
		return itemArray[slotIndex];
	}
•	How you diagnosed the fault
Honestly, I saw this error when i was studying the code to be able to understand it. I read the javadocs and knew this was a bug.
•	The correction you made
		if (itemArray[slotIndex] == null) {
			throw new VendingMachineException(SLOT_MESSAGE + code + IS_EMPTY_MESSAGE);
		}


INJECTED FAULT documentation
•	The fault you introduced
In removeItem, instead of if (item == null) , i changed it to if (item != null) 
•	The test or tests that failed
void testRemoveItemValidCase()
vend.removeItem("A") this line failed
•	The relevant JUnit failure message
VendingMachineException: Slot A is empty -- cannot remove item
        at VendingMachine.removeItem(VendingMachine.java:150)
        at VendingMachineTest.testRemoveItemValidCase(VendingMachineTest.java:118)
        at java.base/java.lang.reflect.Method.invoke(Method.java:565)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1604)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1604)
•	A short explanation of why the test detected the fault
The test was able to detect the fault because it already had an input for it. It would always be detected if that if statement is there. 


