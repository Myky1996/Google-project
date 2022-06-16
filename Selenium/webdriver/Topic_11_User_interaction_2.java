package webdriver;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
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
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_11_User_interaction_2 {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	Actions action;
	String osName = System.getProperty("os.name");
	
	JavascriptExecutor jsExecutor;
	
	String jsHelperPath = projectPath + "/dragAndDrop/drag_and_drop_helper_js";
	
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver",projectPath + "/browserDrivers/geckodriver");
		driver = new FirefoxDriver();
		action = new Actions(driver);
		jsExecutor = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		

	}
	
    @Test
	public void TC_01_Right_click() {
    	
	driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");
	
	action.contextClick(driver.findElement(By.cssSelector("span.context-menu-one"))).perform();
	sleepInSecond(3);
	
	action.moveToElement(driver.findElement(By.cssSelector("li.context-menu-icon-quit"))).perform();
	sleepInSecond(3);
	
	Assert.assertTrue(driver.findElement(By.cssSelector("li.context-menu-visible.context-menu-hover")).isDisplayed());
	
	action.click(driver.findElement(By.cssSelector("li.context-menu-icon-quit"))).perform();
	sleepInSecond(2);
	
	driver.switchTo().alert().accept();
	sleepInSecond(2);
	
	Assert.assertFalse(driver.findElement(By.cssSelector("li.context-menu-icon-quit")).isDisplayed());
	
	}
    
    @Test
    public void TC_02_Drag_and_Drop_HTML4() {
    	driver.get("https://automationfc.github.io/kendo-drag-drop/");
    	
    	WebElement small = driver.findElement(By.id("draggable"));
    	WebElement big = driver.findElement(By.id("droptarget"));
    	
    	action.dragAndDrop(small, big).perform();
    	sleepInSecond(2);
    	
    	Assert.assertEquals(big.getText(), "You did great!"); 	
    	
    }
    
    @Test
    public void TC_03_Drag_and_Drop_HTML5() throws IOException {
    	driver.get("https://automationfc.github.io/drag-drop-html5/");
    	
    	String jsHelperFileContent = getContentFile(jsHelperPath);
    	String sourceCss = "#column-a";
    	String targetCss = "#column-b";
    	

    	// A to B
    	jsHelperFileContent = jsHelperFileContent + "$(\"" + sourceCss + "\").simulateDragDrop({ dropTarget: \"" + targetCss + "\"});";
    	jsExecutor.executeScript(jsHelperFileContent);
    	sleepInSecond(2);
    			Assert.assertTrue(driver.findElement(By.xpath("//div[@id='column-a']/header[text()='B']")).isDisplayed());
    			Assert.assertTrue(driver.findElement(By.xpath("//div[@id='column-b']/header[text()='A']")).isDisplayed());
    			// B to A
    			jsExecutor.executeScript(jsHelperFileContent);
    	    	sleepInSecond(2);
    			Assert.assertTrue(driver.findElement(By.xpath("//div[@id='column-a']/header[text()='A']")).isDisplayed());
    			Assert.assertTrue(driver.findElement(By.xpath("//div[@id='column-b']/header[text()='B']")).isDisplayed());
    	
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

public String getContentFile(String filePath) throws IOException {
	Charset cs = Charset.forName("UTF-8");
	FileInputStream stream = new FileInputStream(filePath);
	try {
		Reader reader = new BufferedReader(new InputStreamReader(stream, cs));
		StringBuilder builder = new StringBuilder();
		char[] buffer = new char[8192];
		int read;
		while ((read = reader.read(buffer, 0, buffer.length)) > 0) {
			builder.append(buffer, 0, read);
		}
		return builder.toString();
	} finally {
		stream.close();
	}
}
}