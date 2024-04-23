package com.softwareonpurpose.uinavigator.test.view;

import com.softwareonpurpose.uinavigator.TestResources;
import com.softwareonpurpose.uinavigator.UiElement;
import com.softwareonpurpose.uinavigator.UiLocatorType;
import com.softwareonpurpose.uinavigator.UiView;

public class NonexistentUrlView extends UiView {
    private static final TestResources resources = TestResources.getInstance();

    public NonexistentUrlView() {
        super(resources.getPageUrl("nonexistent"), UiElement.getInstance("'Body' tag", UiLocatorType.TAG, "body"));
    }

    public static NonexistentUrlView directNav() {
        new NonexistentUrlView().load();
        return UiView.expect(NonexistentUrlView.class);
    }

    @Override
    protected boolean confirmState() {
        return true;
    }
}
