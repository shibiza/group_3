package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;

public class SearchFieldTest {
    private WebDriver webDriver;

    @BeforeTest
    public void before() {
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();
        webDriver.get("https://www.amazon.com/");
        webDriver.manage().window().maximize();
    }

    @AfterTest
    public void after() {
        webDriver.close();
        webDriver.quit();
    }

    @Test
    public void checkIncorrectText() {
        String searchIncorrectText = "csihbwuvcougw6789okjndso8ycgwubc8herbvgip";

        WebElement searchField = new WebDriverWait(webDriver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("twotabsearchtextbox")));
        searchField.clear(); // After adding @BeforeTest and @afterTest to the code, you need to clear search field
        searchField.sendKeys(searchIncorrectText);

        WebElement searchButton = webDriver.findElement(By.id("nav-search-submit-button"));
        searchButton.click();

        WebElement noResultsComment = new WebDriverWait(webDriver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class=\"s-no-outline\"]")));
        Assert.assertEquals("No results for " + searchIncorrectText + ".\nTry checking your spelling or use more general terms", noResultsComment.getText());
    }

    @Test
    public void checkSearchItem() {
        String searchItem = "laptop";

        WebElement searchField = new WebDriverWait(webDriver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("twotabsearchtextbox")));
        searchField.clear(); // After adding @BeforeTest and @afterTest to the code, you need to clear search field
        searchField.sendKeys(searchItem);

        WebElement searchButton = webDriver.findElement(By.id("nav-search-submit-button"));
        searchButton.click();

        WebElement searchingItemIsPresent = new WebDriverWait(webDriver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class=\"a-section a-spacing-small a-spacing-top-small\"]/span[3]")));
        Assert.assertEquals("\"" + searchItem + "\"", searchingItemIsPresent.getText());
    }

    @Test
    public void checkFoundElementsContainsSearchedWord() {
        String searchItem = "laptop";

        WebElement searchField = new WebDriverWait(webDriver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("twotabsearchtextbox")));
        searchField.clear(); // After adding @BeforeTest and @afterTest to the code, you need to clear search field
        searchField.sendKeys(searchItem);

        WebElement searchButton = webDriver.findElement(By.id("nav-search-submit-button"));
        searchButton.click();

        boolean searchingWordIsPresent = webDriver.findElement(By.xpath("//div[@class=\"s-main-slot s-result-list s-search-results sg-row\"]")).getText().contains(searchItem);
        Assert.assertTrue(searchingWordIsPresent);
    }
}
