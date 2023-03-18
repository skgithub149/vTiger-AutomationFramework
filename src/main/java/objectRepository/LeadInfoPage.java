package objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LeadInfoPage {

	// Declaration
	@FindBy(xpath = "//span[@class='dvHeaderText']")
	private WebElement leadHeaderTxt;

	@FindBy(xpath = "//input[@title='Duplicate [Alt+U]']")
	private WebElement duplicateBtn;

	// Initialization
	public LeadInfoPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	// Utilization
	public WebElement getLeadHeaderTxt() {
		return leadHeaderTxt;
	}

	public WebElement getDuplicateBtn() {
		return duplicateBtn;
	}

	// BusinessLibrary
	public String getLeadHeader() {
		return leadHeaderTxt.getText();
	}

	public void clickDuplicateBtn() {
		duplicateBtn.click();
	}

}
