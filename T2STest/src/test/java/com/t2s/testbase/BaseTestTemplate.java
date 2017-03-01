//package com.uktech.test.template;
package com.t2s.testbase;

//import com.gurock.testrail.api.APIException;
import com.t2s.testutil.Constants;
import com.t2s.testutil.WDriver;
//import com.uktech.testrail.Cases;
//import com.uktech.testrail.Runs;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
//import org.json.simple.JSONArray;
//import org.json.simple.JSONObject;
import org.junit.Rule;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.testng.Reporter;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import static com.t2s.testutil.Constants.BURSLEMSPICE_RUN_ID_PROPERTY;
import static com.t2s.testutil.Constants.CRM_RUN_ID_PROPERTY;
import static com.t2s.testutil.Constants.GLOBAL_TIMEOUT_60;
import static com.t2s.testutil.Constants.LETSORDER_RUN_ID_PROPERTY;
import static com.t2s.testutil.Constants.SCREEN_SHOTS_PATH;
import static com.t2s.testutil.Constants.TESTRAIL_JENKINS_URL;
import static com.t2s.testutil.Constants.TESTRAIL_PROPERTIES;

public abstract class BaseTestTemplate {
    //private static final Logger LOGGER = LoggerFactory.getLogger(BaseTestTemplate.class);
    private static final String JOB_NAME_PLACEHOLDER = "JOBNAME";

    private static int testCount = 0;
    private static boolean report = false;
    protected WebDriver drv;
    private WebDriverWait wait;
    private Constants constants;

    @Rule
    public TestRule testWatcher = new TestWatcher() {
        @Override
        public void starting(Description d) {
        	Reporter.log("Launching browser...");
            constants = new Constants();
            drv = WDriver.getDriver(constants.getBrowser());
            wait = new WebDriverWait(drv, GLOBAL_TIMEOUT_60);
            constants.logTestParams();

            String reporterTarget = System.getProperty("report");

            if (reporterTarget != null) {
                report = !reporterTarget.isEmpty();
            } else {
            	
            	Reporter.log("Reporting will not be executed without reporting target.");
            
            }
        }

   /*     @Override
        public void finished(Description d) {
            if (report) {
                closeRun(++testCount, d.getClassName());
            }
            Reporter.log("Quitting driver...");
            drv.quit();
        }
*/
        /*    @Override
        public void succeeded(Description d) {
            if (report) {
            	Reporter.log("Reporting to testrail result of {} test case", d.getMethodName());

                Integer status = constants.getTestrailStatuses().get("passed");
                String comment = "OK !";
                String methodName = d.getMethodName();
                String className = d.getClassName();

                setTestCaseFinalStatus(status, comment, methodName, className);
            }
        }*/

        @Override
        public void failed(Throwable e, Description d) {
            String jenkinsJob = getJenkinsJobName(d.getClassName());
            String methodName = d.getMethodName();
            Integer status = constants.getTestrailStatuses().get("failed");
            String comment = ExceptionUtils.getStackTrace(e);
            String className = d.getClassName();

            Reporter.log("Creating screenshot...");
            String screenshotFileName = methodName + "__" + System.currentTimeMillis() + ".png";
            File scrFile = ((TakesScreenshot) drv).getScreenshotAs(
                    OutputType.FILE);
            File outputFile = new File(SCREEN_SHOTS_PATH, screenshotFileName);
            Reporter.log(screenshotFileName + " screenshot created.");

            if (report) {
                String finalComment = "Screenshot file url: "
                        + TESTRAIL_JENKINS_URL.replaceFirst(JOB_NAME_PLACEHOLDER, jenkinsJob)
                        + screenshotFileName + "\n\n" + comment;
                Reporter.log("Reporting to testrail result of {} test case" + methodName);
              //  setTestCaseFinalStatus(status, finalComment, methodName, className);
            }

            try {
                FileUtils.copyFile(scrFile, outputFile);
            } catch (IOException ioe) {
            	Reporter.log("Error copying screenshot after exception." + ioe);
            }
        }
    };

    private String getJenkinsJobName(String className) {
        if (className.contains("frontend")) {
            return "Frontend";
        } else if (className.contains("letsorder")) {
            return "Letsorder";
        } else if (className.contains("crm")) {
            return "CRM";
        }
        return null;
    }
/*
    private void closeRun(int testCount, String className) {
        try {
            if (testCount == getTestCasesCountOfRun(className)) {
                String runId = getRunId(className);
                Runs runs = new Runs();
                runs.closeRunById(runId);
            }
        } catch (IOException | APIException e) {
            e.printStackTrace();
        }
    }

    private int getTestCasesCountOfRun(String className) throws IOException, APIException {
        String runId = getRunId(className);
        Cases cases = new Cases();
        JSONArray testCases = cases.getTestCasesOfRun(runId);
        return testCases.size();
    }

    private void setTestCaseFinalStatus(int result, String comment, String methodName, String className) {
        try {
            String runId = getRunId(className);
            String testCaseId = getTestCaseIdByMethodName(runId, methodName);
            reportTestCaseStatus(result, comment, runId, testCaseId);
        } catch (IOException | APIException e) {
            e.printStackTrace();
        }
    }*/

    private String getRunId(String className) throws IOException {
        Properties properties = new Properties();
        if (className.contains("frontend")) {
            properties.load(new FileReader(new File(TESTRAIL_PROPERTIES
                    + BURSLEMSPICE_RUN_ID_PROPERTY + ".properties")));
            return properties.getProperty(BURSLEMSPICE_RUN_ID_PROPERTY);
        } else if (className.contains("letsorder")) {
            properties.load(new FileReader(new File(TESTRAIL_PROPERTIES
                    + LETSORDER_RUN_ID_PROPERTY + ".properties")));
            return properties.getProperty(LETSORDER_RUN_ID_PROPERTY);
        } else if (className.contains("crm")) {
            properties.load(new FileReader(new File(TESTRAIL_PROPERTIES
                    + CRM_RUN_ID_PROPERTY + ".properties")));
            return properties.getProperty(CRM_RUN_ID_PROPERTY);
        }
        return null;
    }

  /*  private String getTestCaseIdByMethodName(String runId, String methodName) throws IOException, APIException {
        Cases cases = new Cases();
        JSONArray testCases = cases.getTestCasesOfRun(runId);
        for (Object testCase : testCases) {
            JSONObject tCase = (JSONObject) testCase;
            if (methodName.equals(tCase.get("title").toString())) {
                return tCase.get("case_id").toString();
            }
        }

        return null;
    }*/

  //  private void reportTestCaseStatus(int result, String comment, String runId, String testCaseId)
    //        throws IOException, APIException {
   //     Cases cases = new Cases();
   //     cases.setTestCaseStatus(result, comment, runId, testCaseId);
    }

