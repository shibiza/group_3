package org.example.pageobject.pages;

import org.example.pageobject.BasePage;
import org.example.pageobject.pages.modules.DeliverLocationPopUpModule;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

    @FindBy(id = "nav-global-location-popover-link")
    private WebElement deliverLocationDropDownModule;

    @FindBy(xpath = "//*[@id=\"twotabsearchtextbox\"]")
    private WebElement searchInputField;
    @FindBy(xpath = "//*[@id=\"nav-search-submit-button\"]")
    private WebElement searchButton;

    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }


    public HomePage open() {
        webDriver.get("https://www.amazon.com/");
        return this;
    }

    public DeliverLocationPopUpModule openProfileDropDown() {
        deliverLocationDropDownModule.click();
        return new DeliverLocationPopUpModule(webDriver);
    }
    /*
    WebElement countryPoland = new WebDriverWait(webDriver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li/a[contains(text(),'" + poland + "')]")));
            countryPoland.click();
     */
    public SearchResultPage searchOnSearchFieldByData(String searchData) {
        searchInputField.click();
        searchInputField.sendKeys(searchData);
        searchButton.click();
        return new SearchResultPage(webDriver);
    }
}