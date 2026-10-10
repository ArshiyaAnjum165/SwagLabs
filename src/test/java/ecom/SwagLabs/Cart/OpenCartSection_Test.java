package ecom.SwagLabs.Cart;

import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import baseClass.BaseClass;
import objRepo.CartPage;
import objRepo.LoginPage;

public class OpenCartSection_Test extends BaseClass {
	@Test(groups = { "Functional", "Pos" })
	public void openCartSection_Test() throws Exception {
		LoginPage lp = new LoginPage(driver);

		FileInputStream fis = new FileInputStream("./src/test/resources/TestData.xlsx");
		Workbook wb = WorkbookFactory.create(fis);

		String un = wb.getSheet("LoginCredentials").getRow(1).getCell(0).toString();
		String pwd = wb.getSheet("LoginCredentials").getRow(1).getCell(1).toString();

		lp.getUn().clear();
		lp.getUn().sendKeys(un);
		lp.getPwd().clear();
		lp.getPwd().sendKeys(pwd);
		lp.getLoginB().click();

		Assert.assertEquals(driver.findElement(By.xpath("//a[@class='shopping_cart_link']")).isDisplayed(), true);
		Reporter.log("User logged in successfully with valid credentials", true);

		CartPage hp = new CartPage(driver);
		hp.getCart().click();
		Assert.assertEquals(driver.getCurrentUrl().contains("cart"), true);
		Reporter.log("User successfully navigated to cart section", true);

	}
}
