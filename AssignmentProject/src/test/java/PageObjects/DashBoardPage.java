package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class DashBoardPage {
    private WebDriver driver;
    private Properties locators;

    public DashBoardPage(WebDriver driver) {
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

    public boolean isDashboardDisplayed() {
        WebElement dboardTitle = driver.findElement(By.xpath(locators.getProperty("dboard_title_xpath")));
        return dboardTitle.isDisplayed();
    }

    public int getInventoryCount() {
        WebElement inventoryCount = driver.findElement(By.id(locators.getProperty("inventory_count_id")));
        return Integer.parseInt(inventoryCount.getText());
    }

    public boolean areRecentOrdersDisplayed() {
        return !driver.findElements(By.className(locators.getProperty("recent_orders_class"))).isEmpty();
    }
}