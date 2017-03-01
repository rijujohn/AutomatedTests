package com.t2s.pagelocators;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Reporter;
import org.testng.asserts.SoftAssert;

import com.t2s.testbase.TestBase;

public class contactPageRepo extends TestBase {
	
	
   // WebDriver driver;
    SoftAssert s_assert;
    
    @FindBy(xpath="//div[@id='map']/following-sibling::div[@class='row']")
    private WebElement contentAddressArea;
  
    
    @FindBy(xpath="//*[@id='contactus-pag-sec']/div/div[1]/h2")
    private WebElement contentContactUSArea;
    
    @FindBy(linkText="Contact")
    private WebElement linkContact;
    
   // @FindBy(xpath="//a[@href='/contactus.php#nav']")
   // private WebElement linkContact;
  //button[contains(text(),'Calculate')]
 
    
    public void clickContactLink() {
    	try
    	{
    	linkContact.click();
    	Reporter.log("Clicked on Contact link");
    	}
    	catch(Exception e)
    	{
    	Reporter.log("Error while clicking Contact link"+e.getMessage());	
    	}
    }
    
    public void verifyContactPageHeader() {
    	s_assert = new SoftAssert();
    	String contentContactUSAreaText = contentContactUSArea.getText().trim();
		fnExtentContainsString (contentContactUSAreaText,"Contact us",test,"Validate Contact us header","Header text is visible","Header text is not visible");

    //	s_assert.assertTrue(contentContactUSAreaText.equals(contentContactUSAreaText), "Contact Us Header not found");
	//    s_assert.assertAll();
    }
    
    public void verifyAddress(String strCustomerName,String strDoorNumber,String strStreet,String strTown,String strCity,String strPostCode) {
    	s_assert = new SoftAssert();
    	WebElement contentAddressText = contentAddressArea.findElement(By.className("col-md-3"));
    	String contentAddressTextValue = contentAddressText.getText();
    	fnExtentContainsString (contentAddressTextValue,strCustomerName,test,"Validate Contact page - Customer Name","Name matching","Name mismatch");
    	fnExtentContainsString (contentAddressTextValue,strDoorNumber,test,"Validate Contact page - DoorNumber","DoorNumber matching","DoorNumber mismatch");
    	fnExtentContainsString (contentAddressTextValue,strStreet,test,"Validate Contact page - Street","Street matching","Street mismatch");
    	fnExtentContainsString (contentAddressTextValue,strTown,test,"Validate Contact page - Town","Town matching","Town mismatch");
    	fnExtentContainsString (contentAddressTextValue,strCity,test,"Validate Contact page - City","City matching","City mismatch");
    	fnExtentContainsString (contentAddressTextValue,strPostCode,test,"Validate Contact page - PostCode","PostCode matching","PostCode mismatch");
    	
    	//s_assert.assertTrue(contentAddressTextValue.contains(strCustomerName), "Customer Name Mismatch");
	 //   s_assert.assertTrue(contentAddressTextValue.contains(strDoorNumber), "Door Number Mismatch");
	 //   s_assert.assertTrue(contentAddressTextValue.contains(strStreet), "Street Mismatch");
	 //   s_assert.assertTrue(contentAddressTextValue.contains(strTown), "Town Mismatch");
	 //   s_assert.assertTrue(contentAddressTextValue.contains(strCity), "City Mismatch");
	 //   s_assert.assertTrue(contentAddressTextValue.contains(strPostCode), "PostCode Mismatch");
	  //  s_assert.assertAll();
    }
    
   

    
}
