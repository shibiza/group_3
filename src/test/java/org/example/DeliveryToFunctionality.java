package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.example.factory.WebDriverFactory;
import org.example.pageobject.pages.HomePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;
import java.util.List;

public class DeliveryToFunctionality {

    public HomePage homePage;
    public String poland = "Poland";
    public String zip = "90210";

    @BeforeClass
    public void setup() {
        WebDriver webDriver = new WebDriverFactory().getWebDriver();
        homePage = new HomePage(webDriver);
    }

    @Test
    public void testDeliverCountryPoland() {
        boolean  isDeliveredCountryPoland =  homePage.open().openProfileDropDown().inputPostalCodeInDropdownModuleAndClickApplyButton("90210").openProfileDropDown().
                chooseDeliverLocationAsPolandAndClickDoneButton().searchOnSearchFieldByData("headphones").selectAnyProductOnSearchResultPage().getDeliveryCountry().contains("Poland");
        Assert.assertEquals(true,isDeliveredCountryPoland);
    }
}

