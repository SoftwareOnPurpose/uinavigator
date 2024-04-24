package com.softwareonpurpose.uinavigator;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

public abstract class GetWebElementBehavior {
    protected final By.ByCssSelector locator;
    protected final int ordinal;
    private final String css;

    protected GetWebElementBehavior(String locatorType, String locatorValue, String attribute, String attributeValue, Integer ordinal, UiElement ancestor) {
        this.css = composeCss(locatorType, locatorValue, attribute, attributeValue, ancestor);
        this.ordinal = ordinal == null || ordinal < 0 ? 0 : ordinal;
        this.locator = new By.ByCssSelector(this.css);
    }

    private static String composeCss(String locatorType, String locatorValue, String attribute, String attributeValue, UiElement ancestor) {
        String ancestorCss = ancestor == null ? "" : ancestor.getCss();
        String attributeCss = attribute == null ? "" : String.format("[%s='%s']", attribute, attributeValue);
        //  img[width='104']
        return String.format("%s %s", ancestorCss, String.format("%s%s%s", locatorType, locatorValue, attributeCss));
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
        return css;
    }

    boolean isByOrdinal() {
        return ordinal > 0;
    }
}
