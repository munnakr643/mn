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
public class MorePage extends ScreenManager{

    public MorePage(AppiumDriver<MobileElement> driver)
    {
        super(driver);
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[contains(@resource-id,'txtview_info')]")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Info']")
    public MobileElement infoSectionOn;

    @AndroidFindBy(xpath = "//android.widget.TextView[contains(@text,'About ')]")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[contains(@name,'About ')]")
    public MobileElement aboutUs;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Fashion']")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Fashion']")
    public MobileElement fashionPage;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='Done']")
    public MobileElement doneButton;

    @AndroidFindBy(xpath = "//android.view.View[@content-desc='W3Layouts']/android.widget.Image")
    @iOSXCUITFindBy(xpath = "(//XCUIElementTypeImage[@name='W3Layouts'])[1]")
    public MobileElement inFashionPage;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='About Us Page']")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='About Us Page']")
    public MobileElement inAboutUsPage;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='CT-Notification']")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='CT-Notification']")
    public MobileElement ctInboxOnMorePage;

    @AndroidFindBy(xpath = "//XCUIElementTypeStaticText[@name='CT-notification'] | //android.widget.TextView[contains(@text,'Notification')]")
    @iOSXCUITFindBy(xpath = "(//XCUIElementTypeStaticText[@name='CT-notification'])[1] | //XCUIElementTypeStaticText[contains(@name,'Notifications')]")
    public MobileElement ctInboxTitle;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeImage[@name='iconNotification']")  //Icon is not there for Android
    public MobileElement ctInboxicon;

    @AndroidFindBy(id = "neovocommercefootware.android.staging:id/layout_multi_lang")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='English (en)']")
    public MobileElement languageOnMorePage;

    @AndroidFindBy(id = "android:id/alertTitle")
    @iOSXCUITFindBy(xpath = "(//XCUIElementTypeStaticText[@name='Language'])[2]")
    public MobileElement languagePopupTitle;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='close icon']")
    public MobileElement closeIconOnLanguagePopup;

    @AndroidFindBy(xpath = "//android.widget.CheckedTextView[@index='0']")
    @iOSXCUITFindBy(xpath = "(//XCUIElementTypeStaticText[@name='English (en)'])[2]")
    public MobileElement englishLanguageOnPopup;

    @AndroidFindBy(xpath = "//android.widget.CheckedTextView[@index='1']")
    @iOSXCUITFindBy(xpath = "(//XCUIElementTypeOther[@index='0'])[12]")
    public MobileElement arabicLanguageOnPopup;

    @AndroidFindBy(xpath = "//android.widget.CheckedTextView[@text='French (BE) (fr)']")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='French (BE) (fr)']")
    public MobileElement frenchLanguagePopup;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='plobal-sandbox-test'] | //XCUIElementTypeStaticText[@name='Neovo-Commerce']")
    @AndroidFindBy(xpath = "//android.widget.TextView[contains(@resource-id,'_store_selected_name')]")//plobal-sandbox-test//Neovo-Commerce
    public MobileElement    selectedStoreName;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='my profile']")
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='my profile']")
    public MobileElement myProfile;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Cart']")
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Cart']")
    public MobileElement cart;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Home page']")
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Home page']")
    public MobileElement homepage;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='category']")
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='category']")
    public MobileElement category;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Contact Us']")
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Contact Us']")
    public MobileElement contactUs;

    @iOSXCUITFindBy(xpath = "(//XCUIElementTypeStaticText[@name='Contact Us'])[2]")
    @AndroidFindBy(xpath = "//android.widget.TextView[contains(@resource-id,'txtview_contact_us')]")
    MobileElement contactUsBtn;

    @iOSXCUITFindBy(xpath = "(//XCUIElementTypeStaticText)[7]")
    @AndroidFindBy(xpath = "(//android.widget.TextView)[3]")
    public MobileElement contactUsAddressOnContactUsPage;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='French (BE) (fr)']")
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='French (BE) (fr)']")
    public MobileElement french;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Indian Rupee']")
    @AndroidFindBy(xpath = "//android.widget.RelativeLayout[contains(@resource-id,'layout_currency')]")
    public MobileElement currency;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='US Dollar']")
    @AndroidFindBy(xpath = "//android.widget.CheckedTextView[@text='US Dollar']")
    public MobileElement usDollar;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Contact Us']")
    @iOSXCUITFindBy(xpath = "(//XCUIElementTypeStaticText[@name='Contact Us'])[2]")
    public MobileElement contactUsHeader;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Reach Us At']")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Reach Us At']")
    public MobileElement addressHeaderUnderContactUs;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Size chart']")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Size chart']")
    public MobileElement sizeChart;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Size Chart Table']")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Size Chart Table']")
    public MobileElement sizeChartTable;
}
