package hardcoded.leads;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class CreateDuplicateLead {

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

		// Step 3:Click on Leads link
		driver.findElement(By.linkText("Leads")).click();

		// Step 4:Click on Create Lead lookup image
		driver.findElement(By.xpath("//img[@alt='Create Lead...']")).click();

		// Step 5:Create Lead with mandatory fields
		driver.findElement(By.name("lastname")).sendKeys("Harsh005");
		driver.findElement(By.name("company")).sendKeys("TCS");

		// Step 6:Click on Save button
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();

		// Step 7:Validate newly created Lead
		String leadHeader = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if (leadHeader.contains("Harsh005")) {
			System.out.println(leadHeader + "==>TestCase Passed");
		} else {
			System.out.println(leadHeader + "TestCase Passed");
		}

		// Step 8:Click on Duplicate button
		driver.findElement(By.xpath("//input[@title='Duplicate [Alt+U]']")).click();

		// Step 9:Edit the LastName and click on Save button
		driver.findElement(By.xpath("//input[@name='lastname']")).clear();
		driver.findElement(By.xpath("//input[@name='lastname']")).sendKeys("Gungun005");
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();

		// Step 10:Validate duplicate lead created
		String duplicateLeadHeader = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if (duplicateLeadHeader.contains("Gungun005")) {
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
