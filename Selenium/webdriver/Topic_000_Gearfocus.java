package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
public class Topic_000_Gearfocus {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	JavascriptExecutor jsExecutor;
	String email, firstName, lastName, phone1, phone2, phone3, feedBack,cmt;
	
	@BeforeClass
	public void beforeClass() {
		//System.setProperty("webdriver.gecko.driver",projectPath + "/browserDrivers/geckodriver");
//		driver = new FirefoxDriver();
		System.setProperty("webdriver.chrome.driver", projectPath +  "/browserDrivers/chromedriver");
		driver = new ChromeDriver();
		jsExecutor = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}
	
    //@Test
	public void TC_01_Check_filter() {
    	
	driver.get("https://www.gearfocus.com/c/digital-cameras-9/digital-slr-cameras-73");
	sleepInSecond(7);

	checktoCheckbox(By.xpath("//span[contains(text(),'Nikon')]"));
	sleepInSecond(3);
	checktoCheckbox(By.xpath("//span[text()='New']"));
	sleepInSecond(3);
	
	Select_Custom_dropdown(By.xpath("//a[contains(string(),' Newest Listings')]"),
			By.xpath("//button[@role='menuitem']"),"Price Low-High");
	
	sleepInSecond(6);
	
	Select_Custom_dropdown(By.xpath("//a[contains(string(),' Show 12')]"),
			By.xpath("//button[@role='menuitem']"),"12");
	
	sleepInSecond(6);
	Assert.assertEquals(driver.findElements(By.xpath("//div[@class='products-wrapper ng-star-inserted']/div")).size(), 2);

		
	}
    
    @Test 
    public void TC_02_Feedback() {
    driver.get("https://www.gearfocus.com/website-feedback.html");
    sleepInSecond(5);
    
    
    By emailTextbox = By.xpath("//label[contains(string(),'Email')]/following-sibling::div");
    By firstTexbox = By.xpath("//input[@id='Field111']");
    By lastTexbox = By.xpath("//label[text()='Last']");
    By phoneNumberTextbox1 = By.xpath("//label[@for='Field117']");
    By phoneNumberTextbox2 = By.xpath("//label[@for='Field117-1']");
    By phoneNumberTextbox3 = By.xpath("//label[@for='Field117-2']");
    By FBTextbox = By.id("'Field2'");
    By cmtTextbox = By.id("Field108");
    By whatFb = By.xpath("//span[text()='User Experience']");
    By easyLevel = By.xpath("//span[text()='Easy']");
    By professional_1 = By.xpath("//td[@title='Below Expectations']/label[@for='Field7_1']");
    By Informative_2 = By.xpath("//td[@title='Meets Expectations']//input[@id='Field8_2']");
    By Visual_3 = By.xpath("//td[@title='Exceeds Expectations']/label[@for='Field9_3']");
    
    
    email = "ky.buimy@gmail.com";
    firstName = "Ky";
    lastName ="My";
    phone1 = "123";
    phone2 = "222";
    phone3 = "2234";
    feedBack = "scam page"; 
    cmt = "plz pay commission";
    WebElement ex = driver.findElement(By.xpath("//span[text()='User Experience']"));
    
   //javaExecutor.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(emailTextbox));
  // sleepInSecond(3);
    //driver.findElement(emailTextbox).sendKeys(email);
//   jsExecutor.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//input[@id='Field111']")));
//    driver.findElement(firstTexbox).sendKeys(firstName);
//    driver.findElement(lastTexbox).sendKeys(lastName);
//    driver.findElement(phoneNumberTextbox1).sendKeys(phone1);
//    driver.findElement(phoneNumberTextbox2).sendKeys(phone2);
//    driver.findElement(phoneNumberTextbox3).sendKeys(phone3);
//    driver.findElement(FBTextbox).sendKeys(feedBack);
//    driver.findElement(cmtTextbox).sendKeys(cmt);
    jsExecutor.executeScript("arguments[0].scrollIntoView();", ex);
    driver.findElement(whatFb).click();
    sleepInSecond(3);
    
    
    
	
    }

    @Test 
    public void TC_03_() {

    }
    @AfterClass
    public void afterClass() {
    	driver.quit();
    }
    
   
    public void Select_radio_button(By by) {
    	jsExecutor.executeScript("arguments[0].click();", driver.findElement(by));
    }
    public void Select_Custom_dropdown(By parent, By child, String expectedOutput) {
    	driver.findElement(parent).click();
    	List<WebElement> allItems = driver.findElements(child);
    	
    	for (WebElement item: allItems) {
    		if(item.getText().trim().equals(expectedOutput)) {
    		if(item.isDisplayed()) {
    			item.click();
    		} else  {jsExecutor.executeScript("arguments[0].scrollIntoView(true);", item);
    		         item.click();
    		}
    		break;
    			
    		}
    	}
    	
    }
    public void checktoCheckbox(By by) {
    	if(!driver.findElement(by).isSelected()) {
    	jsExecutor.executeScript("arguments[0].click();", driver.findElement(by));
      }
    }
    
    public void uncheckToCheckBox(By by) {
    	if(driver.findElement(by).isSelected()){
    	jsExecutor.executeScript("arguments[0].click();", driver.findElement(by));
    	}
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