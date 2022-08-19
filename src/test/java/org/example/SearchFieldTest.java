package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.time.Duration;

public class SearchFieldTest {

    @Test
    public void checkIncorrectText() {
        System.setProperty("webdriver.chrome.driver", "..\\webdriver\\chromedriver.exe");

        WebDriver webDriver = new ChromeDriver();
        webDriver.get("https://www.amazon.com/");
        webDriver.manage().window().maximize();

        String searchIncorrectText = "fdghyhkjtuthhdtkj,kfbvmhn3456789okmnbvcser6789pl,mn cxswert6789ocvb4543u8739698302y5nm,ukl";

        WebElement searchField = webDriver.findElement(By.id("twotabsearchtextbox"));
        searchField.sendKeys(searchIncorrectText);

        WebElement searchButton = webDriver.findElement(By.id("nav-search-submit-button"));
        searchButton.click();

        WebElement noResultsComment = new WebDriverWait(webDriver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"search\"]/div[1]/div[1]/div/span[3]/div[2]/div[1]/div/div/div")));
        Assert.assertEquals("No results for " + searchIncorrectText + ".\nTry checking your spelling or use more general terms", noResultsComment.getText());

        WebElement noResultsComment2 = new WebDriverWait(webDriver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"search\"]/div[1]/div[1]/div/span[3]/div[2]/div[1]/div/div/div/div[1]/span[1]")));
        Assert.assertEquals("No results for", noResultsComment2.getText());

        webDriver.close();
        webDriver.quit();
    }

    @Test
    public void checkSearchItem() {
        System.setProperty("webdriver.chrome.driver", "..\\webdriver\\chromedriver.exe");

        WebDriver webDriver = new ChromeDriver();
        webDriver.get("https://www.amazon.com/");
        webDriver.manage().window().maximize();

        String searchItem = "laptop";

        WebElement searchField = webDriver.findElement(By.id("twotabsearchtextbox"));
        searchField.sendKeys(searchItem);

        WebElement searchButton = webDriver.findElement(By.id("nav-search-submit-button"));
        searchButton.click();

        WebElement searchingItemIsPresent = new WebDriverWait(webDriver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"search\"]/span/div/h1/div/div[1]/div/div/span[3]")));
        Assert.assertEquals("\"" + searchItem + "\"", searchingItemIsPresent.getText());

        webDriver.close();
        webDriver.quit();
    }

    @Test
    public void checkFoundElementsContainsSearchedWord() {
        System.setProperty("webdriver.chrome.driver", "..\\webdriver\\chromedriver.exe");

        WebDriver webDriver = new ChromeDriver();
        webDriver.get("https://www.amazon.com/");
        webDriver.manage().window().maximize();

        String searchItem = "laptop";

        WebElement searchField = webDriver.findElement(By.id("twotabsearchtextbox"));
        searchField.sendKeys(searchItem);

        WebElement searchButton = webDriver.findElement(By.id("nav-search-submit-button"));
        searchButton.click();

        boolean searchingWordIsPresent = webDriver.findElement(By.xpath("//*[@id=\"search\"]/div[1]/div[1]/div/span[3]/div[2]")).getText().contains(searchItem);
            Assert.assertTrue(searchingWordIsPresent);

        webDriver.close();
        webDriver.quit();
    }
}
