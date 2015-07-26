package com.KeywordDriven.utility;

import org.openqa.selenium.By;

import java.io.FileInputStream;
import java.util.Properties;

/**
 * Created by ankajsharma on 21/7/15.
 */
public class RepositoryParser {

    private FileInputStream stream;
    private String RepositoryFile;
    private Properties repoProperties=new Properties();

    public RepositoryParser(String RepositoryFile) throws Exception{

        this.RepositoryFile = RepositoryFile;
        stream = new FileInputStream(this.RepositoryFile);
        repoProperties.load(stream);

    }

    public By getObjectLocator (String locatorName) throws Exception{
        String locatorProperty= repoProperties.getProperty(locatorName);
        //System.out.println(locatorProperty.toString());
        String locatorType=locatorProperty.split(":")[0];
        String locatorValue=locatorProperty.split(":")[1];

        //System.out.println(repoProperties.getProperty(locatorProperty));
        //System.out.println(locatorType + "/n" + locatorValue);
        By locator=null;

        switch (locatorType.toLowerCase()) {

            case "id":
                locator = By.id(locatorValue);
                break;
            case "name":
                locator = By.name(locatorValue);
                break;
            case "cssselector":
                locator = By.cssSelector(locatorValue);
                break;
            case "xpath":
                locator = By.xpath(locatorValue);
                break;
            case "tagname":
                locator = By.tagName(locatorName);
                break;
            case "linktext":
                locator = By.linkText(locatorValue);
                break;
            case "partiallinktext":
                locator = By.partialLinkText(locatorValue);
                break;

        }
        return locator;

    }
}
