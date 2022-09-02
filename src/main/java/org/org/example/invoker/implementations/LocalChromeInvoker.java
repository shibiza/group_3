package org.org.example.invoker.implementations;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.org.example.invoker.WebDriverInvoker;

public class LocalChromeInvoker implements WebDriverInvoker {
    @Override
    public WebDriver invokeWebDriver() {
        WebDriverManager.chromedriver().setup();
        return new ChromeDriver();
    }
}
