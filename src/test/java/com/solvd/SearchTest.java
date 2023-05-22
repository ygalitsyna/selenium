package com.solvd;

import com.solvd.pages.HomePage;
import com.solvd.pages.ResultPage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SearchTest extends AbstractTest{

    public String searchQuery = "macbook";

    @Test
    public void testSearchBarText(){
        WebDriver driver = driverThreadLocal.get();
        HomePage homePage = new HomePage(driver);
        Assert.assertTrue(homePage.getSearchBarPlaceholder().equals("Search Amazon"));
    }

    @Test
    public void testSearch(){
        WebDriver driver = driverThreadLocal.get();
        HomePage homePage = new HomePage(driver);
        ResultPage resultPage = homePage.search(searchQuery);
        Assert.assertFalse(resultPage.isResultListEmpty(), "List of results is empty.");
        Assert.assertTrue(resultPage.isResultsNumberOnPageCorrect(), "Expected number of results per page does not match the actual number.");
        Assert.assertTrue(resultPage.isAllResultsMatchCondition(searchQuery), "There are search results that don't match your search query");
    }
}
