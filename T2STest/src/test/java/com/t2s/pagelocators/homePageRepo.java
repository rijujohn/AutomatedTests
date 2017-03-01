package com.t2s.pagelocators;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class homePageRepo {

    @FindBy(linkText="Order now")
    private WebElement linkOrderNow;
    
   
    
    
    
    public void clickOrderNow() {
    	linkOrderNow.click();
    }
    
    
    
}
