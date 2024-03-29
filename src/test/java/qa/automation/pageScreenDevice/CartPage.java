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

public class CartPage extends ScreenManager {
    public CartPage(AppiumDriver<MobileElement> driver) {
        super(driver);
    }

    public void enterDiscount(String discount)
    {
        getApplyDiscountPopupOnCartPage().sendKeys(discount);
    }

    @AndroidFindBy(xpath ="//android.widget.TextView[@text='Cart']")
    public MobileElement cartIconOnCartPage;

    @iOSXCUITFindBy(xpath = "(//XCUIElementTypeStaticText[contains(@name,'Rs')])[2]/preceding-sibling:: XCUIElementTypeStaticText[2]")
    @AndroidFindBy(id = "txtview_product_name")
    MobileElement firstProductTitleOnCartPaga;

    @AndroidFindBy(xpath = "//android.widget.TextView[contains(@resource-id,'price_discounted')]")
    @iOSXCUITFindBy(xpath = "(//XCUIElementTypeStaticText[contains(@name,'Rs. ')])[3]")
    MobileElement firstProductPriceOnCartPage;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Apply Coupon Code']")
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Apply Coupon Code']")
    MobileElement applydiscountOnCartPage;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeTextField[contains(@value,'Add a Coupon Code')]")
    @AndroidFindBy(xpath = "//android.widget.EditText[@text='Add a coupon code']")
    MobileElement applyDiscountPopupOnCartPage;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='PLACE ORDER']")
    @AndroidFindBy(xpath = "//android.widget.Button[@text='PLACE ORDER']")
    MobileElement placeOrderButtonOnCartPage;

    @iOSXCUITFindBy(xpath = "(//XCUIElementTypeOther[@index='0'])[13]")
    @AndroidFindBy(xpath = "//android.widget.TextView[contains(@resource-id,'shopping_cart_applied_coupon_code_tv')]")
    MobileElement discountAppliedOnCartPage;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[contains(@name, 'Congratulation! You Got Discount')]")
    @AndroidFindBy(xpath = "//android.widget.TextView[contains(@text,'Congratulations!')]")
    MobileElement discountAppliedStatusOnCartPage;

    @iOSXCUITFindBy(xpath = "(//XCUIElementTypeStaticText[contains(@name, 'Rs. ')])[4]")
    @AndroidFindBy(id = "neovocommercefootware.android.staging:id/order_details_item_value_textview")
    @CacheLookup
    MobileElement subtotalAmountOnCartPage;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Discount']/following-sibling:: XCUIElementTypeStaticText")
    @AndroidFindBy(xpath = "(//android.widget.TextView[@resource-id='neovocommercefootware.android.staging:id/order_details_item_value_textview'])[2]")
    @CacheLookup
    MobileElement discountedAmountOnCartPage;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='APPLY COUPON']")
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='APPLY COUPON']")
    MobileElement applyCouponbutton;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='+']//parent::XCUIElementTypeOther/XCUIElementTypeTextField")
    @AndroidFindBy(xpath = "//android.widget.EditText[contains(@resource-id,'total_quantity')]")
    MobileElement productsQuantity;

    @iOSXCUITFindBy(xpath = "(//XCUIElementTypeStaticText[contains(@name, 'Rs. ')])[1]")//please don't update-munna
    @AndroidFindBy(xpath = "//android.widget.TextView[contains(@resource-id,'txtview_total')]")
    MobileElement totalAmount;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='+']")
    @AndroidFindBy(xpath = "//android.widget.ImageView[contains(@resource-id,'addtocart_plus')]")
    MobileElement increaseQuantity;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='-']")
    @AndroidFindBy(xpath = "//android.widget.ImageView[contains(@resource-id,'addtocart_minus')]")
    MobileElement decreaseQuantity;

    @AndroidFindBy(xpath = "//android.widget.RelativeLayout[contains(@resource-id,'shopping_cart_faviorite_view')]")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[contains(@name , 'Add more from favorites')]")
    MobileElement addMoreFromFavoriteButton;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='close20']")
    @AndroidFindBy(xpath = "(//android.widget.ImageView[@resource-id,'img_coupon_select'])[7]")
    MobileElement removeDiscountFromCartPage;

    /**
     * App name: Sandbox
     */
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='PLACE ORDER']")
    @AndroidFindBy(xpath = "//android.widget.Button[@text='PLACE ORDER']")
    MobileElement placeOrderNative;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Shop Pay']")
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Shop Pay']")
    MobileElement shopPayPopup;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Credit Card']")
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Credit Card']")
    MobileElement creditCardPopup;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Other']")
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Other']")
    MobileElement otherPopup;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='There are no items currently in your cart'] | //XCUIElementTypeStaticText[contains(@name,'cart is empty')]")
    @AndroidFindBy(xpath = "//android.widget.TextView[contains(@resource-id,'_screen_description')]")
    MobileElement emptyCartMessage;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='CONTINUE SHOPPING']")
    @AndroidFindBy(xpath = "//android.widget.Button[contains(@resource-id,'_screen_retry_btn')]")
    MobileElement continueShopping;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Updated the quantity of the Item']")
    MobileElement checkquantitygetupdate;

    @AndroidFindBy(xpath = "//android.view.ViewGroup[contains(@resource-id, 'toolbar')]/android.widget.ImageButton")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeNavigationBar[@name='Your Cart']/XCUIElementTypeButton[1] | //XCUIElementTypeNavigationBar[@name='BagView']/XCUIElementTypeButton[1]")
    MobileElement backButtonFromCart;

    @iOSXCUITFindBy(xpath = "(//XCUIElementTypeStaticText[contains(@name,'Rs. ')])[2]//parent::XCUIElementTypeOther/XCUIElementTypeStaticText[2]")
    @AndroidFindBy(xpath = "//android.widget.TextView[contains(@resource-id,'_item_variant_option_1')]")
    MobileElement variantCartPage;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Cart']")
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Cart']")
    MobileElement cartHeaderTextOnCartPage;

    @iOSXCUITFindBy(xpath = "(//XCUIElementTypeOther[@name='giftCouponApplyView']//child::XCUIElementTypeStaticText)[2]")
    @AndroidFindBy(xpath = "(//android.widget.TextView[resource-id,'textinput_error'])[2]")
    MobileElement discountCodeErrorMessage;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeNavigationBar[@name='Your Cart']/XCUIElementTypeButton[1]")
    MobileElement backButtonOnCartPage;

    @iOSXCUITFindBy(xpath = "(//XCUIElementTypeButton[@name='+'])[1] | (//XCUIElementTypeButton[@name='edit icon'])[1]")
    @AndroidFindBy(xpath = "(//android.widget.ImageView[contains(@resource-id,'address_shipping')])[1]")
    MobileElement addAddressIconNativeCheckoutPage;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='SAVE ADDRESS']")
    @AndroidFindBy(id = "plobalsandboxtest.android.staging:id/editaddressactivity_submit_button")
    MobileElement clickSaveButton;

    @iOSXCUITFindBy(xpath = "(//XCUIElementTypeStaticText[contains(@name , 'Automatic discount applied')])[1]")
    @AndroidFindBy(xpath = "(//android.widget.TextView[contains(@text , 'Automatic discount applied')])[1]")
    MobileElement automaticDiscountAppliedStringOnCartPage;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Add more from favorites ']")
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Add more from favorite']")
    MobileElement FavoriteButtonOnCartPage;

    @iOSXCUITFindBy(xpath = "(//XCUIElementTypeButton[@name='Hey! Grab a discount of 20% using code WELCOME20 on your first purchase'])[2]")
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Hey! Grab a discount of 20% using code WELCOME20 on your first purchase']")
    MobileElement StaticMessageOnCart;

    @iOSXCUITFindBy(xpath = "(//XCUIElementTypeButton[contains(@name , 'I have read and agree')])[1]")
    @AndroidFindBy(xpath = "//android.widget.TextView[contains(@text , 'I have read and agree')]")
    MobileElement accceptTermsOnCart;

    @AndroidFindBy(xpath = "//android.widget.TextView[contains(@resource-id,'item_discount_lable_textview')]")
    @iOSXCUITFindBy(xpath = "(//XCUIElementTypeStaticText[contains(@name,'Rs')])[3]")
    public MobileElement automaticDiscountTextOnCartPage;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[contains(@name,'% off')]")
    @AndroidFindBy(xpath="//android.widget.TextView[contains(@resource-id,'txt_price_discounted')]")
    MobileElement ProductPriceOnCartPage;

    @AndroidFindBy(xpath = "//android.widget.ImageView[contains(@resource-id,'imgView_product_image')]")
    @iOSXCUITFindBy(xpath = "(//XCUIElementTypeStaticText[contains(@name,'Rs.')])[2]//preceding-sibling:: XCUIElementTypeImage")
    public MobileElement productImageOnCartPage;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Total Payable']/following-sibling:: XCUIElementTypeStaticText[@name='Rs. 0.00 ']")
    @AndroidFindBy(xpath = "//android.widget.TextView[contains(@resource-id,'txt_edit_total_value')]")
    MobileElement totalPayableForFreeProduct;

}

