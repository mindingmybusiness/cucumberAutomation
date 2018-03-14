package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import driverUtils.BrowserManager;

public class Magellan {
	WebDriver driver = BrowserManager.getDriver();

	@FindBy(xpath = "//h2[@class='search-heading row']")
	private WebElement searchHeader;

	@FindBy(xpath= "//*[@class ='select-box' and contains(text(),'TaqMan Assays')]")
	private WebElement searchDropdown;
//	
//	@FindBy(xpath = "//button[@class = 'select-element'][0]")
//	private WebElement searchDropdownbutton;
	
//	@FindBys( {
//		   @FindBy(className = "class1"),
//		   @FindBy(className = "class2")
//		} )
//	private List<WebElement> searchDropdownValues;

	@FindBy()
	private WebElement searchBar;

	@FindBy()
	private WebElement searchButton;

	@FindBy()
	private WebElement buildASearch;
	
	
	public WebElement getSearchHeader() {
		return searchHeader;
	}

	public WebElement getSearchDropdown() {
		return searchDropdown;
	}

	public WebElement getSearchBar() {
		return searchBar;
	}

	public WebElement getSearchButton() {
		return searchButton;
	}

	public WebElement getBuildASearch() {
		return buildASearch;
	}

	
	public Magellan(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);

	}
}
