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

public class TestNG_03_Group {
	// Kieu nguyen thuy se co giá trị mặc định nếu chưa khởi tạo (Global)
	 Object studentAddress = null;
  @BeforeClass(alwaysRun = true)
  public void beforeClass() {
  }
  
  @Test(groups = {"intergration","regression"}) 
  public void TC_01_Get_Student_By_name() {
	  
  }
  
  @Test(groups = "regression") 
  public void TC_02_Update_student_by_iD() {
	  
  }
  
  @Test(groups = {"intergration","regression"}) 
  public void TC_03_Delete_student_by_iD() {
  }
  @AfterClass(alwaysRun = true)
  public void afterClass() {
	  System.out.println("Run after class");
  }
  }
