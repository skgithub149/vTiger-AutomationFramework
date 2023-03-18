package pom.leads;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import genericUtility.ExcelFileUtility;
import genericUtility.JavaUtility;
import genericUtility.PropertyFileUtility;
import genericUtility.WebDriverUtility;
import objectRepository.CreatingNewLeadPage;
import objectRepository.DuplicatingLeadPage;
import objectRepository.HomePage;
import objectRepository.LeadInfoPage;
import objectRepository.LeadsPage;
import objectRepository.LoginPage;

public class CreateDuplicateLeadPOM {

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
		LoginPage lp = new LoginPage(driver);
		lp.loginToApp(username, password);

		// Step 5:Click on Leads link
		HomePage hp = new HomePage(driver);
		hp.clickLeadsLnk();

		// Step 6:Click on Create Lead lookup image
		LeadsPage lds = new LeadsPage(driver);
		lds.clickCreateLeadLookup();

		// Step 7:Create Lead with mandatory fields and save
		CreatingNewLeadPage cnlp = new CreatingNewLeadPage(driver);
		cnlp.createNewLead(lastName, companyName);

		// Step 9:Validate newly created Lead
		LeadInfoPage lip = new LeadInfoPage(driver);
		String leadHeader = lip.getLeadHeader();
		if (leadHeader.contains(lastName)) {
			System.out.println(leadHeader + "==>TestCase Passed");
		} else {
			System.out.println(leadHeader + "TestCase Passed");
		}

		// Step 10:Click on Duplicate button
		lip.clickDuplicateBtn();

		// Step 11:Edit the LastName and click on Save button
		DuplicatingLeadPage dlp = new DuplicatingLeadPage(driver);
		dlp.duplicatingLead(duplicateName);

		// Step 12:Validate duplicate lead created
		String duplicateLeadHeader = lip.getLeadHeader();
		if (duplicateLeadHeader.contains(duplicateName)) {
			System.out.println(duplicateLeadHeader + "==>TestCase Passed");
		} else {
			System.out.println(leadHeader + "TestCase Passed");
		}

		// Step 13:SignOut of Application
		hp.signoutOfApp(driver);

		// Step 14:Close the Browser
		wUtil.closeBrowser(driver);

	}

}
