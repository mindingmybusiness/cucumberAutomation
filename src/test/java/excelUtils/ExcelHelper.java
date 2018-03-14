package excelUtils;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import excelUtils.ExcelData;

public class ExcelHelper {
	public static HashMap<String, String> storeValues = new HashMap<String, String>();

	public static List<HashMap<String, String>> getDataAsListOfMaps(String filepath, String sheetName) {
		List<HashMap<String, String>> mydata = new ArrayList<>();
		try {
			FileInputStream fs = new FileInputStream(filepath);
			XSSFWorkbook workbook = new XSSFWorkbook(fs);
			XSSFSheet sheet = workbook.getSheet(sheetName);
			Row HeaderRow = sheet.getRow(0);
			for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {
				Row currentRow = sheet.getRow(i);
				HashMap<String, String> currentHash = new HashMap<String, String>();
				for (int j = 0; j < currentRow.getPhysicalNumberOfCells(); j++) {
					try {
						Cell currentCell = currentRow.getCell(j);

						switch (currentCell.getCellType()) {
						case Cell.CELL_TYPE_STRING:
							// System.out.print(currentCell.getStringCellValue() + "\t");
							currentHash.put(HeaderRow.getCell(j).getStringCellValue(),
									currentCell.getStringCellValue());
							break;
						}
					} catch (Exception e) {
						e.printStackTrace();
					}

				}
				mydata.add(currentHash);
			}
			fs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mydata;
	}

	/**
	 * Below is to get the test data as list from an excel sheet
	 * shubha.chakrala@thermofisher.com
	 */
	public static ExcelData readExcelData(String filePath, String sheetName) {
		ExcelData ed = new ExcelData();
		ed.setFilePath(filePath);
		ed.setSheetName(sheetName);

		try (FileInputStream fs = new FileInputStream(filePath)) {
			XSSFWorkbook workbook = new XSSFWorkbook(fs);
			XSSFSheet sheet = workbook.getSheet(sheetName);

			// Read the headers
			ed.setHeaders(readRow(sheet.getRow(0)));

			// Read rows
			for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {
				ed.addNewRow(readRow(sheet.getRow(i)));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return ed;
	}

	public static List<String> readRow(Row row) {
		List<String> rowData = new ArrayList<>();
		if (row != null) {
			for (int i = 0; i < row.getPhysicalNumberOfCells(); i++) {
				rowData.add(row.getCell(i) == null ? "" : row.getCell(i).toString());
			}
		}
		return rowData;
	}

}
