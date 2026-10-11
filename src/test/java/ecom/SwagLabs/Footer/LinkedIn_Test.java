
package ecom.SwagLabs.Footer;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Set;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import baseClass.BaseClass;
import objRepo.LoginPage;
import objRepo.ContactPage;

public class LinkedIn_Test extends BaseClass {

	@Test(groups = { "Functional" })
	public void linkedInTest() throws EncryptedDocumentException, IOException, InterruptedException {

		FileInputStream fis = new FileInputStream("./src/test/resources/TestData.xlsx");
		Workbook wb = WorkbookFactory.create(fis);

		try {
			String un = wb.getSheet("LoginCredentials").getRow(1).getCell(0).toString().trim();
			String pwd = wb.getSheet("LoginCredentials").getRow(1).getCell(1).toString().trim();

			// Login
			LoginPage loginPage = new LoginPage(driver);
			loginPage.login(un, pwd);

			Thread.sleep(1000);

			// Scroll to bottom
			((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");

			Thread.sleep(1000);

			// Click LinkedIn link
			String parentWindow = driver.getWindowHandle();

			ContactPage contactPage = new ContactPage(driver);
			contactPage.getLinkedInLink().click();

			Thread.sleep(2000);

			// Switch to new tab if opened
			Set<String> windows = driver.getWindowHandles();

			for (String window : windows) {
				if (!window.equals(parentWindow)) {
					driver.switchTo().window(window);
					break;
				}
			}

			// Verify LinkedIn page
			Assert.assertTrue(driver.getCurrentUrl().contains("linkedin.com"), "LinkedIn page is not opened");

			Reporter.log("LinkedIn link verified successfully", true);

		} finally {
			wb.close();
			fis.close();
		}
	}
}
