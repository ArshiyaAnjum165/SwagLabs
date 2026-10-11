package ecom.SwagLabs.HamburgerMenu;

import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import baseClass.BaseClass;
import objRepo.LoginPage;
import objRepo.HamburgerMenuPage;

public class ResetAppStateActionTest extends BaseClass {

	@Test(groups = { "Functional", "Integration" })
	public void resetAppStateActionTest() throws Exception {

		FileInputStream fis = new FileInputStream("./src/test/resources/TestData.xlsx");
		Workbook wb = WorkbookFactory.create(fis);

		try {
			String un = wb.getSheet("LoginCredentials").getRow(1).getCell(0).toString().trim();

			String pwd = wb.getSheet("LoginCredentials").getRow(1).getCell(1).toString().trim();

			LoginPage loginPage = new LoginPage(driver);
			loginPage.login(un, pwd);

			Thread.sleep(1000);

			Assert.assertTrue(loginPage.isProductsPageDisplayed(), "Login failed");

			// Add a product to the cart
			driver.findElement(org.openqa.selenium.By.id("add-to-cart-sauce-labs-backpack")).click();

			Thread.sleep(1000);

			// Open hamburger menu and reset app state
			HamburgerMenuPage menuPage = new HamburgerMenuPage(driver);
			menuPage.clickMenuButton();

			Thread.sleep(1000);

			menuPage.resetAppStateClick();

			Thread.sleep(1000);

			// Verify cart badge is removed
			Assert.assertTrue(driver.findElements(org.openqa.selenium.By.className("shopping_cart_badge")).isEmpty(),
					"Cart was not reset");

			Reporter.log("App state reset successfully", true);

		} finally {
			wb.close();
			fis.close();
		}
	}
}