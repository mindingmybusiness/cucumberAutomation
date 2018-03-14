@Sanity @Sprint17.0.1 @CustomArray
Feature: custom array feature file1
description for feature file1

@CA_TS0001
Scenario: Validate if user is on custom array page
Given user is on thermofisher website
When user navigates to custom array page
Then user should see the text Array Configurator

@CA_TS0002 @Regression
Scenario Outline: Validate the dropdown values
Given user is on the custom array page
When user clicks on dropdown for selecting array type
Then user should be able to see the <param1> array type

Examples:
|param1|
|Custom MiRNA Array Panels|