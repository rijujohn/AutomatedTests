package com.uktech.page.frontEndDynamic;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Reporter;
import org.testng.asserts.SoftAssert;

import com.uktech.common.TestBase;


public class contactPageRepo extends TestBase {
	
	

	SoftAssert s_assert = new SoftAssert();
    @FindBy(xpath="//div[@id='map']/following-sibling::div[@class='row']")
    private WebElement contentAddressArea;
  
    
 //   @FindBy(xpath="//*[@id='contactus-pag-sec']/div/div[1]/h2")
 //   private WebElement contentContactUSArea;
    
 //   @FindBy(xpath="//section[@id='contactus-pag-sec']")
    @FindBy(css="section[id='contactus-pag-sec']")
    private WebElement contentContactUSPageSection;
    
    @FindBy(linkText="Contact")
    private WebElement linkContact;
    ////button[contains(text(),'Calculate')]
    @FindBy(xpath = "//*[contains(@class,'service-times')]")
    private WebElement openHoursList;
    ////input[starts-with(@id,'reportcombo')
    @FindBy(xpath = ".//*[@id = 'bodyContent']/p/a")
    private WebElement mapsAddress;
    
    
  ////////////////////////////////////  
    public void clickContactLink() {
    	try
    	{
    	linkContact.click();
    	Reporter.log("Clicked on Contact link");
    	}
    	catch(Exception e)
    	{
    	//SoftAssert s_assert = new SoftAssert();		
    	Reporter.log("Error while clicking Contact link.Error message - "+e.getMessage());	
    	s_assert.fail("Error while clicking Contact link.Error message - "+e.getMessage());
    	//s_assert.assertAll();
    	}
    }
    
    public void verifyContactPageHeader(String strTemplate) throws IOException {
    	
    	String contentContactUSAreaText = contentContactUSPageSection.getText().trim();
		fnExtentContainsString (contentContactUSAreaText,"Contact us",test,"Validate Contact us header","Header text is visible","Header text is not visible");
    	//s_assert.assertEquals(contentContactUSAreaText, "Contact us");
    	s_assert.assertTrue(contentContactUSAreaText.contains("Contact us"), strTemplate +"-Header mismatch");
    }
    
    public void verifyAddress(String strTemplate,String strCustomerName,String strDoorNumber,String strStreet,String strTown,String strCity,String strPostCode) throws IOException {
    	
    	WebElement contentAddressText = contentAddressArea.findElement(By.className("col-md-3"));
    	String contentAddressTextValue = contentAddressText.getText();
    	fnExtentContainsString (contentAddressTextValue,strCustomerName,test,"Validate Contact page - Customer Name","Name matching","Name mismatch");
    	fnExtentContainsString (contentAddressTextValue,strDoorNumber,test,"Validate Contact page - DoorNumber","DoorNumber matching","DoorNumber mismatch");
    	fnExtentContainsString (contentAddressTextValue,strStreet,test,"Validate Contact page - Street","Street matching","Street mismatch");
    	fnExtentContainsString (contentAddressTextValue,strTown,test,"Validate Contact page - Town","Town matching","Town mismatch");
    	fnExtentContainsString (contentAddressTextValue,strCity,test,"Validate Contact page - City","City matching","City mismatch");
    	fnExtentContainsString (contentAddressTextValue,strPostCode,test,"Validate Contact page - PostCode","PostCode matching","PostCode mismatch");
    	s_assert.assertTrue(contentAddressTextValue.contains(strCustomerName), strTemplate +"-Customer name mismatch");
    	s_assert.assertTrue(contentAddressTextValue.contains(strDoorNumber), strTemplate +"-Customer door No mismatch");
    	s_assert.assertTrue(contentAddressTextValue.contains(strStreet), strTemplate +"-Customer street mismatch");
    	s_assert.assertTrue(contentAddressTextValue.contains(strTown), strTemplate +"-Customer town mismatch");
    	s_assert.assertTrue(contentAddressTextValue.contains(strCity), strTemplate +"-Customer city mismatch");
    	//s_assert.assertAll();
    }
    /////////////////////////////////////////////////////////
    
    public void verifyOpenHours(String strTemplate,String[] arrMenuList) throws InterruptedException, IOException {
    	ArrayList<String> childItemText = verifyChildItems(openHoursList);
        for (String item:arrMenuList )
        {
        	fnExtentContainsString (childItemText.toString(),item,test,"Validate Open Hours","Item "+ item +"is visible","Item "+ item +"is not visible");
        	s_assert.assertTrue(childItemText.contains(item), strTemplate +"-Open hour mismatch - "+item);
        }
    }
    public void assertAllFn()
    {
    	s_assert.assertAll();
    }
    
    public void verifyContactTextInfo(String strTemplate,String strTakeAwayNumber,String strContactName,String strContactNumber) throws InterruptedException, IOException {
   //	WebElement contactText = contentContactUSPageSection.findElement(By.className("col-md-6"));
   	    String TakeAwayFlag = "False";
   	    String ContactNameFlag = "False";
   	    String ContactNumberFlag = "False";
   	 
    	ArrayList<String> childItemSectionText = verifyChildItems(contentContactUSPageSection);
    	//contentContactUSPageSection.findElements(By.className("col-md-6"));
    	System.out.println(childItemSectionText.toString());
    //	for (String e  : childItemSectionText)
    	for (int i = 0;i<childItemSectionText.size();i++)	
    //	{
    	{
    	System.out.println(childItemSectionText.get(i).toString());
    	if (childItemSectionText.get(i).toString().contains(strTakeAwayNumber) && TakeAwayFlag.equals("False"))
    	{
    		TakeAwayFlag = "True";
    		fnExtentContainsString (strTakeAwayNumber,strTakeAwayNumber,test,"Validate contact information","Takeaway number visible","Takeaway number is not visible");
    	}
    	if (childItemSectionText.get(i).toString().contains(strContactName) && ContactNameFlag.equals("False"))
    	{
    		ContactNameFlag = "True";
    		fnExtentContainsString (strContactName,strContactName,test,"Validate contact information","Contact Name visible","Contact Name is not visible");
    	}
    	if (childItemSectionText.get(i).toString().contains(strContactNumber) && ContactNumberFlag.equals("False"))
    	{
    		ContactNumberFlag = "True";
    		fnExtentContainsString (strContactNumber,strContactNumber,test,"Validate contact information","Contact Number visible","Contact Number is not visible");
    	}
    		
    	}
    	if(TakeAwayFlag == "False")
    	{
    		fnExtentContainsString ("NA",strTakeAwayNumber,test,"Validate contact information","Takeaway number visible","Takeaway number mismatch");
    		s_assert.fail(strTemplate +"-Takeaway number mismatch");
    	}	
    	
    	if(ContactNameFlag == "False")
    	{
    		fnExtentContainsString ("NA",strContactName,test,"Validate contact information","Contact Name visible","Contact name mismatch");
    		s_assert.fail(strTemplate +"-Contact name mismatch");
    	}	
    	
    	if(ContactNumberFlag == "False")
    	{
    		fnExtentContainsString ("NA",strContactNumber,test,"Validate contact information","Contact Number visible","Contact Number is not visible");
    		s_assert.fail(strTemplate +"-Contact number mismatch");
    	}	

    	}


}    



    

