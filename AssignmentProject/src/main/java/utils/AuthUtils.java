package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
	import org.openqa.selenium.By;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;

	public class AuthUtils {
		
		private WebDriver driver;
		private static Properties locators;
		public AuthUtils(WebDriver driver) {
		        this.driver = driver;
		        this.locators = loadLocators();
		    }
		
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
		
	    public static void login(WebDriver driver, String username, String password) {
	        // Find username and password input fields
	        WebElement usernameField = driver.findElement(By.id(locators.getProperty("username")));
	        WebElement passwordField = driver.findElement(By.id(locators.getProperty("password")));

	        // Enter username and password
	        usernameField.sendKeys(username);
	        passwordField.sendKeys(password);

	        // Find and click login button
	        WebElement loginButton = driver.findElement(By.id(locators.getProperty("login_button")));
	        loginButton.click();
	    }

	    // Add any other authentication-related methods here, like logout
	

}
