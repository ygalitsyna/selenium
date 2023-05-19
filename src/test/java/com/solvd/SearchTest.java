package com.solvd;

import com.solvd.pages.HomePage;
import com.solvd.pages.ResultPage;
import com.solvd.utils.ConfigReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class SearchTest {
    private final String driverPath = ConfigReader.getProperty("browser_path");
    private WebDriver driver;
    private HomePage homePage;
    private ResultPage resultPage;

    @BeforeTest
    public void setup(){
        System.setProperty("webdriver.chrome.driver", driverPath);
        driver = new ChromeDriver();
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(ConfigReader.getProperty("url"));

    }

    @Test(priority = 1)
    public void testSearch(){
        homePage = new HomePage(driver);
        resultPage = homePage.search("macbook");
        Assert.assertFalse(resultPage.isResultListEmpty(), "List of results is empty.");
        Assert.assertTrue(resultPage.isResultsNumberOnPageCorrect(), "Expected number of results per page does not match the actual number.");
    }

    @AfterTest
    public void tearDown(){
        driver.close();
        driver.quit();
    }
}
