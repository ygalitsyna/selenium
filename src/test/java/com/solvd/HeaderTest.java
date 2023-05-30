package com.solvd;

import com.solvd.pages.common.HomePageBase;
import com.zebrunner.carina.core.IAbstractTest;
import com.zebrunner.carina.core.registrar.ownership.MethodOwner;
import org.testng.Assert;
import org.testng.annotations.Test;

public class HeaderTest implements IAbstractTest {

    @Test
    @MethodOwner(owner = "ygalitsyna")
    public void testLogo() throws InterruptedException {
        HomePageBase homePage = initPage(getDriver(), HomePageBase.class);
        homePage.goToPage();
        Assert.assertTrue(homePage.isLogoPresent(), "Amazon logo not present on HomePage");
    }

    @Test
    @MethodOwner(owner = "ygalitsyna")
    public void testLocation() {
        HomePageBase homePage = initPage(getDriver(), HomePageBase.class);
        homePage.goToPage();
        Assert.assertTrue(homePage.getAutoLocationText().equalsIgnoreCase("Poland"), "Location not working properly");
    }
}
