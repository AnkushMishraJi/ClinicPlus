package com.selenium.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.selenium.pages.*;
public class InvalidTest extends TestBase {
	
	@Test(priority = 12)
	public void invalidDetails1() throws Exception {
		String[][] data=null;
        data=getExcelData();
        corporatewellness.invalidDetails1(data[0][2],data[0][3],data[0][4],data[0][5]);
	}
	
	@Test(priority = 13)
	public void testDisplayError1() {
		corporatewellness.getError1();
	}
	
	@Test(priority = 14)
	public void invalidDetails2() throws Exception {
		String[][] data=null;
        data=getExcelData();
        corporatewellness.invalidDetails2(data[0][2],data[0][3],data[0][4],data[0][5]);
	}
	
	@Test(priority = 15)
	public void testDisplayError2() {
		corporatewellness.getError2();
	}
	
	@Test(priority = 16)
	public void invalidDetails3() throws Exception {
		String[][] data=null;
        data=getExcelData();
        corporatewellness.invalidDetails3(data[0][2],data[0][3],data[0][4],data[0][5]);
	    
	}
	
	@Test(priority = 17)
	public void testDisplayError3() {
		corporatewellness.getError3();
	}
	
	@Test(priority = 18)
	public void invalidDetails4() throws Exception {
		String[][] data=null;
        data=getExcelData();
        corporatewellness.invalidDetails4(data[0][2],data[0][3],data[0][4],data[0][5]);
	}
	
	@Test(priority = 19)
	public void testDisplayError4() {
		corporatewellness.getError4();
	}
	@Test(priority = 22)
	public void inValidName() throws Exception {
		String[][] data=null;
        data=getExcelData();
        corporatewellness.validDetails6(data[0][6],data[0][3],data[0][4],data[0][5]);
	}
	
	@Test(priority = 23)
	public void displayErrorInvalidName() throws InterruptedException {
		String error=corporatewellness.getNameErrorMessage();
		Assert.assertEquals(error,"Please Enter Name" , "Invalid Name test case Passed");
	}
}