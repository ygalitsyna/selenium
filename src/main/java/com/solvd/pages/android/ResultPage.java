package com.solvd.pages.android;

import com.solvd.pages.common.ProductPageBase;
import com.solvd.pages.common.ResultPageBase;
import com.zebrunner.carina.utils.factory.DeviceType;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import java.util.List;

@DeviceType(pageType = DeviceType.Type.ANDROID_PHONE, parentClass = ResultPageBase.class)
public class ResultPage extends ResultPageBase {
    private static final Logger LOGGER = LogManager.getLogger(com.solvd.pages.desktop.ResultPage.class);
    private static final String THIS_METHOD_IS_DEFINED_ONLY_IN_DESKTOP = "This method is not yet implemented for Android";

    //@FindBy(xpath = ".//div[@class='a-section']//h2//a")
    //@FindBy(xpath = "//div[@data-component-type='s-search-result']//a[@class='a-link-normal s-faceout-link a-text-normal']")
    @FindBy(xpath = "//div[@data-component-type='s-search-result']//div[@class='sg-col sg-col-7-of-12 sg-col-7-of-16 sg-col-7-of-20 sg-col-7-of-24 puis-col-expand-last-child']//div//div//span//a//div//h2")
    //@FindBy(xpath = "//div[@class='sg-row']//div//div[@class='sg-col-inne']//div//span//a")
    private List<ExtendedWebElement> resultList;

//    @FindBy(xpath = "//span[contains(text(),'results for')]")
//    private ExtendedWebElement resultsNumberOnPage;

    public ResultPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isResultListEmpty() {
        waitForJSToLoad();
        if (resultList.isEmpty()) {
            LOGGER.warn("Result list is empty");
            return true;
        }
        System.out.println("Resultlist size = " + resultList.size());
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
        throw new UnsupportedOperationException(THIS_METHOD_IS_DEFINED_ONLY_IN_DESKTOP);
    }

    @Override
    public int getActualNumberOfResultsOnPage(List<ExtendedWebElement> resultList) {
        throw new UnsupportedOperationException(THIS_METHOD_IS_DEFINED_ONLY_IN_DESKTOP);
    }

    @Override
    public boolean isResultsNumberOnPageCorrect() {
        throw new UnsupportedOperationException(THIS_METHOD_IS_DEFINED_ONLY_IN_DESKTOP);
    }

//    @Override
//    public boolean isAllResultsMatchCondition(String searchCondition) {
//        for (ExtendedWebElement element : resultList) {
//            if (!element.getText().toLowerCase().contains(searchCondition.toLowerCase())) {
//                return false;
//            }
//        }
//        return true;
//    }

    @Override
    public boolean isAllResultsMatchCondition(String searchCondition) {
        boolean isMatch = true;
        for (ExtendedWebElement element : resultList) {
            if (!element.getText().toLowerCase().contains(searchCondition.toLowerCase())) {
                System.out.println("Error!!!!");
                System.out.println(element.getText());
                isMatch = false;
                return isMatch;
            }
        }
        return isMatch;
    }

    @Override
    public String getProductLinkForFirstProduct() {
        return resultList.get(0).getAttribute("href");
    }

    @Override
    public ProductPageBase openProductPageByLink(String link) {
        getDriver().get(link);
        return initPage(getDriver(), ProductPageBase.class);
    }
}
