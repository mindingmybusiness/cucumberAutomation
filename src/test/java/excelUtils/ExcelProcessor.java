package excelUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import excelUtils.ExcelHelper;
import mainController.Constants;

import driverUtils.BrowserManager;
import excelUtils.ExcelData;

public class ExcelProcessor {

	public static void mainReader() {
		String excelFilePath = Constants.RUNNER_PATH;
		String mainRunnerSheet = Constants.MAIN_RUNNER_FILE;

		ExcelData mainExcelData = ExcelHelper.readExcelData(excelFilePath, mainRunnerSheet);
		for (int i = 0; i < mainExcelData.fetchRowCount(); i++) {
			// Check for run mode
			if ("N".equals(mainExcelData.fetchRowValue(i, "Run mode"))) {
				System.out.println(
						"Skipping row : " + i + " as run mode is " + mainExcelData.fetchRowValue(i, "Run mode"));
				continue;
			}

//			createScenarioMap(mainExcelData.fetchRowValue(i, "Environment"),
//					mainExcelData.fetchRowValue(i, "Tool type"), mainExcelData.fetchRowValue(i, "Append URL"));
		}
	}

	public static Map<String, List<Integer>> createScenarioMap(String toolType) {
		ExcelData featureExcelData = ExcelHelper.readExcelData(Constants.RUNNER_PATH, toolType);
		Map<String, List<Integer>> scenarioMap = new HashMap<>();
		for (int i = 0; i < featureExcelData.fetchRowCount(); i++) {
//			if ("N".equals(featureExcelData.fetchRowValue(i, "Run mode"))) {
//				System.out.println("Skipping Test" + i + "as Run mode is N");
//				continue;
//			}

			Set<String> scenarioNames = new HashSet<>();
			for (int j = 0; j < featureExcelData.fetchRowCount(); j++) {
				String testScenario = featureExcelData.fetchRowValue(j, "Scenario");
				if (!StringUtils.isEmpty(testScenario)) {
					scenarioNames.add(testScenario);
				}
			}

			for (String scenarioName : scenarioNames) {
				List<Integer> rowIndexes = new ArrayList<>();
				for (int k = 0; k < featureExcelData.fetchRowCount(); k++) {
					if (scenarioName.equals(featureExcelData.fetchRowValue(k, "Scenario"))) {
						rowIndexes.add(Integer.valueOf(k));
					}
				}
				if (rowIndexes.size() > 0) {
					scenarioMap.put(scenarioName, rowIndexes);
				}
			}
		}
		return scenarioMap;
	}


	public static List<String> fetchScenarioParameters(ExcelData ed, int index, List<String> headers) {
		List<String> paramList = new ArrayList<>();
		for (int i = 0; i < headers.size(); i++) {
			String parameterValue = ed.fetchRowValue(index, headers.get(i));
			if (parameterValue == null) {
				parameterValue = "";
			}
			paramList.add(parameterValue);
		}

		return paramList;
	}

	// System.out.println(String.join(",", featureExcelData.getHeaders()));
}
