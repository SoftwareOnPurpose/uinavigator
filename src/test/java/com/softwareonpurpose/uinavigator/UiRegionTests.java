package com.softwareonpurpose.uinavigator;

import com.softwareonpurpose.uinavigator.test.view.region.UnorderedListRegion;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

@Test
public class UiRegionTests {

    private final TestResources resources = TestResources.getInstance();

    @AfterMethod
    public void terminate() {
        UiNavigator.getInstance().quitDriver();
    }

    @Test
    public void isDisplayed() {
        UiElement parent = UiElement.getInstance("Page", UiLocatorType.TAG, "body");
        UiHost.getInstance().load(resources.getPageUrl("list"));
        Boolean expected = true;
        Boolean actual = UnorderedListRegion.getInstance(parent).isDisplayed();
        Assert.assertEquals(actual, expected);
    }
}
