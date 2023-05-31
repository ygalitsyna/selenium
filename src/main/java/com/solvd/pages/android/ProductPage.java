package com.solvd.pages.android;

import com.solvd.pages.common.CartPageBase;
import com.solvd.pages.common.ProductPageBase;
import com.zebrunner.carina.utils.factory.DeviceType;
import com.zebrunner.carina.utils.mobile.IMobileUtils;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

@DeviceType(pageType = DeviceType.Type.ANDROID_PHONE, parentClass = ProductPageBase.class)
public class ProductPage extends ProductPageBase implements IMobileUtils {
    private static final Logger LOGGER = LogManager.getLogger(com.solvd.pages.desktop.ProductPage.class);

    @FindBy(id = "add-to-cart-button")
    private ExtendedWebElement addToCartButton;

    @FindBy(id = "title")
    private ExtendedWebElement productTitleOnProductPage;

//    @FindBy(xpath = "//span[@class='a-sheet-close a-focus-hidden']")
//    @FindBy(xpath = "//div[@class='a-sheet-web-container a-experimental-ios-scrolling']//span[@class='a-sheet-close a-focus-hidden']")
//    @FindBy(xpath = "//span[@aria-label='DONE']")
//    @FindBy(className = "a-sheet-close a-focus-hidden")
//    @FindBy(xpath = "//button[@aria-label='DONE']")
//    @FindBy(css = "button[aria-label='DONE']")
//    private ExtendedWebElement doneButton;

    @FindBy(id = "cart-size")
    private ExtendedWebElement cartLabel;

    public ProductPage(WebDriver driver) {
        super(driver);
    }

    public CartPageBase addProductToCartAndGoToCartPage() {
        waitUntil(ExpectedConditions.visibilityOf(addToCartButton.getElement()), 10);
        addToCartButton.clickByJs();
        cartLabel.clickByJs();
        return initPage(getDriver(), CartPageBase.class);
    }

    @Override
    public String getProductTitleText() {
        String entireProductTitle = productTitleOnProductPage.getText();
        String productTitle = StringUtils.substring(entireProductTitle, 0, entireProductTitle.indexOf(';'));
        LOGGER.info("Title on ProductPage is '{}'", productTitle);
        return productTitle;
    }
}
