
package objRepo;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HamburgerMenuPage {

    WebDriver driver;
    WebDriverWait wait;

    public HamburgerMenuPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(
                driver, Duration.ofSeconds(10));

        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "react-burger-menu-btn")
    private WebElement menuButton;

    @FindBy(id = "react-burger-cross-btn")
    private WebElement closeMenuButton;

    @FindBy(id = "inventory_sidebar_link")
    private WebElement allItems;

    @FindBy(xpath = "//a[normalize-space()='Dynamic Catalog']")
    private WebElement dynamicCatalog;

    @FindBy(xpath = "//a[normalize-space()='Lazy Load']")
    private WebElement lazyLoad;

    @FindBy(xpath = "//a[normalize-space()='Spinner']")
    private WebElement spinner;

    @FindBy(xpath = "//a[normalize-space()='Slider']")
    private WebElement slider;

    @FindBy(id = "about_sidebar_link")
    private WebElement about;

    @FindBy(id = "logout_sidebar_link")
    private WebElement logout;

    @FindBy(id = "reset_sidebar_link")
    private WebElement resetAppState;

    // Click hamburger menu
    public void clickMenuButton() {
        wait.until(ExpectedConditions.elementToBeClickable(
                menuButton)).click();
    }

    // Close hamburger menu
    public void clickCloseMenu() {
        wait.until(ExpectedConditions.elementToBeClickable(
                closeMenuButton)).click();
    }

    // Click All Items
    public void clickAllItems() {
        wait.until(ExpectedConditions.elementToBeClickable(
                allItems)).click();
    }

    // Click Dynamic Catalog
    public void clickDynamicCatalog() {
        wait.until(ExpectedConditions.elementToBeClickable(
                dynamicCatalog)).click();
    }

    // Click Lazy Load
    public void clickLazyLoad() {
        wait.until(ExpectedConditions.elementToBeClickable(
                lazyLoad)).click();
    }

    // Click Spinner
    public void clickSpinner() {
        wait.until(ExpectedConditions.elementToBeClickable(
                spinner)).click();
    }

    // Click Slider
    public void clickSlider() {
        wait.until(ExpectedConditions.elementToBeClickable(
                slider)).click();
    }

    // Verify About is displayed
    public boolean isAboutDisplayed() {
        try {
            return wait.until(
                    ExpectedConditions.visibilityOf(about))
                    .isDisplayed();
        } catch (org.openqa.selenium.TimeoutException e) {
            return false;
        }
    }

    // Verify Reset App State is displayed
    public boolean isResetAppStateDisplayed() {
        try {
            return wait.until(
                    ExpectedConditions.visibilityOf(resetAppState))
                    .isDisplayed();
        } catch (org.openqa.selenium.TimeoutException e) {
            return false;
        }
    }

    // Verify Logout is displayed
    public boolean isLogoutDisplayed() {
        try {
            return wait.until(
                    ExpectedConditions.visibilityOf(logout))
                    .isDisplayed();
        } catch (org.openqa.selenium.TimeoutException e) {
            return false;
        }
    }

    // Verify All Items is displayed
    public boolean isAllItemsDisplayed() {
        try {
            return wait.until(
                    ExpectedConditions.visibilityOf(allItems))
                    .isDisplayed();
        } catch (org.openqa.selenium.TimeoutException e) {
            return false;
        }
    }

    // Click Reset App State
    public void resetAppStateClick() {
        wait.until(ExpectedConditions.elementToBeClickable(
                resetAppState)).click();
    }

    // Click Logout
    public void clickLogout() {
        wait.until(ExpectedConditions.elementToBeClickable(
                logout)).click();
    }

    // Verify Dynamic Catalog is displayed
    public boolean isDynamicCatalogDisplayed() {
        try {
            return wait.until(
                    ExpectedConditions.visibilityOf(dynamicCatalog))
                    .isDisplayed();
        } catch (org.openqa.selenium.TimeoutException e) {
            return false;
        }
    }
}
