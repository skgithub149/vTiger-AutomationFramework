package genericUtility;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelFileUtility {

	public String readDataFromExcelFile(String sheetName, int rowNum, int cellNum) throws EncryptedDocumentException, IOException {
		FileInputStream fise = new FileInputStream(IConstantsUtility.EXCEL_FILE_PATH);
		Workbook workbook = WorkbookFactory.create(fise);
		Sheet sheet = workbook.getSheet(sheetName);
		Row row = sheet.getRow(rowNum);
		Cell cell = row.getCell(cellNum);
		String value = cell.getStringCellValue();
		return value;
	}
	
	public void writeDataIntoExcel(String sheetName, int rowNum, int cellNum, String value) throws EncryptedDocumentException, IOException {
		FileInputStream fise = new FileInputStream(IConstantsUtility.EXCEL_FILE_PATH);
		Workbook workbook = WorkbookFactory.create(fise);
		Sheet sheet = workbook.getSheet(sheetName);
		Row row = sheet.getRow(rowNum);
		Cell cell = row.createCell(cellNum);
		cell.setCellValue(value);

		FileOutputStream fose = new FileOutputStream(IConstantsUtility.EXCEL_FILE_PATH);
		workbook.write(fose);
	}
	
	public int getRowCount(String sheetName) throws EncryptedDocumentException, IOException {
		FileInputStream fise = new FileInputStream(IConstantsUtility.EXCEL_FILE_PATH);
		Workbook workbook = WorkbookFactory.create(fise);
		int rowCount = workbook.getSheet(sheetName).getLastRowNum();
		return rowCount;
	}
}
