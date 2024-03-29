package qa.automation.pageScreenDevice;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import lombok.Getter;
import lombok.Setter;
import qa.automation.pageScreenDevice.manager.ScreenManager;

@Getter
@Setter
public class CheckoutPage extends ScreenManager {

    public CheckoutPage(AppiumDriver<MobileElement> driver)
    {
        super(driver);
    }

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[contains(@name,'Continue to')]")
    @AndroidFindBy(xpath = "//android.widget.Button[contains(@text,'Continue to')]")
    public MobileElement continueButton;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Cash on Delivery (COD)']")
    @AndroidFindBy(xpath = "//android.widget.RadioButton[@text='Cash on Delivery (COD)']")
    public MobileElement codButton;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='TRACK ORDER']")
    @AndroidFindBy(id= "neovocommercefootware.android.staging:id/thank_you_order_trackorder_button")
    public MobileElement trackOrderButton;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='CONTINUE SHOPPING']")
    @AndroidFindBy(id= "neovocommercefootware.android.staging:id/ok_button")
    public MobileElement continueShoppingButton;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='Complete order']")
    @AndroidFindBy(xpath = "//android.widget.Button[@text='Complete order']")
    public MobileElement completeOrderButton;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[contains(@name,'#')]")
    @AndroidFindBy(xpath ="//android.widget.TextView[contains(@resource-id,'OrderNumber')]")
    public MobileElement orderIDString;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Log in']")
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Log in']")
    public MobileElement loginOnWebviewCheckout;

    @AndroidFindBy(xpath = "(//android.widget.TextView[@index='2'])[1]")
    @iOSXCUITFindBy(xpath = "(//XCUIElementTypeStaticText[@index='1'])[2]")
    public MobileElement userEmailIdOnWebviewCheckout;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Free']")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Free']")
    public MobileElement noshipping;

    @AndroidFindBy(xpath = "//android.widget.ImageView[contains(@resource-id,'address_shipping_selected_rate_edit_imageView')]")
    @iOSXCUITFindBy(xpath = "(//XCUIElementTypeButton[@name='edit icon '])[2]")
    public MobileElement shippingChargeIcon;

    @AndroidFindBy(xpath = "//android.widget.ImageView[contains(@resource-id,'payment_add_imageView')]")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Payment']")
    public MobileElement paymentIcon;

    @AndroidFindBy(xpath = "//android.widget.ImageView[contains(@resource-id,'address_shipping_selected_address_edit_imageView')]")
    @iOSXCUITFindBy(xpath = "(//XCUIElementTypeButton[@name='edit icon '])[1]")
    public MobileElement shippingAddressIcon;

    @AndroidFindBy(id = "plobalsandboxtest.android.staging:id/editTxt_card_number")
    @iOSXCUITFindBy(xpath = "(//XCUIElementTypeTextField[@index='0'])[1]")
    public MobileElement creditCardNumber;

    @AndroidFindBy(id = "plobalsandboxtest.android.staging:id/editTxt_card_firstname")
    @iOSXCUITFindBy(xpath = "(//XCUIElementTypeTextField[@index='0'])[2]")
    public MobileElement firstNameOnCredit;

    @AndroidFindBy(id = "plobalsandboxtest.android.staging:id/EditText_expire_date")
    @iOSXCUITFindBy(xpath = "(//XCUIElementTypeTextField[@index='0'])[3]")
    public MobileElement monthAndYearOnCredit;

    @AndroidFindBy(id = "plobalsandboxtest.android.staging:id/editTxt_card_lastname")
    @iOSXCUITFindBy(xpath = "(//XCUIElementTypeTextField[@index='1'])[1]")
    public MobileElement lastNameOnCredit;

    @AndroidFindBy(id = "plobalsandboxtest.android.staging:id/EditText_cvv")
    @iOSXCUITFindBy(xpath = "(//XCUIElementTypeTextField[@index='1'])[2]")
    public MobileElement cvvOnCredit;

    @AndroidFindBy(xpath = "//android.widget.RelativeLayout[@index='1']")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='PAY NOW']")
    public MobileElement PayNowButton;

    @AndroidFindBy(xpath = "//android.widget.Button[contains(@resource-id,'checkout_confirm_button')]")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='CONTINUE']")
    public MobileElement continueButtonOnCheckout;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Currently you have no saved address']")
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Currently you have no saved addresses']")
    public MobileElement noAddressText;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Remove']")
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Remove']")
    public MobileElement removeAddress;

    @AndroidFindBy(xpath = "(//android.widget.ImageView[@index='4'])[1]")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='+']")
    MobileElement increaseItem;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='-']")
    @AndroidFindBy(xpath = "//android.widget.ImageView[contains(@resource-id,'id/product_item_addtocart_minus')]")
    MobileElement decreaseItem;

    @AndroidFindBy(xpath = "//android.widget.EditText[@index='2']")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeTextField[@index=1]")
    MobileElement itemCount;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='Done']")
    MobileElement doneButtonOnIOSKeyboard;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='ADD PAYMENT']")
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='ADD PAYMENT']")
    MobileElement addPaymentButton;

    @iOSXCUITFindBy(xpath = "(//XCUIElementTypeOther[@name='Field container for: Card number'])[2]")
    @AndroidFindBy(xpath="//android.widget.EditText[@resource-id='number']")
    public MobileElement cardNumber;

    @iOSXCUITFindBy(xpath = "(//XCUIElementTypeOther[@name='Field container for: Name on card'])[2]/XCUIElementTypeOther")
    @AndroidFindBy(xpath="//android.widget.EditText[@resource-id='name']")
    public MobileElement nameOnCard;

    @iOSXCUITFindBy(xpath = "(//XCUIElementTypeOther[@name='Field container for: Expiration date (MM / YY)'])[2]/XCUIElementTypeOther")
    @AndroidFindBy(xpath="//android.widget.EditText[@resource-id='expiry']")
    public MobileElement expDate;

    @iOSXCUITFindBy(xpath = "(//XCUIElementTypeOther[@name='Field container for: Security code'])[2]/XCUIElementTypeOther")
    @AndroidFindBy(xpath="//android.widget.EditText[@resource-id='verification_value']")
    public MobileElement cvv;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='Pay now']")
    @AndroidFindBy(xpath="//android.widget.Button[@text='Pay now']")
    public MobileElement payNow;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='THANK YOU']")
    @AndroidFindBy(xpath="//android.widget.TextView[@text='Thank You']")
    public MobileElement thankyouPage;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='THANK YOU']")
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='THANK YOU']")
    public MobileElement thankyouText;

    @AndroidFindBy(xpath="//android.widget.TextView[contains(@text,'Your Order Has Been Placed')]")
    public MobileElement orderSuccessMsg;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='CONTINUE SHOPPING' or @name='Continue Shopping']")
    @AndroidFindBy(xpath ="//android.widget.Button[@text,'CONTINUE SHOPPING' or @text='Continue Shopping']")
    public MobileElement continueShopping;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='ADD PAYMENT']")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='ADD PAYMENT']")
    public MobileElement addPayment;

    @AndroidFindBy(xpath = "//android.view.ViewGroup[contains(@resource-id, 'toolbar')]/android.widget.ImageButton")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeNavigationBar[@name='Checkout']/XCUIElementTypeButton")
    public MobileElement checkoutBack;

    @iOSXCUITFindBy(xpath = "(//XCUIElementTypeSwitch)[1]")
    @AndroidFindBy(xpath = "//android.widget.ToggleButton[contains(@class, 'Toggle')]")
    public MobileElement showOrderSummary;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeTextField[@name='Gift card or discount code']")
    @AndroidFindBy(xpath = "(//android.widget.EditText)[1]")
    public MobileElement discountOnCheckoutPage;

    @AndroidFindBy(xpath = "//android.widget.ListView/android.view.View/android.widget.TextView")
    @iOSXCUITFindBy(xpath = "(//XCUIElementTypeOther[@name='Gift card or discount code']//preceding-sibling::XCUIElementTypeOther/XCUIElementTypeStaticText)[2]")
    public MobileElement discountlabel;

    @AndroidFindBy(xpath = "//android.widget.TextView[contains(@text,'Gift card ending with')]")
    @iOSXCUITFindBy(xpath = "(//XCUIElementTypeOther[@name='Gift card or discount code'])[2]/XCUIElementTypeOther")
    public MobileElement giftLabel;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='Apply Discount Code']")
    @AndroidFindBy(xpath = "(//android.widget.Button)[1]")
    public MobileElement clickArrowOnDiscountPage;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[contains(@name,'Remove')]")
    @AndroidFindBy(xpath = "//android.widget.Button[contains(@text,'Remove')]")
    public MobileElement discountRemoveButton;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeOther/XCUIElementTypeOther[4]/XCUIElementTypeStaticText[contains(@name,'₹')]")
    @AndroidFindBy(xpath = "//android.view.View[4]/android.view.View/android.widget.TextView")
    public MobileElement productPriceBeforeDiscount;


    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Add New Address']")
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Add New Address']")
    public MobileElement addAddressOnCheckoutNativePage;

    @AndroidFindBy(xpath = "//android.widget.ImageView[contains(@resource-id,'address_shipping_selected_address_')]")
    @iOSXCUITFindBy(xpath = "(//XCUIElementTypeButton[@label='+'])[1]")
    public MobileElement addAddressPlusIcon;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='First Class Package International']")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='First Class Package International']")
    public MobileElement shippingChargeName;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='FREE']")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Free']")
    public MobileElement freeshippingCharge;

    @AndroidFindBy(xpath = "//android.widget.TextView[contains(@resource-id,'btn_myprofile_login')]")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[contains(@name,'Login')]")
    public MobileElement loginButtonOnNativeCheckoutPage;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[contains(@name, 'Automatic discount applied')]")
    @AndroidFindBy(xpath = "(//android.widget.TextView[contains(@text , 'Automatic discount applied')])[1]")
    MobileElement automaticDisccountAppliedStringOnCheckoutPage;

    @AndroidFindBy(xpath = "//android.widget.ToggleButton[contains(@text,'Show order summary')]")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeSwitch[contains(@name,'Show order summary')]")
    public  MobileElement showOrderSummaryHeaderBar;

    @AndroidFindBy(xpath = "(//android.widget.TextView[contains(@text,'Rs')])[last()]")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[contains(@name,'Total Payable')]//following-sibling::XCUIElementTypeStaticText")
    MobileElement totalAmountOnNativeCheckoutPage;

    @iOSXCUITFindBy(xpath = "(//XCUIElementTypeStaticText[@name='MESSI'])[2]")
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='MESSI']")
    MobileElement discountOnCheckoutPages;

    @AndroidFindBy(xpath = "//android.widget.ScrollView/android.widget.RelativeLayout/android.widget.LinearLayout/android.widget.TextView[contains(@text, 'Signed in as')]")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[contains(@name,'Signed in as')]")
    MobileElement userEmailId;

    @AndroidFindBy(xpath = "(//android.view.View[contains(@resource-id,'disclosure_content')]//android.widget.TextView)[3]")
    @iOSXCUITFindBy(xpath = "(//XCUIElementTypeStaticText[contains(@name,'₹')]//preceding-sibling:: XCUIElementTypeStaticText)[1]")
    public MobileElement discountCodeTextUnderShowOrderSummary;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Add New Address']")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Add New Address']")
    public MobileElement addNewAddresButton;

    @AndroidFindBy(xpath = "(//android.widget.ImageView[@resource-id,'payment_add_imageView'])[4]")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='+']")
    public MobileElement addNewPaymentdetails;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Credit card is invalid']")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Credit card is invalid']")
    public MobileElement alertMessageForInvalidCreditCard;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='CONTINUE']")
    public MobileElement continuePlaceOrder;

    @AndroidFindBy(xpath = "//android.view.View[contains(@text, 'Subtotal')]//parent::android.view.View/android.view.View[2]")
    @iOSXCUITFindBy(xpath = "(//XCUIElementTypeStaticText[contains(@value,'₹')])[2]")
    public MobileElement subtotalOnCheckoutPage;

    @AndroidFindBy(xpath = "(//android.widget.GridView[contains(@text, 'Cost summary')]//parent::android.view.View/android.view.View)[6]")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[contains(@value,'− ₹')]")
    public MobileElement discountAmountOnCheckoutPage;

    @AndroidFindBy(xpath = "//android.view.View[contains(@text, 'INR ₹')]/android.view.View/android.view.View[2]")
    @iOSXCUITFindBy(xpath = "(//XCUIElementTypeStaticText[contains(@value,'₹')])[6]")
    public MobileElement totalOnCheckoutPage;

    @AndroidFindBy(xpath = "//android.widget.ListView[@text='Gift card or discount code']/android.view.View/android.widget.TextView")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name='Gift card or discount code']/XCUIElementTypeOther/XCUIElementTypeStaticText")
    public MobileElement discountCodeOnWebviewCheckout;

    @AndroidFindBy(xpath = "//android.webkit.WebView/android.webkit.WebView/android.view.View[1]/android.view.View/android.view.View/android.view.View[3]/android.view.View")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name='complementary']")
    public MobileElement orderSummarySection;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Your order is']")
    public MobileElement freePaymentMessage1;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='free']")
    public MobileElement freePaymentMessage2;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='. No payment is required.']")
    public MobileElement freePaymentMessage3;

    @AndroidFindBy(xpath = "(//android.view.View/android.widget.TextView[@text,'Your order is free. No payment is required.'])[3]")
    public MobileElement freePaymentMessage;

    @AndroidFindBy(xpath = "//android.widget.TextView[contains(@text, 'Log out')]")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Log out']")
    public MobileElement logoutFromWebview;


    @AndroidFindBy(xpath = "(//android.widget.TextView[contains(@text,'₹')])[2]")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[contains(@name, 'Cost summary')]/XCUIElementTypeOther[3]/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeStaticText")
    public MobileElement taxOnCheckout;

    @AndroidFindBy(xpath = "//android.widget.RadioButton[contains(@resource-id,'billing_address_selector-shipping_address')]")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[contains(@name,'Same as shipping address')]")
    public MobileElement sameAsShippingAddressRadioButton;

    @AndroidFindBy(xpath = "//android.widget.RadioButton[contains(@resource-id,'billing_address_selector-custom_billing_address')]")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[contains(@name,'Use a different billing address')]")
    public MobileElement useDifferentBillingAddressRadioButton;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Enter a last name']")
    public MobileElement errorMessageOnCheckoutPageForBillingAddress;

    @AndroidFindBy(xpath = "//android.view.View[contains(@resource-id,'billing_address_selector-custom_billing_address-collapsible')]//android.view.View[contains(@resource-id,'Select0')]")
    @iOSXCUITFindBy(xpath ="//XCUIElementTypeOther[@name='Billing address, region']/XCUIElementTypeOther[3]/XCUIElementTypeOther[4]/XCUIElementTypeOther[2]")
    public MobileElement dropDownForBillingAddressSelection;

    @AndroidFindBy(xpath ="(//android.widget.CheckedTextView[contains(@resource-id,'text1')])[last()]")
    @iOSXCUITFindBy(xpath = "(//XCUIElementTypeOther[contains(@name,'Horizontal scroll bar, 1 page')])[2]")
    public MobileElement useANewAddressOptionUnderDropdown;

    @AndroidFindBy(xpath = "//android.view.ViewGroup[contains(@resource-id, 'toolbar')]/android.widget.ImageButton")
    @iOSXCUITFindBy(xpath = "(//XCUIElementTypeNavigationBar[@name='Order Details']/XCUIElementTypeButton)[1]")
    public MobileElement backOnOrderDetails;
}
