package webdriver;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
public class Topic_06_Web_Browser_Command {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko"
				+ ".driver",projectPath + "/browserDrivers/geckodriver");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	}
	
    @Test
	public void TC_01_URL() {
    	
	driver.get("http://live.demoguru99.com/");
    driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
    String loginPageUrl = driver.getCurrentUrl();
    Assert.assertEquals(loginPageUrl, "http://live.demoguru99.com/index.php/customer/account/login/");
    driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
	String registerPageUrl = driver.getCurrentUrl();
	Assert.assertEquals(registerPageUrl, "http://live.demoguru99.com/index.php/customer/account/create/");
		
	}
    
    @Test 
    public void TC_02_Title() {
    	
    driver.get("http://live.demoguru99.com");
    driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
	String titleLoginPage= driver.getTitle();
	Assert.assertEquals(titleLoginPage, "Customer Login");
	driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
	String titleRegisterPage = driver.getTitle();
	assertEquals(titleRegisterPage, "Create New Customer Account");
	
    }

    @Test 
    public void TC_03_Navigation() {
    	driver.get("http://live.demoguru99.com");
    	driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
    	driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
    	String registerPageURL = driver.getCurrentUrl();
    	
    	assertEquals(registerPageURL, "http://live.demoguru99.com/index.php/customer/account/create/");
    	driver.navigate().back();
    	String loginPageUrl = driver.getCurrentUrl();
    	assertEquals(loginPageUrl, "http://live.demoguru99.com/index.php/customer/account/login/");
    	driver.navigate().forward();
    	String registerPageTitle = driver.getTitle();
    	assertEquals(registerPageTitle, "Create New Customer Account");
    	
    	
    	
    	

    }
    
    @Test 
    public void TC_04_Page_Source() {
    	driver.get("http://live.demoguru99.com");
    	driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
    	String registerPageSource = driver.getPageSource();
    	assertTrue(registerPageSource.contains("Login or Create an Account"));
    	driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
    	String loginPageSource = driver.getPageSource();
    	assertTrue(loginPageSource.contains("Create an Account"));
    	
    }
    @AfterClass
    public void afterClass() {
    	driver.quit();
    }
    
}