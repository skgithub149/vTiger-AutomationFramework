package genericUtility;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebDriverUtility {

	public void maximizeWindow(WebDriver driver) {
		driver.manage().window().maximize();
	}

	public void minimizeWindow(WebDriver driver) {
		driver.manage().window().minimize();

	}

	public void waitForPageLoad(WebDriver driver) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	}

	public void waitForElementToBeVisible(WebDriver driver, WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	public void waitForElementToBeClickable(WebDriver driver, By element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	public void handleDropdown(WebElement element, int index) {
		Select select = new Select(element);
		select.selectByIndex(index);
	}

	public void handleDropdown(WebElement element, String value) {
		Select select = new Select(element);
		select.selectByValue(value);
	}

	public void handleDropdown(String text, WebElement element) {
		Select select = new Select(element);
		select.selectByVisibleText(text);
	}

	public void mousehoverAction(WebDriver driver, WebElement element) {
		Actions act = new Actions(driver);
		act.moveToElement(element).perform();
	}

	public void doubleClick(WebDriver driver) {
		Actions act = new Actions(driver);
		act.doubleClick();
	}

	public void doubleClick(WebDriver driver, WebElement element) {
		Actions act = new Actions(driver);
		act.doubleClick(element);
	}

	public void rightClick(WebDriver driver) {
		Actions act = new Actions(driver);
		act.contextClick();
	}

	public void rightClick(WebDriver driver, WebElement element) {
		Actions act = new Actions(driver);
		act.contextClick(element);
	}

	public void dragAndDrop(WebDriver driver, WebElement srcElement, WebElement destElement) {
		Actions act = new Actions(driver);
		act.dragAndDrop(srcElement, destElement);
	}

	public void switchToFrame(WebDriver driver, int frameIndex) {
		driver.switchTo().frame(frameIndex);
	}

	public void switchToFrame(WebDriver driver, String nameOrId, String frameNameOrId) {
		driver.switchTo().frame(frameNameOrId);
	}

	public void switchToFrame(WebDriver driver, WebElement frameElement) {
		driver.switchTo().frame(frameElement);
	}

	public void switchToParentFrame(WebDriver driver) {
		driver.switchTo().parentFrame();
	}

	public void switchToDefaultContent(WebDriver driver) {
		driver.switchTo().defaultContent();
	}

	public void acceptAlert(WebDriver driver) {
		driver.switchTo().alert().accept();
	}

	public void dismissAlert(WebDriver driver) {
		driver.switchTo().alert().dismiss();
	}

	public String getAlertText(WebDriver driver) {
		return driver.switchTo().alert().getText();
	}

	public void switchToWindow(WebDriver driver, String partialText) {
		Set<String> winIds = driver.getWindowHandles();
		for (String winId : winIds) {
			String currentTitle = driver.switchTo().window(winId).getTitle();
			if (currentTitle.contains(partialText)) {
				break;
			}
		}
	}

	public String takeScreenshot(WebDriver driver, String screenshotName) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		File dest = new File(IConstantsUtility.SCREENSHOT_PATH + screenshotName + ".png");
		FileUtils.copyFile(src, dest);
		
		return dest.getAbsolutePath();

	}

	public void scrollAction(WebDriver driver) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,500)", "");
	}

	public void scrollAction(WebDriver driver, WebElement element) {
		Point pt = element.getLocation();
		int x = pt.getX();
		int y = pt.getY();

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(" + x + "," + y + ")", "");
	}

	public void closeBrowser(WebDriver driver) {
		driver.quit();
	}

}
