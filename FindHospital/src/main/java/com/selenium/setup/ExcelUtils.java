package com.selenium.setup;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
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
	public static void writeExcelData(String[] hospName) throws IOException {
		 //Create an object of File class to open xlsx file

        File file =    new File(".\\Driver\\iosheets.xlsx");

        //Create an object of FileInputStream class to read excel file

        FileInputStream inputStream = new FileInputStream(file);

        Workbook wb = null;

        

        wb = new XSSFWorkbook(inputStream);


    //Read excel sheet by sheet name    

    Sheet sheet = wb.getSheet("Outputs");


    //Create a loop over the cell of newly created Row

    for(int j = 0; j <hospName.length; j++){

    	Row newRow = sheet.createRow(j);

        Cell cell = newRow.createCell(0);

        cell.setCellValue(hospName[j]);

    }

    //Close input stream

    inputStream.close();

    //Create an object of FileOutputStream class to create write data in excel file

    FileOutputStream outputStream = new FileOutputStream(file);

    //write data in the excel file

    wb.write(outputStream);

    //close output stream
    wb.close();
    outputStream.close();
	      
	}
	
}