package com.softwareonpurpose.uinavigator;

import com.google.gson.Gson;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;

public class UiElement {
    private static Logger logger;
    @SuppressWarnings({"FieldCanBeLocal", "unused"})
    private final String description;
    private final transient GetWebElementBehavior getElementBehavior;
    @SuppressWarnings({"FieldCanBeLocal", "unused"})
    private final String getElementBehaviorName;

    private UiElement(String description, GetWebElementBehavior getElementBehavior) {
        this.description = description;
        this.getElementBehavior = getElementBehavior;
        this.getElementBehaviorName = this.getElementBehavior.getClass().getSimpleName();
    }

    public static UiElement getInstance(String description, String locatorType, String locatorValue) {
        GetWebElementBehavior getElementBehavior = GetFromRoot.getInstance(locatorType, locatorValue);
        return new UiElement(description, getElementBehavior);
    }

    public static UiElement getInstance(String description, String locatorType, String locatorValue, int ordinal) {
        GetWebElementBehavior getElementBehavior = GetByOrdinal.getInstance(locatorType, locatorValue, ordinal);
        return new UiElement(description, getElementBehavior);
    }

    public static UiElement getInstance(String description, String locatorType, String locatorValue, UiElement ancestor) {
        GetWebElementBehavior getElementBehavior = GetFromAncestor.getInstance(locatorType, locatorValue, ancestor);
        return new UiElement(description, getElementBehavior);
    }

    public static UiElement getInstance(String description, String locatorType, String locatorValue, int ordinal, UiElement ancestor) {
        GetWebElementBehavior getElementBehavior = GetByOrdinalFromAncestor.getInstance(locatorType, locatorValue, ordinal, ancestor);
        return new UiElement(description, getElementBehavior);
    }

    public static UiElement getInstance(String description, String locatorType, String locatorValue, String attribute, String attributeValue) {
        GetWebElementBehavior getElementBehavior = GetByAttribute.getInstance(locatorType, locatorValue, attribute, attributeValue);
        return new UiElement(description, getElementBehavior);
    }

//    public static UiElement getInstance(String description, String locatorType, String locatorValue, String attribute, String attributeValue, UiElement ancestor) {
//        GetWebElementBehavior getElementBehavior = GetByAttributeFromAncestor.getInstance(locatorType, locatorValue, attribute, attributeValue, ancestor);
//        return new UiElement(description, getElementBehavior);
//    }

    boolean isByOrdinal() {
        return getElementBehavior.isByOrdinal();
    }

    public boolean isDisplayed() {
        WebElement element = getElement();
        return element != null && element.isDisplayed();
    }

    public String getText() {
        WebElement element = getElement();
        return element == null ? null : element.getText();
    }

    protected WebElement getElement() {
        return getElementBehavior == null ? null : getElementBehavior.execute();
    }

    String getCss() {
        return getElementBehavior.getCss();
    }

    @Override
    public String toString() {
        return String.format("UiElement: %s", new Gson().toJson(this));
    }

    public String getHref() {
        return getAttribute("href");
    }

    public void click() {
        getLogger().info(String.format("Click %s ...", description));
        WebElement element = getElement();
        if (element == null) {
            getLogger().warn(String.format("%s NOT FOUND using %s", description, getElementBehavior.getLocatorDescription()));
        } else {
            try {
                element.click();
            } catch (Exception e) {
                getLogger().warn(String.format("UNABLE TO CLICK %s using %s", description, getElementBehavior.getLocatorDescription()));
            }
        }
    }

    private Logger getLogger() {
        if (logger == null) {
            logger = LogManager.getLogger("");
        }
        return logger;
    }

    public String getAttribute(String attribute) {
        WebElement element = getElement();
        return element == null ? null : element.getAttribute(attribute);
    }

    public String getStyleProperty(String property) {
        WebElement element = getElement();
        return element == null ? null : element.getCssValue(property);
    }

    public String getDescription() {
        return description;
    }
}
