package TestNG;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.BeforeClass;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class TestNG_06_Parameter {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	WebDriverWait explicitWait;
	JavascriptExecutor jsExecutor;
	Alert alert;
	
	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		System.out.println("Run wwith "+ browserName);
		if (browserName.equals("firefox")) {
			System.setProperty("webdriver.gecko"
					+ ".driver",projectPath + "/browserDrivers/geckodriver");
			driver = new FirefoxDriver();
		}
		else if(browserName.equals("chrome")) {
			    System.setProperty("webdriver.chrome"
					+ ".driver",projectPath + "/browserDrivers/chromedriver");
			driver = new ChromeDriver();
			
		}
		else if(browserName.equals("edge")) {
			    System.setProperty("webdriver.edge"
					+ ".driver",projectPath + "/browserDrivers/msedgedriver");
			driver = new EdgeDriver();   
		} else {
			throw new RuntimeException("Please input correct the browser email!");
		}
		 jsExecutor = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	}
	
	@Test 
    public void TC_02_() {
     driver.get("https://automationfc.com/2020/02/18/training-online-automation-testing/");
     driver.switchTo().frame(driver.findElement(By.cssSelector("iframe.youtube-player")));
     
     String videoTitle = driver.findElement(By.cssSelector("a.ytp-title-link")).getText();
     System.out.println(videoTitle);
     
     
//     Assert.assertEquals(videoTitle,"[Online 10] - Topic 01 (Intro Course/ Outline/ Target/ Rule)");
     
     driver.switchTo().defaultContent();
     
     driver.switchTo().frame(driver.findElement(By.cssSelector("div.post-content iframe[src*='docs.google.com']")));
     
     Assert.assertEquals(driver.findElement(By.cssSelector("div.exportFormTitle")).getText(), "KHÓA HỌC SELENIUM AUTOMATION TESTING");
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