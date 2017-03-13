package com.t2s.testScripts;

import java.io.File;
import java.util.LinkedHashMap;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.t2s.excelReader.ODSReader;
import com.t2s.extent.ExtentManager;
import com.t2s.pagelocators.contactPageRepo;
import com.t2s.prepare.PrepareEnvForTests;
import com.t2s.testbase.TestBase;

//import com.uktech.page.frontend.ContactPage;

public class contactPageTest extends TestBase{
	//File inputFile = new File("//home//dev//Documents//MyOwn//Input.ods");
	String inputFileString = System.getProperty("user.dir") + "//src//test//java//com//t2s//testData//Input.ods";
	File inputFile = new File(inputFileString);
	String inputSheetName = "Contact";
	String jsonBodyPath = System.getProperty("user.dir") + "/src/test/java/com/t2s/testData/";
	LinkedHashMap<String, String> mapData = new LinkedHashMap<String, String>();
    int intRCount; 
    int waitTime = 5000;

 //   File src = new File("//home//dev//eclipse//jee-neon//Jars//phantomjs-2.1.1-linux-i686//bin//phantomjs.exe");
	
   
    @Test(priority = 1)
	public void contactPageTests() throws Exception
	{
    	    ExtentReports rep = ExtentManager.getInstances();
			ODSReader objODSReader = new ODSReader();
			mapData = objODSReader.readODS(inputFile,inputSheetName);
			intRCount = objODSReader.getRowCount(inputFile, inputSheetName);

			PrepareEnvForTests PrepEnv = new PrepareEnvForTests();
			
			//Initialize browser and page factory
			initializeBrowser();
			contactPageRepo ContactPage = PageFactory.initElements(driver, contactPageRepo.class);
		

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
			String strDataCreationStatus = PrepEnv.prepare(strToken,JSONBodyPath);	
			
			//Continue if data created - retrieve input data
			if (strDataCreationStatus.toUpperCase().equals("SUCCESS"))
			{	
			String strURL = mapData.get(i+"URL");
			String strCustomerName = mapData.get(i+"CustomerName");
			String strDoorNumber = mapData.get(i+"DoorNumber");
			String strStreet = mapData.get(i+"Street");
			String strTown = mapData.get(i+"Town");
			String strCity = mapData.get(i+"City");
			String strPostCode = mapData.get(i+"PostCode");
			String strTakeAwayNumber = mapData.get(i+"TakeAwayNumber");
			String strContactName = mapData.get(i+"ContactName");
			String strContactNumber = mapData.get(i+"ContactNumber");
			String strOpenHours = mapData.get(i+"OpenHours");
		    String[] arrOpenHourList = strOpenHours.split(";");
		    //Start extent report log
			test = rep.startTest("Contact Page Validations.Data Set "+ strURL);
			openURL(strURL);
            Reporter.log("The title is - "+ driver.getTitle());
		    implicitWait(waitTime);
		    //Navigate to Contact page
			ContactPage.clickContactLink();
			implicitWait(waitTime);
			//Contact page tests
			ContactPage.verifyContactPageHeader();
			ContactPage.verifyAddress(strCustomerName, strDoorNumber, strStreet, strTown,strCity,strPostCode);
			ContactPage.verifyOpenHours(arrOpenHourList);
			ContactPage.verifyContactTextInfo(strTakeAwayNumber,strContactName,strContactNumber);
			//End extent report log
	    	rep.endTest(test);
	    	rep.flush();
			}
			}
			}	
			closeBrowser();
			implicitWait(waitTime);
	}
	
	}

	

