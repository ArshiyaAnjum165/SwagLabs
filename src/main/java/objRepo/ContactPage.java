
package objRepo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactPage {

	public ContactPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//a[contains(@href,'twitter.com') or contains(@href,'x.com')]")
	private WebElement xLink;

	@FindBy(xpath = "//a[contains(@href,'facebook.com')]")
	private WebElement facebookLink;

	@FindBy(xpath = "//a[contains(@href,'linkedin.com')]")
	private WebElement linkedInLink;

	public WebElement getXLink() {
		return xLink;
	}

	public WebElement getFacebookLink() {
		return facebookLink;
	}

	public WebElement getLinkedInLink() {
		return linkedInLink;
	}
}
