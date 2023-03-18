package objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreatingNewLeadPage {

	// Declaration
	@FindBy(name = "lastname")
	private WebElement lastnameEdt;

	@FindBy(name = "company")
	private WebElement companyEdt;

	@FindBy(xpath = "//input[@title='Save [Alt+S]']")
	private WebElement saveBtn;

	// Initialization
	public CreatingNewLeadPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	public WebElement getLastnameEdt() {
		return lastnameEdt;
	}

	public WebElement getCompanyEdt() {
		return companyEdt;
	}

	public WebElement getSaveBtn() {
		return saveBtn;
	}

	// BusinessLibrary
	public void createNewLead(String lastName, String companyName) {
		lastnameEdt.sendKeys(lastName);
		companyEdt.sendKeys(companyName);
		saveBtn.click();
	}

}
