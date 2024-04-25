package com.softwareonpurpose.uinavigator;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@Test
public class UiElementStateTests {
    private static final TestResource RESOURCE = TestResource.getInstance();
    private static final String BASIC_PAGE = "basic";
    private static final String LIST_PAGE = "list";
    private static final UiElement BY_ID_ROOT =
            UiElement.getInstance("id existent", UiLocatorType.ID, "view");

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
}