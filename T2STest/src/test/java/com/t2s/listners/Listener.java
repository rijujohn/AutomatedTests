package com.t2s.listners;

import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.t2s.extent.ExtentManager;
import com.t2s.testbase.TestBase;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.LogStatus;

public class Listener extends TestBase implements ITestListener, ISuiteListener, IInvokedMethodListener {
   
	
	ExtentReports rep = ExtentManager.getInstances();
	public Listener() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onFinish(ITestContext arg0) {
		// TODO Auto-generated method stub
		Reporter.log("From listner - Ending the test "+arg0);
		
	}

	@Override
	public void onStart(ITestContext arg0) {
		// TODO Auto-generated method stub
		Reporter.log("From listner - Starting the test "+arg0);
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
		// TODO Auto-generated method stub
		Reporter.log("From listner - The test failed out "+arg0);
	}

	@Override
	public void onTestFailure(ITestResult arg0) {
		// TODO Auto-generated method stub
		Reporter.log("From listner - The test failed out "+arg0);
		test = rep.startTest("Test was Failed");
		test.log(LogStatus.FAIL,"Test Failed", arg0.toString() );
		Reporter.log("From listner - The test failed out "+arg0);
		rep.endTest(test);
    	rep.flush();
		
	}

	@Override
	public void onTestSkipped(ITestResult arg0) {
		// TODO Auto-generated method stub
		
		test = rep.startTest("Test was Skipped");
		test.log(LogStatus.FAIL,"Test Skipped", arg0.toString() );
		Reporter.log("From listner - The test failed out "+arg0);
		rep.endTest(test);
    	rep.flush();
	}

	@Override
	public void onTestStart(ITestResult arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestSuccess(ITestResult arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterInvocation(IInvokedMethod arg0, ITestResult arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void beforeInvocation(IInvokedMethod arg0, ITestResult arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onFinish(ISuite arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStart(ISuite arg0) {
		// TODO Auto-generated method stub
		
	}

}
