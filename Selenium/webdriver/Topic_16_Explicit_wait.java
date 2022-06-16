package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
public class Topic_16_Explicit_wait {
	WebDriver driver;
	WebDriverWait explicitWait;
	String projectPath = System.getProperty("user.dir");
	By startButton = By.cssSelector("div#start button");
	By loadingIcon = By.cssSelector("div#loading");
	By textHello = By.xpath("//h4[text()='Hello World!']");
	
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver",projectPath + "/browserDrivers/geckodriver");
		driver = new FirefoxDriver();
		
		explicitWait = new WebDriverWait(driver,15);
		
		driver.manage().window().maximize();
		

	}
	
//    @Test
	public void TC_01_Static_wait() {
    	
    	driver.get("https://automationfc.github.io/dynamic-loading/");

	driver.findElement(startButton).click();
	sleepInSecond(6);
	
	Assert.assertEquals(driver.findElement(textHello).getText(), "Hello World!");
		
	}
    
//    @Test 
    public void TC_02_Explict_wait_Invisible() {
    	driver.get("https://automationfc.github.io/dynamic-loading/");
    	// 1 Wait cho clickable 
    	explicitWait.until(ExpectedConditions.elementToBeClickable(startButton));
        // 2 Click
    	driver.findElement(startButton).click();
        // 3 Wait cho invisible (3s/5s case)
    	
    	explicitWait = new WebDriverWait(driver,5);
    	explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(loadingIcon));
        // 4 Verify:
    	Assert.assertFalse(driver.findElement(loadingIcon).isDisplayed());
    	Assert.assertEquals(driver.findElement(textHello).getText(), "Hello World!");
    	
    }

    @Test 
    public void TC_03_Explict_wait_Visible() {
    	driver.get("https://automationfc.github.io/dynamic-loading/");
    	// 1 Wait cho clickable 
    	explicitWait.until(ExpectedConditions.elementToBeClickable(startButton));
        // 2 Click
    	driver.findElement(startButton).click();
        // 3 Wait cho invisible (3s/5s case)
    	
    	explicitWait = new WebDriverWait(driver,5);
    	explicitWait.until(ExpectedConditions.visibilityOfElementLocated(textHello));
        // 4 Verify:
    	Assert.assertFalse(driver.findElement(loadingIcon).isDisplayed());
    	Assert.assertEquals(driver.findElement(textHello).getText(), "Hello World!");
    	

    }
    
    @Test 
    public void TC_04_Explict_wait() {
    	driver.get("https://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");
    	By textToday = By.xpath("//a[text()='1']");
    	By loadingIcon = By.cssSelector("div.raDiv");
    
    	// 2 Wait cho Date time picker duoc hien thi
    	explicitWait.until(ExpectedConditions.elementToBeClickable(textToday));
        // 2 Click
    	driver.findElement(textToday).click();
        // 3 Wait cho invisible
    	explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(loadingIcon));
        // 4 Wait cho selected date duoc select:
    	explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("td.rcSelected a")));
        // 5 Verify ngay da chon hien thi
    	Assert.assertEquals(driver.findElement(By.id("ctl00_ContentPlaceholder1_Label1")).getText(), "Friday, October 1, 2021");

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