package com.softwareonpurpose.uinavigator;

import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

public class GetByCssFromAncestor extends GetWebElementBehavior {
    private GetByCssFromAncestor(String locatorType, String locatorValue, UiElement ancestor) {
        super(locatorType, locatorValue, null, ancestor);
    }

    public static GetByCssFromAncestor getInstance(String locatorType, String locatorValue, UiElement ancestor) {
        return new GetByCssFromAncestor(locatorType, locatorValue, ancestor);
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
