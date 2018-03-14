package mainController;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections4.CollectionUtils;

import excelUtils.ExcelData;
import excelUtils.ExcelHelper;
import excelUtils.ExcelProcessor;
import featureParser.Feature;
import featureParser.Feature.Example;
import featureParser.Feature.Scenario;
import featureParser.Feature.Step;
import featureParser.Feature.Tag;
import featureParser.TestFeatureParser;

public class MainRunner {
	public static void main(String[] args) throws Exception {
		long startTime = System.currentTimeMillis();
		System.out.println("Start time:" + startTime);
		String excelFilePath = Constants.RUNNER_PATH;
		String mainRunnerSheet = Constants.MAIN_RUNNER_FILE;

		// Read excel
		ExcelData mainExcelData = ExcelHelper.readExcelData(excelFilePath, mainRunnerSheet);

		// TODO : Validate main data

		for (int i = 0; i < mainExcelData.fetchRowCount(); i++) {
			// Check for run mode
			if ("N".equals(mainExcelData.fetchRowValue(i, "Run mode"))) {
				System.out.println(
						"Skipping row : " + i + " as run mode is " + mainExcelData.fetchRowValue(i, "Run mode"));
				continue;
			}

			// Generate feature file for the current sheet
			ExcelData featureExcelData = ExcelHelper.readExcelData(Constants.RUNNER_PATH,
					mainExcelData.fetchRowValue(i, "Tool type"));
			generateFeatureFile(featureExcelData, mainExcelData.fetchRowValue(i, "Environment"),
					mainExcelData.fetchRowValue(i, "Tool type"), mainExcelData.fetchRowValue(i, "Append URL"));

			// Run the generated feature file
			System.out.println(
					"Time taken to construct feature file(milliseconds) : " + (System.currentTimeMillis() - startTime));
			runGeneratedFeatureFile("featuresAsTextFiles");
			System.out.println(
					"Time taken for entire execution(milliseconds) : " + (System.currentTimeMillis() - startTime));
		}
	}

	public static void runGeneratedFeatureFile(String featurefileLocation) {
		try {
			String[] args1 = new String[] { "--strict", "--glue", "stepDefs", "--plugin", "pretty",
					featurefileLocation };
			cucumber.api.cli.Main.main(args1);
		} catch (Exception ex) {
			System.out.println("ex:" + ex.toString());
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	// public static void executeFeature(String environment, String toolType, String
	// appendUrl) {
	//
	// System.out.println(String.join(",", featureExcelData.getHeaders()));
	// }

	public static void generateFeatureFile(ExcelData featureExcelData, String environment, String featureFileName,
			String appendUrl) throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\" + featureFileName
				+ "\\VerifyArrayTypeDropdownValues.feature";
		TestFeatureParser parse = new TestFeatureParser();
		List<Feature> features = parse.mainForParser(filePath);

		Map<String, List<Integer>> scenarioMap = ExcelProcessor.createScenarioMap(featureFileName);
		if (!CollectionUtils.isEmpty(features)) {
			// Read only the first feature for now
			String newFeature = constructNewFeatureFile(features.get(0), scenarioMap, featureExcelData);
			writetofile(newFeature);
		}
	}

	// List<String> fileNames = PackageReader.getFileNamesInFolder(filePath);
	// int count = 0;
	//
	// ExcelProcessor ep = ExcelProcessor.createScenarioMap(environment, toolType,
	// appendUrl);
	// for (String file : fileNames) {
	// String fileName = filePath + "\\" + file;
	// Feature[] features = parse.mainForParser(fileName);
	// for (Feature feature : features) {
	// String newFeature = constructNewFeatureFile(feature, featureExcelData);
	// writetofile(newFeature, count);
	// count++;
	// }
	// }

	public static String constructNewFeatureFile(Feature feature, Map<String, List<Integer>> scenarioMap,
			ExcelData ed) {
		// Feature: Custom Array Tool
		String result = "@Run_" + System.currentTimeMillis() + "\n" + "Feature: " + feature.getName() + "\n\n";
		Set<String> validTags = scenarioMap.keySet();
		if (!CollectionUtils.isEmpty(feature.getElements())) {
			for (Scenario scenario : feature.getElements()) {
				feature.getTags();
				String scenarioStr = "";

				// Check if this scenario needs to be executed
				boolean containsTag = false;
				String currrentExcelTagName = "";
				for (Tag scTag : scenario.getTags()) {
					scenarioStr += scTag.getName() + "\n";
					if (validTags.contains(scTag.getName())
							|| validTags.contains(getTagNameExcelFormat(scTag.getName()))) {
						currrentExcelTagName = getTagNameExcelFormat(scTag.getName());
						containsTag = true;
					}
				}

				if (!containsTag) {
					System.out.println("Skipping scenario:" + scenario.getName());
					continue;
				} else {
					System.out.println("Running scenario:" + scenario.getName());
				}

				// Construct the scenario string
				scenarioStr += "Scenario Outline:" + scenario.getName() + "\n";
				if (!CollectionUtils.isEmpty(scenario.getSteps())) {
					for (Step step : scenario.getSteps()) {
						scenarioStr += " " + step.getKeyword() + " " + step.getName() + "\n";
					}
				}
				if (!CollectionUtils.isEmpty(scenario.getExamples())) {
					// Populate the example headers and other parameters provided in the feature
					// file
					for (Example exm : scenario.getExamples()) {
						scenarioStr += "\n" + "\t" + "Examples:" + "\n";
						if (!CollectionUtils.isEmpty(exm.getRows())) {
							for (featureParser.Feature.Row row : exm.getRows()) {
								if (!CollectionUtils.isEmpty(row.getCells())) {
									scenarioStr += "\t\t |" + String.join("|", row.getCells()) + "| \n";
								}
							}
						}
					}

					// Append parameters provided in the excel
					List<String> headers = scenario.getExamples().get(0).getRows().get(0).getCells();
					System.out.println(String.join(",", headers));

					List<Integer> rowIndexes = scenarioMap.get(currrentExcelTagName);
					for (Integer rowIndex : rowIndexes) {
						List<String> values = ExcelProcessor.fetchScenarioParameters(ed, rowIndex, headers);
						System.out.println(String.join(",", values));
						scenarioStr += "\t\t |" + String.join("|", values) + "| \n";
					}
					scenarioStr += "\n";
				}
				result += scenarioStr;
			}
		}
		return result;
	}

	public static void writetofile(String feature) throws IOException {
		String filePath = System.getProperty("user.dir") + "\\featuresAsTextFiles\\constructedFeatureFile.feature";
		FileWriter fileWrite = new FileWriter(filePath);
		fileWrite.write(feature);
		fileWrite.close();
	}

	public static String getTagNameExcelFormat(String tagName) {
		return tagName.replace("@", "");
	}

}
