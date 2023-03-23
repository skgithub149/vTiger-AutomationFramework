package test.organizations;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import genericUtility.BaseClass;
import objectRepository.CreatingNewOrganizationPage;
import objectRepository.HomePage;
import objectRepository.OrganizationInfoPage;
import objectRepository.OrganizationsPage;

@Listeners(genericUtility.ListenersImplementation.class)
public class CreateOrganizationWithIndustryAndTypeTest extends BaseClass {

	@Test(groups = { "SmokeSuite", "RegressionSuite" })
	public void createOrganizationWithIndustryAndTypeTest() throws EncryptedDocumentException, IOException {

		// Step 1:Read data from excel file
		String orgName = eUtil.readDataFromExcelFile("Organizations", 10, 2) + jUtil.getRandomNumber();
		String industryName = eUtil.readDataFromExcelFile("Organizations", 10, 3);
		String typeName = eUtil.readDataFromExcelFile("Organizations", 10, 4);

		// Step 2:Click on Organizations link
		HomePage hp = new HomePage(driver);
		hp.clickOrganizationLnk();

		// Step 3:Click on Create Organization lookup image
		OrganizationsPage op = new OrganizationsPage(driver);
		op.clickOrganizationLookupImg();

		// Step 4:Create Organization with mandatory fields + Industry dropdown + Type dropdown and Save
		CreatingNewOrganizationPage cnop = new CreatingNewOrganizationPage(driver);
		cnop.createOrganization(orgName, industryName, typeName);

		// Step 5:Validate newly created Organization
		OrganizationInfoPage oip = new OrganizationInfoPage(driver);
		String organizationHeader = oip.getOrganizationHeaderTxt();
		if (organizationHeader.contains(orgName)) {
			System.out.println(organizationHeader + "===>TestCase Passed");
		} else {
			System.out.println(organizationHeader + "===>TestCase Failed");
		}
	}

}
