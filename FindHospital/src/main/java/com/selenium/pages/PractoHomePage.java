package com.selenium.pages;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PractoHomePage {

	public WebDriver driver;
	public ArrayList<String> finalhosps;
	public ArrayList<Double> finalratings;
	public ArrayList<String> topCities=new ArrayList<String>();
	public String error="";
	
	public PractoHomePage(WebDriver driver) { 
		this.driver= driver;
	}
	
	//to set the location
	public void setLocation(String city) throws InterruptedException {
		
		WebElement select=driver.findElement(By.xpath("//input[@placeholder='Search location']"));
		select.click();
		select.sendKeys((Keys.chord(Keys.CONTROL,"a"))+Keys.DELETE);
		select.sendKeys(city);
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("div.content:nth-child(2) div.c-omni-wrapper.u-d__inline-block:nth-child(1) div.c-omni-container--desktop div.c-omni.u-clearfix div.c-omni__wrapper.u-clearfix.c-omni__wrapper--locality:nth-child(1) div.c-omni-suggestion-list div.c-omni-suggestion-group > div.c-omni-suggestion-item:nth-child(1)")).click();
		Thread.sleep(3000);
	}

	//to search doctors, clinics, hospitals, etc
	public void setContent(String name) throws InterruptedException {
		driver.findElement(By.xpath("//input[@placeholder='Search doctors, clinics, hospitals, etc.']")).sendKeys(name);
		Thread.sleep(2000);
		driver.findElement(By.xpath("//div[@class='c-omni-suggestion-group']//div[1]//span[1]//div[1]")).click();
		Thread.sleep(4000);
		
	}

}
