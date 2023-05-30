package com.solvd.pages.common;

import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.WebDriver;

public abstract class NewItemsInCartPageBase extends AbstractPage {

    public NewItemsInCartPageBase(WebDriver driver) {
        super(driver);
    }

    public abstract  CartPageBase goToCartPage();
}
