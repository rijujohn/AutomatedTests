package com.uktech.page.frontEndDynamic;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class homePageRepo {
	
	//CustomFluentWait customFluentWait = new CustomFluentWait(driver);
    @FindBy(linkText="Order now")
    private WebElement linkOrderNow;
    
    @FindBy(className = "modal-title1")
    public WebElement orderTypeSelection;
    
    
    
    public void clickOrderNow() {
    	linkOrderNow.click();
    }
    
    
    
}
