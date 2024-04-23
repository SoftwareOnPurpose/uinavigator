package com.softwareonpurpose.uinavigator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public abstract class UiView {
    public static final int TIMEOUT = 3000;
    private final static String ERROR_CONSTRUCTOR_SCOPE =
            "Unable to access View constructor; ensure it is parameter-less and has 'public' scope";
    private static Logger logger;
    protected final String VIEW_URL;
    private final UiElement VIEW_ELEMENT;

    protected UiView(String viewUrl, UiElement viewElement) {
        VIEW_URL = viewUrl;
        VIEW_ELEMENT = viewElement;
    }


    public static <T extends UiView> T expect(Class<T> viewClass) {
        final String viewClassName = composeViewClassName(viewClass);
        T view = construct(viewClass);
        if (!view.isDisplayed()) {
            getLogger().info(String.format("Unable to confirm the state of '%s'", viewClassName));
        }
        return construct(viewClass);
    }

    private static Logger getLogger() {
        if (logger == null) {
            logger = LogManager.getLogger("");
        }
        return logger;
    }

    private static <T extends UiView> String composeViewClassName(Class<T> viewClass) {
        String invalidCharacters =
                String.format("%s|%s|%s", "(?<=[A-Z])(?=[A-Z][a-z])", "(?<=[^A-Z])(?=[A-Z])", "(?<=[A-Za-z])(?=[^A-Za-z])");
        return viewClass.getSimpleName().replaceAll(invalidCharacters, " ");
    }

    private static <T extends UiView> T construct(Class<T> viewClass) {
        T view = null;
        Constructor<T> constructor;
        try {
            constructor = viewClass.getConstructor();
            view = constructor.newInstance();
        } catch (NoSuchMethodException | InvocationTargetException | InstantiationException |
                 IllegalAccessException e) {
            getLogger().error(String.format("%s%n%s", ERROR_CONSTRUCTOR_SCOPE, System.err));

        }
        return view;
    }

    private static String removeProtocol(String urlPath) {
        int startIndex = urlPath.contains("/") ? urlPath.indexOf("/", urlPath.indexOf("/") + 1) : 0;
        urlPath = urlPath.substring(startIndex);
        return urlPath;
    }

    protected String getUrl() {
        return VIEW_URL;
    }

    protected abstract boolean confirmState();

    public boolean isDisplayed() {
        return confirmNavigationToView() && confirmState();
    }

    protected void load() {
        UiHost.getInstance().load(VIEW_URL);
    }

    protected UiElement getElement() {
        return VIEW_ELEMENT;
    }

    private boolean confirmNavigationToView() {
        String urlPath = removeProtocol(VIEW_URL);
        boolean isDisplayed = false;
        boolean timeRemaining = true;
        long start = System.currentTimeMillis();
        while (!isDisplayed && timeRemaining) {
            isDisplayed = UiHost.getInstance().getCurrentUrl().contains(urlPath);
            timeRemaining = System.currentTimeMillis() - start < TIMEOUT;
        }
        return isDisplayed;
    }
}
