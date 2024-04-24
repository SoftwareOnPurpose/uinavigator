package com.softwareonpurpose.uinavigator;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@Test
public class UiHostTests {
    private static final TestResource resources = TestResource.getInstance();

    @DataProvider
    public static Object[][] scenarios_load_result() {
        return new Object[][]{
                {"https://www.google.com/search", true}
                , {resources.getPageUrl("nonexistent"), false}
        };
    }

    @DataProvider
    public static Object[][] scenarios_currentUrl() {
        String url_localHtml = resources.getPageUrl("redirected");
        String url_localHtmlExpected = url_localHtml.replace("file:/", "file:///");
        String url_withQueryString = "https://www.google.com/search?q=html";
        String url_withQueryStringExpected = "https://www.google.com/search";
        return new Object[][]{
                {url_withQueryString, url_withQueryStringExpected, true}
                , {url_localHtml, url_localHtmlExpected, false}
        };
    }

    @AfterMethod
    public void terminate() {
        UiNavigator.getInstance().quitDriver();
    }

    @Test(dataProvider = "scenarios_load_result")
    public void load_result(String url, Boolean expected) {
        UiHost host = UiHost.getInstance();
        Boolean actual = host.load(url);
        Assert.assertEquals(actual, expected);
    }

    @Test(dataProvider = "scenarios_currentUrl")
    public void load_currentUrl(String url, String urlDestination, Boolean expected) {
        UiHost host = UiHost.getInstance();
        host.load(url);
        String currentUrl = host.getCurrentUrl();
        Boolean actual = currentUrl.contains(urlDestination);
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void getTitle() {
        String expected = "My First HTML";
        UiHost browser = UiHost.getInstance();
        browser.load(TestResource.getInstance().getPageUrl("head"));
        String actual = browser.getTitle();
        Assert.assertEquals(actual, expected);
    }
}
