package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.exec.OS;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
public class Topic_11_User_interaction_1 {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	Actions action;
	String osName = System.getProperty("os.name");
	WebDriverWait explicitWait;
	Keys key;
	JavascriptExecutor jsExecutor;
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver",projectPath + "/browserDrivers/geckodriver");
		driver = new FirefoxDriver();
		action = new Actions(driver);
		explicitWait = new WebDriverWait(driver,10);
		jsExecutor = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		if(osName.contains("Windows")) {
    		key = Keys.CONTROL;
    	} else {
    		key = Keys.COMMAND;
    	}

	}
	
    //@Test
	public void TC_01_Textbox() {
    	
	driver.get("https://automationfc.github.io/jquery-tooltip/");
	
	action. moveToElement(driver.findElement(By.id("age"))).perform();
	sleepInSecond(5);
	Assert.assertEquals(driver.findElement(By.xpath("//div[@class='ui-tooltip-content']")).getText(), "We ask for your age only for statistical purposes.");
	
	}
    
	    
   // @Test 
    public void TC_01_Hover_Menu() {
    	driver.get("http://www.myntra.com/");
    	sleepInSecond(4);
    	action.moveToElement(driver.findElement(By.xpath("//nav[@class='desktop-navbar']//a[text()='Kids']"))).perform();
    	sleepInSecond(5);
    	
    	//Cach 1:
//    	driver.findElement(By.xpath("//nav[@class='desktop-navbar']//a[text()='Home & Bath']")).click();
    	
    	//Cach 2:
    	action.click(driver.findElement(By.xpath("//nav[@class='desktop-navbar']//a[text()='Home & Bath']"))).perform();
    	
    	Assert.assertTrue(driver.findElement(By.xpath("//span[@class='breadcrumbs-crumb' and text()='Kids Home Bath']")).isDisplayed());
	
    }

    //@Test 
    public void TC_01_Hover_Menu_2() {
    	driver.get("https://www.fahasa.com/");
    	sleepInSecond(4);

    	action.moveToElement(driver.findElement(By.xpath("//div[@class='navbar']//span[text()='Sách Trong Nước']"))).perform();
    	
    	sleepInSecond(3);
    }
    
    	
    //@Test
    public void TC_02_CLick_and_Hold() {
    	driver.get("https://automationfc.github.io/jquery-selectable/");
    	List<WebElement>listNumber = driver.findElements(By.xpath("//ol[@id='selectable']/li"));
    	
    	action.clickAndHold(listNumber.get(0)) // Click vao so 1 va giu chuot
    	.moveToElement(listNumber.get(3))      // Di chuot den so 4
    	.release()                             // Tha chuot  
    	.perform();
    	
    	Assert.assertEquals(driver.findElements(By.cssSelector("ol#selectable>li[class$='ui-selected']")).size(), 4);
    }
    //@Test
    public void TC_03_Click_And_Hold_Random() {
    	driver.get("https://automationfc.github.io/jquery-selectable/");
    	
    	List<WebElement>listNumber = driver.findElements(By.xpath("//ol[@id='selectable']/li"));   
    	
    	
    	
    	//Nhan phim Ctrl:
    	action.keyDown(key).perform();
    	//Chon random cac so:
    	action.click(listNumber.get(0)).click(listNumber.get(4)).
    	click(listNumber.get(3)).click(listNumber.get(9)).perform();
    	
    	//Nha phim ctrl
    	action.keyUp(key).perform();
    	
    	Assert.assertEquals(driver.findElements(By.cssSelector("ol#selectable>li[class$='ui-selected']")).size(), 4);
    }
//    @Test
    public void TC_04_Double_Click() {
    	driver.get("https://automationfc.github.io/basic-form/index.html");
    	
    	//Scroll to view:
    	
    	jsExecutor.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//button[@ondblclick='doubleClickMe()']")));
    	sleepInSecond(3);
    	action.doubleClick(driver.findElement(By.xpath("//button[@ondblclick='doubleClickMe()']"))).perform();
    	sleepInSecond(3);
    	
    	Assert.assertEquals(driver.findElement(By.xpath("//p[@id='demo']")).getText(), "Hello Automation Guys!");
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