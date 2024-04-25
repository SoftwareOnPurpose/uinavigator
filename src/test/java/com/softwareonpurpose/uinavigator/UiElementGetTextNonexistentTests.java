package com.softwareonpurpose.uinavigator;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@Test
public class UiElementGetTextNonexistentTests {
    private static final TestResource RESOURCE = TestResource.getInstance();
    private static final String ID_PAGE = "id";
    private static final UiElement BY_ID_NONEXISTENT =
            UiElement.getInstance("id nonexistent", UiLocatorType.ID, "nonexistent");
    private static final UiElement BY_TAG_NONEXISTENT =
            UiElement.getInstance("tag nonexistent", UiLocatorType.TAG, "nonexistent");

    @DataProvider
    public static Object[][] scenarios() {
        return new Object[][]{
                {ID_PAGE, BY_ID_NONEXISTENT, null},
        };
    }

    @Test(dataProvider = "scenarios")
    public void getText(String page, UiElement element, String expected){
        UiHost.getInstance().load(RESOURCE.getPageUrl(page));
        String actual = element.getText();
        Assert.assertEquals(actual, expected);
    }

    @AfterMethod
    public void terminate() {
        UiNavigator.getInstance().quitDriver();
    }
}
