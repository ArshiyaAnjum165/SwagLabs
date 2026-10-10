
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

public class OpenMenuTest extends BaseClass {

	@Test(groups = { "Functional", "Smoke" })
	public void openMenuTest() throws InterruptedException, EncryptedDocumentException, IOException {

		Reporter.log("========== OPEN MENU TEST ==========", true);

		FileInputStream fis = new FileInputStream("./src/test/resources/TestData.xlsx");

		Workbook wb = WorkbookFactory.create(fis);

		String un = wb.getSheet("LoginCredentials").getRow(1).getCell(0).toString().trim();

		String pwd = wb.getSheet("LoginCredentials").getRow(1).getCell(1).toString().trim();

		LoginPage loginPage = new LoginPage(driver);
		loginPage.login(un, pwd);

		Thread.sleep(1000);

		Assert.assertTrue(loginPage.isProductsPageDisplayed(), "Login failed");
		HamburgerMenuPage menuPage = new HamburgerMenuPage(driver);

		menuPage.clickMenuButton();

		Thread.sleep(1000);

		Assert.assertTrue(driver.findElements(By.id("inventory_sidebar_link")).size() > 0,
				"Hamburger menu did not open");

		Reporter.log("Hamburger menu opened successfully", true);

		menuPage.clickCloseMenu();

		Reporter.log("========== OPEN MENU TEST PASSED ==========", true);
	}
}
