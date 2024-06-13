package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;


public class TestBase {
	 private WebDriver driver;
	 private Properties locators;
	 	 
	 private Properties loadLocators() {
	        Properties properties = new Properties();
	        try {
	            FileInputStream fis = new FileInputStream("PageLocators.properties");
	            properties.load(fis);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        return properties;
	    }
	 
	 
	 	 
	 public boolean isElementDisplayed(String locator) {
	        try {
	        	String elementLocator = locators.getProperty(locator);
	            WebElement element = driver.findElement(By.id(elementLocator));
	            return element.isDisplayed();
	        } catch (org.openqa.selenium.NoSuchElementException | org.openqa.selenium.StaleElementReferenceException | org.openqa.selenium.TimeoutException e) {
	            return false;
	        }
	    }
	 
	 public void clickElement(String locatorKey) {
	        String locatorValue = locators.getProperty(locatorKey);
	        WebElement element = driver.findElement(By.id(locatorValue));
	        element.click();
	    }
	 
}
