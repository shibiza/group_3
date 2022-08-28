package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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

        WebElement brandsExpander = webDriver.findElement(By.xpath("(//a[contains(@class,\"a-expander-header\")])[2]"));
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        js.executeScript("arguments[0].click();", brandsExpander);
    }

    /*@AfterTest
    public void after() {
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
        selectBrand(brandName);

        boolean anyTitleContainsInputWord = verifyEveryTitleContainsBrandName(brandName);

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
        selectBrand(brandName);

        WebElement minPriceInput = new WebDriverWait(webDriver, Duration.ofSeconds(6))
                .until(ExpectedConditions.presenceOfElementLocated(By.id("low-price")));
        minPriceInput.sendKeys(String.valueOf(minPrice));

        WebElement maxPriceInput = webDriver.findElement(By.id("high-price"));
        maxPriceInput.sendKeys(String.valueOf(maxPrice));

        WebElement submitPriceRangeBtn = webDriver.findElement(By.xpath("//input[@aria-labelledby='a-autoid-1-announce']"));
        submitPriceRangeBtn.click();

        boolean arePricesInChosenRange = verifyPricesAreInChoseRange(minPrice, maxPrice);

        Assert.assertTrue(arePricesInChosenRange, "Prices are not within specified range");
    }

    @Test(dataProvider = "brandNames")
    public void verifyProductsPricesAreSortedAscendingly(String brandName) {
        selectBrand(brandName);

        WebElement sortingDropdownList = new WebDriverWait(webDriver, Duration.ofSeconds(6))
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("a-autoid-0-announce")));

        sortingDropdownList.click();

        WebElement lowToHighDropdownSelection = webDriver.findElement(By.id("s-result-sort-select_1"));
        lowToHighDropdownSelection.click();

        boolean arePricesInAscendingOrder = verifyPricesAreInAscendingOrder();

        Assert.assertTrue(arePricesInAscendingOrder, "Prices are not in ascending order");
    }

    private void selectBrand(String brandName) {
        WebElement brandNameCheckbox = new WebDriverWait(webDriver, Duration.ofSeconds(6))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[text() = '" + brandName + "']")));
        brandNameCheckbox.click();
    }


    private boolean verifyEveryTitleContainsBrandName(String brandName) {
        WebElement paginationNextBtn;
        boolean everyTitleContainsInputWord;

        while (true) {
            paginationNextBtn = new WebDriverWait(webDriver, Duration.ofSeconds(6))
                    .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(@class,'s-pagination-next')]")));

//checking if every title of the page contains brand name
            everyTitleContainsInputWord = webDriver.findElements(By.xpath("//div[contains(@class,'s-card-container')]//span[contains(@class,'a-size-medium')]"))
                    .stream()
                    .map(WebElement::getText)
                    .map(String::toLowerCase)
                    .allMatch(e -> e.contains(brandName.toLowerCase()));

            if (!everyTitleContainsInputWord) {
                break;
            }

            if (paginationNextBtn.isDisplayed() && !(paginationNextBtn.getAttribute("aria-disabled") == null)) {
                break;
            }

            paginationNextBtn.click();
        }
        return everyTitleContainsInputWord;
    }

    private boolean verifyPricesAreInChoseRange(float minPrice, float maxPrice) {
        WebElement paginationNextBtn;
        boolean arePricesInChosenRange;

        while (true) {
            paginationNextBtn = new WebDriverWait(webDriver, Duration.ofSeconds(6))
                    .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(@class,'s-pagination-next')]")));

            arePricesInChosenRange = webDriver.findElements(By.xpath("//div[@data-component-type=\"s-search-result\"]//span[@class=\"a-price\"]//span[@class=\"a-offscreen\"]"))
                    .stream()
                    .map(e -> e.getAttribute("textContent").replace("$", ""))
                    .filter(e -> !e.isEmpty())
                    .map(Float::parseFloat)
                    .allMatch(price -> price >= minPrice && price <= maxPrice);

            if (!arePricesInChosenRange) {
                break;
            }

            if (paginationNextBtn.isDisplayed() && paginationNextBtn.getAttribute("aria-disabled") == null) {
                paginationNextBtn.click();
                continue;
            }

            break;
        }

        return arePricesInChosenRange;
    }

    private boolean verifyPricesAreInAscendingOrder() {
        WebElement paginationNextBtn;
        boolean arePricesInAscendingOrder;

        while (true) {
            paginationNextBtn = new WebDriverWait(webDriver, Duration.ofSeconds(6))
                    .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(@class,'s-pagination-next')]")));

            List<Float> prices = webDriver.findElements(By.xpath("//div[@data-component-type=\"s-search-result\"]//span[@class=\"a-price\"]//span[@class=\"a-offscreen\"]"))
                    .stream()
                    .map(e -> e.getAttribute("textContent").replace("$", ""))
                    .filter(e -> !e.isEmpty())
                    .map(Float::parseFloat)
                    .collect(Collectors.toList());

            arePricesInAscendingOrder = verifyPricesInAscendingOrder(prices);

            if (!arePricesInAscendingOrder) {
                break;
            }

            if (paginationNextBtn.isDisplayed() && paginationNextBtn.getAttribute("aria-disabled") == null) {
                paginationNextBtn.click();
                continue;
            }

            break;
        }

        return arePricesInAscendingOrder;
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




