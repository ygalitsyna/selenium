package com.solvd.pages.android;

import com.solvd.pages.common.HomePageBase;
import com.solvd.pages.common.ResultPageBase;
import com.solvd.pages.common.SigninPageBase;
import com.zebrunner.carina.utils.factory.DeviceType;
import com.zebrunner.carina.utils.mobile.IMobileUtils;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

@DeviceType(pageType = DeviceType.Type.ANDROID_PHONE, parentClass = HomePageBase.class)
public class HomePage extends HomePageBase implements IMobileUtils {

    @FindBy(id = "nav-logo-sprites")
    private ExtendedWebElement amazonLogo;

    //@FindBy(xpath = "//div[@class='nav-fill']//input[@id='twotabsearchtextbox']")
    //@FindBy(xpath = "//input[@id='nav-search-keywords']")
    @FindBy(xpath = "//input[@name='k']")
    private ExtendedWebElement searchInput;

    //@FindBy(xpath = "//div[@class='nav-right']//div[@class='nav-search-submit nav-sprite']")
    @FindBy(xpath = "//form[@id='nav-search-form']/div[2]/div")
    private ExtendedWebElement searchButton;

    @FindBy(id = "glow-ingress-single-line")
    private ExtendedWebElement autoLocation;

    //@FindBy(xpath = "//div[@class='nav-right']//a[@id='nav-link-accountList']")
    @FindBy(xpath = "//div[@class='nav-right']//div[@id='nav-progressive-greeting']")
    private ExtendedWebElement signinButton;

    @FindBy(xpath = "//span[@id='nav-cart-count']")
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
        return StringUtils.substringAfter(autoLocation.getText(), "to ");
    }

    @Override
    public String getSearchBarPlaceholder() {
        return searchInput.getAttribute("placeholder").trim();
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
