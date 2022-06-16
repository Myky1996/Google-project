package TestNG;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class TestNG_07_Dependencies {
  @Test()
  public void TC_01_Create_new_account() {
  }
  @Test(dependsOnMethods = "TC_01_Create_new_account")
  public void TC_02_View_account() {
  }
  @Test(dependsOnMethods = "TC_02_View_account")
  public void TC_03_Edit_account() {
  }
 

}
