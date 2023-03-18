package hardcoded.contacts;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class CreateContact {

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

		// Step 3:Click on Contacts link
		driver.findElement(By.linkText("Contacts")).click();

		// Step 4:Click on Create Contact lookup image
		driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();

		// Step 5:Create Contact with mandatory fields
		driver.findElement(By.name("lastname")).sendKeys("Harsh01");

		// Step 6:Click on Save button
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();

		// Step 7:Validate newly created Contact
		String contactHeader = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if (contactHeader.contains("Harsh01")) {
			System.out.println(contactHeader + "===>TestCase Passed");
		} else {
			System.out.println(contactHeader + "===>TestCase Failed");
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
