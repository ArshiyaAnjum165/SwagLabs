package baseClass;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import Utility.FileUtility;

public class BaseClass {
	public WebDriver driver;

	@Parameters("browser")
	@BeforeClass(alwaysRun = true)
	public void launchBrowser(@Optional("chrome") String browser) throws Exception {
		FileUtility fu = new FileUtility();
		// String browser =fu.readDatafromPropertyFile("browser");
		ChromeOptions settings = new ChromeOptions();
		Map<String, Object> prefs = new HashMap<>();
		prefs.put("profile.password_manager_leak_detection", false);
		settings.setExperimentalOption("prefs", prefs);
		if (browser.equalsIgnoreCase("chrome"))
			driver = new ChromeDriver(settings);
		else if (browser.equalsIgnoreCase("firefox"))
			driver = new FirefoxDriver();
		else if (browser.equalsIgnoreCase("edge"))
			driver = new EdgeDriver();

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

		driver.get(fu.readDatafromPropertyFile("url"));
	}

	@AfterClass(alwaysRun = true)
	public void closeBrowser() {
		driver.quit();
	}
}