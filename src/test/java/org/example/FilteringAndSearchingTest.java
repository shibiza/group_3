package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

public class FilteringAndSearchingTest {
    private WebDriver webDriver;

    @BeforeTest
    public void before() {
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();

        String categoryPath = "/s?k=gaming+keyboard&pd_rd_r=da8afc49-fa94-41c3-9d45-7e811ac33b10&pd_rd_w=gSHhP&pd_rd_wg"
                + "=fx882&pf_rd_p=12129333-2117-4490-9c17-6d31baf0582a&pf_rd_r=XYWA244WM0H05HEYD0RE&ref=pd_gw_unk";
        webDriver.get("https://www.amazon.com" + categoryPath);
    }

    /*@AfterTest
    public void after(){
        webDriver.quit();
    }*/

    @DataProvider(name = "brandNames")
    public Object[][] brandNames() {
        return new Object[][]{
                {"Corsair"}
        };
    }

    @Test(dataProvider = "brandNames")
    public void verifyTitlesContainChosenBrand(String brandName) {
        WebElement brandNameCheckbox = webDriver.findElement(By.xpath("//span[text() = '" + brandName + "']"));
        brandNameCheckbox.click();

        int numOfPages = webDriver.findElements(By.xpath("//span[@class='s-pagination-strip']/span[4]")).isEmpty() ?
                1 : Integer.parseInt(webDriver.findElement(By.xpath("//span[@class='s-pagination-strip']/span[4]")).getText());

        boolean anyTitleContainsInputWord = false;

        for (int i = 1; i <= numOfPages; i++) {
//checking if every title of the page contains brand name
            anyTitleContainsInputWord = webDriver.findElements(By.xpath("//div[contains(@class,'s-card-container')]//span[contains(@class,'a-size-medium')]"))
                    .stream()
                    .map(WebElement::getText)
                    .map(String::toLowerCase)
                    .allMatch(e -> e.contains(brandName.toLowerCase()));

//if any title doesnt contain brand name, break the loop
            if (!anyTitleContainsInputWord) {
                break;
            }

//click next button until its not clickable, based on num of pages
            if (numOfPages != i) {
                WebElement nextButton = new WebDriverWait(webDriver, Duration.ofSeconds(6))
                        .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(@class,'s-pagination-next')]")));
                nextButton.click();
            }
        }

        Assert.assertTrue(anyTitleContainsInputWord, "Not every title contains chosen brand name");
    }

    @DataProvider(name = "brandNamesAndPriceRanges")
    public Object[][] brandNamesAndPriceRanges() {
        return new Object[][]{
                {"Corsair", 50, 250}
        };
    }

    @Test(dataProvider = "brandNamesAndPriceRanges")
    public void verifyProductsPricesAreInDefinedRange(String brandName, float minPrice, float maxPrice) {
        WebElement brandNameCheckbox = webDriver.findElement(By.xpath("//span[text() = '" + brandName + "']"));
        brandNameCheckbox.click();

        WebElement minPriceInput = webDriver.findElement(By.id("low-price"));
        minPriceInput.sendKeys(String.valueOf(minPrice));

        WebElement maxPriceInput = webDriver.findElement(By.id("high-price"));
        maxPriceInput.sendKeys(String.valueOf(maxPrice));

        WebElement submitPriceRangeBtn = webDriver.findElement(By.xpath("//input[@aria-labelledby='a-autoid-1-announce']"));
        submitPriceRangeBtn.click();

        int numOfPages = webDriver.findElements(By.xpath("//span[@class='s-pagination-strip']/span[4]")).isEmpty() ?
                1 : Integer.parseInt(webDriver.findElement(By.xpath("//span[@class='s-pagination-strip']/span[4]")).getText());

        boolean arePricesInChosenRange = false;

        for (int i = 1; i <= numOfPages; i++) {
//checking if prices are within specified range
            arePricesInChosenRange = webDriver.findElements(By.xpath("//span[@class=\"a-price\"]/span[@class=\"a-offscreen\"]"))
                    .stream()
                    .map(e -> e.getAttribute("textContent").replace("$", ""))
                    .filter(e -> !e.isEmpty())
                    .map(Float::parseFloat)
                    .allMatch(price -> price >= minPrice && price <= maxPrice);

//if any price is out of range, break the loop
            if (!arePricesInChosenRange) {
                break;
            }

//click next button until its not clickable, based on num of pages
            if (numOfPages != i) {
                WebElement nextButton = new WebDriverWait(webDriver, Duration.ofSeconds(6))
                        .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(@class,'s-pagination-next')]")));
                nextButton.click();
            }
        }

        Assert.assertTrue(arePricesInChosenRange, "Prices are not within specified range");
    }

    @Test(dataProvider = "brandNames")
    public void verifyProductsPricesAreSorted(String brandName) {
        WebElement brandNameCheckbox = webDriver.findElement(By.xpath("//span[text() = '" + brandName + "']"));
        brandNameCheckbox.click();

        WebElement searchDropdownList = webDriver.findElement(By.id("a-autoid-0-announce"));
        searchDropdownList.click();

        WebElement selectDropdownList = webDriver.findElement(By.id("s-result-sort-select_1"));
        selectDropdownList.click();

        int numOfPages = webDriver.findElements(By.xpath("//span[@class='s-pagination-strip']/span[4]")).isEmpty() ?
                1 : Integer.parseInt(webDriver.findElement(By.xpath("//span[@class='s-pagination-strip']/span[4]")).getText());

        boolean arePricesInAscendingOrder = false;

        for (int i = 1; i <= numOfPages; i++) {
            List<Float> prices = webDriver.findElements(By.xpath("//span[@class=\"a-price\"]/span[@class=\"a-offscreen\"]"))
                    .stream()
                    .map(e -> e.getAttribute("textContent").replace("$", ""))
                    .filter(e -> !e.isEmpty())
                    .map(Float::parseFloat)
                    .collect(Collectors.toList());

            arePricesInAscendingOrder = verifyPricesInAscendingOrder(prices);

            if (!arePricesInAscendingOrder) {
                break;
            }

            if (numOfPages != i) {
                WebElement nextButton = new WebDriverWait(webDriver, Duration.ofSeconds(6))
                        .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(@class,'s-pagination-next')]")));
                nextButton.click();
            }
        }

        Assert.assertTrue(arePricesInAscendingOrder, "Prices are not in ascending order");
    }

    private boolean verifyPricesInAscendingOrder(List<Float> prices) {
        for (int i = 0; i < prices.size() - 2; i++) {
            if (prices.get(i) > prices.get(i + 1)) {
                return false;
            }
        }
        return true;
    }
}



