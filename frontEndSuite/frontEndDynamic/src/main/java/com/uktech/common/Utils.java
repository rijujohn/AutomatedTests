package com.uktech.common;




import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;

import junit.framework.Assert;

























import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.GeckoDriverService;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.uktech.common.TestBase;


public class Utils {
	//public static Properties Repository = new Properties();
	
	//public String TakeScreenShot = TakeScreenShotIN.toUpperCase();
	
	public static WebDriver driver;
	public static ExtentTest test;
	public static String filePath = System.getProperty("user.dir") + "//build//test-results//frontEndDynamic//";


	
	///////////////////
	String PHANTOMJS_EXECUTABLE_PATH_PROPERTY = "phantomjs.binary.path";
	
	//String webDriverExecutableName = "//home//dev//eclipse//jee-neon//Jars//phantomjs-2.1.1-linux-i686//bin//phantomjs";
	public static final String PHANTOMJS_CLI_ARGS = "phantomjs.cli.args";
	
	//////////////////
	public static WebDriver selectBrowser(String browser){
	Capabilities caps = new DesiredCapabilities();
    ((DesiredCapabilities) caps).setJavascriptEnabled(true);
    ((DesiredCapabilities) caps).setCapability("takesScreenshot", true);
    ArrayList<String> cliArgsCap = new ArrayList<String>();
    cliArgsCap.add("--webdriver-loglevel=NONE");
    ((DesiredCapabilities) caps).setCapability(PhantomJSDriverService.PHANTOMJS_CLI_ARGS, cliArgsCap);
    java.util.logging.Logger.getLogger(PhantomJSDriverService.class.getName()).setLevel(Level.OFF);

    String[] phantomArgs = new String[]{
            "--webdriver-loglevel=NONE"
    };

  //  Constants constants = new Constants();
 //   String webDriverExecutableName = constants.getDriver();
	
	///////////////////////
	{
		if (browser.equals("firefox"))
		{
			System.setProperty("webdriver.gecko.driver",System.getProperty("user.dir")+"//src//test//java//com//t2s//browserDriver//geckodriver.exe");
			driver = new FirefoxDriver();
			return driver;
		}
		if (browser.equals("chrome"))
		{
			
			System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"//src//test//java//com//t2s//browserDriver//chromedriver");
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			return driver;
		}
		if (browser.equals("chrome32"))
		{
			driver = new ChromeDriver();
			return driver;
			
		}
		if (browser.equals("phantom"))
			
		{
			//String webDriverExecutableName = System.getProperty("user.dir")+"//src//test//java//com//t2s//browserDriver//linux//x32//phantomjs";
			String webDriverExecutableName = System.getProperty("user.dir")+"//src//main//resources//linux//x32//phantomjs";

            ((DesiredCapabilities) caps).setCapability(
                    PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY,
                    webDriverExecutableName
            );
            ((DesiredCapabilities) caps).setCapability(
                    PhantomJSDriverService.PHANTOMJS_CLI_ARGS, phantomArgs
            );
            driver = new PhantomJSDriver(caps);
            return driver;
			
		}
		
		if (browser.equals("ie"))
		{
			driver = new InternetExplorerDriver();
			return driver;
		}
		
		return null;
	
	}	
	}
	

	public void implicitWait(int timeInSec)
	{
		Reporter.log("waiting for page to load..");
		try{
		driver.manage().timeouts().implicitlyWait(timeInSec, TimeUnit.SECONDS);
		Reporter.log("Page is loaded");
		}
		catch (Throwable error)
		{
			System.out.println("Timeout for page load request");
			Reporter.log("Timeout for page load request");
		//	Assert.assertTrue(false,"Time out for page after" +timeInSec + " seconds");
		//	Assert.assertTrue(false);
		}
		
		
	}
	
	public void driverwait () throws InterruptedException{
		
		Thread.sleep(30000);
		
	}	
	
	public void elemWait(WebElement elem )
	{	
		
		WebDriverWait wait = new WebDriverWait(driver, 10);
	    wait.until(ExpectedConditions.elementToBeClickable(elem));

	}
 
	public String switchFrame (WebElement elem )
	{	
		try{
		driver.switchTo().frame(elem);
		
		Reporter.log("Control switched to frame --> '" + elem + "'");
		return "Pass";
		}
        catch (Throwable error)
        { 	
        	
        	Reporter.log("Control not switched to frame --> '" + elem + "'.Error -" + error.getMessage()); 
        	return "Fail";
         }

	}
	//switchFrame (getWebElement("cashkaro.signInframe"));

	public String switchToPopUpFrame ()
	{	
		try{
		for(String winHandle : driver.getWindowHandles()){
			    driver.switchTo().window(winHandle);
			}	
		
		Reporter.log("Control switched to popup");
		return "Pass";
		}
        catch (Throwable error)
        { 	
        	
        	Reporter.log("Control not switched to popup.Error -" + error.getMessage()); 
        	return "Fail";
         }

	}
	
	public String switchToPopUpBrowserIndex (int brwIndex)
	{	
		try{
			List<String> browserTabs = new ArrayList<String> (driver.getWindowHandles());
			driver.switchTo().window(browserTabs.get(brwIndex));
			String brwTitle = driver.getTitle();

		
		Reporter.log("Control switched to popup browser");
		return brwTitle;
		}
        catch (Throwable error)
        { 	
        	
        	Reporter.log("Control not switched to popup browser.Error -" + error.getMessage()); 
        	return "Fail";
         }

	}
	
	public String closeUpBrowserIndex (int brwIndex)
	{	
		try{
			List<String> browserTabs = new ArrayList<String> (driver.getWindowHandles());
			driver.switchTo().window(browserTabs.get(brwIndex)).close();
	
		Reporter.log("Browser close");
		return "Pass";
		}
        catch (Throwable error)
        { 	
        	
        	Reporter.log("Browser not closed.Error -" + error.getMessage()); 
        	return "Fail";
         }

	}
	
	
	public void switchDefault ()
	{
	driver.switchTo().defaultContent();
	}
	


public String fnSaveScreenShotByPoint() throws IOException
{ String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
String snapshotName = "Cap" + timeStamp +".png" ;
WebElement ele = driver.findElement(By.id("security_image"));
File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
BufferedImage  fullImg = ImageIO.read(screenshot);
// Get the location of element on the page
Point point = ele.getLocation();
// Get width and height of the element
int eleWidth = ele.getSize().getWidth();
int eleHeight = ele.getSize().getHeight();
// Crop the entire page screenshot to get only element screenshot
BufferedImage eleScreenshot= fullImg.getSubimage(point.getX(), point.getY(),
   eleWidth, eleHeight);
ImageIO.write(eleScreenshot, "png", screenshot);
// Copy the element screenshot to disk
File screenshotLocationA = new File(System.getProperty("user.dir")+"\\src\\test\\java\\com\\cashkaro\\screenshot\\runtimeCaptcha\\"+snapshotName);
FileUtils.copyFile(screenshot, screenshotLocationA);
//return screenshotLocationA;

String sumVal = fnCompareCaptcha(screenshotLocationA);
System.out.println("Value is" +sumVal );
return sumVal;
}
//System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"\\src\\test\\java\\com\\cashkaro\\browserDriver\\chromedriver.exe");

public String fnCompareCaptcha(File newFileLocation1)
{
String retval = "False";
//File newFileLocation1 = new File("C:\\Users\\dell\\Desktop\\images\\Sam.png");
Iterator it = FileUtils.iterateFiles(new File(System.getProperty("user.dir")+"\\src\\test\\java\\com\\cashkaro\\captchaValidationImage\\"), null, false);
while(it.hasNext())
{
	String TheFileName = ((File) it.next()).getName();
//    System.out.println(((File) it.next()).getName());
 //  File screenshotLocationC = new File("C:\\Users\\dell\\Desktop\\images\\capchaNew.png");
   File screenshotLocationB = new File(System.getProperty("user.dir")+"\\src\\test\\java\\com\\cashkaro\\captchaValidationImage\\"+TheFileName);   
   float gm = compareImage(newFileLocation1, screenshotLocationB);
   if (gm == 100)
   {
   	System.out.println("Found"+TheFileName);
   	String[] ghspl = TheFileName.split(Pattern.quote(".")); 
   	System.out.println("Found"+ghspl[0]);
   	retval =  ghspl[0];
   	return retval;
   	//break; 
   }

}
   return retval;
}








public float compareImage(File fileA, File fileB) {

    float percentage = 0;
    try {
        // take buffer data from both image files //
        BufferedImage biA = ImageIO.read(fileA);
        DataBuffer dbA = biA.getData().getDataBuffer();
        int sizeA = dbA.getSize();
        BufferedImage biB = ImageIO.read(fileB);
        DataBuffer dbB = biB.getData().getDataBuffer();
        int sizeB = dbB.getSize();
        int count = 0;
        // compare data-buffer objects //
        if (sizeA == sizeB) {

            for (int i = 0; i < sizeA; i++) {

                if (dbA.getElem(i) == dbB.getElem(i)) {
                    count = count + 1;
                }

            }
            percentage = (count * 100) / sizeA;
        } else {
            System.out.println("Both the images are not of same size");
        }

    } catch (Exception e) {
        System.out.println("Failed to compare image files ..."+e.getMessage());
    }
    return percentage;
}















}