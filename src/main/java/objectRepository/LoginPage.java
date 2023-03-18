package objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage { 	// Step 1: Create a pom class for every webpage
							// Step 2: Classname should be the title of webpage and ends with page
	// Step 3: Identify the web elements using @FindBy, @FindAll, @FindBys
	@FindBy(name = "user_name")
	private WebElement usernameEdt;

	@FindBy(name = "user_password")
	private WebElement passwordEdt;

	@FindBy(id = "submitButton")
	private WebElement loginBtn;

	// Step 4:Create a constructor to initialize the variables/web elements
	public LoginPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	// Step 5:Provide getters to access the web elements
	public WebElement getUsernameEdt() {
		return usernameEdt;
	}

	public WebElement getPasswordEdt() {
		return passwordEdt;
	}

	public WebElement getLoginBtn() {
		return loginBtn;
	}

	// Business Library-Generic Methods-specific to this application only-not to be
	// written in genericLibrary
	public void loginToApp(String username, String password) {
		usernameEdt.sendKeys(username);
		passwordEdt.sendKeys(password);
		loginBtn.click();

	}

}
