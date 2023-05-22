package com.solvd;

import com.solvd.pages.HomePage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class HeaderTest extends AbstractTest{

    @Test
    public void testLogo(){
        WebDriver driver = driverThreadLocal.get();
        HomePage homePage = new HomePage(driver);
        Assert.assertTrue(homePage.isLogoPresent(), "Amazon logo not present on HomePage");
    }

    @Test
    public void testLocation(){
        WebDriver driver = driverThreadLocal.get();
        HomePage homePage = new HomePage(driver);
        Assert.assertTrue(homePage.getAutoLocationText().equalsIgnoreCase("Poland"));
    }
}
