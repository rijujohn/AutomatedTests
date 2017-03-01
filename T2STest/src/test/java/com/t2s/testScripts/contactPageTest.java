package com.t2s.testScripts;

import java.io.File;
import java.util.LinkedHashMap;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.t2s.excelReader.ODSReader;
import com.t2s.pagelocators.contactPageRepo;
import com.t2s.testbase.TestBase;

//import com.uktech.page.frontend.ContactPage;

public class contactPageTest extends TestBase{
	//File inputFile = new File("//home//dev//Documents//MyOwn//Input.ods");
	String inputFileString = System.getProperty("user.dir") + "//src//test//java//com//t2s//testData//Input.ods";
	File inputFile = new File(inputFileString);
	String inputSheetName = "Contact";
	LinkedHashMap<String, String> mapData = new LinkedHashMap<String, String>();
    int intRCount; 
    int waitTime = 500;

 //   File src = new File("//home//dev//eclipse//jee-neon//Jars//phantomjs-2.1.1-linux-i686//bin//phantomjs.exe");
	
   
    @Test(priority = 1)
	public void contactPageTests() throws Exception
	{

			ODSReader objODSReader = new ODSReader();
			mapData = objODSReader.readODS(inputFile,inputSheetName);
			intRCount = objODSReader.getRowCount(inputFile, inputSheetName);

			initializeBrowser();
			contactPageRepo ContactPage = PageFactory.initElements(driver, contactPageRepo.class);
		

			for(int i = 1;i<intRCount;i++)
			{
				
			String strURL = mapData.get(i+"URL");
			String strCustomerName = mapData.get(i+"CustomerName");
			String strDoorNumber = mapData.get(i+"DoorNumber");
			String strStreet = mapData.get(i+"Street");
			String strTown = mapData.get(i+"Town");
			String strCity = mapData.get(i+"City");
			String strPostCode = mapData.get(i+"PostCode");

		    
			openURL(strURL);
            // System.out.println("The title is - "+ driver.getTitle());
            Reporter.log("The title is - "+ driver.getTitle());
		    implicitWait(waitTime);
			ContactPage.clickContactLink();
			//Thread.sleep(70);
			implicitWait(waitTime);
			ContactPage.verifyContactPageHeader();
			ContactPage.verifyAddress(strCustomerName, strDoorNumber, strStreet, strTown,strCity,strPostCode);
			}
			closeBrowser();
			Thread.sleep(70);
	}
	
	}

	

