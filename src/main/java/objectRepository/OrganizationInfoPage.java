package objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganizationInfoPage {

	// Declaration
	@FindBy(xpath = "//span[@class='dvHeaderText']")
	private WebElement organizationHeaderText;

	// Initialization
	public OrganizationInfoPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	// Utilization
	public WebElement getOrganizationHeaderText() {
		return organizationHeaderText;
	}

	// Business Library
	public String getOrganizationHeaderTxt() {
		return organizationHeaderText.getText();
	}

}
