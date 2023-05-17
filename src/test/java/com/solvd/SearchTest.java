package com.solvd;

import com.solvd.pages.HomePage;
import com.solvd.pages.ResultPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SearchTest extends AbstractTest{

    @Test(priority = 1)
    public void testSearch(){
        HomePage homePage = new HomePage(driver);
        ResultPage resultPage = homePage.search("macbook");
        Assert.assertFalse(resultPage.isResultListEmpty(), "List of results is empty.");
        Assert.assertTrue(resultPage.isResultsNumberOnPageCorrect(), "Expected number of results per page does not match the actual number.");
    }
}
