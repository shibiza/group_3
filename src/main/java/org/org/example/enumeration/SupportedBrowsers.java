package org.org.example.enumeration;


import org.openqa.selenium.WebDriver;
import org.org.example.invoker.WebDriverInvoker;
import org.org.example.invoker.implementations.LocalChromeInvoker;
import org.org.example.invoker.implementations.LocalFirefoxInvoker;
import org.org.example.invoker.implementations.RemoteChromeInvoker;

public enum SupportedBrowsers {
    LOCAL_CHROME(new LocalChromeInvoker()),
    LOCAL_FIREFOX(new LocalFirefoxInvoker()),
    REMOTE_CHROME(new RemoteChromeInvoker());


    private WebDriverInvoker webDriverInvoker;

    SupportedBrowsers(WebDriverInvoker webDriverInvoker) {
        this.webDriverInvoker = webDriverInvoker;
    }

    public WebDriver getWebDriver() {
        return webDriverInvoker.invokeWebDriver();
    }
    }
