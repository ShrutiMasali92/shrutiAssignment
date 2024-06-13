package utils;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WebWaitUtils {
    private static final int DEFAULT_TIMEOUT_SECONDS = 10;

    public static WebElement waitForElementVisible(WebDriver driver, WebElement element) {
        return new WebDriverWait(driver, DEFAULT_TIMEOUT_SECONDS)
                .until(ExpectedConditions.visibilityOf(element));
    }

    public static WebElement waitForElementClickable(WebDriver driver, WebElement element) {
        return new WebDriverWait(driver, DEFAULT_TIMEOUT_SECONDS)
                .until(ExpectedConditions.elementToBeClickable(element));
    }

    public static WebElement waitForElementClickable(WebDriver driver, By locator) {
        return new WebDriverWait(driver, DEFAULT_TIMEOUT_SECONDS)
                .until(ExpectedConditions.elementToBeClickable(locator));
    }

    public static void waitForPageLoad(WebDriver driver) {
        new WebDriverWait(driver, DEFAULT_TIMEOUT_SECONDS)
                .until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
    }

    public static FluentWait<WebDriver> fluentWait(WebDriver driver, long timeoutSeconds) {
        return new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(timeoutSeconds))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(NoSuchElementException.class);
    }
}