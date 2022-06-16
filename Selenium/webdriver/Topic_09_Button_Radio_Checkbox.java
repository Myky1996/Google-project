package webdriver;

import static org.testng.Assert.assertTrue;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
public class Topic_09_Button_Radio_Checkbox {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	JavascriptExecutor jsExecutor;
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver",projectPath + "/browserDrivers/geckodriver");
		driver = new FirefoxDriver();
		jsExecutor = (JavascriptExecutor) driver;
	
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}
	
    //@Test
	public void TC_01_Button_Java() {
    	
	driver.get("https://www.fahasa.com/customer/account/create");
	By loginButton = By.xpath("//button[@class='fhs-btn-login']");
	driver.findElement(By.xpath("//li[@class='popup-login-tab-item popup-login-tab-login']")).click();
	//Verify login button is disabled
	Assert.assertFalse(driver.findElement(loginButton).isEnabled());
	
	driver.findElement(By.xpath("//input[@id='login_username']")).sendKeys("dam@gmail.com");
	driver.findElement(By.xpath("//input[@id='login_password']")).sendKeys("123456");
	sleepInSecond(1);
	
	//Verify background login button:
		String rgbaColor = driver.findElement(loginButton).getCssValue("background-color");
		System.out.println("RGBA = " + rgbaColor);
		
		String hexaColor = Color.fromString(rgbaColor).asHex().toUpperCase();
		System.out.println("Hexa = " + hexaColor);
		
		Assert.assertEquals(hexaColor, "#C92127");
		
	//Verify button is enabled
	Assert.assertTrue(driver.findElement(loginButton).isEnabled());
	
	driver.navigate().refresh();
	
	driver.findElement(By.xpath("//li[@class='popup-login-tab-item popup-login-tab-login']")).click();
	
//	Remove disabled attribute of login button:
	jsExecutor.executeScript("arguments[0].removeAttribute('disabled');", driver.findElement(loginButton));
	sleepInSecond(2);
	//Verify background button color = red
	driver.findElement(loginButton).click();
	
	Assert.assertEquals(driver.findElement(By.xpath("//div[@class='popup-login-content']//label[text()='Số điện thoại/Email']/following-sibling::"
			+ "div[@class='fhs-input-alert']")).getText(), "Thông tin này không thể để trống");
	Assert.assertEquals(driver.findElement(By.xpath("//div[@class='popup-login-content']//label[text()='Mật khẩu']/following-sibling::"
			+ "div[@class='fhs-input-alert']")).getText(), "Thông tin này không thể để trống");

		
	}
    
    @Test 
    public void TC_02_1_Radio_Default() {
    	driver.get("http://demos.telerik.com/kendo-ui/styling/radios");
    	By petrolTwo = By.xpath("//label[text()='2.0 Petrol, 147kW']/preceding-sibling::input");
    	//verify radio is deselected
    	Assert.assertFalse(driver.findElement(petrolTwo).isSelected());
	    driver.findElement(petrolTwo).click();
	    sleepInSecond(2);
	    //Verify is Selected:
	    Assert.assertTrue(driver.findElement(petrolTwo).isSelected());
	    
	    driver.findElement(petrolTwo).click();
	    sleepInSecond(2);
	    
	    //Verify is Selected:
	    Assert.assertTrue(driver.findElement(petrolTwo).isSelected());
	    
	    By dieselTwo = By.xpath("//label[text()='2.0 Diesel, 103kW']/preceding-sibling::input");
	    driver.findElement(dieselTwo).click();
	    Assert.assertTrue(driver.findElement(dieselTwo).isSelected());
	    Assert.assertFalse(driver.findElement(petrolTwo).isSelected());
	    
	    By petrolThree = By.xpath("//label[text()='3.6 Petrol, 191kW']/preceding-sibling::input");
	    Assert.assertFalse(driver.findElement(petrolThree).isEnabled());
	     
    }

    //@Test 
    public void TC_02_2_Checkbox_Default() {
        driver.get("http://demos.telerik.com/kendo-ui/styling/checkboxes");
      
        By rearSide = By.xpath("//label[text()='Rear side airbags']/preceding-sibling::input");
        checkToCheckBox(rearSide);
        sleepInSecond(2);
        
        //Verify
        Assert.assertTrue(driver.findElement(rearSide).isSelected());
        
        By luggage = By.xpath("//label[text()='Luggage compartment cover']/preceding-sibling::input");
       // Verify luggage checkbox is deselected
        Assert.assertFalse(driver.findElement(luggage).isSelected());
        
        checkToCheckBox(luggage);
        
        sleepInSecond(2);
        
        Assert.assertTrue(driver.findElement(luggage).isSelected());
        
        unCheckToCheckBox(luggage);
        
        sleepInSecond(2);
        
        Assert.assertFalse(driver.findElement(luggage).isSelected());
    
    }
    
    
    //@Test
    public void TC_03_Custom_checkbox() {
    	driver.get("https://material.angular.io/components/radio/examples");
//    	By summerButtonSpan = By.xpath("//input[@value='Summer']/preceding-sibling::span[contains(@class,'outer')]");
//    	By summerButtonInput = By.xpath("//input[@value='Summer']");
    	
//        //1: Span để click, Input để verify
//    	driver.findElement(summerButtonSpan).click();
//    	sleepInSecond(2);
//    	Assert.assertTrue(driver.findElement(summerButtonInput).isSelected());
    	
    	// 2: Dùng thẻ input (Click= JS vs Verify)
    	By summerButtonInput = By.xpath("//input[@value='Summer']");
    	jsExecutor.executeScript("arguments[0].click();",driver.findElement(summerButtonInput));
    	sleepInSecond(2);
    	Assert.assertTrue(driver.findElement(summerButtonInput).isSelected());   
   }
//   @Test
    public void TC_03_2_Custom_checkbox() {
    	driver.get("https://material.angular.io/components/checkbox/examples");
    	By checkedCheckBox = By.xpath("//span[contains(string(),'Checked')]//preceding-sibling::span//input");
    	By intermediateCheckbox = By.xpath("//span[text()='Indeterminate']/preceding-sibling::span/input");
    	checkToCheckboxByJS(checkedCheckBox);
    	checkToCheckboxByJS(intermediateCheckbox);
    	sleepInSecond(2);
    	//Verify checkbox da duoc chon:
    	Assert.assertTrue(driver.findElement(checkedCheckBox).isSelected());
    	Assert.assertTrue(driver.findElement(intermediateCheckbox).isSelected());
    	sleepInSecond(2);
    	//Deselect:
    	uncheckToCheckboxByJS(checkedCheckBox);
    	uncheckToCheckboxByJS(intermediateCheckbox);
    	
    	//Verify deselected:
    	Assert.assertFalse(driver.findElement(checkedCheckBox).isSelected());
    	Assert.assertFalse(driver.findElement(intermediateCheckbox).isSelected());
    	
    }
    
//    @Test
    public void TC_04_Custom_Radio_button() {
    	driver.get("https://docs.google.com/forms/d/e/1FAIpQLSfiypnd69zhuDkjKgqvpID9kwO29UCzeCVrGGtbNPZXQok0jA/viewform");
    	By canThoRadioButton = By.xpath("//div[@data-value='Cần Thơ']");
    	Assert.assertTrue(driver.findElement(By.xpath("//div[@data-value='Cần Thơ' and @aria-checked='false']")).isDisplayed());
    	Assert.assertEquals(driver.findElement(canThoRadioButton).getAttribute("aria-checked"), "false");
    	
    	checkToCheckboxByJS(canThoRadioButton);
    	sleepInSecond(2);
    	Assert.assertTrue(driver.findElement(By.xpath("//div[@data-value='Cần Thơ' and @aria-checked='true']")).isDisplayed());
    	Assert.assertEquals(driver.findElement(canThoRadioButton).getAttribute("aria-checked"), "true");
    
    		 
    		 
    	//Click all
    	List<WebElement> checkboxes = driver.findElements(By.xpath("//div[@role='checkbox']"));
    	for  (WebElement checkbox: checkboxes) {
    		checkbox.click();
    		sleepInSecond(2);
    	}
    	//Verify all
    	for (WebElement checkbox: checkboxes) {
    		Assert.assertEquals(checkbox.getAttribute("aria-checked"), "true");
    	}
    }
    
//    @Test
    public void TC_05_live_guru() {
    	driver.get("http://live.demoguru99.com/index.php/backendlogin/index/index/key/eb4704ab59a838dbf890298c18591d50/");
    	driver.findElement(By.id("username")).sendKeys("user01");
    	driver.findElement(By.id("login")).sendKeys("guru99com");
    	driver.findElement(By.xpath("//input[@title='Login']")).click();
    	
    	sleepInSecond(5);
    	checktoCheckboxByCustomerEmail(	"Automation38202@gmail.uk");
    	checktoCheckboxByCustomerEmail(	"chinhnguyen1239@gmail.us");
    	checktoCheckboxByCustomerEmail(	"naiklakshmi123@gmail.com");
    	checktoCheckboxByCustomerEmail(	"iii.smith4334@gmail.com");
    
    }
    
    
  public void checktoCheckboxByCustomerEmail(String customerEmail) {
	  WebElement cutomerNameCheckbox = driver.findElement(By.xpath("//td[contains(text(),'" + customerEmail + "')]//preceding-sibling::td/input"));
	  if(!cutomerNameCheckbox.isSelected()) {
		  cutomerNameCheckbox.click();
	  }
  }
    
    public void checkToCheckboxByJS (By by) {
    	if(!driver.findElement(by).isSelected()) {
    		jsExecutor.executeScript("arguments[0].click();", driver.findElement(by));
    	}
    }
    public void uncheckToCheckboxByJS (By by) {
    	if(driver.findElement(by).isSelected()) {
    		jsExecutor.executeScript("arguments[0].click()", driver.findElement(by));    	}
    }
    public void clickToElementByJS(By by) {
    	jsExecutor.executeScript("arguments[0].click();", driver.findElement(by));
    }
    
    public void checkToCheckBox(By by) {
    	if(!driver.findElement(by).isSelected()) {
    		driver.findElement(by).click();
    	}
    }
    public void unCheckToCheckBox(By by) {
    	if(driver.findElement(by).isSelected()) {
    		driver.findElement(by).click();
    		}
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