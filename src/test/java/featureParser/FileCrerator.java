package featureParser;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections4.CollectionUtils;

import excelUtils.ExcelData;
import excelUtils.ExcelProcessor;
import featureParser.Feature.Example;
import featureParser.Feature.Row;
import featureParser.Feature.Scenario;
import featureParser.Feature.Step;
import featureParser.Feature.Tag;

public class FileCrerator {
	// , ExcelData featureExcelData
	public static String generateFeatureFileContent(List<Feature> features, ExcelData featureExcelData,
			Map<String, List<Integer>> scenarioMap) {

		StringBuilder result = new StringBuilder("@Run_");
		result.append(System.currentTimeMillis() + "\n" + "Feature: CurrentRun" + "\n\n");

		if (!CollectionUtils.isEmpty(features)) {
			for (Feature currentFeature : features) {
				List<Tag> featureTags = currentFeature.getTags();
				for (Scenario scenario : currentFeature.getElements()) {
					result.append("\n");
					if (!CollectionUtils.isEmpty(featureTags)) {
						for (Tag tag : featureTags) {
							result.append(tag.getName() + " ");
						}
					}
					if (!CollectionUtils.isEmpty(scenario.getTags())) {
						for (Tag tag : scenario.getTags()) {
							result.append(tag.getName() + " ");
						}
					}
					if (!scenario.getKeyword().equals("Scenario Outline")) {
						result.append("\n" + "Scenario: " + scenario.getName() + "\n");
						appendSteps(scenario, result);
					} else {
						// result.append(scenario.getTags().toString());
						result.append("\n" + "Scenario Outline: " + scenario.getName() + "\n");
						appendSteps(scenario, result);
						appendExamples(scenario, result, featureExcelData, scenarioMap);
					}
				}
			}
		}

		// System.out.println(result);
		return result.toString();
	}

	public static StringBuilder appendSteps(Scenario sc, StringBuilder result) {
		ArrayList<Step> steps = sc.getSteps();
		for (Step step : steps) {
			result.append(step.getKeyword() + " " + step.getName() + "\n");
		}
		return result;
	}

	public static StringBuilder appendExamples(Scenario sc, StringBuilder result, ExcelData featureExcelData,
			Map<String, List<Integer>> scenarioMap) {
		ArrayList<Example> examples = sc.getExamples();
		String examplesStr = "";
		if (!CollectionUtils.isEmpty(examples)) {
			examplesStr += "\t" + "Examples:" + "\n";
			for (Example exm : examples) {
				if (!CollectionUtils.isEmpty(exm.getRows())) {
					for (Row row : exm.getRows()) {
						if (!CollectionUtils.isEmpty(row.getCells())) {
							examplesStr += "\t |" + String.join("|", row.getCells()) + "| \n";
						}
					}
				}
			}
			result.append(examplesStr);
		}

		// Append results from excel
		Set<String> validScenario = scenarioMap.keySet();
		String currrentExamples = "";

		if (validScenario.contains(sc.getName())) {
			List<String> headers = sc.getExamples().get(0).getRows().get(0).getCells();
			List<Integer> rowIndexes = scenarioMap.get(sc.getName());

			for (Integer rowIndex : rowIndexes) {
				List<String> values = ExcelProcessor.fetchScenarioParameters(featureExcelData, rowIndex, headers);
				// System.out.println(String.join(",", values));
				currrentExamples += "\t |" + String.join("|", values) + "| \n";
			}
			result.append(currrentExamples);
		} else {
			System.out.println("No exampes in excel sheet for scenario" + sc.getName());
		}

		return result;
	}
}
