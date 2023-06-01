package com.solvd.pages.android.chrome;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class ChromeMainPage extends AbstractPage {
    @FindBy(id = "com.android.chrome:id/menu_button")
    private ExtendedWebElement chromeSettingsButton;

    @FindBy(id = "com.android.chrome:id/tab_switcher_button")
    private ExtendedWebElement tabsNumber;

    @FindBy(id = "com.android.chrome:id/search_provider_logo")
    private ExtendedWebElement googleLogo;

    @FindBy(id = "com.android.chrome:id/search_box_text")
    private ExtendedWebElement searchBar;

    public ChromeMainPage(WebDriver driver) {
        super(driver);
    }

    public ChromeSettingsPage goToChromeSettings() {
        chromeSettingsButton.click();
        return new ChromeSettingsPage(driver);
    }

    public int getTabsNumber() {
        return Integer.parseInt(StringUtils.substringBefore(tabsNumber.getAttribute("content-desc"), " "));
    }

    public boolean isGooglelogoPresent(){
        return googleLogo.isElementPresent();
    }

    public boolean isSearchBarPresent(){
        return searchBar.isElementPresent();
    }

    public String getSearchBarPlaceholder(){
        return searchBar.getAttribute("text").trim();
    }
}
