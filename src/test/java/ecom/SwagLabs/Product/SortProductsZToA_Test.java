
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

public class SortProductsZToA_Test extends BaseClass {

    @Test(groups = { "Integration" })
    public void sortProductsZToATest()
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

            // Sort products Z to A
            productsPage.selectProductsZToA();

            // Get actual product names
            List<String> actualNames = productsPage.getProductNames();

            // Prepare expected reverse alphabetical order
            List<String> expectedNames =
                    new ArrayList<>(actualNames);

            expectedNames.sort(
                    Collections.reverseOrder(
                            String.CASE_INSENSITIVE_ORDER));

            // Verify sorting
            Assert.assertEquals(
                    actualNames,
                    expectedNames,
                    "Products are not sorted from Z to A");

            Reporter.log(
                    "Products sorted from Z to A successfully", true);

        } finally {
            wb.close();
            fis.close();
        }
    }
}
