package gui.leads;

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

public class CreateDuplicateLeadGUI {

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

		String lastName = eUtil.readDataFromExcelFile("Leads", 1, 2) + jUtil.getRandomNumber();
		String companyName = eUtil.readDataFromExcelFile("Leads", 1, 3) + jUtil.getRandomNumber();
		String duplicateName = eUtil.readDataFromExcelFile("Leads", 1, 4) + jUtil.getRandomNumber();

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

		// Step 5:Click on Leads link
		driver.findElement(By.linkText("Leads")).click();

		// Step 6:Click on Create Lead lookup image
		driver.findElement(By.xpath("//img[@alt='Create Lead...']")).click();

		// Step 7:Create Lead with mandatory fields
		driver.findElement(By.name("lastname")).sendKeys(lastName);
		driver.findElement(By.name("company")).sendKeys(companyName);

		// Step 8:Click on Save button
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();

		// Step 9:Validate newly created Lead
		String leadHeader = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if (leadHeader.contains(lastName)) {
			System.out.println(leadHeader + "==>TestCase Passed");
		} else {
			System.out.println(leadHeader + "TestCase Passed");
		}

		// Step 10:Click on Duplicate button
		driver.findElement(By.xpath("//input[@title='Duplicate [Alt+U]']")).click();

		// Step 11:Edit the LastName and click on Save button
		driver.findElement(By.xpath("//input[@name='lastname']")).clear();
		driver.findElement(By.xpath("//input[@name='lastname']")).sendKeys(duplicateName);
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();

		// Step 12:Validate duplicate lead created
		String duplicateLeadHeader = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if (duplicateLeadHeader.contains(duplicateName)) {
			System.out.println(duplicateLeadHeader + "==>TestCase Passed");
		} else {
			System.out.println(leadHeader + "TestCase Passed");
		}

		// Step 13:SignOut of Application
		WebElement administratorIcon = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		wUtil.mousehoverAction(driver, administratorIcon);
		driver.findElement(By.xpath("//a[text()='Sign Out']")).click();

		// Step 14:Close the Browser
		wUtil.closeBrowser(driver);

	}

}
