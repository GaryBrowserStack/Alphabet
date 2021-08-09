package com.alphabet.page;

import com.alphabet.common.CommonLibrary;
import com.paypal.selion.platform.asserts.SeLionAsserts;
import com.paypal.selion.platform.grid.Grid;
import com.paypal.selion.platform.html.support.HtmlElementUtils;
import constants.generalConstants;
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
        Grid.driver().manage().deleteAllCookies();
        Grid.open(testURL);

        List<WebElement> productList = HtmlElementUtils.locateElements(generalConstants.productNamesXpath);

        SeLionAsserts.verifyFalse(productList.isEmpty());
    }

}
