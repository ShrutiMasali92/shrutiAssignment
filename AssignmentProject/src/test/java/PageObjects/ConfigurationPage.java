package PageObjects;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigurationPage {
    private WebDriver driver;
    private Properties locators;

    public ConfigurationPage(WebDriver driver) {
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
    
    public void clickConfigLink() {
        WebElement configLink = driver.findElement(By.id(locators.getProperty("configLink")));
        configLink.click();
    }

    public boolean isConfigurationPageDisplayed() {
        WebElement configTitle = driver.findElement(By.xpath(locators.getProperty("confi_title")));
        return configTitle.isDisplayed();
    }

    public void setSyncInterval(String interval) {
        WebElement syncIntervalInput = driver.findElement(By.id(locators.getProperty("sync_interval_id")));
        syncIntervalInput.clear();
        syncIntervalInput.sendKeys(interval);
    }

    public void saveChanges() {
        WebElement saveButton = driver.findElement(By.id(locators.getProperty("save_button_id")));
        saveButton.click();
    }
    
    public boolean isSuccessMessageDisplayed() {
        WebElement successMessage = driver.findElement(By.id(locators.getProperty("successMessage")));
        return successMessage.isDisplayed();
    }

    public void updateAuthentication(String username, String password) {
        WebElement usernameField = driver.findElement(By.id(locators.getProperty("usernameField")));
        WebElement passwordField = driver.findElement(By.id(locators.getProperty("passwordField")));

        usernameField.clear();
        usernameField.sendKeys(username);
        passwordField.clear();
        passwordField.sendKeys(password);
    }
}