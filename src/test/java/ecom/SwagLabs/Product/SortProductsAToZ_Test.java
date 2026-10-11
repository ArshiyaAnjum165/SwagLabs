
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
import objRepo.LoginPage;
import objRepo.ProductPage;

public class SortProductsAToZ_Test extends BaseClass {

	@Test(groups = { "Integration" })
	public void sortProductsAToZTest() throws EncryptedDocumentException, IOException, InterruptedException {

		FileInputStream fis = new FileInputStream("./src/test/resources/TestData.xlsx");

		Workbook wb = WorkbookFactory.create(fis);

		String un = wb.getSheet("LoginCredentials").getRow(1).getCell(0).toString().trim();

		String pwd = wb.getSheet("LoginCredentials").getRow(1).getCell(1).toString().trim();

		LoginPage loginPage = new LoginPage(driver);
		loginPage.login(un, pwd);

		Thread.sleep(1000);

		Assert.assertTrue(loginPage.isProductsPageDisplayed(), "Login failed");

		ProductPage productsPage = new ProductPage(driver);

		productsPage.selectProductsAToZ();

		Thread.sleep(1000);

		List<String> actualNames = productsPage.getProductNames();

		List<String> expectedNames = new ArrayList<>(actualNames);
		Collections.sort(expectedNames, String.CASE_INSENSITIVE_ORDER);

		Assert.assertEquals(actualNames, expectedNames, "Products are not sorted from A to Z");

		Reporter.log("Products sorted from A to Z successfully", true);

		wb.close();
		fis.close();
	}
}
