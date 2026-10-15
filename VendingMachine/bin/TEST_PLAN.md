
1. Method: addItem
Valid case:[("Chips", 10), "A"], [("Cookie", 5), "B"]
Invalid case:[("Candy", 10), "A"]
Oracle / Expected Result:
Valid case: I expect the item to be in the Vending Machine when those slots are emtpy because i am following the specification of the method. 
Invalid case: I expect it to return the VendingMachineException because i am trying to add an item to an filled slots which should not be allowed.
Related JUnit 5 test: assertEquals and assertThrows

2. Method: removeItem
Valid case: A, B, C,
Invalid case: D which is Null
Oracle / Expected Result:
Valid case: I expect the item at A, B, C, D are removed because its a specification of the method
Invalid case: I expect it to return a VendingMachineException because its a specification of the method

Related JUnit 5 test: assertEquals and assertThrows

3. Method: insertMoney
Valid case: 1, 10.30, 0
Invalid case: -10.24, -1
Boundary case: 1, 0.5, 0.001, 0, -0.001, -0.5, -1
Oracle / Expected Result:
Valid case: I expect my balance, of Zero to begin with, to be 11.30 because that is what is specify The Javadocs.
Invalid case: I expect it to throw a VendingMachineException because its an condition of an if statement in the method
Boundary Case: I expect for 0-1 to be inserted into the balance because its a specification of the method and i expect the -0.001 --- -1 to throw an 
VendingMachineException because its specify in the Javadocs that amounts lower than 0 so throw VendingMachineException

Related JUnit 5 test:assertEquals and assertThrows and @ParameterizedTest. I will use a Parameterized Test because i want to test what is the smallest amount of money i can insert.

4. Method: makePurchase
Prep :Balance:10 (item "Chip", price:4, Slot : A), (item "Cookie", price:2, Slot : B), (item "Pizza", price:11, Slot : C)
Valid case: A, B
Invalid case: Chip, Null, C
Oracle / Expected Result:
Valid Case:I expect to be able to purchase A and B because it follows the specification of the method
Invalid case: I expect to not able to purchase Chip, Null, and C because of the if condition in the method and the specification of the method

Related JUnit 5 test:assertEquals and assertThrows

5. Method: getItem
Valid case: A, B
Invalid case: Chip, Null, C is empty 
Oracle / Expected Result:
Valid Case:I expect to be able to access item A and B because the specification of the method says that it should Gets the item occupying the slot with the given code
Invalid case: I expect it to throw an VendingMachineException because its a specification of the method where if the code is invalid, it throws 
VendingMachineException

Related JUnit 5 test:assertEquals and assertThrows
