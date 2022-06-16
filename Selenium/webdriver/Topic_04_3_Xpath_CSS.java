package webdriver;

import static org.testng.Assert.assertTrue;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
@Test
public class Topic_04_3_Xpath_CSS {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String firstName, lastName, fullname, email, password;
	
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver",projectPath + "/browserDrivers/geckodriver");
		driver = new FirefoxDriver();
		
		firstName = "Gear_05";
		lastName = "Foccssee";
		fullname = firstName + " " + lastName;
		email = "GF" + getRandomNumber() + "@gmail.com";
		password ="B1234567*";
		
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}
	
    public void TC_01_Create_a_new_account() {
    	
	driver.get("http://live.demoguru99.com/");
	driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
	driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
	
	driver.findElement(By.id("firstname")).sendKeys(firstName);
	driver.findElement(By.id("lastname")).sendKeys(lastName);
	driver.findElement(By.id("email_address")).sendKeys(email);
	driver.findElement(By.id("password")).sendKeys(password);	
	driver.findElement(By.id("confirmation")).sendKeys(password);	
	
	driver.findElement(By.xpath("//button[@title='Register']")).click();
	
	Assert.assertTrue(driver.findElement(By.xpath("//li[@class='success-msg']//span[text()='Thank you for registering with Main Website Store.']")).isDisplayed());
	
	
//	Cach 1
	String contactInformation = driver.findElement(By.xpath("//h3[text()='Contact Information']//parent::div//following-sibling::div[@class='box-content']/p")).getText();
	
	Assert.assertTrue(contactInformation.contains(fullname));
	Assert.assertTrue(contactInformation.contains(email));
	
// Cach 2
    Assert.assertTrue(driver.findElement(By.xpath("//h3[text()='Contact Information']//"
    		+ "parent::div//following-sibling::div[@class='box-content']/p[contains(string(),'"+fullname+"')]")).isDisplayed());
    
    Assert.assertTrue(driver.findElement(By.xpath("//h3[text()='Contact Information']//"
    		+ "parent::div//following-sibling::div[@class='box-content']/p[contains(string(),'"+email+"')]")).isDisplayed());
    
   driver.findElement(By.xpath("//a//span[text()='Account']")).click();
   driver.findElement(By.xpath("//a[text()='Log Out']")).click();
  
    }
    
  

	public void TC_02_Login_in_with_in_valid_Email_Password() {
		driver.get("http://live.demoguru99.com/");
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		driver.findElement(By.id("email")).sendKeys(email);
		driver.findElement(By.id("pass")).sendKeys(password);
		driver.findElement(By.id("send2")).click();
		
		Assert.assertTrue(driver.findElement(By.xpath("//h1[text()= 'My Dashboard']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//strong[text()= 'Hello, Gear_05 Foccssee!']")).isDisplayed());
		
//	Cach 1:
       String contactInformationLogin = driver.findElement(By.xpath("//h3[text()='Contact Information']//parent::div//following-sibling::div/p")).getText();
       Assert.assertTrue(contactInformationLogin.contains(fullname));
       Assert.assertTrue(contactInformationLogin.contains(email));
//  Cach 2:
		Assert.assertTrue(driver.findElement(By.xpath("//h3[text()='Contact Information']//"
				+ "parent::div//following-sibling::div/p[contains(string(),'"+fullname+"')]")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//h3[text()='Contact Information']//"
				+ "parent::div//following-sibling::div/p[contains(string(),'"+email+"')]")).isDisplayed());
	
				

		
		
		
		
    }

    public void TC_03_() {

    }
    
    @AfterClass
    public void afterClass() {
    }
    
    	public int getRandomNumber() {
    		Random rand = new Random();
    		return rand.nextInt(99999);
    	}
}