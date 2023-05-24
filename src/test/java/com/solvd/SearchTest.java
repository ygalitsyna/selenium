package com.solvd;

import com.solvd.pages.HomePage;
import com.solvd.pages.ResultPage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class SearchTest extends AbstractTest {

    @Test
    @Parameters("search_query")
    public void testSearchBarText(String searchQuery) {
        WebDriver driver = driverThreadLocal.get();
        HomePage homePage = new HomePage(driver);
        homePage.goToPage();
        Assert.assertTrue(homePage.getSearchBarPlaceholder().equals("Search Amazon"));
    }

    @Test
    @Parameters("search_query")
    public void testSearch(String searchQuery) {
        WebDriver driver = driverThreadLocal.get();
        HomePage homePage = new HomePage(driver);
        ResultPage resultPage = homePage.goToPage().search(searchQuery);
        Assert.assertFalse(resultPage.isResultListEmpty(), "List of results is empty.");
        Assert.assertTrue(resultPage.isResultsNumberOnPageCorrect(), "Expected number of results per page does not match the actual number.");
        Assert.assertTrue(resultPage.isAllResultsMatchCondition(searchQuery), "There are search results that don't match your search query");
    }
}
