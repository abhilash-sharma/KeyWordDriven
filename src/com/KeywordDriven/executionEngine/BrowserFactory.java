package com.KeywordDriven.executionEngine;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ankajsharma on 23/7/15.
 */
public class BrowserFactory {

    private static Map<String, WebDriver> drivers = new HashMap<String, WebDriver>();

    public static WebDriver getBrowser(String browserName){
        WebDriver driver = null;

        switch (browserName.toLowerCase()){
            case "firefox":{
                driver=drivers.get("Firefox");
                if (driver == null) {
                    driver=new FirefoxDriver();
                    drivers.put("firefox", driver);
                }
                break;
            }
            case "chrome":{
                driver=drivers.get("chrome");
                if (driver == null) {
                    String chromedriver = "/Volumes/Macintosh HD SafeDisc/Selenium/chromedriver";
                    System.setProperty("webdriver.chrome.driver", chromedriver);
                    driver=new ChromeDriver();
                    drivers.put("chrome", driver);
                    break;
                }
            }

        }
        return driver;

    }

    public static void closeAllBrowser(){

        for (String key : drivers.keySet()) {
            drivers.get(key).close();
            drivers.get(key).quit();
            drivers.remove(key);
        }
        drivers.clear();
    }


    public static void closeBrowser(String browserName) {
        WebDriver driver = drivers.get(browserName);
        driver.close();
        driver.quit();
        drivers.remove(browserName);
    }
}
