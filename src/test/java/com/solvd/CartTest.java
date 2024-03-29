package com.solvd;

import com.solvd.pages.common.*;
import com.solvd.utils.MobileContextUtils;
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
        HomePageBase homePage = initPage(getDriver(), HomePageBase.class);
        homePage.goToPage();
        Assert.assertTrue(homePage.isInitProductNumberInCartEqualsToZero(), "The number of products in the cart is not equal to 0");
        ResultPageBase resultPage = homePage.search(searchQuery);
        ProductPageBase productPage = resultPage.goToFirstProductPage();
        if(resultPage.getClass().getName().contains("ios")) {
            MobileContextUtils contextHelper = new MobileContextUtils();
            contextHelper.switchMobileContext(MobileContextUtils.View.WEB_BROWSER);
            initPage(getDriver(), ProductPageBase.class);
        }
        String productTitleOnProductPage = productPage.getProductTitleText();
        CartPageBase cartPage = productPage.addProductToCartAndGoToCartPage();
        String productTitleOnCartPage = cartPage.getProductTitleText();
        Assert.assertTrue(cartPage.isProductAddedToCartCorrectly(), "The number of products in the cart is not equal to 1");
        Assert.assertEquals(productTitleOnProductPage, productTitleOnCartPage);
    }
}
