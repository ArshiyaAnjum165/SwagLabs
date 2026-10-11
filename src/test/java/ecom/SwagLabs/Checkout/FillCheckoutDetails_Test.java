
package ecom.SwagLabs.Checkout;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import baseClass.BaseClass;
import objRepo.LoginPage;
import objRepo.ProductPage;
import objRepo.CartPage;
import objRepo.CheckoutPage;

public class FillCheckoutDetails_Test extends BaseClass {

    @Test(groups = { "Integration" })
    public void fillCheckoutDetailsTest()
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

            // Read checkout data from Sheet2
            String firstName = formatter.formatCellValue(
                    wb.getSheet("Sheet2")
                      .getRow(1).getCell(0)).trim();

            String lastName = formatter.formatCellValue(
                    wb.getSheet("Sheet2")
                      .getRow(1).getCell(1)).trim();

            String postalCode = formatter.formatCellValue(
                    wb.getSheet("Sheet2")
                      .getRow(1).getCell(2)).trim();

            // Login
            LoginPage loginPage = new LoginPage(driver);
            loginPage.login(un, pwd);

            // Verify Products page
            ProductPage productsPage = new ProductPage(driver);

            Assert.assertTrue(
                    productsPage.isProductsPageDisplayed(),
                    "Products page is not displayed");

            Assert.assertFalse(
                    productName.isEmpty(),
                    "Product name is empty in Excel");

            // Add product to cart
            productsPage.addProductToCart(productName);

            // Open cart and checkout
            CartPage hp = new CartPage(driver);
            hp.getCart().click();

            CheckoutPage checkoutPage = new CheckoutPage(driver);
            checkoutPage.clickCheckoutButton();

            // Fill checkout details
            checkoutPage.getFirstName().sendKeys(firstName);
            checkoutPage.getLastName().sendKeys(lastName);
            checkoutPage.getPostalCode().sendKeys(postalCode);

            // Verify entered details
            Assert.assertEquals(
                    checkoutPage.getFirstName().getAttribute("value"),
                    firstName,
                    "First name is incorrect");

            Assert.assertEquals(
                    checkoutPage.getLastName().getAttribute("value"),
                    lastName,
                    "Last name is incorrect");

            Assert.assertEquals(
                    checkoutPage.getPostalCode().getAttribute("value"),
                    postalCode,
                    "Postal code is incorrect");

            Reporter.log(
                    "Checkout details filled successfully", true);

        } finally {
            wb.close();
            fis.close();
        }
    }
}
