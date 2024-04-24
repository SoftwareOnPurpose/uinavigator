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
        Class expected = UiElement.class;
        UiElement parent = UiElement.getInstance("'Unordered List' element", UiLocatorType.TAG, "ul");
        Class actual = UiElement.getInstance("'List Item' element", UiLocatorType.TAG, "li", parent).getClass();
        Assert.assertEquals(actual, expected);
    }

    @SuppressWarnings("rawtypes")
    @Test
    public void getInstance_ordinal_parent() {
        Class expected = UiElement.class;
        UiElement parent = UiElement.getInstance("'Ordered List' element", UiLocatorType.TAG, "ol");
        Class actual = UiElement.getInstance("'List Item' element", UiLocatorType.TAG, "li", 2, parent).getClass();
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void getInstance_attribute(){
        Class expected = UiElement.class;
        String description = "'Attribute' element";
        String locatorType = UiLocatorType.TAG;
        String locatorValue = "img";
        String attribute = "width";
        String attributeValue = "104";
//        Class actual = UiElement.getInstance(description, locatorType, locatorValue, attribute, attributeValue);
//        Assert.assertEquals(actual, expected);
    }
}