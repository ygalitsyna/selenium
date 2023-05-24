package com.solvd.pages;

import com.solvd.utils.ConfigReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends AbstractPage {

    @FindBy(xpath = "//div[@id='nav-belt']//a[@id='nav-logo-sprites']")
    private WebElement amazonLogo;

    @FindBy(xpath = "//div[@class='nav-fill']//input[@id='twotabsearchtextbox']")
    private WebElement searchInput;

    @FindBy(xpath = "//div[@class='nav-right']//div[@class='nav-search-submit nav-sprite']")
    private WebElement searchButton;

    @FindBy(xpath = "//div[@id='nav-global-location-slot']//span[@id='glow-ingress-line2']")
    private WebElement autoLocation;

    @FindBy(xpath = "//div[@class='nav-right']//a[@id='nav-link-accountList']")
    private WebElement signinButton;

    @FindBy(xpath = "//span[@id='nav-cart-count']")
    private WebElement initProductNumberInCart;

    public HomePage(WebDriver driver) {
        super(driver);
        driver.get(ConfigReader.getProperty("url"));
    }

    public HomePage goToPage() {
        getDriver().get(ConfigReader.getProperty("url"));
        return this;
    }

    public ResultPage search(String product) {
        sendKeys(searchInput, product);
        click(searchButton);
        return new ResultPage(getDriver());
    }

    public boolean isLogoPresent() {
        return isElementVisible(amazonLogo);
    }

    public String getAutoLocationText() {
        return getWebElementText(autoLocation);
    }

    public String getSearchBarPlaceholder() {
        return getWebElementPlaceholder(searchInput);
    }

    public SigninPage goToSigninPage() {
        signinButton.click();
        return new SigninPage(getDriver());
    }

    public boolean isInitProductNumberInCartEqualsToZero() {
        return Integer.parseInt(getWebElementText(initProductNumberInCart)) == 0;
    }
}
