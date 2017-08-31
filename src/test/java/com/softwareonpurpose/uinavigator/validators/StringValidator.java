package com.softwareonpurpose.uinavigator.validators;

import com.softwareonpurpose.validator4test.Validator;

public class StringValidator extends Validator {

    private final static String DESCRIPTION = "String";
    private final String actual;
    private final String expected;
    private final String stringDescription;

    private StringValidator(String description, String expected, String actual) {
        super(DESCRIPTION, expected, actual);
        this.stringDescription = description;
        this.actual = actual;
        this.expected = expected;
    }

    public static StringValidator getInstance(String description, String expected, String actual) {
        return new StringValidator(description, expected, actual);
    }

    @Override
    protected void executeVerifications() {
        verify(String.format("%s %s contains %s", stringDescription, actual, expected), actual.contains(expected), true);
    }

    @Override
    protected boolean actualExists() {
        return actual != null;
    }

    @Override
    protected boolean expectedExists() {
        return expected != null;
    }
}
