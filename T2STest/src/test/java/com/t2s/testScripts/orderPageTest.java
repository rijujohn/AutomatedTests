package com.t2s.testScripts;

import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.LinkedList;

import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.t2s.excelReader.ODSReader;
import com.t2s.extent.ExtentManager;
import com.t2s.pagelocators.contactPageRepo;
import com.t2s.pagelocators.homePageRepo;
import com.t2s.pagelocators.orderNowPageRepo;
import com.t2s.prepare.PrepareEnvForTests;
import com.t2s.testbase.TestBase;

import junit.framework.Assert;

public class orderPageTest extends TestBase{
	
	
    @Test(priority = 1)
	public void orderPageTests() throws InterruptedException, IOException
	{	
    	ExtentReports rep = ExtentManager.getInstances();
    	//String inputFileString = System.getProperty("user.dir") + "//src//test//java//com//t2s//testData//Input.ods";
		File inputFile = new File(inputFileString);
		//String inputSheetName = "Order";
		LinkedHashMap<String, String> mapData = new LinkedHashMap<String, String>();
	    int intRCount; 
	  //  int waitTime = 500;
	    
    	ODSReader objODSReader = new ODSReader();
		mapData = objODSReader.readODS(inputFile,orderData);
		intRCount = objODSReader.getRowCount(inputFile, orderData);
		PrepareEnvForTests PrepEnv = new PrepareEnvForTests();
		
		initializeBrowser();

    	homePageRepo HomePage = PageFactory.initElements(driver, homePageRepo.class);
    	orderNowPageRepo OrderNow = PageFactory.initElements(driver, orderNowPageRepo.class);


		for(int i = 1;i<intRCount;i++)
		{
			//Get to execute details	
			String strExecute = mapData.get(i+"Execute");	
			if (strExecute.toUpperCase().equals("YES"))
			{
			//Get API data creation tokens
			String strTemplateName = mapData.get(i+"Template");	
			String strToken = mapData.get(i+"Token");
			String JSONBodyPath = jsonBodyPath + strTemplateName+".json";
			
			//Update data with API
		//	String strDataCreationStatus = "SUCCESS";
			String strDataCreationStatus = PrepEnv.prepare(strToken,JSONBodyPath);	
			
			
			if (strDataCreationStatus.toUpperCase().equals("SUCCESS"))
			{	
			
				String strURL = mapData.get(i+"URL");	
				String strDelivery = mapData.get(i+"Delivery");
				String strCollection = mapData.get(i+"Collection");
				String strAskPostCode = mapData.get(i+"AskPostCode");
				String strCollectionWaitTime = mapData.get(i+"CollectionWait");
				String strDeliveryWaitTime = mapData.get(i+"DeliveryWait");
				String strMenuItem = mapData.get(i+"MenuItems");
			    String[] arrMenuList = strMenuItem.split(";");
			   // System.out.println("String item is -- "+MenuList.toString());
				openURL(strURL);
		        // System.out.println("The title is - "+ driver.getTitle());
        
			    implicitWait(waitTime);
			    test = rep.startTest("Order Now Page Validations.Data Set "+ strURL);
		    	HomePage.clickOrderNow();
		    	implicitWait(waitTime);

		    	
		    	
		    	OrderNow.validateDeliveryOptions(strDelivery,strCollection,strDeliveryWaitTime,strCollectionWaitTime);
		    	OrderNow.validateFoodMenuOptions(arrMenuList);
		    	
		    	OrderNow.validatePopUpTitle(strAskPostCode,strDelivery);
		    	OrderNow.validatePopUpSelectionOptions(strAskPostCode,strDelivery, strCollection);
			    
		    	rep.endTest(test);
		    	rep.flush();
		    	if (i == intRCount-1 ){
		    	OrderNow.assertAllFn();
		    	}
    	
			}
			else {Reporter.log("Error while creating data for Contact page tests");
			closeBrowser();
            Assert.fail();}
		}
		}
		closeBrowser();
		Thread.sleep(70);

}
}