package com.solvd.pages.common;

import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.WebDriver;

public abstract class SigninPageBase extends AbstractPage {
    public SigninPageBase(WebDriver driver) {
        super(driver);
    }

    public abstract SigninPageBase signin(String userEmail);

    public abstract String getAlertHeadingText();

    public abstract String getAlertText();

    public abstract boolean isAlertHeadingCorrect();

    public abstract boolean isAlertTextCorrect();
}
