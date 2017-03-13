package com.t2s.pagelocators;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
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
  
    
 //   @FindBy(xpath="//*[@id='contactus-pag-sec']/div/div[1]/h2")
 //   private WebElement contentContactUSArea;
    
    @FindBy(xpath="//*[@id='contactus-pag-sec']")
    private WebElement contentContactUSPageSection;
    
    @FindBy(linkText="Contact")
    private WebElement linkContact;
    ////button[contains(text(),'Calculate')]
    @FindBy(xpath = "//*[contains(@class,'service-times')]")
    private WebElement openHoursList;
    ////input[starts-with(@id,'reportcombo')
    @FindBy(xpath = ".//*[@id = 'bodyContent']/p/a")
    private WebElement mapsAddress;
    
    //WebElement contactText = contentContactUSPageSection.findElement(By.className("col-md-6"));
    
   // @FindBy(xpath="//a[@href='/contactus.php#nav']")
   // private WebElement linkContact;
  //button[contains(text(),'Calculate')]
 
 /////////////////////////////////////   
    
    private static final String SCRIPT = "\tvar gLat = $(\"#gLat\").text();\n"
            + "\tvar gLng = $(\"#gLng\").text();\n"
            + "\tvar gSitename = $(\"#gSitename\").text();\n"
            + "\tvar gNumber = $(\"#gNumber\").text();\n"
            + "\tvar gStreet = $(\"#gStreet\").text();\n"
            + "\tvar gTown = $(\"#gTown\").text();\n"
            + "\tvar gCity = $(\"#gCity\").text();\n"
            + "\tvar gPostcode = $(\"#gPostcode\").text();\n"
            + "\tvar gPhone = $(\"#gPhone\").text();\n"
            + "    var settings = {\n"
            + "        zoom: 15,\n"
            + "        center: latlng,\n"
            + "        mapTypeControl: true,\n"
            + "        mapTypeControlOptions: {style: google.maps.MapTypeControlStyle.DROPDOWN_MENU},\n"
            + "        navigationControl: true,\n"
            + "        navigationControlOptions: {style: google.maps.NavigationControlStyle.SMALL},\n"
            + "        mapTypeId: google.maps.MapTypeId.ROADMAP};\n"
            + "\tvar map = new google.maps.Map(document.getElementById(\"map\"), settings);\n"
            + "\t\tvar contentString = '<div id=\"content\" style=\"width:150%; height:100%\">'+\n"
            + "\t\t'<h1 id=\"firstHeading\" class=\"firstHeading\">Artyom</h1>'+\n"
            + "\t\t'<div id=\"bodyContent\">'+\n"
            + "\t\t'<p><a href=\"http://maps.google.co.uk/maps?f=d&source=s_d&saddr=&daddr=ST6%203EX\">"
            + "32 Waterloo Road.<br>burslem<br>Stoke On Trent, Staffordshire<br>ST6 3EX</a></p>'+\n"
            + "\t\t'</div>'+\n"
            + "\t\t'</div>';\n"
            + "\t\tvar infowindow = new google.maps.InfoWindow({\n"
            + "\t\t\tcontent: contentString\n"
            + "\t\t});\n\n"
            + "\tvar latlng = new google.maps.LatLng(gLat, gLng);\n"
            + "\tvar companyImage = new google.maps.MarkerImage('https://197c2b2de2695f3fc516-"
            + "9d1369894b0466c92e9c5ccf435da7e6.ssl.cf3.rackcdn.com/images/map_logo.png',\n"
            + "\t    new google.maps.Size(100, 50),\n"
            + "\t    new google.maps.Point(0, 0),\n"
            + "\t    new google.maps.Point(50, 50)\n"
            + "\t);\n"
            + "\n\tvar companyShadow = new google.maps.MarkerImage('https://197c2b2de2695f3fc516-"
            + "9d1369894b0466c92e9c5ccf435da7e6.ssl.cf3.rackcdn.com/images/map_logo_shadow.png',\n"
            + "\t    new google.maps.Size(130, 50),\n"
            + "\t    new google.maps.Point(0, 0),\n"
            + "\t    new google.maps.Point(65, 50)\n"
            + "\t);\n"
            + "\n"
            + "\tvar companyPos = new google.maps.LatLng(gLat, gLng);\n"
            + "\tvar companyMarker = new google.maps.Marker({\n"
            + "\t    position: companyPos,\n"
            + "\t    map: map,\n"
            + "\t    icon: companyImage,\n"
            + "\t    shadow: companyShadow,\n"
            + "\t    title: '',\n"
            + "\t    zIndex: 4\n"
            + "\t});\n"
            + "\n"
            + "    google.maps.event.addListener(companyMarker, 'click', function() {\n"
            + "        infowindow.open(map,companyMarker);\n"
            + "    });\n"
            + "\n"
            + "\tgoogle.maps.event.trigger(companyMarker, 'click');";
    
    
    
  ////////////////////////////////////  
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
    	String contentContactUSAreaText = contentContactUSPageSection.getText().trim();
		fnExtentContainsString (contentContactUSAreaText,"Contact us",test,"Validate Contact us header","Header text is visible","Header text is not visible");
    }
    
    public void verifyAddress(String strCustomerName,String strDoorNumber,String strStreet,String strTown,String strCity,String strPostCode) {
    //	s_assert = new SoftAssert();
    	WebElement contentAddressText = contentAddressArea.findElement(By.className("col-md-3"));
    	String contentAddressTextValue = contentAddressText.getText();
    	fnExtentContainsString (contentAddressTextValue,strCustomerName,test,"Validate Contact page - Customer Name","Name matching","Name mismatch");
    	fnExtentContainsString (contentAddressTextValue,strDoorNumber,test,"Validate Contact page - DoorNumber","DoorNumber matching","DoorNumber mismatch");
    	fnExtentContainsString (contentAddressTextValue,strStreet,test,"Validate Contact page - Street","Street matching","Street mismatch");
    	fnExtentContainsString (contentAddressTextValue,strTown,test,"Validate Contact page - Town","Town matching","Town mismatch");
    	fnExtentContainsString (contentAddressTextValue,strCity,test,"Validate Contact page - City","City matching","City mismatch");
    	fnExtentContainsString (contentAddressTextValue,strPostCode,test,"Validate Contact page - PostCode","PostCode matching","PostCode mismatch");
    }
    /////////////////////////////////////////////////////////
    
    public void verifyOpenHours(String[] arrMenuList) throws InterruptedException {
    	ArrayList<String> childItemText = verifyChildItems(openHoursList);
        for (String item:arrMenuList )
        {
        	fnExtentContainsString (childItemText.toString(),item,test,"Validate Open Hours","Item "+ item +"is visible","Item "+ item +"is not visible");
    
        }
    }
    
    public void verifyContactTextInfo(String strTakeAwayNumber,String strContactName,String strContactNumber) throws InterruptedException {
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
    	if (childItemSectionText.get(i).toString().contains(strTakeAwayNumber))
    	{
    		TakeAwayFlag = "True";
    		fnExtentContainsString (strTakeAwayNumber,strTakeAwayNumber,test,"Validate contact information","Takeaway number visible","Takeaway number is not visible");
    	}
    	if (childItemSectionText.get(i).toString().contains(strContactName))
    	{
    		ContactNameFlag = "True";
    		fnExtentContainsString (strContactName,strContactName,test,"Validate contact information","Contact Name visible","Contact Name is not visible");
    	}
    	if (childItemSectionText.get(i).toString().contains(strContactNumber))
    	{
    		ContactNumberFlag = "True";
    		fnExtentContainsString (strContactNumber,strContactNumber,test,"Validate contact information","Contact Number visible","Contact Number is not visible");
    	}
    		}
    	if(TakeAwayFlag == "False")
    	{
    		fnExtentContainsString ("NA",strTakeAwayNumber,test,"Validate contact information","Takeaway number visible","Takeaway number is not visible");
    	}	
    	
    	if(ContactNameFlag == "False")
    	{
    		fnExtentContainsString ("NA",strContactName,test,"Validate contact information","Contact Name visible","Contact Name is not visible");
    	}	
    	
    	if(ContactNumberFlag == "False")
    	{
    		fnExtentContainsString ("NA",strContactNumber,test,"Validate contact information","Contact Number visible","Contact Number is not visible");
    	}	

    	}


}    

/*
    public void verifyMapAddress() {
        contactPage.clickMapMarker();
        assertTrue(contactPage.getMapAddress().contains(POST_CODE));
    }
    
    
    public void clickMapMarker() {
        jsExecutor(SCRIPT);
    }

    public String getMapAddress() {
        return mapsAddress.getText();
    }*/

    

