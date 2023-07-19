package com.solvd.pages.desktop;

import com.solvd.pages.common.CartPageBase;
import com.solvd.pages.common.ProductPageBase;
import com.zebrunner.carina.utils.factory.DeviceType;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

@DeviceType(pageType = DeviceType.Type.DESKTOP, parentClass = ProductPageBase.class)
public class ProductPage extends ProductPageBase {
    private static final Logger LOGGER = LogManager.getLogger(ProductPage.class);

    @FindBy(xpath = "//input[@id ='add-to-cart-button']")
    private ExtendedWebElement addToCartButton;

    @FindBy(xpath = "//div[@id='titleSection']//span[@id='productTitle']")
    private ExtendedWebElement productTitleOnProductPage;

    @FindBy(id = "attach-view-cart-button-form")
    private ExtendedWebElement cartButton;

    public ProductPage(WebDriver driver) {
        super(driver);
    }

    public CartPageBase addProductToCartAndGoToCartPage() {
        addToCartButton.clickByJs();
        waitUntil(ExpectedConditions.visibilityOf(cartButton.getElement()), 10);
        cartButton.click();
        return initPage(getDriver(), CartPageBase.class);
    }

    @Override
    public String getProductTitleText() {
        String entireProductTitle = productTitleOnProductPage.getText();
        String productTitle = StringUtils.substring(entireProductTitle, 0, 60);
        LOGGER.info("Title on ProductPage is '{}'", productTitle);
        return productTitle;
    }
}
