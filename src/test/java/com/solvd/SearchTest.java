package com.solvd;

import com.solvd.pages.HomePage;
import com.solvd.pages.ResultPage;
import com.solvd.utils.ConfigReader;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SearchTest extends AbstractTest {

    @Test
    public void testLogo() {
        WebDriver driver = driverThreadLocal.get();
        HomePage homePage = new HomePage(driver);
        homePage.goToPage();
        Assert.assertTrue(homePage.isLogoPresent(), "Amazon logo not present on HomePage");
    }

    @Test
    public void testSearch() {
        WebDriver driver = driverThreadLocal.get();
        HomePage homePage = new HomePage(driver);
        ResultPage resultPage = homePage.goToPage().search("macbook");
        Assert.assertFalse(resultPage.isResultListEmpty(), "List of results is empty.");
        Assert.assertTrue(resultPage.isResultsNumberOnPageCorrect(), "Expected number of results per page does not match the actual number.");
    }
}
