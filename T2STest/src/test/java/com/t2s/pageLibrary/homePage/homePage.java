package com.t2s.pageLibrary.homePage;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

import com.t2s.testbase.TestBase;
import com.relevantcodes.extentreports.LogStatus;

public class homePage extends TestBase {
	
	
	
	public void validateCalculation(String firstVal,String secondVal,String validateString,String operator )
	{
		String stepResult = setText ("t2s.calchomePage.txtFirstVal",firstVal);
		fnCheckAndLogExtentResult(stepResult,"Enter data in first numeric field","Data entered ","Unable to enter data");
		stepResult = getValue ("t2s.calchomePage.txtFirstVal");
		fnExtentCompareString (stepResult,firstVal,test,"Validate the first numeric entry","Data set correctly","Data not set correctly");
		
		stepResult = setText ("t2s.calchomePage.txtlastVal",secondVal);
		fnCheckAndLogExtentResult(stepResult,"Enter data in Second numeric field","Data entered ","Unable to enter data");
		stepResult = getValue ("t2s.calchomePage.txtlastVal");
		fnExtentCompareString (stepResult,secondVal,test,"Validate the Second numeric entry","Data set correctly","Data not set correctly");
		
		stepResult =  selectDropDown ("t2s.calchomePage.selOperator",operator);
		
		stepResult =  clickWebElement ("t2s.calchomePage.btnCalc");
		fnCheckAndLogExtentResult(stepResult,"Click the Calculate button","Button clicked","Button not clicked");
		
		stepResult = getValue ("t2s.calchomePage.txtResult");
		fnExtentCompareString (stepResult,validateString,test,"Validate the Result","Result appears correctly","Result Does not appear correctly");
		
		
		
		//System.out.println();
		//System.out.println(h);
	}
	
	

}
