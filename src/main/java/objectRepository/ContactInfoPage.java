package objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactInfoPage {

	// Declaration
	@FindBy(xpath = "//span[@class='dvHeaderText']")
	private WebElement contactHeaderTxt;

	// Initialization
	public ContactInfoPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	// Utilization
	public WebElement getContactHeaderTxt() {
		return contactHeaderTxt;
	}

	// BusinessLibrary
	public String getContactHeader() {
		return contactHeaderTxt.getText();
	}

}
