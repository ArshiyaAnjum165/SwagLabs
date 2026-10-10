
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

public class AllItemsTest extends BaseClass {

	@Test(groups = { "Integration" })
	public void allItemsTest() throws InterruptedException, EncryptedDocumentException, IOException {

		Reporter.log("========== ALL ITEMS TEST ==========", true);

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
			// Click All Items
			menuPage.clickAllItems();
			Thread.sleep(1000);

			Assert.assertTrue(loginPage.isProductsPageDisplayed(),
					"Products page is not displayed after clicking All Items");

			Reporter.log("All Items navigation verified successfully", true);

		} finally {
			wb.close();
			fis.close();
		}
	}
}
