package ecom.SwagLabs.HamburgerMenu;

import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import baseClass.BaseClass;
import objRepo.LoginPage;
import objRepo.HamburgerMenuPage;

public class ResetAppStateTest extends BaseClass {

    @Test(groups = { "Functional" })
    public void resetAppStateTest() throws Exception {

        FileInputStream fis = new FileInputStream(
                "./src/test/resources/TestData.xlsx");

        Workbook wb = null;

        try {
            wb = WorkbookFactory.create(fis);

            DataFormatter formatter = new DataFormatter();

            String un = formatter.formatCellValue(
                    wb.getSheet("LoginCredentials")
                      .getRow(1).getCell(0)).trim();

            String pwd = formatter.formatCellValue(
                    wb.getSheet("LoginCredentials")
                      .getRow(1).getCell(1)).trim();

            // Login
            LoginPage loginPage = new LoginPage(driver);
            loginPage.login(un, pwd);

            // Verify successful login
            Assert.assertTrue(
                    loginPage.isProductsPageDisplayed(),
                    "Login failed - Products page is not displayed");

            Reporter.log("Login successful", true);

            // Open hamburger menu
            HamburgerMenuPage menuPage =
                    new HamburgerMenuPage(driver);

            menuPage.clickMenuButton();

            Reporter.log("Hamburger menu opened", true);

            // Verify Reset App State option
            Assert.assertTrue(
                    menuPage.isResetAppStateDisplayed(),
                    "Reset App State option is not displayed");

            Reporter.log(
                    "Reset App State option is displayed", true);

        } finally {
            try {
                if (wb != null) {
                    wb.close();
                }
            } finally {
                fis.close();
            }
        }
    }
}
