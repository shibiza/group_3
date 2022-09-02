package org.org.example.pageobject.Pages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.org.example.pageobject.BasePage;

public class HomePage extends BasePage {
    @FindBy(xpath = "(//span[@class =\"a-size-small a-color-base truncate-2line\"])[1]")
    private WebElement selectCatalog;

    public CatalogPage catalogClick(){
        selectCatalog.click();
        return new CatalogPage(webDriver);
    }
    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }
    public HomePage open(){
        webDriver.get("https://github.com/login");
        return this;
    }
}
