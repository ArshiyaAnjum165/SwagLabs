package objRepo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	public LoginPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "user-name")
	private WebElement un;

	@FindBy(id = "password")
	private WebElement pwd;

	@FindBy(id = "login-button")
	private WebElement loginB;
	@FindBy(className = "title")
	private WebElement productsTitle;

	public WebElement getUn() {
		return un;
	}

	public WebElement getPwd() {
		return pwd;
	}

	public WebElement getLoginB() {
		return loginB;
	}

	public void login(String usern, String passw) {
		un.clear();
		un.sendKeys(usern);
		pwd.clear();
		pwd.sendKeys(passw);
		loginB.click();

	}

	// Verify products page is displayed
	public boolean isProductsPageDisplayed() {
		return productsTitle.isDisplayed();
	}
}