package Utility;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

public class TakeScreenshot {

    public void takeScreenshot(WebDriver driver, String screenshotName) throws IOException {
    	TakesScreenshot ts = (TakesScreenshot) driver;
		byte[] screenshot = ts.getScreenshotAs(OutputType.BYTES);
		Path path = Paths.get("./Screenshots/" + screenshotName + ".png");
		Files.createDirectories(path.getParent());
		Files.write(path, screenshot);
    }
}