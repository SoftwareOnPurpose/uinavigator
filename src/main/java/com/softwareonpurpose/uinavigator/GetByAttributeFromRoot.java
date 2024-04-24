package com.softwareonpurpose.uinavigator;

import org.openqa.selenium.WebElement;

public class GetByAttributeFromRoot extends GetWebElementBehavior {
    private GetByAttributeFromRoot(String locatorType, String locatorValue, String attribute, String attributeValue, Integer ordinal, UiElement ancestor) {
        super(locatorType, locatorValue, /*attribute, attributeValue,*/ ordinal, ancestor);
    }

    @Override
    WebElement execute() {
        return null;
    }
}
