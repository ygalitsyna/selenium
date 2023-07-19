package com.solvd.pages.common;

import com.zebrunner.carina.utils.Configuration;
import com.zebrunner.carina.utils.R;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.WebDriver;

public abstract class HomePageBase extends AbstractPage {
    public HomePageBase(WebDriver driver) {
        super(driver);
        setPageAbsoluteURL(R.CONFIG.get(Configuration.Parameter.URL.getKey()));
    }

    public HomePageBase goToPage() {
        open();
        return this;
    }

    public abstract ResultPageBase search(String product);

    public abstract boolean isLogoPresent();

    public abstract String getAutoLocationText();

    public abstract String getSearchBarPlaceholder();

    public abstract SigninPageBase goToSigninPage();

    public abstract boolean isInitProductNumberInCartEqualsToZero();
}
