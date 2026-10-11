
package ecom.SwagLabs.Product;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import baseClass.BaseClass;
<<<<<<< HEAD
import objRepo.LoginPage;
import objRepo.ProductPage;

=======
import ObjectRepo.LoginPage;
import ObjectRepo.ProductPage;
>>>>>>> 5b7f25652b05f2e95bee78314a22a550c47d4ddd

public class AddProductToCart_Test extends BaseClass {

	@Test(groups = { "Integration" })
	public void addProductToCartTest() throws EncryptedDocumentException, IOException, InterruptedException {

		FileInputStream fis = new FileInputStream("./src/test/resources/TestData.xlsx");
		Workbook wb = WorkbookFactory.create(fis);

		try {
			String un = wb.getSheet("LoginCredentials").getRow(1).getCell(0).toString().trim();

			String pwd = wb.getSheet("LoginCredentials").getRow(1).getCell(1).toString().trim();

			String productName = wb.getSheet("Sheet1").getRow(1).getCell(0).toString().trim();

			LoginPage loginPage = new LoginPage(driver);
			loginPage.login(un, pwd);

			Thread.sleep(1000);

<<<<<<< HEAD
			Assert.assertTrue(loginPage.isProductsPageDisplayed(), "Login failed");

			ProductPage productsPage = new ProductPage(driver);

=======
			Assert.assertTrue(loginPage.isProductsDisplayed(), "Login failed");

			ProductPage productsPage = new ProductPage(driver);

			Assert.assertTrue(productsPage.isProductsPageDisplayed(), "Products page is not displayed");

>>>>>>> 5b7f25652b05f2e95bee78314a22a550c47d4ddd
			Assert.assertFalse(productName.isEmpty(), "Product name is empty in Excel");

			productsPage.addProductToCart(productName);

			Thread.sleep(1000);

<<<<<<< HEAD
=======
			Assert.assertTrue(productsPage.isCartBadgeDisplayed(), "Cart badge is not displayed");

			Assert.assertEquals(productsPage.getCartCount(), "1", "Cart should contain exactly one product");

>>>>>>> 5b7f25652b05f2e95bee78314a22a550c47d4ddd
			Reporter.log("Product added to cart: " + productName, true);

		} finally {
			wb.close();
			fis.close();
		}
	}
}
