package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;
import java.util.List;

public class DeliveryToFunctionality {

    public WebDriver webDriver;
    public String poland = "Poland";
    public String zip = "90210";

    @BeforeMethod
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "E:\\it\\java\\web_driver_test\\src\\test\\resources\\webdriver\\chromedriver.exe");

        this.webDriver = new ChromeDriver();
        webDriver.get("https://www.amazon.com/");
        webDriver.manage().window().maximize();
    }

    @AfterMethod
    public void finish() {
        webDriver.close();
        webDriver.quit();
    }

    @Test
    public void theDeliverIcon() {

        //      searching a field for a post code
        WebElement destination = webDriver.findElement(By.id("nav-global-location-popover-link"));
        destination.click();

        //      waiting for a drop down window
        WebElement zipDropDown = new WebDriverWait(webDriver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("GLUXZipUpdateInput")));

        //      entering the post code
        WebElement zipCode = webDriver.findElement(By.id("GLUXZipUpdateInput"));
        zipCode.sendKeys(zip);

        WebElement apply = webDriver.findElement(By.xpath("//*[@id=\"GLUXZipUpdate\"]/span"));
        apply.click();

        //  waiting for a new window with continue button
        WebElement continueDropDown = new WebDriverWait(webDriver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//*[contains (@class, 'a-button-input')])[4]")));

        WebElement pressContinue = webDriver.findElement(By.xpath("(//*[contains (@class, 'a-button-input')])[4]"));
        pressContinue.click();

        //  waiting for refresh page
        boolean waitToReload = new WebDriverWait(webDriver, Duration.ofSeconds(10)).until(ExpectedConditions.stalenessOf(destination));

        WebElement zipCodeInfo = new WebDriverWait(webDriver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@id= 'nav-global-location-data-modal-action']//span[contains(@id, 'glow-ingress-line2')]")));

        //   compare post codes
        Assert.assertTrue(zipCodeInfo.getText().contains(zip));
    }

    @Test
    public void verifyPoland() {

        // var poland = "Poland";

        WebElement destination = webDriver.findElement(By.id("nav-global-location-popover-link"));
        destination.click();

        //      waiting for country drop down window
        WebElement zipDropDown = new WebDriverWait(webDriver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//select[@id = 'GLUXCountryList']")));

        WebElement listOfCountries = webDriver.findElement(By.xpath("//select[@id = 'GLUXCountryList']"));

        //     working with countries
        Select select = new Select(listOfCountries);

        List<WebElement> selectList = select.getOptions();
        Assert.assertTrue(selectList.stream().anyMatch(c -> c.getText().equals(poland)));
    }

    @Test
    public void verifyShipping() {

        //        choose window with countries
        WebElement deliverTo = webDriver.findElement(By.xpath("//div/span[@id= 'nav-global-location-data-modal-action']"));
        deliverTo.click();

        WebElement buttonDropDown = new WebDriverWait(webDriver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@id = 'GLUXCountryListDropdown']")));
        buttonDropDown.click();

        // choose Poland
        WebElement countryPoland = new WebDriverWait(webDriver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li/a[contains(text(),'" + poland + "')]")));
        countryPoland.click();

        WebElement buttonDone = webDriver.findElement(By.xpath("//button[contains(@name, 'glowDoneButton')]"));
        buttonDone.click();

//      waiting to refresh the website
        boolean waitingToReload = new WebDriverWait(webDriver, Duration.ofSeconds(10)).until(ExpectedConditions.stalenessOf(deliverTo));

        //      choose any item
        WebElement buttonHeadsets = new WebDriverWait(webDriver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id = 'gw-layout']//a[contains(@aria-label, 'Headsets')]")));
        buttonHeadsets.click();

        WebElement buttonHeadsetsMicro = new WebDriverWait(webDriver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id = 'search']//div[@cel_widget_id= 'MAIN-SEARCH_RESULTS-1']//div[contains(@class, 'a-section')]//div[contains(@class, 'sg-col')]")));
        buttonHeadsetsMicro.click();

        //      checking if tthey ship our item to Poland
        WebElement deliveryBox = new WebDriverWait(webDriver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id = 'apex_offerDisplay_single_desktop']//div[@id = 'amazonGlobal_feature_div']")));
        Assert.assertTrue(deliveryBox.getText().contains(poland));
    }
}

