package org.example.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;

public abstract class BasePage {

    protected WebDriver webDriver;



    protected BasePage(WebDriver webDriver) {

            this.webDriver = webDriver;
            PageFactory.initElements(webDriver, this);
            webDriver.manage().window().maximize();
            webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
            webDriver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5));
            webDriver.manage().timeouts().scriptTimeout(Duration.ofSeconds(5));
        }

    }