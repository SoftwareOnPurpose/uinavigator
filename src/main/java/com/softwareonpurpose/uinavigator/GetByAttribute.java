package com.softwareonpurpose.uinavigator;

import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

public class GetByAttribute extends GetWebElementBehavior {
    private GetByAttribute(String locatorType, String locatorValue, String attribute, String attributeValue, Integer ordinal, UiElement ancestor) {
        super(locatorType, locatorValue, attribute, attributeValue, ordinal, ancestor);
    }

    public static GetWebElementBehavior getInstance(String locatorType, String locatorValue, String attribute, String attributeValue) {
        return new GetByAttribute(locatorType, locatorValue, attribute, attributeValue, null, null);
    }

    @Override
    WebElement execute() {
        try {
            return UiNavigator.getInstance().getDriver().findElement(locator);
        } catch (NoSuchElementException e) {
            LogManager.getLogger("").warn(extractExceptionMessage(e));
            return null;
        }
    }
}
