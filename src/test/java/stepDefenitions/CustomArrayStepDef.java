package stepDefenitions;

import org.openqa.selenium.WebDriver;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import driverUtils.BrowserManager;
import mainController.ConfigManager;

public class CustomArrayStepDef {

	BrowserManager bm = new BrowserManager();
	WebDriver driver = bm.getDriver();

	@When("^user navigates to custom array page$")
	public void goToCustomArrayPage() {
		
		//WebDriver driver = BrowserManager.getDriver();
		driver.get(ConfigManager.getBaseUrl() + "/order/custom-array");
	}

	@Then("^user should see the text Array Configurator$")
	public void validateTextOnCAPage() {
		//driver.findElement(By.xpath(""));
	}

	@Given("^user is on the custom array page$")
	public void customArrayPage() {
		

	}

	@When("^user clicks on dropdown for selecting array type$")
	public void clickDropdown() {

	}

	@Then("^user should be able to see the ([^\"]*) array type$")
	public void listArrayTypes(String param1) {

	}

	@When("^user selects ([^\"]*) from custom array dropdown$")
	public void selectArrayType(String arrayType) {

	}

	@Then("^user should see array format table for ([^\"]*) with ([^\"]*) SKUs/formats$")
	public void validateFormatTable(String arrayType, String noOfSkus) {

	}
}
