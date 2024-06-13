package UITests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utils.AuthUtils;
import utils.DriverFactory;
import utils.TestBase;

public class DashboardNavigation_VerifyData {
    private WebDriver driver;
    private TestBase testBase;

    @BeforeClass
    public void setUp() {
    	// Set up WebDriver
   	 driver = DriverFactory.createDriver("chrome");

        // Open the application URL
        driver.get("your_application_url");
        
        AuthUtils.login(driver, "username", "password");
    }

    @Test
    public void testDashboardNavigation() {
        // Click on the dashboard link
             
        testBase.clickElement("dashboard_link");

        // Verify navigation to the dashboard page
        WebElement dashboardTitle = driver.findElement(By.xpath("//h1[contains(text(),'Dashboard')]"));
        Assert.assertTrue(dashboardTitle.isDisplayed(), "Navigation to the dashboard page failed");
    }

    
    @Test(dependsOnMethods = "testDashboardNavigation")
    public void testInventoryDisplay() {
        // Verify inventory count display
        WebElement inventoryCount = driver.findElement(By.id("inventory_count"));
        Assert.assertTrue(inventoryCount.isDisplayed(), "Inventory count not displayed on the dashboard");

        // Validate inventory count value
        int inventory = Integer.parseInt(inventoryCount.getText());
        Assert.assertTrue(inventory >= 0, "Invalid inventory count: " + inventory);
    }

    
    @Test(dependsOnMethods = "testDashboardNavigation")
    public void testRecentOrdersDisplay() {
        // Verify recent orders display
        WebElement recentOrdersSection = driver.findElement(By.id("recent_orders_section"));
        Assert.assertTrue(recentOrdersSection.isDisplayed(), "Recent orders section not displayed on the dashboard");

        // Check if there's at least one recent order listed
        int recentOrdersCount = driver.findElements(By.className("recent_order")).size();
        Assert.assertTrue(recentOrdersCount > 0, "No recent orders found on the dashboard");
    }

    @AfterClass
    public void tearDown() {
        // Close the browser
        driver.quit();
    }
}