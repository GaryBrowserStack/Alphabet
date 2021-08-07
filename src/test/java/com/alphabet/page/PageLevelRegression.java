package com.alphabet.page;

import com.alphabet.common.CommonLibrary;
import com.paypal.selion.platform.asserts.SeLionAsserts;
import constants.generalConstants;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.util.List;

public class PageLevelRegression extends CommonLibrary {

    @Test(priority = 1)
    public void InitialTest() {
        driver.get(testURL);

        // First thing we want to do is ensure we are on the correct page by looking for an identifier.
        SeLionAsserts.verifyTrue(driver.getPageSource().contains("StackDemo"));
    }

    @Test(priority = 2)
    public void testAppleProducts() throws Exception {
        // Before we load the page again, we should clear the cookies to ensure a fresh session
        driver.manage().deleteAllCookies();
        driver.get(testURL);

        // Get the list of elements for each of the filter triggers.
        List<WebElement> elementList = driver.findElements(By.xpath(generalConstants.productsFilter));

        // Firstly we will check all of the apple products by filtering for Apple.
        elementList.get(0).click();

        // Call the function to check the information for all products in the current filter.
        checkCartProductInformation();
    }

    @Test(priority = 3)
    public void testSamsungProducts() throws Exception {
        // Before we load the page again, we should clear the cookies to ensure a fresh session
        driver.manage().deleteAllCookies();
        driver.get(testURL);

        // Get the list of elements for each of the filter triggers.
        List<WebElement> elementList = driver.findElements(By.xpath(generalConstants.productsFilter));

        // Firstly we will check all of the apple products by filtering for Apple.
        elementList.get(1).click();

        // Call the function to check the information for all products in the current filter.
        checkCartProductInformation();
    }

    @Test(priority = 4)
    public void testGoogleProducts() throws Exception {
        // Before we load the page again, we should clear the cookies to ensure a fresh session
        driver.manage().deleteAllCookies();
        driver.get(testURL);

        // Get the list of elements for each of the filter triggers.
        List<WebElement> elementList = driver.findElements(By.xpath(generalConstants.productsFilter));

        // Firstly we will check all of the apple products by filtering for Apple.
        elementList.get(2).click();

        // Call the function to check the information for all products in the current filter.
        checkCartProductInformation();
    }

    @Test(priority = 5)
    public void testOnePlusProducts() throws Exception {
        // Before we load the page again, we should clear the cookies to ensure a fresh session
        driver.manage().deleteAllCookies();
        driver.get(testURL);

        // Get the list of elements for each of the filter triggers.
        List<WebElement> elementList = driver.findElements(By.xpath(generalConstants.productsFilter));

        // Firstly we will check all of the apple products by filtering for Apple.
        elementList.get(2).click();

        // Call the function to check the information for all products in the current filter.
        checkCartProductInformation();
    }


}
