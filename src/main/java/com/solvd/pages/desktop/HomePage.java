package com.solvd.pages.desktop;

import com.solvd.pages.common.HomePageBase;
import com.solvd.pages.common.ResultPageBase;
import com.solvd.pages.common.SigninPageBase;
import com.zebrunner.carina.utils.Configuration;
import com.zebrunner.carina.utils.R;
import com.zebrunner.carina.utils.factory.DeviceType;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

@DeviceType(pageType = DeviceType.Type.DESKTOP, parentClass = HomePageBase.class)
public class HomePage extends HomePageBase {

    @FindBy(xpath = "//div[@id='nav-belt']//a[@id='nav-logo-sprites']")
    private ExtendedWebElement amazonLogo;

    @FindBy(xpath = "//div[@class='nav-fill']//input[@id='twotabsearchtextbox']")
    private ExtendedWebElement searchInput;

    @FindBy(xpath = "//div[@class='nav-right']//div[@class='nav-search-submit nav-sprite']")
    private ExtendedWebElement searchButton;

    @FindBy(xpath = "//div[@id='nav-global-location-slot']//span[@id='glow-ingress-line2']")
    private ExtendedWebElement autoLocation;

    @FindBy(xpath = "//div[@class='nav-right']//a[@id='nav-link-accountList']")
    private ExtendedWebElement signinButton;

    @FindBy(xpath = "//span[@id='nav-cart-count']")
    private ExtendedWebElement initProductNumberInCart;

    public HomePage(WebDriver driver) {
        super(driver);
        setPageAbsoluteURL(R.CONFIG.get(Configuration.Parameter.URL.getKey()));
    }

    @Override
    public ResultPageBase search(String product) {
        searchInput.type(product);
        searchButton.click();
        return initPage(getDriver(), ResultPageBase.class);
    }

//    @Override
//    public HomePage goToPage() {
//        open();
//        return this;
//    }

    @Override
    public boolean isLogoPresent() {
        return amazonLogo.isVisible();
    }

    @Override
    public String getAutoLocationText() {
        return autoLocation.getText();
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
        return Integer.parseInt(initProductNumberInCart.getText()) == 0;
    }
}
