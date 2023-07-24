package com.solvd;

import com.solvd.pages.common.HomePageBase;
import com.solvd.pages.common.ResultPageBase;
import com.zebrunner.agent.core.annotation.TestRailCaseId;
import com.zebrunner.carina.core.IAbstractTest;
import com.zebrunner.carina.core.registrar.ownership.MethodOwner;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class SearchTest implements IAbstractTest {

    @Test
    @TestRailCaseId("3602")
    @MethodOwner(owner = "ygalitsyna")
    public void testSearchBarText() {
        HomePageBase homePage = initPage(getDriver(), HomePageBase.class);
        homePage.goToPage();
        Assert.assertTrue(homePage.getSearchBarPlaceholder().equals("Search Amazon"), "Search bar placeholder is not correct");
    }

    
    @Test
    @TestRailCaseId("3603")
    @Parameters("search_query")
    @MethodOwner(owner = "ygalitsyna")
    public void testSearch(String searchQuery) {
        HomePageBase homePage = initPage(getDriver(), HomePageBase.class);
        ResultPageBase resultPage = homePage.goToPage().search(searchQuery);
        Assert.assertFalse(resultPage.isResultListEmpty(), "List of results is empty");
        if(resultPage.getClass().getName().contains("desktop")) {
            Assert.assertTrue(resultPage.isResultsNumberOnPageCorrect(), "Expected number of results per page does not match the actual number");
        }
        Assert.assertTrue(resultPage.isAllResultsMatchCondition(searchQuery), "There are search results that don't match your search query");
    }
}
