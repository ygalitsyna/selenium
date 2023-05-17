package com.solvd.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public abstract class AbstractPage {
    private static final Logger LOGGER = LogManager.getLogger(AbstractPage.class);
    private WebDriver driver;

    public AbstractPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void click(WebElement element){
        element.click();
        LOGGER.info(String.format("Element with id %s was clicked", element.getAttribute("id")));
    }

    public void sendKeys(WebElement element, String text){
        element.sendKeys(text);
        LOGGER.info(String.format("Text '%s' was sent to the element with id %s", text, element.getAttribute("id")));
    }
}
