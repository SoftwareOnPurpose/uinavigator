package com.softwareonpurpose.uinavigator;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

@Test
public class UiNavigatorTests {
    @AfterMethod
    public void terminate() {
        UiNavigator.getInstance().quitDriver();
    }

    @Test(enabled = false)
    public void getInstance() {
        UiNavigator expected = UiNavigator.getInstance();
        UiNavigator actual = UiNavigator.getInstance();
        Assert.assertEquals(actual, expected);
    }
}
