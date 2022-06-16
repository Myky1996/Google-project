package webdriver;



import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
public class Topic_15_Implicit_Wait {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver",projectPath + "/browserDrivers/geckodriver");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://www.facebook.com/");

	}
	
//    @Test
	public void TC_01_Find_Element() {
    	
	
    //  1. Tim thay 1 matching node
    System.out.println("Start 1:" + getDateTimeNow());
    driver.findElement(By.id("email")).sendKeys("ky@gmail.com");
    System.out.println("End 1:" + getDateTimeNow());
    	
    //	2. Khong tim thay matching node
    System.out.println("Start 2:"+ getDateTimeNow());
    	
     try {
     driver.findElement(By.id("tiki")).isDisplayed();
     } finally {
     System.out.println("End 2:"+ getDateTimeNow());
     }
    //	3. Tim thay nhieu macthing node 
    
    	System.out.println("Start 3:"+ getDateTimeNow());
    	
    	driver.findElement(By.cssSelector("div#pageFooter a")).click();
    	
    	System.out.println("Start 3:"+ getDateTimeNow());
    	
//    	driver.findElement(By.id("pass")).sendKeys("12345678");

	}
    
    @Test 
    public void TC_02_Find_Elements() {
    	
//    	Find mutiple elements
    	List<WebElement>all;
    	
    //  1. Tim thay 1 matching node
    	System.out.println("Start 1:"+ getDateTimeNow());
    	all = driver.findElements(By.cssSelector("input#email"));
    	System.out.println("End 1:"+ getDateTimeNow());
    	System.out.println("List size:" + all.size());
    	
    //  2. Khong tim thay matching node
    	System.out.println("Start 2:"+ getDateTimeNow());
    	all = driver.findElements(By.cssSelector("input#tiki"));
    	System.out.println("End 2:"+ getDateTimeNow());
    	System.out.println("List size:" + all.size());
    	
    //  3. Tim thay nhieu macthing node 
    	System.out.println("Start 3:"+ getDateTimeNow());
    	all = driver.findElements(By.cssSelector("div#pageFooter a"));
    	System.out.println("End 3:"+ getDateTimeNow());
    	System.out.println("List size:" + all.size());

    }

    @Test 
    public void TC_03_() {

    }
    @AfterClass
    public void afterClass() {
    	driver.quit();
    }
    
    public String getDateTimeNow() {
    	Date date = new Date();
    	return date.toString();
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