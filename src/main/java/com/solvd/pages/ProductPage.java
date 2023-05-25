package com.solvd.pages;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ProductPage extends AbstractPage {
    private static final Logger LOGGER = LogManager.getLogger(ProductPage.class);

    @FindBy(xpath = "//input[@id ='add-to-cart-button']")
    private ExtendedWebElement addToCartButton;

    @FindBy(xpath = "//span[@id='attach-sidesheet-view-cart-button']")
    private ExtendedWebElement cartButton;

    @FindBy(xpath = "//div[@id='titleSection']//span[@id='productTitle']")
    private ExtendedWebElement productTitleOnProductPage;

    public ProductPage(WebDriver driver) {
        super(driver);
    }

    public CartPage addProductToCartAndgoToCartPage() {
        addToCartButton.clickByJs();
        waitUntil(ExpectedConditions.visibilityOf(cartButton.getElement()), 10);
        cartButton.click();
        return new CartPage(getDriver());
    }

    public String getProductTitleText() {
        String productTitle = productTitleOnProductPage.getText().trim();
        LOGGER.info("Title on ProductPage is '{}'", productTitle);
        return productTitle;
    }
}
