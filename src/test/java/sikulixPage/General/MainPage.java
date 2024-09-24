package sikulixPage.General;

import org.sikuli.basics.Debug;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.Region;
import org.sikuli.script.Screen;

import sikulixConfig.ConfigKeys;

/**
 * Class representing main page.
 * 
 * @author Alan Buda
 */ 
public class MainPage {
	
	protected static double timeout = 30;
	protected Screen s = new Screen();
	protected Region r = new Region(0,0,s.getBounds().width,s.getBounds().height,0);
	
	//Page elements
	protected String logOn = "logon.png";
	protected String openFile = "openFile.png";
	protected String user = "user.png";
	protected String edgeIcon = "edgeIcon.png";
	protected String edgeUrl = "edgeUrl.png";
	
	/**
	 * Change timeout value of waiting for an element.
	 * 
	 * @param time
	 *            period of time to set(in seconds)
	 */
	public void changeTimeout(double time) {
		timeout = time;
	}

	/**
	 * Check if element exists on the screen.
	 * 
	 * @param text
	 *            text that should exists on the screen
	 */
	public void assertElement(String text) {
		s.existsText(text, timeout);
	}
	
	/**
	 * Click App icon.
	 * 
	 * @throws FindFailed 
	 */
	public void openApp() throws FindFailed {
		s.wait(icon, timeout).doubleClick(icon);
		s.wait(logo,timeout);
	}
	
	/**
	 * Open Edge and insert url.
	 * 
	 * @throws FindFailed 
	 */
	public void openLoginApp() throws FindFailed {
		s.wait(edgeIcon, timeout).doubleClick(edgeIcon);
		s.wait(edgeUrl, timeout).click(edgeUrl);
		s.type(ConfigKeys.getConfigKey("url") + Key.ENTER);
		s.wait(logo,timeout);
	}
	
	/**
	 * Fill 'User name:' field in app.
	 * 
	 * @param username
	 * 		value of username to set
	 * 
	 * @throws FindFailed 
	 * @throws InterruptedException 
	 */
	public void setUsername(String username) throws FindFailed {
		s.wait(user,timeout).click(user);
		s.type(username);
		s.click(logo);		
	}
	
	/**
	 * Click domain folder. 
	 * 
	 * @param domain
	 * 	name of the domain folder
	 * 
	 * @throws FindFailed
	 */
	public void pickDomainFolder(String domain) throws FindFailed {
		r.waitText(domain, timeout);
		s.doubleClick(r.findText(domain));
		try {
			s.wait(AppsFolder,timeout);
	 	} catch (Exception e) {
	 		Debug.log("Exeption raised");
	 		s.click(folderIcon);
	 		s.wait(AppsFolder);
	 	  }
	}

	
	/**
	 * Click Apps folder.
	 * 
	 * @throws FindFailed
	 */
	public void clickApps() throws FindFailed {
		s.wait(appsFolder).doubleClick(appsFolder);
	}
	
	/**
	 * Search application in search box.
	 * 
	 * @param appName
	 * 		name of the application
	 * 
	 * @throws FindFailed
	 */
	public void searchRegisterApp(String appName) throws FindFailed {
		s.wait(appsFolderSearch, timeout).click(appsFolderSearch);
		s.wait(appsFolderSearchClicked, timeout).click(appsFolderSearchClicked);
		s.type(appName);
	}
	
	/**
	 * Click app file.
	 * 
	 * @throws FindFailed
	 * @throws InterruptedException 
	 */
	public void openRegisterApp() throws FindFailed, InterruptedException {
		s.wait(icon).doubleClick(icon);
		s.wait(logScreen, timeout);
	}
	
}
