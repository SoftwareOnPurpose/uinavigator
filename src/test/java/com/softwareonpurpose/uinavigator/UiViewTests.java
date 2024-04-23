package com.softwareonpurpose.uinavigator;

import com.softwareonpurpose.uinavigator.test.view.BasicView;
import com.softwareonpurpose.uinavigator.test.view.ConfirmationFailureView;
import com.softwareonpurpose.uinavigator.test.view.NonexistentUrlView;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

@Test
public class UiViewTests extends TestResources {
    @AfterMethod
    public void terminate() {
        UiNavigator.getInstance().quitDriver();
    }
    
    @SuppressWarnings("rawtypes")
    @Test
    public void expect() {
        Class expected_class = BasicView.class;
        Class expected_inheritance = UiView.class;
        Class actual_class = BasicView.expect().getClass();
        Class actual_inheritance = actual_class.getSuperclass();
        Assert.assertEquals(actual_class, expected_class);
        Assert.assertEquals(actual_inheritance, expected_inheritance);
    }

    @Test
    public void isDisplayed() {
        Boolean expected = true;
        Boolean actual = BasicView.directNav().isDisplayed();
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void isDisplayed_stateConfirmationFailure() {
        Boolean expected = false;
        Boolean actual = ConfirmationFailureView.directNav().isDisplayed();
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void isDisplayed_nonexistentUrl() {
        Boolean expected = false;
        Boolean actual = NonexistentUrlView.directNav().isDisplayed();
        Assert.assertEquals(actual, expected);
    }
}
