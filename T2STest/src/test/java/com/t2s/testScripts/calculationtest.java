package com.t2s.testScripts;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


import com.t2s.excelReader.ExcelDataConfig;
import com.t2s.extent.ExtentManager;
import com.t2s.pageLibrary.homePage.homePage;
import com.t2s.testbase.TestBase;
import com.relevantcodes.extentreports.ExtentReports;




public class calculationtest extends TestBase {
	
	
	homePage HomePage = new homePage();

	//Riju
	//Updated on 2/4/2017
	ExtentReports rep = ExtentManager.getInstances();

	
	@Test(priority = 1)
	public void openHomePage() throws Exception
	{
	//	init();
	}
	
	@Test(priority = 2,dependsOnMethods={"openHomePage"},dataProvider = "AdditionTestData")
	public void validateAddition(String firstVal,String secondVal,String validationData) throws Exception
	{   
		test = rep.startTest("Test2CheckAddition.Data"+firstVal +","+secondVal);
		HomePage.validateCalculation(firstVal,secondVal,validationData,"+");
		rep.endTest(test);
    	rep.flush();
	}
	@Test(priority = 3,dependsOnMethods={"openHomePage"},dataProvider = "substractionTestData")
	public void validateSubstraction(String firstVal,String secondVal,String validationData) throws Exception
	{   
		test = rep.startTest("Test2CheckSubtration.Data"+firstVal +","+secondVal);
		HomePage.validateCalculation(firstVal,secondVal,validationData,"-");
		rep.endTest(test);
    	rep.flush();
	}
	
	
	
/*
		
	@Test(priority = 5) 
	public void tearPwdDown()
	{  
		test = rep.startTest("Test1ForgotPassword_Step4_CloseBrowser");	
	    closeBrowser();
	    rep.endTest(test);
    	rep.flush();
	}
	
*/	
	
	@ DataProvider(name = "AdditionTestData")
	public Object [][] passData() throws Exception
	{   ExcelDataConfig excelObj = new ExcelDataConfig(System.getProperty("user.dir") + "//src//test//java//com//t2s//testData//Addition.xlsx"); 
	    int rows = excelObj.getRowCount(0);
        System.out.println("rowe -- " + rows );
	    Object [][] data = new Object [2][3];
	    for (int i= 0 ; i<= rows;i++ )  
        {
        	data[i][0] = excelObj.getData(0, i, 0);
        	data[i][1] = excelObj.getData(0, i, 1);
        	data[i][2] = excelObj.getData(0, i, 2);
        }
		
		return data;
	}
	

	@ DataProvider(name = "substractionTestData")
	public Object [][] passsubData() throws Exception
	{   ExcelDataConfig excelObj = new ExcelDataConfig(System.getProperty("user.dir") + "//src//test//java//com//t2s//testData//Subtraction.xlsx"); 
	    int rows = excelObj.getRowCount(0);
        System.out.println("rowe -- " + rows );
	    Object [][] data = new Object [2][3];
	    for (int i= 0 ; i<= rows;i++ )  
        {
        	data[i][0] = excelObj.getData(0, i, 0);
        	data[i][1] = excelObj.getData(0, i, 1);
        	data[i][2] = excelObj.getData(0, i, 2);
        }
		
		return data;
	}
	
}
