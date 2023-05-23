package com.solvd.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class ResultPage extends AbstractPage {

    @FindBy(xpath = ".//div[@class='a-section']//h2")
    private List<WebElement> resultList;

    @FindBy(xpath = "//span[contains(text(),'results for')]")
    private WebElement resultsNumberOnPage;

    public ResultPage(WebDriver driver) {
        super(driver);
    }

    public boolean isResultListEmpty() {
        WebDriverWait wait = new WebDriverWait(this.getDriver(), Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOfAllElements(resultList));
        if (resultList.isEmpty()) {
            return true;
        }
        printElementsInConsole();
        return false;
    }

    public void printElementsInConsole() {
        for (WebElement element : resultList) {
            System.out.println(element.getText());
        }
    }

    public int getExpectedNumberOfResultsOnPage(WebElement resultsNumberOnPage) {
        String expectedNumberInString = resultsNumberOnPage.getText();
        String[] array = (expectedNumberInString.split("-"))[1].split(" ");
        int expectedNumber = Integer.parseInt(array[0]);
        return expectedNumber;
    }

    public int getActualNumberOfResultsOnPage(List<WebElement> resultList) {
        int actualNumber = resultList.size();
        return actualNumber;
    }

    public boolean isResultsNumberOnPageCorrect() {
        if (getExpectedNumberOfResultsOnPage(resultsNumberOnPage) == getActualNumberOfResultsOnPage(resultList)) {
            return true;
        }
        return false;
    }
}
