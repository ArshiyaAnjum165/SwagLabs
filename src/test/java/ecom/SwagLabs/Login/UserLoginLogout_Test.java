
package ecom.SwagLabs.Login;

import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import baseClass.BaseClass;
import objRepo.HamburgerMenuPage;
import objRepo.LoginPage;

public class UserLoginLogout_Test extends BaseClass {

	@Test(groups = { "System", "Pos" })
	public void userLoginLogout_Test() throws Exception {

		LoginPage lp = new LoginPage(driver);

		FileInputStream fis = new FileInputStream("./src/test/resources/TestData.xlsx");
		Workbook wb = WorkbookFactory.create(fis);

		try {
			String un = wb.getSheet("LoginCredentials").getRow(1).getCell(0).toString().trim();
			String pwd = wb.getSheet("LoginCredentials").getRow(1).getCell(1).toString().trim();

			// Login
			lp.login(un, pwd);

			Assert.assertTrue(driver.getCurrentUrl().contains("inventory"), "Login failed");

			Reporter.log("User logged in successfully with valid credentials", true);

			// Open hamburger menu
			HamburgerMenuPage hp = new HamburgerMenuPage(driver);
			hp.clickMenuButton();

			// Click Logout
			hp.clickLogout();

			// Verify logout
			Assert.assertTrue(driver.findElement(org.openqa.selenium.By.id("login-button")).isDisplayed(),
					"User logout failed");

			Reporter.log("User logged out successfully", true);

		} finally {
			wb.close();
			fis.close();
		}
	}
}
