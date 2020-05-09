package com.softwareonpurpose.uinavigator.web;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

@Test
public class CookieViewerNonExistentTests {
    private WebDriver driver;

    @AfterMethod
    public void terminate() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void testGetCookieValue_nonexistent() {
        driver = ChromeUiDriver.getInstance().instantiateDriver();
        String expected = null;
        CookieViewer viewer = CookieViewer.getInstance(driver);
        String actual = viewer.getCookieValue("unknown", "domain", "path");
        //noinspection ConstantConditions
        Assert.assertEquals(actual, expected, "Failed to return an instance of CookieViewer");
    }

    @Test
    public void testGetCookieValue_nameQualifiedNonExistent() {
        driver = ChromeUiDriver.getInstance().instantiateDriver();
        String uri = "http://www.google.com";
        driver.navigate().to(uri);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("document.cookie = \"cookiename=cookievalue\";");
        String expected = null;
        CookieViewer viewer = CookieViewer.getInstance(driver);
        String actual = viewer.getCookieValue("nonexistent");
        //noinspection ConstantConditions
        Assert.assertEquals(actual, expected, "Failed to return expected cookie value");
    }
}
