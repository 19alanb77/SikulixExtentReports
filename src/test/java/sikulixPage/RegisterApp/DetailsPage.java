package sikulixPage.RegisterApp;

import org.sikuli.basics.Debug;
import org.sikuli.script.FindFailed;

import sikulixPage.General.MainPage;

/**
 * Class representing Details page.
 * 
 * @author Alan Buda
 */ 
public class DetailsPage extends MainViewPage{
	
	//Details page elements
	protected String address = "address.png";
	protected String addressCity = "addressCity.png";
	protected String addressCountry = "addressCountry.png";
	protected String addressFlat = "addressFlat.png";
	protected String addressModify = "addressModify.png";
	protected String addressPostcode = "addressPostcode.png";
	protected String addressStreet = "addressStreet.png";
	protected String addressTab = "addressTab.png";
	protected String mobile = "mobile.png";
	protected String mobileAdd = "mobileAdd.png";
	protected String mobileNumber = "mobileNumber.png";
	protected String ok = "ok.png";
	protected String title = "title.png";

	
	/**
	 * Fill 'Title:' field.
	 * 
	 * @param titleValue
	 * 		value of Title to set
	 * 
	 * @throws FindFailed 
	 * @throws InterruptedException 
	 */
	public void setTitle(String titleValue) throws FindFailed, InterruptedException {
		if(s.exists(maximize, 5) != null) {
			s.click(maximize);
		}
		s.wait(title, timeout).click(title);
		s.type(titleValue);	
	}
	
	/**
	 * Click 'Address' tab.
	 * 
	 * @throws FindFailed
	 */
	public void clickAddressTab() throws FindFailed {
		s.wait(addressTab, timeout).click(addressTab);
	}

	
	/**
	 * Modify Address details.
	 * 
	 * @param flat
	 * 			value of 'Flat Number:' field to set
	 * @param street
	 * 			value of 'Street Number:' field to set
	 * @param city
	 * 			value of 'Town/City:' field to set
	 * @param country
	 * 			value of 'Country:' field to set
	 * @param postcode
	 * 			value of 'Postcode:' field to set
	 * 
	 * @throws FindFailed
	 * @throws InterruptedException 
	 */
	public void modifyHomeAddress(String flat, String street, String city, String country, String postcode) throws FindFailed, InterruptedException {
		if(s.exists(addressModify, 10) != null) {
			s.wait(addressModify, MainPage.timeout).click(addressModify);
		} else {
			s.wait(address, MainPage.timeout).click(address);	
			s.wait(addressModify, MainPage.timeout).click(addressModify);
		}
		s.wait(addressModify, timeout).click(addressModify);
		s.wait(addressFree, timeout).click(addressFree);
		s.type("Yes");
		s.wait(addressFlat, timeout).click(addressFlat);
		s.type(flat);
		s.wait(addressStreet, timeout).click(addressStreet);
		s.type(street);
		s.wait(addressCity, timeout).click(addressCity);
		s.type(city);
		s.wait(addressCountry, timeout).click(addressCountry);
		s.type(country);
		s.wait(addressPostcode, timeout).click(addressPostcode);
		s.type(postcode);
		clickOK();
	}
	
	/**
	 * Fill 'Number/E-mail:'.
	 * 
	 * @param mobileNo
	 * 			value of mobile number/email to set
	 * 
	 * @throws FindFailed
	 */
	public void setMobileNumber(String mobileNo) throws FindFailed {
		if(s.exists(mobileAdd, 10) != null) {
			s.wait(mobileAdd, timeout).click(mobileAdd);
		} else {
			s.wait(mobile, timeout).click(mobile);	
			s.wait(mobileAdd, timeout).click(mobileAdd);
		}
		s.wait(mobileNumber, timeout).click(mobileNumber);
		s.type(mobileNo);
		clickOK();
	}

}
