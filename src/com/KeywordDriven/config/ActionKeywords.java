package com.KeywordDriven.config;

import com.KeywordDriven.executionEngine.BrowserFactory;
import com.KeywordDriven.utility.Log;
import com.KeywordDriven.utility.RepositoryParser;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

public class ActionKeywords {

	public static WebDriver driver= null;
	public static RepositoryParser parser;
	public static void openBrowser(String browser) throws Exception {
		Log.info("Opening "+ browser);
		driver = BrowserFactory.getBrowser(browser);
		parser= new RepositoryParser(Constants.Path_OR);
	}
	
	public static void navigate(){
		Log.info("Navigate to base url");
		driver.manage().timeouts().implicitlyWait(3000, TimeUnit.MILLISECONDS);
		driver.get(Constants.URL);
	}
	
	public static void click_MyAccount(String element) throws Exception {
		Log.info("Click My Account");
		driver.findElement(parser.getObjectLocator(element)).click();
	}
	
	public static void input_Username(String element) throws Exception {
		Log.info("Entering Username");
		driver.findElement(parser.getObjectLocator(element)).sendKeys(Constants.UserName);

	}
	
	public static void input_Password(String element) throws Exception {
		Log.info("Entering Password");
		driver.findElement(parser.getObjectLocator(element)).sendKeys(Constants.Password);

	}
	
	public static void click_Login(String element) throws Exception {
		Log.info("Clicking Login");
		driver.findElement(parser.getObjectLocator(element)).click();
	}
	
	public static void waitFor() throws InterruptedException{
		Thread.sleep(10000);
	}
	
	public static void click_Logout(String element) throws Exception {
		Log.info("Clicking Logout");
        driver.findElement (parser.getObjectLocator(element)).click();
	}
	
	public static void closeAllBrowser(){
		Log.info("Closing All Browser");
		BrowserFactory.closeAllBrowser();
	}

	public static void closeBrowser(String browserName){
		Log.info("Closing Browser " + browserName);
		BrowserFactory.closeBrowser(browserName);
	}

	public static void click (String element) throws Exception {
		Log.info("Clicking "+ element);
		driver.findElement(parser.getObjectLocator(element)).click();
	}

	public static void input (String element, String text) throws Exception {
		driver.findElement(parser.getObjectLocator(element)).sendKeys(text);
	}
	
}
