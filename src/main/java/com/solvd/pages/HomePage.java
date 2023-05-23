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

    public HomePage(WebDriver driver) {
        super(driver);
        //driver.get(ConfigReader.getProperty("url"));
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
}
