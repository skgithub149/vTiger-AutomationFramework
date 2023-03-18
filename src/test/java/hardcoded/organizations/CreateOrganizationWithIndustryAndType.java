package hardcoded.organizations;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class CreateOrganizationWithIndustryAndType {

	public static void main(String[] args) {

		// Step 1:Launch the Browser
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://localhost:8888/index.php");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		// Step 2:Login to Application
		driver.findElement(By.name("user_name")).sendKeys("admin");
		driver.findElement(By.name("user_password")).sendKeys("manager");
		driver.findElement(By.id("submitButton")).click();

		// Step 3:Click on Organizations link
		driver.findElement(By.linkText("Organizations")).click();

		// Step 4:Click on Create Organization lookup image
		driver.findElement(By.xpath("//img[@src='themes/softed/images/btnL3Add.gif']")).click();

		// Step 5:Create Organization with mandatory fields + Industry dropdown + Type dropdown
		driver.findElement(By.name("accountname")).sendKeys("Mahindra02");

		WebElement industryDropdown = driver.findElement(By.name("industry"));
		Select selectIndustry = new Select(industryDropdown);
		selectIndustry.selectByValue("Energy");

		WebElement typeDropdown = driver.findElement(By.name("accounttype"));
		Select selectType = new Select(typeDropdown);
		selectType.selectByValue("Customer");

		// Step 6:Click on Save button
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();

		// Step 7:Validate newly created Organization
		String orgnanizationHeader = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if (orgnanizationHeader.contains("Mahindra02")) {
			System.out.println(orgnanizationHeader + "===>TestCase Passed");
		} else {
			System.out.println(orgnanizationHeader + "===>TestCase Failed");
		}

		// Validate Industry dropdown
		String actualIndustry = driver.findElement(By.id("dtlview_Industry")).getText();
		if (actualIndustry.contains("Energy")) {
			System.out.println(actualIndustry + "==>TestCase Passed");
		} else {
			System.out.println(actualIndustry + "==>TestCase Failed");
		}

		// Validate Type dropdown
		String actualType = driver.findElement(By.id("dtlview_Type")).getText();
		if (actualType.contains("Customer")) {
			System.out.println(actualType + "==>TestCase Passed");
		} else {
			System.out.println(actualType + "==>TestCase Failed");
		}

		// Step 8:SignOut of Application
		WebElement administratorIcon = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		Actions act = new Actions(driver);
		act.moveToElement(administratorIcon).perform();
		driver.findElement(By.xpath("//a[text()='Sign Out']")).click();

		// Step 9:Close the Browser
		driver.quit();

	}

}
