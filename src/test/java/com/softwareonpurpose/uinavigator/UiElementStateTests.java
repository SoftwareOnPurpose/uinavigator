package com.softwareonpurpose.uinavigator;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@Test
public class UiElementStateTests {
    private static final TestResources resources = TestResources.getInstance();

    @DataProvider
    public static Object[][] scenarios_isDisplayed() {
        boolean isDisplayed = true;
        boolean isNotDisplayed = false;
        final String basicPage = "basic";
        final String listPage = "list";
        final String idPage = "id";
        final String classPage = "class";
        final String tablesPage = "tables";
        final UiElement byIdNonexistent =
                UiElement.getInstance("id nonexistent", UiLocatorType.ID, "nonexistent");
        final UiElement byIdRoot =
                UiElement.getInstance("id existent", UiLocatorType.ID, "view");
        final UiElement byIdChild =
                UiElement.getInstance("id on sub-element", UiLocatorType.ID, "heading-id");
        final UiElement byIdDiv =
                UiElement.getInstance("id on ancestor element", UiLocatorType.ID, "div-id");
        final UiElement byIdDescendent =
                UiElement.getInstance("id on descendent", UiLocatorType.ID, "p-id");
        final UiElement byTagNonexistent =
                UiElement.getInstance("tag nonexistent", UiLocatorType.TAG, "nonexistent");
        final UiElement byTagRoot =
                UiElement.getInstance("tag 'body'", UiLocatorType.TAG, "body");
        final UiElement byTagChild =
                UiElement.getInstance("tag 'p'", UiLocatorType.TAG, "p");
        final UiElement byTagDescendent =
                UiElement.getInstance("tag 'li'", UiLocatorType.TAG, "li");
        final UiElement byTagTable = UiElement.getInstance("tag 'table'", UiLocatorType.TAG, "table");
        final UiElement byClassNonexistent =
                UiElement.getInstance("class nonexistent", UiLocatorType.CLASS, "nonexistent");
        final UiElement byClassRoot =
                UiElement.getInstance("class 'root-element'", UiLocatorType.CLASS, "root-element");
        final UiElement byClassChild =
                UiElement.getInstance("class 'error'", UiLocatorType.CLASS, "error");
        final UiElement byClassDescendent =
                UiElement.getInstance("class 'names'", UiLocatorType.CLASS, "names");
        final UiElement byTagOrdinalNonexistent =
                UiElement.getInstance("tag ordinal nonexistent", UiLocatorType.TAG, "h1", 2);
        final UiElement byTagOrdinalChild =
                UiElement.getInstance("child tag ordinal", UiLocatorType.TAG, "p", 3);
        final UiElement byTagOrdinalDescendent =
                UiElement.getInstance("descendent tag ordinal", UiLocatorType.TAG, "li", 4);
        final UiElement byTagOrdinalChildDescendent =
                UiElement.getInstance("child/descendent tag ordinal", UiLocatorType.TAG, "table", 2);
        final UiElement byClassOrdinalNonexistent =
                UiElement.getInstance("class ordinal nonexistent", UiLocatorType.CLASS, "error", 3);
        final UiElement byClassOrdinalChild =
                UiElement.getInstance("child class ordinal", UiLocatorType.CLASS, "error", 2);
        final UiElement byClassOrdinalDescendent =
                UiElement.getInstance("descendent class ordinal", UiLocatorType.CLASS, "sub-table", 2);
        final UiElement byClassChildDescendent =
                UiElement.getInstance("child/descendent class ordinal", UiLocatorType.CLASS, "names", 2);
        final UiElement byTagNonexistentInRoot =
                UiElement.getInstance("id nonexistent", UiLocatorType.ID, "nonexistent", byIdRoot);
        final UiElement byClassNonexistentInParent =
                UiElement.getInstance("class nonexistent in parent", UiLocatorType.CLASS, "h1", byTagTable);
        final UiElement byTagInParentNonexistent =
                UiElement.getInstance("tag in parent nonexistent", UiLocatorType.TAG, "h1", byIdNonexistent);
        final UiElement byClassTableContainer =
                UiElement.getInstance("class 'table-container'", UiLocatorType.CLASS, "table-container");
        final UiElement byClassInParent =
                UiElement.getInstance("class in parent", UiLocatorType.CLASS, "sub-table", byClassTableContainer);
        final UiElement byTagInAncestor =
                UiElement.getInstance("tag in ancestor", UiLocatorType.TAG, "td", byClassTableContainer);
        final UiElement byTagUl =
                UiElement.getInstance("'ul' tag", UiLocatorType.TAG, "ul");
        final UiElement byTagOrdinalNonexistentInAncestor =
                UiElement.getInstance("'li' tag", UiLocatorType.TAG, "li", 4, byTagUl);
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
                        {idPage, byIdNonexistent, isNotDisplayed}
                        , {idPage, byIdRoot, isDisplayed}
                        , {idPage, byIdChild, isDisplayed}
                        , {idPage, byIdDescendent, isDisplayed}
                        //  element by tag
                        , {basicPage, byTagNonexistent, isNotDisplayed}
                        , {basicPage, byTagRoot, isDisplayed}
                        , {basicPage, byTagChild, isDisplayed}
                        , {listPage, byTagDescendent, isDisplayed}
                        //  element by class
                        , {basicPage, byClassNonexistent, isNotDisplayed}
                        , {classPage, byClassRoot, isDisplayed}
                        , {classPage, byClassChild, isDisplayed}
                        , {tablesPage, byClassDescendent, isDisplayed}
                        //  element by tag and ordinal
                        , {basicPage, byTagOrdinalNonexistent, isNotDisplayed}
                        , {classPage, byTagOrdinalChild, isDisplayed}
                        , {listPage, byTagOrdinalDescendent, isDisplayed}
                        , {tablesPage, byTagOrdinalChildDescendent, isDisplayed}
                        //  element by class and ordinal
                        , {basicPage, byClassOrdinalNonexistent, isNotDisplayed}
                        , {classPage, byClassOrdinalChild, isDisplayed}
                        , {tablesPage, byClassOrdinalDescendent, isDisplayed}
                        , {tablesPage, byClassChildDescendent, isDisplayed}
                        //  element as descendent
                        , {basicPage, byTagNonexistentInRoot, isNotDisplayed}
                        , {basicPage, byClassNonexistentInParent, isNotDisplayed}
                        , {basicPage, byTagInParentNonexistent, isNotDisplayed}
                        , {tablesPage, byClassInParent, isDisplayed}
                        , {tablesPage, byTagInAncestor, isDisplayed}
                        //  element as nth descendent of ancestor
                        , {listPage, byTagOrdinalNonexistentInAncestor, isNotDisplayed}
                        , {tablesPage, byClassOrdinalInParent, isDisplayed}
                        , {tablesPage, byTagInParentOrdinal, isDisplayed}
                        , {tablesPage, byClassOrdinalInParentOrdinal, isDisplayed}
                };
    }

    @AfterMethod
    public void terminate() {
        UiNavigator.getInstance().quitDriver();
    }

    @Test(dataProvider = "scenarios_isDisplayed")
    public void isDisplayed(String page, UiElement element, boolean expected) {
        UiHost.getInstance().load(resources.getPageUrl(page));
        boolean actual = element.isDisplayed();
        Assert.assertEquals(actual, expected);
    }
}