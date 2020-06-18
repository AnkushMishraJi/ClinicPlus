package com.selenium.setup;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.DataFormatter;
public class ExcelUtils {
	public static String[][] readExcelData(String sheetName) throws Exception {
		   String[][] s=null;
		   FileInputStream fis = new FileInputStream(".\\Driver\\iosheets.xlsx");
		   XSSFWorkbook workbook = new XSSFWorkbook(fis);
		   workbook.getSheet(sheetName);
		   int rCount = workbook.getSheet(sheetName).getLastRowNum();
		   int colCount = workbook.getSheet(sheetName).getRow(0).getLastCellNum();
     
		   s = new String[rCount][colCount];		   
		   for(int i=1; i<rCount+1;i++){
			   for(int j=0;j<colCount-2;j++) {
				   XSSFCell cell = workbook.getSheet(sheetName).getRow(i).getCell(j);
				   s[i-1][j]=cell.getStringCellValue();
			   }			   
		   }
		   DataFormatter formatter = new DataFormatter();
		   XSSFCell cell = workbook.getSheet(sheetName).getRow(1).getCell(colCount-2);
		   s[0][colCount-2]=formatter.formatCellValue(cell);
		   XSSFCell cell1 = workbook.getSheet(sheetName).getRow(1).getCell(colCount-1);
		   s[0][colCount-1]=formatter.formatCellValue(cell1);
		   workbook.close();
		   
		   return s;
	}
	
}