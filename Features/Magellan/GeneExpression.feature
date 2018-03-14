Feature: Tests associated with gene expression search

Scenario Outline: Validate Gene Expression search funcitionality
Given user is on new magellan search page
When Gene Expression is selected in the dropdown
When user inputs <param1> in the seach box 
And clicks on the search button
#Then user should see the text Your search for <param1> returned <param2> <Taqman® Gene Expression Assays & Arrays>

Examples: 
|param1|
