package org.example.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class BasePage {

    protected final WebDriverWait webDriverWait;
    protected WebDriver webDriver;
    protected BasePage(WebDriver webDriver) {

        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(15));
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        webDriverWait  = new WebDriverWait(webDriver,Duration.ofSeconds(15),Duration.ofMillis(50));
    }

}