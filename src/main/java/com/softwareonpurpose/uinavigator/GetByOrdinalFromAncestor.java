package com.softwareonpurpose.uinavigator;

import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.WebElement;

import java.util.List;

public class GetByOrdinalFromAncestor extends GetWebElementBehavior {
    private final UiElement ancestor;

    protected GetByOrdinalFromAncestor(String locatorType, String locatorValue, Integer ordinal, UiElement ancestor) {
        super(locatorType, locatorValue, null, null, ordinal, ancestor);
        this.ancestor = ancestor;
    }

    public static GetByOrdinalFromAncestor getInstance(String locatorType, String locatorValue, Integer ordinal, UiElement ancestor) {
        return new GetByOrdinalFromAncestor(locatorType, locatorValue, ordinal, ancestor);
    }

    @Override
    WebElement execute() {
        int index = ordinal - 1;
        List<WebElement> elements = ancestor.getElement().findElements(locator);
        if (index < elements.size()) {
            return elements.get(index);
        } else {
            String message = String.format("Unable to locate element %s ordinal %d", locator, ordinal);
            LogManager.getLogger("").warn(message);
            return null;
        }
    }
}
