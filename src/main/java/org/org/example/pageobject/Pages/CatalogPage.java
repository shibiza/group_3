package org.org.example.pageobject.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.org.example.pageobject.BasePage;

public class CatalogPage extends BasePage {
    @FindBy(xpath = "(//*[@class = \"a-section aok-relative s-image-fixed-height\"])[1]")
    public WebElement selectItem;

    protected CatalogPage(WebDriver webDriver) {
        super(webDriver);
    }

    public ItemPage itemClick(){
        selectItem.click();
        return new ItemPage(webDriver);
    }


}
