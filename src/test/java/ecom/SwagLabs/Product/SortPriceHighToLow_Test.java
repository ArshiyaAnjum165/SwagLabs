
package ecom.SwagLabs.Product;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

public class SortPriceHighToLow_Test extends BaseClass {

    @Test(groups = { "Integration" })
    public void sortPriceHighToLowTest()
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

            // Login
            LoginPage loginPage = new LoginPage(driver);
            loginPage.login(un, pwd);

            // Verify Products page
            ProductPage productsPage = new ProductPage(driver);

            Assert.assertTrue(
                    productsPage.isProductsPageDisplayed(),
                    "Products page is not displayed");

            // Select Price: High to Low
            productsPage.selectPriceHighToLow();

            // Get product prices
            List<String> actualPrices = productsPage.getProductPrices();

            List<Double> actualValues = new ArrayList<>();

            for (String price : actualPrices) {
                actualValues.add(
                        Double.parseDouble(
                                price.replace("$", "").trim()));
            }

            // Expected descending order
            List<Double> expectedValues =
                    new ArrayList<>(actualValues);

            expectedValues.sort(Collections.reverseOrder());

            // Verify sorting
            Assert.assertEquals(
                    actualValues,
                    expectedValues,
                    "Products are not sorted by price from high to low");

            Reporter.log(
                    "Products sorted by price: High to Low successfully",
                    true);

        } finally {
            wb.close();
            fis.close();
        }
    }
}
