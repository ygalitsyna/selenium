package com.solvd.pages.ios;

import com.solvd.pages.common.SigninPageBase;
import com.zebrunner.carina.utils.factory.DeviceType;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

@DeviceType(pageType = DeviceType.Type.IOS_PHONE, parentClass = SigninPageBase.class)
public class SigninPage extends SigninPageBase {

    @FindBy(id = "ap_email_login")
    private ExtendedWebElement userEmailInput;

    @FindBy(xpath = "//span[@class='a-button a-button-span12 a-button-primary']//input[@id='continue']")
    private ExtendedWebElement continueButton;

    @FindBy(xpath = "//div[@id='auth-warning-message-box']//h4[@class='a-alert-heading']")
    private ExtendedWebElement alertHeading;

    @FindBy(xpath = "//div[@id='auth-warning-message-box']//span[@class='a-list-item']")
    private ExtendedWebElement alertText;

    public SigninPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public SigninPageBase signin(String userEmail) {
        userEmailInput.type(userEmail);
        continueButton.click();
        return initPage(getDriver(), SigninPageBase.class);
    }

    @Override
    public String getAlertHeadingText() {
        return alertHeading.getText().trim();
    }

    @Override
    public String getAlertText() {
        return alertText.getText().trim();
    }

    public boolean isAlertHeadingCorrect(){
        return getAlertHeadingText().equals("No account found with email address");
    }

    public boolean isAlertTextCorrect(){
        return getAlertText().equals("Please check your email address or click Create Account if you are new to Amazon.");
    }
}
