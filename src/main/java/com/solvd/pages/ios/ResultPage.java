package com.solvd.pages.ios;

import com.solvd.pages.common.ProductPageBase;
import com.solvd.pages.common.ResultPageBase;
import com.zebrunner.carina.utils.factory.DeviceType;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.decorator.annotations.ClassChain;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import java.util.List;

@DeviceType(pageType = DeviceType.Type.IOS_PHONE, parentClass = ResultPageBase.class)
public class ResultPage extends ResultPageBase {
    private static final Logger LOGGER = LogManager.getLogger(com.solvd.pages.desktop.ResultPage.class);
    private static final String THIS_METHOD_IS_DEFINED_ONLY_FOR_DESKTOP = "This method is not yet implemented for IOS";

    @FindBy(xpath = "**/XCUIElementTypeLink[`value == '2'`]/XCUIElementTypeStaticText")
    @ClassChain
    private List<ExtendedWebElement> resultList;

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
        throw new UnsupportedOperationException(THIS_METHOD_IS_DEFINED_ONLY_FOR_DESKTOP);
    }

    @Override
    public int getActualNumberOfResultsOnPage(List<ExtendedWebElement> resultList) {
        throw new UnsupportedOperationException(THIS_METHOD_IS_DEFINED_ONLY_FOR_DESKTOP);
    }

    @Override
    public boolean isResultsNumberOnPageCorrect() {
        throw new UnsupportedOperationException(THIS_METHOD_IS_DEFINED_ONLY_FOR_DESKTOP);
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
        resultList.get(0).click();
        return initPage(getDriver(), ProductPageBase.class);
    }
}
