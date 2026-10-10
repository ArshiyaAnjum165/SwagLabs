package objRepo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HamburgerMenuPage {

	WebDriver driver;

	public HamburgerMenuPage(WebDriver driver) {
		this.driver = driver;
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

	public void clickMenuButton() {
		menuButton.click();
	}

	public void clickCloseMenu() {
		closeMenuButton.click();
	}

	public void clickAllItems() {
		allItems.click();
	}

	public void clickDynamicCatalog() {
		dynamicCatalog.click();
	}

	public void clickLazyLoad() {
		lazyLoad.click();
	}

	public void clickSpinner() {
		spinner.click();
	}

	public void clickSlider() {
		slider.click();
	}

	public boolean isAboutDisplayed() {
		return about.isDisplayed();
	}

	public boolean isResetAppStateDisplayed() {
		return resetAppState.isDisplayed();
	}

	public boolean isLogoutDisplayed() {
		return logout.isDisplayed();
	}

	public boolean isAllItemsDisplayed() {
		return allItems.isDisplayed();
	}

	public void resetAppStateClick() {
		resetAppState.click();
	}

	public void clickLogout() {
		logout.click();
	}

	public boolean isDynamicCatalogDisplayed() {
		return dynamicCatalog.isDisplayed();
	}
}