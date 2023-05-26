package com.solvd.pages.common;

import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.WebDriver;

public abstract class ProductPageBase extends AbstractPage {

    protected ProductPageBase(WebDriver driver) {
        super(driver);
    }

    public abstract CartPageBase addProductToCartAndGoToCartPage();

    public abstract String getProductTitleText();
}
