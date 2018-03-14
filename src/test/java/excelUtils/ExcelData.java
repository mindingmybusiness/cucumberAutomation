package excelUtils;

import java.util.ArrayList;
import java.util.List;

public class ExcelData {
	private List<String> headers;
	private List<List<String>> data;
	private String sheetName;
	private String filePath;

	public ExcelData() {
		super();
	}

	public List<String> getHeaders() {
		return headers;
	}

	public void setHeaders(List<String> headers) {
		this.headers = headers;
	}

	public List<List<String>> getData() {
		return data;
	}

	public void setData(List<List<String>> data) {
		this.data = data;
	}

	public String getSheetName() {
		return sheetName;
	}

	public void setSheetName(String sheetName) {
		this.sheetName = sheetName;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public void addNewRow(List<String> row) {
		if (this.data == null) {
			this.data = new ArrayList<>();
		}
		this.data.add(row);
	}

	public String fetchRowValue(int rowIndex, String headerName) {
		if (this.headers == null || this.headers.indexOf(headerName) < 0  || this.data == null
				|| rowIndex >= this.data.size() 
				||  this.headers.indexOf(headerName) >= this.data.get(rowIndex).size()) {
			return "";
		}

		return this.data.get(rowIndex).get(this.headers.indexOf(headerName));
	}

	public int fetchRowCount() {
		return this.data == null ? 0 : this.data.size();
	}
	
	@Override
	public String toString() {
		return "ExcelData [headers=" + headers + ", data=" + data + ", sheetName=" + sheetName + ", filePath="
				+ filePath + "]";
	}
}
