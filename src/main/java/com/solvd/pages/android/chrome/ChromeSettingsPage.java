package com.solvd.pages.android.chrome;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class ChromeSettingsPage extends AbstractPage {
    @FindBy(id = "com.android.chrome:id/menu_item_icon")
    private ExtendedWebElement addNewTabButton;

    public ChromeSettingsPage(WebDriver driver) {
        super(driver);
    }

    public ChromeMainPage addNewTab() {
        addNewTabButton.click();
        return new ChromeMainPage(driver);
    }
}
