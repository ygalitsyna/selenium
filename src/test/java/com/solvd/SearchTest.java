package com.solvd;

import com.solvd.pages.HomePage;
import com.solvd.pages.ResultPage;
import com.zebrunner.carina.core.IAbstractTest;
import com.zebrunner.carina.core.registrar.ownership.MethodOwner;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class SearchTest implements IAbstractTest {

    @Test
    @MethodOwner(owner = "ygalitsyna")
    public void testSearchBarText() {
        HomePage homePage = new HomePage(getDriver());
        homePage.goToPage();
        Assert.assertTrue(homePage.getSearchBarPlaceholder().equals("Search Amazon"), "Search bar placeholder is not correct");
    }

    @Test
    @Parameters("search_query")
    @MethodOwner(owner = "ygalitsyna")
    public void testSearch(String searchQuery) {
        HomePage homePage = new HomePage(getDriver());
        ResultPage resultPage = homePage.goToPage().search(searchQuery);
        Assert.assertFalse(resultPage.isResultListEmpty(), "List of results is empty");
        Assert.assertTrue(resultPage.isResultsNumberOnPageCorrect(), "Expected number of results per page does not match the actual number");
        Assert.assertTrue(resultPage.isAllResultsMatchCondition(searchQuery), "There are search results that don't match your search query");
    }
}
