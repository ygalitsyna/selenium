package com.solvd.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class HomePage {

    WebDriver driver;
    @FindBy(id = "twotabsearchtextbox")
    WebElement searchInput;
    @FindBy(id = "nav-search-submit-button")
    WebElement searchButton;

    //@FindBy(xpath = "//span[contains(text(),\"Don't Change\")]")
    //WebElement dontChangeButton;
    public HomePage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public ResultPage search(String product){
        //dontChangeButton.click();
        //searchInput.click();
        searchInput.sendKeys(product);
        searchButton.click();
        return new ResultPage(driver);
    }
}
