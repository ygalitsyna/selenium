package com.solvd;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class AbstractTest {
    private final String driverPath = "/Users/yanagalitsyna/Desktop/chromedrivers/chromedriver";
    protected WebDriver driver;

    @BeforeMethod
    public void setup(){
        System.setProperty("webdriver.chrome.driver", driverPath);
        driver = new ChromeDriver();
        driver.get("https://www.amazon.com/");

    }

    @AfterMethod
    public void tearDown(){
        driver.close();
        driver.quit();
    }
}
