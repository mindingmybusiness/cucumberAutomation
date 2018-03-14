@CA2 @Sprint17.0.2
Feature: feature2

@CA_TS0003 @Sprint17.0.3 @Regression
Scenario Outline: Validate the format table visibility
Given user is on the custom array page
When user selects <param1> from custom array dropdown
Then user should see array format table for <param2> with <param3> SKUs/formats

Examples:
|param1|param2|param3|
|Custom MiRNA Array Panels|Custom MiRNA Array Panels|10|