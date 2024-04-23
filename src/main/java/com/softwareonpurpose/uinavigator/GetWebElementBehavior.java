package com.softwareonpurpose.uinavigator;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

public abstract class GetWebElementBehavior {
    protected final By.ByCssSelector locator;
    private final String css;
    protected final int ordinal;

    protected GetWebElementBehavior(String locatorType, String locatorValue, Integer ordinal, UiElement ancestor) {
        this.css = composeCss(locatorType, locatorValue, ancestor);
        this.ordinal = ordinal == null || ordinal < 0 ? 0 : ordinal;
        this.locator = new By.ByCssSelector(this.css);
    }

    private static String composeCss(String locatorType, String locatorValue, UiElement ancestor) {
        String ancestorCss = ancestor == null ? "" : ancestor.getCss();
        return String.format("%s %s", ancestorCss, String.format("%s%s", locatorType, locatorValue));
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
