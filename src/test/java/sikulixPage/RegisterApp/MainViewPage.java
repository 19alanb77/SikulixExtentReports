package sikulixPage.RegisterApp;

import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.Pattern;

import sikulixPage.General.MainPage;

/**
 * Class representing Main View page.
 * 
 * @author Alan Buda
 */ 
public class MainViewPage extends MainPage{
	
	//Main View page elements
	protected String loginOk = "login_ok.png";
	protected String loginUser = "login_userLabel.png";
	protected String maximize = "maximize";
	
	//Main View - User Search window elements
	protected String searchAddUserButton = "searchWindow_addUser.png";
	protected String searchDateOfBirth = "searchWindow_dateOfBirth.png";
	protected String searchForename = "searchWindow_forename.png";
	protected String searchGender = "searchWindow_gender.png";
	protected String searchOkButton = "searchWindow_ok.png";
	protected String searchPostcode = "searchWindow_postcode.png";
	protected String searchResetButton = "searchWindow_reset.png";
	protected String searchSurname = "searchWindow_surname.png";
	
	
	/**
	 * Click and fill 'Surname:' field in Search window.
	 * 
	 * @param surname
	 * 			value of Surname to set
	 * 
	 * @throws FindFailed
	 * @throws InterruptedException 
	 */
	public void searchSetSurname(String surname) throws FindFailed {
		s.wait(searchSurname, timeout);
		s.click(new Pattern(searchSurname).targetOffset(5,15));
		s.type(surname);
	}
	
	/**
	 * Click and fill 'Forename:' field in Search window.
	 * 
	 * @param forename
	 * 			value of Forename to set
	 * 
	 * @throws FindFailed
	 */
	public void searchSetForename(String forename) throws FindFailed {
		s.wait(searchForename, timeout);
		s.click(new Pattern(searchForename).targetOffset(5,15));
		s.type(forename);
	}
	
	/**
	 * Click and fill 'Gender:' field in Search window.
	 * 
	 * @param gender
	 * 			value of Gender to set
	 * 
	 * @throws FindFailed
	 */
	public void searchSetGender(String gender) throws FindFailed {
		s.wait(searchGender, timeout);
		s.click(new Pattern(searchGender).targetOffset(5,15));
		s.type(gender);
	}
	
	/**
	 * Click and fill 'Date of Birth:' field in Search window.
	 * 
	 * @param date
	 * 			value of Date of Birth to set
	 * 
	 * @throws FindFailed
	 */
	public void searchSetDateOfBirth(String date) throws FindFailed {
		s.wait(searchDateOfBirth, timeout);
		s.click(new Pattern(searchDateOfBirth).targetOffset(5,15));
		s.type(date);
	}
	
	/**
	 * Click and fill 'Postcode:' field in Search window.
	 * 
	 * @param postcode
	 * 			value of Postcode to set
	 * 
	 * @throws FindFailed
	 */
	public void searchSetPostcode(String postcode) throws FindFailed {
		s.wait(searchPostcode, timeout);
		s.click(new Pattern(searchPostcode).targetOffset(5,15));
		s.type(postcode);
	}
	
	/**
	 * Click 'Search' button in Search window.
	 * 
	 * @throws FindFailed 
	 */
	public void searchClickSearch() throws FindFailed {
		s.wait(searchSearchButton, timeout).click(searchSearchButton);
	}
	
	/**
	 * Click 'Reset' button in Search window.
	 * 
	 * @throws FindFailed 
	 * @throws InterruptedException 
	 */
	public void searchClickReset() throws FindFailed {
		s.wait(searchResetButton, timeout).click(searchResetButton);
	}
	
}
