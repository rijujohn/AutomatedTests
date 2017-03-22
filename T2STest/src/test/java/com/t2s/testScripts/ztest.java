package com.t2s.testScripts;

//import static com.uktech.helpers.Constants.GLOBAL_TIMEOUT_30;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.testng.Reporter;
import org.testng.annotations.Test;
import com.t2s.testbase.TestBase;

import com.t2s.testutil.CustomFluentWait;

public class ztest {
	
	
	@Test(priority = 1)
	public void openHomePage() throws Exception
	{
		//try{
			WebDriver driver = new ChromeDriver();
			
			driver.get("http://d-357.t2scdn.com/");
			driver.manage().window().maximize();
			//Thread.sleep(5000);
			CustomFluentWait customFluentWait = new CustomFluentWait(driver);
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.findElement(By.linkText("Order now")).click();
			Thread.sleep(5000);
			driver.switchTo().activeElement();
			//driver.findElement(By.id("selectionNextFirst")).click(); //Next button
			
			String hj = driver.findElement(By.id("addonModalLabel1")).getText(); //title
		
			
			//	WebElement openHoursList = driver.findElement(By.id("delivery")).click();
			//driver.findElement(By.id("delivery")).click();
			//ArrayList<String> childItemText = verifyChildItems(openHoursList);
			System.out.println(driver.findElement(By.id("delivery")).isDisplayed());
			System.out.println(hj);
			
//		    @FindBy(className = "modal-title1")
//		    public WebElement orderTypeSelection;
			
	//		 if (customFluentWait.isElementPresent(orderTypeSelection, 30)) {
		            //orderType();
	//			 System.out.println("present");
	//	        }
			
			//driver.manage().window().maximize();
		//	driver.findElement(By.id("delivery")).isSelected();
		//	driver.findElement(By.id("selectionNextFirs")).click();
		//	customFluentWait.isElementPresent(driver.findElement(By.id("selectionNextFirst")));
			//driver.findElement(By.className("modal-dialog"));
		    // Switching to Alert        
        //    Alert alert=driver.switchTo().alert();		
            		
            // Capturing alert message.    
         //   String alertMessage=driver.switchTo().alert().getText();		
            		
            // Displaying alert message		
         //   System.out.println(alertMessage);	
            
		//	driver.get("http://d-488.t2scdn.com/");
		//	driver.get("https://foodhub.co.uk/list/ST66DX");
			//Thread.sleep(5000);

		  //  @FindBy(id = "selectionNextFirst")
		  //  private WebElement popUpNextButton;
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
			
		//	WebElement tmpElement= driver.findElement(By.linkText("Privacy Policy"));
		//	JavascriptExecutor executor = (JavascriptExecutor)driver;
		//	executor.executeScript("arguments[0].click();", tmpElement);
			
			
			
			
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
	
	
	
	public ArrayList<String> verifyChildItems(WebElement ele) throws InterruptedException
	{
		Thread.sleep(2000);
	List<WebElement> childs = ele.findElements(By.xpath(".//*"));
	ArrayList<String> pageTexts = new ArrayList<String>();
	for (WebElement e  : childs)
	{
	  String st = e.getText();
	  //String tagName = e.getTagName();
		if (st.isEmpty())
		{
			Reporter.log("No child items retreived for element - " + ele);
		}
		else
		{	
			//pageTexts.add(e.getTagName());
			Reporter.log("Child items retreived for element - " + ele);
			pageTexts.add(e.getText());
		}

		
	} 

	return pageTexts;
	}
}


