package com.solvd;

import com.solvd.pages.common.HomePageBase;
import com.zebrunner.agent.core.annotation.TestRailCaseId;
import com.zebrunner.agent.core.registrar.TestRail;
import com.zebrunner.carina.core.IAbstractTest;
import com.zebrunner.carina.core.registrar.ownership.MethodOwner;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class HeaderTest implements IAbstractTest {
    @BeforeSuite
    public void setUp() {
        TestRail.setSuiteId("S196");
        TestRail.setRunName("First run");
    }

    @Test
    @TestRailCaseId("C3595")
    @MethodOwner(owner = "ygalitsyna")
    public void testLogo() {
        HomePageBase homePage = initPage(getDriver(), HomePageBase.class);
        homePage.goToPage();
        Assert.assertTrue(homePage.isLogoPresent(), "Amazon logo not present on HomePage");
    }

    @Test
    @TestRailCaseId("C3596")
    @MethodOwner(owner = "ygalitsyna")
    public void testLocation() {
        HomePageBase homePage = initPage(getDriver(), HomePageBase.class);
        homePage.goToPage();
        Assert.assertTrue(homePage.getAutoLocationText().equalsIgnoreCase("Poland"), "Location not working properly");
    }
}
