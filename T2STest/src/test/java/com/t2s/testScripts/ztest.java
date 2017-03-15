package com.t2s.testScripts;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

public class test {
	
	
	@Test(priority = 1)
	public void openHomePage() throws Exception
	{
		//try{
			WebDriver driver = new ChromeDriver();
		//	driver.get("http://d-188.t2scdn.com/");
			driver.get("https://foodhub.co.uk/list/ST66DX");
			Thread.sleep(5000);
			
		//	/html/body/div/my-app/home/footer/div/div/div[1]/p/a[1]
		   
			
		//	 WebElement icon = driver.findElement(By.xpath("//a[@class='text-muted small']"));
		//        Actions ob = new Actions(driver);
		   //     ob.click(icon);
			
			
			
			
		//	List<WebElement> hj =  driver.findElements(By.xpath("//a[@class='text-muted small']"));
	       
		//    WebElement ghm = driver.findElement(By.className("visible-sm visible-xs copyright"));
		  //   WebElement ghmq = driver.findElement(By.className("//a[@class='text-muted small']"));
		//	 ghm.findElement(By.className("text-muted small")).click();
			
			 
			// WebElement elem = driver.findElement(By.xpath("//a[@class='text-muted small"));
			//WebElement elem = driver.findElement(By.linkText("Privacy Policy"));
			
			WebElement tmpElement= driver.findElement(By.linkText("Privacy Policy"));
			JavascriptExecutor executor = (JavascriptExecutor)driver;
			executor.executeScript("arguments[0].click();", tmpElement);
			
			
			
			
			//String js = "arguments[0].style.height='auto'; arguments[0].style.visibility='visible';";

			// ((JavascriptExecutor) driver).executeScript(js, elem);
			// elem.click();
			 
			 //driver.findElement(By.linkText("Privacy Policy")).click();
			// new Actions(driver).moveToElement(driver.findElement(By.linkText("Privacy Policy"))).click().perform();
			 
			 
		/*   ArrayList<String> pageTexts = new ArrayList<String>();
		   for (WebElement e  : hj)
		   { 
			 
		     Dimension gh = e.getSize();
		     int hg = gh.height;
		     String hgn = gh.toString();
		     System.out.println ("the height is"+ hg); 
		     System.out.println ("the height is"+ hgn); 
			   }*/
		   
		   //   driver.findElement(By.linkText("Privacy Policy")).click();
		//	xpath="//*[@id='contactus-pag-sec']
		    
		//    WebElement elem = driver.findElement(By.xpath(".//*[@id='__dialog1-footer']/button[1]/div"));
		  //  WebElement elem =  driver.findElement(By.xpath("//a[@class='text-muted small']"));
		 // This will enable this element if element is invisible      
		  
		 //String js = "arguments[0].style.height='auto'; arguments[0].style.visibility='visible';";
		   
		 // Execute the Java Script for the element which we find out
		// ((JavascriptExecutor) driver).executeScript(js, elem);
		  
		 // Click on element
		  
		// elem.click();
		 
}
}