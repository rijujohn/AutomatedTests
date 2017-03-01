package com.t2s.testScripts;

import java.io.File;
import java.util.LinkedHashMap;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.t2s.excelReader.ODSReader;
import com.t2s.pagelocators.contactPageRepo;

public class testtask {
	File file = new File("//home//dev//Documents//MyOwn//Input.ods");
	
	LinkedHashMap<String, String> mapData = new LinkedHashMap<String, String>();
    int intRCount; 

	@Test(priority = 1)
	public void openHomePage() throws Exception
	{
		//try{
			WebDriver driver = new ChromeDriver();
			driver.get("https://foodhub.co.uk/home");
			
			
		/*	SoftAssert s_assert = new SoftAssert();
			ODSReader objODSReader = new ODSReader();
		//	contactPageRepo objContact = new contactPageRepo();
			mapData = objODSReader.readODS(file,"Contact");
			intRCount = objODSReader.getRowCount(file, "Contact");
			contactPageRepo objContact =PageFactory.initElements(driver, contactPageRepo.class);
			for(int i = 1;i<intRCount;i++)
			{	
			String strURL = mapData.get(i+"URL");
			String strCustomerName = mapData.get(i+"CustomerName");
			String strDoorNumber = mapData.get(i+"DoorNumber");
			String strStreet = mapData.get(i+"Street");
			String strTown = mapData.get(i+"Town");
			String strCity = mapData.get(i+"City");
			String strPostCode = mapData.get(i+"PostCode");

			driver.get(strURL);
			Thread.sleep(40);
			objContact.clickContactLink();
			Thread.sleep(70);*/
			//objContact.verifyAddress(strCustomerName, strDoorNumber, strStreet, strTown);
			//driver.findElement(By.linkText("Contact")).click();
			
			//WebElement contentAddressArea = driver.findElement(By.xpath("//div[@id='map']/following-sibling::div[@class='row']"));
	      //  WebElement contentAddressText = contentAddressArea.findElement(By.className("col-md-3"));
	      //  String contentAddressTextValue = contentAddressText.getText();
	      //  System.out.println(contentAddressText.getText());
	     //   s_assert.assertTrue(contentAddressTextValue.contains(strCustomerName), "Customer Name Mismatch");
	      //  s_assert.assertTrue(contentAddressTextValue.contains(strDoorNumber), "Door Number Mismatch");
	      //  s_assert.assertTrue(contentAddressTextValue.contains(strStreet), "Street Mismatch");
	      //  s_assert.assertTrue(contentAddressTextValue.contains(strTown), "Town Mismatch");
			}
			//s_assert.assertAll();
			
			//driver.close();
			//Thread.sleep(70);

	}

	
