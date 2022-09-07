package org.example.pageobject.pages;

import org.example.pageobject.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MainPage extends BasePage {

    @FindBy(id = "twotabsearchtextbox")
    private WebElement searchField;

    @FindBy(id = "nav-search-submit-button")
    private WebElement searchButton;

    public MainPage(WebDriver webDriver) {
        super(webDriver);
    }

    public MainPage open() {
        webDriver.get("https://www.amazon.com/");
        return this;
    }

    public PageWithIncorrectTextSearch searchIncorrectText(String text) {
        this.searchField.clear();
        searchField.sendKeys(text);
        searchButton.click();

        return new PageWithIncorrectTextSearch(webDriver);
    }

    public PageWithLaptopSearch searchLaptop(String text) {
        this.searchField.clear();
        searchField.sendKeys(text);
        searchButton.click();

        return new PageWithLaptopSearch(webDriver);
    }

    public PageWithLaptopSearchOnPage searchLaptopOnPage(String text) {
        this.searchField.clear();
        searchField.sendKeys(text);
        searchButton.click();

        return new PageWithLaptopSearchOnPage(webDriver);
    }
}
