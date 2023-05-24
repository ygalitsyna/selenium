package com.solvd;

import com.solvd.pages.CartPage;
import com.solvd.pages.HomePage;
import com.solvd.pages.ProductPage;
import com.solvd.pages.ResultPage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class CartTest extends AbstractTest {

    @Test
    @Parameters("search_query")
    public void testAddProductToCart(String searchQuery) {
        WebDriver driver = driverThreadLocal.get();
        HomePage homePage = new HomePage(driver);
        Assert.assertTrue(homePage.isInitProductNumberInCartEqualsToZero());
        ResultPage resultPage = homePage.goToPage().search(searchQuery);
        ProductPage productPage = resultPage.openProductPageByLink(resultPage.getProductLinkForFirstProduct());
        String productTitleOnProductPage = productPage.getProductTitleText();
        CartPage cartPage = productPage.addProductToCartAndgoToCartPage();
        String productTitleOnCartPage = cartPage.getProductTitleText();
        Assert.assertTrue(cartPage.isProductAddedToCartCorrectly());
        Assert.assertEquals(productTitleOnProductPage,productTitleOnCartPage);
    }
}
