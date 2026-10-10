package objRepo;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductPage {

	WebDriver driver;
	WebDriverWait wait;

	public ProductPage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//select[@class='product_sort_container']")
	private WebElement sort;

	public void selectingSorting(String sortVText) {
		Select sel = new Select(sort);
		sel.selectByVisibleText(sortVText);
	}

	// Locate product using its exact name
	public WebElement getProduct(String productName) {
		By locator = By
				.xpath("//div[@class='inventory_item'][.//div[@class='inventory_item_name' and normalize-space()='"
						+ productName + "']]");

		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	// Add product to cart
	public void addProductToCart(String productName) {
		WebElement product = getProduct(productName);
		WebElement button = product.findElement(By.tagName("button"));

		wait.until(ExpectedConditions.elementToBeClickable(button));

		if (button.getText().equals("Add to cart")) {
			button.click();
		}

		wait.until(driver -> getProductButtonText(productName).equals("Remove"));
	}

	// Get current product button text
	public String getProductButtonText(String productName) {
		return getProduct(productName).findElement(By.tagName("button")).getText();
	}

	// Get cart badge count
	public String getCartBadgeCount() {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("shopping_cart_badge"))).getText();
	}

	// Remove product from cart
	public void removeProductFromCart(String productName) {
		WebElement product = getProduct(productName);
		WebElement button = product.findElement(By.tagName("button"));

		wait.until(ExpectedConditions.elementToBeClickable(button));

		if (button.getText().equals("Remove")) {
			button.click();
		}
	}

}