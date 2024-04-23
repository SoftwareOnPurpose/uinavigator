package com.softwareonpurpose.uinavigator;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@Test
public class UiElementGetAttributeTests {
    private static final TestResources resources = TestResources.getInstance();
    private static final UiElement byTagP =
            UiElement.getInstance("tag 'p'", UiLocatorType.TAG, "p");
    private static final UiElement byTagA =
            UiElement.getInstance("'Anchor' tag", UiLocatorType.TAG, "a");
    private static final String stylePage = "style";

    @DataProvider
    public static Object[][] scenarios_getHref() {
        final String basicPage = "basic";
        final String linkPage = "link";
        return new Object[][]{
                {linkPage, byTagP, null}
                , {basicPage, byTagA, null}
                , {linkPage, byTagA, "https://www.google.com/"}
        };
    }

    @DataProvider
    public static Object[][] scenarios_getAttribute() {
        final String imagePage = "image";
        final UiElement byTagNonexistent =
                UiElement.getInstance("tag nonexistent", UiLocatorType.TAG, "nonexistent");
        final UiElement byTagImg =
                UiElement.getInstance("'Image' element", UiLocatorType.TAG, "img");
        return new Object[][]{
                {imagePage, byTagNonexistent, "src", null}
                , {imagePage, byTagImg, "bogus", null}
                , {imagePage, byTagImg, "src", "https://www.w3schools.com/html/w3schools.jpg"}
                , {imagePage, byTagImg, "alt", "W3Schools.com"}
                , {imagePage, byTagImg, "width", "104"}
                , {stylePage, byTagP, "style", "color: red;"}
        };
    }

    @DataProvider
    public static Object[][] scenarios_getStyleProperty() {
        String color = "color";
        String bogus = "bogus";
        String red = "rgba(255, 0, 0, 1)";
        return new Object[][]{
                {byTagA, color, null}
                , {byTagP, bogus, ""}
                , {byTagP, color, red}
        };
    }

    @AfterMethod
    public void terminate() {
        UiNavigator.getInstance().quitDriver();
    }

    @Test(dataProvider = "scenarios_getHref")
    public void getHref(String page, UiElement element, String expected) {
        UiHost.getInstance().load(resources.getPageUrl(page));
        String actual = element.getHref();
        Assert.assertEquals(actual, expected);
    }

    @Test(dataProvider = "scenarios_getAttribute")
    public void getAttribute(String page, UiElement element, String attribute, String expected) {
        UiHost.getInstance().load(resources.getPageUrl(page));
        String actual = element.getAttribute(attribute);
        Assert.assertEquals(actual, expected);
    }

    @Test(dataProvider = "scenarios_getStyleProperty")
    public void getStyleProperty(UiElement element, String styleProperty, String expected) {
        UiHost.getInstance().load(resources.getPageUrl(stylePage));
        String actual = element.getStyleProperty(styleProperty);
        Assert.assertEquals(actual, expected);
    }
}