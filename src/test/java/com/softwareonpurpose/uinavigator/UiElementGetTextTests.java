package com.softwareonpurpose.uinavigator;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@Test
public class UiElementGetTextTests {
    private static final TestResources resources = TestResources.getInstance();

    @DataProvider
    public static Object[][] scenarios_getText() {
        final String myHeader = "My Header";
        final String nestedParagraph = "A nested paragraph";
        final String idPageContent = "The id Attribute\n" +
                "Use CSS to style an element with the id \"myHeader\":\n" +
                myHeader + "\n" +
                nestedParagraph;
        final String firstParagraph = "My first paragraph.";
        final String firstHeading = "My First Heading";
        final String coffeeUnordered = "Coffee (unordered)";
        final String different = "I am different.";
        final String aParagraph = "This is a paragraph.";
        final String differentToo = "I am different too.";
        final String firstname = "Firstname";
        final String lastname = "Lastname";
        final String firstname3 = String.format("%s%d", firstname, 3);
        final String age = "Age";
        final String tableHeading2 = String.format("%s%d %s%d %s%d", firstname, 2, lastname, 2, age, 2);
        final String tableHeading3 = String.format("%s%d %s%d %s%d", firstname, 3, lastname, 3, age, 3);
        final String tableHeading4 = String.format("%s%d %s%d %s%d", firstname, 4, lastname, 4, age, 4);
        final String jill = "Jill";
        final String smith = "Smith";
        final String eve = "Eve";
        final String jackson = "Jackson";
        final String john = "John";
        final String doe = "Doe";
        final String jill2FirstName = String.format("%s2", jill);
        final String jill2 = String.format("%s%d %s%d %d", jill, 2, smith, 2, 50);
        final String jill3 = String.format("%s%d %s%d %d", jill, 3, smith, 3, 50);
        final String jill4 = String.format("%s%d %s%d %d", jill, 4, smith, 4, 50);
        final String eve2 = String.format("%s%d %s%d %d", eve, 2, jackson, 2, 94);
        final String eve3 = String.format("%s%d %s%d %d", eve, 3, jackson, 3, 94);
        final String eve4 = String.format("%s%d %s%d %d", eve, 4, jackson, 4, 94);
        final String joe2 = String.format("%s%d %s%d %d", john, 2, doe, 2, 80);
        final String joe3 = String.format("%s%d %s%d %d", john, 3, doe, 3, 80);
        final String joe4 = String.format("%s%d %s%d %d", john, 4, doe, 4, 80);
        final String coffeeOrdered = "Coffee (ordered)";
        final String table2 = tableHeading2 + "\n" + jill2 + "\n" + eve2 + "\n" + joe2;
        final String table3 = tableHeading3 + "\n" + jill3 + "\n" + eve3 + "\n" + joe3;
        final String table4 = tableHeading4 + "\n" + jill4 + "\n" + eve4 + "\n" + joe4;
        final UiElement byIdNonexistent =
                UiElement.getInstance("id nonexistent", UiLocatorType.ID, "nonexistent");
        final UiElement byIdRoot =
                UiElement.getInstance("id existent", UiLocatorType.ID, "view");
        final UiElement byIdChild =
                UiElement.getInstance("id on sub-element", UiLocatorType.ID, "heading-id");
        final UiElement byIdDescendent =
                UiElement.getInstance("id on descendent", UiLocatorType.ID, "p-id");
        final UiElement byIdDiv =
                UiElement.getInstance("id on ancestor element", UiLocatorType.ID, "div-id");
        final UiElement byTagNonexistent =
                UiElement.getInstance("tag nonexistent", UiLocatorType.TAG, "nonexistent");
        final UiElement byTagRoot =
                UiElement.getInstance("tag 'body'", UiLocatorType.TAG, "body");
        final UiElement byTagChild =
                UiElement.getInstance("tag 'p'", UiLocatorType.TAG, "p");
        final UiElement byTagDescendent =
                UiElement.getInstance("tag 'li'", UiLocatorType.TAG, "li");
        final UiElement byClassNonexistent =
                UiElement.getInstance("class nonexistent", UiLocatorType.CLASS, "nonexistent");
        final UiElement byClassRootElement =
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
        final UiElement byTagNonexistentInParent =
                UiElement.getInstance("tag nonexistent in parent", UiLocatorType.TAG, "h1", byIdDiv);
        final UiElement byTagInParentNonexistent =
                UiElement.getInstance("tag in parent nonexistent", UiLocatorType.TAG, "h1", byIdNonexistent);
        final UiElement byTagInParent =
                UiElement.getInstance("tag in parent", UiLocatorType.TAG, "p", byIdDiv);
        final UiElement byClassTableContainer =
                UiElement.getInstance("class 'table-container'", UiLocatorType.CLASS, "table-container");
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
        final String basicPage = "basic";
        final String listPage = "list";
        final String idPage = "id";
        final String classPage = "class";
        final String tablesPage = "tables";
        return new Object[][]{
                //  element by id
                {idPage, byIdNonexistent, null}
                , {idPage, byIdRoot, idPageContent}
                , {idPage, byIdChild, myHeader}
                , {idPage, byIdDescendent, nestedParagraph}
                //  element by tag
                , {basicPage, byTagNonexistent, null}
                , {basicPage, byTagRoot, firstHeading + "\n" + firstParagraph}
                , {basicPage, byTagChild, firstParagraph}
                , {listPage, byTagDescendent, coffeeUnordered}
                //  element by class
                , {basicPage, byClassNonexistent, null}
                , {classPage, byClassRootElement,
                aParagraph + "\n" + aParagraph + "\n" + different + "\n" + aParagraph + "\n" + differentToo}
                , {classPage, byClassChild, different}
                , {tablesPage, byClassDescendent, table2}
                //  element by tag and ordinal
                , {basicPage, byTagOrdinalNonexistent, null}
                , {classPage, byTagOrdinalChild, different}
                , {listPage, byTagOrdinalDescendent, coffeeOrdered}
                , {tablesPage, byTagOrdinalChildDescendent, table2}
                //  element by class and ordinal
                , {basicPage, byClassOrdinalNonexistent, null}
                , {classPage, byClassOrdinalChild, differentToo}
                , {tablesPage, byClassOrdinalDescendent, table3}
                , {tablesPage, byClassChildDescendent, table4}
                //  element as descendent
                , {basicPage, byTagNonexistentInRoot, null}
                , {basicPage, byTagNonexistentInParent, null}
                , {basicPage, byTagInParentNonexistent, null}
                , {idPage, byTagInParent, nestedParagraph}
                , {tablesPage, byTagInAncestor, jill2FirstName}
                //  element as nth descendent of ancestor
                , {listPage, byTagOrdinalNonexistentInAncestor, null}
                , {tablesPage, byClassOrdinalInParent, table3}
                , {tablesPage, byTagInParentOrdinal, firstname3}
                , {tablesPage, byClassOrdinalInParentOrdinal, joe4}
        };
    }

    @AfterMethod
    public void terminate() {
        UiNavigator.getInstance().quitDriver();
    }

    @Test(dataProvider = "scenarios_getText")
    public void getText(String page, UiElement element, String expected) {
        UiHost.getInstance().load(resources.getPageUrl(page));
        String actual = element.getText();
        Assert.assertEquals(actual, expected);
    }
}