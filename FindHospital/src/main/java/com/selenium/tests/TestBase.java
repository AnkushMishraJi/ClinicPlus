package com.selenium.tests;

import static org.testng.Assert.assertEquals;

import java.awt.AWTException;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.selenium.pages.*;
import com.selenium.setup.DriverSetup;
import com.selenium.setup.ExcelUtils;
import com.selenium.setup.UrlSetup;

@Listeners
public class TestBase {
	
	public static WebDriver driver;
	public static String[] rslt = null;
	public static String url="https://www.practo.com/";
	PractoHomePage practohomepage;
	PractoSearchHospitalsPage searchpage;
	PractoDiagnosticsPage diagnosticspage;
	PractoCorporateWellnessPage corporatewellness;
	UrlSetup urlSetup;
	DriverSetup ds;
	
	
	@BeforeClass
	@Parameters("browser")
	public void setUp(String browser) {
		try {
			DriverSetup ds=new DriverSetup();
			if(browser.equalsIgnoreCase("chrome")) {
				driver=ds.getChromeDriver();
			}
			else if(browser.equalsIgnoreCase("edge")) {
				driver=ds.getEDriver();
			}
			else if(browser.equalsIgnoreCase("firefox")) {
				driver=ds.getFirefoxDriver();
			}
		}
		catch (WebDriverException e) {
			System.out.println(e.getMessage());
		}
		practohomepage=new PractoHomePage(driver);
		searchpage= new PractoSearchHospitalsPage(driver);
		diagnosticspage=new PractoDiagnosticsPage(driver);
		corporatewellness= new PractoCorporateWellnessPage(driver);
		urlSetup=new UrlSetup(driver);
		
	}
	
	public static String[][] getExcelData() throws Exception {
		String sheetName = "Inputs";
	    return ExcelUtils.readExcelData(sheetName);
	}
	
	@Test(priority=0)
	public void UrlTesting() {
		
		urlSetup.Url(url);
	}
	
	@Test(priority=1)
	public void testValidDetails() throws Exception{
		String[][] data=null;
        data=getExcelData();
        practohomepage.setLocation(data[0][0]);
        practohomepage.setContent(data[0][1]);
	}
	
	@Test(priority=2)
	public void testCondition() throws InterruptedException {
		searchpage.setCondition();
	}
	
	@Test(priority=3)
	public void testFilterButton() throws InterruptedException {
		searchpage.filterButton();
	}
	
	@Test(priority=4)
	public void testFilter() throws InterruptedException {
		searchpage.setParking();
		
	}
	
	@Test(priority=5)
	public void testScroll() throws AWTException {
		searchpage.scrollDown();
	}
	
	@Test(priority=6)
	public void testHospsWithRatings() throws InterruptedException {
		rslt = searchpage.findHospsWithRatings();
	}
	
	@Test(priority=8)
	public void testDiagnosticspage() throws InterruptedException {
		diagnosticspage.DiagnosticsPage();
	}
	
	@Test(priority=9)
	public void testGetTopCities() {
		diagnosticspage.getTopCities();
	}
	
	@Test(priority=10)
	public void testDisplayTopCities() {
		diagnosticspage.displayTopCities();
	}
	
	@Test(priority =11)
	public void testcWellnessPage() throws InterruptedException {
		corporatewellness.corporateWellnessPage();
	}
	@AfterClass
	public void closeBrowser(){
		//close the driver
		driver.quit();
}
}