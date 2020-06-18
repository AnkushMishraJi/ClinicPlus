package com.selenium.setup;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

import org.openqa.selenium.firefox.FirefoxDriver;


public class DriverSetup {
	
	public static WebDriver driver;
	
	public ChromeDriver getChromeDriver() {
		System.setProperty("webdriver.chrome.driver",".\\Driver\\chromedriver.exe");
		ChromeDriver driver = new ChromeDriver();
		return driver;
	}
	
	//to return EdgeDriver
	public EdgeDriver getEDriver() {
		System.setProperty("webdriver.edge.driver",".\\Driver\\msedgedriver.exe");
		EdgeDriver driver = new EdgeDriver();
		return driver;
	}
	
	public FirefoxDriver getFirefoxDriver() {
		System.setProperty("webdriver.gecko.driver",".\\Driver\\geckodriver.exe");
		FirefoxDriver driver=new FirefoxDriver();
	    return driver;
	}
}
