package com.acuver.opsroom.automation.test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelTestDataReader {

	private final Workbook workbook;

	public ExcelTestDataReader(String filePath) {
		try {
			workbook = WorkbookFactory.create(new File(filePath));
		} catch (EncryptedDocumentException | IOException e) {
			throw new RuntimeException(e);
		}
	}

	public List<TestData> getTestLines(String environment) {
		Sheet sheet = getSheet(environment);

		List<TestData> lines = new ArrayList<>();
		boolean headerRowRead = false;

		for (Row row : sheet) {
			if (!headerRowRead) {
				headerRowRead = true;
				continue;
			}
			
			String dataId = readCellAsString(row, 0);
			String description = readCellAsString(row, 1);

			String url = readCellAsString(row, 2);
			String username = readCellAsString(row, 3);
			String password = readCellAsString(row, 4);
			String pageLoadTimeoutInSecondsAsString = readCellAsString(row, 5);

			if (dataId.isEmpty() || description.isEmpty() || url.isEmpty() || username.isEmpty() || password.isEmpty()
					|| pageLoadTimeoutInSecondsAsString.isEmpty()) {
				continue;
			}

			lines.add(new TestData(dataId, description, url, username, password,
					new Double(pageLoadTimeoutInSecondsAsString).intValue()));
		}
		return lines;
	}

	public Sheet getSheet(String environment) {
		Sheet sheet = workbook.getSheet(environment);
		if (sheet != null) {
			return sheet;
		}

		throw new RuntimeException(String.format("No sheet with name %s found.", environment));
	}
	
	private String readCellAsString(Row row, int cellIndex) {
		Cell cell = row.getCell(cellIndex);
		if(cell == null) {
			return "";
		}
		return cell.toString().trim();
	}
}
