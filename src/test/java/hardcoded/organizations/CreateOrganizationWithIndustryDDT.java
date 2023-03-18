package hardcoded.organizations;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class CreateOrganizationWithIndustryDDT {

	public static void main(String[] args) throws IOException {

		// Generates random number_append to the organization name
		Random rnd = new Random();
		int randomNum = rnd.nextInt(1000);

		// Step 1:Read all required data from external resources
		/* Read data from property file */
		FileInputStream fisp = new FileInputStream("./src/test/resources/commonData.properties");
		Properties pObj = new Properties();
		pObj.load(fisp);
		String browser = pObj.getProperty("browser");
		String url = pObj.getProperty("url");
		String username = pObj.getProperty("username");
		String password = pObj.getProperty("password");

		/* Read data from excel file */
		FileInputStream fise = new FileInputStream("./src/test/resources/TestDataOnline.xlsx");
		Workbook workbook = WorkbookFactory.create(fise);
		String orgName = workbook.getSheet("Organizations").getRow(4).getCell(2).getStringCellValue() + randomNum;
		String industryName = workbook.getSheet("Organizations").getRow(4).getCell(3).getStringCellValue();

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

		// Step 4:Click on Organizations link
		driver.findElement(By.linkText("Organizations")).click();

		// Step 5:Click on Create Organization lookup image
		driver.findElement(By.xpath("//img[@src='themes/softed/images/btnL3Add.gif']")).click();

		// Step 6:Create Organization with mandatory fields + Industry dropdown
		driver.findElement(By.name("accountname")).sendKeys(orgName);
		WebElement industryDropdown = driver.findElement(By.name("industry"));
		Select selectIndustry = new Select(industryDropdown);
		selectIndustry.selectByValue(industryName);

		// Step 7:Click on Save button
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();

		// Step 8:Validate newly created Organization
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

		// Step 9:SignOut of Application
		WebElement administratorIcon = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		Actions act = new Actions(driver);
		act.moveToElement(administratorIcon).perform();
		driver.findElement(By.xpath("//a[text()='Sign Out']")).click();

		// Step 10:Close the Browser
		driver.quit();

	}

}
