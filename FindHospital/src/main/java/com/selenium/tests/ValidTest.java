package com.selenium.tests;
import org.testng.annotations.Test;
import com.selenium.setup.ExcelUtils;
public class ValidTest extends TestBase{
	@Test(priority = 20)
	public void validDetailsCorporateWellness() throws Exception {
		String[][] data=null;
        data=getExcelData();
        corporatewellness.validDetails5(data[0][2],data[0][3],data[0][4],data[0][5]);
	}
	
	@Test(priority = 21)
	public void testDisplaySuccessMessage() throws InterruptedException {
		corporatewellness.getSuccessMessage();
	}
}
