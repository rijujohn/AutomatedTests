package com.t2s.pagelocators;

import java.io.IOException;
import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.asserts.SoftAssert;

import com.gargoylesoftware.htmlunit.javascript.host.Iterator;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.t2s.extent.ExtentManager;
import com.t2s.testbase.TestBase;

public class orderNowPageRepo extends TestBase{
	
	SoftAssert s_assertOrd = new SoftAssert();
	
	@FindBy(id="waiting-time")
    private WebElement waitingTimeArea;
	
	@FindBy(id="side-category-menu")
    private WebElement menuArea;
	
	//WebElement ele = driver.findElement(By.id("waiting-time"));
	
	//Order Type Selection PopUp
	@FindBy(id="selectionNextFirst")
    private WebElement btnNext;
	
	@FindBy(id="addonModalLabel1")
    private WebElement headerTitleNext;
	
	@FindBy(id="delivery")
    private WebElement chkDelivery;
	
	@FindBy(id="collection")
    private WebElement chkCollection;
	
	@FindBy(id="selections")
    private WebElement areaSelections;
	
	
	
	//driver.findElement(By.id("selectionNextFirst")).click(); //Next button
	
//	String hj = driver.findElement(By.id("addonModalLabel1")).getText(); //title
//	WebElement openHoursList = driver.findElement(By.id("delivery")).click();
//	driver.findElement(By.id("delivery")).click();
	
	
	
	public void validatePopUpTitle(String strAskPostCode,String strDelivery) throws IOException
	{
		if (strAskPostCode.toUpperCase().equals("YES")&&(strDelivery.toUpperCase().equals("YES")))
		{
		driver.switchTo().activeElement();	
		String popUPTitle = headerTitleNext.getText().trim();
		fnExtentCompareString (popUPTitle,"Order Type Selection",test,"Validate PopUP title","Title appears correctly","Title does not appear correctly");
		s_assertOrd.assertEquals(popUPTitle, "Order Type Selection","Title does not appear correctly");
		}
	
		}
	
	public void validatePopUpSelectionOptions(String strAskPostCode, String strDelivery,String strCollection) throws IOException, InterruptedException
	{
	if (strAskPostCode.toUpperCase().equals("YES") && (strDelivery.toUpperCase().equals("YES")))
		{	
    	ArrayList<String> childItemText = verifyChildItems(areaSelections);
    	
    	String strRetItem = childItemText.toString();
    	
	//	String strCollResult =  verifyWebElementDIsplayed(chkCollection);
	//	String strDelResult =  verifyWebElementDIsplayed(chkDelivery);
		
		if (strDelivery.toUpperCase().equals("YES"))
	    	{
			
			fnExtentContainsString (strRetItem,"Delivery",test,"Validate Delivery checkbox present","Checkbox present","Checkbox not present");
			s_assertOrd.assertTrue(strRetItem.contains("Delivery"), "Delivery checkbox is not visible"); 
			
	    	}
		if (strDelivery.toUpperCase().equals("NO"))
		 	{
			fnExtentNOTContainsString (strRetItem,"Delivery",test,"Validate Delivery checkbox not present","Checkbox not present","Checkbox present");	
			s_assertOrd.assertFalse(strRetItem.contains("Delivery"), "Delivery checkbox is not visible"); 
			
		 	}
	  
		if (strCollection.toUpperCase().equals("YES"))
		    {
			fnExtentContainsString (strRetItem,"Collection",test,"Validate Collection checkbox present","Checkbox present","Checkbox not present");
			s_assertOrd.assertTrue(strRetItem.contains("Collection"), "Collection checkbox is not visible"); 
			
		    }
	   if (strCollection.toUpperCase().equals("NO"))
			 {
		   fnExtentNOTContainsString (strRetItem,"Collection",test,"Validate Collection checkbox not present","Checkbox not present","Checkbox present");	
		   s_assertOrd.assertFalse(strRetItem.contains("Collection"), "Collection checkbox is not visible"); 
			
			 }
		}
	}
	
	
	
    public void validateDeliveryOptions(String strDelivery,String strCollection,String strDeliveryWaitTime,String strCollectionWaitTime) throws InterruptedException, IOException {

    	
    	ArrayList<String> childItemText = verifyChildItems(waitingTimeArea);
    	
    	System.out.println(childItemText.toString());
    	
    	if (strDelivery.toUpperCase().equals("YES"))
    	{		
    		fnExtentContainsString (childItemText.toString(),"Delivery "+strDeliveryWaitTime+" mins",test,"Validate delivery option","Delivery Available text is visible","Delivery Available text is not visible");
    		s_assertOrd.assertTrue(childItemText.toString().contains("Delivery "+strDeliveryWaitTime+" mins"), "Delivery Available text is not visible");
    	}
    	else
		{		
    		fnExtentContainsString (childItemText.toString(),"Delivery unavailable",test,"Validate delivery option","Delivery unavailable text is visible","Delivery unavailable text is not visible");
    		s_assertOrd.assertTrue(childItemText.toString().contains("Delivery unavailable"), "Delivery unavailable text is not visible");

		}
    	
    	if (strCollection.toUpperCase().equals("YES"))
		{		
    		fnExtentContainsString (childItemText.toString(),"Collection "+strCollectionWaitTime+" mins",test,"Validate delivery option","Collection Available text is visible","Collection Available text is not visible");
    		s_assertOrd.assertTrue(childItemText.toString().contains("Collection "+strCollectionWaitTime+" mins"), "Collection Available text is not visible");
		}
	    else
	    {	
	    	fnExtentContainsString (childItemText.toString(),"Collection unavailable",test,"Validate delivery option","Collection Unavailable text is visible","Collection Unavailable text is not visible");	
	    	s_assertOrd.assertTrue(childItemText.toString().contains("Collection unavailable"), "Collection Unavailable text is not visible");
	    }
    }
    
    public void validateFoodMenuOptions(String[] arrMenuList) throws InterruptedException, IOException
    {
    	ArrayList<String> childItemText = verifyChildItems(menuArea);
    	
        for (String item:arrMenuList )
        {
        	fnExtentContainsStringPar (childItemText.toString(),item,test,"Validate Menu options","Item "+ item +"is visible","Item "+ item +"is not visible");
        }

    }
    
    public void assertAllFn()
    {
    	s_assertOrd.assertAll();
    }
    
	
}
