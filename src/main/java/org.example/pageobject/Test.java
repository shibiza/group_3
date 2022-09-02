package org.example.pageobject;

import org.example.factory.WebDriverFactory;
import org.example.pageobject.deliverytofunctionalitypages.HomePage;
import org.openqa.selenium.WebDriver;

import java.util.Objects;

public class Test {


    private int a;
    public static void main(String[] args) {
        WebDriver webDriver = new WebDriverFactory().getWebDriver();
        HomePage homePage = new HomePage(webDriver);
        homePage.open().openProfileDropDown().inputPostalCodeInDropdownModuleAndClickApplyButton("90210").openProfileDropDown().
                chooseDeliverLocationAsPolandAndClickDoneButton().searchOnSearchFieldByData("headphones").selectAnyProductOnSearchResultPage().getDeliveryCountry();

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Test test = (Test) o;
        return a == test.a;
    }

    @Override
    public int hashCode() {
        return Objects.hash(a);
    }
}
