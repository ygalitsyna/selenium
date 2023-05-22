package com.solvd.pages;

import com.solvd.utils.ConfigReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends AbstractPage {

    @FindBy(id = "nav-logo-sprites")
    private WebElement amazonLogo;

    @FindBy(id = "twotabsearchtextbox")
    private WebElement searchInput;

    @FindBy(id = "nav-search-submit-button")
    private WebElement searchButton;

    @FindBy(id = "glow-ingress-line2")
    private WebElement autoLocation;

    @FindBy(id = "nav-link-accountList-nav-line-1")
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
        signinButton.click();
        return new SigninPage(getDriver());
    }
}
