Feature: Tests associated with gene expression search

Scenario Outline: Validate MicroRNA search funcitionality
Given user is on new magellan search page
When MicroRNA is selected in the dropdown
When user inputs <param1> in the seach box 
And clicks on the search button
#Then user should see the text Your search for <param1> returned <param2> <Taqman® Gene Expression Assays & Arrays>

Examples: 
|param1|

@Sanity @Regression @Sprint_GSD2Bullets_18.1
Scenario: Validate action dropdown
Given user is on new magellan search page
When user has selected some assays
Then the select action dropdown should be enabled and clickable

@Sanity @Regression @Sprint_GSD2Bullets_18.1
Scenario: Validate email functionality
Given user is on new magellan search page
When user has selected some assays
And selects email from select action dropdown
And enters a valid email address and clicks ok
Then user should get a success message

@Sanity @Regression @Sprint_GSD2Bullets_18.1
Scenario: Validate export functionality
Given user is on new magellan search page
When user has selected some assays
And selects export from select action dropdown
Then a text file download should get started
