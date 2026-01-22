package com.dataprovider;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility {

	static XSSFWorkbook wb = null;

	public static Object[][] getData(String sheetName) {

		FileInputStream fis = null;

		File src = new File(System.getProperty("user.dir") + "/Testdata/LoginData.xlsx");

		try {

			fis = new FileInputStream(src);

		} catch (FileNotFoundException e) {

			System.out.println("Couldn't read the file in the location" + e.getMessage());

		}
		try {
			wb = new XSSFWorkbook(fis);

		} catch (IOException e) {

			System.out.println("Couldn't load the file" + e.getMessage());

		}

		int row = wb.getSheet(sheetName).getPhysicalNumberOfRows();

		int column = wb.getSheet(sheetName).getRow(0).getPhysicalNumberOfCells();

		System.out.println("Columns detected: " + column);

		Object[][] arr = new Object[row - 1][column];

		for (int i = 1; i < row; i++) {

			for (int j = 0; j < column; j++) {

				arr[i - 1][j] = ExcelUtility.getCellData(sheetName, i, j);

			}

		}

		return arr;

	}

	public static Object[][] getData(String excelName, String sheetName) { // use when having multiple excel sheets

		FileInputStream fis = null;

		File src = new File(System.getProperty("user.dir") + "/Testdata/"+excelName+".xlsx");

		try {

			fis = new FileInputStream(src);

		} catch (FileNotFoundException e) {

			System.out.println("Couldn't read the file in the location" + e.getMessage());

		}
		try {
			wb = new XSSFWorkbook(fis);

		} catch (IOException e) {

			System.out.println("Couldn't load the file" + e.getMessage());

		}

		int row = wb.getSheet(sheetName).getPhysicalNumberOfRows();

		int column = wb.getSheet(sheetName).getRow(0).getPhysicalNumberOfCells();

		System.out.println("Columns detected: " + column);

		Object[][] arr = new Object[row - 1][column];

		for (int i = 1; i < row; i++) {

			for (int j = 0; j < column; j++) {

				arr[i - 1][j] = ExcelUtility.getCellData(sheetName, i, j);

			}

		}

		return arr;

	}

	public static String getCellData(String SheetName, int row, int column) {

		XSSFCell cell = wb.getSheet(SheetName).getRow(row).getCell(column);

		String data = "";

		CellType celltype = cell.getCellType();

		if (celltype == CellType.STRING) {

			data = cell.getStringCellValue();

		}

		if (celltype == CellType.BOOLEAN) {

			data = String.valueOf(cell.getBooleanCellValue());

		}

		if (celltype == CellType.NUMERIC) {

			data = String.valueOf(cell.getNumericCellValue());

		}

		if (celltype == CellType.BLANK) {

			data = "";

		}

		return data;

	}

}
