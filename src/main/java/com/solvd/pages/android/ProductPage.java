package com.solvd.pages.android;

import com.solvd.pages.common.NewItemsInCartPageBase;
import com.solvd.pages.common.ProductPageBase;
import com.zebrunner.carina.utils.factory.DeviceType;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

@DeviceType(pageType = DeviceType.Type.ANDROID_PHONE, parentClass = ProductPageBase.class)
public class ProductPage extends ProductPageBase {
    private static final Logger LOGGER = LogManager.getLogger(com.solvd.pages.desktop.ProductPage.class);

    @FindBy(xpath = "//input[@id ='add-to-cart-button']")
    private ExtendedWebElement addToCartButton;

    @FindBy(xpath = "//div[@id='titleSection']//span[@id='productTitle']")
    private ExtendedWebElement productTitleOnProductPage;

    public ProductPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public NewItemsInCartPageBase addProductToCart() {
        addToCartButton.clickByJs();
        return initPage(getDriver(), NewItemsInCartPageBase.class);
    }

    @Override
    public String getProductTitleText() {
        String entireProductTitle = productTitleOnProductPage.getText();
        String productTitle = StringUtils.substring(entireProductTitle, 0, entireProductTitle.indexOf(';'));
        LOGGER.info("Title on ProductPage is '{}'", productTitle);
        return productTitle;
    }
}
