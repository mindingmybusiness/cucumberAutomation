package mainController;

public class Constants {

	// Driver Path - saved as Drivers within the package
	public static final String IE_DRIVER_EXE_PATH = System.getProperty("user.dir") + "\\Drivers\\IEDriverServer.exe";
	public static final String FIREFOX_DRIVER_EXE_PATH = System.getProperty("user.dir") + "\\Drivers\\geckodriver.exe";
	public static final String CHROME_DRIVER_EXE_PATH = System.getProperty("user.dir") + "\\Drivers\\chromedriver.exe";
	public static final String REPORTS_PATH = System.getProperty("user.dir") + "\\output" +"\\failedScreenshots\\";
	
	//config property file path
	public static final String CONFIG_PROPERTIES = "configuration/config.properties";

	// Excel Workbook and SheetName path
	public static final String RUNNER_PATH = System.getProperty("user.dir")
			+ "\\Data\\CurrentRunData\\CurrentRunManager.xlsx";
	public static final String MAIN_RUNNER_FILE = "Main";

	// Keys
	public static final String KEY_BROWSER_CONFIG_PARAM = "browser";
	public static final String KEY_BASE_URL = "baseurl";
	public static final String KEY_GLOBAL_TAGS = "globalTags";

}
