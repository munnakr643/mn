package qa.automation.enums;

public enum DeeplinkPathEnum {
    CART_AND("cart"),
    CART_IOS("feature/cart"),
    LOGIN("login"),
    HOME_IOS("feature/home"),
    HOME_AND(""),
    WISHLIST("wishlist"),
    PRODUCT("products/7224835178541"),
    COLLECTION("collections/277013430317"),
    SEARCH_PAGE("pages/search-results"),
    SEARCH_PAGE_WITH_QUERY("pages/search-results?q=dress"),
    CONTACTUS("pages/contact-us"),
    ADDRESS("account/addresses"),
    ORDERS("orders"),
    REGISTER("account/register"),
    PRODUCT_SANDBOX("products/7867764277493"),
    COLLECTION_TITLE("collections/277013397549?title=Dress");

    private final String value;

    DeeplinkPathEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
