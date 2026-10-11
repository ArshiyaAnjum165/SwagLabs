package objRepo;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.WebDriver;

public class CheckoutPage {

	public CheckoutPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "first-name")
	private WebElement firstName;

	@FindBy(id = "last-name")
	private WebElement lastName;

	@FindBy(id = "postal-code")
	private WebElement postalCode;

	@FindBy(id = "continue")
	private WebElement continueButton;

	@FindBy(id = "cancel")
	private WebElement cancelButton;

	@FindBy(id = "checkout_info_container")
	private WebElement checkoutInfo;

	@FindBy(id = "checkout")
	private WebElement checkoutButton;

	public WebElement getFirstName() {
		return firstName;
	}

	public WebElement getLastName() {
		return lastName;
	}

	public WebElement getPostalCode() {
		return postalCode;
	}

	public WebElement getContinueButton() {
		return continueButton;
	}

	public WebElement getCancelButton() {
		return cancelButton;
	}

	public WebElement getCheckoutInfo() {
		return checkoutInfo;
	}

	public WebElement getCheckoutButton() {
		return checkoutButton;
	}

	public void clickCheckoutButton() {
		checkoutButton.click();
	}
}