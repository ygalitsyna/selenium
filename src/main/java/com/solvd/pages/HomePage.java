package com.solvd.pages;

import com.zebrunner.carina.utils.Configuration;
import com.zebrunner.carina.utils.R;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class HomePage extends AbstractPage {

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

    public ResultPage search(String product) {
        searchInput.type(product);
        searchButton.click();
        return new ResultPage(getDriver());
    }

    public HomePage goToPage() {
        open();
        return this;
    }

    public boolean isLogoPresent() {
        return amazonLogo.isVisible();
    }

    public String getAutoLocationText() {
        return autoLocation.getText();
    }

    public String getSearchBarPlaceholder() {
        return searchInput.getAttribute("placeholder").trim();
    }

    public SigninPage goToSigninPage() {
        signinButton.click();
        return new SigninPage(getDriver());
    }

    public boolean isInitProductNumberInCartEqualsToZero() {
        return Integer.parseInt(initProductNumberInCart.getText()) == 0;
    }
}
