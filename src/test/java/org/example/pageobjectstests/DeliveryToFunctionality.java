package org.example.pageobjectstests;

import org.example.factory.WebDriverFactory;
import org.example.pageobject.deliverytofunctionalitypages.HomePage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

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

