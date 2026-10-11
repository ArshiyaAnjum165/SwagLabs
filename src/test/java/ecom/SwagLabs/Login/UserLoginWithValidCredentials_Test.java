package ecom.SwagLabs.Login;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import Utility.ExcelUtility;
import baseClass.BaseClass;
import objRepo.LoginPage;

public class UserLoginWithValidCredentials_Test extends BaseClass {
	@Test(groups = { "Smoke", "Pos" })
	public void userLoginWithValidCredentials_Test() throws Exception {
		LoginPage lp = new LoginPage(driver);
		ExcelUtility eu = new ExcelUtility();

		lp.login(eu.readDataFromExcel("LoginCredentials", 1, 0), eu.readDataFromExcel("LoginCredentials", 1, 1));
		Assert.assertEquals(driver.findElement(By.xpath("//a[@class='shopping_cart_link']")).isDisplayed(), true);
		Reporter.log("User logged in successfully with valid credentials", true);
	}

}