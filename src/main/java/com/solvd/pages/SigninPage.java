package com.solvd.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SigninPage extends AbstractPage{

    @FindBy(xpath = "//input[@name='email']")
    private WebElement userEmailInput;

    @FindBy(css = "#continue")
    private WebElement continueButton;

    @FindBy(css = ".a-alert-heading")
    private WebElement alertHeading;

    @FindBy(css = ".a-list-item")
    private WebElement alertText;

    public SigninPage(WebDriver driver) {
        super(driver);
    }

    public SigninPage signin(String userEmail){
        sendKeys(userEmailInput, userEmail);
        click(continueButton);
        return new SigninPage(getDriver());
    }

    public String getAlertHedingText(){
        return getWebElementText(alertHeading);
    }

    public String getAlertText(){
        return getWebElementText(alertText);
    }
}
