package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
public class Topic_12_2_Frame_Iframe {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
    JavascriptExecutor jsExecutor;
    WebDriverWait explicitWait;
	
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver",projectPath + "/browserDrivers/geckodriver");
		driver = new FirefoxDriver();
		explicitWait = new WebDriverWait(driver,15);
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	    jsExecutor = (JavascriptExecutor) driver;

	}
	
//    @Test
	public void TC_01_Iframe() {
    	
	driver.get("https://kyna.vn/");
	By kynaPopup = By.cssSelector("div.fancybox-inner");
	
//	Step 2:
		List<WebElement>popUpElement = driver.findElements(kynaPopup);
		
		if(popUpElement.size()>0) {
			System.out.println("----Pop up display and close----");
			driver.findElement(By.xpath("//a[@title='Close']")).click();
			sleepInSecond(3);
			
		} else { System.out.println("----Pop up display and close----");
		
		}
//Step 3: Switch to popup
	
		driver.switchTo().frame(driver.findElement(By.cssSelector("div.fanpage iframe")));
		
		String kynaFanpagelikr = driver.findElement(By.xpath("//a[text()='Kyna.vn']//parent::div//following-sibling::div")).getText();
		System.out.println(kynaFanpagelikr);
		
//		Back to homepage
		driver.switchTo().defaultContent();
		Assert.assertTrue(driver.findElement(By.cssSelector("a.login-btn")).isDisplayed());
		
    }
    
	
	
	@Test
	public void TC01_01_() {
		driver.get("https://kyna.vn/");
		WebElement iframeJava = driver.findElement(By.cssSelector("div.face-content iframe"));
		
		Assert.assertTrue(iframeJava.isDisplayed());
		
		driver.switchTo().frame(iframeJava);
		Assert.assertEquals(driver.findElement(By.xpath("//a[@title='Kyna.vn']//parent::div//following-sibling::div")).getText(), "168K lượt thích");
		
		driver.switchTo().defaultContent();
        driver.switchTo().frame(driver.findElement(By.cssSelector("iframe#cs_chat_iframe")));
				
//		Step 4: Click on chat iframe
		driver.findElement(By.cssSelector("div[class*='chatButton_Button ltr']")).click();
		driver.findElement(By.cssSelector("input[class*='input_name standalone mobile']")).sendKeys("Ky Bui");
		driver.findElement(By.cssSelector("input[class*='input_phone']")).sendKeys("0123456789");
		
		driver.findElement(By.id("serviceSelect")).click();
		
		String expectedItem = "HỖ TRỢ KỸ THUẬT";
		List<WebElement>all = driver.findElements(By.cssSelector("select#serviceSelect option"));
		for (WebElement each : all) {
			if(each.getText().equals(expectedItem)) {
				if(each.isDisplayed()) {
				each.click();
			} else {
				jsExecutor.executeScript("argurments[0].scrollIntoView(true);",each);
				each.click();	
			}
			}
		}
		driver.findElement(By.name("message")).sendKeys("123");
		driver.findElement(By.cssSelector("input[class*='submit meshim_widget']")).click();
		
		//explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='floater_inner_seriously']")));
		
	sleepInSecond(3);
//	Step 5: Verify
		Assert.assertEquals(driver.findElement(By.xpath("//label[@class='logged_in_name ng-binding' and text()='Ky Bui']")).getText(), "Ky Bui");
		Assert.assertEquals(driver.findElement(By.xpath("//label[@class='logged_in_phone ng-binding' and text()='0123456789']")).getText(), "0123456789");
		Assert.assertEquals(driver.findElement(By.name("message")).getText(), "123");
//		Step 6: Sendkey:
		driver.switchTo().defaultContent();
		driver.findElement(By.id("live-search-bar")).sendKeys("Excel");
		driver.findElement(By.cssSelector("button.search-button")).click();
		
//		Verify:
		List<WebElement>Whole = driver.findElements(By.xpath("//ul[@class='k-box-card-list']/li"));
		for(WebElement min: Whole) {
			Assert.assertTrue(min.getText().contains("Excel"));	
		}
	}
//    @Test 
    public void TC_02_() {
     driver.get("https://automationfc.com/2020/02/18/training-online-automation-testing/");
     driver.switchTo().frame(driver.findElement(By.cssSelector("iframe.youtube-player")));
     
     String videoTitle = driver.findElement(By.cssSelector("a.ytp-title-link")).getText();
     System.out.println(videoTitle);
     
     
     Assert.assertEquals(videoTitle,"[Online 10] - Topic 01 (Intro Course/ Outline/ Target/ Rule)");
     
     driver.switchTo().defaultContent();
     
     driver.switchTo().frame(driver.findElement(By.cssSelector("div.post-content iframe[src*='docs.google.com']")));
     
     Assert.assertEquals(driver.findElement(By.cssSelector("div.exportFormTitle")).getText(), "KHÓA HỌC SELENIUM AUTOMATION TESTING");
    }
    
//@Test 
    public void TC_03_Frame() {
    	driver.get("https://netbanking.hdfcbank.com/netbanking/");
//    	Step 2
    	driver.switchTo().frame(driver.findElement(By.name("login_page")));
    	driver.findElement(By.cssSelector("input[name='fldLoginUserId']")).sendKeys("automationfc");
    	sleepInSecond(2);
    	driver.findElement(By.cssSelector("a.btn.btn-primary.login-btn")).click();	
//    	Step 3
    	Assert.assertTrue(driver.findElement(By.cssSelector("input[placeholder='Password/ IPIN']")).isDisplayed());
//    	Step 4:
    	
    	driver.findElement(By.xpath("//a[text()='Terms and Conditions']")).click();
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