package com.solvd.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CartPage extends AbstractPage {
    private static final Logger LOGGER = LogManager.getLogger(CartPage.class);

    @FindBy(xpath = "//span[@id='nav-cart-count']")
    private WebElement currentProductNumberInCart;

    @FindBy(xpath = "//span[@id='sc-subtotal-label-buybox']")
    private WebElement subtotalLabelInBuyBox;

    @FindBy(xpath = "//span[@class='a-dropdown-prompt']")
    private WebElement dropdownPromptButton;

    @FindBy(xpath = "//span[@id='sc-subtotal-label-activecart']")
    private WebElement subtotalLabelInActiveCart;

    @FindBy(xpath = "//div[@class='sc-item-content-group']//span[@class='a-truncate-cut']")
    private WebElement productTitleOnCartPage;

    @FindBy(xpath = "//div[@class='a-row']//h")
    private WebElement shoppingCartLabel;

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public boolean isProductAddedToCartCorrectly() {
        waitUntilElementToBeVisible(currentProductNumberInCart);
        return getCurrentProductNumberInCart(currentProductNumberInCart)
                && getCurrentProductNumberInCart(dropdownPromptButton)
                && getProductNumberInSubtotalLabel(subtotalLabelInBuyBox)
                && getProductNumberInSubtotalLabel(subtotalLabelInActiveCart);
    }

    public boolean getCurrentProductNumberInCart(WebElement element) {
        return Integer.parseInt(getWebElementText(element).trim()) == 1;
    }

    public boolean getProductNumberInSubtotalLabel(WebElement element) {
        String[] array = getWebElementText(element).split("\\D+");
        return Integer.parseInt(String.join("", array)) == 1;
    }

    public String getProductTitleText() {
        waitUntilElementToBeVisible(productTitleOnCartPage);
        String productTitle = getWebElementText(productTitleOnCartPage).trim();
        LOGGER.info("Title on CartPage is '{}'", productTitle);
        return productTitle;
    }
}
