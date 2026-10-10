
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
import ObjectRepo.LoginPage;
import ObjectRepo.ProductPage;

public class SortPriceHighToLow_Test extends BaseClass {

	@Test(groups = { "Integration" })
	public void sortPriceHighToLowTest() throws EncryptedDocumentException, IOException, InterruptedException {

		FileInputStream fis = new FileInputStream("./src/test/resources/TestData.xlsx");

		Workbook wb = WorkbookFactory.create(fis);

		String un = wb.getSheet("LoginCredentials").getRow(1).getCell(0).toString().trim();

		String pwd = wb.getSheet("LoginCredentials").getRow(1).getCell(1).toString().trim();

		LoginPage loginPage = new LoginPage(driver);
		loginPage.login(un, pwd);

		Thread.sleep(1000);

		Assert.assertTrue(loginPage.isProductsDisplayed(), "Login failed");

		ProductPage productsPage = new ProductPage(driver);

		productsPage.selectPriceHighToLow();

		Thread.sleep(1000);

		List<String> actualPrices = productsPage.getProductPrices();
		List<Double> actualValues = new ArrayList<>();

		for (String price : actualPrices) {
			actualValues.add(Double.parseDouble(price.replace("$", "").trim()));
		}

		List<Double> expectedValues = new ArrayList<>(actualValues);

		expectedValues.sort(Collections.reverseOrder());

		Assert.assertEquals(actualValues, expectedValues, "Products are not sorted by price from high to low");

		Reporter.log("Products sorted by price: High to Low successfully", true);

		wb.close();
		fis.close();
	}
}
