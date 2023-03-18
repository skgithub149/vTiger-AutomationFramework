package objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import genericUtility.WebDriverUtility;

public class HomePage extends WebDriverUtility {
	
	//Declaration
	@FindBy(linkText = "Organizations")
	private WebElement organizationLnk;
	
	@FindBy(linkText = "Contacts")
	private WebElement contactsLnk;
	
	@FindBy(linkText = "Leads")
	private WebElement leadsLnk;
	
	@FindBy(xpath = "//img[@src='themes/softed/images/user.PNG']")
	private WebElement administratorImg;
	
	@FindBy(linkText = "Sign Out")
	private WebElement signoutLnk;
	
	//Initialization
	public HomePage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	//Utilization
	public WebElement getOrganizationLnk() {
		return organizationLnk;
	}

	public WebElement getContactsLnk() {
		return contactsLnk;
	}

	public WebElement getLeadsLnk() {
		return leadsLnk;
	}
	
	public WebElement getAdministratorImg() {
		return administratorImg;
	}

	public WebElement getSignoutLnk() {
		return signoutLnk;
	}

	//BusinessLibrary
	public void clickOrganizationLnk() {
		organizationLnk.click();
	}
	
	public void clickContactsLnk() {
		contactsLnk.click();
	}
	
	public void clickLeadsLnk() {
		leadsLnk.click();
	}
	
	public void signoutOfApp(WebDriver driver) {
		mousehoverAction(driver, administratorImg);
		signoutLnk.click();
	}	
	
}
