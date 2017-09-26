package com.softwareonpurpose.uinavigator.validators.webelement;

import com.softwareonpurpose.validator4test.Validator;
import org.openqa.selenium.WebElement;

public class   WebElementValidator extends Validator {

    private final static String description = "WebElement";
    private final WebElement actual;
    private final WebElementExpected expected;

    private WebElementValidator(WebElementExpected expected, WebElement actual) {
        super(description, expected, actual);
        this.actual = actual;
        this.expected = expected;
    }

    public static WebElementValidator getInstance(WebElementExpected expected, WebElement actual) {
        return new WebElementValidator(expected, actual);
    }

    @Override
    protected void executeVerifications() {
        verify("Id", expected.getId(), actual.getAttribute("id"));
        verify("Class", expected.getClasses(), actual.getAttribute("class"));
        verify("Tag", expected.getTag(), actual.getTagName());
        verify("Href", expected.getHref(), actual.getAttribute("href"));
    }
}
