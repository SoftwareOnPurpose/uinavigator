package com.softwareonpurpose.uinavigator.test.view;

import com.softwareonpurpose.uinavigator.TestResources;
import com.softwareonpurpose.uinavigator.UiElement;
import com.softwareonpurpose.uinavigator.UiLocatorType;
import com.softwareonpurpose.uinavigator.UiView;

public class ConfirmationFailureView extends UiView {
    public ConfirmationFailureView() {
        super(TestResources.getInstance().getPageUrl("basic"), UiElement.getInstance("'View' element", UiLocatorType.TAG, "body"));
    }

    public static ConfirmationFailureView directNav() {
        new ConfirmationFailureView().load();
        return ConfirmationFailureView.expect();
    }

    private static ConfirmationFailureView expect() {
        return UiView.expect(ConfirmationFailureView.class);
    }

    @Override
    protected boolean confirmState() {
        return false;
    }
}
