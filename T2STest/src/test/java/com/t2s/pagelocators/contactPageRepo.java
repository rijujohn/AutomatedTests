package com.t2s.pagelocators;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.asserts.SoftAssert;

public class contactPageRepo {
	
	
    WebDriver driver;
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
    	linkContact.click();
    }
    
    public void verifyContactPageHeader() {
    	s_assert = new SoftAssert();
    	String contentContactUSAreaText = contentContactUSArea.getText().trim();
    	
    	s_assert.assertTrue(contentContactUSAreaText.equals(contentContactUSAreaText), "Contact Us Header not found");
	    s_assert.assertAll();
    }
    
    public void verifyAddress(String strCustomerName,String strDoorNumber,String strStreet,String strTown,String strCity,String strPostCode) {
    	s_assert = new SoftAssert();
    	WebElement contentAddressText = contentAddressArea.findElement(By.className("col-md-3"));
    	String contentAddressTextValue = contentAddressText.getText();
    	s_assert.assertTrue(contentAddressTextValue.contains(strCustomerName), "Customer Name Mismatch");
	    s_assert.assertTrue(contentAddressTextValue.contains(strDoorNumber), "Door Number Mismatch");
	    s_assert.assertTrue(contentAddressTextValue.contains(strStreet), "Street Mismatch");
	    s_assert.assertTrue(contentAddressTextValue.contains(strTown), "Town Mismatch");
	    s_assert.assertTrue(contentAddressTextValue.contains(strCity), "City Mismatch");
	    s_assert.assertTrue(contentAddressTextValue.contains(strPostCode), "PostCode Mismatch");
	    s_assert.assertAll();
    }
    
   

    
}
