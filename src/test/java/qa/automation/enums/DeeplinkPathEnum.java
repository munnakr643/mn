package qa.automation.enums;

public enum DeeplinkPathEnum {
    CART_AND("cart"),
    CART_IOS("feature/cart"),
    LOGIN("login"),
    HOME_IOS("feature/home"),
    HOME_AND(""),
    WISHLIST("wishlist"),
    
    private final String value;

    DeeplinkPathEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
