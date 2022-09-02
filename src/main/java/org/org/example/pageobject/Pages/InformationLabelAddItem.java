package org.org.example.pageobject.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.org.example.pageobject.BasePage;

public class InformationLabelAddItem extends BasePage {
    @FindBy(xpath = "//*[@class = \"a-size-medium-plus a-color-base sw-atc-text a-text-bold\"]")
    private WebElement checkInformationLabelAddItem;

    @FindBy(xpath = "//*[@id=\"nav-cart-count\"]")
    private WebElement checkInformationLabelCart;
    @FindBy(xpath = "//*[@id = \"nav-cart\"]")
    private WebElement openCartButton;

    public static final String CART_COUNT = "1";
    public static final String ADDED_TO_CART = "Added to Cart";

    protected InformationLabelAddItem(WebDriver webDriver) {
        super(webDriver);
    }

    public String getCheckInformationLabelAddItem() {
        return checkInformationLabelAddItem.getText();
    }

    public String getCheckInformationLabelCart() {
        return checkInformationLabelCart.getText();
    }

    public CartPage cartButtonClick() {
        openCartButton.click();
        return new CartPage(webDriver);
    }
}
