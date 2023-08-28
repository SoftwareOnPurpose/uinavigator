package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.UiLocatorType;
import com.softwareonpurpose.uinavigator.UiElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

@Test
public class WebGetListByLocatorTests {
    @AfterMethod(alwaysRun = true)
    public void terminate() {
        WebUiHost.quitInstance();
    }

    @Test
    public void testConstructor_typeClass() {
        String description = "Any Class";
        Class<WebUiGetElementListByLocator> expected = WebUiGetElementListByLocator.class;
        //noinspection rawtypes
        Class actual = WebUiGetElementListByLocator.getInstance(description, UiLocatorType.CLASS, "any").getClass();
        Assert.assertEquals(actual, expected, "Failed to get an instance of WebGetListByLocator");
    }

    @Test
    public void testConstructor_typeId() {
        String description = "Any Id";
        Class<WebUiGetElementListByLocator> expected = WebUiGetElementListByLocator.class;
        //noinspection rawtypes
        Class actual = WebUiGetElementListByLocator.getInstance(description, UiLocatorType.ID, "any").getClass();
        Assert.assertEquals(actual, expected, "Failed to get an instance of WebGetListByLocator");
    }

    @Test
    public void testExecute_tagOption() {
        String description = "Option Tag";
        String uri = "file:///C:/Users/craig/Documents/git/uinavigator/src/test/resources/MockPage.html";
        WebUiHost.getInstance().load(uri);
        final String locatorValue = "option";
        WebUiGetElementListByLocator listBehavior =
                WebUiGetElementListByLocator.getInstance(description, UiLocatorType.TAG, locatorValue);
        Class<UiElement> expected = UiElement.class;
        //noinspection rawtypes
        Class actual = listBehavior.execute().iterator().next().getClass();
        Assert.assertEquals(actual, expected, "Failed to return a Collection of WebUiElement");
    }
}
