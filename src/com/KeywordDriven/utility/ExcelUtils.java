package com.KeywordDriven.utility;

import com.KeywordDriven.config.Constants;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.FileInputStream;

public class ExcelUtils {
	
	public static HSSFSheet ExcelWSheet;
	public static HSSFWorkbook ExcelWBook;
	public static HSSFCell Cell;
	
	
	public static void setExcelFile (String FileName) throws Exception {
		FileInputStream ExcelFile = new FileInputStream(FileName);
		ExcelWBook = new HSSFWorkbook(ExcelFile);
		//ExcelWSheet = ExcelWBook.getSheet(Sheet);
	}
	
	public static String getCellData(int RowNum, int ColNum, String Sheet){

		ExcelWSheet = ExcelWBook.getSheet(Sheet);
		Cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);
		String cellData=null; 
		if (Cell != null)
		{
		cellData = Cell.getStringCellValue();
		} 
		/*else
		{
			System.out.println("Cell is Empty in Column");
		}*/
		return cellData;
	}

	public static int getRowCount(String SheetName){
		ExcelWSheet = ExcelWBook.getSheet(SheetName);
		int number=ExcelWSheet.getLastRowNum();
		return number;
	}

	public static int getRowContains (String TestCaseName, int Rowid, String SheetName){
		int i;
		ExcelWSheet = ExcelWBook.getSheet(SheetName);
		int rowCount = ExcelUtils.getRowCount(SheetName);
		for (i = 0; i < rowCount; i++) {
			if (ExcelUtils.getCellData(i,Rowid,SheetName).equalsIgnoreCase(TestCaseName))
			{
				break;
			}
		}
		return i;

	}

	public static int getTestCaseCount (String TestCaseName, int iTestCaseStart, String SheetName) throws Exception{
		for (int i = iTestCaseStart; i < ExcelUtils.getRowCount(SheetName); i++) {
			if (!TestCaseName.equalsIgnoreCase(ExcelUtils.getCellData(i, Constants.Col_TestCaseID, SheetName))){
				int number=i;
				return number;
			}
		}
		ExcelWSheet=ExcelWBook.getSheet(SheetName);
		int number=ExcelWSheet.getLastRowNum()+1;
		return number;
	}
	
}
