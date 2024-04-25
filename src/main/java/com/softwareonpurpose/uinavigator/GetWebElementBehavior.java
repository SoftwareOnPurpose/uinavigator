package com.softwareonpurpose.uinavigator;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

public abstract class GetWebElementBehavior {
    protected final By.ByCssSelector locator;
    protected final int ordinal;
    private final String css;
    private final UiElement ancestor;

    protected GetWebElementBehavior(String locatorType, String locatorValue, String attribute, String attributeValue, Integer ordinal, UiElement ancestor) {
        this.css = composeCss(locatorType, locatorValue, attribute, attributeValue);
        this.ordinal = ordinal == null || ordinal < 0 ? 0 : ordinal;
        this.locator = new By.ByCssSelector(this.css);
        this.ancestor = ancestor;
    }

    private static String composeCss(String locatorType, String locatorValue, String attribute, String attributeValue) {
        String attributeCss = attribute == null ? "" : String.format("[%s='%s']", attribute, attributeValue);
        return String.format("%s%s%s", locatorType, locatorValue, attributeCss);
    }

    protected static String extractExceptionMessage(NoSuchElementException e) {
        String message = e.getMessage();
        int endIndex = message.indexOf("\n");
        return message.substring(0, endIndex);
    }

    abstract WebElement execute();

    String getLocatorDescription() {
        return locator.toString();
    }

    String getCss() {
        return ancestor.isByOrdinal() ? css : String.format("%s %s", ancestor.getCss(), css);
    }

    boolean isByOrdinal() {
        return ordinal > 0;
    }
}
