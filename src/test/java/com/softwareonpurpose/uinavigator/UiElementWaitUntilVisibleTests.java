package com.softwareonpurpose.uinavigator;

import com.softwareonpurpose.uinavigator.web.WebUiHost;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

@Test
public class UiElementWaitUntilVisibleTests {
    @AfterMethod(alwaysRun = true)
    public void terminate() {
        WebUiHost.quitInstance();
    }

    @Test
    public void testWaitUntilVisible() {
        MockView.directNav();
        UiElement element = UiElement.getInstance("element", UiLocatorType.ID, "name");
        boolean actual = element.waitUntilVisible();
        Assert.assertTrue(actual, "Failed to return true when element is nonexistent");
    }
}
