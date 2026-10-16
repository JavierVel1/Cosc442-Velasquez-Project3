Initial coverage results:

VendingMachine.java--  Initial Statement/Line Coverage: 93.33% Initial Branch Coverage:95.45%

VendingMachineItem.java--  Initial Statement/Line Coverage: 87.50% Initial Branch Coverage: 50%

I added a test for returnChange and a test for vendingMachineException in VendingMachineItem

Final coverage results: 
VendingMachine.java-- Final Statement/Line coverage:100% Final Branch Coverage:95.45%

I cant reach 100% in branch coverage is because this condition, if (item != null).
It is impossible to have a null item in makePurchase because getItem removes null items so i cannot test that this if condition. 

VendingMachineItem.java-- Final Statement/Line coverage:100% Final Branch Coverage:100%

--------------------------------------------------------------------------------------------------------------------------------------------------
The Compound Boolean decision in the VendingMachine implementation that i will write about is in makePurchase()
The individual Boolean conditions that make up the decision:
(item != null)  and (this.balance >= item.getPrice())

The test that evaluate those condition to true and false is testMakePurchaseValidCase() and testMakePurchaseInvalidCase()
i made sure that those tests cover those condition because i wanted to be thorough. 

--------------------------------------------------------------------------------------------------------------------------------------------------

When i introduce the injected fault these test failed: testReturnChange(), testMakePurchaseInvalidCase(), testMakePurchaseValidCase(), and testInsertMoneyValidCase(). 