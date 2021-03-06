package com.alphabet.common;

import com.browserstack.local.Local;
import com.paypal.selion.platform.asserts.SeLionAsserts;
import constants.generalConstants;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.FileReader;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class CommonLibrary {
    public String testURL;

    public WebDriver driver;
    public String username;
    public String accessKey;
    public String server;
    private Local l;

    @BeforeMethod(alwaysRun = true)
    @org.testng.annotations.Parameters(value = { "config", "environment" })
    @SuppressWarnings("unchecked")
    public void setUp(String config_file, String environment) throws Exception {
        JSONParser parser = new JSONParser();
        JSONObject config = (JSONObject) parser.parse(new FileReader("src/test/resources/conf/" + config_file));
        JSONObject envs = (JSONObject) config.get("environments");

        DesiredCapabilities capabilities = new DesiredCapabilities();

        Map<String, String> envCapabilities = (Map<String, String>) envs.get(environment);
        Iterator it = envCapabilities.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            capabilities.setCapability(pair.getKey().toString(), pair.getValue().toString());
        }

        Map<String, String> commonCapabilities = (Map<String, String>) config.get("capabilities");
        it = commonCapabilities.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            if (capabilities.getCapability(pair.getKey().toString()) == null) {
                capabilities.setCapability(pair.getKey().toString(), pair.getValue().toString());
            }
        }

        username = System.getenv("BROWSERSTACK_USERNAME");
        if (username == null) {
            username = (String) config.get("user");
        }

        accessKey = System.getenv("BROWSERSTACK_ACCESS_KEY");
        if (accessKey == null) {
            accessKey = (String) config.get("key");
        }

        server = config.get("server").toString();

        if (capabilities.getCapability("browserstack.local") != null
                && capabilities.getCapability("browserstack.local") == "true") {
            l = new Local();
            Map<String, String> options = new HashMap<String, String>();
            options.put("key", accessKey);
            l.start(options);
        }

        testURL = (String) config.get("testpage");

        driver = new RemoteWebDriver(
                new URL("http://" + username + ":" + accessKey + "@" + server + "/wd/hub"), capabilities);

    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() throws Exception {
        driver.quit();
        if (l != null) {
            l.stop();
        }
    }

    /**
     * Check the pricing information between page and cart
     * @throws Exception
     */
    public void checkCartProductInformation() throws Exception {
        List<WebElement> productPriceList = driver.findElements(By.xpath(generalConstants.productPricesXpath));
        List<WebElement> productInstallmentPriceList = driver.findElements(By.xpath(generalConstants.productInstallmentPricesXpath));
        List<WebElement> cartButtonList = driver.findElements(By.xpath(generalConstants.addToCartButtons));

        for (int i = 0; i < productPriceList.size(); i++) {
            // Click on the current "Add to Cart" button
            cartButtonList.get(i).click();
            Thread.sleep(2000);

            // Get the cart pricing elements on the page
            WebElement cartPrice = driver.findElement(By.xpath(generalConstants.cartProductPrice));
            WebElement cartSubtotalPrice = driver.findElement(By.xpath(generalConstants.cartSubPrice));
            String cartSubtotalInstallmentPrice = driver.findElement(By.xpath(generalConstants.cartSubInstallmentPrice)).getText().split("x ")[1];

            SeLionAsserts.verifyEquals(productPriceList.get(i).getText(), cartPrice.getText().replace(" ", ""),
                    "The top cart price is matching with the page.");
            SeLionAsserts.verifyEquals(productPriceList.get(i).getText(), cartSubtotalPrice.getText().replace(" ", ""),
                    "The subtotal price in the cart matches with the page.");
            SeLionAsserts.verifyEquals(productInstallmentPriceList.get(i).getText().split("x ")[1].replace(" ", ""),
                    cartSubtotalInstallmentPrice.replace(" ", ""),
                    "The installments amount in the cart matches with the page.");

            // Once we have verified that the product names are matching in the cart, we need to remove the product and close the cart before moving to the next instance in the loop
            driver.findElement(By.xpath(generalConstants.cartRemoveProduct)).click();
            driver.findElement(By.xpath(generalConstants.cartCloseButton)).click();
        }
    }
}
