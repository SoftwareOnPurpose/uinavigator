package com.softwareonpurpose.uinavigator;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@Test
public class UiElementActionTests {
    private static final TestResources resources = TestResources.getInstance();

    @DataProvider
    public static Object[][] scenarios_click() {
        final UiElement byTagPNotClickable =
                UiElement.getInstance("tag 'p'", UiLocatorType.TAG, "p");
        final UiElement byTagA =
                UiElement.getInstance("'Anchor' tag", UiLocatorType.TAG, "a");
        final UiElement byTagAOrdinal2 =
                UiElement.getInstance("'Anchor' tag #2", UiLocatorType.TAG, "a", 2);
        final String basicPage = "basic";
        final String linkPage = "link";
        return new Object[][]{
                {basicPage, byTagPNotClickable, "basic"}
                , {basicPage, byTagA, "basic"}
                , {linkPage, byTagA, "https://www.google.com/"}
                , {linkPage, byTagAOrdinal2, "https://www.youtube.com/"}
        };
    }

    @AfterMethod
    public void terminate() {
        UiNavigator.getInstance().quitDriver();
    }

    @Test(dataProvider = "scenarios_click")
    public void click(String page, UiElement element, String expected) {
        expected = expected.contains("http")
                ? expected
                : resources.getPageUrl(expected).replace("file:/", "file:///");
        UiHost.getInstance().load(resources.getPageUrl(page));
        element.click();
        String actual = UiHost.getInstance().getCurrentUrl();
        Assert.assertEquals(actual, expected);
    }
}