package com.softwareonpurpose.uinavigator;

import org.openqa.selenium.WebElement;

public class GetFromAncestor extends GetWebElementBehavior {
    private final UiElement ancestor;

    protected GetFromAncestor(String locatorType, String locatorValue, UiElement ancestor) {
        super(locatorType, locatorValue,null,null, null, ancestor);
        this.ancestor = ancestor;
    }

    public static GetFromAncestor getInstance(String locatorType, String locatorValue, UiElement ancestor) {
        return new GetFromAncestor(locatorType, locatorValue, ancestor);
    }

    @Override
    WebElement execute() {
        WebElement ancestorElement = ancestor.getElement();
        return ancestorElement == null ? null : ancestorElement.findElement(locator);
    }
}
