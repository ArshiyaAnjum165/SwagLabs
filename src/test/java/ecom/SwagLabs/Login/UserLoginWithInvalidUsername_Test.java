package ecom.SwagLabs.Login;

import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import Utility.TakeScreenshot;
import baseClass.BaseClass;
import objRepo.LoginPage;

public class UserLoginWithInvalidUsername_Test extends BaseClass {
	@Test(groups = { "Functional", "Nev" })
	public void userLoginWithInvalidUsername_Test() throws Exception {
		LoginPage lp = new LoginPage(driver);

		FileInputStream fis = new FileInputStream("./src/test/resources/TestData.xlsx");
		Workbook wb = WorkbookFactory.create(fis);

		String un = wb.getSheet("InvalidCredentials").getRow(1).getCell(0).toString();
		String pwd = wb.getSheet("InvalidCredentials").getRow(1).getCell(1).toString();

		lp.login(un, pwd);

		Assert.assertEquals(driver.findElement(By.xpath("//h3[@data-test='error']")).isDisplayed(), true);
		Reporter.log("User didn't logged in with invalid credentials", true);
		TakeScreenshot ts = new TakeScreenshot();
		ts.takeScreenshot(driver, "InvalidUsername");

	}

}