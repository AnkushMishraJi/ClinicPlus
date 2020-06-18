package com.selenium.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PractoDiagnosticsPage {
	public WebDriver driver;
	public ArrayList<String> topCities=new ArrayList<String>();
	public PractoDiagnosticsPage(WebDriver driver) { 
		this.driver= driver;
	}
	
	//to navigate to diagnostics page by clicking the option
	public void DiagnosticsPage() throws InterruptedException {
		driver.findElement(By.xpath("//div[contains(text(),'Diagnostics')]")).click();
	}
	
	
	//to store the top cities in a collection
	public void getTopCities() {
		WebDriverWait wait = new WebDriverWait(driver, 20); 
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='u-margint--standard o-f-color--primary']")));
		List<WebElement> cities=driver.findElements(By.xpath("//div[@class='u-margint--standard o-f-color--primary']"));
		for(WebElement city:cities) {
			if(city!=null)
				topCities.add(city.getText());
		}
	}

	//to display the top cities
	public void displayTopCities() {
		for(int i=1;i<=topCities.size();i++) {
			System.out.println("Top City "+i+": "+topCities.get(i-1));
		}
	}

}
