package gui.contacts;

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

public class CreateContactWithOrganizationGUI {

	public static void main(String[] args) throws EncryptedDocumentException, IOException {

		// Step 1: Create instances of all required libraries
		PropertyFileUtility pUtil = new PropertyFileUtility();
		ExcelFileUtility eUtil = new ExcelFileUtility();
		JavaUtility jUtil = new JavaUtility();
		WebDriverUtility wUtil = new WebDriverUtility();

		// Step 2: Read all required data from external resources
		String browser = pUtil.readDataFromPropertyFile("browser");
		String url = pUtil.readDataFromPropertyFile("url");
		String username = pUtil.readDataFromPropertyFile("username");
		String password = pUtil.readDataFromPropertyFile("password");

		String orgName = eUtil.readDataFromExcelFile("Contacts", 7, 3) + jUtil.getRandomNumber();
		String lastName = eUtil.readDataFromExcelFile("Contacts", 7, 2) + jUtil.getRandomNumber();

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

		// Step 7:Create Organization with mandatory fields and Save
		driver.findElement(By.name("accountname")).sendKeys(orgName);
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();

		// Step 8:Validate newly created Organization
		String orgnanizationHeader = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if (orgnanizationHeader.contains(orgName)) {
			System.out.println(orgnanizationHeader + "===>TestCase Passed");
		} else {
			System.out.println(orgnanizationHeader + "===>TestCase Failed");
		}

		// Step 9:Click on Contacts link
		driver.findElement(By.linkText("Contacts")).click();

		// Step 10:Click on Create Contact lookup image
		driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();

		// Step 11:Create Contact with mandatory fields
		driver.findElement(By.name("lastname")).sendKeys(lastName);

		// Step 12:Select Organization from Organization Name lookup image
		driver.findElement(By.xpath("//input[@name='account_name']/../img[@alt='Select']")).click();
		wUtil.switchToWindow(driver, "Accounts");
		driver.findElement(By.name("search_text")).sendKeys(orgName);
		driver.findElement(By.name("search")).click();
		driver.findElement(By.xpath("//a[text()='" + orgName + "']")).click();
		wUtil.switchToWindow(driver, "Contacts");

		// Step 13:Click on Save Button
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();

		// Step 14:SignOut of Application
		WebElement administratorIcon = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		wUtil.mousehoverAction(driver, administratorIcon);
		driver.findElement(By.xpath("//a[text()='Sign Out']")).click();

		// Step 15:Close the Browser
		wUtil.closeBrowser(driver);
	}

}
