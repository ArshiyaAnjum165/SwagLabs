
package ecom.SwagLabs.Product;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

public class SortPriceLowToHigh_Test extends BaseClass {

	@Test(groups = { "Integration" })
	public void sortPriceLowToHighTest() throws EncryptedDocumentException, IOException, InterruptedException {

		FileInputStream fis = new FileInputStream("./src/test/resources/TestData.xlsx");

		Workbook wb = WorkbookFactory.create(fis);

		String un = wb.getSheet("LoginCredentials").getRow(1).getCell(0).toString().trim();

		String pwd = wb.getSheet("LoginCredentials").getRow(1).getCell(1).toString().trim();

		LoginPage loginPage = new LoginPage(driver);
		loginPage.login(un, pwd);

		Thread.sleep(1000);

<<<<<<< HEAD
		Assert.assertTrue(loginPage.isProductsPageDisplayed(), "Login failed");
=======
		Assert.assertTrue(loginPage.isProductsDisplayed(), "Login failed");
>>>>>>> 5b7f25652b05f2e95bee78314a22a550c47d4ddd

		ProductPage productsPage = new ProductPage(driver);

		productsPage.selectPriceLowToHigh();

		Thread.sleep(1000);

		List<String> actualPrices = productsPage.getProductPrices();

		List<Double> actualValues = new ArrayList<>();

		for (String price : actualPrices) {
			actualValues.add(Double.parseDouble(price.replace("$", "").trim()));
		}

		List<Double> expectedValues = new ArrayList<>(actualValues);
		Collections.sort(expectedValues);

		Assert.assertEquals(actualValues, expectedValues, "Products are not sorted by price from low to high");

		Reporter.log("Products sorted by price: Low to High successfully", true);

		wb.close();
		fis.close();
	}
}
