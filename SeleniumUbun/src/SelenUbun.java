import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class SelenUbun {

	//Riju Updated this time one 22 March
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//This one is newly added by riju
		//System.setProperty("webdriver.gecko.driver","//home//dev//Documents//MyOwn//Ver12//geckodriver");
	//	System.setProperty("webdriver.gecko.driver","//home//dev//Documents//MyOwn//Ver12//geckodriver");
		try{
		WebDriver driver = new ChromeDriver();
		//	WebDriver driver = new FirefoxDriver();
		driver.get("http://d-166.t2scdn.com/");
		Thread.sleep(40);
		driver.findElement(By.linkText("Contacts Us")).click();
		////*[@id="contactus-pag-sec"]/div/div[2]/div[2]/div[4]
	    }
	    catch (Exception e)
	    {
	    		System.out.println("sdf and hello there message dsf"+ e.getMessage());	
	    }
	    
	 //http://askubuntu.com/questions/681312/how-to-update-firefox-on-ubuntu   
	    		//ChromeDriver();
		//driver.manage().window().maximize();
		//home//dev//Documents//MyOwn//TestDoc.ods
	}

}
