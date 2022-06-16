package webdriver;

import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
public class Topic_14_Upload_Files {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String dellName = "Dell.jpeg";
	String razorName = "Razor.jpg";
	String thinkpadName = "Thinkpad.png";
	
	String anh1 = "anh1.jpg";
	String anh2 = "anh2.jpeg";
	String anh3 = "anh3.png";
	
	
	String uploadFilePath = projectPath + File.separator + "Upload" + File.separator;
	
	String dellFilePath = uploadFilePath + dellName ;
	String razorFilePath = uploadFilePath+ razorName;
	String thinkpadFilePath = uploadFilePath + thinkpadName;
	
    String uploadFile2Path = projectPath + File.separator + "Upload 2" + File.separator;
    String uploadAnh1Path = uploadFile2Path + anh1;
    String uploadAnh2Path = uploadFile2Path + anh2;
    String uploadAnh3Path = uploadFile2Path + anh3;
    
    WebDriverWait Explicit;
	
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver",projectPath + "/browserDrivers/geckodriver");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		//driver.get("https://blueimp.github.io/jQuery-File-Upload/");
		Explicit = new WebDriverWait (driver,15);

	}
	
 //   @Test
	public void TC_01_sendKey_1_File() {
    	By uploadFile = By.xpath("//input[@type='file']");
    	
	
	driver.findElement(uploadFile).sendKeys(dellFilePath);	
	sleepInSecond(1);
	driver.findElement(uploadFile).sendKeys(razorFilePath);
	sleepInSecond(1);
	driver.findElement(uploadFile).sendKeys(thinkpadFilePath);	
	sleepInSecond(1);
	
//	Verify file is uploaded successfully:
	Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + dellName + "']")).isDisplayed());
	Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + razorName + "']")).isDisplayed());
	Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + thinkpadName + "']")).isDisplayed());
	
//	Click Start button to start
    List<WebElement>startList = driver.findElements(By.cssSelector("table button[class*='start']"));
    for (WebElement each: startList) {
    	each.click();
    	sleepInSecond(2);
    }
//  Verify 3 files are uploaded:
    	Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + dellName + "']")).isDisplayed());
    	Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + razorName + "']")).isDisplayed());
    	Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + thinkpadName + "']")).isDisplayed());
    	
    }
    
    
   // @Test 
    public void TC_02_senkey_allFiles() {
    	By uploadFile = By.xpath("//input[@type='file']");
    	driver.findElement(uploadFile).sendKeys(dellFilePath + "\n" + razorFilePath + "\n" + thinkpadFilePath);	
    	
	
    }

    @Test 
    public void TC_03_Upload() {
driver.get("https://vi.imgbb.com/");
sleepInSecond(3);
driver.findElement(By.xpath("//a[@class='btn btn-big blue']")).click();

sleepInSecond(4);
//Step3: Click upload
Explicit.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.upload-box-inner")));
driver.findElement(By.xpath("//button[@class='btn btn-big green']")).click();


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