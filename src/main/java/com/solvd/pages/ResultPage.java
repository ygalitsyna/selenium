package com.solvd.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ResultPage {
    private WebDriver driver;

    @FindBy(xpath = "//span[@class ='a-size-medium a-color-base a-text-normal']")
    private List<WebElement> resultList;

    @FindBy(xpath = "//span[contains(text(),'results for')]")
    private WebElement resultsNumberOnPage;

    public ResultPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean isResultListEmpty(){
        if(resultList.isEmpty()){
            return true;
        }
        printElementsInConsole();
        return false;
    }

    public void printElementsInConsole(){
        for (WebElement element : resultList) {
            System.out.println(element.getText());
        }
    }

    public int getExpectedNumberOfResultsOnPage(WebElement resultsNumberOnPage){
        String expectedNumberInString = resultsNumberOnPage.getText();
        String[] array = (expectedNumberInString.split("-"))[1].split(" ");
        int expectedNumber = Integer.parseInt(array[0]);
        return expectedNumber;
    }

    public int getActualNumberOfResultsOnPage(List<WebElement> resultList){
        int actualNumber = resultList.size();
        return actualNumber;
    }

    public boolean isResultsNumberOnPageCorrect(){
        if(getExpectedNumberOfResultsOnPage(resultsNumberOnPage) == getActualNumberOfResultsOnPage(resultList)){
            return true;
        }
        return false;
    }
}
