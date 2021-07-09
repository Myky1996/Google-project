package webdriver;

import static org.testng.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Topic05_Xpath_CSS {
	WebDriver driver;
	String name, emailAdress, password, phone;
	// Action
	By nameTextboxBy = By.id("txtFirstname");
	By emailTextboxBy = By.id("txtEmail");
	By confirmEmailTextboxBy = By.id("txtCEmail");
	By passwordTextboxBy = By.id("txtPassword");
	By confirmPasswordTextboxBy = By.id("txtCPassword");
	By phoneTextboxBody = By.id("txtPhone");
	By registerButtonBy = By.xpath("//form[@id='frmLogin']//button");

	// Error
	By nameErrorMsgBy = By.id("txtFirstname-error");
	By emailErrorMsgBy = By.id("txtEmail-error");
	By confirmEmailErrorMsgBy = By.id("txtCEmail-error");
	By passwordErrorMsgBy = By.id("txtPassword-error");
	By confirmPasswordErrorMsgBy = By.id("txtCPassword-error");
	By phoneErrorMsgBy = By.id("txtPhone-error");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", "./browserDrivers/geckodriver");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
		name = "John Wick";
		emailAdress = "automation@gmail.net";
		password = "123456";
		phone = "0987666555";
	}

	@BeforeMethod
	public void beforeMethod() {
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
	}

	@Test
	public void TC01_Verify_text() {
		// Click Dang ky button:
		driver.findElement(registerButtonBy).click();
		// Error msg:
		assertEquals(driver.findElement(nameErrorMsgBy).getText(), "Vui lòng nhập họ tên");
		assertEquals(driver.findElement(emailErrorMsgBy).getText(), "Vui lòng nhập email");
		assertEquals(driver.findElement(confirmEmailErrorMsgBy).getText(), "Vui lòng nhập lại địa chỉ email");
		assertEquals(driver.findElement(passwordErrorMsgBy).getText(), "Vui lòng nhập mật khẩu");
		assertEquals(driver.findElement(confirmPasswordErrorMsgBy).getText(), "Vui lòng nhập lại mật khẩu");
		assertEquals(driver.findElement(phoneErrorMsgBy).getText(), "Vui lòng nhập số điện thoại.");
	}

	@Test
	public void TC_02_Inavlid_email() {
		// Input:
		driver.findElement(nameTextboxBy).sendKeys(name);
		driver.findElement(emailTextboxBy).sendKeys("123@123.234@");
		driver.findElement(confirmEmailTextboxBy).sendKeys("123@123.234@");
		driver.findElement(passwordTextboxBy).sendKeys(password);
		driver.findElement(confirmPasswordTextboxBy).sendKeys(password);
		driver.findElement(phoneTextboxBody).sendKeys(phone);
		//Click:
		driver.findElement(registerButtonBy).click();
		//Error:
		assertEquals(driver.findElement(emailErrorMsgBy).getText(), "Vui lòng nhập email hợp lệ");
		assertEquals(driver.findElement(confirmEmailErrorMsgBy).getText(), "Email nhập lại không đúng");
	}

	@Test
	public void TC_03_incorrect_Confirm_email() {
		// Input:
		driver.findElement(nameTextboxBy).sendKeys(name);
        driver.findElement(emailTextboxBy).sendKeys(emailAdress);
	    driver.findElement(confirmEmailTextboxBy).sendKeys("automation@gmail.com");
	    driver.findElement(passwordTextboxBy).sendKeys(password);
		driver.findElement(confirmPasswordTextboxBy).sendKeys(password);
		driver.findElement(phoneTextboxBody).sendKeys(phone);
		//Click:
		driver.findElement(registerButtonBy).click();
		//Error:
		assertEquals(driver.findElement(confirmEmailErrorMsgBy).getText(), "Email nhập lại không đúng");
	}

	@Test
	public void TC04_Password_Less_than_6_characters() {
		// Input:
		driver.findElement(nameTextboxBy).sendKeys(name);
		driver.findElement(emailTextboxBy).sendKeys(emailAdress);
	    driver.findElement(confirmEmailTextboxBy).sendKeys(emailAdress);
	    driver.findElement(passwordTextboxBy).sendKeys("1234");
		driver.findElement(confirmPasswordTextboxBy).sendKeys("1234");
		driver.findElement(phoneTextboxBody).sendKeys(phone);
		//Click:
		driver.findElement(registerButtonBy).click();
		//Error:
		assertEquals(driver.findElement(passwordErrorMsgBy).getText(), "Mật khẩu phải có ít nhất 6 ký tự");
		assertEquals(driver.findElement(confirmPasswordErrorMsgBy).getText(), "Mật khẩu phải có ít nhất 6 ký tự");
	}

	@Test
	public void TC_05_Incorrect_confirm_password() {
		// Input:
				driver.findElement(nameTextboxBy).sendKeys(name);
				driver.findElement(emailTextboxBy).sendKeys(emailAdress);
			    driver.findElement(confirmEmailTextboxBy).sendKeys(emailAdress);
			    driver.findElement(passwordTextboxBy).sendKeys(password);
				driver.findElement(confirmPasswordTextboxBy).sendKeys("654321");
				driver.findElement(phoneTextboxBody).sendKeys(phone);
				//Click:
				driver.findElement(registerButtonBy).click();
				//Error:
				assertEquals(driver.findElement(confirmPasswordErrorMsgBy).getText(), "Mật khẩu bạn nhập không khớp");
				
	}

	@Test
	public void TC_06_Invalid_phone_number() {
		// Nhap phone dang email
		// Input:
		driver.findElement(nameTextboxBy).sendKeys(name);
		driver.findElement(emailTextboxBy).sendKeys(emailAdress);
	    driver.findElement(confirmEmailTextboxBy).sendKeys(emailAdress);
	    driver.findElement(passwordTextboxBy).sendKeys(password);
		driver.findElement(confirmPasswordTextboxBy).sendKeys("654321");
		driver.findElement(phoneTextboxBody).sendKeys(emailAdress);
		//Click:
		driver.findElement(registerButtonBy).click();
		//Error:
		assertEquals(driver.findElement(phoneErrorMsgBy).getText(), "Vui lòng nhập con số");

		//Clear du lieu di de nhap lai:
		driver.findElement(phoneTextboxBody).clear();
		driver.findElement(phoneTextboxBody).sendKeys("012345678");
		driver.findElement(registerButtonBy).click();
		assertEquals(driver.findElement(phoneErrorMsgBy).getText(), "Số điện thoại phải từ 10-11 số.");
		
		//Clear du lieu di de nhap lai:
		driver.findElement(phoneTextboxBody).clear();
		driver.findElement(phoneTextboxBody).sendKeys("012345678444444444");
		driver.findElement(registerButtonBy).click();
		assertEquals(driver.findElement(phoneErrorMsgBy).getText(), "Số điện thoại phải từ 10-11 số.");
		//Clear du lieu di de nhap lai:
		driver.findElement(phoneTextboxBody).clear();
		driver.findElement(phoneTextboxBody).sendKeys("08");
		driver.findElement(registerButtonBy).click();
		assertEquals(driver.findElement(phoneErrorMsgBy).getText(), "Số điện thoại bắt đầu bằng: 09 - 03 - 012 - 016 - 018 - 019");		

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
