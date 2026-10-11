
package ecom.SwagLabs.Checkout;

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
import objRepo.CheckoutPage;

public class CancelCheckout_Test extends BaseClass {

    @Test(groups = { "Integration" })
    public void cancelCheckoutTest()
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

            // Open cart and checkout
            CartPage hp = new CartPage(driver);
            hp.getCart().click();

            CheckoutPage checkoutPage = new CheckoutPage(driver);
            checkoutPage.clickCheckoutButton();

            // Cancel checkout
            checkoutPage.getCancelButton().click();

            // Verify navigation back to cart
            Assert.assertTrue(
                    driver.findElement(
                            By.id("cart_contents_container")).isDisplayed(),
                    "Did not navigate back to cart");

            Reporter.log(
                    "Checkout cancelled successfully", true);

        } finally {
            wb.close();
            fis.close();
        }
    }
}
