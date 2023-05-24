package com.solvd.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProductPage extends AbstractPage {
    private static final Logger LOGGER = LogManager.getLogger(ProductPage.class);

    @FindBy(xpath = "//input[@id ='add-to-cart-button']")
    private WebElement addToCartButton;

    @FindBy(xpath = "//a[@id='attach-close_sideSheet-link']")
    private WebElement closeButton;

    @FindBy(xpath = "//a[@class='nav-a nav-a-2 nav-progressive-attribute']")
    private WebElement cartLabel;

    @FindBy(xpath = "//div[@id='titleSection']//span[@id='productTitle']")
    private WebElement productTitleOnProductPage;

    public ProductPage(WebDriver driver) {
        super(driver);
    }

    public CartPage addProductToCartAndgoToCartPage() {
        click(addToCartButton);
        click(closeButton);
        WebDriverWait wait2 = new WebDriverWait(getDriver(), Duration.ofSeconds(15));
        wait2.until(ExpectedConditions.invisibilityOf(closeButton));
        click(cartLabel);
        return new CartPage(getDriver());
    }

    public String getProductTitleText() {
        waitUntilElementToBeVisible(productTitleOnProductPage);
        String productTitle = getWebElementText(productTitleOnProductPage).trim();
        LOGGER.info("Title on ProductPage is '{}'", productTitle);
        return productTitle;
    }
}
