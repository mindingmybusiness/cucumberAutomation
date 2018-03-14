package mainController;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.cucumber.listener.ExtentCucumberFormatter;
import com.cucumber.listener.Reporter;

import driverUtils.BrowserManager;
import excelUtils.ExcelData;
import excelUtils.ExcelHelper;
import excelUtils.ExcelProcessor;
import featureParser.Feature;
import featureParser.FileCrerator;
import featureParser.PackageReader;
import featureParser.TestFeatureParser;

public class MainRun {

	public static void main(String[] args) throws IOException {

		// Read from excel
		String excelFilePath = Constants.RUNNER_PATH;
		String mainRunnerSheet = Constants.MAIN_RUNNER_FILE;

		List<String> browsersToRun = ConfigManager.getBrowsersToRun();
		
		for (String browser : browsersToRun) {
			System.out.println("Executing on browser:" + browser);
			BrowserManager.setBrowserName(browser);
			// Read excel and populate feature run details
			ExcelData mainExcelData = ExcelHelper.readExcelData(excelFilePath, mainRunnerSheet);
			for (int rowIndex = 0; rowIndex <= mainExcelData.fetchRowCount(); rowIndex++) {
				String toolType = mainExcelData.fetchRowValue(rowIndex, "Tool type");
				String runMode = mainExcelData.fetchRowValue(rowIndex, "Run mode");
				if ("Y".equals(runMode) && !StringUtils.isEmpty(toolType)) {
					String tagsTobeExecuted = ConfigManager.getGlobalTags();
					if (StringUtils.isEmpty(tagsTobeExecuted)) {
						tagsTobeExecuted = mainExcelData.fetchRowValue(rowIndex, "Tags to be executed");
					}
					// Read feature data from excel
					ExcelData featureExcelData = ExcelHelper.readExcelData(excelFilePath, toolType);
					Map<String, List<Integer>> scenarioMap = ExcelProcessor.createScenarioMap(toolType);

					// Execute feature data
					executeFeature(toolType, tagsTobeExecuted, featureExcelData, scenarioMap);
				}
			}
		}
	}

	public static void executeFeature(String featureName, String tagsTobeExecuted, ExcelData featureExcelData,
			Map<String, List<Integer>> scenarioMap) throws IOException {

		// Create feature objects from the feature files
		List<Feature> features = constructFeatureObjects(featureName);

		// Generate feature content from feature objects
		String generatedFeatureContent = FileCrerator.generateFeatureFileContent(features, featureExcelData,
				scenarioMap);

		// Write the generated content string to a file
		String generatedFeatureFileName = "generated_" + featureName + ".feature";
		writetofile(generatedFeatureContent, generatedFeatureFileName);

		// Run the generated feature file
		runGeneratedFeatureFile("Features/generated_files/" + generatedFeatureFileName, tagsTobeExecuted);
	}

	public static List<Feature> constructFeatureObjects(String featureName) {
		String featureLocation = System.getProperty("user.dir") + "\\Features\\" + featureName;
		List<String> fileNames = PackageReader.getFileNamesInFolder(featureLocation);

		TestFeatureParser tfp = new TestFeatureParser();
		List<Feature> allFeatures = new ArrayList<>();
		for (String file : fileNames) {
			try {
				List<Feature> results = tfp.mainForParser(featureLocation + "\\" + file);
				if (!CollectionUtils.isEmpty(results)) {
					allFeatures.addAll(results);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return allFeatures;
	}

	public static void runGeneratedFeatureFile(String featurefileLocation, String tagsTobeExecuted) {
		try {
			String[] args = null;
			if (StringUtils.isEmpty(tagsTobeExecuted)) {
				args = new String[] { "--strict", "--glue", "stepDefenitions", "--plugin",
						"com.cucumber.listener.ExtentCucumberFormatter:", featurefileLocation };
				// , "rerun:target/rerun.txt" -- for rerunning failed scenarios
			} else {
				args = new String[] { "--strict", "--glue", "stepDefenitions", "--tags", tagsTobeExecuted, "--plugin",
						"com.cucumber.listener.ExtentCucumberFormatter:", featurefileLocation };

			}

			cucumber.api.cli.Main.run(args, Thread.currentThread().getContextClassLoader());
			prepareReport();
		} catch (Exception ex) {
			System.out.println("ex:" + ex.toString());
		} catch (Throwable e) {
			e.printStackTrace();
		}

		// finally {
		// String[] args = null;
		// if (StringUtils.isEmpty(tagsTobeExecuted)) {
		// args = new String[] { "--strict", "--glue", "stepDefenitions", "--plugin",
		// "com.cucumber.listener.ExtentCucumberFormatter:", featurefileLocation };
		// }

	}

	// List<String> browsersToRun
	public static void prepareReport() throws Exception {
		Reporter.loadXMLConfig(new File("src/main/resources/extent-config.xml"));
		// String browserName = browsersToRun.get(1);
		Reporter.setSystemInfo("user", System.getProperty("user.name"));
		Reporter.setSystemInfo("Operating System", System.getProperty("os.name"));
		Reporter.setSystemInfo("Operating System Version", System.getProperty("os.version"));
		Reporter.setSystemInfo("Enivronment", "QA5");
		// Reporter.setSystemInfo("Browser", browserName);
	}

	// Returns file name
	public static void writetofile(String feature, String featureFileName) throws IOException {
		String filePath = System.getProperty("user.dir") + "\\Features\\generated_files\\" + featureFileName;
		FileWriter fileWrite = new FileWriter(filePath);
		fileWrite.write(feature);
		fileWrite.close();
	}
}
