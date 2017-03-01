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
import com.t2s.testbase.TestBase;

public class orderPageTest extends TestBase{
	
	
    @Test(priority = 1)
	public void orderPageTests() throws InterruptedException, IOException
	{	
    	ExtentReports rep = ExtentManager.getInstances();
    	String inputFileString = System.getProperty("user.dir") + "//src//test//java//com//t2s//testData//Input.ods";
		File inputFile = new File(inputFileString);
		String inputSheetName = "Order";
		LinkedHashMap<String, String> mapData = new LinkedHashMap<String, String>();
	    int intRCount; 
	    int waitTime = 500;
	    
    	ODSReader objODSReader = new ODSReader();
		mapData = objODSReader.readODS(inputFile,inputSheetName);
		intRCount = objODSReader.getRowCount(inputFile, inputSheetName);

		initializeBrowser();

    	homePageRepo HomePage = PageFactory.initElements(driver, homePageRepo.class);
    	orderNowPageRepo OrderNow = PageFactory.initElements(driver, orderNowPageRepo.class);


		for(int i = 1;i<intRCount;i++)
		{
		String strURL = mapData.get(i+"URL");	
		String strDelivery = mapData.get(i+"Delivery");
		String strCollection = mapData.get(i+"Collection");
		String strMenuItem = mapData.get(i+"Menu_Items");
	    String[] arrMenuList = strMenuItem.split(";");
	   // System.out.println("String item is -- "+MenuList.toString());
		openURL(strURL);
        // System.out.println("The title is - "+ driver.getTitle());
        
	    implicitWait(waitTime);
	    test = rep.startTest("Order Now Page Validations.Data Set "+ strURL);
    	HomePage.clickOrderNow();
    	implicitWait(waitTime);
    	OrderNow.validateDeliveryOptions(strDelivery,strCollection);
    	OrderNow.validateFoodMenuOptions(arrMenuList);
	
    	rep.endTest(test);
    	rep.flush();
    	
	}
		closeBrowser();
		Thread.sleep(70);

}
}