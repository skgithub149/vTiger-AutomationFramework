package gui.organizations;

import java.io.IOException;
import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import genericUtility.ExcelFileUtility;
import genericUtility.JavaUtility;
import genericUtility.PropertyFileUtility;
import genericUtility.WebDriverUtility;

public class CreateOrganizationWithIndustryGUI {

	public static void main(String[] args) throws EncryptedDocumentException, IOException {

		// Step 1:Create instances for all required libraries
		PropertyFileUtility pUtil = new PropertyFileUtility();
		ExcelFileUtility eUtil = new ExcelFileUtility();
		JavaUtility jUtil = new JavaUtility();
		WebDriverUtility wUtil = new WebDriverUtility();

		// Step 2:Read all the required data from external resources
		String browser = pUtil.readDataFromPropertyFile("browser");
		String url = pUtil.readDataFromPropertyFile("url");
		String username = pUtil.readDataFromPropertyFile("username");
		String password = pUtil.readDataFromPropertyFile("password");

		String orgName = eUtil.readDataFromExcelFile("Organizations", 4, 2) + jUtil.getRandomNumber();
		String industryName = eUtil.readDataFromExcelFile("Organizations", 4, 3);

		// Step 3:Launch the Browser
		WebDriver driver = null;
		if (browser.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
		} else if (browser.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
		} else {
			System.out.println("Invalid Browser");
		}
		wUtil.maximizeWindow(driver);
		driver.get(url);
		wUtil.waitForPageLoad(driver);

		// Step 4:Login to Application
		driver.findElement(By.name("user_name")).sendKeys(username);
		driver.findElement(By.name("user_password")).sendKeys(password);
		driver.findElement(By.id("submitButton")).click();

		// Step 5:Click on Organizations link
		driver.findElement(By.linkText("Organizations")).click();

		// Step 6:Click on Create Organization lookup image
		driver.findElement(By.xpath("//img[@src='themes/softed/images/btnL3Add.gif']")).click();

		// Step 7:Create Organization with mandatory fields + Industry dropdown
		driver.findElement(By.name("accountname")).sendKeys(orgName);
		WebElement industryDropdown = driver.findElement(By.name("industry"));
		Select selectIndustry = new Select(industryDropdown);
		selectIndustry.selectByValue(industryName);

		// Step 8:Click on Save button
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();

		// Step 9:Validate newly created Organization
		String orgnanizationHeader = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if (orgnanizationHeader.contains(orgName)) {
			System.out.println(orgnanizationHeader + "===>TestCase Passed");
		} else {
			System.out.println(orgnanizationHeader + "===>TestCase Failed");
		}

		// Validate Industry dropdown
		String actualIndustry = driver.findElement(By.id("dtlview_Industry")).getText();
		if (actualIndustry.contains(industryName)) {
			System.out.println(actualIndustry + "==>TestCase Passed");
		} else {
			System.out.println(actualIndustry + "==>TestCase Failed");
		}

		// Step 10:SignOut of Application
		WebElement administratorIcon = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		wUtil.mousehoverAction(driver, administratorIcon);
		driver.findElement(By.xpath("//a[text()='Sign Out']")).click();

		// Step 11:Close the Browser
		wUtil.closeBrowser(driver);

	}

}
