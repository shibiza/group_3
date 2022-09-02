package org.example.pageobject.pages;

import org.example.pageobject.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PageWithLaptopSearchOnPage extends BasePage {

    @FindBy(xpath = "//div[@class=\"s-main-slot s-result-list s-search-results sg-row\"]")
    private WebElement searchResultOnPage;

    public PageWithLaptopSearchOnPage(WebDriver webDriver) {
        super(webDriver);
    }

    public String getComment() {
        searchResultOnPage = new WebDriverWait(webDriver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class=\"s-main-slot s-result-list s-search-results sg-row\"]")));
        return searchResultOnPage.getText();
    }
}
