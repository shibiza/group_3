package org.example.pageobject.filteringandsearchingpages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

public class GamingKeyboardsCategoryPage extends BasePage {

    private final String sponsoredSpanXpath = ".//span[@class='s-label-popover-default']/span";

    private final String biggerPriceTextXpath = ".//div//span[@class='a-price']//span[@class='a-offscreen']";

    private final String titleXpath = "//div[contains(@class,'s-card-container')]//span[contains(@class,'a-size-medium')]";

    private final String itemContainersXpath = "//div[@data-component-type='s-search-result']//div[contains(@class,'s-card-container')]";

    @FindBy(xpath = itemContainersXpath)
    List<WebElement> itemContainers;

    @FindBy(xpath = titleXpath)
    List<WebElement> titlesList;

    @FindBy(xpath = "(//a[contains(@class,\"a-expander-header\")])[2]")
    private WebElement brandsExpander;

    @FindBy(id = "low-price")
    private WebElement minPriceInput;

    @FindBy(id = "high-price")
    private WebElement maxPriceInput;

    @FindBy(xpath = "//span[contains(@class,'a-button')]/span/input")
    private WebElement submitPriceRangeBtn;

    @FindBy(id = "a-autoid-0-announce")
    private WebElement sortingDropdownList;

    @FindBy(id = "s-result-sort-select_1")
    private WebElement lowToHighDropdownSelection;

    @FindBy(xpath = "//*[contains(@class,'s-pagination-next')]")
    private WebElement paginationNextBtn;

    public GamingKeyboardsCategoryPage(WebDriver webDriver) {
        super(webDriver);
    }

    public GamingKeyboardsCategoryPage open() {
        String categoryPath = "/s?k=gaming+keyboard&pd_rd_r=da8afc49-fa94-41c3-9d45-7e811ac33b10&pd_rd_w=gSHhP&pd_rd_wg"
                + "=fx882&pf_rd_p=12129333-2117-4490-9c17-6d31baf0582a&pf_rd_r=XYWA244WM0H05HEYD0RE&ref=pd_gw_unk";
        webDriver.get("https://www.amazon.com" + categoryPath);
        return this;
    }

    public GamingKeyboardsCategoryPage selectBrand(String brandName) {
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        js.executeScript("arguments[0].click();", brandsExpander);

        WebElement brandNameCheckbox = new WebDriverWait(webDriver, Duration.ofSeconds(6))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[text() = '" + brandName + "']")));
        brandNameCheckbox.click();
        return this;
    }

    public GamingKeyboardsCategoryPage setPriceRange(float minPrice, float maxPrice) {
        waitForElementVisibility(minPriceInput);
        minPriceInput.sendKeys(String.valueOf(minPrice));
        maxPriceInput.sendKeys(String.valueOf(maxPrice));
        waitForElementVisibility(submitPriceRangeBtn);
        submitPriceRangeBtn.click();
        return this;
    }

    public GamingKeyboardsCategoryPage sortProductsByPriceLowToHigh() {
        waitForElementVisibility(sortingDropdownList);
        sortingDropdownList.click();
        lowToHighDropdownSelection.click();
        return this;
    }

    public boolean verifyEveryTitleContainsBrandName(String brandName) {
        boolean everyTitleContainsInputWord;

        while (true) {
            waitForElementPresence(titleXpath);

            everyTitleContainsInputWord = titlesList
                    .stream()
                    .map(WebElement::getText)
                    .map(String::toLowerCase)
                    .allMatch(e -> e.contains(brandName.toLowerCase()));

            if (!everyTitleContainsInputWord) {
                break;
            }

            try {
                waitForElementVisibility(paginationNextBtn);
            } catch (Exception e) {
                break;
            }

            if (paginationNextBtn.isDisplayed() && !(paginationNextBtn.getAttribute("aria-disabled") == null)) {
                break;
            }

            paginationNextBtn.click();
        }
        return everyTitleContainsInputWord;
    }

    public boolean verifyPricesAreInChosenRange(float minPrice, float maxPrice) {
        boolean arePricesInChosenRange;

        while (true) {
            waitForElementPresence(itemContainersXpath);

            arePricesInChosenRange = itemContainers
                    .stream()
                    .filter(e -> e.findElements(By.xpath(sponsoredSpanXpath)).isEmpty()
                            && !e.findElements(By.xpath(biggerPriceTextXpath)).isEmpty())
                    .map(e -> Float.parseFloat(e.findElement(By.xpath(biggerPriceTextXpath))
                            .getAttribute("textContent").replace("$", "")))
                    .allMatch(price -> price >= minPrice && price <= maxPrice);

            if (!arePricesInChosenRange) {
                break;
            }

            try {
                waitForElementVisibility(paginationNextBtn);
            } catch (Exception e) {
                break;
            }

            if (paginationNextBtn.isDisplayed() && !(paginationNextBtn.getAttribute("aria-disabled") == null)) {
                break;
            }

            paginationNextBtn.click();
        }

        return arePricesInChosenRange;
    }

    public boolean verifyPricesAreInAscendingOrder() {
        boolean arePricesInAscendingOrder;
        List<Float> prices;

        while (true) {
            waitForElementPresence(itemContainersXpath);

            prices = itemContainers
                    .stream()
                    .filter(e -> e.findElements(By.xpath(sponsoredSpanXpath)).isEmpty()
                            && !e.findElements(By.xpath(biggerPriceTextXpath)).isEmpty())
                    .map(e -> Float.parseFloat(e.findElement(By.xpath(biggerPriceTextXpath))
                            .getAttribute("textContent").replace("$", "")))
                    .collect(Collectors.toList());

            arePricesInAscendingOrder = verifyPricesInAscendingOrder(prices);

            if (!arePricesInAscendingOrder) {
                break;
            }

            try {
                waitForElementVisibility(paginationNextBtn);
            } catch (Exception e) {
                break;
            }

            if (paginationNextBtn.isDisplayed() && !(paginationNextBtn.getAttribute("aria-disabled") == null)) {
                break;
            }

            paginationNextBtn.click();
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

    private void waitForElementVisibility(WebElement element) {
        webDriverWait.until(ExpectedConditions.visibilityOf(element));
    }

    private void waitForElementPresence(String elementXpath){
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(elementXpath)));
    }
}