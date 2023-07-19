package com.solvd;

import com.solvd.pages.android.chrome.ChromeMainPage;
import com.solvd.pages.common.HomePageBase;
import com.solvd.utils.MobileContextUtils;
import com.zebrunner.carina.core.IAbstractTest;
import com.zebrunner.carina.core.registrar.ownership.MethodOwner;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class ContextTest implements IAbstractTest {

    @Test
    @MethodOwner(owner = "ygalitsyna")
    public void testContextSwitching() {
        HomePageBase homePage = initPage(getDriver(), HomePageBase.class);
        homePage.goToPage();
        MobileContextUtils contextHelper = new MobileContextUtils();
        contextHelper.switchMobileContext(MobileContextUtils.View.NATIVE);
        ChromeMainPage chromeMainPage = new ChromeMainPage(getDriver());
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(chromeMainPage.getTabsNumber(), 1);
        ChromeMainPage chromeMainPageInNewTab = chromeMainPage.goToChromeSettings().addNewTab();
        softAssert.assertEquals(chromeMainPageInNewTab.getTabsNumber(), 2);
        softAssert.assertTrue(chromeMainPageInNewTab.isGooglelogoPresent(), "Google logo is not present on ChromeMainPage");
        softAssert.assertTrue(chromeMainPageInNewTab.isSearchBarPresent(), "Search bar is not present on ChromeMainPage");
        softAssert.assertEquals(chromeMainPageInNewTab.getSearchBarPlaceholder(), "Search or type web address", "Search bar placeholder on ChromeMainPage is not correct");
    }
}
