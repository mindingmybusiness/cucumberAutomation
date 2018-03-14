@Run_1518879046673
Feature: CurrentRun


@Sanity @Sprint17.0.1 @CustomArray @CA_TS0001 
Scenario: Validate if user is on custom array page
Given  user is on thermofisher website
When  user navigates to custom array page
Then  user should see the text Array Configurator

@Sanity @Sprint17.0.1 @CustomArray @CA_TS0002 @Regression 
Scenario Outline: Validate the dropdown values
Given  user is on the custom array page
When  user clicks on dropdown for selecting array type
Then  user should be able to see the <param1> array type
	Examples:
	 |param1| 
	 |Custom MiRNA Array Panels| 
	 |TaqMan® Advanced miRNA Arrays Micro Fluidic Panels| 
	 |TaqMan® Array Micro Fluidic Cards| 
	 |TaqMan® Arrays Fast 96-well Plates| 
	 |TaqMan® Arrays Standard 96-well Plates| 
	 |TaqMan® OpenArray® Advanced miRNA Panels| 
	 |TaqMan® OpenArray® Genotyping Plates| 
	 |TaqMan® OpenArray® Real-Time PCR Custom Assays Format| 
	 |TaqMan® OpenArray® Real-Time PCR Inventoried Assays Format| 

@CA2 @Sprint17.0.2 @CA_TS0003 @Sprint17.0.3 @Regression 
Scenario Outline: Validate the format table visibility
Given  user is on the custom array page
When  user selects <param1> from custom array dropdown
Then  user should see array format table for <param2> with <param3> SKUs/formats
	Examples:
	 |param1|param2|param3| 
	 |Custom MiRNA Array Panels|Custom MiRNA Array Panels|10| 
	 |TaqMan® Advanced miRNA Arrays Micro Fluidic Panels|TaqMan® Advanced miRNA Arrays Micro Fluidic Panels|10.0| 
	 |TaqMan® Array Micro Fluidic Cards|TaqMan® Array Micro Fluidic Cards|10.0| 
	 |TaqMan® Arrays Fast 96-well Plates|TaqMan® Arrays Fast 96-well Plates|9.0| 
	 |TaqMan® Arrays Standard 96-well Plates|TaqMan® Arrays Standard 96-well Plates|9.0| 
	 |TaqMan® OpenArray® Advanced miRNA Panels|TaqMan® OpenArray® Advanced miRNA Panels|5.0| 
	 |TaqMan® OpenArray® Genotyping Plates|TaqMan® OpenArray® Genotyping Plates|6.0| 
	 |TaqMan® OpenArray® Real-Time PCR Custom Assays Format|TaqMan® OpenArray® Real-Time PCR Custom Assays Format|5.0| 
	 |TaqMan® OpenArray® Real-Time PCR Inventoried Assays Format|TaqMan® OpenArray® Real-Time PCR Inventoried Assays Format|5.0| 

@Sanity 
Scenario: validate custom Array page
Given  user is on home page
When  user navigates to custom array page
Then  user should be able to see the text Welcome to the TaqMan® Array Configurator

@Sanity 
Scenario Outline: validate format table for all the product types
Given  user is on custom array page
When  user selects <param1> from dropdown
And  validates all the values in format table
Then  user should be seeing the format table for <param1>
	Examples:
	 |param1| 
	 |TaqMan® Advanced miRNA Arrays Micro Fluidic Panels| 
	 |TaqMan® Array Micro Fluidic Cards| 
	 |TaqMan® Advanced miRNA Arrays Micro Fluidic Panels| 

@Sanity 
Scenario Outline: Validate add to cart functionality for all product types
Given  user has selected <param1> and <param2>
When  user configures the product
And  adds the product to cart
Then  user should see the product of type <param1> in the cart
	Examples:
	 |param1|param2| 
	 |Custom MiRNA Array Panels|12| 
	 |Custom MiRNA Array Panels|16| 
	 |Custom MiRNA Array Panels|24| 
	 |TaqMan® Advanced miRNA Arrays Micro Fluidic Panels|12| 
	 |TaqMan® Advanced miRNA Arrays Micro Fluidic Panels|16| 
	 |TaqMan® Advanced miRNA Arrays Micro Fluidic Panels|24| 
	 |TaqMan® Advanced miRNA Arrays Micro Fluidic Panels|32| 
	 |TaqMan® Array Micro Fluidic Cards|12| 
	 |TaqMan® Array Micro Fluidic Cards|16| 
	 |TaqMan® Array Micro Fluidic Cards| 
	 |TaqMan® Array Micro Fluidic Cards| 
	 |TaqMan® Advanced miRNA Arrays Micro Fluidic Panels| 
	 |TaqMan® Advanced miRNA Arrays Micro Fluidic Panels| 
	 |TaqMan® Advanced miRNA Arrays Micro Fluidic Panels| 
