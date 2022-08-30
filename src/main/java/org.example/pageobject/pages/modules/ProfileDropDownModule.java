package org.example.pageobject.pages.modules;

import org.example.pageobject.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProfileDropDownModule extends BasePage {
    private WebElement userInformationProfile;

    public ProfileDropDownModule(WebDriver webDriver) {
        super(webDriver);
    }

    public String getUserInformationProfile() {
        userInformationProfile = new WebDriverWait(webDriver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//strong[text()=\"shibiza\"]")));
        return userInformationProfile.getText();
    }
}