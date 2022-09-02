package org.example.addremoveitemintocartTest;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.openqa.selenium.WebDriver;
import org.org.example.factory.WebDriverFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {

    protected final WebDriver webDriver = new WebDriverFactory().getWebDriver();

    @BeforeMethod
    protected void setUpDriver() {
        WebDriverManager.chromedriver().setup();
        webDriver.get("https://www.amazon.com/");
        webDriver.manage().window().maximize();
    }
    @AfterMethod
    public void finish() {
        webDriver.close();
        webDriver.quit();
    }
}
