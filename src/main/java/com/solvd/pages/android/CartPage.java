package com.solvd.pages.android;

import com.solvd.pages.common.CartPageBase;
import com.zebrunner.carina.utils.factory.DeviceType;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

@DeviceType(pageType = DeviceType.Type.ANDROID_PHONE, parentClass = CartPageBase.class)
public class CartPage extends CartPageBase {
    private static final Logger LOGGER = LogManager.getLogger(com.solvd.pages.desktop.CartPage.class);
    private static final String THIS_METHOD_IS_DEFINED_ONLY_FOR_DESKTOP = "This method is not yet implemented for Android";

    @FindBy(id = "nav-cart-count")
    private ExtendedWebElement currentProductNumberInCart;

    @FindBy(xpath = "//div[@class='sc-quantity-label ']/span/a")
    private ExtendedWebElement dropdownPromptButton;

    @FindBy(className = "a-truncate-full a-offscreen")
    private ExtendedWebElement productTitleOnCartPage;

    public CartPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isProductAddedToCartCorrectly() {
        return getCurrentProductNumberInCart(currentProductNumberInCart)
                && getCurrentProductNumberInCart(dropdownPromptButton);
    }

    @Override
    public boolean getCurrentProductNumberInCart(ExtendedWebElement element) {
        return Integer.parseInt(element.getText().trim()) == 1;
    }

    @Override
    public boolean getProductNumberInSubtotalLabel(ExtendedWebElement element) {
        throw new UnsupportedOperationException(THIS_METHOD_IS_DEFINED_ONLY_FOR_DESKTOP);
    }

    @Override
    public String getProductTitleText() {
        waitUntil(ExpectedConditions.visibilityOf(productTitleOnCartPage.getElement()), 10);
        String entireProductTitle = productTitleOnCartPage.getText();
        String productTitle = StringUtils.substring(entireProductTitle, 0, entireProductTitle.indexOf(';'));
        LOGGER.info("Title on CartPage is '{}'", productTitle);
        return productTitle;
    }
}
