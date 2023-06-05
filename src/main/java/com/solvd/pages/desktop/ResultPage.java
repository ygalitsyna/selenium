package com.solvd.pages.desktop;

import com.solvd.pages.common.ProductPageBase;
import com.solvd.pages.common.ResultPageBase;
import com.zebrunner.carina.utils.factory.DeviceType;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import java.util.List;

@DeviceType(pageType = DeviceType.Type.DESKTOP, parentClass = ResultPageBase.class)
public class ResultPage extends ResultPageBase {
    private static final Logger LOGGER = LogManager.getLogger(ResultPage.class);

    @FindBy(xpath = ".//div[@class='a-section']//h2//a")
    private List<ExtendedWebElement> resultList;

    @FindBy(xpath = "//span[contains(text(),'results for')]")
    private ExtendedWebElement resultsNumberOnPage;

    public ResultPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isResultListEmpty() {
        if (resultList.isEmpty()) {
            LOGGER.warn("Result list is empty");
            return true;
        }
        printElementsInConsole();
        return false;
    }

    @Override
    public void printElementsInConsole() {
        for (ExtendedWebElement element : resultList) {
            System.out.println(element.getText());
        }
    }

    @Override
    public int getExpectedNumberOfResultsOnPage(ExtendedWebElement resultsNumberOnPage) {
        String expectedNumberInString = resultsNumberOnPage.getText();
        String[] array = (expectedNumberInString.split("-"))[1].split(" ");
        int expectedNumber = Integer.parseInt(array[0]);
        LOGGER.info("Expected number of results in resultlist is {}", expectedNumber);
        return expectedNumber;
    }

    @Override
    public int getActualNumberOfResultsOnPage(List<ExtendedWebElement> resultList) {
        int actualNumber = resultList.size();
        LOGGER.info("Actual number of results in resultlist is {}", actualNumber);
        return actualNumber;
    }

    @Override
    public boolean isResultsNumberOnPageCorrect() {
        return (getExpectedNumberOfResultsOnPage(resultsNumberOnPage) == getActualNumberOfResultsOnPage(resultList));
    }

    @Override
    public boolean isAllResultsMatchCondition(String searchCondition) {
        boolean isMatch = true;
        for (ExtendedWebElement element : resultList) {
            if (!element.getText().toLowerCase().contains(searchCondition.toLowerCase())) {
                isMatch = false;
                return isMatch;
            }
        }
        return isMatch;
    }

    @Override
    public ProductPageBase goToFirstProductPage() {
        getDriver().get(resultList.get(1).getAttribute("href"));
        return initPage(getDriver(), ProductPageBase.class);
    }
}
