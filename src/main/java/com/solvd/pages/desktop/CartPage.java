package com.solvd.pages.desktop;

import com.solvd.pages.common.CartPageBase;
import com.zebrunner.carina.utils.factory.DeviceType;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

@DeviceType(pageType = DeviceType.Type.DESKTOP, parentClass = CartPageBase.class)
public class CartPage extends CartPageBase {
    private static final Logger LOGGER = LogManager.getLogger(CartPage.class);

    @FindBy(xpath = "//span[@id='nav-cart-count']")
    private ExtendedWebElement currentProductNumberInCart;

    @FindBy(xpath = "//span[@id='sc-subtotal-label-buybox']")
    private ExtendedWebElement subtotalLabelInBuyBox;

    @FindBy(xpath = "//span[@class='a-dropdown-prompt']")
    private ExtendedWebElement dropdownPromptButton;

    @FindBy(xpath = "//span[@id='sc-subtotal-label-activecart']")
    private ExtendedWebElement subtotalLabelInActiveCart;

    @FindBy(xpath = "//div[@class='sc-item-content-group']//span[@class='a-truncate-cut']")
    private ExtendedWebElement productTitleOnCartPage;

    //@FindBy(xpath = "//div[@class='sc-quantity-decrementer']")
    @FindBy(xpath = "//input[@value='Delete']")
    private ExtendedWebElement deleteButton;

    @FindBy(css = "a[href*='delete']")
    private ExtendedWebElement infoAboutRemovedProduct;

    public CartPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isProductAddedToCartCorrectly() {
        return getCurrentProductNumberInCart(currentProductNumberInCart)
                && getCurrentProductNumberInCart(dropdownPromptButton)
                && getProductNumberInSubtotalLabel(subtotalLabelInBuyBox)
                && getProductNumberInSubtotalLabel(subtotalLabelInActiveCart);
    }

    @Override
    public boolean getCurrentProductNumberInCart(ExtendedWebElement element) {
        return Integer.parseInt(element.getText().trim()) == 1;
    }

    @Override
    public boolean getProductNumberInSubtotalLabel(ExtendedWebElement element) {
        String[] array = element.getText().split("\\D+");
        return Integer.parseInt(String.join("", array)) == 1;
    }

    @Override
    public String getProductTitleText() {
        waitUntil(ExpectedConditions.visibilityOf(productTitleOnCartPage.getElement()), 10);
        String entireProductTitle = productTitleOnCartPage.getText();
        String productTitle = StringUtils.substring(entireProductTitle, 0, 60);
        LOGGER.info("Title on CartPage is '{}' ", productTitle);
        return productTitle;
    }
}
