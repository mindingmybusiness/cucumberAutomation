package stepDefenitions;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import driverUtils.BrowserManager;
import mainController.Constants;

public class Hooks {
	@Before
	public void launchBrowser(Scenario sc) {
		// Launch a new browser
		BrowserManager.createNewDriver();
		BrowserManager.getLogger().trace("Current running Scenario:"+ sc.getName());
	}

	@After
	public void tearDown(Scenario sc) {

		if (sc.isFailed()) {

			Date d = new Date();
			String screenshotFile = d.toString().replace(":", "_").replace(" ", "_") + sc.getName() + ".png";
			String filePath = Constants.REPORTS_PATH + screenshotFile;
			WebDriver driver = BrowserManager.getDriver();
			// store screenshot in that file
			File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			try {
				FileUtils.copyFile(scrFile, new File(filePath));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		BrowserManager.quitDriver();
	}
}