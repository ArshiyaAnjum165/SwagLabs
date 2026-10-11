
package ecom.SwagLabs.Cart;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import objRepo.ProductPage;
import baseClass.BaseClass;
import objRepo.LoginPage;
import objRepo.CartPage;

public class ContinueShopping_Test extends BaseClass {

	@Test(groups = { "Integration" })
	public void continueShoppingTest() throws EncryptedDocumentException, IOException, InterruptedException {

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

			Assert.assertFalse(productName.isEmpty(), "Product name is empty in Excel");

			productsPage.addProductToCart(productName);

			Thread.sleep(1000);

			// Open cart
			driver.findElement(By.className("shopping_cart_link")).click();

			Thread.sleep(1000);

			// Click Continue Shopping
			CartPage cartPage = new CartPage(driver);
			cartPage.getContinueS().click();

			Thread.sleep(1000);

			Reporter.log("Continue Shopping clicked successfully", true);

		} finally {
			wb.close();
			fis.close();
		}
	}
}
