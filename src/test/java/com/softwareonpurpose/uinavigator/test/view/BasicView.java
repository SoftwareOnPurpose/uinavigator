package com.softwareonpurpose.uinavigator.test.view;

import com.softwareonpurpose.uinavigator.TestResources;
import com.softwareonpurpose.uinavigator.UiElement;
import com.softwareonpurpose.uinavigator.UiLocatorType;
import com.softwareonpurpose.uinavigator.UiView;

public class BasicView extends UiView {

    public BasicView() {
        super(TestResources.getInstance().getPageUrl("basic"), UiElement.getInstance("'Basic' view", UiLocatorType.TAG, "body"));
    }

    public static BasicView expect() {
        return UiView.expect(BasicView.class);
    }

    public static BasicView directNav() {
        new BasicView().load();
        return BasicView.expect();
    }

    public static BasicView directNav(String queryString) {
        new BasicView().load(queryString);
        return BasicView.expect();
    }

    @Override
    protected boolean confirmState() {
        return this.getElement().isDisplayed();
    }
}
