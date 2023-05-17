package com.solvd.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends AbstractPage {

    @FindBy(css = "#twotabsearchtextbox")
    private WebElement searchInput;

    @FindBy(css = "#nav-search-submit-button")
    private WebElement searchButton;

    public HomePage(WebDriver driver){
        super(driver);
    }

    public ResultPage search(String product){
        sendKeys(searchInput, product);
        click(searchButton);
        return new ResultPage(getDriver());
    }
}
