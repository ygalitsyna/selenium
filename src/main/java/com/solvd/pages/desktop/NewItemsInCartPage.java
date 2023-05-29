package com.solvd.pages.desktop;

import com.solvd.pages.common.CartPageBase;
import com.solvd.pages.common.NewItemsInCartPageBase;
import com.zebrunner.carina.utils.factory.DeviceType;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

@DeviceType(pageType = DeviceType.Type.DESKTOP, parentClass = NewItemsInCartPageBase.class)
public class NewItemsInCartPage extends NewItemsInCartPageBase {

    @FindBy(xpath = "//span[@id='sw-gtc']")
    private ExtendedWebElement goToCartButton;

    public NewItemsInCartPage(WebDriver driver) {
        super(driver);
    }

    public CartPageBase goToCartPage() {
        goToCartButton.click();
        return initPage(getDriver(), CartPageBase.class);
    }
}
