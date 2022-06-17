package basic;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Search_Google {
	WebDriver driver;
	WebDriverWait expliciteWait;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		expliciteWait = new WebDriverWait(driver, 15);
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	}

	@Test
	public void TC_01_Search_Google() {
		driver.get("https://www.google.com/");

		WebElement searchBox = driver.findElement(By.xpath("//input[@title='Tìm kiếm']"));

		searchBox.sendKeys("Demo with selenium");
		searchBox.sendKeys(Keys.ENTER);
		
		expliciteWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@aria-label='Tìm kiếm']")));
		Assert.assertEquals(driver.findElement(By.xpath("//input[@aria-label='Tìm kiếm']")).getAttribute("value"),
				"Demo with selenium");
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
