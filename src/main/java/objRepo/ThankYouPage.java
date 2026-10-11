
package objRepo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ThankYouPage {

	public ThankYouPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	@FindBy(className = "complete-header")
	private WebElement thankYouMessage;

	@FindBy(className = "complete-text")
	private WebElement orderDescription;

	@FindBy(id = "back-to-products")
	private WebElement backHomeButton;
	@FindBy(xpath = "//button[contains(text(),'Generate PDF')]")
	private WebElement generatePDFButton;

	public WebElement getThankYouMessage() {
		return thankYouMessage;
	}

	public WebElement getOrderDescription() {
		return orderDescription;
	}

	public WebElement getBackHomeButton() {
		return backHomeButton;
	}

	public WebElement getGeneratePDFButton() {
		return generatePDFButton;
	}
}
