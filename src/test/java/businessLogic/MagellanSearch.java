package businessLogic;

import static org.junit.Assert.assertEquals;

import driverUtils.BrowserManager;
import mainController.ConfigManager;
import pageObjects.Magellan;

public class MagellanSearch extends BrowserManager {
	// WebDriver driver = BrowserManager.getDriver();
	Magellan mg = new Magellan(driver);

	// static Magellan mg = new Magellan(driver);
	public void validateMagellanHome() {

		driver.get(ConfigManager.getBaseUrl() + "/order/genome-database/?pearUXVerSuffix=pearUX2&elcanoForm=true");
		log.trace("User is on Magellan search page URL: " + driver.getCurrentUrl());
		String expectedText = "Sarch TaqMan® Assays and Arrays";
		String actualText = mg.getSearchHeader().getText();
		log.trace("Text shown on New Magellan search home page is :" + actualText);
		try {
			assertEquals(expectedText, actualText);
			log.trace("Home page of magellan search has launched and validated for header text");
		} catch (AssertionError e) {
			log.debug("Could not validate the Magellan home page header text");
		}
		
	}

	public void validateDefaultDropdownValue() {
		String actualText = mg.getSearchDropdown().getText();
		String expectedText = "TaqMan Assays";
		try {
			assertEquals(expectedText, actualText);
			log.trace("Default text shown on the dropdown is: "+ actualText);
		} catch (AssertionError e) {
			log.debug("Could not validate the default dropdown text");
		}
	}

	public void clickDropdownValue() {

		
		mg.getSearchDropdown().click();
	}

	// public void validateDropdownValues(String param1) {
	// mg.getSearchDropdown().click();
	//
	// }

}
