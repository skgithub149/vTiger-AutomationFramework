package test.contacts;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.testng.annotations.Test;

import genericUtility.BaseClass;
import objectRepository.ContactInfoPage;
import objectRepository.ContactsPage;
import objectRepository.CreatingNewContactPage;
import objectRepository.HomePage;

public class CreateContactTest extends BaseClass {
	@Test
	public void createContactTest() throws EncryptedDocumentException, IOException {

		// Step 1:Read data from excel file
		String lastName = eUtil.readDataFromExcelFile("Contacts", 1, 2) + jUtil.getRandomNumber();

		// Step 2:Click on Contacts link
		HomePage hp = new HomePage(driver);
		hp.clickContactsLnk();

		// Step 3:Click on Create Contact lookup image
		ContactsPage cp = new ContactsPage(driver);
		cp.clickCreateContactLookupImg();

		// Step 4:Create Contact with mandatory fields and Save
		CreatingNewContactPage cncp = new CreatingNewContactPage(driver);
		cncp.createContact(lastName);

		// Step 5:Validate newly created Contact
		ContactInfoPage cip = new ContactInfoPage(driver);
		String contactHeader = cip.getContactHeader();
		if (contactHeader.contains(lastName)) {
			System.out.println(contactHeader + "===>TestCase Passed");
		} else {
			System.out.println(contactHeader + "===>TestCase Failed");
		}
	}

}
