package test.leads;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import genericUtility.BaseClass;
import objectRepository.CreatingNewLeadPage;
import objectRepository.HomePage;
import objectRepository.LeadInfoPage;
import objectRepository.LeadsPage;

@Listeners(genericUtility.ListenersImplementation.class)
public class CreateLeadTest extends BaseClass {

	@Test(groups = { "SmokeSuite", "RegressionSuite" })
	public void createLeadTest() throws EncryptedDocumentException, IOException {

		// Step 1:Read data from excel file
		String lastName = eUtil.readDataFromExcelFile("Leads", 1, 2) + jUtil.getRandomNumber();
		String companyName = eUtil.readDataFromExcelFile("Leads", 1, 3) + jUtil.getRandomNumber();

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

	}

}
