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
import objRepo.ProductPage;

public class ProductAddedToCart_Test extends BaseClass {
	@Test(groups = { "Integration", "Pos" })
	public void verifyProductAddedToCart_Test() throws Exception {
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

		ProductPage pp = new ProductPage(driver);
		pp.selectingSorting("Price (low to high)");

		String productName = "Sauce Labs Bike Light";
		driver.findElement(By.xpath("//div[text()='" + productName
				+ "']/ancestor::div[@class='inventory_item_description']/descendant::button")).click();

		CartPage hp = new CartPage(driver);
		hp.getCart().click();

		Thread.sleep(3000);
		Assert.assertEquals(driver.findElement(By.xpath("//div[text()='" + productName + "']")).isDisplayed(), true);
		Reporter.log("Product Added successfully", true);
	}
}
