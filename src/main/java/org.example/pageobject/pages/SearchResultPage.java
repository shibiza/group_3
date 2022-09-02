package org.example.pageobject.pages;

import org.example.pageobject.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class SearchResultPage extends BasePage {
    @FindBys({
            @FindBy(xpath = "//*[@class=\"a-size-medium a-color-base a-text-normal\"]")
    })
    List<WebElement> searchResultData;

    public SearchResultPage(WebDriver webDriver) {
        super(webDriver);
    }

    public ProductPage selectAnyProductOnSearchResultPage() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        searchResultData.stream().findFirst().get().click();
        return new ProductPage(webDriver);
    }

}
