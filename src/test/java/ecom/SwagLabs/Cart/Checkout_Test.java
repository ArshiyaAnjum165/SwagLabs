
package ecom.SwagLabs.Cart;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
<<<<<<< HEAD
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

=======
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
>>>>>>> 5b7f25652b05f2e95bee78314a22a550c47d4ddd
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

<<<<<<< HEAD
import java.time.Duration;

import baseClass.BaseClass;
import objRepo.LoginPage;
import objRepo.ProductPage;
=======
import ObjectRepo.ProductPage;
import baseClass.BaseClass;
import objRepo.LoginPage;
>>>>>>> 5b7f25652b05f2e95bee78314a22a550c47d4ddd
import objRepo.CartPage;

public class Checkout_Test extends BaseClass {

<<<<<<< HEAD
    @Test(groups = { "Integration" })
    public void checkoutTest()
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

            WebDriverWait wait =
                    new WebDriverWait(driver, Duration.ofSeconds(10));

            // Login
            LoginPage loginPage = new LoginPage(driver);
            loginPage.login(un, pwd);

            // Wait until Products page is displayed
            ProductPage productsPage = new ProductPage(driver);

            Assert.assertTrue(
                    productsPage.isProductsPageDisplayed(),
                    "Products page is not displayed");

            // Add product to cart
            productsPage.addProductToCart(productName);

            // Verify Add to cart changed to Remove
            Assert.assertEquals(
                    productsPage.getProductButtonText(productName),
                    "Remove",
                    "Product was not added to cart");

            // Verify cart badge
            Assert.assertEquals(
                    productsPage.getCartBadgeCount(),
                    "1",
                    "Cart badge count is incorrect");

            Reporter.log(
                    productName + " added to cart successfully", true);

            // Open cart
            wait.until(ExpectedConditions.elementToBeClickable(
                    By.className("shopping_cart_link"))).click();

            // Click Checkout
            CartPage cartPage = new CartPage(driver);

            wait.until(ExpectedConditions.elementToBeClickable(
                    cartPage.getCheckout())).click();

            // Verify checkout information page
            Assert.assertTrue(
                    wait.until(ExpectedConditions.visibilityOfElementLocated(
                            By.id("checkout_info_container"))).isDisplayed(),
                    "Checkout information page is not displayed");

            Reporter.log(
                    "Checkout button clicked successfully", true);

        } finally {
            wb.close();
            fis.close();
        }
    }
=======
	@Test(groups = { "Integration" })
	public void checkoutTest() throws EncryptedDocumentException, IOException, InterruptedException {

		FileInputStream fis = new FileInputStream("./src/test/resources/TestData.xlsx");
		Workbook wb = WorkbookFactory.create(fis);

		try {
			String un = wb.getSheet("LoginCredentials").getRow(1).getCell(0).toString().trim();
			String pwd = wb.getSheet("LoginCredentials").getRow(1).getCell(1).toString().trim();
			String productName = wb.getSheet("Sheet1").getRow(1).getCell(0).toString().trim();

			// Login
			LoginPage loginPage = new LoginPage(driver);
			loginPage.login(un, pwd);

			Thread.sleep(1000);

			// Add product to cart
			ProductPage productsPage = new ProductPage(driver);

			Assert.assertTrue(productsPage.isProductsPageDisplayed(), "Products page is not displayed");

			Assert.assertFalse(productName.isEmpty(), "Product name is empty in Excel");

			productsPage.addProductToCart(productName);

			Thread.sleep(1000);

			// Open cart
			driver.findElement(By.className("shopping_cart_link")).click();

			Thread.sleep(1000);

			// Click Checkout
			CartPage cartPage = new CartPage(driver);
			cartPage.getCheckout().click();

			Thread.sleep(1000);

			// Verify checkout information page
			Assert.assertTrue(driver.findElement(By.id("checkout_info_container")).isDisplayed(),
					"Checkout information page is not displayed");

			Reporter.log("Checkout button clicked successfully", true);

		} finally {
			wb.close();
			fis.close();
		}
	}
>>>>>>> 5b7f25652b05f2e95bee78314a22a550c47d4ddd
}
