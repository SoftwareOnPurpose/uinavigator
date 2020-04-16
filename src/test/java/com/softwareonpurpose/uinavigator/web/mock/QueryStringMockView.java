package com.softwareonpurpose.uinavigator.web.mock;

import com.softwareonpurpose.uinavigator.UiLocatorType;
import com.softwareonpurpose.uinavigator.web.WebUiElement;
import com.softwareonpurpose.uinavigator.web.WebUiView;

public class QueryStringMockView extends WebUiView {

    private static final String VIEW_URI = "http://www.google.com/search";

    public QueryStringMockView() {
        super(VIEW_URI, WebUiElement.getInstance("'Mock' view", UiLocatorType.TAG, "body"));
    }

    public static QueryStringMockView directNav() {
        QueryStringMockView view = construct(QueryStringMockView.class);
        view.load("?q=mousetrap");
        return WebUiView.expect(view.getClass());
    }

    @Override
    protected boolean confirmElementStates() {
        return this.getElement().waitUntilVisible();
    }
}