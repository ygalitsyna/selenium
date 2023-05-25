package com.solvd;

import com.solvd.pages.CartPage;
import com.solvd.pages.HomePage;
import com.solvd.pages.ProductPage;
import com.solvd.pages.ResultPage;
import com.zebrunner.carina.core.IAbstractTest;
import com.zebrunner.carina.core.registrar.ownership.MethodOwner;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class CartTest implements IAbstractTest {

    @Test
    @Parameters("search_query")
    @MethodOwner(owner = "ygalitsyna")
    public void testAddProductToCart(String searchQuery) {
        HomePage homePage = new HomePage(getDriver());
        homePage.goToPage();
        Assert.assertTrue(homePage.isInitProductNumberInCartEqualsToZero(), "The number of products in the cart is not equal to 0");
        ResultPage resultPage = homePage.search(searchQuery);
        ProductPage productPage = resultPage.openProductPageByLink(resultPage.getProductLinkForSecondProduct());
        String productTitleOnProductPage = productPage.getProductTitleText();
        CartPage cartPage = productPage.addProductToCartAndgoToCartPage();
        String productTitleOnCartPage = cartPage.getProductTitleText();
        Assert.assertTrue(cartPage.isProductAddedToCartCorrectly(), "The number of products in the cart is not equal to 1");
        Assert.assertEquals(productTitleOnProductPage, productTitleOnCartPage);
    }
}
