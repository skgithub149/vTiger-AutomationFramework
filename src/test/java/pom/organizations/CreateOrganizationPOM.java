package pom.organizations;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import genericUtility.ExcelFileUtility;
import genericUtility.JavaUtility;
import genericUtility.PropertyFileUtility;
import genericUtility.WebDriverUtility;
import objectRepository.CreatingNewOrganizationPage;
import objectRepository.HomePage;
import objectRepository.LoginPage;
import objectRepository.OrganizationInfoPage;
import objectRepository.OrganizationsPage;

public class CreateOrganizationPOM {

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
		LoginPage lp = new LoginPage(driver);
		lp.loginToApp(username, password);

		// Step 4:Click on Organizations link
		HomePage hp = new HomePage(driver);
		hp.clickOrganizationLnk();

		// Step 5:Click on Create Organization lookup image
		OrganizationsPage op = new OrganizationsPage(driver);
		op.clickOrganizationLookupImg();

		// Step 6:Create Organizations with mandatory fields and Save
		CreatingNewOrganizationPage cnop = new CreatingNewOrganizationPage(driver);
		cnop.createOrganization(orgName);

		// Step 7:Validate newly created Organizations
		OrganizationInfoPage oip = new OrganizationInfoPage(driver);
		String organizationHeader = oip.getOrganizationHeaderTxt();
		if (organizationHeader.contains(orgName)) {
			System.out.println(organizationHeader + "===>TestCase Passed");
		} else {
			System.out.println(organizationHeader + "===>TestCase Failed");
		}

		// Step 8:SignOut of Application
		hp.signoutOfApp(driver);

		// Step 9:Close the Browser
		wUtil.closeBrowser(driver);

	}

}
