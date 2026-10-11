package ecom.SwagLabs.HamburgerMenu;

import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import baseClass.BaseClass;
import objRepo.LoginPage;
import objRepo.HamburgerMenuPage;

public class LogoutTest extends BaseClass {

	@Test(groups = { "Functional", "Smoke" })
	public void logoutTest() throws Exception {

		FileInputStream fis = new FileInputStream("./src/test/resources/TestData.xlsx");

		Workbook wb = WorkbookFactory.create(fis);

		try {
			String un = wb.getSheet("LoginCredentials").getRow(1).getCell(0).toString().trim();

			String pwd = wb.getSheet("LoginCredentials").getRow(1).getCell(1).toString().trim();

			LoginPage loginPage = new LoginPage(driver);
			loginPage.login(un, pwd);

			Thread.sleep(1000);

			Assert.assertTrue(loginPage.isProductsPageDisplayed(), "Login failed");

			HamburgerMenuPage menuPage = new HamburgerMenuPage(driver);

			menuPage.clickMenuButton();
			Thread.sleep(1000);

			Assert.assertTrue(menuPage.isLogoutDisplayed(), "Logout option is not displayed");

			Reporter.log("Logout option is displayed", true);

			menuPage.clickLogout();
			Thread.sleep(1000);

			Assert.assertTrue(driver.findElement(By.id("login-button")).isDisplayed(), "Logout failed");

			Reporter.log("User logged out successfully", true);

		} finally {
			wb.close();
			fis.close();
		}
	}
}