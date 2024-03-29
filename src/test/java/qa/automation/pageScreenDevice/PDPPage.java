package qa.automation.pageScreenDevice;


import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.support.CacheLookup;
import qa.automation.pageScreenDevice.manager.ScreenManager;

@Getter
@Setter
public class PDPPage extends ScreenManager{

    /**
     * Owner: Aditya Nisal
     * PDP locators
     */
    public PDPPage(AppiumDriver<MobileElement> driver)
    {
        super(driver);
    }

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeNavigationBar[@name='Details']/XCUIElementTypeButton[3]")
    @AndroidFindBy(xpath = "//android.widget.ImageView[contains(@resource-id,'cart_custom_menu_imageView')]")
    public MobileElement cartIcon;

    @AndroidFindBy(xpath = "//android.widget.TextView[contains(@resource-id,'txt_product_title')]")
    //@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[contains(@name,'Rs')]/following-sibling::XCUIElementTypeStaticText") // This locator is wrong hence updated
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[contains(@name,'Rs')]/preceding-sibling::XCUIElementTypeStaticText | //XCUIElementTypeStaticText[contains(@name,'Rs')]/following-sibling::XCUIElementTypeStaticText")
    public MobileElement productTitleOnPdp;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[contains(@name,'Rs')]")
    @AndroidFindBy(xpath ="//android.widget.TextView[contains(@resource-id,'txt_price_discounted')]")
    public MobileElement productPriceOnPdp;

    @AndroidFindBy(id ="neovocommercefootware.android.staging:id/textview_title")
    @CacheLookup
    public MobileElement productTitleOnTopOfPdp;

    @AndroidFindBy(xpath = "//android.widget.ImageView[contains(@resource-id,'pager_item_imageView')]")
    @CacheLookup
    public MobileElement productImageOnPDP;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text= 'M']")
    @CacheLookup
    public MobileElement productSizeM;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='L']")
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='L']")
    @CacheLookup
    public MobileElement productSizeL;

    @AndroidFindBy(id = "stevemadden.android.app:id/cart_custom_menu_imageView")
    @CacheLookup
    public MobileElement cartIconForSM;

    @AndroidFindBy(id ="stevemadden.android.app:id/txt_price_discounted")
    @CacheLookup
    public MobileElement productPriceOnPdpForSM;

    @AndroidFindBy(id ="stevemadden.android.app:id/txt_product_title")
    @CacheLookup
    public MobileElement productTitleOnPdpForSM;

    @AndroidFindBy(id ="stevemadden.android.app:id/textview_title")
    @CacheLookup
    public MobileElement productTitleOnTopOfPdpForSM;

    @AndroidFindBy(id ="stevemadden.android.app:id/action_search")
    @CacheLookup
    public MobileElement searchIcon;

    @AndroidFindBy(id ="stevemadden.android.app:id/product_review_ratingbar")
    @CacheLookup
    public MobileElement starRating;

    @AndroidFindBy(id ="stevemadden.android.app:id/product_review_TextView")
    @CacheLookup
    public MobileElement averageRatingCountOnPdp;

    @AndroidFindBy(id ="stevemadden.android.app:id/product_details_variant_details_textview")
    @CacheLookup
    public MobileElement variantOptionName;

    @AndroidFindBy(xpath = "(//android.widget.ImageView[@resource-id='stevemadden.android.app:id/circular_image'])[3]")
    @CacheLookup
    public MobileElement variantImageForSM;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='FavoritePDP']")
    @AndroidFindBy(xpath = "//android.widget.ImageView[contains(@resource-id,'imageview_wish_icon')]")
    public MobileElement wishlistButton;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='shareHomeProdList']")
    @AndroidFindBy(xpath = "//android.widget.ImageView[contains(@resource-id,'pdp_share_like_share')]")
    public MobileElement shareButton;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='ADD TO CART']/parent::XCUIElementTypeOther")
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='ADD TO CART']")
    public MobileElement CTAbutton;

    @AndroidFindBy(xpath = "//android.widget.Button[contains(@resource-id,'action_search')]")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeNavigationBar[@name='Details']/XCUIElementTypeButton[2]")
    public MobileElement searchButton;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Select Quantity']")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Select Quantity']")
    public MobileElement selectQuantityTextOnPDPPage;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='XS']")
    @AndroidFindBy(xpath = "(//android.widget.TextView[resource-id,'product_options_textView'])[7]")
    @CacheLookup
    MobileElement XSVariantOnPDP;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='S']")
    @AndroidFindBy(xpath = "(//android.widget.TextView[resource-id,'product_options_textView'])[8]")
    @CacheLookup
    MobileElement SVariantOnPDP;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='M']")
    @AndroidFindBy(xpath = "(//android.widget.TextView[resource-id,'product_options_textView'])[9]")
    @CacheLookup
    MobileElement MVariantOnPDP;

    @iOSXCUITFindBy(xpath = "(//XCUIElementTypeStaticText[contains(@name,'Rs')]/parent::XCUIElementTypeCell/following-sibling:: XCUIElementTypeCell//XCUIElementTypeImage)[1]")
    @AndroidFindBy(xpath = "(//android.widget.ImageView[@resource-id,'circular_image'])[5]")
    @CacheLookup
    MobileElement relatedProductOnPDP;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='ADD TO CART']/parent::XCUIElementTypeOther/XCUIElementTypeButton")
    @AndroidFindBy(xpath = "//android.widget.RelativeLayout[contains(@resource-id,'bottom_add_to_cart')]")
    MobileElement addToCartButtonOnPDP;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='Confirm']")
    @AndroidFindBy (xpath = "(//android.widget.Button[text(),'CONFIRM'])[2]")
    @CacheLookup
    MobileElement confirmButtonAfterAddToCartClick;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='FavoritePDP']")
    @AndroidFindBy(xpath ="//android.widget.ImageView[contains(@resource-id,'imageview_wish_icon')]")
    MobileElement addToFavoriteIconOnPDPPage;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='Back']")
    @AndroidFindBy(xpath = "//android.view.ViewGroup[contains(@resource-id,'toolbar')]/android.widget.ImageButton")
    MobileElement backArrowOnNativeApp;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='Confirm']")
    @AndroidFindBy(xpath="//android.widget.Button[@text='CONFIRM']")
    public MobileElement confirm;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Related']")
    MobileElement relatedProductHeaderText;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Select Size']")
    @AndroidFindBy(xpath = "//android.widget.TextView[contains(@resource-id, 'action_product_details_option_1_TextView')]")
    MobileElement selectSizeText;


    /**
     * App name: Sandbox
     */

    @AndroidFindBy(xpath = "(//android.widget.LinearLayout/android.widget.RelativeLayout/android.widget.TextView[@text,'ADD TO CART'])[1]")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='GO TO CART']/parent::XCUIElementTypeOther/XCUIElementTypeButton")
    MobileElement goToCartPageFromPdp;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[contains(@name,'Rs')][1]/following-sibling::XCUIElementTypeStaticText[1]")
    @AndroidFindBy(id = "txt_discount_message")
    MobileElement additionalPromoLabelOnPDP;

    @iOSXCUITFindBy(xpath = "(//XCUIElementTypeStaticText[@name='Lapels Jacket'])[2]")
    MobileElement productLpJacket;

    @AndroidFindBy(xpath = "//android.widget.ScrollView[contains(@resource-id,'contentPanel')]")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='Close']")
    MobileElement closeSharePage;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[contains(@name,'Sold By:')]")
    @AndroidFindBy(xpath = "//android.widget.TextView[contains(@resource-id,'product_sold_by')]")
    MobileElement vendorName;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeImage[@name='copy']")
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Copy']")
    MobileElement copyButtonOnSharePage;

    @AndroidFindBy(xpath = "//android.view.View[contains(@resource-id,'sem_chooser_preview_icon')]")
    MobileElement checkOutIconOnSharePage;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[contains(@name,'FavoritePDP')][1]/following-sibling::XCUIElementTypeOther[1]")
    @AndroidFindBy(xpath = "(//android.widget.ImageView[contains(@resource-id,'pdp_image_sticker')])[1]")
    public MobileElement imageStickerOnPDP;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='ADD TO CART']/parent::XCUIElementTypeOther")
    @AndroidFindBy(xpath="//android.widget.TextView[@text='ADD TO CART']")
    public MobileElement addCart;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='BUY NOW']/parent::XCUIElementTypeOther")
    @AndroidFindBy(xpath="//android.widget.TextView[@text='BUY NOW']")
    public MobileElement buyNow;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name = '+']")
    @AndroidFindBy(xpath = "//android.widget.ImageView[contains(@resource-id, 'product_item_addtocart_plus')]")
    public MobileElement increaseQuantityButton;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[contains(@name, 'Description')]")
    @AndroidFindBy(xpath = "//android.widget.TextView[contains(@resource-id, 'txt_description')]")
    public MobileElement descriptionHeader;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name = '-']")
    @AndroidFindBy(xpath = "//android.widget.ImageView[contains(@resource-id, 'product_item_addtocart_minus')]")
    public MobileElement decreaseQuantityButton;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name = '+']/preceding-sibling::XCUIElementTypeTextField")
    @AndroidFindBy(xpath = "//android.widget.EditText[contains(@resource-id, 'product_item_addtocart_total_quantity')]")
    public MobileElement productQuantity;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text = 'Extra Rs. 89.10 at Checkout Rs. 108.90 and Rs. 99.00 or Rs. 9.90']")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Extra Rs. 89.10  at Checkout Rs. 108.90  and Rs. 99.00  or Rs. 9.90 ']")
    public MobileElement Additionallabelv2OnPDP;

    @iOSXCUITFindBy(xpath = "(//XCUIElementTypeCollectionView[@type='XCUIElementTypeCollectionView']/XCUIElementTypeCell/XCUIElementTypeOther/XCUIElementTypeImage)[4]")
    @AndroidFindBy(xpath = "(//android.widget.ImageView[contains(@resource-id, 'circular_image')])[3]")
    public MobileElement variantSwitch;

    @AndroidFindBy(xpath = "(//androidx.recyclerview.widget.RecyclerView[contains(@resource-id,'rv_shop_the_look')])/android.widget.LinearLayout[2]")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[contains(@name,'Horizontal scroll bar, 1 page')]//preceding-sibling:: XCUIElementTypeCell[2]")
    public MobileElement secondVSKOnPDP;

    @AndroidFindBy(xpath = "(//androidx.recyclerview.widget.RecyclerView[contains(@resource-id,'rv_shop_the_look')])/android.widget.LinearLayout[3]")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[contains(@name,'Horizontal scroll bar, 1 page')]//preceding-sibling:: XCUIElementTypeCell[1]")
    public MobileElement thirdVSKOnPDP;

    @AndroidFindBy(xpath = "//android.widget.RelativeLayout[contains(@resource-id,'horizontal_scroll_products_view')]")
    @iOSXCUITFindBy(xpath = "(//XCUIElementTypeButton[@name='productDetails'])[2]")
    public MobileElement productRecommendationOnPDP;
}
