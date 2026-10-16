baseline coverage value: 70.83% statement coverage and 77.78% branch coverage
Final coverage value: 91.67% statement coverage and 100% branch coverage

I added a test for the remove method which tested whether or not an item was remove from the inventory. i already had tests for the other methods from last project, i only had the remove method to test. But, As you can see there was two statements that i could not test which were: 	public Item[] getItems() { return items; } public Item get(int i) { return items[i]; } as i dont know how or why we should test them. 