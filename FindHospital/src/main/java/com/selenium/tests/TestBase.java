package com.selenium.tests;

import java.awt.AWTException;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.selenium.pages.*;
import com.selenium.setup.DriverSetup;
import com.selenium.setup.ExcelUtils;
import com.selenium.setup.UrlSetup;

@Listeners
public class TestBase {
	
	public static WebDriver driver;
	public static String[] rslt = null;
	public static String url="https://www.practo.com/";
	ExtentHtmlReporter htmlReporter;
	ExtentReports extent;
	ExtentTest test;
	PractoHomePage practohomepage;
	PractoSearchHospitalsPage searchpage;
	PractoDiagnosticsPage diagnosticspage;
	PractoCorporateWellnessPage corporatewellness;
	UrlSetup urlSetup;
	DriverSetup ds;
	
	//Entering the defined browser
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
	@BeforeTest
	@Parameters("browser")
	public void startReport(String browser) {
		 // start reporters
		htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir")+"/ExtentReport.html");;	    
	    extent = new ExtentReports();
	    extent.attachReporter(htmlReporter);  // create ExtentReports and attach reporter(s)
	    extent.setSystemInfo("User Name","Team 1");
	    extent.setSystemInfo("Company", "CTS");
	    extent.setSystemInfo("OS", "Windows");
	    extent.setSystemInfo("Browser", browser);
	    htmlReporter.config().setChartVisibilityOnOpen(true);
	    htmlReporter.config().setDocumentTitle("Extent Report");
	    htmlReporter.config().setReportName("Test Report");
	    htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
	    htmlReporter.config().setTheme(Theme.STANDARD);
	    htmlReporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");
	}
	
	public static String[][] getExcelData() throws Exception {
		String sheetName = "Inputs";
	    return ExcelUtils.readExcelData(sheetName);
	}
	
	@Test(priority=0)
	public void UrlTesting() {
        test = extent.createTest("Test Case 1", "URL test case");
		urlSetup.Url(url);
		Assert.assertTrue(true);
	}
	
	@Test(priority=1)
	public void testValidDetails() throws Exception{
		test = extent.createTest("Test Case 2", "City and Search test case"); //entering name of the city and search type
		String[][] data=null;
        data=getExcelData();
        practohomepage.setLocation(data[0][0]);
        practohomepage.setContent(data[0][1]);
        Assert.assertTrue(true);
	}
	
	@Test(priority=2)
	public void testCondition() throws InterruptedException {  //24*7 facility test
		test = extent.createTest("Test Case 3", "24*7 test case");
		searchpage.setCondition();
		Assert.assertTrue(true);
	}
	
	@Test(priority=3)
	public void testFilterButton() throws InterruptedException {
		test = extent.createTest("Test Case 4", "Filter button test case"); // FilterButtpn test
		searchpage.filterButton();
		Assert.assertTrue(true);
	}
	
	@Test(priority=4)
	public void testFilter() throws InterruptedException {
		 test = extent.createTest("Test Case 5", "Parking test case"); // Has Parking test
		searchpage.setParking();
		Assert.assertTrue(true);
		
	}
	
	@Test(priority=5)
	public void testScroll() throws AWTException {
		 test = extent.createTest("Test Case 6", "Scrolling test case");  // Scroll bar test
		searchpage.scrollDown();
		Assert.assertTrue(true);
	}
	
	@Test(priority=6)
	public void testHospsWithRatings() throws InterruptedException {
		   test = extent.createTest("Test Case 7", "3.5 Rating test case"); //Ratings above 3.5 stars
		rslt = searchpage.findHospsWithRatings();
		Assert.assertTrue(true);
	}
	
	@Test(priority=7)
	public void testDiagnosticspage() throws InterruptedException {
		test = extent.createTest("Test Case 8", "Diagnostic Page test case");   //Entering Diagnostics page
		diagnosticspage.DiagnosticsPage();
		Assert.assertTrue(true);
	}
	
	@Test(priority=8)
	public void testGetTopCities() {
		 test = extent.createTest("Test Case 9", "Top Cities test case");
		diagnosticspage.getTopCities();
		Assert.assertTrue(true);
	}
	
	@Test(priority=9)
	public void testDisplayTopCities() {
		  test = extent.createTest("Test Case 10", "Display Cities test case");
		diagnosticspage.displayTopCities();
		Assert.assertTrue(true);
	}
	// Entering valid and invalid details in corporate wellness
	@Test(priority =10)
	public void testcWellnessPage() throws InterruptedException {
		test = extent.createTest("Test Case 11", "Corporate Wellness test case");
		corporatewellness.corporateWellnessPage();
		Assert.assertTrue(true);
	}
	@Test(priority = 11)
	public void invalidDetails1() throws Exception {
		test = extent.createTest("Test Case 12", "InValid Details 1 test case");
		String[][] data=null;
        data=getExcelData();
        corporatewellness.invalidDetails1(data[0][2],data[0][3],data[0][4],data[0][5]);
        Assert.assertTrue(true);
	}
	
	@Test(priority = 12)
	public void testDisplayError1() {
		test = extent.createTest("Test Case 13", "Error msg 1 test case");
		corporatewellness.getError1();
		Assert.assertTrue(true);
	}
	
	@Test(priority = 13)
	public void invalidDetails2() throws Exception {
		test = extent.createTest("Test Case 14", "Invalid Details 1 test case");
		String[][] data=null;
        data=getExcelData();
        corporatewellness.invalidDetails2(data[0][2],data[0][3],data[0][4],data[0][5]);
        Assert.assertTrue(true);
	}
	
	@Test(priority = 14)
	public void testDisplayError2() {
		test = extent.createTest("Test Case 15", "Error msg 2 test case");
		corporatewellness.getError2();
		Assert.assertTrue(true);
	}
	
	@Test(priority = 15)
	public void invalidDetails3() throws Exception {
		test = extent.createTest("Test Case 16", "Invalid Details 3 test case");
		String[][] data=null;
        data=getExcelData();
        corporatewellness.invalidDetails3(data[0][2],data[0][3],data[0][4],data[0][5]);
        Assert.assertTrue(true);
	    
	}
	
	@Test(priority = 16)
	public void testDisplayError3() {
		test = extent.createTest("Test Case 17", "Error msg 3 test case");
		corporatewellness.getError3();
		Assert.assertTrue(true);
	}
	
	@Test(priority = 17)
	public void invalidDetails4() throws Exception {
		test = extent.createTest("Test Case 18", "Invalid Details 4  test case");
		String[][] data=null;
        data=getExcelData();
        corporatewellness.invalidDetails4(data[0][2],data[0][3],data[0][4],data[0][5]);
        Assert.assertTrue(true);
	}
	
	@Test(priority = 18)
	public void testDisplayError4() {
		test = extent.createTest("Test Case 19", "Error msg 4 test case");
		corporatewellness.getError4();
		Assert.assertTrue(true);
	}
	@Test(priority = 19)
	public void validDetailsCorporateWellness() throws Exception {
		test = extent.createTest("Test Case 20", "Valid Details test case");
		String[][] data=null;
        data=getExcelData();
        corporatewellness.validDetails5(data[0][2],data[0][3],data[0][4],data[0][5]);
        Assert.assertTrue(true);
	}
	
	@Test(priority = 20)
	public void testDisplaySuccessMessage() throws InterruptedException {
		test = extent.createTest("Test Case 21", "Success msg test case");
		corporatewellness.getSuccessMessage();
		Assert.assertTrue(true);
	}
	@Test(priority = 21)
	public void inValidName() throws Exception {
		test = extent.createTest("Test Case 22", "Invalid Name test case");
		String[][] data=null;
        data=getExcelData();
        corporatewellness.validDetails6(data[0][6],data[0][3],data[0][4],data[0][5]);
        Assert.assertTrue(true);
	}
	
	@Test(priority = 22)
	public void displayErrorInvalidName() throws InterruptedException {
		test = extent.createTest("Test Case 23", "Display Invalid Name test case");
		String error=corporatewellness.getNameErrorMessage();
		Assert.assertEquals(error,"Please Enter Name" , "Invalid Name test case Passed");
	}
	@AfterMethod
	 public void tearDown(ITestResult result) throws IOException {
	  if (result.getStatus() == ITestResult.FAILURE) {
	   test.log(Status.FAIL, "TEST CASE FAILED IS " + result.getName());       // log(Status, details)
	   test.log(Status.FAIL, "TEST CASE FAILED IS " + result.getThrowable());
	  } else if (result.getStatus() == ITestResult.SKIP) {
	   test.log(Status.SKIP, "Test Case SKIPPED IS " + result.getName());
	  }
	  else if (result.getStatus() == ITestResult.SUCCESS) {
	   test.log(Status.PASS, "Test Case PASSED IS " + result.getName());
	  }
	}
	@AfterTest
	public void tearDown(){
		extent.flush();  // calling flush writes everything to the log file
	}
	
	@AfterClass
	public void closeBrowser(){
		//close the driver
		driver.quit();
	}
}