package com.solvd.pages.common;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.WebDriver;

public abstract class CartPageBase extends AbstractPage {

    public CartPageBase(WebDriver driver) {
        super(driver);
    }

    public abstract boolean isProductAddedToCartCorrectly();

    public abstract boolean getCurrentProductNumberInCart(ExtendedWebElement element);

    public abstract boolean getProductNumberInSubtotalLabel(ExtendedWebElement element);

    public abstract String getProductTitleText();
}
