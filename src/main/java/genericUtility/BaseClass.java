package genericUtility;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import objectRepository.HomePage;
import objectRepository.LoginPage;

public class BaseClass {

	public PropertyFileUtility pUtil = new PropertyFileUtility();
	public ExcelFileUtility eUtil = new ExcelFileUtility();
	public JavaUtility jUtil = new JavaUtility();
	public WebDriverUtility wUtil = new WebDriverUtility();
	public WebDriver driver;
	public static WebDriver sdriver; 								// this is used for taking screenshots in listeners


	@BeforeSuite(groups = { "SmokeSuite", "RegressionSuite" })
	public void bsConfig() {
		System.out.println("===Database Connection Successful===");
	}

	// @Parameters("browser")										//Invoke during XBrowser Execution
	// @BeforeTest													//Invoke during Distributed Parallel & XBrowser Execution
	@BeforeClass
	public void bcConfig(/*String Browser*/) throws IOException {	//Invoke during XBrowser Execution
		String browser = pUtil.readDataFromPropertyFile("browser");	//Comment during XBrowser Execution
		String url = pUtil.readDataFromPropertyFile("url");

		if (browser.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
			sdriver = driver;										// this is used for taking screenshots in listeners
			System.out.println(browser + "Launched Successfully");
		} else if (browser.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
			sdriver = driver;										// this is used for taking screenshots in listeners
			System.out.println(browser + "Launched Successfully");
		} else {
			System.out.println("Invalid Browser Selected");
		}
		wUtil.maximizeWindow(driver);
		driver.get(url);
		wUtil.waitForPageLoad(driver);
	}

	@BeforeMethod(groups = { "SmokeSuite", "RegressionSuite" })
	public void bmConfig() throws IOException {
		String username = pUtil.readDataFromPropertyFile("username");
		String password = pUtil.readDataFromPropertyFile("password");
		LoginPage lp = new LoginPage(driver);
		lp.loginToApp(username, password);
		System.out.println("===Login Successful===");
	}

	@AfterMethod(groups = { "SmokeSuite", "RegressionSuite" })
	public void amConfig() {
		HomePage hp = new HomePage(driver);
		hp.signoutOfApp(driver);
		System.out.println("===Logout Successful===");
	}
	
	// @AfterTest
	@AfterClass(groups = { "SmokeSuite", "RegressionSuite" })
	public void acConfig() {
		wUtil.closeBrowser(driver);
		System.out.println("===Browser Closed Successfully===");
	}

	@AfterSuite(groups = { "Smoke", "RegressionSuite" })
	public void asConfig() {
		System.out.println("===Database Disconnected Successfully===");
	}
}
