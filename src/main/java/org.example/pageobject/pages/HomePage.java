package org.example.pageobject.pages;

import org.example.pageobject.BasePage;
import org.example.pageobject.pages.modules.ProfileDropDownModule;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

    @FindBy(id = "nav-global-location-popover-link")

    private WebElement profileDropDownButton;

    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }

    public ProfileDropDownModule openProfileDropDown() {
        profileDropDownButton.click();
        return new ProfileDropDownModule(webDriver);
    }

}