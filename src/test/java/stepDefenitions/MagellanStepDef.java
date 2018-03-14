package stepDefenitions;

import org.openqa.selenium.WebDriver;

import businessLogic.MagellanSearch;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import driverUtils.BrowserManager;

public class MagellanStepDef {
	BrowserManager bm = new BrowserManager();
	WebDriver driver = BrowserManager.getDriver();
	
	MagellanSearch mg = new MagellanSearch();

	@Given("^user is on new magellan search page$")
	public void MagellanHome() {
		mg.validateMagellanHome();
		
	}

	@Then("^user should see TaqMan Assays as the default$")
	public void defaultDorpdownValue() {
		mg.validateDefaultDropdownValue();
	}

	@When("^user clicks on the main dropdown$")
	public void clickDropdown() {

	}

	@Then("^user should see ([^\"]*)$")
	public void validateDropdownValues(String param1) {

	}

	@When("^user clicks on the search button$")
	public void clickSearch() {

	}

	@Then("^user should see the cards ([^\"]*)$")
	public void validateCards(String param1) {
	}

	@When("^user has not selected any dropdown value other than default TaqMan Assays$")
	public void selectDefault() {

	}

	@Then("^Build a search button to be disabled$")
	public void buildSearch() {

	}


}
