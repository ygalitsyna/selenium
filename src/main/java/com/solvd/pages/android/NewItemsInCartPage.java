package com.solvd.pages.android;

import com.solvd.pages.common.CartPageBase;
import com.solvd.pages.common.NewItemsInCartPageBase;
import com.zebrunner.carina.utils.factory.DeviceType;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

@DeviceType(pageType = DeviceType.Type.ANDROID_PHONE, parentClass = NewItemsInCartPageBase.class)
public class NewItemsInCartPage extends NewItemsInCartPageBase {

    @FindBy(xpath = "//span[@id='sw-gtc']")
    private ExtendedWebElement addButton;

    public NewItemsInCartPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public CartPageBase goToCartPage() {
        addButton.clickByJs();
        return initPage(getDriver(), CartPageBase.class);
    }
}
