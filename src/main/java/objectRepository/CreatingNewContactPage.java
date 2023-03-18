package objectRepository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import genericUtility.WebDriverUtility;

public class CreatingNewContactPage extends WebDriverUtility {

	// Declaration
	@FindBy(name = "lastname")
	private WebElement lastNameEdt;

	@FindBy(xpath = "//input[@title='Save [Alt+S]']")
	private WebElement saveBtn;

	@FindBy(xpath = "//input[@name='account_name']/following-sibling::img[@title='Select']")
	private WebElement organizationLookupImg;

	@FindBy(id = "search_txt")
	private WebElement searchEdt;

	@FindBy(name = "search")
	private WebElement searchBtn;

	// Initialization
	public CreatingNewContactPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	// Utilization
	public WebElement getLastNameEdt() {
		return lastNameEdt;
	}

	public WebElement getSaveBtn() {
		return saveBtn;
	}

	public WebElement getOrganizationLookupImg() {
		return organizationLookupImg;
	}

	public WebElement getSearchEdt() {
		return searchEdt;
	}

	public WebElement getSearchBtn() {
		return searchBtn;
	}

	// BusinessLibrary
	public void createContact(String lastName) {
		lastNameEdt.sendKeys(lastName);
		saveBtn.click();
	}

	public void createContact(String lastName, WebDriver driver, String partialTextOrganization, String orgName, String partialTextContact) {
		lastNameEdt.sendKeys(lastName);
		organizationLookupImg.click();
		switchToWindow(driver, partialTextOrganization);
		searchEdt.sendKeys(orgName);
		searchBtn.click();
		driver.findElement(By.xpath("//a[text()='" + orgName + "']")).click();
		switchToWindow(driver, partialTextContact);
		saveBtn.click();

	}

}
