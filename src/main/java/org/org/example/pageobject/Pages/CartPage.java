package org.org.example.pageobject.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.org.example.pageobject.BasePage;

public class CartPage extends BasePage {

    @FindBy(css = ".sc-action-delete .a-color-link")
    private WebElement buttonDelete;
    @FindBy(xpath = "//*[@class = \"a-spacing-mini a-spacing-top-base\"]")
    private WebElement checkInformationLabelDelItem;
    @FindBy(xpath = "//*[@id=\"nav-cart-count\"]")
    private WebElement checkInformationLabelDelCart;

    protected CartPage(WebDriver webDriver) {
        super(webDriver);
    }

    public CartPage buttonDeleteClick() {
        buttonDelete.click();
        return this;
    }

    public static final String VALUE = "0";
    public static final String YOUR_AMAZON_CART_IS_EMPTY = "Your Amazon Cart is empty.";
    public String getCheckInformationLabelDelItem() {
        return checkInformationLabelDelItem.getText();
    }
    public String getCheckInformationLabelDelCart() {
        return checkInformationLabelDelCart.getText();
    }
}
