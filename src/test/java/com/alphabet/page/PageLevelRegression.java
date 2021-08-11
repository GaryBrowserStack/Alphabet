package com.alphabet.page;

import com.alphabet.common.CommonLibrary;
import com.paypal.selion.annotations.WebTest;
import com.paypal.selion.platform.asserts.SeLionAsserts;
import constants.generalConstants;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import java.util.List;

@WebTest
@Test(singleThreaded = true)
public class PageLevelRegression extends CommonLibrary {

    /**
     * Validate that the page we are checking is the correct one
     */
    @Test(priority = 1)
    public void validateValidPage() {
        driver.get(testURL);

        // First thing we want to do is ensure we are on the correct page by looking for an identifier.
        SeLionAsserts.verifyTrue(driver.getTitle().contains("StackDemo"),
                "The correct page is being shown.");
    }

    /**
     * Validate that the product list is present on the page
     * @throws Exception - throw an Exception if necessary
     */
    @Test(priority = 2)
    public void validateProductList() throws Exception {
        // Before we load the page again, we should clear the cookies to ensure a fresh session
        driver.manage().deleteAllCookies();
        driver.get(testURL);

        List<WebElement> productList =
                driver.findElements(By.xpath(generalConstants.productNamesXpath));

        SeLionAsserts.verifyFalse(productList.isEmpty());
    }

}
