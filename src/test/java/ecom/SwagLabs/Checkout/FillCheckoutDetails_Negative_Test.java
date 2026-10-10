
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

import baseClass.BaseClass;
import objRepo.LoginPage;
import ObjectRepo.ProductPage;
import Utility.TakeScreenshot;
import objRepo.CartPage;
import objRepo.CheckoutPage;

public class FillCheckoutDetails_Negative_Test extends BaseClass {

	@Test(groups = { "Integration", "Nev" })
	public void fillCheckoutDetailsNegativeTest() throws EncryptedDocumentException, IOException, InterruptedException {

		FileInputStream fis = new FileInputStream("./src/test/resources/TestData.xlsx");
		Workbook wb = WorkbookFactory.create(fis);

		try {
			String un = wb.getSheet("LoginCredentials").getRow(1).getCell(0).toString().trim();

			String pwd = wb.getSheet("LoginCredentials").getRow(1).getCell(1).toString().trim();

			String productName = wb.getSheet("Sheet1").getRow(1).getCell(0).toString().trim();

			// Read negative checkout data from Sheet3
			String firstName = wb.getSheet("Sheet3").getRow(1).getCell(0) == null ? ""
					: wb.getSheet("Sheet3").getRow(1).getCell(0).toString().trim();

			String lastName = wb.getSheet("Sheet3").getRow(1).getCell(1) == null ? ""
					: wb.getSheet("Sheet3").getRow(1).getCell(1).toString().trim();

			String postalCode = wb.getSheet("Sheet3").getRow(1).getCell(2) == null ? ""
					: wb.getSheet("Sheet3").getRow(1).getCell(2).toString().trim();

			// Login
			LoginPage loginPage = new LoginPage(driver);
			loginPage.login(un, pwd);

			Thread.sleep(1000);

			// Add product to cart
			ProductPage productsPage = new ProductPage(driver);

			Assert.assertTrue(productsPage.isProductsPageDisplayed(), "Products page is not displayed");

			productsPage.addProductToCart(productName);

			Thread.sleep(1000);

			// Open cart
			CartPage hp = new CartPage(driver);
			hp.getCart().click();

			// Click Checkout
			hp.getCheckout().click();

			Thread.sleep(1000);

			// Initialize CheckoutPage object
			CheckoutPage checkoutPage = new CheckoutPage(driver);

			// Fill negative checkout data
			checkoutPage.getFirstName().sendKeys(firstName);
			checkoutPage.getLastName().sendKeys(lastName);
			checkoutPage.getPostalCode().sendKeys(postalCode);

			// Verify entered data
			Assert.assertEquals(checkoutPage.getFirstName().getAttribute("value"), firstName,
					"First name does not match Excel data");

			Assert.assertEquals(checkoutPage.getLastName().getAttribute("value"), lastName,
					"Last name does not match Excel data");

			Assert.assertEquals(checkoutPage.getPostalCode().getAttribute("value"), postalCode,
					"Postal code does not match Excel data");

			// Click Continue
			checkoutPage.getContinueButton().click();

			Thread.sleep(1000);

			// Verify error message
			boolean errorDisplayed = driver.findElement(By.xpath("//h3[@data-test='error']")).isDisplayed();

			Assert.assertTrue(errorDisplayed, "Error message is not displayed");

			// Take screenshot after error message appears
			TakeScreenshot ts = new TakeScreenshot();
			ts.takeScreenshot(driver, "InvalidChechoutData");

			Reporter.log("Error message displayed for invalid checkout data", true);

		} finally {
			wb.close();
			fis.close();
		}
	}
}
