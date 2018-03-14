@Sanity @Regression 
Feature: Tests associated with TaqMan Assay search 

Scenario Outline: Validate TaqMan Assays search functionality
Given user is on new magellan search page
When TaqMan Assays is selected in the dropdown
When user inputs <param1> in the seach box 
And clicks on the search button
Then user should see <param2> number of cards which has 
Examples:
|param1|param2|
|assays|5|
|arrays|2|
|	*  |5|