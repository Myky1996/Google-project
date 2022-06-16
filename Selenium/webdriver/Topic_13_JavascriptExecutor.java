package webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
public class Topic_13_JavascriptExecutor {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String loginPageURL, userID, password, name, gender, dobInput, dobOutput, addressInput, addressOutput, city, state, pin, phone, email, customerID;
	JavascriptExecutor jsExecutor;
	WebDriverWait expliciteWait;
	By nameTextboxBy = By.name("name");
	By genderRadioBy = By.xpath("//input[@value='f']");
	By genderTextboxBy = By.name("gender");
	By dobTextboxBy = By.name("dob");
	By addressTextboxBy = By.name("addr");
	By cityTextboxBy = By.name("city");
	By stateTextboxBy = By.name("state");
	By pinTextboxBy = By.name("pinno");
	By mobileTextboxBy = By.name("telephoneno");
	By emailTextboxBy = By.name("emailid");
	By passwordTextboxBy = By.name("password");
	
	
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver",projectPath + "/browserDrivers/geckodriver");
		driver = new FirefoxDriver();
		jsExecutor = (JavascriptExecutor) driver;
		expliciteWait = new WebDriverWait(driver,20);
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}
	
//    @Test
	public void TC_01_() {
//    Step 1:	
	driver.get("http://live.demoguru99.com/");
	
//	Step 2: Get domain
	String domainGuru = (String) executeForBrowser("return document.domain");
	Assert.assertEquals(domainGuru, "live.demoguru99.com");
//	Step 3: Get URL:
	String urlGuru = (String) executeForBrowser("return document.URL");

	Assert.assertEquals(urlGuru,"http://live.demoguru99.com/");
//	Step 4: Open Mobile page:
	
	highlightElement("//a[text()='Mobile']");
	
	clickToElementByJS("//a[text()='Mobile']");
//	Step 5: Add cart:
    highlightElement("//a[text()='Samsung Galaxy']/parent::h2//following-sibling::div//button");
	
	clickToElementByJS("//a[text()='Samsung Galaxy']/parent::h2//following-sibling::div//button");
//	Step 6: Verify msg add vao cart
//	Assert.assertTrue(isExpectedTextInInnerText("Samsung Galaxy was added to your shopping cart."));
	
//	Step 7: Open customer service page:
	highlightElement("//a[text()='Customer Service']");
	clickToElementByJS("//a[text()='Customer Service']");
//	String csTitle = (String) executeForBrowser("return docucment.title");
	
//	Assert.assertEquals(csTitle, "Customer Service");
//	Step 8: Scroll toi cuoi trang
	scrollToBottomPage();
//	Step 9: Input email:
	highlightElement("input[type='email']");
	
	sendkeyToElementByJS("input[type='email']", generateEmail());
		
//	Step 10: 
	highlightElement("//span[text()='Subscribe']");
	clickToElementByJS("//span[text()='Subscribe']");
	
//	Assert.assertTrue(getInnerText().contains("Thank you for your subscription."));
//	Step 11:
	navigateToUrlByJS("http://demo.guru99.com/v4/");
	String domainGuru1 = (String) executeForBrowser("return document.domain");
	Assert.assertEquals(domainGuru1, "demo.guru99.com");
	
	}
    
//    @Test 
    public void TC_02_validation_msg() {
	driver.get("https://sieuthimaymocthietbi.com/account/register");
	String validationMsg;
	driver.findElement(By.xpath("//button[@value='Đăng ký']")).click();
	
	validationMsg = getElementValidationMessage("//input[@id='lastName']");
	Assert.assertEquals(validationMsg, "Please fill out this field.");
	
	driver.findElement(By.xpath("//input[@id='lastName']")).sendKeys("Automation FC");
	
	driver.findElement(By.xpath("//button[@value='Đăng ký']")).click();
	validationMsg = getElementValidationMessage("//input[@id='firstName']");
	Assert.assertEquals(validationMsg, "Please fill out this field.");
	
	driver.findElement(By.xpath("//input[@id='firstName']")).sendKeys("mymy");
	
	driver.findElement(By.xpath("//button[@value='Đăng ký']")).click();
	validationMsg = getElementValidationMessage("//input[@id='email']");
	Assert.assertEquals(validationMsg, "Please fill out this field.");
	
//	Email:
	driver.findElement(By.xpath("//input[@id='email']")).sendKeys("auto@");
	driver.findElement(By.xpath("//button[@value='Đăng ký']")).click();
	validationMsg = getElementValidationMessage("//input[@id='email']");
	Assert.assertEquals(validationMsg, "Please enter an email address.");
	
	driver.findElement(By.xpath("//input[@id='email']")).clear();
	
	driver.findElement(By.xpath("//input[@id='email']")).sendKeys("auto@33");
	driver.findElement(By.xpath("//button[@value='Đăng ký']")).click();
	validationMsg = getElementValidationMessage("//input[@id='email']");
	Assert.assertEquals(validationMsg, "Please match the requested format.");
	
	driver.findElement(By.xpath("//input[@id='email']")).clear();

	
	driver.findElement(By.xpath("//input[@id='email']")).sendKeys("auto@33.com");
	driver.findElement(By.xpath("//button[@value='Đăng ký']")).click();
	
	validationMsg = getElementValidationMessage("//input[@id='password']");
	Assert.assertEquals(validationMsg, "Please fill out this field.");
	
	
    }
    

   // @Test 
    public void TC_03_Remove_attribute() {

    	driver.get("http://demo.guru99.com/v4");
    	
    	name = "Angela Jolie";
		gender = "female";
		dobInput = "01/01/1990";
		dobOutput ="1990-01-01";
		addressInput = "234 PO\nBridge New";
		addressOutput ="234 PO Bridge New";
		city = "Los Angeles";
		state = "California";
		pin = "225588";
		phone = "0977308530";
		email = "angela" + generateEmail();
		
		
    	loginPageURL = driver.getCurrentUrl();
		driver.findElement(By.xpath("//a[text()= 'here']")).click();

		driver.findElement(By.name("emailid")).sendKeys("dam@gmail.com");
		driver.findElement(By.name("btnLogin")).click();
		
		userID = driver.findElement(By.xpath("//td[text()='User ID :']/following-sibling::td")).getText();
		password = driver.findElement(By.xpath("//td[text()='Password :']/following-sibling::td")).getText();

		driver.get(loginPageURL);

		driver.findElement(By.name("uid")).sendKeys(userID);
		driver.findElement(By.name("password")).sendKeys(password);
		driver.findElement(By.name("btnLogin")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//marquee[@class='heading3' and text()= \"Welcome To Manager's Page of Guru99 Bank\"]"))
				.isDisplayed());
		
		driver.findElement(By.xpath("//a[text()='New Customer']")).click();

		driver.findElement(nameTextboxBy).sendKeys(name);
		driver.findElement(genderRadioBy).click();
		
		jsExecutor.executeScript("arguments[0].removeAttribute('type')",driver.findElement(dobTextboxBy));
		sleepInSecond(5);
		removeAttributeInDOM("//input[@name='dob']","type");
		sleepInSecond(5);
		driver.findElement(dobTextboxBy).sendKeys(dobInput);
		
		driver.findElement(addressTextboxBy).sendKeys(addressInput);
		driver.findElement(cityTextboxBy).sendKeys(city);
		driver.findElement(stateTextboxBy).sendKeys(state);
		driver.findElement(pinTextboxBy).sendKeys(pin);
		driver.findElement(mobileTextboxBy).sendKeys(phone);
		driver.findElement(emailTextboxBy).sendKeys(email);
		driver.findElement(passwordTextboxBy).sendKeys(password);
        driver.findElement(By.name("sub")).click();
        
        Assert.assertTrue(driver.findElement(By.xpath("//p[text()='Customer Registered Successfully!!!']")).isDisplayed());
     
        Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Customer Name']/following-sibling::td")).getText(), name);
        Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Gender']/following-sibling::td")).getText(), gender);
        Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Birthdate']/following-sibling::td")).getText(), dobOutput);
        Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText(), addressOutput);
        Assert.assertEquals(driver.findElement(By.xpath("//td[text()='City']/following-sibling::td")).getText(), city);
        Assert.assertEquals(driver.findElement(By.xpath("//td[text()='State']/following-sibling::td")).getText(), state);
        Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Pin']/following-sibling::td")).getText(), pin);
        Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Mobile No.']/following-sibling::td")).getText(), phone);
        Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Email']/following-sibling::td")).getText(), email);
		
		
    }
    @Test
    public void TC_04_Create_account() {
//    	Step 1
    	driver.get("http://live.demoguru99.com/");
//    	Step 2:
    	clickToElementByJS("//div[@id='header-account']//a[@title='My Account']");
//    	Step 3:
    	clickToElementByJS("//a[@title='Create an Account']");
//    	Step 4: Input thong tin
    	sendkeyToElementByJS("//input[@id='firstname']","Rachel");
    	sendkeyToElementByJS("//input[@id='middlename']","Green");
    	sendkeyToElementByJS("//input[@id='lastname']","Family");
    	sendkeyToElementByJS("//input[@id='email_address']",generateEmail());
    	sendkeyToElementByJS("//input[@id='password']","Buimyky888");
    	sendkeyToElementByJS("//input[@id='confirmation']","Buimyky888");
//    	Step 5:
    	clickToElementByJS("//button[@title='Register']");
    	sleepInSecond(2);
//    	Step 6: Verify
    	
    	Assert.assertTrue(isExpectedTextInInnerText("Thank you for registering with Main Website Store."));
    	
//    	Step 7: Log out
    	clickToElementByJS("//a[@title='Log Out']");
    	
//    	Step 8: Navigate homepage 
    	String urlGuru = (String)executeForBrowser("return document.URL");
    	
    	
    	
    	
    }
    @AfterClass
    public void afterClass() {
    	//driver.quit();
    }
    public Object executeForBrowser(String javaScript) {
		return jsExecutor.executeScript(javaScript);
	}

	public String getInnerText() {
		return (String) jsExecutor.executeScript("return document.documentElement.innerText;");
	}

	public boolean isExpectedTextInInnerText(String textExpected) {
		String textActual = (String) jsExecutor.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0];");
		return textActual.equals(textExpected);
	}

	public void scrollToBottomPage() {
		jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}

	public void navigateToUrlByJS(String url) {
		jsExecutor.executeScript("window.location = '" + url + "'");
	}

	public void highlightElement(String locator) {
		WebElement element = getElement(locator);
		String originalStyle = element.getAttribute("style");
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", "border: 2px solid red; border-style: dashed;");
		sleepInSecond(1);
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", originalStyle);
	}

	public void clickToElementByJS(String locator) {
		jsExecutor.executeScript("arguments[0].click();", getElement(locator));
	}

	public void scrollToElement(String locator) {
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getElement(locator));
	}

	public void sendkeyToElementByJS(String locator, String value) {
		jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", getElement(locator));
	}

	public void removeAttributeInDOM(String locator, String attributeRemove) {
		jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getElement(locator));
	}
	public boolean areJQueryAndJSLoadedSuccess(WebDriver driver) {
		WebDriverWait explicitWait = new WebDriverWait(driver, 30);
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		
		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				try {
					return ((Long) jsExecutor.executeScript("return jQuery.active") == 0);
					
				} catch (Exception e) {
					return true;
				}
			}
		};
		
		ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return jsExecutor.executeScript("return document.readyState").toString().equals("complete");
			}
			};
			return explicitWait.until(jQueryLoad) && explicitWait.until(jsLoad);
	}

	public String getElementValidationMessage(String locator) {
		return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getElement(locator));
	}

	public boolean isImageLoaded(String locator) {
		boolean status = (boolean) jsExecutor.executeScript("return arguments[0].complete "
				+ "&& typeof arguments[0].naturalWidth != \"undefined\" "
				+ "&& arguments[0].naturalWidth > 0", getElement(locator));
	
			return status;
		
	}
	public String generateEmail() {
		Random rand = new Random();
		return rand.nextInt(9999) + "@mail.net";
	}

	public WebElement getElement(String locator) {
		return driver.findElement(By.xpath(locator));
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