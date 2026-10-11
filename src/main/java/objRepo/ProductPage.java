
package objRepo;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

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

    // Select sorting option
    public void selectingSorting1(String sortVText) {
        wait.until(ExpectedConditions.elementToBeClickable(sort));
        new Select(sort).selectByVisibleText(sortVText);
    }

    public void selectingSorting(String sortVText) {
        selectingSorting1(sortVText);
    }

    // Sorting methods
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

    // Get product prices
    public List<String> getProductPrices() {
        List<String> prices = new ArrayList<>();

        List<WebElement> priceElements =
                driver.findElements(By.className("inventory_item_price"));

        for (WebElement price : priceElements) {
            prices.add(price.getText());
        }

        return prices;
    }

    // Get product names
    public List<String> getProductNames() {
        List<String> names = new ArrayList<>();

        List<WebElement> nameElements =
                driver.findElements(By.className("inventory_item_name"));

        for (WebElement name : nameElements) {
            names.add(name.getText());
        }

        return names;
    }

    // Locate product using its exact name
    public WebElement getProduct(String productName) {
        By locator = By.xpath(
                "//div[contains(@class,'inventory_item')]"
                + "[.//div[contains(@class,'inventory_item_name') "
                + "and normalize-space()=\"" + productName + "\"]]");

        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(locator));
    }

    // Add product to cart
    public void addProductToCart(String productName) {
        WebElement product = getProduct(productName);

        WebElement button = product.findElement(
                By.xpath(".//button[normalize-space()='Add to cart']"));

        wait.until(ExpectedConditions.elementToBeClickable(button));
        button.click();

        // Verify button changes to Remove
        wait.until(driver ->
                getProductButtonText(productName).equals("Remove"));
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
