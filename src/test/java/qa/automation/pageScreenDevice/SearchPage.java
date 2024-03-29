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
public class SearchPage extends ScreenManager {
    public SearchPage(AppiumDriver<MobileElement> driver)
    {
        super(driver);
    }

    @iOSXCUITFindBy(xpath = "(//XCUIElementTypeStaticText[contains(@name,'Extra Rs. ')])[1]")
    @AndroidFindBy(id = "txt_discount_message")
    MobileElement additionalPromoLabelOnSearchPage;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Search']")
    @AndroidFindBy(id = "barcode_search_textView")
    MobileElement searchHeaderTextOnSearchPage;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeSearchField[@name='Search products']")
    @AndroidFindBy(xpath = "//android.widget.EditText[contains(@resource-id ,'search_product_search_edittext')]")
    MobileElement searchHeaderTextOnSearchPageSandbox;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='Search']")
    MobileElement searchButtonOnIOSKeyboard;

    @AndroidFindBy(xpath = "//android.widget.TextView[contains(@text,'off')]")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[contains(@name,'off')]")
    MobileElement discountOnSearchPage;

    @iOSXCUITFindBy(xpath = "(//XCUIElementTypeCollectionView/XCUIElementTypeCell/XCUIElementTypeOther/XCUIElementTypeImage)[2]")
    @AndroidFindBy(xpath = "(//android.widget.ImageView[contains(@resource-id,'pdp_image_sticker')])[1]")
    MobileElement imageStickerOnSearchPage;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Extra Rs. 89.10  at Checkout Rs. 108.90  and Rs. 99.00  or Rs. 9.90 ']")
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Extra Rs. 89.10 at Checkout Rs. 108.90 and Rs. 99.00 or Rs. 9.90']")
    MobileElement addtionalLabelV2OnSearchPage;

    @AndroidFindBy(xpath = "(//android.widget.ImageView[@resource-id,'imageview_product'])[4]")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[contains(@name,'Rs')]//preceding-sibling:: XCUIElementTypeImage")
    public MobileElement firstProductOnSearchPage;

    @AndroidFindBy(xpath = "(//android.widget.ImageView[resource-id, 'search_product_backarrow_imageview'])[1]")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='Cancel']")
    public MobileElement backarrowIconOnSearchPage;

    @AndroidFindBy(xpath = "(//android.widget.ImageView[contains(@resource-id ,'product_action_item_imgView')])[2]")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='like icon']")
    public MobileElement wishlistIconOnSearchPage;
}
