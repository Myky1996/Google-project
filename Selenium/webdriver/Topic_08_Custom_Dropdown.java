package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
public class Topic_08_Custom_Dropdown {
	WebDriver driver;
	WebDriverWait explicitWait;
	JavascriptExecutor jsExecutor;
	String projectPath = System.getProperty("user.dir");
	
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver",projectPath + "/browserDrivers/geckodriver");
		driver = new FirefoxDriver();
		
//		System.setProperty("webdriver.chrome.driver", projectPath +  "/browserDrivers/chromedriver");
//		driver = new ChromeDriver();
		
		//Driver ID
		
		//Wait de appl trang thai cua element(visible/invisible/presence/clickable)
		explicitWait = new WebDriverWait(driver, 15);
		
		jsExecutor = (JavascriptExecutor) driver;
		
		
		//Wait de tim element (findElement, findElements):
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		driver.manage().window().maximize();

	}
	
//    @Test
	public void TC_01_JQuery() {
    	
	driver.get("http://jqueryui.com/resources/demos/selectmenu/default.html");
		
	By parent = By.id("number-button");
	By child = By.cssSelector("ul#number-menu div");
	
	selecItemInDropdown(parent, child, "5");
	sleepInSecond(3);
	Assert.assertTrue(isElementDisplayed(By.xpath("//span[@id='number-button']//span[@class='ui-selectmenu-text' and text()='5']")));
	
	selecItemInDropdown(parent, child, "19");
	sleepInSecond(3);
	Assert.assertTrue(isElementDisplayed(By.xpath("//span[@id='number-button']//span[@class='ui-selectmenu-text' and text()='19']")));
	selecItemInDropdown(parent, child, "10");
	sleepInSecond(3);
	Assert.assertTrue(isElementDisplayed(By.xpath("//span[@id='number-button']//span[@class='ui-selectmenu-text' and text()='10']")));
	selecItemInDropdown(parent, child, "15");
	sleepInSecond(3);
	Assert.assertTrue(isElementDisplayed(By.xpath("//span[@id='number-button']//span[@class='ui-selectmenu-text' and text()='15']")));
	
	}
    
//    @Test 
    public void TC_02_ReactJS() {
	driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");
	
	By parent = By.cssSelector("i.dropdown.icon");
    By child = By.cssSelector("div[role='option']>span");
	
    selecItemInDropdown(parent, child, "Jenny Hess");
    sleepInSecond(3);
    Assert.assertTrue(isElementDisplayed(By.xpath("//div[@role='alert' and text()='Jenny Hess']")));
    
    selecItemInDropdown(parent, child, "Stevie Feliciano");
    sleepInSecond(3);
    Assert.assertTrue(isElementDisplayed(By.xpath("//div[@role='alert' and text()='Stevie Feliciano']")));
    
    selecItemInDropdown(parent, child, "Justen Kitsune");
    sleepInSecond(3);
    Assert.assertTrue(isElementDisplayed(By.xpath("//div[@role='alert' and text()='Justen Kitsune']")));
    
    
    }

//    @Test 
    public void TC_03_VueJS() {
    	driver.get("https://mikerodham.github.io/vue-dropdowns/");
    	By parent = By.cssSelector("li.dropdown-toggle");
    	By child = By.cssSelector("ul.dropdown-menu a");
    	selecItemInDropdown(parent, child, "First Option");
        sleepInSecond(3);
        Assert.assertTrue(isElementDisplayed(By.xpath("//li[@class='dropdown-toggle' and contains(text(),'First Option')]")));
    	
        selecItemInDropdown(parent, child, "Second Option");
        sleepInSecond(3);
        Assert.assertTrue(isElementDisplayed(By.xpath("//li[@class='dropdown-toggle' and contains(text(),'Second Option')]")));
        
        selecItemInDropdown(parent, child, "Third Option");
        sleepInSecond(3);
        Assert.assertTrue(isElementDisplayed(By.xpath("//li[@class='dropdown-toggle' and contains(text(),'Third Option')]")));

    }
    
    @Test
    public void TC_04_Angular() {
    	driver.get("https://ej2.syncfusion.com/angular/demos/?_ga=2.262049992.437420821.1575083417-524628264.1575083417#/material/drop-down-list/data-binding");
    	
    	
    	selecItemInDropdown(By.cssSelector("span[aria-owns='games_options']"), By.cssSelector(" ul#games_options>li"), "Basketball");
    	sleepInSecond(3);
    	
    	selecItemInDropdown(By.cssSelector("span[aria-owns='games_options']"), By.cssSelector(" ul#games_options>li"), "Cricket");
    	sleepInSecond(3);
    	 		
    	
    }
    
    @Test
    public void TC_05_Editable() {
    	driver.get("http://indrimuska.github.io/jquery-editable-select/");
    	
    	selecItemInEditableDropdown(By.xpath("//div[@id='default-place']/input"),By.xpath("//ul[@class='es-list' and @style]/li"), "Nissan");   
    	sleepInSecond(3);
    	driver.navigate().refresh();
    	
    	selecItemInEditableDropdown(By.xpath("//div[@id='default-place']/input"),By.xpath("//ul[@class='es-list' and @style]/li"), "Audi");   
    	sleepInSecond(3);
    	driver.navigate().refresh();
    	
    	selecItemInEditableDropdown(By.xpath("//div[@id='default-place']/input"),By.xpath("//ul[@class='es-list' and @style]/li"), "Land Rover");   
    	sleepInSecond(3);
    	driver.navigate().refresh();
    	
    	
    }
    
    @Test
    public void TC_06_Editable() {
    	
    
    }
    
    @AfterClass
    public void afterClass() {
    	//driver.quit();
    }
    
    public void sleepInSecond(long timeoutInSecond) {
    	try {
			Thread.sleep(timeoutInSecond * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    public boolean isElementDisplayed(By by) {
    	
        WebElement element = driver.findElement(by);
        if(element.isDisplayed()) {
           System.out.println("Element [" + by + "] is displayed");
           return true;
        	
        } else {
           System.out.println("Element [" + by + "] is not displayed");
           return false;
        }
        
        }
    
    public void selecItemInEditableDropdown(By parentBy, By childBy, String expectedTextItem) {
    	driver.findElement(parentBy).clear();
    	
    	driver.findElement(parentBy).sendKeys(expectedTextItem);
    		
    	sleepInSecond(1);
    		 //Store lai tat ca cac element (item cua dropdown)
    		
    		List<WebElement> allItems = driver.findElements(childBy);
    		System.out.println("All items = " + allItems.size());
    		
    		for (WebElement item : allItems) {
    			if(item.getText().trim().equals(expectedTextItem)) {
    				if(item.isDisplayed()) { //3: Neu item can chon nam trong view
    					item.click();
    				} else { //4: Neu item can chon khong nhin thay thi scroll xuong va click vao
    					jsExecutor.executeScript("argurments[0].scrollIntoView(true);", item);
    					item.click();
    				}
    				break;
    			}
    		}
    }
    
    public void selecItemInDropdown(By parentBy, By childBy, String expectedTextItem) {
	//1: click vao 1 item cho xo het all items
		explicitWait.until(ExpectedConditions.elementToBeClickable(parentBy)).click();
		
		//2: wait cho tat ca element duoc load ra (co trong HTML/DOM)
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(childBy));
		
		 //Store lai tat ca cac element (item cua dropdown)
		
		List<WebElement> allItems = driver.findElements(childBy);
		System.out.println("All items = " + allItems.size());
		
		for (WebElement item : allItems) {
			if(item.getText().trim().equals(expectedTextItem)) {
				if(item.isDisplayed()) { //3: Neu item can chon nam trong view
					item.click();
				} else { //4: Neu item can chon khong nhin thay thi scroll xuong va click vao
					jsExecutor.executeScript("argurments[0].scrollIntoView(true);", item);
					item.click();		
			}
				break;
		  } 
		}

    }	
}






