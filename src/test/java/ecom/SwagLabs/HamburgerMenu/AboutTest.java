
package ecom.SwagLabs.HamburgerMenu;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import baseClass.BaseClass;
import objRepo.LoginPage;
import objRepo.HamburgerMenuPage;

public class AboutTest extends BaseClass {

	@Test(groups = { "Integration" })
	public void aboutTest() throws InterruptedException, EncryptedDocumentException, IOException {

		Reporter.log("========== ABOUT TEST ==========", true);

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

			// Open menu
			menuPage.clickMenuButton();
			Thread.sleep(1000);

			// Verify About is displayed
			Assert.assertTrue(menuPage.isAboutDisplayed(), "About option is not displayed");

			Reporter.log("About option is displayed successfully", true);

			// Close menu
			menuPage.clickCloseMenu();

			Reporter.log("========== ABOUT TEST PASSED ==========", true);

		} finally {
			wb.close();
			fis.close();
		}
	}
}
