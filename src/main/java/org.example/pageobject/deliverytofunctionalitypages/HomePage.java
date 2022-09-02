package org.example.pageobject.deliverytofunctionalitypages;

import org.awaitility.Awaitility;
import org.example.pageobject.BasePage;
import org.example.pageobject.deliverytofunctionalitypages.modules.DeliverLocationPopUpModule;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;


public class HomePage extends BasePage {

    @FindBy(xpath = "//*[@id=\"glow-ingress-block\"]")
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
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        webDriverWait.until(ExpectedConditions.visibilityOf(deliverLocationDropDownModule)).click();
        return new DeliverLocationPopUpModule(webDriver);
    }

    public SearchResultPage searchOnSearchFieldByData(String searchData) {
        webDriverWait.until(ExpectedConditions.visibilityOf(searchInputField)).click();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        webDriverWait.until(ExpectedConditions.visibilityOf(searchInputField)).sendKeys(searchData);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        webDriverWait.until(ExpectedConditions.visibilityOf(searchButton)).click();
        return new SearchResultPage(webDriver);
    }
}