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

public class TestNG_04_Priority {
	@Test(priority = 1)
	public void Create_New_account() {
		
	}
	@Test(description = "Customer can view an account", priority = 3)
	public void View_account() {
		
	}
	@Test(priority = 2)
	public void Edit_account() {
		
	}
	@Test(priority = 4)
	public void Move_account() {
		
	}
	@Test(priority = 5)
	public void Delete_account() {
	}
  }
