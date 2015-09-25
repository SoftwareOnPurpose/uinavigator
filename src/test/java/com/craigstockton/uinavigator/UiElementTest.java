package com.craigstockton.uinavigator;

import com.craigstockton.uinavigator.validators.ClassValidator;
import com.craigstockton.uinavigator.validators.StringValidator;
import org.testng.annotations.Test;

@Test
public class UiElementTest extends TestBase {

    @Test
    public void getRootInstance() {
        final Class expected = UiElement.class;
        UiHost.getInstance().load("http://www.google.com");
        final Class actual = UiElement.getInstance("UiElement", UiElement.LocatorType.ID, "viewport").getClass();
        confirm(ClassValidator.getInstance(expected, actual).validate());
    }

    @Test
    public void getChildInstance() {
        Class expected = UiElement.class;
        UiHost.getInstance().load("http://www.google.com");
        UiElement parent = UiElement.getInstance("Parent UiElement", UiElement.LocatorType.ID, "gsr");
        UiElement child = UiElement.getInstance("Child UiElement", UiElement.LocatorType.ID, "viewport", parent);
        Class actual = child.getClass();
        confirm(ClassValidator.getInstance(expected, actual).validate());
    }

    @Test
    public void getChildOrdinalInstance() {
        Class expected = UiElement.class;
        UiHost.getInstance().load("http://www.google.com");
        UiElement parent = UiElement.getInstance("Parent UiElement", UiElement.LocatorType.ID, "gsr");
        UiElement child = UiElement.getInstance("Child UiElement", UiElement.LocatorType.CLASS, "_Gs", 3, parent);
        Class actual = child.getClass();
        confirm(ClassValidator.getInstance(expected, actual).validate());
    }

    @Test
    public void getChildAttributeInstance() {
        Class expected = UiElement.class;
        UiHost.getInstance().load("http://www.google.com");
        UiElement parent = UiElement.getInstance("Parent UiElement", UiElement.LocatorType.ID, "gsr");
        UiElement child = UiElement
                .getInstance("Child UiElement", UiElement.LocatorType.CLASS, "gb_ua", "data-ved", "0CBQQwi4oAA", parent);
        Class actual = child.getClass();
        confirm(ClassValidator.getInstance(expected, actual).validate());
    }

    @Test
    public void getChildOrdinalGetText() {
        String expected = "Settings";
        UiHost.getInstance().load("http://www.google.com");
        UiElement parent = UiElement.getInstance("Parent UiElement", UiElement.LocatorType.ID, "gsr");
        final UiElement child = UiElement.getInstance("Child UiElement", UiElement.LocatorType.CLASS, "_Gs", 3, parent);
        String actual = child.getText();
        confirm(StringValidator.getInstance(expected, actual).validate());
    }

    @Test(enabled = false) //  element is now hidden on Google.com
    public void getChildAttributeHref() {
        String expected = "https://mail.google.com/mail/?tab=wm";
        UiHost.getInstance().load("http://www.google.com");
        UiElement parent = UiElement.getInstance("Parent UiElement", UiElement.LocatorType.ID, "gsr");
        UiElement child = UiElement
                .getInstance("Child UiElement", UiElement.LocatorType.CLASS, "gb_ua", "data-ved", "0CBQQwi4oAA", parent);
        String actual = child.getHref();
        confirm(StringValidator.getInstance(expected, actual).validate());
    }

    @Test(enabled = false) //  element is now hidden on Google.com
    public void getChildAttributeTip() {
        String expected = "Google Apps";
        UiHost.getInstance().load("http://www.google.com");
        UiElement parent = UiElement.getInstance("Parent UiElement", UiElement.LocatorType.ID, "gsr");
        UiElement child = UiElement
                .getInstance("Child UiElement", UiElement.LocatorType.CLASS, "gb_pa", "data-ved", "0CBYQvSc", parent);
        String actual = child.getTip();
        confirm(StringValidator.getInstance(expected, actual).validate());
    }

    @Test
    public void set() {
        String setText = "Search phrase";
        UiHost.getInstance().load("http://www.google.com");
        String expected = setText;
        final UiElement searchTextbox = UiElement.getInstance("Search Box", UiElement.LocatorType.NAME, "q");
        searchTextbox.set(setText);
        String actual = searchTextbox.getText();
        confirm(StringValidator.getInstance(expected, actual).validate());
    }

    @Test
    public void click() {
        String expected = null;
        UiHost.getInstance().load("http://www.google.com");
        UiElement parent = UiElement.getInstance("'Images' link", UiElement.LocatorType.CLASS, "gb_Lc");
        UiElement.getInstance("'Images' link", UiElement.LocatorType.CLASS, "gb_ua", 3, parent).click();
        parent = UiElement.getInstance("'Images' link", UiElement.LocatorType.CLASS, "gb_Lc");
        UiElement actualElement = UiElement.getInstance("'Images' link", UiElement.LocatorType.CLASS, "gb_ua", 3, parent);
        String actual = actualElement == null ? null : actualElement.getText();
        confirm(StringValidator.getInstance(expected, actual).validate());
    }
}