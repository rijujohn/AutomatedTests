package com.t2s.testutil;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.GeckoDriverService;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

//import static com.uktech.helpers.Constants.CHROME_DRIVER_ID;
//import static com.uktech.helpers.Constants.FIREFOX_DRIVER_ID;
//import static com.uktech.helpers.Constants.GECKO_DRIVER_ID;
//import static com.uktech.helpers.Constants.GLOBAL_TIMEOUT_60;
//import static com.uktech.helpers.Constants.PHANTOMJS_DRIVER_ID;
//import static com.uktech.helpers.Constants.RESOURCES;

public final class WDriver {
   // private static final Integer FIREFOX_DRIVER_ID = null;
	//private static final Integer PHANTOMJS_DRIVER_ID = null;
	//private static final Integer CHROME_DRIVER_ID = null;
	//private static final Integer GECKO_DRIVER_ID = null;
	//private static final long GLOBAL_TIMEOUT_60 = 0;
	
    public static final int FIREFOX_DRIVER_ID = 1;
    public static final int PHANTOMJS_DRIVER_ID = 2;
    public static final int CHROME_DRIVER_ID = 3;
    public static final String RESOURCES = "src//main//resources//";
    public static final int GECKO_DRIVER_ID = 4;
    
	static WebDriver drv;

    private WDriver() {

    }

    public static WebDriver getDriver(String webDriverName) {
      
    	
    	Capabilities caps = new DesiredCapabilities();
        ((DesiredCapabilities) caps).setJavascriptEnabled(true);
        ((DesiredCapabilities) caps).setCapability("takesScreenshot", true);
        ArrayList<String> cliArgsCap = new ArrayList();
        cliArgsCap.add("--webdriver-loglevel=NONE");
        ((DesiredCapabilities) caps).setCapability(PhantomJSDriverService.PHANTOMJS_CLI_ARGS, cliArgsCap);
        java.util.logging.Logger.getLogger(PhantomJSDriverService.class.getName()).setLevel(Level.OFF);

        String[] phantomArgs = new String[]{
                "--webdriver-loglevel=NONE"
        };

        Constants constants = new Constants();
        String webDriverExecutableName = constants.getDriver();

        
        switch (constants.getWebDriverNameInNumbers().get(webDriverName)) {
            case FIREFOX_DRIVER_ID:
                drv = new FirefoxDriver();
                break;
            case PHANTOMJS_DRIVER_ID:
                ((DesiredCapabilities) caps).setCapability(
                        PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY,
                        getDriverPath(webDriverExecutableName)
                );
                ((DesiredCapabilities) caps).setCapability(
                        PhantomJSDriverService.PHANTOMJS_CLI_ARGS, phantomArgs
                );
                drv = new PhantomJSDriver(caps);
                break;
            case CHROME_DRIVER_ID:
            	
               System.setProperty(ChromeDriverService.CHROME_DRIVER_EXE_PROPERTY,
                        getDriverPath(webDriverExecutableName));

                Map<String, Object> prefs = new HashMap();
                prefs.put("profile.default_content_setting_values.notifications", 2);
                ChromeOptions options = new ChromeOptions();
                options.setExperimentalOption("prefs", prefs);
                drv = new ChromeDriver(options);
                break;
            case GECKO_DRIVER_ID:
                System.setProperty(GeckoDriverService.GECKO_DRIVER_EXE_PROPERTY,
                        getDriverPath(webDriverExecutableName));
                drv = new FirefoxDriver();
                break;

                
            default:
                ((DesiredCapabilities) caps).setCapability(
                        PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY,
                        getDriverPath(webDriverExecutableName)
                );
                ((DesiredCapabilities) caps).setCapability(
                        PhantomJSDriverService.PHANTOMJS_CLI_ARGS, phantomArgs
                );
                drv = new PhantomJSDriver(caps);
                break;
        }

        drv.manage().window().maximize();
        drv.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        return drv;
    }

    private static String getDriverPath(String driverName) {
        if (getOsNamePath().contains("windows")) {
            return getOsNamePath() + driverName + ".exe";
        } else {
            return getOsNamePath() + driverName;
        }
    }

    private static String getOsNamePath() {
        String osName = System.getProperty("os.name");
        if (osName.contains("Mac")) {
            return RESOURCES + "macosx//";
        } else if (osName.contains("Win")) {
            return RESOURCES + "windows//";
        } else {
            if (System.getProperty("os.arch").contains("amd")) {
                return RESOURCES + "linux//x64//";
            } else {
                return RESOURCES + "linux//x86//";
            }

        }
    }
}
