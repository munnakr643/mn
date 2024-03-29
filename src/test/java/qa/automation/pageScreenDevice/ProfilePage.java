package qa.automation.pageScreenDevice;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import lombok.Getter;
import lombok.Setter;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.support.CacheLookup;
import qa.automation.pageScreenDevice.manager.ScreenManager;

@Getter
@Setter
public class ProfilePage extends ScreenManager {
    public static Logger logger = LogManager.getLogger(ProfilePage.class);

    public ProfilePage(AppiumDriver<MobileElement> driver)
    {
        super(driver);
    }

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Logout' or @name='Login']")
    @AndroidFindBy(xpath = "//android.widget.Button[text(),'LOGOUT']")
    MobileElement loginOrLogoutButtonOnProfilePage;

    @AndroidFindBy(xpath = "(//android.widget.Button[@id,'button1'])[2]")
    @CacheLookup
    MobileElement okConfirmationPopupAfterLogout;

    @AndroidFindBy(xpath = "(//android.widget.Button[@id,'button1'])[1]")
    @CacheLookup
    MobileElement cancleConfirmationPopupAfterLogout;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Favorites']")
    @AndroidFindBy(xpath = "//android.widget.TextView[contains(@resource-id,'_fav_title_textview')]")
    MobileElement favoriteFeatureOnProfilePage;

    @AndroidFindBy(xpath = "(//android.widget.TextView[@resource-id,'profile_35_user_name'])[2]")
    @CacheLookup
    MobileElement userNameOnProfilePage;

    @AndroidFindBy(xpath = "(//android.widget.TextView[@resource-id,'profile_35_user_name'])[3]")
    @CacheLookup
    MobileElement userEmailOnProfilePage;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='My Addresses']")
    @AndroidFindBy(xpath = "//android.widget.RelativeLayout[contains(@resource-id,'profile_35_address_relativeLayout')]")
    MobileElement addressFeatureOnProfilePage;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='CONFIRM DELETE']")
    @AndroidFindBy(xpath = "//android.widget.Button[contains(@resource-id , 'btn_confirm_delete')]")
    MobileElement deleteAccountConfirmButton;

    @AndroidFindBy(id = "profile_35_my_order_relativeLayout")
    MobileElement myOrdersFeatureOnProfilePage;

    @AndroidFindBy(xpath = "//android.widget.TextView[contains(@resource-id , 'profile_35_user_name')]")
    @iOSXCUITFindBy(xpath = "(//XCUIElementTypeCell/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeStaticText)[1]")
    MobileElement userNameOnProfile;

    @AndroidFindBy(xpath = "//android.widget.TextView[contains(@resource-id , 'profile_35_user_email')]")
    @iOSXCUITFindBy(xpath = "(//XCUIElementTypeCell/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeStaticText)[2]")
    MobileElement userNameEmail;

    @AndroidFindBy(xpath = "//android.widget.TextView[contains(@resource-id , 'profile_35_user_name')]")
    @iOSXCUITFindBy(xpath = "(//XCUIElementTypeCell/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeStaticText)[1]")
    MobileElement LoggedInUserNameOnProfilePage;

    @AndroidFindBy(xpath = "//android.widget.TextView[contains(@resource-id , 'profile_35_user_email')]")
    @iOSXCUITFindBy(xpath = "(//XCUIElementTypeCell/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeStaticText)[2]")
    MobileElement emailOnProfilePage;

    @AndroidFindBy(xpath = "//android.widget.Button[contains(@text, 'LOGIN')]")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='Login']")
    MobileElement loginButtonOnProfile;
}

