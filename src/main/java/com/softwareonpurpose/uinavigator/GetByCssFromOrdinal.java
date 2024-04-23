package com.softwareonpurpose.uinavigator;

import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

public class GetByCssFromOrdinal extends GetWebElementBehavior {
    private final UiElement ancestor;

    protected GetByCssFromOrdinal(String locatorType, String locatorValue, UiElement ancestor) {
        super(locatorType, locatorValue, null, ancestor);
        this.ancestor = ancestor;
    }

    public static GetByCssFromOrdinal getInstance(String locatorType, String locatorValue, UiElement ancestor) {
        return new GetByCssFromOrdinal(locatorType, locatorValue, ancestor);
    }

    @Override
    WebElement execute() {
        try {
            return ancestor.getElement().findElement(locator);
        } catch (NoSuchElementException e) {
            LogManager.getLogger("").warn(extractExceptionMessage(e));
            return null;
        }
    }
}
