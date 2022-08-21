package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class DeliveryToFunctionality {

    @Test
    public void theDeliverIcon() {
        var zip = "90210";

        System.setProperty("webdriver.chrome.driver", "E:\\it\\java\\web_driver_test\\src\\test\\resources\\webdriver\\chromedriver.exe");
        assertTrue(true);

        WebDriver webDriver = new ChromeDriver();
        webDriver.get("https://www.amazon.com/");
        webDriver.manage().window().maximize();

        //      ищем поле дл€ почтового индекса
        WebElement destination = webDriver.findElement(By.id("nav-global-location-popover-link"));
        destination.click();

        //      ждем доп окно дл€ ввода индекса
        WebElement zipDropDown = new WebDriverWait(webDriver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("GLUXZipUpdateInput")));

        //      вводим индекс
        WebElement zipCode = webDriver.findElement(By.id("GLUXZipUpdateInput"));
        zipCode.sendKeys(zip);

        //  нажимаем apply
        WebElement apply = webDriver.findElement(By.xpath("//*[@id=\"GLUXZipUpdate\"]/span"));
        apply.click();

        //  ждем нового окна с continue
        WebElement continueDropDown = new WebDriverWait(webDriver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//*[contains (@class, 'a-button-input')])[4]")));

        //  жмем continue
        WebElement pressContinue = webDriver.findElement(By.xpath("(//*[contains (@class, 'a-button-input')])[4]"));
        pressContinue.click();

        //  ждем перезагрузки страницы
        boolean waitToReload = new WebDriverWait(webDriver, Duration.ofSeconds(10)).until(ExpectedConditions.stalenessOf(destination));

        WebElement zipCodeInfo = new WebDriverWait(webDriver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@id= 'nav-global-location-data-modal-action']//span[contains(@id, 'glow-ingress-line2')]")));

        //   свер€ем индексы
        Assert.assertTrue(zipCodeInfo.getText().contains(zip));

        webDriver.close();
        webDriver.quit();
    }

    @Test
    public void verifyPoland() {

        System.setProperty("webdriver.chrome.driver", "E:\\it\\java\\web_driver_test\\src\\test\\resources\\webdriver\\chromedriver.exe");
        assertTrue(true);

        WebDriver webDriver = new ChromeDriver();
        webDriver.get("https://www.amazon.com/");
        webDriver.manage().window().maximize();

        var poland = "Poland";

        WebElement destination = webDriver.findElement(By.id("nav-global-location-popover-link"));
        destination.click();

        //      ждем доп окно дл€ ввода страны
        WebElement zipDropDown = new WebDriverWait(webDriver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//select[@id = 'GLUXCountryList']")));

        WebElement listOfCountries = webDriver.findElement(By.xpath("//select[@id = 'GLUXCountryList']"));

        //      работаем со списком стран
        Select select = new Select(listOfCountries);

        List<WebElement> selectList = select.getOptions();
        Assert.assertTrue(selectList.stream().anyMatch(c -> c.getText().equals(poland)));

        webDriver.close();
        webDriver.quit();
    }

    @Test
    public void verifyShipping() {

        System.setProperty("webdriver.chrome.driver", "E:\\it\\java\\web_driver_test\\src\\test\\resources\\webdriver\\chromedriver.exe");
        assertTrue(true);

        WebDriver webDriver = new ChromeDriver();
        webDriver.get("https://www.amazon.com/");
        webDriver.manage().window().maximize();

        var poland = "Poland";

        WebElement deliverTo = webDriver.findElement(By.xpath("//div/span[@id= 'nav-global-location-data-modal-action']"));
        deliverTo.click();

        WebElement buttonDropDown = new WebDriverWait(webDriver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@id = 'GLUXCountryListDropdown']")));
        buttonDropDown.click();

        WebElement countryPoland = new WebDriverWait(webDriver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li/a[contains(text(),'" + poland + "')]")));
        countryPoland.click();

        WebElement buttonDone = webDriver.findElement(By.xpath("//button[contains(@name, 'glowDoneButton')]"));
        buttonDone.click();

        boolean waitingToReload = new WebDriverWait(webDriver, Duration.ofSeconds(10)).until(ExpectedConditions.stalenessOf(deliverTo));

        WebElement buttonHeadsets = new WebDriverWait(webDriver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id = 'gw-layout']//a[contains(@aria-label, 'Headsets')]")));

        buttonHeadsets.click();

        WebElement buttonHeadsetsMicro = new WebDriverWait(webDriver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id = 'search']//div[@cel_widget_id= 'MAIN-SEARCH_RESULTS-1']//div[contains(@class, 'a-section')]//div[contains(@class, 'sg-col')]")));
        buttonHeadsetsMicro.click();

        WebElement deliveryBox = new WebDriverWait(webDriver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id = 'apex_offerDisplay_single_desktop']//div[@id = 'amazonGlobal_feature_div']")));
        Assert.assertTrue(deliveryBox.getText().contains(poland));

        webDriver.close();
        webDriver.quit();
    }
}
