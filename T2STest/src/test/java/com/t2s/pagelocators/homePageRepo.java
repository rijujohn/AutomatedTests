package com.t2s.pagelocators;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import com.t2s.testutil.CustomFluentWait;

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
