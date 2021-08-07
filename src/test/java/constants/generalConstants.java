package constants;

public class generalConstants {
    public static final String productNamesXpath = "//div[@class='shelf-item']/p";
    public static final String productPricesXpath = "//div[@class='shelf-item']/div[3]/div[1]";
    public static final String productInstallmentPricesXpath = "//div[@class='shelf-item']/div[3]/div[2]";

    public static final String cartProductName = "//div[@class='shelf-item__details']/p[1]";
    public static final String cartProductType = "//div[@class='shelf-item__details']/p[2]";
    public static final String cartProductPrice = "//div[@class='shelf-item__price']/p[1]";

    public static final String cartSubPrice = "//div[@class='sub-price']/p[1]";
    public static final String cartSubInstallmentPrice = "//div[@class='sub-price']/small/span";

    public static final String cartRemoveProduct = "//div[@class='shelf-item__del']";
    public static final String cartCloseButton = "//div[@class='float-cart__close-btn']";

    public static final String addToCartButtons = "//div[@class='shelf-item']/div[4]";

    public static final String productsFilter = "//div[@class='filters-available-size']/label/span";

    public static final String noOffersText = "//main[@class='fit']/div[1]/div[1]/div[1]";



}
