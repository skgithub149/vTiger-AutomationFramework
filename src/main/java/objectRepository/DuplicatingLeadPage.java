package objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DuplicatingLeadPage {

	// Declaration
	@FindBy(xpath = "//span[@class='lvtHeaderText']")
	private WebElement duplicatingLeadHeader;

	@FindBy(name = "lastname")
	private WebElement lastnameEdt;

	@FindBy(xpath = "//input[@title='Save [Alt+S]']")
	private WebElement saveBtn;

	// Initialization
	public DuplicatingLeadPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	// Utilization
	public WebElement getDuplicatingLeadHeader() {
		return duplicatingLeadHeader;
	}

	public WebElement getLastnameEdt() {
		return lastnameEdt;
	}

	public WebElement getSaveBtn() {
		return saveBtn;
	}

	// BusinessLibrary
	public void getDuplicatingHeader() {
		duplicatingLeadHeader.getText();
	}

	public void duplicatingLead(String duplicateName ) {
		lastnameEdt.clear();
		lastnameEdt.sendKeys(duplicateName);
		saveBtn.click();

	}

}
