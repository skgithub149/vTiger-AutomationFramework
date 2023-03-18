package objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import genericUtility.WebDriverUtility;

public class CreatingNewOrganizationPage extends WebDriverUtility {

	// Declaration
	@FindBy(xpath = "//input[@name='accountname']")
	private WebElement organizationNameEdt;

	@FindBy(name = "industry")
	private WebElement industryDropdown;

	@FindBy(name = "accounttype")
	private WebElement typeDropdown;

	@FindBy(xpath = "//input[@title='Save [Alt+S]']")
	private WebElement saveBtn;

	// Initialization
	public CreatingNewOrganizationPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	// Utilization
	public WebElement getOrganizationNameEdt() {
		return organizationNameEdt;
	}

	public WebElement getIndustryDropdown() {
		return industryDropdown;
	}

	public WebElement getTypeDropdown() {
		return typeDropdown;
	}

	public WebElement getSaveBtn() {
		return saveBtn;
	}

	// BusinessLibrary
	public void createOrganization(String orgName) {
		organizationNameEdt.sendKeys(orgName);
		saveBtn.click();
	}

	public void createOrganization(String orgName, String industry) {
		organizationNameEdt.sendKeys(orgName);
		handleDropdown(industryDropdown, industry);
		saveBtn.click();
	}

	public void createOrganization(String orgName, String industry, String type) {
		organizationNameEdt.sendKeys(orgName);
		handleDropdown(industryDropdown, industry);
		handleDropdown(industryDropdown, type);
		saveBtn.click();
	}

}
