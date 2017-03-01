//package com.uktech.helpers;
package com.t2s.testutil;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

public class Constants {

    public static final int POLLING_IN_SECONDS_5 = 5;
    public static final int POLLING_IN_SECONDS_10 = 10;

    public static final int SLEEP_TIME_IN_MILISECONDS_200 = 200;
    public static final int SLEEP_TIME_IN_MILISECONDS_1000 = 1000;
    public static final int SLEEP_TIME_IN_MILISECONDS_2000 = 2000;
    public static final int SLEEP_TIME_IN_MILISECONDS_3000 = 3000;
    public static final int SLEEP_TIME_IN_MILISECONDS_4000 = 4000;
    public static final int SLEEP_TIME_IN_MILISECONDS_5000 = 5000;

    public static final int GLOBAL_TIMEOUT_30 = 30;
    public static final int GLOBAL_TIMEOUT_60 = 60;
    public static final int GLOBAL_TIMEOUT_90 = 90;

    public static final String SCREEN_SHOTS_PATH = "build//test-results//screenshots//";
    public static final String TEST_DATA_DIRECTORY = "src//test//resources//data//json//";
    public static final String TESTRAIL_PROPERTIES = "src//test//resources//prepare//";
    public static final String TESTRAIL_BASE_URL = "https://touch2success.testrail.com";
    public static final String TESTRAIL_PROJECT_ID_BURSLEMSPICE = "12";
    public static final String TESTRAIL_PROJECT_ID_LETSORDER = "13";
    public static final String TESTRAIL_PROJECT_ID_CRM = "14";
    public static final String TESTRAIL_USER_NAME = "grigavag@gmail.com";
    public static final String TESTRAIL_API_KEY = "35s0VDPcixXMTyFT/2Ix-S3bCGC5a9zFaEA2IZCia";
    public static final String TESTRAIL_JENKINS_URL =
            "http://jenkins.touch2success.com/job/JOBNAME/ws/build/test-results/screenshots/";
    public static final String BURSLEMSPICE_RUN_ID_PROPERTY = "burslemspiceRunId";
    public static final String LETSORDER_RUN_ID_PROPERTY = "letsorderRunId";
    public static final String CRM_RUN_ID_PROPERTY = "crmRunId";
    public static final int FIREFOX_DRIVER_ID = 1;
    public static final int PHANTOMJS_DRIVER_ID = 2;
    public static final int CHROME_DRIVER_ID = 3;
    public static final String RESOURCES = "src//main//resources//";
    public static final int GECKO_DRIVER_ID = 4;

  //  private static final Logger LOGGER = LoggerFactory.getLogger(Constants.class);
    private static final HashMap<String, Integer> WEB_DRIVER_NAME_IN_NUMBERS = new HashMap();
    private static final String TEST_PROPERTIES = "src//test//resources//prepare//test.properties";
    private static final String BURSLEM_USER_NAME = "grigor";
    private static final String BURSLEM_PASSWORD = "house4790";
    private static final int TESTRAIL_CASE_PASSED = 1;
    private static final int TESTRAIL_CASE_BLOCKED = 2;
    private static final int TESTRAIL_CASE_RETEST = 3;
    private static final int TESTRAIL_CASE_FAILED = 5;
    private HashMap<String, String> testProperties = new HashMap<String, String>();
    private HashMap<String, Integer> testrailStatuses = new HashMap<String, Integer>();

    public Constants() {
        initTestProperties();
        initWebDrivers();
        initTestrailTestStatuses();
    }

    private void initTestProperties() {
        String usernameProperty = "";
        String passwordProperty = "";
        String urlProperty = "";

        Properties properties = new Properties();
        try {
            properties.load(new FileReader(new File(TEST_PROPERTIES)));
            usernameProperty = properties.getProperty("user");
            passwordProperty = properties.getProperty("pass");
            urlProperty = properties.getProperty("domain");
        } catch (IOException e) {
         //   LOGGER.debug("Test properties is not created, trying to check properties from command line...");
        }

        String user = System.getProperty("user");
        String pass = System.getProperty("pass");
        String url = System.getProperty("url");
        String browser = System.getProperty("browser");

        if (url != null && !url.isEmpty()) {
            testProperties.put("url", url + "/");
        } else {
            testProperties.put("url", urlProperty);
        }

        if (user != null && !user.isEmpty()) {
            testProperties.put("user", user);
        } else {
            testProperties.put("user", usernameProperty);
        }

        if (pass != null && !pass.isEmpty()) {
            testProperties.put("pass", pass);
        } else {
            testProperties.put("pass", passwordProperty);
        }

        if (browser != null && !browser.isEmpty()) {
            testProperties.put("browser", browser);
            switch (browser) {
            case "chrome":
                testProperties.put("driver", "chromedriver");
                break;
            case "gecko":
                testProperties.put("driver", "geckodriver");
                break;
            default:
                testProperties.put("driver", browser);
                break;
            }
        } else {
           testProperties.put("browser", "phantomJS");
            testProperties.put("driver", "phantomjs");
        }
    }

    private void initTestrailTestStatuses() {
        testrailStatuses.put("passed", TESTRAIL_CASE_PASSED);
        testrailStatuses.put("blocked", TESTRAIL_CASE_BLOCKED);
        testrailStatuses.put("retest", TESTRAIL_CASE_RETEST);
        testrailStatuses.put("failed", TESTRAIL_CASE_FAILED);
    }

    private void initWebDrivers() {
        WEB_DRIVER_NAME_IN_NUMBERS.put("firefox", FIREFOX_DRIVER_ID);
        WEB_DRIVER_NAME_IN_NUMBERS.put("phantomJS", PHANTOMJS_DRIVER_ID);
        WEB_DRIVER_NAME_IN_NUMBERS.put("chrome", CHROME_DRIVER_ID);
        WEB_DRIVER_NAME_IN_NUMBERS.put("gecko", GECKO_DRIVER_ID);
    }

    public HashMap<String, Integer> getTestrailStatuses() {
        return testrailStatuses;
    }

    public void logTestParams() {
     //   LOGGER.info("Url/env for current test is - {}", testProperties.get("url"));
    //    LOGGER.info("Using credentials - user: {} and pass: {}", testProperties.get("user"),
    //            testProperties.get("pass"));
     //   LOGGER.info("Using {} driver for testing", testProperties.get("driver"));
    }

    public String getBaseUrl() {
        return testProperties.get("url");
    }

    public String getUserName() {
        return testProperties.get("user");
    }

    public String getBurslemUserName() {
        return BURSLEM_USER_NAME;
    }

    public String getPassword() {
        return testProperties.get("pass");
    }

    public String getBurslemPassword() {
        return BURSLEM_PASSWORD;
    }

    public String getBrowser() {
        return testProperties.get("browser");
    }

    public String getDriver() {
        return testProperties.get("driver");
    }

    HashMap<String, Integer> getWebDriverNameInNumbers() {
        return WEB_DRIVER_NAME_IN_NUMBERS;
    }
}