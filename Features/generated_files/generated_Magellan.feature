@Run_1518879138607
Feature: CurrentRun



Scenario Outline: Validate Gene Expression search funcitionality
Given  user is on new magellan search page
When  Gene Expression is selected in the dropdown
When  user inputs <param1> in the seach box
And  clicks on the search button
	Examples:
	 |param1| 


Scenario Outline: Validate MicroRNA search funcitionality
Given  user is on new magellan search page
When  MicroRNA is selected in the dropdown
When  user inputs <param1> in the seach box
And  clicks on the search button
	Examples:
	 |param1| 

@Sanity @Regression @Sprint_GSD2Bullets_18.1 
Scenario: Validate action dropdown
Given  user is on new magellan search page
When  user has selected some assays
Then  the select action dropdown should be enabled and clickable

@Sanity @Regression @Sprint_GSD2Bullets_18.1 
Scenario: Validate email functionality
Given  user is on new magellan search page
When  user has selected some assays
And  selects email from select action dropdown
And  enters a valid email address and clicks ok
Then  user should get a success message

@Sanity @Regression @Sprint_GSD2Bullets_18.1 
Scenario: Validate export functionality
Given  user is on new magellan search page
When  user has selected some assays
And  selects export from select action dropdown
Then  a text file download should get started

@MagellanNewSearchTool @Sanity @Regression 
Scenario: Validate search page UI
Given  user is on new magellan search page
Then  user should see TaqMan Assays as the default text

@MagellanNewSearchTool 
Scenario Outline: Validate dropdown values on new search page
Given  user is on new magellan search page
When  user clicks on the main dropdown
Then  user should see <param1>
	Examples:
	 |param1| 
	 |TaqMan Assays, Gene expression, SNP genotyping, MicroRNA, Copy number, Mutation detection| 

@Sanity @Regression 
Scenario Outline: Validate TaqMan Assays search functionality
Given  user is on new magellan search page
When  TaqMan Assays is selected in the dropdown
When  user inputs <param1> in the seach box
And  clicks on the search button
Then  user should see <param2> number of cards which has
	Examples:
	 |param1|param2| 
	 |assays|5| 
	 |arrays|2| 
	 |*|5| 
