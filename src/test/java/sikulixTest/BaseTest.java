package sikulixTest;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.sikuli.basics.Settings;
import org.sikuli.script.ImagePath;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.JsonFormatter;

import sikulixConfig.ConfigKeys;
import sikulixConfig.ExcelUtils;
import sikulixPage.General.MainPage;
import sikulixPage.RegisterApp.MainViewPage;
import sikulixPage.RegisterApp.DetailsPage;


/**
 * Base class to initiate report tool and page objects.
 * 
 * @author Alan Buda
 */ 
public abstract class BaseTest {

	// Declaration of objects for specific pages
	protected MainPage main = new MainPage();
	protected MainViewPage regMainView = new MainViewPage();
	protected DetailsPage regDetails = new DetailsPage();

	
	// Declaration of ExtentReports variables
	protected ExtentReports extent = new ExtentReports();
	protected ExtentSparkReporter htmlReporter;
	protected ExtentTest test;
	
    /**
     * Initialize ExtentReports tool.
     * 
     * @throws Exception
     */
	@BeforeTest(description = "Initialization of ExtentReports tool")
	public void initExtentReports() throws Exception {
		if(!ConfigKeys.getConfigKey("extent_report").equals("off")) {
			String jsonPath = "./reports/jsons/" + new SimpleDateFormat("yyyy-MM-dd").format(new Date()) + ".json";
			JsonFormatter jsons = new JsonFormatter(jsonPath);	
			htmlReporter = new ExtentSparkReporter("./reports/" + new SimpleDateFormat("yyyy-MM-dd").format(new Date()) + ".html");				    
			extent.createDomainFromJsonArchive(jsonPath);
			extent.attachReporter(jsons, htmlReporter);
		    test = extent.createTest(this.getClass().getSimpleName());
		}
	}
	
	/*
	 * Initialize global settings for all test scripts.
	 */
	@BeforeClass(description = "Initialization of global settings for tests")
	public void initGlobalData() {
		Settings.DebugLogs = true;
		Logger.getRootLogger().setLevel(Level.OFF);
		ImagePath.add("./images/");	
	}

	/**
	 * Generate test results in ExtentReports tool.
	 * 
	 * @param result 
	 * 			test result captured by ITestResult class
	 * 
	 * @throws Exception
	 */
	@AfterMethod(description = "Generate test results in ExtentReports tool")
	public void getResult(ITestResult result) throws Exception {
		if (!ConfigKeys.getConfigKey("extent_report").equals("off")) {
			if (result.getStatus() == ITestResult.FAILURE) {
				test.log(Status.FAIL, result.getName() + "	| Error: " + result.getThrowable().getMessage());
	            captureScreenshot();
				Throwable th = result.getThrowable();
				if (th != null) {
					ExcelUtils.insertRowTestResults(result.getTestClass().getRealClass().getSimpleName(), "Failed",th.getMessage());
					System.out.println(th.getMessage());
					for (int i = 0; i < th.getStackTrace().length; i++) {
						System.out.println(th.getStackTrace()[i].toString());
					}
					;
					result.setThrowable(null);
				}
			} else if (result.getStatus() == ITestResult.SUCCESS) {
				test.log(Status.PASS, MarkupHelper.createLabel(result.getName() + " PASSED ", ExtentColor.GREEN));
			} else {
				test.log(Status.SKIP, MarkupHelper.createLabel(result.getName() + " SKIPPED ", ExtentColor.ORANGE));
			}
		}
	}

    /**
     * Clear data from ExtentReports tool.
     * 
     */
	@AfterTest(description = "Clear data from ExtentReports tool")
	public void clearResultsData() {
		if(!ConfigKeys.getConfigKey("extent_report").equals("off")) {
			 extent.flush();
		}
	}

	/**
	 * Capture screenshot. Method created for ExtentReports tool.
	 * 
	 * @throws IOException
	 * @throws AWTException 
	 */
    public void captureScreenshot() throws IOException, AWTException {
    	String path = "./reports/screenshots/" + this.getClass().getSimpleName() + "_" + new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date()) + ".png";     
    	Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
    	BufferedImage capture = new Robot().createScreenCapture(screenRect);
    	ImageIO.write(capture, "png", new File(path));
    }
}
