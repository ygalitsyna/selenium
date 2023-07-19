package com.solvd.pages.common;

import com.zebrunner.carina.utils.mobile.IMobileUtils;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.WebDriver;

import java.util.List;

public abstract class ResultPageBase extends AbstractPage implements IMobileUtils {
    public ResultPageBase(WebDriver driver) {
        super(driver);
    }

    public abstract boolean isResultListEmpty();

    public abstract void printElementsInConsole();

    public abstract int getExpectedNumberOfResultsOnPage(ExtendedWebElement resultsNumberOnPage);

    public abstract int getActualNumberOfResultsOnPage(List<ExtendedWebElement> resultList);

    public abstract boolean isResultsNumberOnPageCorrect();

    public abstract boolean isAllResultsMatchCondition(String searchCondition);

    public abstract ProductPageBase goToFirstProductPage();
}
