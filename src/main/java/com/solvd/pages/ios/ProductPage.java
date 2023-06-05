package com.solvd.pages.ios;

import com.solvd.pages.common.CartPageBase;
import com.solvd.pages.common.ProductPageBase;
import com.zebrunner.carina.utils.factory.DeviceType;
import com.zebrunner.carina.utils.mobile.IMobileUtils;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

@DeviceType(pageType = DeviceType.Type.IOS_PHONE, parentClass = ProductPageBase.class)
public class ProductPage extends ProductPageBase implements IMobileUtils {
    private static final Logger LOGGER = LogManager.getLogger(com.solvd.pages.desktop.ProductPage.class);

    @FindBy(id = "add-to-cart-button")
    private ExtendedWebElement addToCartButton;

    @FindBy(id = "title")
    private ExtendedWebElement productTitleOnProductPage;

    @FindBy(xpath = "//div[@class='a-sheet-web-container a-experimental-ios-scrolling']//button[@class='a-sheet-close a-focus-hidden']")
    private ExtendedWebElement doneButton;

    @FindBy(id = "cart-size")
    private ExtendedWebElement cartLabel;

    public ProductPage(WebDriver driver) {
        super(driver);
    }

    public CartPageBase addProductToCartAndGoToCartPage() {
        addToCartButton.scrollTo();
        addToCartButton.clickByJs();
        waitUntil(ExpectedConditions.elementToBeClickable(doneButton.getElement()), 10);
        doneButton.click();
        cartLabel.clickByJs();
        return initPage(getDriver(), CartPageBase.class);
    }

    @Override
    public String getProductTitleText() {
        String entireProductTitle = productTitleOnProductPage.getText();
        String productTitle = StringUtils.substring(entireProductTitle, 0, 50);
        LOGGER.info("Title on ProductPage is '{}'", productTitle);
        return productTitle;
    }
}
