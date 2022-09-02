package org.example.factory;

import properties.PropertyHolder;
import properties.convertors.SupportedBrowserConverter;
import org.openqa.selenium.WebDriver;

public class WebDriverFactory {

    public WebDriver getWebDriver() {

        return SupportedBrowserConverter.valueOfWebBrowser(
                new PropertyHolder().readProperty("browser")
        ).getWebDriver();
    }
}
