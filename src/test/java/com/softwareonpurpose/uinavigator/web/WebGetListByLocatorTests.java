package com.softwareonpurpose.uinavigator.web;

import org.openqa.selenium.By;
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
        Class expected = WebGetListByLocator.class;
        Class actual = WebGetListByLocator.getInstance(new By.ByClassName("any")).getClass();
        Assert.assertEquals(actual, expected, "Failed to get an instance of WebGetListByLocator");
    }

    @Test
    public void testConstructor_typeId() {
        Class expected = WebGetListByLocator.class;
        Class actual = WebGetListByLocator.getInstance(new By.ById("any")).getClass();
        Assert.assertEquals(actual, expected, "Failed to get an instance of WebGetListByLocator");
    }

    @Test
    public void testExecute_tagOption() {
        Class expected = WebUiElement.class;
        String uri = "file:///C:/Users/craig/Documents/git/uinavigator/src/test/resources/MockPage.html";
        WebUiHost.getInstance().load(uri);
        WebGetListByLocator listBehavior = WebGetListByLocator.getInstance(new By.ByTagName("option"));
        Class actual = listBehavior.execute().iterator().next().getClass();
        Assert.assertEquals(actual, expected, "Failed to return a Collection of WebUiElement");
    }

    @Test
    public void testExecute_tagBody() {
        Class expected = WebUiElement.class;
        String uri = "file:///C:/Users/craig/Documents/git/uinavigator/src/test/resources/MockPage.html";
        WebUiHost.getInstance().load(uri);
        WebGetListByLocator listBehavior = WebGetListByLocator.getInstance(new By.ByTagName("body"));
        Class actual = listBehavior.execute().iterator().next().getClass();
        Assert.assertEquals(actual, expected, "Failed to return a Collection of WebUiElement");
    }
}
