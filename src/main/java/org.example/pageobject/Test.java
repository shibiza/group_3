package org.example.pageobject;

import org.example.factory.WebDriverFactory;
import org.example.pageobject.pages.HomePage;
import org.openqa.selenium.WebDriver;

public class Test {
    public static void main(String[] args) {
        WebDriver webDriver = new WebDriverFactory().getWebDriver();
        HomePage homePage = new HomePage(webDriver);
        homePage.open().openProfileDropDown().inputPostalCodeInDropdownModuleAndClickApplyButton("90210");
    }
}
