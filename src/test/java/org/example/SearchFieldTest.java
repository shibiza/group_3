package org.example;

import org.example.pageobject.pages.MainPage;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

public class SearchFieldTest extends BaseTest{

    @AfterTest
    public void after() {
        webDriver.close();
        webDriver.quit();
    }

    @Test
    public void checkIncorrectText() {
        String IncorrectText = "csihbwuvcougw6789okjndso8ycgwubc8herbvgip";

        MainPage mainPage = new MainPage(webDriver);
        String lackOfResults = mainPage.open()
                        .searchIncorrectText(IncorrectText).getComment();

        Assert.assertEquals("No results for " + IncorrectText + ".\nTry checking your spelling or use more general terms", lackOfResults);
    }

    @Test
    public void checkSearchItem() {
        String searchItem = "laptop";

        MainPage mainPage = new MainPage(webDriver);
        String result = mainPage.open()
                .searchLaptop(searchItem).getComment();

        Assert.assertEquals("\"" + searchItem + "\"", result);
    }

    @Test
    public void checkFoundElementsContainsSearchedWord() {
        String searchItem = "laptop";

        MainPage mainPage = new MainPage(webDriver);
        String checkResults = mainPage.open()
                .searchLaptopOnPage(searchItem).getComment();

        boolean searchingWordIsPresent = checkResults.contains(searchItem);
        Assert.assertTrue(searchingWordIsPresent);
    }
}
