
package ecom.SwagLabs.Checkout;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

<<<<<<< HEAD
import objRepo.ProductPage;
=======
import ObjectRepo.ProductPage;
>>>>>>> 5b7f25652b05f2e95bee78314a22a550c47d4ddd
import baseClass.BaseClass;
import objRepo.LoginPage;
import objRepo.CartPage;
import objRepo.CheckoutPage;

public class CancelCheckout_Test extends BaseClass {

	@Test(groups = { "Integration" })
	public void cancelCheckoutTest() throws EncryptedDocumentException, IOException, InterruptedException {

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
<<<<<<< HEAD
			Assert.assertTrue(loginPage.isProductsPageDisplayed(), "Products page is not displayed");
=======
			Assert.assertTrue(productsPage.isProductsPageDisplayed(), "Products page is not displayed");
>>>>>>> 5b7f25652b05f2e95bee78314a22a550c47d4ddd

			productsPage.addProductToCart(productName);

			Thread.sleep(1000);

			// Open cart and checkout
			CartPage hp = new CartPage(driver);
			hp.getCart().click();
			CheckoutPage checkoutPage = new CheckoutPage(driver);
			checkoutPage.clickCheckoutButton();
			Thread.sleep(1000);

			// Cancel checkout
			checkoutPage.getCancelButton().click();

			Thread.sleep(1000);

			// Verify navigation back to cart
			Assert.assertTrue(driver.findElement(By.id("cart_contents_container")).isDisplayed(),
					"Did not navigate back to cart");

			Reporter.log("Checkout cancelled successfully", true);

		} finally {
			wb.close();
			fis.close();
		}
	}
}
