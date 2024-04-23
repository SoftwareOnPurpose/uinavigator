package com.softwareonpurpose.uinavigator.test.view.region;

import com.softwareonpurpose.uinavigator.UiElement;
import com.softwareonpurpose.uinavigator.UiLocatorType;
import com.softwareonpurpose.uinavigator.UiRegion;

public class UnorderedListRegion extends UiRegion {
    public UnorderedListRegion(UiElement ancestor) {
        super(UiElement.getInstance("Unordered List", UiLocatorType.TAG, "ul", ancestor));
    }

    public static UnorderedListRegion getInstance(UiElement parent) {
        return new UnorderedListRegion(parent);
    }

    @Override
    protected boolean confirmState() {
        return true;
    }
}
