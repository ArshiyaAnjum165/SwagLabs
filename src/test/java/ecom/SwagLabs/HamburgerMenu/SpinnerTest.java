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

public class SpinnerTest extends BaseClass {

	@Test(groups = { "Functional" })
	public void spinnerTest() throws Exception {

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
			menuPage.clickDynamicCatalog();

			menuPage.clickSpinner();
			Thread.sleep(2000);

			Reporter.log("Spinner option clicked successfully", true);

		} finally {
			wb.close();
			fis.close();
		}
	}
}