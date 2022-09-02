package org.org.example.factory;


import org.openqa.selenium.WebDriver;
import org.org.example.properties.PropertyHolder;
import org.org.example.properties.convertors.SupportedBrowserConverter;

public class WebDriverFactory {
    public WebDriver getWebDriver() {
        return SupportedBrowserConverter.valueOfWebBrowser(
                        new PropertyHolder().readProperty("browser"))
                .getWebDriver();
    }
}
