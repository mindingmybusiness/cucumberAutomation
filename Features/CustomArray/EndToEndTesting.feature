@Sanity 
Feature: Custom Array functional validation end to end 
			A B2C customer of any locale should be able to
			configure a product with any SKU 
			and add the same to the cart

Scenario: validate custom Array page 
	Given user is on home page 
	When user navigates to custom array page 
	Then user should be able to see the text Welcome to the TaqMan® Array Configurator 
	
	
Scenario Outline: validate format table for all the product types 
	Given user is on custom array page 
	When user selects <param1> from dropdown 
	And validates all the values in format table 
	Then user should be seeing the format table for <param1> 
	Examples: 
		|param1											   |
		|TaqMan® Advanced miRNA Arrays Micro Fluidic Panels|
		|TaqMan® Array Micro Fluidic Cards				   |
		|TaqMan® Advanced miRNA Arrays Micro Fluidic Panels|
		
#Scenario Outline: validate format table for all the product types in the configurator page 
#	Given user is on custom array configurator page by selecting <param1> and <param2>
#	When user selects <param1> from dropdown 
#	And validates all the values in format table 
#	Then user should be seeing the format table for <param1> 
#	Examples: 
#		|param1											   |param2|
#		|TaqMan® Advanced miRNA Arrays Micro Fluidic Panels|12|
#		|TaqMan® Advanced miRNA Arrays Micro Fluidic Panels|16|
#		|TaqMan® Array Micro Fluidic Cards				   |12|
#		|TaqMan® Advanced miRNA Arrays Micro Fluidic Panels|24|
		
Scenario Outline: Validate add to cart functionality for all product types 
	Given user has selected <param1> and <param2>
	When user configures the product
	And adds the product to cart
	Then user should see the product of type <param1> in the cart
Examples:
		|param1											   |param2|
		|Custom MiRNA Array Panels							|12| 
		|Custom MiRNA Array Panels							|16| 
		|Custom MiRNA Array Panels							|24| 		
		|TaqMan® Advanced miRNA Arrays Micro Fluidic Panels|12|
		|TaqMan® Advanced miRNA Arrays Micro Fluidic Panels|16|
		|TaqMan® Advanced miRNA Arrays Micro Fluidic Panels|24|
		|TaqMan® Advanced miRNA Arrays Micro Fluidic Panels|32|
		|TaqMan® Array Micro Fluidic Cards				   |12|
		|TaqMan® Array Micro Fluidic Cards				   |16|
		|TaqMan® Array Micro Fluidic Cards				   |
		|TaqMan® Array Micro Fluidic Cards				   |
		|TaqMan® Advanced miRNA Arrays Micro Fluidic Panels|
		|TaqMan® Advanced miRNA Arrays Micro Fluidic Panels|
		|TaqMan® Advanced miRNA Arrays Micro Fluidic Panels|
	
