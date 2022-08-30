package org.example.pageobject.pages;

import org.example.pageobject.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

import java.util.List;

public class SearchResultPage extends BasePage {
    @FindBys({
            @FindBy(xpath = "//*[@class=\"twotabsearchtextbox\"]")
    })
    List<WebElement> searchResultData;

    public SearchResultPage(WebDriver webDriver) {
        super(webDriver);
    }
    public ProductPage selectAnyProductOnSearchResultPage(){
        searchResultData.stream().findFirst().get().click();
        return new ProductPage(webDriver);
    }
}
