
package objRepo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutOverviewPage {

	public CheckoutOverviewPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "finish")
	private WebElement finishButton;

	@FindBy(id = "cancel")
	private WebElement cancelButton;

	public WebElement getFinishButton() {
		return finishButton;
	}

	public WebElement getCancelButton() {
		return cancelButton;
	}
}
