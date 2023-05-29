package com.solvd.pages.desktop;

import com.solvd.pages.common.SigninPageBase;
import com.zebrunner.carina.utils.factory.DeviceType;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

@DeviceType(pageType = DeviceType.Type.DESKTOP, parentClass = SigninPageBase.class)
public class SigninPage extends SigninPageBase {

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

    @Override
    public SigninPageBase signin(String userEmail) {
        userEmailInput.type(userEmail);
        continueButton.click();
        return initPage(getDriver(), SigninPageBase.class);
    }

    @Override
    public String getAlertHeadingText() {
        return alertHeading.getText();
    }

    @Override
    public String getAlertText() {
        return alertText.getText();
    }
}
