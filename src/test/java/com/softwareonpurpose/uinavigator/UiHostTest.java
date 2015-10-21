package com.softwareonpurpose.uinavigator;

import com.softwareonpurpose.uinavigator.driverinstantiation.FirefoxInstantiation;
import com.softwareonpurpose.uinavigator.validators.BooleanValidator;
import com.softwareonpurpose.uinavigator.validators.ClassValidator;
import com.softwareonpurpose.uinavigator.validators.IntegerValidator;
import com.softwareonpurpose.uinavigator.validators.StringValidator;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

@Test
public class UiHostTest extends TestBase {

    private static final String uri = "http://www.google.com";

    @Test
    public void getDefaultInstance() {
        Class expected = UiHost.class;
        Class actual = UiHost.getInstance().getClass();
        confirm(ClassValidator.getInstance(expected, actual).validate());
    }

    @Test
    public void load() {
        String domain = "www.google.com";
        String expected = String.format("https://%s", domain);
        UiHost.getInstance().load(String.format("http://%s", domain));
        String actual = UiHost.getInstance().getUri();
        confirm(StringValidator.getInstance(expected, actual).validate());
    }

    @Test
    public void findUiElement() {
        String expected = "ctr-p";
        final UiHost browser = UiHost.getInstance();
        browser.load("http://www.google.com");
        String actual = browser.findUiElement(By.id("viewport")).getAttribute("class");
        confirm(StringValidator.getInstance(expected, actual).validate());
    }

    @Test
    public void findUiElements() {
        Integer expected = 6;
        final UiHost browser = UiHost.getInstance();
        browser.load(uri);
        Integer actual = browser.findUiElements(By.className("_Gs")).size();
        confirm(IntegerValidator.getInstance(expected, actual).validate());
    }

    @Test( /*
            groups = "under_development"//*/)
    public void waitUntilVisible() {
        UiHost browser = UiHost.getInstance();
        browser.load(uri);
        Boolean actual = browser.waitUntilVisible(By.name("btnK"));
        confirm(BooleanValidator.getInstance(true, actual).validate());
    }

    @Test( /*
            groups = "under_development"//*/)
    public void getSpecifWebDriverInstance() {
        String expected = "Firefox";
        DriverInstantiation driverInstantiation = FirefoxInstantiation.getInstance();
        String actual = UiHost.getInstance(driverInstantiation).getDriverName();
        confirm(StringValidator.getInstance(expected, actual).validate());
    }

    @Test(/*
            groups = "under_development"//*/
    )
    public void selectFrame() {
        String expected = "Log In";
        UiHost host = UiHost.getInstance();
        host.load("http:espn.go.com");
        UiElement.getInstance("'Login' link", UiElement.LocatorType.TAG, "a", UiElement.getInstance("'User' region", UiElement.LocatorType.CLASS, "user")).click();
        host.selectFrame("disneyid-iframe");
        String actual = UiElement.getInstance("'Login' button", UiElement.LocatorType.CLASS, "btn-group").getText();
        confirm(StringValidator.getInstance(expected, actual).validate());
    }

    @Test(/*
            groups = "under_development"//*/
    )
    public void selectWindow() {
        String expected = null;
        UiHost host = UiHost.getInstance();
        host.load("http:espn.go.com");
        UiElement.getInstance("'Login' link", UiElement.LocatorType.TAG, "a", UiElement.getInstance("'User' region", UiElement.LocatorType.CLASS, "user")).click();
        host.selectFrame("disneyid-iframe");
        host.selectWindow();
        String actual = UiElement.getInstance("'Login' button", UiElement.LocatorType.CLASS, "btn-group").getText();
        confirm(StringValidator.getInstance(expected, actual).validate());
    }
}