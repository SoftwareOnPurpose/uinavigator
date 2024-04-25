package com.softwareonpurpose.uinavigator;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@Test
public class UiElementStateNonexistentTests {
    private static final boolean IS_NOT_DISPLAYED = false;
    private static final TestResource RESOURCE = TestResource.getInstance();
    private static final String BASIC_PAGE = "basic";
    private static final String LIST_PAGE = "list";
    private static final UiElement BY_TAG_TABLE =
            UiElement.getInstance("tag 'table'", UiLocatorType.TAG, "table");
    private static final UiElement BY_ID_ROOT =
            UiElement.getInstance("id existent", UiLocatorType.ID, "view");
    private static final UiElement BY_TAG_NONEXISTENT =
            UiElement.getInstance("tag nonexistent", UiLocatorType.TAG, "nonexistent");
    private static final UiElement BY_CLASS_NONEXISTENT =
            UiElement.getInstance("class nonexistent", UiLocatorType.CLASS, "nonexistent");
    private static final UiElement BY_TAG_ORDINAL_NONEXISTENT =
            UiElement.getInstance("tag ordinal nonexistent", UiLocatorType.TAG, "h1", 2);
    private static final UiElement BY_CLASS_ORDINAL_NONEXISTENT =
            UiElement.getInstance("class ordinal nonexistent", UiLocatorType.CLASS, "error", 3);
    private static final UiElement BY_TAG_NONEXISTENT_IN_ROOT =
            UiElement.getInstance("id nonexistent", UiLocatorType.ID, "nonexistent", BY_ID_ROOT);
    private static final UiElement BY_CLASS_NONEXISTENT_IN_PARENT =
            UiElement.getInstance("class nonexistent in parent", UiLocatorType.CLASS, "h1", BY_TAG_TABLE);
    private static final UiElement BY_ID_NONEXISTENT =
            UiElement.getInstance("id nonexistent", UiLocatorType.ID, "nonexistent");
    private static final UiElement BY_TAG_IN_PARENT_NONEXISTENT =
            UiElement.getInstance("tag in parent nonexistent", UiLocatorType.TAG, "h1", BY_ID_NONEXISTENT);
    private static final UiElement BY_TAG_UL =
            UiElement.getInstance("'ul' tag", UiLocatorType.TAG, "ul");
    private static final UiElement BY_TAG_ORDINAL_NONEXISTENT_IN_ANCESTOR =
            UiElement.getInstance("'li' tag", UiLocatorType.TAG, "li", 4, BY_TAG_UL);

    @DataProvider
    public static Object[][] scenarios() {
        return new Object[][]{
                {"basic", BY_ID_NONEXISTENT, false}
                , {BASIC_PAGE, BY_TAG_NONEXISTENT, IS_NOT_DISPLAYED}
                , {BASIC_PAGE, BY_CLASS_NONEXISTENT, IS_NOT_DISPLAYED}
                , {BASIC_PAGE, BY_TAG_ORDINAL_NONEXISTENT, IS_NOT_DISPLAYED}
                , {BASIC_PAGE, BY_CLASS_ORDINAL_NONEXISTENT, IS_NOT_DISPLAYED}
                , {BASIC_PAGE, BY_TAG_NONEXISTENT_IN_ROOT, IS_NOT_DISPLAYED}
                , {BASIC_PAGE, BY_CLASS_NONEXISTENT_IN_PARENT, IS_NOT_DISPLAYED}
                , {BASIC_PAGE, BY_TAG_IN_PARENT_NONEXISTENT, IS_NOT_DISPLAYED}
                , {LIST_PAGE, BY_TAG_ORDINAL_NONEXISTENT_IN_ANCESTOR, IS_NOT_DISPLAYED}
        };
    }

    @AfterMethod
    public void terminate() {
        UiNavigator.getInstance().quitDriver();
    }

    @Test(dataProvider = "scenarios")
    public void idDisplayed(String page, UiElement element, Boolean expected) {
        UiHost.getInstance().load(RESOURCE.getPageUrl(page));
        Boolean actual = element.isDisplayed();
        Assert.assertEquals(actual, expected);
    }
}
