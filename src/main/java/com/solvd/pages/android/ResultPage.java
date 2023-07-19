package com.solvd.pages.android;

import com.solvd.pages.common.ProductPageBase;
import com.solvd.pages.common.ResultPageBase;
import com.zebrunner.carina.utils.Configuration;
import com.zebrunner.carina.utils.R;
import com.zebrunner.carina.utils.factory.DeviceType;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import java.util.List;

@DeviceType(pageType = DeviceType.Type.ANDROID_PHONE, parentClass = ResultPageBase.class)
public class ResultPage extends ResultPageBase {
    private static final Logger LOGGER = LogManager.getLogger(com.solvd.pages.desktop.ResultPage.class);
    private static final String THIS_METHOD_IS_DEFINED_ONLY_FOR_DESKTOP = "This method is not yet implemented for Android";

    @FindBy(xpath = ".//div[@class='a-section a-spacing-small a-spacing-top-small puis-padding-right-small']/span/a/div/h2")
    private List<ExtendedWebElement> resultList;

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
    public String getProductLinkForFirstProduct() {
      ExtendedWebElement e = findExtendedWebElement(By.xpath(".//div[@id='search']/span/div[@class='s-main-slot s-result-list s-search-results sg-row']/div[4]//div[@class='sg-col sg-col-7-of-12 sg-col-7-of-16 sg-col-7-of-20 sg-col-7-of-24 puis-col-expand-last-child']/div/div/span/a"));
        String link = R.CONFIG.get(Configuration.Parameter.URL.getKey()) + e.getAttribute("href");
        return link;
    }

    @Override
    public ProductPageBase openProductPageByLink(String link) {
        getDriver().get(link);
        return initPage(getDriver(), ProductPageBase.class);
    }
}
