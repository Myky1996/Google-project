package webdriver;

import static org.testng.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_06_Web_Element_Command {
	
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	By emailTextboxBy = By.id("mail");
    By ageOver18RadioBy = By.id("over_18");
    By educationTextArea = By.id("edu");
    By User5Textby = By.xpath("//h5[text()='Name: User5']");
    By jobRole01Dropdown = By.id("job1");
    By developmentCheckbox = By.id("development");
    By slider01 = By.id("slider-1");
    By passwordTexbox = By.id("password");
    By ageDisabledRadioButton = By.id("radio-disabled");
    By biographyTextArea = By.id("bio");
    By jobRole03Dropdown = By.id("job3");
    By interestDisabledCheckbox = By.id("check-disbaled");
    By slider02 = By.id("slider-2");
    By javaCheckbox = By.id("java");
    
    
    
    
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver",projectPath + "/browserDrivers/geckodriver");
		driver = new FirefoxDriver();
		
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		
	}
	
    @Test
	public void TC_01_is_displayed() {
    	driver.get("https://automationfc.github.io/basic-form/index.html");
//    	1.1
    	WebElement emailTextBox = driver.findElement(By.id("email"));
    	
    	if(emailTextBox.isDisplayed()) {
    		emailTextBox.sendKeys("Automation Testing");
    		System.out.println("Email textbox is displayed");
    	} else {
    		System.out.println("Email text box is Not displayed");
    	}
		
//    	1.2
    	WebElement educationTextArea = driver.findElement(By.id("edu"));
    	if(educationTextArea.isDisplayed()) {
    	   educationTextArea.sendKeys("Automation Testing");
    	   System.out.println("Education text area is displayed");
    	} else {
    		System.out.println("Education text is not displayed");
    	}
    	
//    	1.3
    	WebElement ageOver18Radio = driver.findElement(By.id("over_18"));
    	
    	if(ageOver18Radio.isDisplayed()) {
    	   ageOver18Radio.click();
    	   System.out.println("Age over 18 radio is displayed");
    	} else {
    	   System.out.println("Age over 18 radio is displayed");
    	}
//    	1.4
    	WebElement nameUser5 = driver.findElement(By.xpath("//h5[text()='Name: User5']"));
    	if(nameUser5.isDisplayed()) {
    		System.out.println("User 5 is displayed");
    	} else { 
    		System.out.println("User 5 is not displayed");
    	}
    	
	}
    
    
    @Test 
    public void TC_02_is_displayed_Refactor() {
      driver.get("https://automationfc.github.io/basic-form/index.html");
      
      
      if(isElementDisplayed(emailTextboxBy)) {
    	 sendkeyToElement(emailTextboxBy, "Automation Testing");
      }
      
      if(isElementDisplayed(ageOver18RadioBy)) {
    	 clickToElement(ageOver18RadioBy);
      }
      
      if(isElementDisplayed(educationTextArea)) {
    	  sendkeyToElement(educationTextArea,"Automation Testing");
      }
      
      Assert.assertFalse(isElementDisplayed(User5Textby));  
    		
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
    
    public void sendkeyToElement(By by, String value) {
	  WebElement element = driver.findElement(by);
	  element.clear();
	  element.sendKeys(value);
	  
    }
    public void clickToElement(By by) {
    WebElement element = driver.findElement(by);
  	  element.click();
    }
    	
   	
    
    @Test 
    public void TC_03_is_Enabled() {
    	
    driver.get("https://automationfc.github.io/basic-form/index.html");
    
    Assert.assertTrue(isElementEnabled(emailTextboxBy));
    Assert.assertTrue(isElementEnabled(ageOver18RadioBy));
    Assert.assertTrue(isElementEnabled(educationTextArea));
    Assert.assertTrue(isElementEnabled(jobRole01Dropdown));
    Assert.assertTrue(isElementEnabled(developmentCheckbox));
    Assert.assertTrue(isElementEnabled(slider01));

    Assert.assertFalse(isElementEnabled(passwordTexbox));
    Assert.assertFalse(isElementEnabled(ageDisabledRadioButton));
    Assert.assertFalse(isElementEnabled(biographyTextArea));
    Assert.assertFalse(isElementEnabled(jobRole03Dropdown));
    Assert.assertFalse(isElementEnabled(interestDisabledCheckbox));
    Assert.assertFalse(isElementEnabled(slider02));

    
    }
    
    
    public boolean isElementEnabled(By by) {
    	
        WebElement element = driver.findElement(by);
        if(element.isEnabled()) {
           System.out.println("Element [" + by + "] is enabled");
           return true;
        	
        } else {
           System.out.println("Element [" + by + "] is disabled");
           return false;
        } 
    }
        
     @Test 
     public void TC_04_is_Selected() {
        	
     driver.get("https://automationfc.github.io/basic-form/index.html");   
 
     clickToElement(ageOver18RadioBy);
     clickToElement(developmentCheckbox);
     
     
     Assert.assertTrue(isElementSelected(ageOver18RadioBy));
     Assert.assertTrue(isElementSelected(developmentCheckbox));
     Assert.assertFalse(isElementSelected(javaCheckbox));
     
     clickToElement(ageOver18RadioBy);
     clickToElement(developmentCheckbox);
     
     Assert.assertTrue(isElementSelected(ageOver18RadioBy));
     Assert.assertFalse(isElementSelected(developmentCheckbox));
     Assert.assertFalse(isElementSelected(javaCheckbox));
     
     }
        
     public boolean isElementSelected(By by) {
     	
       WebElement element = driver.findElement(by);
       if(element.isSelected()) {
    	  System.out.println("Element [" + by + "] is selected");
    	  return true;
    	    	
       } else {
    	  System.out.println("Element [" + by + "] is de-selected");
    	  return false;
       }
     }
     
     
       @Test 
       public void TC_05_Sign_up_Validate() {
    	   driver.get("https://login.mailchimp.com/signup/");
       driver.findElement(By.id("email")).sendKeys("automation@gmail.com");
       driver.findElement(By.id("new_username")).sendKeys("automation");
       
       By passwordTextbox = By.id("new_password");
       By signUpButton = By.id("create_account");
       By checkBox = By.id("marketing_newsletter");
       driver.findElement(checkBox).click();
    		   
       //Lowercase
       driver.findElement(passwordTextbox).sendKeys("auto");
       
       Assert.assertTrue(driver.findElement(By.xpath("//li[@class='lowercase-char completed' and text()='One lowercase character']")).isDisplayed());
       Assert.assertFalse(driver.findElement(signUpButton).isEnabled());
       
       //Uppercase
       driver.findElement(passwordTextbox).clear();
       driver.findElement(passwordTextbox).sendKeys("U");
       Assert.assertTrue(driver.findElement(By.xpath("//li[@class='uppercase-char completed' and text()='One uppercase character']")).isDisplayed());
       Assert.assertFalse(driver.findElement(signUpButton).isEnabled());
       
       //Number
       driver.findElement(passwordTextbox).clear();
       driver.findElement(passwordTextbox).sendKeys("1");
       Assert.assertTrue(driver.findElement(By.xpath("//li[@class='number-char completed' and text()='One number']")).isDisplayed());
       Assert.assertFalse(driver.findElement(signUpButton).isEnabled());
       
       //Special
       driver.findElement(passwordTextbox).clear();
       driver.findElement(passwordTextbox).sendKeys("*");
       Assert.assertTrue(driver.findElement(By.xpath("//li[@class='special-char completed' and text()='One special character']")).isDisplayed());
       Assert.assertFalse(driver.findElement(signUpButton).isEnabled());
       
       //>=8 Characters
       driver.findElement(passwordTextbox).clear();
       driver.findElement(passwordTextbox).sendKeys("123456780");
       Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char completed' and text()='8 characters minium']")).isDisplayed());
       Assert.assertFalse(driver.findElement(signUpButton).isEnabled());
       
       //Full valid data
  
       driver.findElement(passwordTextbox).clear();
       driver.findElement(passwordTextbox).sendKeys("Ky12345*");
       
       Assert.assertFalse(driver.findElement(By.xpath("//li[@class='lowercase-char completed' and text()='One lowercase character']")).isDisplayed());
       Assert.assertFalse(driver.findElement(By.xpath("//li[@class='uppercase-char completed' and text()='One uppercase character']")).isDisplayed());
       Assert.assertFalse(driver.findElement(By.xpath("//li[@class='number-char completed' and text()='One number']")).isDisplayed());
       Assert.assertFalse(driver.findElement(By.xpath("//li[@class='special-char completed' and text()='One special character']")).isDisplayed());
       Assert.assertFalse(driver.findElement(By.xpath("//li[@class='special-char completed' and text()='One special character']")).isDisplayed());
       Assert.assertTrue(driver.findElement(signUpButton).isEnabled());
       Assert.assertTrue(driver.findElement(checkBox).isSelected());
       }
    @AfterClass
    public void afterClass() {
   	driver.quit();
    }
    
}