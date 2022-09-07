package org.example;

import org.example.factory.WebDriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;

public class BaseTest {
    protected final WebDriver webDriver = new WebDriverFactory().getWebDriver();

    @BeforeMethod
    protected void setUpDriver() {
        webDriver.manage().window().maximize();
    }
}
