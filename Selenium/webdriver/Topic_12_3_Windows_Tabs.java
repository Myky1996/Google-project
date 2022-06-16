package webdriver;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
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
public class Topic_12_3_Windows_Tabs {
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
	
//    @Test
	public void TC_01_() {
    	
	driver.get("https://automationfc.github.io/basic-form/index.html"); 
	String parentId = driver.getWindowHandle();
	
//	switch to google page:
	driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();
	sleepInSecond(4);
	switchToWindowsByTitle("Google");
	Assert.assertEquals(driver.getTitle(), "Google");
	
	
//	Switch to parent page:
	
	switchToWindowsByTitle("SELENIUM WEBDRIVER FORM DEMO");
	
//	Switch to Facebook:
	driver.findElement(By.xpath("//a[text()='FACEBOOK']")).click();
	sleepInSecond(4);
	switchToWindowsByTitle("Facebook - Đăng nhập hoặc đăng ký");
	Assert.assertEquals(driver.getTitle(), "Facebook - Đăng nhập hoặc đăng ký");
//	Switch to parent page:
		
	switchToWindowsByTitle("SELENIUM WEBDRIVER FORM DEMO");
//	Switch to Tiki:
	driver.findElement(By.xpath("//a[text()='TIKI']")).click();
	sleepInSecond(4);
	switchToWindowsByTitle("Tiki - Mua hàng online giá tốt, hàng chuẩn, ship nhanh");
	Assert.assertEquals(driver.getTitle(), "Tiki - Mua hàng online giá tốt, hàng chuẩn, ship nhanh");
	switchToWindowsByTitle("SELENIUM WEBDRIVER FORM DEMO");
//	Close tat ca window tru parent window:
	closeAllwindow(parentId);
	
		
	}
    
//    @Test 
    public void TC_02_Ky_na() {
    	driver.get("https://kyna.vn/");
    	String parentID = driver.getWindowHandle();
    	sleepInSecond(5);
//    	Step 2: Neu pop up hien thi thi close
    	By kynaPopup = By.cssSelector("img.fancybox-image");
    	
    	List<WebElement>popupList = driver.findElements(kynaPopup);
    	
    	if(popupList.size()>0) {
    	System.out.println("--Popup displayed--");
    	driver.findElement(By.cssSelector("a[title='Close']")).click();
    	}
    	else { System.out.println("--Popup NOT displayed--");
    	
    	}
    	sleepInSecond(3);
//    	Step 3: Click link tai footer
    	jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
    	driver.findElement(By.xpath("//img[@alt='facebook']")).click();
    	sleepInSecond(3);
//    	Go to Facebook window by title:
    	Set<String>allWindows = driver.getWindowHandles();
    	for(String each: allWindows) {
    		driver.switchTo().window(each);
    		String windowTitle = driver.getTitle();
    		System.out.println(windowTitle);
    		if(windowTitle.equals("Kyna.vn - Trang chủ | Facebook")) {
    			break;
    		}
    	}
    	sleepInSecond(4);
    	Assert.assertEquals(driver.getTitle(), "Kyna.vn - Trang chủ | Facebook");
//    	Go back to parent page
    	switchToWindowsByTitle("Kyna.vn - Học online cùng chuyên gia");
    	
//    	Go to Youtube tai footer:
    	jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
    	driver.findElement(By.xpath("//img[@alt='youtube']")).click();
    	sleepInSecond(4);
    	switchToWindowsByTitle("Kyna.vn - YouTube");
    	sleepInSecond(4);
    	Assert.assertEquals(driver.getTitle(), "Kyna.vn - YouTube");
    	switchToWindowsByTitle("Kyna.vn - Học online cùng chuyên gia");
//      Quay lai trang kyna va dong cac tab
    	Set<String> alls = driver.getWindowHandles();
    	for(String every: alls) {
    		if(!every.equals(parentID)) {
   			driver.switchTo().window(every);
   			driver.close();
    		}
    	}
    	driver.switchTo().window(parentID);
    	
    }

    
   
//    @Test 
    public void TC_03_Demoguru() {
//    	Step 1:
    	driver.get("http://live.demoguru99.com/index.php/");
    	String parentID= driver.getWindowHandle();
//    	Step 2:
    	driver.findElement(By.xpath("//a[text()='Mobile']")).click();
//    	Step 3:
    	driver.findElement(By.xpath("//a[@title='Xperia']/following-sibling::div//a[text()='Add to Compare']")).click();
    	Assert.assertTrue(driver.findElement(By.xpath("//span[text()='The product Sony Xperia has been added to comparison list.']")).isDisplayed());
//    	Step 4:
    	driver.findElement(By.xpath("//a[@title='Samsung Galaxy']/following-sibling::div//a[text()='Add to Compare']")).click();
    	Assert.assertTrue(driver.findElement(By.xpath("//span[text()='The product Samsung Galaxy has been added to comparison list.']")).isDisplayed());
//      Step 5:
    	driver.findElement(By.xpath("//span[text()='Compare']")).click();
    	
//    	Step 6:
    	Set<String>allTabs = driver.getWindowHandles();
    	for(String compareTab: allTabs) {
    		driver.switchTo().window(compareTab);
    		String tabame = driver.getTitle();
    		if(tabame.equals("Products Comparison List - Magento Commerce")) {
    			break;
    		}
    	}
    	sleepInSecond(4);
//    	Step 7:
    	Assert.assertEquals(driver.getTitle(), "Products Comparison List - Magento Commerce");
//      Close all tabs va quay ve trang parent:
    	Set<String>Atabs = driver.getWindowHandles();
    	for (String run: Atabs) {
    		if(!run.equals(parentID)) {
    			driver.switchTo().window(run);
    			driver.close();
    		}
    	}
    	driver.switchTo().window(parentID);
//    	Step 8:
    	driver.findElement(By.xpath("//a[text()='Clear All']")).click();
    	Alert alert1 = driver.switchTo().alert();
    	alert1.accept();
    	sleepInSecond(5);
    	Assert.assertTrue(driver.findElement(By.xpath("//span[text()='The comparison list was cleared.']")).isDisplayed());
    }
    
    
    
    
    public void switchToWindowsByTitle(String expectedTitle) {
    	Set<String>allWindows = driver.getWindowHandles();
    	for(String id: allWindows) {
    		driver.switchTo().window(id);
    		String windowTitle = driver.getTitle();
    		System.out.println(windowTitle);
    		if(windowTitle.equals(expectedTitle)) {
    			break;
    		}
    	}
    	
    }
    
    public void closeAllwindow(String parentId) {
//    	Get ra tat ca
    	Set<String>allWindows = driver.getWindowHandles();
//    	Dung vong lap
    	for(String id: allWindows) {
    		if(!id.equals(parentId)) {
    			driver.switchTo().window(id);
    			driver.close();
    		}
    	}
    	driver.switchTo().window(parentId);
    }
    public void clickByJS(By element) {
    	jsExecutor.executeScript("argurments[0].click()", driver.findElement(element));
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
}