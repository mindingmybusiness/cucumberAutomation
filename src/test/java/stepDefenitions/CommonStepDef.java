package stepDefenitions;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class CommonStepDef {
	
//	WebDriver driver = null;
//	BrowserManager brow = new BrowserManager();
//	String browser = "Chrome";	
	
	@Given("^user is on thermofisher website$")
	public void validateHomePage() {

//		driver = brow.getDriver(browser);
//		driver.getTitle().equals("Shop All Products Online | Thermo Fisher Scientific");
		
	}

	@When("^user hovers on Sign in option$")
	public void signIn() {
		

	}
	
	@And("^clicks on Sign in button$")
	public void clickSigninButton() {
		
	}

	@Then("^user should be redirected to login page$")
	public void validateLoginPage() {

	}
	
	@Given("^user is on login page$")
	public void verifyIfUserIsOnLoginPage() {
		
	}
	
	@When("^user enters valid ([^\"]*) as username and ([^\"]*) as password$")
	public void enterValidCreds(String un, String pw) {
		
	}
	
	@And("^clicks on login button$")
	public void clickLoginButton() {
		
	}
	
	@Then("^user should be able to see Account button in place of sign in button$")
	public void validateAccountButton() {
		
	}
}
