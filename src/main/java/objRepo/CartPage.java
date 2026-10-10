
package objRepo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CartPage {

	public CartPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "continue-shopping")
	private WebElement continueS;

	@FindBy(id = "checkout")
	private WebElement checkout;

	@FindBy(xpath = "//div[@class='cart_item']//button[text()='Remove']")
	private WebElement removeProduct;

	@FindBy(className = "shopping_cart_link")
	private WebElement cart;

	public WebElement getCart() {
		return cart;
	}

	public WebElement getContinueS() {
		return continueS;
	}

	public WebElement getCheckout() {
		return checkout;
	}

	public void removeProductFromCart() {
		removeProduct.click();
	}
}
