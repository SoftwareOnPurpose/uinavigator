package com.softwareonpurpose.uinavigator;

import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

public class GetFromRoot extends GetWebElementBehavior {
    private GetFromRoot(String locatorType, String locatorValue) {
        super(locatorType, locatorValue, null, null);
    }

    public static GetFromRoot getInstance(String locatorType, String locatorValue) {
        return new GetFromRoot(locatorType, locatorValue);
    }

    @Override
    public WebElement execute() {
        try {
            return UiNavigator.getInstance().getDriver().findElement(locator);
        } catch (NoSuchElementException e) {
            LogManager.getLogger("").warn(extractExceptionMessage(e));
            return null;
        }
    }
}
