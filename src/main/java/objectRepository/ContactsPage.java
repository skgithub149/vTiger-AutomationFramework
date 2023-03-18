package objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactsPage {

	// Declaration
	@FindBy(xpath = "//img[@title='Create Contact...']")
	private WebElement createContactLookupImg;

	// Initialization
	public ContactsPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	// Utilization
	public WebElement getCreateContactLookupImg() {
		return createContactLookupImg;
	}

	// BusinessLibrary
	public void clickCreateContactLookupImg() {
		createContactLookupImg.click();

	}

}
