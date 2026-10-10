
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

import ObjectRepo.ProductPage;
import baseClass.BaseClass;
import objRepo.LoginPage;
import objRepo.CheckoutPage;
import objRepo.CartPage;
import objRepo.CheckoutOverviewPage;
import objRepo.ThankYouPage;

public class GeneratePDFOrder_Test extends BaseClass {

	@Test(groups = { "Integration", "System" })
	public void generatePDFOrderTest() throws EncryptedDocumentException, IOException, InterruptedException {

		FileInputStream fis = new FileInputStream("./src/test/resources/TestData.xlsx");
		Workbook wb = WorkbookFactory.create(fis);

		try {
			String un = wb.getSheet("LoginCredentials").getRow(1).getCell(0).toString().trim();
			String pwd = wb.getSheet("LoginCredentials").getRow(1).getCell(1).toString().trim();
			String productName = wb.getSheet("Sheet1").getRow(1).getCell(0).toString().trim();

			String firstName = wb.getSheet("Sheet2").getRow(1).getCell(0).toString().trim();
			String lastName = wb.getSheet("Sheet2").getRow(1).getCell(1).toString().trim();
			String postalCode = wb.getSheet("Sheet2").getRow(1).getCell(2).toString().trim();

			// Login
			LoginPage loginPage = new LoginPage(driver);
			loginPage.login(un, pwd);

			Thread.sleep(1000);

			// Add product to cart
			ProductPage productsPage = new ProductPage(driver);
			Assert.assertTrue(productsPage.isProductsPageDisplayed(), "Products page is not displayed");

			productsPage.addProductToCart(productName);

			Thread.sleep(1000);

			// Open cart and checkout
			CartPage hp = new CartPage(driver);
			hp.getCart().click();
			CheckoutPage checkoutPage = new CheckoutPage(driver);
			checkoutPage.clickCheckoutButton();
			Thread.sleep(1000);

			// Fill checkout details
			checkoutPage.getFirstName().sendKeys(firstName);
			checkoutPage.getLastName().sendKeys(lastName);
			checkoutPage.getPostalCode().sendKeys(postalCode);

			checkoutPage.getContinueButton().click();
			Thread.sleep(1000);

			// Finish order
			CheckoutOverviewPage overviewPage = new CheckoutOverviewPage(driver);
			overviewPage.getFinishButton().click();

			Thread.sleep(1000);

			// Verify Thank You page
			ThankYouPage thankYouPage = new ThankYouPage(driver);

			Assert.assertEquals(thankYouPage.getThankYouMessage().getText(), "Thank you for your order!",
					"Thank You page is not displayed");

			// Click Generate PDF order
			thankYouPage.getGeneratePDFButton().click();

			Thread.sleep(1000);

			Reporter.log("Generate PDF order button clicked successfully", true);

		} finally {
			wb.close();
			fis.close();
		}
	}
}
