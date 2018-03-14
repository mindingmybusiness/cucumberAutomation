@Commons @Regression @Sanity
Feature: Common functions
This covers all the common scenarios that can be there

Scenario: Validate if user can reach login page
Given user is on thermofisher website
When user hovers on Sign in option
And clicks on Sign in button
Then user should be redirected to login page

Scenario Outline: Validate login funcitonality
Given user is on login page
When user enters valid <param1> as username and <param2> as password
And clicks on login button
Then user should be able to see Account button in place of sign in button

Examples:
|param1|param2|
|unitedstatesindust11|Password1|
|unitedkingdomindust11|Password1|