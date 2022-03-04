package com.softwareonpurpose.uinavigator.web;
/*
  Copyright 2020 Craig A. Stockton
  <p/>
  Licensed under the Apache License, Version 2.0 (the "License");
  you may not use this file except in compliance with the License.
  You may obtain a copy of the License at
  <p/>
  http://www.apache.org/licenses/LICENSE-2.0
  <p/>
  Unless required by applicable law or agreed to in writing, software
  distributed under the License is distributed on an "AS IS" BASIS,
  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  See the License for the specific language governing permissions and
  limitations under the License.
 */

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class WebUiGetElementByLocatorAttribute extends WebUiGetElement {
    private final String attribute;
    private final String attributeValue;

    private WebUiGetElementByLocatorAttribute(String description, By locator, String attribute, String attributeValue) {
        super(description, locator);
        this.attribute = attribute;
        this.attributeValue = attributeValue;
    }

    public static WebUiGetElementByLocatorAttribute getInstance(
            String description, String locatorType, String locatorValue, String attribute, String attributeValue) {
        return new WebUiGetElementByLocatorAttribute(
                description, WebElementLocator.getInstance(locatorType, locatorValue), attribute, attributeValue);
    }

    @Override
    public WebElement execute() {
        List<WebElement> candidates = WebUiHost.getInstance().findUiElements(locator);
        List<WebElement> elements = new ArrayList<>();
        for (WebElement candidate : candidates) {
            final String attributeValue = candidate.getAttribute(this.attribute);
            if (attributeValue != null && attributeValue.equals(this.attributeValue)) {
                elements.add(candidate);
            }
        }
        return elements.size() == 0 ? null : elements.get(0);
    }
}