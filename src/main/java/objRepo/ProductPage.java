<<<<<<< HEAD

package objRepo;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
=======
package objRepo;

import java.time.Duration;
>>>>>>> 5b7f25652b05f2e95bee78314a22a550c47d4ddd

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductPage {

<<<<<<< HEAD
    WebDriver driver;
    WebDriverWait wait;

    public ProductPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//select[@class='product_sort_container']")
    private WebElement sort;

    public void selectingSorting1(String sortVText) {
        wait.until(ExpectedConditions.elementToBeClickable(sort));
        new Select(sort).selectByVisibleText(sortVText);
    }

    public void selectingSorting(String sortVText) {
        selectingSorting1(sortVText);
    }

    public void selectProductsAToZ() {
        selectingSorting1("Name (A to Z)");
    }

    public void selectProductsZToA() {
        selectingSorting1("Name (Z to A)");
    }

    public void selectPriceLowToHigh() {
        selectingSorting1("Price (low to high)");
    }

    public void selectPriceHighToLow() {
        selectingSorting1("Price (high to low)");
    }

    public List<String> getProductPrices() {
        List<String> prices = new ArrayList<>();

        List<WebElement> priceElements =
                driver.findElements(By.className("inventory_item_price"));

        for (WebElement price : priceElements) {
            prices.add(price.getText());
        }

        return prices;
    }

    public List<String> getProductNames() {
        List<String> names = new ArrayList<>();

        List<WebElement> nameElements =
                driver.findElements(By.className("inventory_item_name"));

        for (WebElement name : nameElements) {
            names.add(name.getText());
        }

        return names;
    }

    // Locate the exact product container
    public WebElement getProduct(String productName) {
        By locator = By.xpath(
                "//div[contains(@class,'inventory_item')]"
                + "[.//div[contains(@class,'inventory_item_name') "
                + "and normalize-space()=\"" + productName + "\"]]");

        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(locator));
    }

    // Add the specified product to cart
    public void addProductToCart(String productName) {

        WebElement product = getProduct(productName);

        By addButtonLocator = By.xpath(
                ".//button[normalize-space()='Add to cart']");

        WebElement addButton = product.findElement(addButtonLocator);

        wait.until(ExpectedConditions.elementToBeClickable(addButton));
        addButton.click();

        // Verify that the button changes to Remove
        wait.until(driver ->
                getProduct(productName)
                .findElement(By.tagName("button"))
                .getText().trim().equals("Remove"));
    }

    // Get current product button text
    public String getProductButtonText(String productName) {
        return getProduct(productName)
                .findElement(By.tagName("button"))
                .getText().trim();
    }

    // Get cart badge count
    public String getCartBadgeCount() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.className("shopping_cart_badge")))
                .getText();
    }

    // Remove product from cart
    public void removeProductFromCart(String productName) {
        WebElement product = getProduct(productName);

        WebElement button = product.findElement(
                By.xpath(".//button[normalize-space()='Remove']"));

        wait.until(ExpectedConditions.elementToBeClickable(button));
        button.click();
    }

    // Verify Products page is displayed
    public boolean isProductsPageDisplayed() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.className("title")))
                .getText().equals("Products");
    }
}
=======
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
>>>>>>> 5b7f25652b05f2e95bee78314a22a550c47d4ddd
