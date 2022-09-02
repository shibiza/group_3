package org.example.pageobject.deliverytofunctionalitypages.modules;


import org.example.pageobject.BasePage;
import org.example.pageobject.deliverytofunctionalitypages.HomePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class DeliverLocationPopUpModule extends BasePage {
    @FindBy(id = "GLUXZipUpdateInput")
    private WebElement postalCodeInputField;

    @FindBy(xpath = "//*[@id=\"GLUXZipUpdate\"]/span")
    private WebElement applyButton;

    @FindBy(xpath = "//*[@id=\"GLUXSpecifyLocationDiv\"]/div[4]")
    private WebElement dropdownCountriesList;

    @FindBy(xpath = "//li/a[contains(text(),'" + "Poland" + "')]")
    private WebElement polandCountryDropDownElement;

    @FindBy(xpath = "//*[@id=\"a-popover-1\"]/div/div[2]/span/span/span/button")
    private WebElement doneButton;

    @FindBy(xpath = "//*[@id=\"a-popover-1\"]/div/div[2]/span/span")
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
        webDriverWait.until(ExpectedConditions.visibilityOf(postalCodeInputField)).sendKeys(postalCode);
        webDriverWait.until(ExpectedConditions.visibilityOf(applyButton)).click();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        webDriverWait.until(ExpectedConditions.visibilityOf(continueButton)).click();
        return new HomePage(webDriver);
    }

    public HomePage chooseDeliverLocationAsPolandAndClickDoneButton() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        dropdownCountriesList.click();
        polandCountryDropDownElement.click();
        doneButton.click();
        return new HomePage(webDriver);
    }

}