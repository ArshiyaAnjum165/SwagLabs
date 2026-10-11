package ecom.SwagLabs.HamburgerMenu;

import java.io.FileInputStream;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import baseClass.BaseClass;
<<<<<<< HEAD
import objRepo.LoginPage;
import objRepo.HamburgerMenuPage;
=======
import ObjectRepo.LoginPage;
import ObjectRepo.HamburgerMenuPage;
>>>>>>> 5b7f25652b05f2e95bee78314a22a550c47d4ddd

public class ResetAppStateTest extends BaseClass {

	@Test(groups = { "Functional" })
	public void resetAppStateTest() throws Exception {

		FileInputStream fis = new FileInputStream("./src/test/resources/TestData.xlsx");
		Workbook wb = WorkbookFactory.create(fis);

		try {
			String un = wb.getSheet("LoginCredentials").getRow(1).getCell(0).toString().trim();

			String pwd = wb.getSheet("LoginCredentials").getRow(1).getCell(1).toString().trim();

			LoginPage loginPage = new LoginPage(driver);
			loginPage.login(un, pwd);

			Thread.sleep(1000);

<<<<<<< HEAD
			Assert.assertTrue(loginPage.isProductsPageDisplayed(), "Login failed");
=======
			Assert.assertTrue(loginPage.isProductsDisplayed(), "Login failed");
>>>>>>> 5b7f25652b05f2e95bee78314a22a550c47d4ddd

			HamburgerMenuPage menuPage = new HamburgerMenuPage(driver);

			menuPage.clickMenuButton();
			Thread.sleep(1000);

			Assert.assertTrue(menuPage.isResetAppStateDisplayed(), "Reset App State option is not displayed");

			Reporter.log("Reset App State option is displayed", true);

		} finally {
			wb.close();
			fis.close();
		}
	}
}