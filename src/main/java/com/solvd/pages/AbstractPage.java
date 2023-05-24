package com.solvd.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class AbstractPage {
    private static final Logger LOGGER = LogManager.getLogger(AbstractPage.class);
    private WebDriver driver;

    public AbstractPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void click(WebElement element) {
        try {
            waitUntilElementToBeClickable(element);
            element.click();
            LOGGER.info("Element {} was clicked", element);
        } catch (Exception e) {
            LOGGER.warn("Failed to click element {}", element);
        }
    }

    public void sendKeys(WebElement element, String text) {
        try {
            waitUntilElementToBeVisible(element);
            element.sendKeys(text);
            LOGGER.info("Text {} was sent to the element {}", text, element);
        } catch (Exception e) {
            LOGGER.warn("Failed to sent text to the element {}", element);
        }
    }

    public boolean isElementVisible(WebElement element) {
        boolean isVisible = true;
        try {
            waitUntilElementToBeVisible(element);
            LOGGER.info("Element {} is visible", element);
        } catch (TimeoutException e) {
            isVisible = false;
            LOGGER.info("Element {} isn't visible", element);
        }
        return isVisible;
    }

    public void waitUntilElementToBeClickable(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public void waitUntilElementToBeVisible(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public WebDriver getDriver() {
        return driver;
    }
}
