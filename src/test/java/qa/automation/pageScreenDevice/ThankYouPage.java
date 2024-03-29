package qa.automation.pageScreenDevice;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.WebElement;
import qa.automation.pageScreenDevice.manager.ScreenManager;

@Getter
@Setter
public class ThankYouPage extends ScreenManager {

    public ThankYouPage(AppiumDriver<MobileElement> driver)
    {
        super(driver);
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Your order is confirmed']")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Your order is confirmed']")
    public MobileElement orderConfrimText;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Customer Information']")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Customer Information']")
    public MobileElement customerInfoText;

    @AndroidFindBy(id = "plobalsandboxtest.android.staging:id/txtview_product_name")
    public MobileElement productName;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='My Order']")
    @AndroidFindBy(xpath = "//android.widget.Button[contains(@text, 'My Order')]")
    public MobileElement myOrderOnThankYouPage;

    @AndroidFindBy(xpath = "//android.widget.Button[contains(@text, 'Continue Shopping')]")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Continue Shopping']")
    public MobileElement continueShoppingButton;

    @AndroidFindBy(xpath = "//android.widget.TextView[contains(@text, 'Order #')]")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[contains(@name, 'Order: #')]")
    public MobileElement orderId;

    @AndroidFindBy(xpath = "//android.widget.TextView[contains(@text, 'Thank You')]")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[contains(@name, 'Thank You')]")
    public MobileElement thankYouText;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Total Payable']")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Total Payable']")
    public MobileElement totalPayable;

    @AndroidFindBy(xpath = "//android.widget.TextView[contains(@resource-id,'txt_total')]")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[contains(@name,'Shipping Charges')]//following-sibling::XCUIElementTypeStaticText[2]")
    MobileElement totalPayableAmount;

    @AndroidFindBy(xpath = "//android.widget.TextView[contains(@resource-id,'txt_total')]")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Free']/following-sibling:: XCUIElementTypeStaticText[@name='Rs. 0.00 ']")
    public MobileElement totalPayableForFreeProductOnOrderConfirmation;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='Not Now']")
    public MobileElement notNowOnRatingPopUp;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name='Rating']")
    public MobileElement ratingPopUp;

}
