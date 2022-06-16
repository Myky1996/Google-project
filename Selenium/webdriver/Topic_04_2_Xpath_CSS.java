package webdriver;


import static org.testng.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_04_2_Xpath_CSS {
	
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", "./browserDrivers/geckodriver");
		driver = new FirefoxDriver();		
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.get("http://live.demoguru99.com/index.php/customer/account/login/");
	}

	
	@Test
    public void TC_01_Login_with_empty_email_and_password() {
		
	// Nhập dữ liệu vào 1 text box
		driver.findElement(By.id("email")).sendKeys("");
	    driver.findElement(By.name("login[password]")).sendKeys("");
	
	// Click login button;
	driver.findElement(By.xpath("//button[@title='Login']")).click();
		
	// Get error message
	assertEquals(driver.findElement(By.id("advice-required-entry-email")).getText(), "This is a required field.");
	assertEquals(driver.findElement(By.id("advice-required-entry-pass")).getText(), "This is a required field.");
	}
	
   @Test
   public void TC_02_Login_Invalid_Email() {
	   //Refresh current page
	   driver.navigate().refresh();
	   
	   //Nhap du lieu vao 1 text box
	   driver.findElement(By.id("email")).sendKeys("456@456.256");
	   driver.findElement(By.name("login[password]")).sendKeys("y454845948");
	    
	    //Click vao button
	   driver.findElement(By.xpath("//button[@title='Login']")).click();
	   
	   //Get error message text of email:	
	   assertEquals(driver.findElement(By.id("advice-validate-email-email")).getText(), "Please enter a valid email address. For example johndoe@domain.com.");
   }
   
   @Test
   public void TC_03_Login_with_password_less_than_6_characters() {
	   //Refresh current page
	   driver.navigate().refresh();
	   
	   //Nhap du lieu vao textbox
	   driver.findElement(By.id("email")).sendKeys("456@456.256");
	   driver.findElement(By.name("login[password]")).sendKeys("123@");
	   
	   //Click on button:
	   driver.findElement(By.xpath("//button[@title='Login']")).click();
	   
	   //Get error message of password:
	   assertEquals(driver.findElement(By.id("advice-validate-password-pass")).getText(), "Please enter 6 or more characters without leading or trailing spaces.");
   }
   
   @Test
   public void TC_04_Login_with_incorrect_Email_and_password() {
	   
 //Refresh current page
   driver.navigate().refresh();
   
   //Nhap du lieu vao textbox
   driver.findElement(By.id("email")).sendKeys("automation32233@gmail.com");
   driver.findElement(By.name("login[password]")).sendKeys("123123123");
   
   //Click on button:
   driver.findElement(By.xpath("//button[@title='Login']")).click();
   
   //Get error message of password:
   assertEquals(driver.findElement(By.xpath("//li[@class='error-msg']//span")).getText(), "Invalid login or password.");
   
   }
   
   
   
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
