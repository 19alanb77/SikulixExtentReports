package sikulixTest.Examples;

import org.sikuli.script.FindFailed;
import org.testng.annotations.Test;

import sikulixConfig.ConfigKeys;
import sikulixConfig.ExcelUtils;
import sikulixTest.BaseTest;

/**
 * Class that allows to create example test using SikuliX.
 * 
 * @author Alan Buda
 */ 
public class PoCTest extends BaseTest {

	public String[] data = ConfigKeys.csvReader("PoCTest");

	@Test(description = "Open app and log in to the system")
	public void openAndLoginToSystem() throws FindFailed {
		main.changeTimeout(100);
		main.openApp();
		main.setUsername(data[0]);
		main.setDomain(data[1]);
		main.clickLogon();
	}
	
	@Test(description = "Search and open Register application", dependsOnMethods = "openAndLoginToSystem")
	public void openRegisterApp() throws FindFailed, InterruptedException {
		main.clickApps();
		main.searchRegisterApp(data[2]);
		main.openRegisterApp();
	}
	
	@Test(description = "Search for new user", dependsOnMethods = "openRegisterApp")
	public void userSearch() throws FindFailed, InterruptedException {
		regMainView.searchClickReset();
		regMainView.searchSetSurname(data[3]);
		regMainView.searchSetForename(data[4]);
		regMainView.searchSetGender(data[5]);
		regMainView.searchSetPostcode(data[6]);
		regMainView.searchSetDateOfBirth(data[7]);
		regMainView.searchClickSearch();
	}
	
	@Test(description = "Confirm register details", dependsOnMethods = "userSearch")
	public void assertDetails() throws FindFailed {
		regMainView.assertElement("Register details");
	}

	@Test(description = "Add number and addresss", dependsOnMethods = "assertDetails")
	public void addNumberAndAddress() throws FindFailed, InterruptedException {
		regDetails.setMobileNumber(data[8]);
		regDetails.clickAddressTab();
		regDetails.modifyHomeAddress(data[9], data[10], data[11], data[12], data[13]);	
		ExcelUtils.insertRowTestResults(this.getClass().getSimpleName(), "Success", "");		
	}
	
}
