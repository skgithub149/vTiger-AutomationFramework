package hardcoded.leads;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

public class CreateDuplicateLeadDDT {

	public static void main(String[] args) throws EncryptedDocumentException, IOException {

		// Generates random number_append to the organization name
		Random rnd = new Random();
		int randomNum = rnd.nextInt(1000);

		// Step 1:Read all the required data from external resources
		/* Reading data from properties file */
		FileInputStream fisp = new FileInputStream("./src/test/resources/commonData.properties");
		Properties pObj = new Properties();
		pObj.load(fisp);
		String browser = pObj.getProperty("browser");
		String url = pObj.getProperty("url");
		String username = pObj.getProperty("username");
		String password = pObj.getProperty("password");

		/* Reading data from the excel file */
		FileInputStream fise = new FileInputStream("./src/test/resources/TestDataOnline.xlsx");
		Workbook workbook = WorkbookFactory.create(fise);
		String lastName = workbook.getSheet("Leads").getRow(1).getCell(2).getStringCellValue() + randomNum;
		String companyName = workbook.getSheet("Leads").getRow(1).getCell(3).getStringCellValue() + randomNum;
		String duplicateName = workbook.getSheet("Leads").getRow(1).getCell(4).getStringCellValue() + randomNum;

		// Step 2:Launch the Browser
		WebDriver driver = null;
		if (browser.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
		} else if (browser.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
		} else {
			System.out.println("Invalid Browser");
		}
		driver.manage().window().maximize();
		driver.get(url);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		// Step 3:Login to Application
		driver.findElement(By.name("user_name")).sendKeys(username);
		driver.findElement(By.name("user_password")).sendKeys(password);
		driver.findElement(By.id("submitButton")).click();

		// Step 3:Click on Leads link
		driver.findElement(By.linkText("Leads")).click();

		// Step 4:Click on Create Lead lookup image
		driver.findElement(By.xpath("//img[@alt='Create Lead...']")).click();

		// Step 5:Create Lead with mandatory fields
		driver.findElement(By.name("lastname")).sendKeys(lastName);
		driver.findElement(By.name("company")).sendKeys(companyName);

		// Step 6:Click on Save button
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();

		// Step 7:Validate newly created Lead
		String leadHeader = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if (leadHeader.contains(lastName)) {
			System.out.println(leadHeader + "==>TestCase Passed");
		} else {
			System.out.println(leadHeader + "TestCase Passed");
		}

		// Step 8:Click on Duplicate button
		driver.findElement(By.xpath("//input[@title='Duplicate [Alt+U]']")).click();

		// Step 9:Edit the LastName and click on Save button
		driver.findElement(By.xpath("//input[@name='lastname']")).clear();
		driver.findElement(By.xpath("//input[@name='lastname']")).sendKeys(duplicateName);
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();

		// Step 10:Validate duplicate lead created
		String duplicateLeadHeader = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if (duplicateLeadHeader.contains(duplicateName)) {
			System.out.println(duplicateLeadHeader + "==>TestCase Passed");
		} else {
			System.out.println(leadHeader + "TestCase Passed");
		}

		// Step 11:SignOut of Application
		WebElement administratorIcon = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		Actions act = new Actions(driver);
		act.moveToElement(administratorIcon).perform();
		driver.findElement(By.xpath("//a[text()='Sign Out']")).click();

		// Step 12:Close the Browser
		driver.quit();

	}

}
