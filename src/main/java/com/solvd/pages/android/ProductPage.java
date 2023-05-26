package com.solvd.pages.android;

import com.solvd.pages.common.CartPageBase;
import com.solvd.pages.common.ProductPageBase;
import com.zebrunner.carina.utils.factory.DeviceType;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

@DeviceType(pageType = DeviceType.Type.ANDROID_PHONE, parentClass = ProductPageBase.class)
public class ProductPage extends ProductPageBase {
    private static final Logger LOGGER = LogManager.getLogger(com.solvd.pages.desktop.ProductPage.class);

    @FindBy(xpath = "//input[@id ='add-to-cart-button']")
    private ExtendedWebElement addToCartButton;

    @FindBy(xpath = "//span[@id='attach-sidesheet-view-cart-button']")
    private ExtendedWebElement cartButton;

    @FindBy(xpath = "//div[@id='titleSection']//span[@id='productTitle']")
    private ExtendedWebElement productTitleOnProductPage;

    public ProductPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public CartPageBase addProductToCartAndGoToCartPage() {
        addToCartButton.clickByJs();
        waitUntil(ExpectedConditions.visibilityOf(cartButton.getElement()), 10);
        cartButton.click();
        return initPage(getDriver(), CartPageBase.class);
    }

    @Override
    public String getProductTitleText() {
        String productTitle = productTitleOnProductPage.getText().trim();
        LOGGER.info("Title on ProductPage is '{}'", productTitle);
        return productTitle;
    }
}
