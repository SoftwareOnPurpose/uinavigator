package com.softwareonpurpose.uinavigator;

import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

public class GetByCssFromAncestorOrdinal extends GetWebElementBehavior {
    private final UiElement ancestor;

    protected GetByCssFromAncestorOrdinal(String locatorType, String locatorValue, UiElement ancestor) {
        super(locatorType, locatorValue, null, ancestor);
        this.ancestor = ancestor;
    }

    public static GetByCssFromAncestorOrdinal getInstance(String locatorType, String locatorValue, UiElement ancestor) {
        return new GetByCssFromAncestorOrdinal(locatorType, locatorValue, ancestor);
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
