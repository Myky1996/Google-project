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
public class Topic_12_1_Popup {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	WebDriverWait explicitWait;
	JavascriptExecutor jsExecutor;
	
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver",projectPath + "/browserDrivers/geckodriver");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		explicitWait = new WebDriverWait(driver,15);
		jsExecutor = (JavascriptExecutor) driver;
		driver.manage().window().maximize();

	}
	
//   @Test
	public void TC_01_Fixed_Popup() {
    	
	driver.get("https://ngoaingu24h.vn/");
	By loginPopup = By.cssSelector("div#modal-login-v1");
	
//	Verify login pop up is not displayed:
	Assert.assertFalse(driver.findElement(loginPopup).isDisplayed());
// Click dang nhap button:	
	driver.findElement(By.cssSelector("button.login_.icon-before")).click();
	sleepInSecond(3);
//	Verify pop up is Displayed:
	Assert.assertTrue(driver.findElement(loginPopup).isDisplayed());
//	Sendkeys:
	driver.findElement(By.xpath("//input[@placeholder='Tài khoản đăng nhập (*)']")).sendKeys("automationfc");
	driver.findElement(By.xpath("//input[@placeholder='Mật khẩu (*)']")).sendKeys("123456");
	driver.findElement(By.cssSelector("button.btn-v1.btn-login-v1.buttonLoading")).click();
//	Verify:
	Assert.assertEquals(driver.findElement(By.xpath("//div[text()='Tài khoản không tồn tại!']")).getText(), "Tài khoản không tồn tại!");
	
	}

    
//    @Test 
    public void TC_02_Random_popup_in_DOM() {
//  Step 1:
	driver.get("https://blog.testproject.io/");
	sleepInSecond(40);
	By mailchPopup = By.cssSelector("div.mailch-wrap");
	
//	Step 2: Neu hien thi close popup 
	if(driver.findElement(mailchPopup).isDisplayed()) {
		driver.findElement(By.cssSelector("div#close-mailch")).click();
		
		Assert.assertFalse(driver.findElement(mailchPopup).isDisplayed());
	}
//	Step 3: Sendkeys:( Neu khong co step 2 thi dang load ngam ben duoi)
	driver.findElement(By.cssSelector("section#search-2 input.search-field")).sendKeys("Selenium");
	sleepInSecond(3);

	driver.findElement(By.cssSelector("section#search-2 span.glass")).click();
	sleepInSecond(40);

//  Step 4: Verify title contains Selenium text
    List<WebElement>post_titles = driver.findElements(By.cssSelector("h3.post-title>a"));
    
    for(WebElement postTitle: post_titles) {
    	Assert.assertTrue(postTitle.getText().contains("Selenium"));
    }
    }

//   @Test 
    public void TC_03_Random_popup_Not_in_DOM() {
//    	Step 1
    	driver.get("https://shopee.vn/");
    	sleepInSecond(5);
    	By shopeePopup = By.cssSelector("div.shopee-popup__container");
    	
//    	Step 2: Neu hien thi thi close popup, else qua step 3
    	List<WebElement>shopeePopupElement = driver.findElements(shopeePopup);
    	if(shopeePopupElement.size()>0) {
    		System.out.println("----Popup hiển thị và close----");
    		driver.findElement(By.cssSelector("div.shopee-popup__close-btn")).click();
    		sleepInSecond(2);
    		Assert.assertEquals(driver.findElements(shopeePopup).size(), 0);
    		
    	} else { System.out.println("----Popup khong hien thi va pass to step 3----");
    		
    	}
      	
//    	Step 3: 
    	driver.findElement(By.cssSelector("input.shopee-searchbar-input__input")).sendKeys("Macbook Pro");
    	driver.findElement(By.cssSelector("button.btn-solid-primary")).click();
    	
    }
    
   @Test 
    public void TC_03_1_Not_in_Dom() {
    	driver.get("https://dehieu.vn/");
    	List<WebElement>All = driver.findElements(By.cssSelector("div.popup-content"));
    	if(All.size()>0) {
    		driver.findElement(By.cssSelector("button.close")).click();
    	}
        driver.findElement(By.xpath("//a[text()='Đăng ký']")).click();
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