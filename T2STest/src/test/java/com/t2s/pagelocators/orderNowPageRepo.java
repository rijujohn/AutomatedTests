package com.t2s.pagelocators;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.asserts.SoftAssert;

import com.gargoylesoftware.htmlunit.javascript.host.Iterator;
import com.relevantcodes.extentreports.ExtentReports;
import com.t2s.extent.ExtentManager;
import com.t2s.testbase.TestBase;

public class orderNowPageRepo extends TestBase{
	
	SoftAssert s_assert;
	
	@FindBy(id="waiting-time")
    private WebElement waitingTimeArea;
	
	@FindBy(id="side-category-menu")
    private WebElement menuArea;
	
	//WebElement ele = driver.findElement(By.id("waiting-time"));
	
    public void validateDeliveryOptions(String strDelivery,String strCollection) throws InterruptedException {
    	s_assert = new SoftAssert();
    	
    	ArrayList<String> childItemText = verifyChildItems(waitingTimeArea);
    	
    	System.out.println(childItemText.toString());
    	
    	if (strDelivery.equals("Yes"))
    	{		
	        //   s_assert.assertTrue(childItemText.contains("Delivery 30 mins"), "Delivery Available text is not visible");
    	    //  s_assert.fail();
    		fnExtentContainsString (childItemText.toString(),"Delivery 30 mins",test,"Validate delivery option","Delivery Available text is visible","Delivery Available text is not visible");
    	    //  fnExtentCompareString (stepResult,firstVal,test,"Validate the first numeric entry","Data set correctly","Data not set correctly");
    	}
    	else
		{		
    		fnExtentContainsString (childItemText.toString(),"Delivery unavailable",test,"Validate delivery option","Delivery unavailable text is visible","Delivery unavailable text is not visible");
	        //   s_assert.assertTrue(childItemText.contains("Delivery unavailable"), "Delivery Unavailable text is not visible");
 		}
    	
    	if (strCollection.equals("Yes"))
		{		
    		fnExtentContainsString (childItemText.toString(),"Collection 15 mins",test,"Validate delivery option","Collection Available text is visible","Collection Available text is not visible");

	          // s_assert.assertTrue(childItemText.contains("Collection 15 mins"), "Collection Available text is not visible");
		}
	    else
	    {	
	    	fnExtentContainsString (childItemText.toString(),"Collection unavailable",test,"Validate delivery option","Collection Unavailable text is visible","Collection Unavailable text is not visible");	
	      // s_assert.assertTrue(childItemText.contains("Collection unavailable"), "Collection Unavailable text is not visible");
		}
	   // s_assert.assertAll();
    }
    
    public void validateFoodMenuOptions(String[] arrMenuList) throws InterruptedException
    {
    	ArrayList<String> childItemText = verifyChildItems(menuArea);
    	
        for (String item:arrMenuList )
        {
        	fnExtentContainsStringPar (childItemText.toString(),item,test,"Validate Menu options","Item "+ item +"is visible","Item "+ item +"is not visible");
        //	 s_assert.assertTrue(childItemText.contains(item), "Menu item"+ item +"text is not visible");
       
        }
    //	System.out.println(childItemText.toString());
    	// s_assert.assertAll();
    }
    
    
	
}
