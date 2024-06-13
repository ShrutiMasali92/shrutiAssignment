package UITests;

import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.chrome.ChromeDriver;
	import org.testng.Assert;
	import org.testng.annotations.AfterClass;
	import org.testng.annotations.BeforeClass;
	import org.testng.annotations.Test;

import PageObjects.ConfigurationPage;
import utils.AuthUtils;
import utils.DriverFactory;

import java.io.IOException;

	public class ConfigNavigation_VerifyData {
	    private WebDriver driver;
	    private ConfigurationPage configurationPage;
	    

	    @BeforeClass
	    public void setUp() {
	        // Set up WebDriver
	    	 driver = DriverFactory.createDriver("chrome");

	         // Open the application URL
	         driver.get("your_application_url");
	         
	         AuthUtils.login(driver, "username", "password");
	         
	         // Initialize ConfigurationPage
	         configurationPage = new ConfigurationPage(driver);
	    }

	    @Test
	    public void testConfigPageNavigation() {
	        // Click on the configuration link
	        configurationPage.clickConfigLink();

	        // Verify navigation to the configuration page
	        Assert.assertTrue(configurationPage.isConfigurationPageDisplayed(), "Navigation to the configuration page failed");
	    }

	    
	    @Test(dependsOnMethods = "testConfigPageNavigation")
	    public void testSynchronizationIntervalSetting() {
	        // Set synchronization interval
	        String newInterval = "10"; // Example: set a new interval of 10 seconds
	        configurationPage.setSyncInterval(newInterval);
	        configurationPage.saveChanges();

	        // Verify that changes are saved successfully
	        Assert.assertTrue(configurationPage.isSuccessMessageDisplayed(), "Changes to synchronization interval not saved successfully");
	    }

	    
	    @Test(dependsOnMethods = "testConfigPageNavigation")
	    public void testAuthenticationParameters() {
	        // Update authentication parameters
	        String newUsername = "new_user";
	        String newPassword = "new_password";
	        configurationPage.updateAuthentication(newUsername, newPassword);
	        configurationPage.saveChanges();

	        // Verify that changes are saved successfully
	        Assert.assertTrue(configurationPage.isSuccessMessageDisplayed(), "Changes to authentication parameters not saved successfully");
	    }
	    

	    @AfterClass
	    public void tearDown() {
	        // Close the browser
	        driver.quit();
	    }
	}