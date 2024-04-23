package com.softwareonpurpose.uinavigator;


import org.apache.logging.log4j.LogManager;

public abstract class UiRegion {
    private final UiElement regionElement;

    protected UiRegion(UiElement regionElement) {
        LogManager.getLogger("").info(String.format("In '%s' region ...", regionElement.getDescription()));
        this.regionElement = regionElement;
    }

    public boolean isDisplayed() {
        boolean isDisplayed = this.getElement().isDisplayed();
        return isDisplayed && confirmState();
    }

    private UiElement getElement() {
        return regionElement;
    }

    protected abstract boolean confirmState();
}
