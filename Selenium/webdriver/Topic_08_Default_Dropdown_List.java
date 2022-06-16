package webdriver;


import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_08_Default_Dropdown_List {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	Select select;
	
	
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver",projectPath + "/browserDrivers/geckodriver");
		driver = new FirefoxDriver();
		
		
		
		
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	
    @Test
	public void TC_01_NopEcommerce() {
    	
	driver.get("https://demo.nopcommerce.com/register");
	
	String firstName = "Automation";
	String lastName = "FC";
	String emailAddress = "autofc" + getRandomNumber() + "@gmail.net";
	String day = "15";
	String month = "December";
	String year = "1996";
	String company = "Automation VN";
	String password = "1234567";
	
	By genderMaleBy = By.id("gender-male");
	By firstNameBy = By.id("FirstName");
	By lastNameBy = By.id("LastName");
	By dateDropdownBy = By.name("DateOfBirthDay");
	By monthDropdownBy= By.name("DateOfBirthMonth");
	By yearDropdownBy = By.name("DateOfBirthYear");
	By emailBy = By.id("Email");
	By companyBy = By.id("Company");
	
	driver.findElement(By.xpath("//a[@class='ico-register']")).click();
	driver.findElement(genderMaleBy).click();
	driver.findElement(firstNameBy).sendKeys(firstName);
	driver.findElement(lastNameBy).sendKeys(lastName);
	
	select = new Select(driver.findElement(dateDropdownBy));
	
	// Chon 1 item
	select.selectByVisibleText(day);
	
	// Kiem tra dropdown co phai multiple select
	Assert.assertFalse(select.isMultiple());
	
	// Kiem tra da chon dung item A chua 
	Assert.assertEquals(select.getFirstSelectedOption().getText(), day);
	// Get ra tong so item trong dropdown
	Assert.assertEquals(select.getOptions().size(), 32);
	
	select = new Select(driver.findElement(monthDropdownBy));
	
	select.selectByVisibleText(month);
	
	select = new Select(driver.findElement(yearDropdownBy));
	
	select.selectByVisibleText(year);
	
	driver.findElement(emailBy).sendKeys(emailAddress);
	driver.findElement(companyBy).sendKeys(company);
	driver.findElement(By.id("Password")).sendKeys(password);
	driver.findElement(By.id("ConfirmPassword")).sendKeys(password);
	driver.findElement(By.id("register-button")).click();
		
	Assert.assertTrue(driver.findElement(By.xpath("//div[@class='result']")).isDisplayed());
	
	driver.findElement(By.xpath("//a[@class='ico-account']")).click();
	
	Assert.assertTrue(driver.findElement(genderMaleBy).isSelected());
	Assert.assertEquals(driver.findElement(firstNameBy).getAttribute("value"), firstName);
	Assert.assertEquals(driver.findElement(lastNameBy).getAttribute("value"), lastName);
	
	select = new Select(driver.findElement(dateDropdownBy));
	Assert.assertEquals(select.getFirstSelectedOption().getText(), day);
	
	select = new Select(driver.findElement(monthDropdownBy));
	Assert.assertEquals(select.getFirstSelectedOption().getText(), month);
	
	select = new Select(driver.findElement(yearDropdownBy));
	Assert.assertEquals(select.getFirstSelectedOption().getText(), year);
	
	Assert.assertEquals(driver.findElement(emailBy).getAttribute("value"), emailAddress);
	Assert.assertEquals(driver.findElement(companyBy).getAttribute("value"), company);
	
	
	
    }
    
    @Test 
    public void TC_02_Rode() {
    driver.get("https://www.rode.com/wheretobuy");	
    select = new Select(driver.findElement(By.id("where_country")));
    select.selectByVisibleText("Vietnam");
    
    driver.findElement(By.id("search_loc_submit")).click();
    
    //De fail:
    Assert.assertEquals(driver.findElement(By.xpath("//div[@class='result_count']/span")).getText(), "29");
    
    //De pass:
//    Assert.assertEquals(driver.findElement(By.xpath("//span[text()='29']")), "29");
	
    List<WebElement> storeName = driver.findElements(By.xpath("//div[@id='search_results']//div[@class='store_name']"));
    Assert.assertEquals(driver.findElements(By.xpath("//div[@id='search_results']//div[@class='store_name']")).size(), 29);
    
    for (WebElement store : storeName) {
    	System.out.println(store.getText());
    }
    }

    @Test 
    public void TC_03_() {

    }
    @AfterClass
    public void afterClass() {
    	driver.quit();
    }
    public int getRandomNumber() {
		Random rand = new Random();
		return rand.nextInt(99999);
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