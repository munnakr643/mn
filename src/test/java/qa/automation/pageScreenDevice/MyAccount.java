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

public class MyAccount extends ScreenManager {
    public MyAccount(AppiumDriver<MobileElement> driver) {
        super(driver);
    }

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Login']")
    @AndroidFindBy(xpath = "(//android.widget.TextView[@text='LOGIN'])[2]")
    public MobileElement loginButton;

    @iOSXCUITFindBy(xpath = "(//XCUIElementTypeStaticText[@index='1'])[2]")
    @AndroidFindBy(xpath = "(//android.widget.TextView[@index=1])[2]") // Neove App
    @CacheLookup
    public MobileElement loginEmailIDfield;

    @AndroidFindBy(xpath = "(//android.widget.TextView[@index=1])[1]") // Steve Madden App
    @CacheLookup
    public MobileElement loginEmailIDForSteve;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Edit Profile']")
    @AndroidFindBy(xpath="//android.widget.TextView[@text='Edit Profile']") //Steve Madden App
    @CacheLookup
    public MobileElement editProfileButtonOnProfilePage; // Steve Madden App

    @iOSXCUITFindBy(xpath = "(//XCUIElementTypeImage[@name='newProfileEditDetails']/following-sibling::XCUIElementTypeTable/XCUIElementTypeCell/XCUIElementTypeTextField)[1]")
    @AndroidFindBy(xpath = "//android.widget.EditText[contains(@resource-id,'editText_firstname')]") // Steve Madden App
    @CacheLookup
    public MobileElement firstNameOnEditProfilePage;

    @iOSXCUITFindBy(xpath = "(//XCUIElementTypeImage[@name='newProfileEditDetails']/following-sibling::XCUIElementTypeTable/XCUIElementTypeCell/XCUIElementTypeTextField)[2]")
    @AndroidFindBy(xpath = "//android.widget.EditText[contains(@resource-id,'editText_lastname')]") // Steve Madden App
    @CacheLookup
    public MobileElement lastNameOnEditProfilePage;

    @iOSXCUITFindBy(xpath = "(//XCUIElementTypeImage[@name='newProfileEditDetails']/following-sibling::XCUIElementTypeTable/XCUIElementTypeCell/XCUIElementTypeTextField)[3]")
    @AndroidFindBy(xpath = "//android.widget.EditText[contains(@resource-id,'editText_email')]") // Steve Madden App
    @CacheLookup
    public MobileElement emailIdOnEditProfilePage;

    @AndroidFindBy(xpath = "(//android.widget.TextView[@resource-id,'btnEdit_profile'])[5]")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='UPDATE PROFILE']")
    public MobileElement updateProfileOnEditProilePage;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeNavigationBar[@name='EDIT PROFILE']/XCUIElementTypeButton")
    @AndroidFindBy(xpath = "//android.widget.ImageButton[@index=0]") // Steve Madden App
    public MobileElement backButtonOnEditProfilePage;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Delete Account']")
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Delete Account']") // Steve Madden App
    public MobileElement deleteAccountButtonOnProfilePage;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='CONFIRM DELETE']")
    @AndroidFindBy(xpath = "//android.widget.Button[contains(@resource-id , 'btn_confirm_delete')]") // Steve Madden App
    public MobileElement confirmDeleteButtonOnProfilePage;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Logout']")
    @AndroidFindBy(xpath = "//android.widget.Button[@text='LOGOUT']")
    @CacheLookup
    public MobileElement logoutButtonOnProfilePage;


    @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='REGISTER']")
    @AndroidFindBy(xpath = "//android.widget.TextView[contains(@resource-id,'btn_register')]")
    public MobileElement registerBtn;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='My Orders']")
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='My Orders']")
    public MobileElement myOrders;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Favorites']")
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Favorites']")
    public MobileElement favorites;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeApplication[@name='staging plobal-sandbox-test']/XCUIElementTypeWindow/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeCollectionView/XCUIElementTypeCell[4]/XCUIElementTypeOther")
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='My Addresses']")
    public MobileElement myaddress;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='Remove']")
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Remove']")
    public MobileElement removeaddress;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Settings']")
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Settings']")
    public MobileElement settings;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='My Orders']")
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='My Orders']")
    public MobileElement myOrderPageText;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[contains(@name,'No order found')]")
    @AndroidFindBy(xpath = "//android.widget.TextView[contains(@resource-id,'error_screen_description')]")
    public MobileElement noOrderMessage;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[contains(@name,'ORDER ID')]")
    @AndroidFindBy(xpath = "//android.widget.TextView[contains(@resource-id,'_orderId')]")
    public MobileElement orderId;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='ITEMS DETAILS']")
    @AndroidFindBy(xpath = "//android.widget.TextView[contains(@resource-id,'product_details')]")
    public MobileElement productDetails;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[contains(@name,'Qty:')]//parent::XCUIElementTypeCell/XCUIElementTypeStaticText[2]")
    @AndroidFindBy(xpath = "//android.widget.TextView[contains(@resource-id,'textview_quantity')]")
    public MobileElement productVariant;

    @iOSXCUITFindBy(xpath = "(//XCUIElementTypeStaticText[contains(@name,'Rs.')])[2]")
    @AndroidFindBy(xpath = "//android.widget.TextView[contains(@resource-id,'textView_product_price')]")
    public MobileElement productPrice;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='CT-notification']")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='CT-notification']")
    public MobileElement ctInboxButton;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Notifications']")
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='CT-notification']")
    public MobileElement pnTitleOnCtInboxPage;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Added to cart']/parent::XCUIElementTypeCell/XCUIElementTypeStaticText[3]")
    @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='neovocommercefootware.android.staging:id/timestamp']")
    public MobileElement pnTimestampOnCtInboxPage;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Added to cart']")
    @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='neovocommercefootware.android.staging:id/messageTitle']")
    public MobileElement pnMessageOnCtInboxPage;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeCell[@name='27']")
    @AndroidFindBy(xpath = "(//android.widget.RelativeLayout[contains(@resource-id,'layout_cart')]//parent::android.widget.LinearLayout/android.widget.ImageView)[5]")
    public MobileElement myAccountLogoSandbox;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Login']")
    @AndroidFindBy(xpath = "//android.widget.Button[contains(@resource-id,'_login_button')]")
    public MobileElement loginBtnMyProfile;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Are you sure you want to remove this address?']")
    @AndroidFindBy(xpath = "//android.widget.TextView[@text,'Are you sure you want to remove this address?']")
    MobileElement textOnRemoveAddressPopup;

    @AndroidFindBy(xpath = "//android.widget.Button[contains(@resource-id , 'btn_confirm_delete')]")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='YES, GO AHEAD']")
    MobileElement yesGoAhead;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeTextField[@value='First Name*']")
    @AndroidFindBy(xpath = "(//android.widget.EditText[@resource-id,'ui_editText'])[1]")
    MobileElement firstNameAccountPage;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeTextField[@value='Last Name*']")
    @AndroidFindBy(xpath = "(//android.widget.EditText[@resource-id,'ui_editText'])[2]")
    MobileElement lastNameAccountPage;

    @AndroidFindBy(xpath = "//android.widget.Button[@text='LINK']")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='Link']")
    MobileElement ctPnLink;

    @AndroidFindBy(xpath = "//android.widget.TextView[contains(@text,'Login to access order details')]")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[contains(@name,'Login to access order details')]")
    MobileElement beforeLoginMessage;

    @AndroidFindBy(xpath = "//android.widget.RelativeLayout[contains(@resource-id,'profile_35_my_order_relativeLayout')]")
    @iOSXCUITFindBy(xpath = "(//XCUIElementTypeOther[@index='0'])[12]")
    MobileElement myOrdersBlock;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='✕']")
    MobileElement xButtonOnCtNotification;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Choose Address']")
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='My Addresses']")
    public MobileElement addressPage;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Your address have been added to your account.']")
    public MobileElement addressAddedPopUp;

    @AndroidFindBy(xpath = "//android.widget.Button[@text='OK']")
    public MobileElement okOnAddressAddedPopUp;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeTable[@name='Empty list']")
    @AndroidFindBy(xpath = "//android.widget.TextView[contains(@resource-id,'no_address_message')]")
    public MobileElement noAddressList;
}
