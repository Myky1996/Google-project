package TestNG;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeClass;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class TestNG_02_Assert {
	// Kieu nguyen thuy se co giá trị mặc định nếu chưa khởi tạo (Global)
	 Object studentAddress = null;
  @Test()
  public void TC_01() {
	  
	  String studentName = "Le Van Nam";
      //isEnabled//isDisplayed//isSelected//isMultiple
	  
	  //Verify tra ve Dung
	  Assert.assertTrue(studentName.contains("Nam"));
	  
	  //Verify tra ve Sai
	  Assert.assertFalse(studentName.contains("Trung"));
	  
	  //Verrify 2 dieu kien bang nhau
	  Assert.assertEquals(studentName,"Le Van Nam");
	  
	  Assert.assertNotEquals(studentName,("Le Van Trung"));
	  
	  //Assert.assertNull(studentName);
	  Assert.assertNull(studentAddress);
	  
	  studentAddress = "Bui My Ky";
	  
	  Assert.assertNotNull(studentAddress);
	  
  }
  

}
