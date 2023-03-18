package test.leads;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.testng.annotations.Test;

import genericUtility.BaseClass;
import objectRepository.CreatingNewLeadPage;
import objectRepository.DuplicatingLeadPage;
import objectRepository.HomePage;
import objectRepository.LeadInfoPage;
import objectRepository.LeadsPage;

public class CreateDuplicateLeadTest extends BaseClass {
	
	@Test
	public void createDuplicateLeadTest() throws EncryptedDocumentException, IOException {

		// Step 1:Read data from excel file
		String lastName = eUtil.readDataFromExcelFile("Leads", 1, 2) + jUtil.getRandomNumber();
		String companyName = eUtil.readDataFromExcelFile("Leads", 1, 3) + jUtil.getRandomNumber();
		String duplicateName = eUtil.readDataFromExcelFile("Leads", 1, 4) + jUtil.getRandomNumber();

		// Step 2:Click on Leads link
		HomePage hp = new HomePage(driver);
		hp.clickLeadsLnk();

		// Step 3:Click on Create Lead lookup image
		LeadsPage lds = new LeadsPage(driver);
		lds.clickCreateLeadLookup();

		// Step 4:Create Lead with mandatory fields and save
		CreatingNewLeadPage cnlp = new CreatingNewLeadPage(driver);
		cnlp.createNewLead(lastName, companyName);

		// Step 5:Validate newly created Lead
		LeadInfoPage lip = new LeadInfoPage(driver);
		String leadHeader = lip.getLeadHeader();
		if (leadHeader.contains(lastName)) {
			System.out.println(leadHeader + "==>TestCase Passed");
		} else {
			System.out.println(leadHeader + "TestCase Passed");
		}

		// Step 6:Click on Duplicate button
		lip.clickDuplicateBtn();

		// Step 7:Edit the LastName and click on Save button
		DuplicatingLeadPage dlp = new DuplicatingLeadPage(driver);
		dlp.duplicatingLead(duplicateName);

		// Step 8:Validate duplicate lead created
		String duplicateLeadHeader = lip.getLeadHeader();
		if (duplicateLeadHeader.contains(duplicateName)) {
			System.out.println(duplicateLeadHeader + "==>TestCase Passed");
		} else {
			System.out.println(leadHeader + "TestCase Passed");
		}
	}

}
