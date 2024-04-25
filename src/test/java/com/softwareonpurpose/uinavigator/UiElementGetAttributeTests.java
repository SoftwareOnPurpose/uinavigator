package com.softwareonpurpose.uinavigator;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@Test
public class UiElementGetAttributeTests {
    private static final TestResource RESOURCE = TestResource.getInstance();
    private static final UiElement BY_TAG_P =
            UiElement.getInstance("tag 'p'", UiLocatorType.TAG, "p");
    private static final UiElement BY_TAG_A =
            UiElement.getInstance("'Anchor' tag", UiLocatorType.TAG, "a");
    private static final UiElement BY_TAG_IMG =
            UiElement.getInstance("'Image' element", UiLocatorType.TAG, "img");
    private static final UiElement BY_TAG_NONEXISTENT =
            UiElement.getInstance("tag nonexistent", UiLocatorType.TAG, "nonexistent");
    private static final String STYLE_PAGE = "style";
    private static final String IMAGE_PAGE = "image";

    @DataProvider
    public static Object[][] scenarios_getHref() {
        final String basicPage = "basic";
        final String linkPage = "link";
        return new Object[][]{
                {linkPage, BY_TAG_P, null}
                , {basicPage, BY_TAG_A, null}
                , {linkPage, BY_TAG_A, "https://www.google.com/"}
        };
    }

    @DataProvider
    public static Object[][] scenarios_getAttribute() {
        return new Object[][]{
                {IMAGE_PAGE, BY_TAG_NONEXISTENT, "src", null}
                , {IMAGE_PAGE, BY_TAG_IMG, "bogus", null}
                , {IMAGE_PAGE, BY_TAG_IMG, "src", "https://www.w3schools.com/html/w3schools.jpg"}
                , {IMAGE_PAGE, BY_TAG_IMG, "alt", "W3Schools.com"}
                , {IMAGE_PAGE, BY_TAG_IMG, "width", "104"}
                , {STYLE_PAGE, BY_TAG_P, "style", "color: red;"}
        };
    }

    @DataProvider
    public static Object[][] scenarios_getStyleProperty() {
        String color = "color";
        String bogus = "bogus";
        String red = "rgba(255, 0, 0, 1)";
        return new Object[][]{
                {BY_TAG_A, color, null}
                , {BY_TAG_P, bogus, ""}
                , {BY_TAG_P, color, red}
        };
    }

    @AfterMethod
    public void terminate() {
        UiNavigator.getInstance().quitDriver();
    }

    @Test(dataProvider = "scenarios_getHref")
    public void getHref(String page, UiElement element, String expected) {
        UiHost.getInstance().load(RESOURCE.getPageUrl(page));
        String actual = element.getHref();
        Assert.assertEquals(actual, expected);
    }

    @Test(dataProvider = "scenarios_getAttribute")
    public void getAttribute(String page, UiElement element, String attribute, String expected) {
        UiHost.getInstance().load(RESOURCE.getPageUrl(page));
        String actual = element.getAttribute(attribute);
        Assert.assertEquals(actual, expected);
    }

    @Test(dataProvider = "scenarios_getStyleProperty")
    public void getStyleProperty(UiElement element, String styleProperty, String expected) {
        UiHost.getInstance().load(RESOURCE.getPageUrl(STYLE_PAGE));
        String actual = element.getStyleProperty(styleProperty);
        Assert.assertEquals(actual, expected);
    }
}