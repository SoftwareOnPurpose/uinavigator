package com.softwareonpurpose.uinavigator;

import java.net.URL;

public class TestResource {

    public static TestResource getInstance() {
        return new TestResource();
    }

    protected String getResourceFilename(String resourceFilename) {
        URL resource = getClass().getResource(resourceFilename);
        return resource == null ? "[RESOURCE NOT FOUND]" : resource.toString();
    }

    public String getPageUrl(String page) {
        return getResourceFilename(String.format("/%s.html", page));
    }
}
