package test.contacts;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.testng.annotations.Test;

import genericUtility.BaseClass;
import objectRepository.ContactsPage;
import objectRepository.CreatingNewContactPage;
import objectRepository.CreatingNewOrganizationPage;
import objectRepository.HomePage;
import objectRepository.OrganizationInfoPage;
import objectRepository.OrganizationsPage;

public class CreateContactWithOrganizationTest extends BaseClass {
	@Test
	public void createContactWithOrganizationTest() throws EncryptedDocumentException, IOException {

		// Step 1: Read data from excel file
		String orgName = eUtil.readDataFromExcelFile("Contacts", 7, 3) + jUtil.getRandomNumber();
		String lastName = eUtil.readDataFromExcelFile("Contacts", 7, 2) + jUtil.getRandomNumber();

		// Step 2:Click on Organizations link
		HomePage hp = new HomePage(driver);
		hp.clickOrganizationLnk();

		// Step 3:Click on Create Organization lookup image
		OrganizationsPage op = new OrganizationsPage(driver);
		op.clickOrganizationLookupImg();

		// Step 4:Create Organization with mandatory fields and Save
		CreatingNewOrganizationPage cnop = new CreatingNewOrganizationPage(driver);
		cnop.createOrganization(orgName);

		// Step 5:Validate newly created Organization
		OrganizationInfoPage oip = new OrganizationInfoPage(driver);
		String organizationHeader = oip.getOrganizationHeaderTxt();
		if (organizationHeader.contains(orgName)) {
			System.out.println(organizationHeader + "===>TestCase Passed");
		} else {
			System.out.println(organizationHeader + "===>TestCase Failed");
		}

		// Step 6:Click on Contacts link
		hp.clickContactsLnk();

		// Step 7:Click on Create Contact lookup image
		ContactsPage cp = new ContactsPage(driver);
		cp.clickCreateContactLookupImg();

		// Step 8:Create Contact with Organization and Save
		CreatingNewContactPage cncp = new CreatingNewContactPage(driver);
		cncp.createContact(lastName, driver, "Accounts", orgName, "Contacts");
	}

}
