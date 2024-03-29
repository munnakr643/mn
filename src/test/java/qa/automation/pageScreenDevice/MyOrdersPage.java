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
public class MyOrdersPage extends ScreenManager {

    public MyOrdersPage(AppiumDriver<MobileElement> driver)
    {
        super(driver);
    }

    @iOSXCUITFindBy(xpath = "(//XCUIElementTypeStaticText[@index='5'])[1]")
    @AndroidFindBy(xpath = "(//android.widget.TextView[@index='1'])[1]")
    public MobileElement orderID;

    @AndroidFindBy(xpath = "//android.widget.RelativeLayout[@index='2']")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='Order Cancel']")
    public MobileElement orderCancelButton;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Reorder']")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='REORDER']")
    public MobileElement reorderButton;

    @AndroidFindBy(xpath = "(//android.widget.RelativeLayout[@index='0'])[12]")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='TRACK MY ORDER']")
    public MobileElement trackOrderButton;

    /**
     * App Name: Sandbox
     */

    @AndroidFindBy(xpath = "(//android.widget.TextView[@index='1'])[1]")
    @iOSXCUITFindBy(xpath = "(//XCUIElementTypeStaticText[@index='7'])[1]")
    public MobileElement orderIDFromNewOrderListing;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[contains(@name, 'ORDER ID:')]")
    @AndroidFindBy(xpath = "(//android.widget.TextView[contains(@resource-id,'textview_orderId')])[1]")
    public MobileElement orderIDOnNewOrderDetails;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[contains(@name, 'Item')]")
    public MobileElement orderItemOnNewOrderDetails;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[contains(@name, 'Rs. ')]")
    public MobileElement orderPriceOnNewOrderDetails;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[contains(@name, 'Qty')]/following-sibling::XCUIElementTypeImage")
    public MobileElement orderImageOnNewOrderDetails;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[contains(@name, 'Qty')]")
    public MobileElement orderQuantityOnNewOrderDetails;

    @AndroidFindBy(xpath = "//android.widget.TextView[contains(@resource-id,'error_screen_description')]")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='No order found for the associated Email ID.']")
    public MobileElement noOrderPlacedYetOnMyOrderSection;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Payment:']")
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Payment: ']")
    MobileElement paymentTextOnTrackOrder;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Fulfillment:']")
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Fulfillment:']")
    MobileElement fulfillmentTextOnTrackOrder;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='cart']")
    @AndroidFindBy(xpath = "//android.widget.ImageView[contains(@resource-id,'cart_custom_menu_imageView')]")
    MobileElement cartIcon;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='My Orders']")
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='My Orders']")
    MobileElement myOrderText;

    @AndroidFindBy(xpath = "(//android.widget.ImageView[contains(@resource-id,'pdp_image_sticker')])[1]")
    public MobileElement imageStickerOnOrderDetailsPage;

    @AndroidFindBy(xpath = "(//android.widget.RelativeLayout[contains(@resource-id,'relative_myorder_cell')])[1]")
    MobileElement FirstOrderFromOrderListing;
}
