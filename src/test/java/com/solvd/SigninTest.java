package com.solvd;

import com.solvd.pages.HomePage;
import com.solvd.pages.SigninPage;
import com.zebrunner.carina.core.IAbstractTest;
import com.zebrunner.carina.core.registrar.ownership.MethodOwner;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class SigninTest implements IAbstractTest {

    @Test
    @Parameters("unsuccessful_signin")
    @MethodOwner(owner = "ygalitsyna")
    public void testUnsuccessfulSignin(String userEmail) {
        HomePage homePage = new HomePage(getDriver());
        SigninPage signinPage = homePage.goToPage().goToSigninPage().signin(userEmail);
        Assert.assertTrue(signinPage.getAlertHeadingText().equalsIgnoreCase("There was a problem"), "Alert heading is not correct");
        Assert.assertTrue(signinPage.getAlertText().equalsIgnoreCase("We cannot find an account with that email address"), "Alert text is not correct");
    }
}
