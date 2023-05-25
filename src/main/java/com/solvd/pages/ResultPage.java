package com.solvd.pages;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ResultPage extends AbstractPage {
    private static final Logger LOGGER = LogManager.getLogger(ResultPage.class);

    @FindBy(xpath = ".//div[@class='a-section']//h2//a")
    private List<ExtendedWebElement> resultList;

    @FindBy(xpath = "//span[contains(text(),'results for')]")
    private ExtendedWebElement resultsNumberOnPage;

    public ResultPage(WebDriver driver) {
        super(driver);
    }

    public boolean isResultListEmpty() {
        if (resultList.isEmpty()) {
            LOGGER.warn("Result list is empty");
            return true;
        }
        printElementsInConsole();
        return false;
    }

    public void printElementsInConsole() {
        for (ExtendedWebElement element : resultList) {
            System.out.println(element.getText());
        }
    }

    public int getExpectedNumberOfResultsOnPage(ExtendedWebElement resultsNumberOnPage) {
        String expectedNumberInString = resultsNumberOnPage.getText();
        String[] array = (expectedNumberInString.split("-"))[1].split(" ");
        int expectedNumber = Integer.parseInt(array[0]);
        LOGGER.info("Expected number of results in resultlist is {}", expectedNumber);
        return expectedNumber;
    }

    public int getActualNumberOfResultsOnPage(List<ExtendedWebElement> resultList) {
        int actualNumber = resultList.size();
        LOGGER.info("Actual number of results in resultlist is {}", actualNumber);
        return actualNumber;
    }

    public boolean isResultsNumberOnPageCorrect() {
        return (getExpectedNumberOfResultsOnPage(resultsNumberOnPage) == getActualNumberOfResultsOnPage(resultList));
    }

    public boolean isAllResultsMatchCondition(String searchCondition) {
        for (ExtendedWebElement element : resultList) {
            if (!element.getText().toLowerCase().contains(searchCondition.toLowerCase())) {
                return false;
            }
        }
        return true;
    }

    public String getProductLinkForSecondProduct() {
        return resultList.get(1).getAttribute("href");
    }

    public ProductPage openProductPageByLink(String link) {
        getDriver().get(link);
        return new ProductPage(getDriver());
    }
}
