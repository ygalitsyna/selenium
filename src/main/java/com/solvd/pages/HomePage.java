package com.solvd.pages;

import com.solvd.utils.ConfigReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends AbstractPage {

    @FindBy(css = "#nav-logo-sprites")
    private WebElement amazonLogo;

    @FindBy(css = "#twotabsearchtextbox")
    private WebElement searchInput;

    @FindBy(css = "#nav-search-submit-button")
    private WebElement searchButton;

    @FindBy(css = "#glow-ingress-line2")
    private WebElement autoLocation;

    @FindBy(css = "#nav-link-accountList-nav-line-1")
    //@FindBy(xpath = "//a[@data-nav-ref='nav_ya_signin']")
    //@FindBy(xpath = "//a[@id='nav-link-accountList']")
    //@FindBy(xpath = "//a[@class='nav-a nav-a-2   nav-progressive-attribute'][1]")
    //@FindBy(xpath = "//div[@id='nav-tools']")
    //@FindBy(xpath = "//div[@class='nav-right']/div[@id='nav-tools']/a[@id='nav-link-accountList']")
    //@FindBy(xpath = "//span[contains(text(),'Hello, sign in')]")
    private WebElement signinButton;

    public HomePage(WebDriver driver){
        super(driver);
        driver.get(ConfigReader.getProperty("url"));
    }

    public ResultPage search(String product){
        sendKeys(searchInput, product);
        click(searchButton);
        return new ResultPage(getDriver());
    }

    public boolean isLogoPresent(){
        return isElementVisible(amazonLogo);
    }

    public String getAutoLocationText(){
        return getWebElementText(autoLocation);
    }

    public String getSearchBarPlaceholder(){
        return getWebElementPlaceholder(searchInput);
    }

    public SigninPage goToSigninPage(){
        click(signinButton);
        return new SigninPage(getDriver());
    }
}
