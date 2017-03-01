package com.t2s.testScripts;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.logging.Level;

import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.t2s.excelReader.ExcelDataConfig;
import com.t2s.excelReader.ODSReader;
import com.t2s.pagelocators.contactPageRepo;
import com.t2s.testbase.BaseTestTemplate;
import com.t2s.testbase.TestBase;
import com.t2s.testutil.Utils;
import com.t2s.testutil.WDriver;
//import com.uktech.page.frontend.ContactPage;

public class backup extends TestBase{
	//File inputFile = new File("//home//dev//Documents//MyOwn//Input.ods");
	String inputFileString = System.getProperty("user.dir") + "//src//test//java//com//t2s//testData//Input.ods";
	File inputFile = new File(inputFileString);
	String inputSheetName = "Contact";
//	int waitTime = 70;
//	String PHANTOMJS_EXECUTABLE_PATH_PROPERTY = "phantomjs.binary.path";
//	String webDriverExecutableName = "//home//dev//eclipse//jee-neon//Jars//phantomjs-2.1.1-linux-i686//bin//phantomjs";
//	public static final String PHANTOMJS_CLI_ARGS = "phantomjs.cli.args";
	LinkedHashMap<String, String> mapData = new LinkedHashMap<String, String>();
    int intRCount; 
   // WDriver driver = new WDriver();

    //WebDriver driver = new ChromeDriver();
    File src = new File("//home//dev//eclipse//jee-neon//Jars//phantomjs-2.1.1-linux-i686//bin//phantomjs.exe");
	
 ///home/dev/eclipse/jee-neon/Jars/phantomjs-2.1.1-linux-i686/bin/phantomjs.exe
    
    @Test(priority = 1)
	public void contactPageTests() throws Exception
	{
		//try{
			
			//System.setProperty("phantomjs.binary.path",src.getAbsolutePath());
			//WebDriver driver = new PhantomJSDriver();
//////////////////
			
     //   WebDriver drv;
     //   drv = WDriver.getDriver(constants.getBrowser());
			
		/*	Capabilities caps = new DesiredCapabilities();
	        ((DesiredCapabilities) caps).setJavascriptEnabled(true);
	        ((DesiredCapabilities) caps).setCapability("takesScreenshot", true);
	        ArrayList<String> cliArgsCap = new ArrayList();
	        cliArgsCap.add("--webdriver-loglevel=NONE");
	        ((DesiredCapabilities) caps).setCapability(PhantomJSDriverService.PHANTOMJS_CLI_ARGS, cliArgsCap);
	        java.util.logging.Logger.getLogger(PhantomJSDriverService.class.getName()).setLevel(Level.OFF);

	        String[] phantomArgs = new String[]{
	                "--webdriver-loglevel=NONE"
	        };
			
	        ((DesiredCapabilities) caps).setCapability(
                    PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY,
                    webDriverExecutableName);
            ((DesiredCapabilities) caps).setCapability(
                    PhantomJSDriverService.PHANTOMJS_CLI_ARGS, phantomArgs
            );
            WebDriver driver = new PhantomJSDriver(caps);
            driver.manage().window().setSize(new Dimension(1280, 1024));*/
///////////////			
			
			
		  //  init();
			ODSReader objODSReader = new ODSReader();
			mapData = objODSReader.readODS(inputFile,inputSheetName);
			intRCount = objODSReader.getRowCount(inputFile, inputSheetName);
			contactPageRepo ContactPage =PageFactory.initElements(driver, contactPageRepo.class);
		    

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
           System.out.println("The title is - "+ driver.getTitle());
			//driver.get(strURL);
		//	implicitWait(waitTime,drv);
		    Thread.sleep(70);
		 //   drv.findElement(By.linkText("Contact")).click();
			ContactPage.clickContactLink();
			Thread.sleep(70);
		//	ContactPage.verifyContactPageHeader();
		//	implicitWait(waitTime,drv);
			ContactPage.verifyContactPageHeader();
			ContactPage.verifyAddress(strCustomerName, strDoorNumber, strStreet, strTown,strCity,strPostCode);
			}
			closeBrowser();
			Thread.sleep(70);
	}
	
	}
