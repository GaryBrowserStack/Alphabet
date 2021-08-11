package com.alphabet.pricing;

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
public class PricingChecks extends CommonLibrary {
    /**
     * Validate that the pricing on the page is correct
     * @throws Exception - throw an Exception if necessary
     */
    @Test(priority = 1)
    public void validatePricingOnPage() throws Exception {
        driver.get(testURL);

        checkCartProductInformation();
    }

}
