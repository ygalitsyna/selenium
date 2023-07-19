package com.solvd;

import com.solvd.pages.common.HomePageBase;
import com.solvd.pages.common.SigninPageBase;
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
        HomePageBase homePage = initPage(getDriver(), HomePageBase.class);
        SigninPageBase signinPage = homePage.goToPage().goToSigninPage().signin(userEmail);
        Assert.assertTrue(signinPage.isAlertHeadingCorrect(), "Alert heading is not correct");
        Assert.assertTrue(signinPage.isAlertTextCorrect(), "Alert text is not correct");
    }
}
