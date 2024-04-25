package com.softwareonpurpose.uinavigator;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@Test
public class UiElementStateTests {
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
        boolean isDisplayed = true;
        final String idPage = "id";
        final String classPage = "class";
        final String tablesPage = "tables";
        final UiElement byIdChild =
                UiElement.getInstance("id on sub-element", UiLocatorType.ID, "heading-id");
        final UiElement byIdDescendent =
                UiElement.getInstance("id on descendent", UiLocatorType.ID, "p-id");
        final UiElement byTagRoot =
                UiElement.getInstance("tag 'body'", UiLocatorType.TAG, "body");
        final UiElement byTagChild =
                UiElement.getInstance("tag 'p'", UiLocatorType.TAG, "p");
        final UiElement byTagDescendent =
                UiElement.getInstance("tag 'li'", UiLocatorType.TAG, "li");
        final UiElement byClassRoot =
                UiElement.getInstance("class 'root-element'", UiLocatorType.CLASS, "root-element");
        final UiElement byClassChild =
                UiElement.getInstance("class 'error'", UiLocatorType.CLASS, "error");
        final UiElement byClassDescendent =
                UiElement.getInstance("class 'names'", UiLocatorType.CLASS, "names");
        final UiElement byTagOrdinalChild =
                UiElement.getInstance("child tag ordinal", UiLocatorType.TAG, "p", 3);
        final UiElement byTagOrdinalDescendent =
                UiElement.getInstance("descendent tag ordinal", UiLocatorType.TAG, "li", 4);
        final UiElement byTagOrdinalChildDescendent =
                UiElement.getInstance("child/descendent tag ordinal", UiLocatorType.TAG, "table", 2);
        final UiElement byClassOrdinalChild =
                UiElement.getInstance("child class ordinal", UiLocatorType.CLASS, "error", 2);
        final UiElement byClassOrdinalDescendent =
                UiElement.getInstance("descendent class ordinal", UiLocatorType.CLASS, "sub-table", 2);
        final UiElement byClassChildDescendent =
                UiElement.getInstance("child/descendent class ordinal", UiLocatorType.CLASS, "names", 2);
        final UiElement byClassTableContainer =
                UiElement.getInstance("class 'table-container'", UiLocatorType.CLASS, "table-container");
        final UiElement byClassInParent =
                UiElement.getInstance("class in parent", UiLocatorType.CLASS, "sub-table", byClassTableContainer);
        final UiElement byTagInAncestor =
                UiElement.getInstance("tag in ancestor", UiLocatorType.TAG, "td", byClassTableContainer);
        final UiElement byClassOrdinalInParent =
                UiElement.getInstance("'sub-table' class", UiLocatorType.CLASS, "sub-table", 2, byClassTableContainer);
        final UiElement byTagInParentOrdinal =
                UiElement.getInstance("'th' tag in ancestor by ordinal", UiLocatorType.TAG, "th", byClassOrdinalDescendent);
        UiElement byTagOrdinal =
                UiElement.getInstance("by 'div' tag and ordinal", UiLocatorType.TAG, "div", 2);
        final UiElement byClassOrdinalInParentOrdinal =
                UiElement.getInstance("class 'row-style'", UiLocatorType.CLASS, "row-style", 3, byTagOrdinal);
        return new Object[][]
                {
                        //  element by id
                        {idPage, BY_ID_ROOT, isDisplayed}
                        , {idPage, byIdChild, isDisplayed}
                        , {idPage, byIdDescendent, isDisplayed}
                        //  element by tag
                        , {BASIC_PAGE, byTagRoot, isDisplayed}
                        , {BASIC_PAGE, byTagChild, isDisplayed}
                        , {LIST_PAGE, byTagDescendent, isDisplayed}
                        //  element by class
                        , {classPage, byClassRoot, isDisplayed}
                        , {classPage, byClassChild, isDisplayed}
                        , {tablesPage, byClassDescendent, isDisplayed}
                        //  element by tag and ordinal
                        , {classPage, byTagOrdinalChild, isDisplayed}
                        , {LIST_PAGE, byTagOrdinalDescendent, isDisplayed}
                        , {tablesPage, byTagOrdinalChildDescendent, isDisplayed}
                        //  element by class and ordinal
                        , {classPage, byClassOrdinalChild, isDisplayed}
                        , {tablesPage, byClassOrdinalDescendent, isDisplayed}
                        , {tablesPage, byClassChildDescendent, isDisplayed}
                        //  element as descendent
                        , {tablesPage, byClassInParent, isDisplayed}
                        , {tablesPage, byTagInAncestor, isDisplayed}
                        //  element as nth descendent of ancestor
                        , {tablesPage, byClassOrdinalInParent, isDisplayed}
                        , {tablesPage, byTagInParentOrdinal, isDisplayed}
                        , {tablesPage, byClassOrdinalInParentOrdinal, isDisplayed}
                };
    }

    @DataProvider
    public static Object[][] scenarios_nonexistent() {
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
    public void isDisplayed(String page, UiElement element, boolean expected) {
        UiHost.getInstance().load(RESOURCE.getPageUrl(page));
        boolean actual = element.isDisplayed();
        Assert.assertEquals(actual, expected);
    }

    @Test(dataProvider = "scenarios_nonexistent")
    public void idDisplayed_nonexistent(String page, UiElement element, Boolean expected) {
        UiHost.getInstance().load(RESOURCE.getPageUrl(page));
        Boolean actual = element.isDisplayed();
        Assert.assertEquals(actual, expected);
    }
}