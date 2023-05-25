package com.solvd.pages;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CartPage extends AbstractPage {
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

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public boolean isProductAddedToCartCorrectly() {
        return getCurrentProductNumberInCart(currentProductNumberInCart)
                && getCurrentProductNumberInCart(dropdownPromptButton)
                && getProductNumberInSubtotalLabel(subtotalLabelInBuyBox)
                && getProductNumberInSubtotalLabel(subtotalLabelInActiveCart);
    }

    public boolean getCurrentProductNumberInCart(ExtendedWebElement element) {
        return Integer.parseInt(element.getText().trim()) == 1;
    }

    public boolean getProductNumberInSubtotalLabel(ExtendedWebElement element) {
        String[] array = element.getText().split("\\D+");
        return Integer.parseInt(String.join("", array)) == 1;
    }

    public String getProductTitleText() {
        waitUntil(ExpectedConditions.visibilityOf(productTitleOnCartPage.getElement()), 10);
        String productTitle = productTitleOnCartPage.getText().trim();
        LOGGER.info("Title on CartPage is '{}'", productTitle);
        return productTitle;
    }
}
