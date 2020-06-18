package com.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PractoCorporateWellnessPage {
	public WebDriver driver;
	String error=" ";
	public PractoCorporateWellnessPage(WebDriver driver) { 
		this.driver= driver;
	}
	
	//navigating to the corporate wellness page
	public void corporateWellnessPage() throws InterruptedException {
		driver.get("https://www.practo.com/plus/corporate");
	}
	
	//leaving all the boxes empty
public void invalidDetails1(String name,String company,String email,String phone) {
		
	}

	//clicking submit and printing error message
	public void getError1() {

		driver.findElement(By.xpath("//button[@id='button-style']")).click();
		System.out.println("Error message: ");
		System.out.println(driver.switchTo().alert().getText());
		driver.switchTo().alert().accept();

	}
	
	//writing valid name and leaving all the other boxes empty
	public void invalidDetails2(String name,String company,String email,String phone) {
		driver.findElement(By.xpath("//input[@id='name']")).clear();
		driver.findElement(By.xpath("//input[@id='name']")).sendKeys(name);
		
	}
	
	//clicking submit and printing error message
	public void getError2() {

		driver.findElement(By.xpath("//button[@id='button-style']")).click();
		System.out.println("Error message: ");
		System.out.println(driver.switchTo().alert().getText());
		driver.switchTo().alert().accept();

	}
	
	//writing name and company while leaving others empty
	public void invalidDetails3(String name,String company,String email,String phone) {
		driver.findElement(By.xpath("//input[@id='name']")).clear();
		driver.findElement(By.xpath("//input[@id='name']")).sendKeys(name);
		driver.findElement(By.xpath("//input[@id='organization_name']")).clear();
		driver.findElement(By.xpath("//input[@id='organization_name']")).sendKeys(company);
		
	}
	
	//clicking submit and printing error message
	public void getError3() {

		driver.findElement(By.xpath("//button[@id='button-style']")).click();
		System.out.println("Error message: ");
		System.out.println(driver.switchTo().alert().getText());
		driver.switchTo().alert().accept();

	}
	
	//writing name, company and email while leaving other spaces empty
	public void invalidDetails4(String name,String company,String email,String phone) {
		driver.findElement(By.xpath("//input[@id='name']")).clear();
		driver.findElement(By.xpath("//input[@id='name']")).sendKeys(name);
		driver.findElement(By.xpath("//input[@id='organization_name']")).clear();
		driver.findElement(By.xpath("//input[@id='organization_name']")).sendKeys(company);
		driver.findElement(By.xpath("//input[@id='official_email_id']")).clear();
		driver.findElement(By.xpath("//input[@id='official_email_id']")).sendKeys(email);
		
	}
	
	//clicking submit and printing error message
	public void getError4() {

		driver.findElement(By.xpath("//button[@id='button-style']")).click();
		System.out.println("Error message: ");
		System.out.println(driver.switchTo().alert().getText());
		driver.switchTo().alert().accept();

	}
	
	//entering all the data
	public void validDetails5(String name,String company,String email,String phone) {
		driver.findElement(By.xpath("//input[@id='name']")).clear();
		driver.findElement(By.xpath("//input[@id='name']")).sendKeys(name);
		driver.findElement(By.xpath("//input[@id='organization_name']")).clear();
		driver.findElement(By.xpath("//input[@id='organization_name']")).sendKeys(company);
		driver.findElement(By.xpath("//input[@id='official_email_id']")).clear();
		driver.findElement(By.xpath("//input[@id='official_email_id']")).sendKeys(email);
		driver.findElement(By.xpath("//input[@id='official_phone_no']")).clear();
		driver.findElement(By.xpath("//input[@id='official_phone_no']")).sendKeys(phone);
		System.out.println(phone);
	}
	
	//clicking submit and printing error message
	public void getSuccessMessage() throws InterruptedException {

		driver.findElement(By.xpath("//button[@id='button-style']")).click();
		Thread.sleep(1000);
		System.out.println("Error message: ");
		System.out.println(driver.switchTo().alert().getText());
		driver.switchTo().alert().accept();

	}
	
	//entering all the data correctly while entering invalid name
	public void validDetails6(String name,String company,String email,String phone) {
		driver.findElement(By.xpath("//input[@id='name']")).clear();
		driver.findElement(By.xpath("//input[@id='name']")).sendKeys(name);
		driver.findElement(By.xpath("//input[@id='organization_name']")).clear();
		driver.findElement(By.xpath("//input[@id='organization_name']")).sendKeys(company);
		driver.findElement(By.xpath("//input[@id='official_email_id']")).clear();
		driver.findElement(By.xpath("//input[@id='official_email_id']")).sendKeys(email);
		driver.findElement(By.xpath("//input[@id='official_phone_no']")).clear();
		driver.findElement(By.xpath("//input[@id='official_phone_no']")).sendKeys(phone);
		System.out.println(phone);
	}
	
	//clicking submit and printing error message
	public String getNameErrorMessage() throws InterruptedException {

		driver.findElement(By.xpath("//button[@id='button-style']")).click();
		Thread.sleep(1000);
		System.out.println("Error message: ");
		error=driver.switchTo().alert().getText();
		System.out.println(error);
		driver.switchTo().alert().accept();
		return error;

	}

}
