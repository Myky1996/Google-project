package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class Topic_10_Alert {

	WebDriver driver;
	WebDriverWait explicitWait;
	Alert alert;
	String projectPath = System.getProperty("user.dir");
	JavascriptExecutor jsExecutor;
	
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver",projectPath + "/browserDrivers/geckodriver");
		driver = new FirefoxDriver();
//		System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver");
//		driver = new ChromeDriver();
	
		explicitWait = new WebDriverWait(driver,15);
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		 jsExecutor = (JavascriptExecutor) driver;

	}
	
   // @Test
	public void TC_01_Accept_alert() {
   driver.get("https://demo.guru99.com/v4/index.php");
   driver.findElement(By.name("btnLogin")).click();
   sleepInSecond(2);
   //Wait + switch to Alert:
   alert = explicitWait.until(ExpectedConditions.alertIsPresent());
   
   //Get text of alert
   Assert.assertEquals(alert.getText(), "User or Password is not valid");
   
   // Accept alert: alert bien mat
   alert.accept();
   
	}
	
   //@Test 
    public void TC_02_Accept_alert() {
    	driver.get("https://automationfc.github.io/basic-form/index.html");
    	
        driver.findElement(By.xpath("//button[@onclick='jsAlert()']")).click();
        
        alert = explicitWait.until(ExpectedConditions.alertIsPresent());
        
//      Verify alert message:
        Assert.assertEquals(alert.getText(),"I am a JS Alert");
        
        // Accept Alert:
        alert.accept();
        
        //Verify:
        Assert.assertEquals(driver.findElement(By.xpath("//p[@id='result']")).getText(), "You clicked an alert successfully");

    }

//    @Test 
    public void TC_03_Confirm_alert() {
    driver.get("https://automationfc.github.io/basic-form/index.html");
    
    driver.findElement(By.xpath("//button[@onclick='jsConfirm()']")).click();
    sleepInSecond(3);
    
    alert = explicitWait.until(ExpectedConditions.alertIsPresent());
    
    //verify message:
    Assert.assertEquals(alert.getText(),"I am a JS Confirm");
    
    //Dismiss Alert:
    alert.dismiss();
    
    //Verify:
    Assert.assertEquals(driver.findElement(By.xpath("//p[@id='result']")).getText(), "You clicked: Cancel");
    
    }
//    @Test 
    public void TC_04_Prompt_alert() {
    	driver.get("https://automationfc.github.io/basic-form/index.html");
    	
    	driver.findElement(By.xpath("//button[@onclick='jsPrompt()']")).click();
    	sleepInSecond(3);
    	
    	alert = explicitWait.until(ExpectedConditions.alertIsPresent());
    	
    	//verify message:
    	Assert.assertEquals(alert.getText(),"I am a JS prompt");
    	
    	String userName = "buimyky";
    	
    	//Input text:
    	alert.sendKeys(userName);
    	
    	//Accept Alert:
    	alert.accept();
    	
    	//Verify:
    	Assert.assertEquals(driver.findElement(By.xpath("//p[@id='result']")).getText(), "You entered: " + userName);
    	
    	//Dismiss Alert:
    	alert.dismiss();
    	
    	//Verify
    	Assert.assertEquals(driver.findElement(By.xpath("//p[@id='result']")).getText(), "You entered: null");
    	
    }
    
//    @Test
    public void TC_05_Authen_alert() {
    	String userName ="admin";
    	String passWord = "admin";
    	String url = "http://" + userName + ":" + passWord + "@" + "the-internet.herokuapp.com/basic_auth";
    	driver.get(url);
    	sleepInSecond(2);
    	
    	Assert.assertEquals(driver.findElement(By.xpath("//p[contains(text(),'Congratulations! You must have the proper credentials.')]")).getText(), "Congratulations! You must have the proper credentials.");
    	
    }
    @AfterClass
    public void afterClass() {
    	driver.quit();
    }
    
    public void sleepInSecond(long timeoutInSecond) {
    	try {
			Thread.sleep(timeoutInSecond * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}