package gui.organizations;

import java.io.IOException;
import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import genericUtility.ExcelFileUtility;
import genericUtility.JavaUtility;
import genericUtility.PropertyFileUtility;
import genericUtility.WebDriverUtility;

public class CreateOrganizationGUI {

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

		String orgName = eUtil.readDataFromExcelFile("Organizations", 1, 2) + jUtil.getRandomNumber();

		// Step 2:Launch the Browser
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

		// Step 3:Login to Application
		driver.findElement(By.name("user_name")).sendKeys(username);
		driver.findElement(By.name("user_password")).sendKeys(password);
		driver.findElement(By.id("submitButton")).click();

		// Step 4:Click on Organizations link
		driver.findElement(By.linkText("Organizations")).click();

		// Step 5:Click on Create Organization lookup image
		driver.findElement(By.xpath("//img[@src='themes/softed/images/btnL3Add.gif']")).click();

		// Step 6:Create Organizations with mandatory fields
		driver.findElement(By.name("accountname")).sendKeys(orgName);

		// Step 7:Click on Save Button
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();

		// Step 8:Validate newly created Organizations
		String orgnanizationHeader = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if (orgnanizationHeader.contains(orgName)) {
			System.out.println(orgnanizationHeader + "===>TestCase Passed");
		} else {
			System.out.println(orgnanizationHeader + "===>TestCase Failed");
		}
		// Step 9:SignOut of Application
		WebElement administratorIcon = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		wUtil.mousehoverAction(driver, administratorIcon);
		driver.findElement(By.xpath("//a[text()='Sign Out']")).click();

		// Step 10:Close the Browser
		wUtil.closeBrowser(driver);

	}

}
