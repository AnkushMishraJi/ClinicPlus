package com.selenium.pages;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PractoSearchHospitalsPage {
	public WebDriver driver;
	public PractoSearchHospitalsPage(WebDriver driver) { 
		this.driver= driver;
	}
	
	//to set open 24*7 condition
	public void setCondition() throws InterruptedException {
		driver.findElement(By.xpath("//body//div[@id='container']//div//div[3]//label[1]//div[1]")).click();
		Thread.sleep(3000);
	}

	//to click the all filters button
	public void filterButton() throws InterruptedException {
		driver.findElement(By.xpath("//span[contains(text(),'All Filters')]")).click();
		Thread.sleep(3000);
	}

	//to set "has parking" condition
	public void setParking() throws InterruptedException {
		driver.findElement(By.xpath("//span[contains(text(),'Has Parking')]")).click();
		Thread.sleep(3000);
	}

	//to scroll down in order to load more search results
	public void scrollDown() throws AWTException {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		try {
			long lastHeight=((Number)js.executeScript("return document.body.scrollHeight")).longValue();
			while (true) {
				((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight);");
				Thread.sleep(2000);
				Robot robot=new Robot();
				robot.keyPress(KeyEvent.VK_PAGE_UP);
				robot.keyRelease(KeyEvent.VK_PAGE_UP);
				Thread.sleep(1000);
				long newHeight = ((Number)js.executeScript("return document.body.scrollHeight")).longValue();
				if (newHeight == lastHeight) {
					break;
				}
				lastHeight = newHeight;
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	//to find and print hospital names with rating more than 3.5 stars
	public String[] findHospsWithRatings() throws InterruptedException {
		ArrayList<String> hosps=new ArrayList<String>();
		ArrayList<Double> ratings=new ArrayList<Double>();
		List<WebElement> names=driver.findElements(By.xpath("//span[@class='common__star-rating__value']/ancestor::div[@class='pure-u-6-24']/preceding-sibling::div[@class='pure-u-18-24']/descendant::h2[@class='u-title-font u-c-pointer u-bold']"));
		List<WebElement> stars=driver.findElements(By.xpath("//span[@class='common__star-rating__value']"));
		int c=0;
		for(int i=1;i<names.size();i++) {
			String value=names.get(i).getText();
			String numtemp=stars.get(i).getText();
			NumberFormat nf = NumberFormat.getInstance();
			double num;
			try {
				num = nf.parse(numtemp).doubleValue();
				if(num>3.5) {
					hosps.add(value);
					ratings.add(num);
					c++;
					System.out.print(c+". ");
					System.out.print("Hospital Name: "+value);
					System.out.println(" Rating: "+num);
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}  
		}
		driver.navigate().back();
		Thread.sleep(2000);
		String hosp[] = new String[hosps.size()];
		for(int i=0;i<hosps.size();i++) {
			hosp[i]= hosps.get(i);
		}
		return hosp;
	}
}
