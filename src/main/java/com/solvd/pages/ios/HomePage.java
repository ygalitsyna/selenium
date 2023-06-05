package com.solvd.pages.ios;

import com.solvd.pages.common.HomePageBase;
import com.solvd.pages.common.ResultPageBase;
import com.solvd.pages.common.SigninPageBase;
import com.zebrunner.carina.utils.factory.DeviceType;
import com.zebrunner.carina.utils.mobile.IMobileUtils;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.decorator.annotations.ClassChain;
import com.zebrunner.carina.webdriver.decorator.annotations.Predicate;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

@DeviceType(pageType = DeviceType.Type.IOS_PHONE, parentClass = HomePageBase.class)
public class HomePage extends HomePageBase implements IMobileUtils {

    @FindBy(id = "Amazon")
    private ExtendedWebElement amazonLogo;

    @FindBy(id = "Search Amazon")
    private ExtendedWebElement searchInput;

    @FindBy(id = "Go")
    private ExtendedWebElement searchButton;

    @FindBy(xpath = "**/XCUIElementTypeStaticText[`name CONTAINS 'Deliver'`]")
    @ClassChain
    private ExtendedWebElement autoLocation;

    @FindBy(xpath = "name == 'Sign in ›' AND type == 'XCUIElementTypeLink'")
    @Predicate
    private ExtendedWebElement signinButton;

    @FindBy(xpath = "**/XCUIElementTypeStaticText[`label == 'Cart'`]")
    @ClassChain
    private ExtendedWebElement initProductNumberInCart;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    @Override
    public ResultPageBase search(String product) {
        searchInput.type(product);
        hideKeyboard();
        searchButton.click();
        return initPage(getDriver(), ResultPageBase.class);
    }

    @Override
    public boolean isLogoPresent() {
        return amazonLogo.isVisible();
    }

    @Override
    public String getAutoLocationText() {
//        return StringUtils.substringBetween(autoLocation.getText(), "to", "⌵").trim();
        return StringUtils.substringAfter(autoLocation.getText(), "to ").trim();
    }

    @Override
    public String getSearchBarPlaceholder() {
        return searchInput.getText().trim();
    }

    @Override
    public SigninPageBase goToSigninPage() {
        signinButton.click();
        return initPage(getDriver(), SigninPageBase.class);
    }

    @Override
    public boolean isInitProductNumberInCartEqualsToZero() {
        return Integer.parseInt(initProductNumberInCart.getText().trim()) == 0;
    }
}
