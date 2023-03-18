package hardcoded.contacts;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class CreateContactWithOrganization {

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

		// Step 5:Create Organization with mandatory fields and Save
		driver.findElement(By.name("accountname")).sendKeys("TataMotors10");
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();

		// Step 6:Validate newly created Organization
		String orgnanizationHeader = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if (orgnanizationHeader.contains("TataMotors10")) {
			System.out.println(orgnanizationHeader + "===>TestCase Passed");
		} else {
			System.out.println(orgnanizationHeader + "===>TestCase Failed");
		}

		// Step 7:Click on Contacts link
		driver.findElement(By.linkText("Contacts")).click();

		// Step 8:Click on Create Contact lookup image
		driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();

		// Step 9:Create Contact with mandatory fields
		driver.findElement(By.name("lastname")).sendKeys("Harsh07");

		// Step 10:Select Organization from Organization Name lookup image
		String parent = driver.getWindowHandle();

		driver.findElement(By.xpath("//input[@name='account_name']/../img[@alt='Select']")).click();
		Set<String> setOfChild = driver.getWindowHandles();
		for (String child : setOfChild) {
			driver.switchTo().window(child);
		}
		driver.findElement(By.name("search_text")).sendKeys("TataMotors10");
		driver.findElement(By.name("search")).click();
		driver.findElement(By.xpath("//a[text()='TataMotors10']")).click();
		driver.switchTo().window(parent);

		// Step 11:Click on Save Button
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();

		// Step 12:SignOut of Application
		WebElement administratorIcon = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		Actions act = new Actions(driver);
		act.moveToElement(administratorIcon).perform();
		driver.findElement(By.xpath("//a[text()='Sign Out']")).click();

		// Step 13:Close the Browser
		driver.quit();

	}

}
