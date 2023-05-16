package com.solvd;

import com.solvd.pages.HomePage;
import com.solvd.pages.ResultPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class SearchTest {

    String driverPath = "/Users/yanagalitsyna/Desktop/chromedrivers/chromedriver";
    WebDriver driver;
    HomePage homePage;
    ResultPage resultPage;

    @BeforeTest
    public void setup(){
        System.setProperty("webdriver.chrome.driver", driverPath);
        ChromeDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://www.amazon.com/");
    }

    @Test(priority = 1)
    public void testSearch(){
        homePage = new HomePage(driver);
        resultPage = homePage.search("macbook");
        System.out.println("ui");
    }
}
