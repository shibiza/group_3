package org.example.pageobject.pages;

import org.example.pageobject.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {
    @FindBy(id = "login_field")
    private WebElement userName;

    @FindBy(id = "password")
    private WebElement passwordField;

    @FindBy(name = "commit")
    private WebElement sighInButton;

    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    public LoginPage open() {
        webDriver.get("https://www.amazon.com/");
        return this;
    }

    public HomePage login(String userName, String password) {
        this.userName.sendKeys(userName);
        this.passwordField.sendKeys(password);
        sighInButton.click();

        return  new HomePage(webDriver);

    }


}