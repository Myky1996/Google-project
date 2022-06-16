package webdriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;
public class Topic_03_Run_on_browsers {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	
@Test
	public void TC_01_Firefox_Lastest() {
//		Firefox lastest: 89
		// Selenium 3.141.59
		// TestNG 6.14.3
		//Gecko driver 
		System.setProperty("webdriver.gecko.driver",projectPath + "/browserDrivers/geckodriver");
		driver = new FirefoxDriver();
		
		driver.get("https://www.facebook.com/");
		
		driver.quit();
	}
@Test 
public void TC_02_Chrome() {
	System.setProperty("webdriver.chrome.driver",projectPath + "/browserDrivers/Chromedriver");
	driver = new ChromeDriver();
	
	driver.get("https://www.facebook.com/");
	
	driver.quit();

}

@Test 
public void TC_03_Edge() {

	System.setProperty("webdriver.edge.driver",projectPath + "/browserDrivers/msedgedriver");
	driver = new EdgeDriver();
	
	driver.get("https://www.facebook.com/");
	
	driver.quit();
}

}