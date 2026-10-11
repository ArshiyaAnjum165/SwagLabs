
package ecom.SwagLabs.Cart;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import baseClass.BaseClass;
import objRepo.LoginPage;
import objRepo.ProductPage;
import objRepo.CartPage;

public class ContinueShopping_Test extends BaseClass {

    @Test(groups = { "Integration" })
    public void continueShoppingTest()
            throws EncryptedDocumentException, IOException {

        FileInputStream fis =
                new FileInputStream("./src/test/resources/TestData.xlsx");

        Workbook wb = WorkbookFactory.create(fis);

        try {
            DataFormatter formatter = new DataFormatter();

            String un = formatter.formatCellValue(
                    wb.getSheet("LoginCredentials")
                      .getRow(1).getCell(0)).trim();

            String pwd = formatter.formatCellValue(
                    wb.getSheet("LoginCredentials")
                      .getRow(1).getCell(1)).trim();

            String productName = formatter.formatCellValue(
                    wb.getSheet("Sheet1")
                      .getRow(1).getCell(0)).trim();

            Assert.assertFalse(un.isEmpty(),
                    "Username is empty in Excel");

            Assert.assertFalse(pwd.isEmpty(),
                    "Password is empty in Excel");

            Assert.assertFalse(productName.isEmpty(),
                    "Product name is empty in Excel");

            // Login
            LoginPage loginPage = new LoginPage(driver);
            loginPage.login(un, pwd);

            // Verify Products page
            ProductPage productsPage = new ProductPage(driver);

            Assert.assertTrue(
                    productsPage.isProductsPageDisplayed(),
                    "Products page is not displayed");

            // Add product to cart
            productsPage.addProductToCart(productName);

            // Open cart
            driver.findElement(
                    By.className("shopping_cart_link")).click();

            // Click Continue Shopping
            CartPage cartPage = new CartPage(driver);
            cartPage.getContinueS().click();

            // Verify navigation to Products page
            Assert.assertTrue(
                    productsPage.isProductsPageDisplayed(),
                    "Continue Shopping did not navigate to Products page");

            Reporter.log(
                    "Continue Shopping clicked successfully", true);

        } finally {
            wb.close();
            fis.close();
        }
    }
}
