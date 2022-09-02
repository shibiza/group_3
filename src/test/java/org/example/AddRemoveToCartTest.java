package org.example;


import org.org.example.pageobject.Pages.CartPage;
import org.org.example.pageobject.Pages.HomePage;
import org.org.example.pageobject.Pages.InformationLabelAddItem;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.org.example.pageobject.Pages.CartPage.VALUE;
import static org.org.example.pageobject.Pages.CartPage.YOUR_AMAZON_CART_IS_EMPTY;
import static org.org.example.pageobject.Pages.InformationLabelAddItem.ADDED_TO_CART;
import static org.org.example.pageobject.Pages.InformationLabelAddItem.CART_COUNT;


public class AddRemoveToCartTest extends BaseTest {

    private InformationLabelAddItem addingToCart() {
        return new HomePage(webDriver)
                .open()
                .catalogClick()
                .itemClick()
                .addToCartButtonClick();
    }

    @Test
    public void addToCart() {
        InformationLabelAddItem informationLabelAddItem = addingToCart();
        Assert.assertEquals(informationLabelAddItem.getCheckInformationLabelAddItem(), CART_COUNT);
        Assert.assertEquals(informationLabelAddItem.getCheckInformationLabelCart(), ADDED_TO_CART);
    }

    @Test
    public void deleteFromCart() {
        InformationLabelAddItem informationLabelAddItem = addingToCart();
        CartPage cartPage = informationLabelAddItem.cartButtonClick();
        cartPage.buttonDeleteClick();
        Assert.assertEquals(cartPage.getCheckInformationLabelDelCart(), VALUE);
        Assert.assertEquals(cartPage.getCheckInformationLabelDelItem(), YOUR_AMAZON_CART_IS_EMPTY);
    }
}
