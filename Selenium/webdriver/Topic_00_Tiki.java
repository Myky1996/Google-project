package webdriver;

import static org.testng.Assert.assertEquals;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
public class Topic_00_Tiki {
	WebDriver driver;
	JavascriptExecutor jsExecutor;
	String projectPath = System.getProperty("user.dir");
	WebDriverWait explicitWait;
	Select select;
	Actions action;
	By loginbtn = By.xpath("//button[contains(text(),'Đăng nhập')]");
	By emailTextBox = By.xpath("//input[@placeholder='Email/Số điện thoại/Tên đăng nhập']");	
	By passwordTextBox = By.xpath("//input[@placeholder='Mật khẩu']");
	String name, email, password, phone;
	String nameTooltip, emailTooltip, nationTooltip, fieldTooltip;
	
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver",projectPath + "/browserDrivers/geckodriver");
		driver = new FirefoxDriver();
        explicitWait = new WebDriverWait(driver, 15);
		action = new Actions(driver);
		
		jsExecutor = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}
	
    //@Test
	public void TC_01_Sign_in() {
    	
	driver.get("https://shopee.vn/");
	sleepInSecond(5);
	driver.findElement(By.xpath("//div[@class='shopee-popup__close-btn']")).click();
	
	//Verify loginbtn is disable:
//	Assert.assertFalse(driver.findElement(loginbtn).isEnabled());
	
	driver.findElement(By.xpath("//a[contains(text(),'Đăng nhập')]")).click();
	sleepInSecond(4);
	driver.findElement(emailTextBox).sendKeys("0366823285");
	driver.findElement(passwordTextBox).sendKeys("Buimyky888");
	
	sleepInSecond(1);
	driver.findElement(loginbtn).click();
	
	
	
	}
    
    //@Test 
    public void TC_02_Verify_error_message() {
    	driver.get("https://shopee.vn/buyer/login?next=https%3A%2F%2Fshopee.vn%2F");
        sleepInSecond(2);	
     
    	//click:
        verifyMessage(emailTextBox, "k");
        verifyMessage(passwordTextBox, "m");
    	Assert.assertEquals(driver.findElement(By.xpath("//input[@placeholder='Email/Số điện thoại/Tên đăng nhập']/parent::div/following-sibling::div")).getText(), 
        		"Vui lòng điền vào mục này.");
    	Assert.assertEquals(driver.findElement(By.xpath("//input[@placeholder='Mật khẩu']/parent::div/following-sibling::div")).getText(), 
        		"Vui lòng điền vào mục này.");
        
    }

//    @Test 
    public void TC_03_Verify_wrong_pw_message() {
    	driver.get("https://shopee.vn/buyer/login?next=https%3A%2F%2Fshopee.vn%2F");
    	String phoneNumber = "0366823285";
    	sleepInSecond(2);
    	driver.findElement(emailTextBox).sendKeys(phoneNumber);
    	driver.findElement(passwordTextBox).sendKeys("mmm");
      	sleepInSecond(2);
    	driver.findElement(loginbtn).click();
  
    	Assert.assertEquals(driver.findElement(By.xpath("//div[contains(text(),"
    			+ "'Tên tài khoản của bạn hoặc Mật khẩu không đúng, vui lòng thử lại')]")).getText(), 
    			"Tên tài khoản của bạn hoặc Mật khẩu không đúng, vui lòng thử lại");
    
    }
    
   //@Test
    public void TC_04_Sign_up_Ebay() {
    	driver.get("https://signup.ebay.com/pa/crte?");
    	String firstName = "Ky";
    	String lastName = "Bui";
    	String password = "Kk153970#";
    	driver.findElement(By.xpath("//input[@id='firstname']")).sendKeys(firstName);  	
    	driver.findElement(By.xpath("//input[@id='lastname']")).sendKeys(lastName);  	
    	driver.findElement(By.xpath("//input[@id='password']")).sendKeys(password);  	
    	driver.findElement(By.xpath("//input[@id='showpassword']")).click();  
    	sleepInSecond(2);
    	
    	driver.findElement(By.xpath("//button[@id='EMAIL_REG_FORM_SUBMIT']")).click();
    }
    	
//    @Test
    public void TC_05_Sign_up_Sparkle_Panda() {
    	driver.get("https://www.sparklepandas.com/");
    	sleepInSecond(7);
    	driver.findElement(By.xpath("//button[@class='btn-sign-in']")).click();
    	driver.findElement(By.xpath("//a[text()=' Join Now ']")).click();
    	
    	//step 1:
    	driver.findElement(By.xpath("//span[text()=' User Account ']/following-sibling::span[@class='checkmark']")).click();
    	driver.findElement(By.xpath("//input[@placeholder='Username']")).sendKeys("Amy");
    	driver.findElement(By.xpath("//button[@name='signup']")).click();
    	
    	
    	//step 2:
    	driver.findElement(By.xpath("//input[@id='inputEmail']")).sendKeys("AmySP@yopmail.com");
    	driver.findElement(By.xpath("//input[@id='input-password3']")).sendKeys("AmySP@yopmail.com");
    	driver.findElement(By.xpath("//input[@id='input-password4']")).sendKeys("AmySP@yopmail.com");
    	sleepInSecond(7);
    	
    	selecItemInDropdown(By.xpath("//span[text()='month']"), By.xpath("//div[@class='pd-dropdown mrr-24 input-box-size-123 dropdown-month show']//div[@class='dropdown-menu show']/a"), "June");
    	selecItemInDropdown(By.xpath("//span[text()='day']"), By.xpath("//div[@class='pd-dropdown mrr-24 dropdown-days input-box-size-108 show']//div[@class='dropdown-menu show']/a"), "25");
    	selecItemInDropdown(By.xpath("//span[text()='year']"), By.xpath("//div[@class='pd-dropdown dropdown-year input-box-rest input-box-size-123 show']//div[@class='dropdown-menu show']/a"), "1996");
    		
    	
    	driver.findElement(By.xpath("//button[contains(text(),' Join SparklePandas ')]")).click();
    	
    	
    	
    }
//    @Test
    public void TC_06_Verify_error_messsage() {
    	driver.get("https://www.sparklepandas.com/");
    	sleepInSecond(7);
    	driver.findElement(By.xpath("//button[@class='btn-sign-in']")).click();
    	driver.findElement(By.xpath("//a[text()=' Join Now ']")).click();
    	
//    	1. Verify leaving empty
    	
//    	driver.findElement(By.xpath("//button[@name='signup']")).click();
//    	Assert.assertEquals(driver.findElement(By.xpath("//p[@class='your-role']")).getText(), "Select your role on Sparkle Pandas to proceed");
//    	Assert.assertEquals(driver.findElement(By.xpath("//small[@class='text-danger error-form']")).getText(), "Nickname is required!");
    	
    	// 2. Wrong invitaion code:
    	//step 1:
    	driver.findElement(By.xpath("//span[text()=' User Account ']/following-sibling::span[@class='checkmark']")).click();
    	driver.findElement(By.xpath("//input[@placeholder='Username']")).sendKeys("Amee");
    	driver.findElement(By.xpath("//input[@formcontrolname='fr_invite_code']")).sendKeys("2233");
    	driver.findElement(By.xpath("//button[@name='signup']")).click();
    	
    	Assert.assertTrue(driver.findElement(By.xpath("//small[@class='text-danger error-form ng-star-inserted']")).isDisplayed());
    	Assert.assertTrue(driver.findElement(By.xpath("//div[@aria-label='Your invite code is invalid, please try again!']")).isDisplayed());
	   
   }
    //@Test
    public void TC_07_Update_profile() {    	
    	driver.get("https://www.sparklepandas.com/");
    	sleepInSecond(3);
    	driver.findElement(By.xpath("//button[@class='btn-sign-in']")).click();
    	sleepInSecond(3);
    	driver.findElement(By.id("InputEmail1")).sendKeys("Mm@yopmail.com");
    	driver.findElement(By.id("input-password2")).sendKeys("Mm@yopmail.com");
    	driver.findElement(By.xpath("//button[@type='submit']")).click();
    	sleepInSecond(7);
    	driver.findElement(By.xpath("//img[@alt='avatar']")).click();
    	driver.findElement(By.xpath("//span[text()='My Account']")).click();
    	driver.findElement(By.xpath("//button[contains(text(),' Edit Profile ')]")).click();
    	
    	
    	
    	driver.findElement(By.xpath("//textarea[@formcontrolname='bio']")).sendKeys("Singer");
    	driver.findElement(By.name("name")).sendKeys("Amee");
    	
    	selectItemIn_1_DropDown(By.xpath("//div[text()='Select Gender']/parent::div//following-sibling::span[@class='ng-arrow-wrapper']"), By.xpath("//ng-select[@formcontrolname='gender']//div[@class='ng-dropdown-panel-items scroll-host']/div[2]/div"),"Female");
    	editable_dropdown(By.xpath("//input[@formcontrolname='item']"), By.xpath("//div_ngcontent_nghost-kgt-c46[@class='ng2-dropdown-menu__options-container ng-tns-c46-1 ng-trigger ng-trigger-opacity']/child"),"English");
    	driver.findElement(By.xpath("//button[contains(text(),' Save Changes ')]")).click();
    	
    }

   // @Test
    public void TC_08_Hover_menu_tiki() {
    	driver.get("https://tiki.vn/");
    	sleepInSecond(2);
    	action.moveToElement(driver.findElement(By.xpath("//span[text()='Danh Mục']"))).perform();
    	sleepInSecond(2);
    	action.moveToElement(driver.findElement(By.xpath("//ul[@class='Navigation__Wrapper-sc-knnw0g-0 cawfVW']//span[text()='Điện Thoại - Máy Tính Bảng']"))).perform();
    	sleepInSecond(2);
//    	action.moveToElement(driver.findElement(By.xpath("//span[text()='Nổi bật']"))).perform();
    	action.moveToElement(driver.findElement(By.xpath("//span[text()='Nổi bật']"))).perform();
    	
//    	action.click(driver.findElement(By.xpath("//a[text()='Oppo']"))).perform();
    	action.click(driver.findElement(By.xpath("//a[text()='iPhone 11 Pro Max 64GB']"))).perform();
    	sleepInSecond(2);
    //Verify:
    	Assert.assertEquals(driver.findElement(By.xpath("//span[text()='Điện Thoại iPhone 11 Pro Max 64GB - Hàng Chính Hãng ']")).getText(), "Điện Thoại iPhone 11 Pro Max 64GB - Hàng Chính Hãng");
    }
    
  //  @Test
    public void TC_09_Hover_Signin_tiki() {
    	driver.get("https://tiki.vn/");
    	sleepInSecond(2); 
    	action.moveToElement(driver.findElement(By.xpath("//span[text()='Đăng Nhập / Đăng Ký']"))).perform();
    	sleepInSecond(2); 

    	Assert.assertTrue(driver.findElement(By.xpath("//button[text()='Đăng nhập']")).isDisplayed());
    	Assert.assertTrue(driver.findElement(By.xpath("//button[text()='Tạo tài khoản']")).isDisplayed());
    	Assert.assertTrue(driver.findElement(By.xpath("//button[contains(string(),'Đăng nhập bằng Facebook')]")).isDisplayed());
    	Assert.assertTrue(driver.findElement(By.xpath("//button[contains(string(),'Đăng nhập bằng Google')]")).isDisplayed());
    	Assert.assertTrue(driver.findElement(By.xpath("//button[contains(string(),'Đăng nhập bằng Zalo')]")).isDisplayed());
    
    }
  //  @Test
    public void TC_10_Thai_ha_books() {
    	driver. get("https://thaihabooks.com/");
    	action.moveToElement(driver.findElement(By.xpath("//div[@class='menu']//a[contains(text(),'Tủ sách')]"))).perform();
    	action.click(driver.findElement(By.xpath("//li[@id='menu-item-39087']//a[text()='V-Parents']"))).perform();
    	sleepInSecond(3);
    	Assert.assertTrue(driver.findElement(By.xpath("//span[text()='V-Parents']")).isDisplayed());
    	
    }
     
 //  @Test
    public void TC11() {
    	driver.get("https://sellercenter.tiki.vn/new#/register?lang=vi");
   By nameTextbox = By.xpath("//input[@placeholder='Nhập đầy đủ họ tên']");
   By emailTextbox = By.xpath("//input[@placeholder='Nhập địa chỉ email']");
   By passwordTextbox =By.xpath("//input[@placeholder='Nhập mật khẩu']");
   By confirmPwTextbox = By.xpath("//div[text()='Xác nhận mật khẩu']//parent::div//following-sibling::div/input");
   By sdtTextbox = By.xpath("//input[@placeholder='Nhập số điện thoại']");
     
name = " Bui My Ky";
email = "buimyky@gmail.com";
password = "Buimyky888";
phone = "0999999123455555";
     
    //sendKey:
driver.findElement(nameTextbox).sendKeys(name);
driver.findElement(emailTextbox).sendKeys(email);
selectTikiform(By.xpath("//span[text()='Việt Nam']/parent::span/parent::div"),By.xpath("//div[@class=\"RegisterSelect__Picker-sc-1tth315-6 dyCWhu\"]"
      		+ "//div[@class='RegisterSelect__PickerItemContainer-sc-1tth315-7 bUWBfo']/div"), "Việt Nam");
sleepInSecond(2);
driver.findElement(sdtTextbox).sendKeys(phone);
selectTikiform(By.xpath("//div[text()='Chọn ngành hàng']"),By.xpath("//div[@class='RegisterSelect__Picker-sc-1tth315-6 dyCWhu']"
     		+ "//div[@class='RegisterSelect__PickerItemContainer-sc-1tth315-7 bUWBfo']/div"), "Voucher - Dịch vụ");
driver.findElement(passwordTextbox).sendKeys(password);
driver.findElement(confirmPwTextbox).sendKeys(password);
//verify invalid number:
Assert.assertEquals(driver.findElement(By.xpath("//div[text()='Số điện thoại không hợp lệ hoặc đã được sử dụng.']")).getText(), "Số điện thoại không hợp lệ hoặc đã được sử dụng.");
// verify close circle:
Assert.assertTrue(driver.findElement(By.xpath("//input[@placeholder='Nhập đầy đủ họ tên']//following-sibling::span")).isDisplayed());
Assert.assertEquals(driver.findElement(By.xpath("//input[@placeholder='Nhập đầy đủ họ tên']//following-sibling::span/span")).getAttribute("aria-label"),"check-circle");
Assert.assertEquals(driver.findElement(By.xpath("//input[@placeholder='Nhập số điện thoại']//parent::div/span/span")).getAttribute("aria-label"),"close-circle");


//     Verify error msg:

//     driver.findElement(By.xpath("//span[text()='Đăng ký ngay']")).click();
//     Assert.assertEquals(driver.findElement(By.xpath("//div[text()='Vui lòng nhập tên nhà bán.']")).getText(), "Vui lòng nhập tên nhà bán.");
//     Assert.assertEquals(driver.findElement(By.xpath("//div[text()='Email chưa đúng định dạng.']")).getText(), "Email chưa đúng định dạng.");
//     Assert.assertEquals(driver.findElement(By.xpath("//div[text()='SĐT phải chứa ít nhất 8 chữ số.']")).getText(), "SĐT phải chứa ít nhất 8 chữ số.");
//     Assert.assertEquals(driver.findElement(By.xpath("//div[text()='Vui lòng chọn danh mục bán hàng.']")).getText(), "Vui lòng chọn danh mục bán hàng.");
//     Assert.assertEquals(driver.findElement(By.xpath("//div[text()='Mật khẩu tối thiểu 6 ký tự, không được chứa khoảng trắng và phải có số hoặc ký tự đặc biệt.']")).getText(), "Mật khẩu tối thiểu 6 ký tự, không được chứa khoảng trắng và phải có số hoặc ký tự đặc biệt.");
//     Assert.assertEquals(driver.findElement(By.xpath("//div[text()='Chưa chính xác với mật khẩu đã điền.']")).getText(), "Chưa chính xác với mật khẩu đã điền.");
//
//     //   Verify alert:
//     Assert.assertTrue(driver.findElement(By.xpath("//span[text()='Vui lòng kiểm tra lại nội dung vừa nhập.']")).isDisplayed());
    }
   
   // @Test
    public void TC_12_Verify_hover_box() {
    	driver.get("https://sellercenter.tiki.vn/new#/register?lang=vi");
    	sleepInSecond(5);
    	nameTooltip = "Để tránh việc hồ sơ đăng ký bị từ chối đáng tiếc, xin Quý Nhà Bán lưu ý:\n"
    			+ "- Không điền tên công ty, tên cửa hàng hoặc tên riêng vào ô này.\n"
    			+ "- Điền họ & tên như trên CMND/Giấy Căn Cước.\n"
    			+ "- Vui lòng sử dụng tiếng Việt có dấu & viết hoa ký tự đầu tiên của mỗi từ."; 
    	emailTooltip = "Vui lòng sử dụng email chính của bạn để đảm bảo bạn không bỏ lỡ các thông tin quan trọng từ Tiki.";
    	nationTooltip="Quốc gia nơi Nhà bán đăng ký kinh doanh và có kho hàng chính.";
    	fieldTooltip = "Việc chọn đúng danh mục sẽ giúp Bộ phận hỗ trợ đối tác điều phối nhân viên có chuyên môn về ngành hàng để hỗ trợ Quý nhà bán trong tương lai.";
//    	Hover:
    action.moveToElement(driver.findElement(By.xpath("//div[contains(text(),'Điền họ và tên như trên CMND/Giấy Căn Cước.')]/span"))).perform();
	sleepInSecond(2);
    Assert.assertEquals(driver.findElement(By.xpath("//span[contains(string(),'Để tránh việc hồ sơ')]")).getText(),nameTooltip);
    
    action.moveToElement(driver.findElement(By.xpath("//div[contains(text(),'Địa chỉ email')]/span"))).perform();
    sleepInSecond(2);
    Assert.assertEquals(driver.findElement(By.xpath("//span[contains(string(),'Vui lòng sử dụng email')]")).getText(),emailTooltip);

    action.moveToElement(driver.findElement(By.xpath("//div[contains(text(),'Quốc gia')]/span"))).perform();
    sleepInSecond(2);
    Assert.assertEquals(driver.findElement(By.xpath("//span[contains(string(),'Quốc gia nơi Nhà bán')]")).getText(),nationTooltip);

    action.moveToElement(driver.findElement(By.xpath("//div[contains(text(),'Ngành hàng chính cửa hàng sẽ bán')]/span"))).perform();
    sleepInSecond(2);
    Assert.assertEquals(driver.findElement(By.xpath("//span[contains(string(),'Việc chọn đúng danh')]")).getText(),fieldTooltip);
    }
    
   @Test
   public void TC_13_Verify_color() {
	   driver.get("https://sellercenter.tiki.vn/new#/register?lang=vi");
	   sleepInSecond(3);
	   By signupButton = By.xpath("//span[text()='Đăng ký ngay']/parent::button");
	  String rgbaBlue = driver.findElement(signupButton).getCssValue("background-color"); 
	  System.out.println("RBGA = " + rgbaBlue);
	  
	  String hexaBlue = Color.fromString(rgbaBlue).asHex();
	  System.out.println ("Hexa = " + hexaBlue);
	  
	  Assert.assertEquals(hexaBlue, "#1890ff");
   }
    
    	
    
    
    public void selectTikiform(By parent, By son, String expected_one) {
    explicitWait.until(ExpectedConditions.elementToBeClickable(parent)).click();
    
    List<WebElement>allItems = driver.findElements(son);
    
    for(WebElement level: allItems) {
	   if(level.getText().trim().equals(expected_one)) {
		   if(level.isDisplayed()) {
			   level.click();
		   } else {
			   jsExecutor.executeScript("arguments[0].srcollIntoView(true);",level);
			   level.click();
		   }
		   break;
	   }
	   
   }
    }
    public void selecItemInDropdown(By parentBy, By childBy, String expectedTextItem) {
    	//1: click vao 1 item cho xo het all items
    		explicitWait.until(ExpectedConditions.elementToBeClickable(parentBy)).click();
    		
    		//2: wait cho tat ca element duoc load ra (co trong HTML/DOM)
    		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(childBy));
    		
    		 //Store lai tat ca cac element (item cua dropdown)
    		
    		List<WebElement> allItems = driver.findElements(childBy);
    		System.out.println("All items = " + allItems.size());
    		
    		for (WebElement item : allItems) {
    			if(item.getText().trim().equals(expectedTextItem)) {
    				if(item.isDisplayed()) { //3: Neu item can chon nam trong view
    					item.click();
    				} else { //4: Neu item can chon khong nhin thay thi scroll xuong va click vao
    					jsExecutor.executeScript("argurments[0].scrollIntoView(true);", item);
    					item.click();		
    			}
    				break;
    			}
    		}
    }
    
    public void selectItemIn_1_DropDown(By mother, By son, String intend_text) {
    //	1: cho cho click duoc 
    		explicitWait.until(ExpectedConditions.elementToBeClickable(mother)).click();
    //  2: wait element loaded(DOM)
    		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(son));
    //  3: Store all elements:
    		List<WebElement>rangeItems = driver.findElements(son);
    		System.out.println("rangeItems = " + rangeItems.size());
    		
    		for(WebElement item: rangeItems) {
    			if(item.getText().trim().equals(intend_text)) {
    				if(item.isDisplayed()) {
    					item.click();
    				} else { 
    					jsExecutor.executeScript("argurments[0].scrollIntoView(true);",item);
    					item.click();
    				}
    				break;
    		}
    		}
    }
    
    
    public void editable_dropdown(By tag, By mouse, String country ) {
    	driver.findElement(tag).clear();
    	driver.findElement(tag).sendKeys(country);
    	sleepInSecond(1);
    	//Store cac item cua dropdown
    	List<WebElement>countryList = driver.findElements(mouse);
    	System.out.println("Country_list = " + countryList.size());
    	
    	for (WebElement item: countryList) {
    		if(item.getText().trim().equals(country)) {
    			if(item.isDisplayed()) {
    				item.click();
    			} else {
    				jsExecutor.executeScript("argurments[0].scrollIntoView(true);",item);
    			}
    			break;
    		}
    	}
    	
    }
    public void verifyMessage (By by, String Text) {
    	driver.findElement(by).sendKeys(Text);
    	driver.findElement(by).clear();
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