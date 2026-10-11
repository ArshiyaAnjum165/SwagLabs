
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

public class VerifyAllProducts_Test extends BaseClass {

    @Test(groups = { "Integration" })
    public void verifyAllProductsTest()
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

            // Verify product count
            List<String> productNames = productsPage.getProductNames();

            Assert.assertEquals(
                    productNames.size(), 6,
                    "Expected 6 products");

            // Verify product names are not empty
            for (String name : productNames) {
                Assert.assertFalse(
                        name.trim().isEmpty(),
                        "Product name is empty");
            }

            // Verify product prices
            List<String> productPrices = productsPage.getProductPrices();

            Assert.assertEquals(
                    productPrices.size(), 6,
                    "Expected 6 product prices");

            for (String price : productPrices) {
                Assert.assertTrue(
                        price.matches("\\$\\d+\\.\\d{2}"),
                        "Invalid product price: " + price);
            }

            // Verify Name A to Z sorting
            productsPage.selectProductsAToZ();

            List<String> actualNames = productsPage.getProductNames();
            List<String> expectedNames = new ArrayList<>(actualNames);

            expectedNames.sort(String.CASE_INSENSITIVE_ORDER);

            Assert.assertEquals(
                    actualNames, expectedNames,
                    "Name A to Z sorting failed");

            // Verify Name Z to A sorting
            productsPage.selectProductsZToA();

            actualNames = productsPage.getProductNames();
            expectedNames = new ArrayList<>(actualNames);

            expectedNames.sort(
                    Collections.reverseOrder(
                            String.CASE_INSENSITIVE_ORDER));

            Assert.assertEquals(
                    actualNames, expectedNames,
                    "Name Z to A sorting failed");

            // Verify Price Low to High sorting
            productsPage.selectPriceLowToHigh();

            List<String> actualPrices = productsPage.getProductPrices();
            List<Double> expectedPrices = new ArrayList<>();

            for (String price : actualPrices) {
                expectedPrices.add(
                        Double.parseDouble(
                                price.replace("$", "").trim()));
            }

            List<Double> sortedPrices = new ArrayList<>(expectedPrices);
            Collections.sort(sortedPrices);

            Assert.assertEquals(
                    expectedPrices, sortedPrices,
                    "Price Low to High sorting failed");

            // Verify Price High to Low sorting
            productsPage.selectPriceHighToLow();

            actualPrices = productsPage.getProductPrices();
            expectedPrices = new ArrayList<>();

            for (String price : actualPrices) {
                expectedPrices.add(
                        Double.parseDouble(
                                price.replace("$", "").trim()));
            }

            sortedPrices = new ArrayList<>(expectedPrices);
            sortedPrices.sort(Collections.reverseOrder());

            Assert.assertEquals(
                    expectedPrices, sortedPrices,
                    "Price High to Low sorting failed");

            Reporter.log(
                    "Product count, names, prices and all sorting options verified successfully",
                    true);

        } finally {
            wb.close();
            fis.close();
        }
    }
}
