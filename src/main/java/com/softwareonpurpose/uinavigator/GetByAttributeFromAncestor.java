package com.softwareonpurpose.uinavigator;

import org.openqa.selenium.WebElement;

public class GetByAttributeFromAncestor extends GetWebElementBehavior {
    private GetByAttributeFromAncestor(String locatorType, String locatorValue, String attribute, String attributeValue, UiElement ancestor) {
        super(locatorType, locatorValue, attribute, attributeValue, null, ancestor);
    }

    @Override
    WebElement execute() {
//        WebElement ancestorElement = ancestor.getElement();
//        return ancestorElement == null ? null : ancestorElement.findElement(locator);
        return null;
    }
}
