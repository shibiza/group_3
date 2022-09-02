package org.example.pageobject.pages;

import org.example.pageobject.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PageWithLaptopSearch extends BasePage {

    @FindBy(xpath = "//div[@class=\"a-section a-spacing-small a-spacing-top-small\"]/span[3]")
    private WebElement searchResult;

    public PageWithLaptopSearch(WebDriver webDriver) {
        super(webDriver);
    }

    public String getComment() {
        searchResult = new WebDriverWait(webDriver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class=\"a-section a-spacing-small a-spacing-top-small\"]/span[3]")));
        return searchResult.getText();
    }
}
