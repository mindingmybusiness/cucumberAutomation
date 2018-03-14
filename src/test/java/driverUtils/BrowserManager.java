package driverUtils;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.*;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import cucumber.api.Scenario;
import mainController.Constants;

public class BrowserManager {

	protected static WebDriver driver = null;
	public static String browserName = null;

	protected static Logger log = LogManager.getLogger(BrowserManager.class.getName());
	//debug info error fatal

	public static void setBrowserName(String browser) {
		browserName = browser;
	}

	public static WebDriver createNewDriver() {
		return createNewDriver(browserName);
	}

	private static WebDriver createNewDriver(String browser) {
		switch (browser.toUpperCase()) {
		case "CHROME":
			System.setProperty("webdriver.chrome.driver", Constants.CHROME_DRIVER_EXE_PATH);
			driver = new ChromeDriver();
			break;
		case "FIREFOX":
			System.setProperty("webdriver.gecko.driver", Constants.FIREFOX_DRIVER_EXE_PATH);
			driver = new FirefoxDriver();
			break;
		case "IE":
			System.setProperty("webdriver.ie.driver", Constants.IE_DRIVER_EXE_PATH);
			driver = new InternetExplorerDriver();
		default:
			System.setProperty("webdriver.gecko.driver", Constants.IE_DRIVER_EXE_PATH);
			driver = new FirefoxDriver();
			break;
		}
		return driver;
	}

	public static WebDriver getDriver() {
		return driver;
	}
	
	public static Logger getLogger() {
		return log;
	}

	public static void quitDriver() {
		driver.quit();
	}
	
	@SuppressWarnings("null")
	public void takeScreenshot() {
		Scenario sc = null;
		Date d = new Date();
		String screenshotFile = d.toString().replace(":", "_").replace(" ", "_") +sc.getName()+ ".png";
		String filePath = Constants.REPORTS_PATH + "Scenarios//" + screenshotFile;
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(scrFile, new File(filePath));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
