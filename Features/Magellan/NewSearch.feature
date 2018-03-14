@MagellanNewSearchTool
Feature: New UI for Magellan Search

#@Sanity @Regression
#Scenario: Validate new UI
#Given user is on home page
#When user selects TaqMan Real-Time PCR Assays from Shop All Products
#Then Assay search tool—find your TaqMan Assays: should be displayed

@Sanity @Regression
Scenario: Validate search page UI
Given user is on new magellan search page
Then user should see TaqMan Assays as the default text

Scenario Outline: Validate dropdown values on new search page
Given user is on new magellan search page
When user clicks on the main dropdown
Then user should see <param1>
Examples:
|param1|
|TaqMan Assays, Gene expression, SNP genotyping, MicroRNA, Copy number, Mutation detection|

#@Sanity @Regression
#Scenario Outline: Validate default star search
#Given user is on new magellan search page
#When user clicks on the search button
#Then user should see the cards <param1>
#Examples:
#|param1|
#|TaqMan Assays, Gene expression, SNP genotyping, MicroRNA, Copy number, Mutation detection|
#
#@Sanity @Regression
#Scenario: Validate disabled build a search button 
##by default
#Given user is on new magellan search page
#When user has not selected any dropdown value other than default TaqMan Assays
#Then Build a search button to be disabled
#
#@Sanity @Regression
#Scenario: Validate disabled build a search button
##on search
#Given user is on new magellan search page
#When user has not selected any dropdown value other than default TaqMan Assays
#When user clicks on the search button
#Then Build a search button to be disabled




