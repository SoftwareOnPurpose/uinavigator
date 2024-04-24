package com.softwareonpurpose.uinavigator;

import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class UiElementGetInstanceTests {
    @SuppressWarnings("rawtypes")
    @Test
    public void getInstance() {
        Class expected = UiElement.class;
        String description = "'body' element";
        String locatorType = UiLocatorType.TAG;
        String locatorValue = "body";
        Class actual = UiElement.getInstance(description, locatorType, locatorValue).getClass();
        Assert.assertEquals(actual, expected);
    }

    @SuppressWarnings("rawtypes")
    @Test
    public void getInstance_ordinal() {
        Class expected = UiElement.class;
        String description = "'paragraph' element";
        String locatorType = UiLocatorType.TAG;
        String locatorValue = "p";
        int ordinal = 2;
        Class actual = UiElement.getInstance(description, locatorType, locatorValue, ordinal).getClass();
        Assert.assertEquals(actual, expected);
    }

    @SuppressWarnings("rawtypes")
    @Test
    public void getInstance_parent() {
        String parentDescription = "'Unordered List' element";
        String parentLocatorValue = "ul";
        String description = "'List Item' element";
        String locatorValue = "li";
        UiElement parent = UiElement.getInstance(parentDescription, UiLocatorType.TAG, parentLocatorValue);
        Class actual = UiElement.getInstance(description, UiLocatorType.TAG, locatorValue, parent).getClass();
        Class expected = UiElement.class;
        Assert.assertEquals(actual, expected);
    }

    @SuppressWarnings("rawtypes")
    @Test
    public void getInstance_ordinal_parent() {
        Class expected = UiElement.class;
        String parentDescription = "'Ordered List' element";
        String parentLocatorValue = "ol";
        UiElement parent = UiElement.getInstance(parentDescription, UiLocatorType.TAG, parentLocatorValue);
        String description = "'List Item' element";
        String locatorValue = "li";
        int ordinal = 2;
        Class actual = UiElement.getInstance(description, UiLocatorType.TAG, locatorValue, ordinal, parent).getClass();
        Assert.assertEquals(actual, expected);
    }

    @SuppressWarnings("rawtypes")
    @Test
    public void getInstance_attribute() {
        Class expected = UiElement.class;
        String description = "'Attribute' element";
        String locatorType = UiLocatorType.TAG;
        String locatorValue = "img";
        String attribute = "width";
        String attributeValue = "104";
        Class actual =
                UiElement.getInstance(description, locatorType, locatorValue, attribute, attributeValue).getClass();
        Assert.assertEquals(actual, expected);
    }
}