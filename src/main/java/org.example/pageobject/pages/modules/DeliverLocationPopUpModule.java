package org.example.pageobject.pages.modules;

import org.example.pageobject.BasePage;
import org.example.pageobject.pages.HomePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class DeliverLocationPopUpModule extends BasePage {
    @FindBy(id = "GLUXZipUpdateInput")
    private WebElement postalCodeInputField;

    @FindBy(xpath = "//*[@id=\"GLUXZipUpdate\"]/span")
    private WebElement applyButton;

    @FindBy(xpath = "//*[@id=\"GLUXCountryListDropdown\"]/span/span")
    private WebElement dropdownCountriesList;

    @FindBy(xpath = "//li/a[contains(text(),'" + "Poland" + "')]")
    private WebElement polandCountryDropDownElement;

    @FindBy(xpath = "//*[@id=\"a-autoid-3-announce\"]")
    private WebElement doneButton;
    @FindBy(xpath = "//*[@id=\"GLUXConfirmClose\"]")
    private WebElement continueButton;

    public DeliverLocationPopUpModule(WebDriver webDriver) {
        super(webDriver);
    }

//    public String getPostalCodeInputField() {
//        postalCodeInputField = new WebDriverWait(webDriver, Duration.ofSeconds(10))
//                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//strong[text()=\"shibiza\"]")));
//        return postalCodeInputField.getText();
//
//    }
    public HomePage inputPostalCodeInDropdownModuleAndClickApplyButton(String postalCode) {
        postalCodeInputField.sendKeys(postalCode);
        applyButton.click();
        continueButton.click();
        return new HomePage(webDriver);
    }

    public HomePage chooseDeliverLocationAsPolandAndClickDoneButton() {
        dropdownCountriesList.click();
        polandCountryDropDownElement.click();
        doneButton.click();
        return new HomePage(webDriver);
    }

}