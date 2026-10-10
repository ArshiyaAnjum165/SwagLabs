
package ecom.SwagLabs.HamburgerMenu;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import baseClass.BaseClass;
import objRepo.LoginPage;
import objRepo.HamburgerMenuPage;

public class CloseMenuTest extends BaseClass {

	@Test(groups = { "Integration" })
	public void closeMenuTest() throws InterruptedException, EncryptedDocumentException, IOException {

		Reporter.log("========== CLOSE MENU TEST ==========", true);

		FileInputStream fis = new FileInputStream("./src/test/resources/TestData.xlsx");

		Workbook wb = WorkbookFactory.create(fis);

		String un = wb.getSheet("LoginCredentials").getRow(1).getCell(0).toString().trim();

		String pwd = wb.getSheet("LoginCredentials").getRow(1).getCell(1).toString().trim();

		LoginPage loginPage = new LoginPage(driver);
		loginPage.login(un, pwd);

		Thread.sleep(1000);

		Assert.assertTrue(loginPage.isProductsPageDisplayed(), "Login failed");
		HamburgerMenuPage menuPage = new HamburgerMenuPage(driver);

		// Open menu
		menuPage.clickMenuButton();
		Thread.sleep(1000);

		Assert.assertTrue(driver.findElements(By.id("inventory_sidebar_link")).size() > 0, "Menu did not open");

		// Close menu
		menuPage.clickCloseMenu();
		Thread.sleep(3000);

		Assert.assertTrue(driver.findElement(By.id("react-burger-menu-btn")).isDisplayed(),
				"Hamburger menu button is not displayed after closing");

		Reporter.log("Hamburger menu closed successfully", true);
	}
}
