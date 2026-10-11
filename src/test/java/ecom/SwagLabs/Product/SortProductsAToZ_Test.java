
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

public class SortProductsAToZ_Test extends BaseClass {

    @Test(groups = { "Integration" })
    public void sortProductsAToZTest()
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

            // Sort products A to Z
            productsPage.selectProductsAToZ();

            // Get actual product names
            List<String> actualNames = productsPage.getProductNames();

            // Prepare expected alphabetical order
            List<String> expectedNames =
                    new ArrayList<>(actualNames);

            Collections.sort(
                    expectedNames,
                    String.CASE_INSENSITIVE_ORDER);

            // Verify sorting
            Assert.assertEquals(
                    actualNames,
                    expectedNames,
                    "Products are not sorted from A to Z");

            Reporter.log(
                    "Products sorted from A to Z successfully", true);

        } finally {
            wb.close();
            fis.close();
        }
    }
}
