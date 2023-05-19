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

    public AbstractPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void click(WebElement element){
        try{
            waitUntilElementToBeClickable(element);
            element.click();
            LOGGER.info("Element with id {} was clicked", element.getAttribute("id"));
        }catch(Exception e){
            LOGGER.warn("Failed to click element with id {} was ", element.getAttribute("id"));
        }
    }

    public void sendKeys(WebElement element, String text){
        try{
            waitUntilElementToBeVisible(element);
            element.sendKeys(text);
            LOGGER.info("Text {} was sent to the element with id {}", text, element.getAttribute("id"));
        }catch(Exception e){
            LOGGER.warn("Failed to sent text to the element with id {}", element.getAttribute("id"));
        }
    }

    public boolean isElementVisible(WebElement element){
        boolean isVisible = true;
        try {
            waitUntilElementToBeVisible(element);
            LOGGER.info("Element with id {} is visible", element.getAttribute("id"));
        } catch (TimeoutException e) {
            isVisible = false;
            LOGGER.info("Element with id {} isn't visible", element.getAttribute("id"));
        }
        return isVisible;
    }

    public void waitUntilElementToBeClickable(WebElement element){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public void waitUntilElementToBeVisible(WebElement element){
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public WebDriver getDriver() {
        return driver;
    }
}
