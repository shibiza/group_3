package org.example.pageobject.pages;

import org.example.pageobject.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PageWithIncorrectTextSearch extends BasePage {

    @FindBy(xpath = "//div[@class=\"s-no-outline\"]")
    private WebElement noResultsComment;

    public PageWithIncorrectTextSearch(WebDriver webDriver) {
        super(webDriver);
    }

    public String getComment() {
        noResultsComment = new WebDriverWait(webDriver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class=\"s-no-outline\"]")));
        return noResultsComment.getText();
    }
}
