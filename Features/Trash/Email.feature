@Magellan1 @Regression @Sanity @Sprint17.0.5
Feature: feature1
feature1 makes sure that magellan is working fine

@MA_TS0001
Scenario: Validate Magellan Search page
Given user is on thermofisher website
When user navigates to magellan url
Then user should see the magellan search options

@MA_TS0002
Scenario Outline: Magellan search
Given user is on magellan search page
When user selects <param1>
Then user should see the respective search parameters for <param1>
Examples:
|param1|
|Gene Expression|
|SNP Genotyping|