package org.example.pageobjectstests.filteringandsearchingtest;

import org.example.factory.WebDriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class BaseTest {
    protected final WebDriver webDriver =
            new WebDriverFactory().getWebDriver();

    @BeforeTest
    protected void setUpDriver() {
        webDriver.manage().window().maximize();
    }

    @AfterTest
    public void tearDown(){
        webDriver.quit();
    }
}
