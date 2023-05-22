package com.solvd;

import com.solvd.pages.HomePage;
import com.solvd.pages.SigninPage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class SigninTest extends AbstractTest{

    //private final String userEmail = "uncorrectemail@new.com";

    @Test
    @Parameters("unsuccessful_signin")
    public void testUnsuccessfulSignin(String userEmail){
        WebDriver driver = driverThreadLocal.get();
        HomePage homePage = new HomePage(driver);
        SigninPage signinPage = homePage.goToSigninPage().signin(userEmail);
        Assert.assertTrue(signinPage.getAlertHedingText().equalsIgnoreCase("There was a problem"));
        Assert.assertTrue(signinPage.getAlertText().equalsIgnoreCase("We cannot find an account with that email address"));

    }

    //@DataProvider(name = "unsuccessful_signin")

}
