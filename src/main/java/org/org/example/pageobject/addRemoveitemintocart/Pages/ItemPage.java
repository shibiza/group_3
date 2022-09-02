package org.org.example.pageobject.addRemoveitemintocart.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.org.example.pageobject.addRemoveitemintocart.BasePage;

public class ItemPage extends BasePage {
    @FindBy(xpath = "//*[@id=\"add-to-cart-button\"]")
    private WebElement additionToCartButton;

    protected ItemPage(WebDriver webDriver) {
        super(webDriver);
    }

    public InformationLabelAddItem addToCartButtonClick() {
        additionToCartButton.click();
        return new InformationLabelAddItem(webDriver);
    }

}
