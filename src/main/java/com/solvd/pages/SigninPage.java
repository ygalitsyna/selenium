package com.solvd.pages;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class SigninPage extends AbstractPage {

    @FindBy(xpath = "//div[@class='a-section']//input[@type='email']")
    private ExtendedWebElement userEmailInput;

    @FindBy(css = "#continue")
    private ExtendedWebElement continueButton;

    @FindBy(css = ".a-alert-heading")
    private ExtendedWebElement alertHeading;

    @FindBy(css = ".a-list-item")
    private ExtendedWebElement alertText;

    public SigninPage(WebDriver driver) {
        super(driver);
    }

    public SigninPage signin(String userEmail) {
        userEmailInput.type(userEmail);
        continueButton.click();
        return new SigninPage(getDriver());
    }

    public String getAlertHeadingText() {
        return alertHeading.getText();
    }

    public String getAlertText() {
        return alertText.getText();
    }
}
