package com.selenium.setup;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;

public class UrlSetup {
	
	public WebDriver driver;
	
	public UrlSetup() {}
	
	public UrlSetup(WebDriver driver) {
		this.driver=driver;
	}
	
	public void Url(String url) {
		driver.get(url);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
}
