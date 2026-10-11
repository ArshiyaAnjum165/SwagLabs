
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
import objRepo.CheckoutPage;
import objRepo.CartPage;
import objRepo.CheckoutOverviewPage;
import objRepo.ThankYouPage;

public class GeneratePDFOrder_Test extends BaseClass {

    @Test(groups = { "Integration", "System" })
    public void generatePDFOrderTest()
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

            // Continue to Checkout Overview
            checkoutPage.getContinueButton().click();

            // Finish order
            CheckoutOverviewPage overviewPage =
                    new CheckoutOverviewPage(driver);

            overviewPage.getFinishButton().click();

            // Verify Thank You page
            ThankYouPage thankYouPage = new ThankYouPage(driver);

            Assert.assertEquals(
                    thankYouPage.getThankYouMessage().getText(),
                    "Thank you for your order!",
                    "Thank You page is not displayed");

            // Click Generate PDF order
            thankYouPage.getGeneratePDFButton().click();

            Reporter.log(
                    "Generate PDF order button clicked successfully", true);

        } finally {
            wb.close();
            fis.close();
        }
    }
}
