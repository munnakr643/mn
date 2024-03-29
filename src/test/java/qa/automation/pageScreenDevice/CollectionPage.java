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
public class CollectionPage extends ScreenManager{

    public CollectionPage(AppiumDriver<MobileElement> driver)
    {
        super(driver);
    }

    /**
     * Owner: Aditya Nisal
     * Collection page locators
     */

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Fashion']")
    @AndroidFindBy(xpath="//android.widget.TextView[@text='Fashion']")
    public MobileElement fashionCollection;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Dress']")
    @AndroidFindBy(xpath="//android.widget.TextView[@text='Dress']")
    public MobileElement dressCollection;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Tops']")
    public MobileElement topsCollection;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='Back']")
    @AndroidFindBy(xpath = "//android.view.ViewGroup[contains(@resource-id, 'toolbar')]/android.widget.ImageButton")
    MobileElement backButtonIcon;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Shoes']")
    @AndroidFindBy(xpath = "(//android.widget.TextView[text(),'Shoes'])[2]")
    MobileElement shoesCollection;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='View Collection']")
    @AndroidFindBy(xpath = "(//android.widget.TextView[text(),'Sandals'])[3]")
    MobileElement sandalsCollection;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeSearchField[@name='Search products']")
    @AndroidFindBy(xpath = "//android.widget.EditText[contains(@resource-id, 'search_product_search_edittext')]")
    MobileElement searchOnCollectionPage;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeNavigationBar[@name='EcommerceLibraryProjectMain.CategoryView']//XCUIElementTypeStaticText")
    @AndroidFindBy(xpath = "//android.widget.TextView[contains(@resource-id,'textview_title')]")
    MobileElement categoryHeaderTextOnCategoryPage;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Jacket & Jumpsuit']")
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Jacket & Jumpsuit']")
    MobileElement jacketAndJumpSuitCollection;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='View Collection']/parent::XCUIElementTypeOther/XCUIElementTypeStaticText[1]")
    @AndroidFindBy(xpath = "//android.widget.TextView[contains(@resource-id,'viewtype_category_title_textview')]")
    MobileElement collectionText;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Variant Product']")
    @AndroidFindBy(xpath="//android.widget.TextView[@text='Variant Product']")
    public MobileElement variantProductCollection;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Free Product']")
    @AndroidFindBy(xpath="(//android.widget.RelativeLayout[contains(@resource-id,'relativeLayout')]/android.widget.TextView[@text,'Free Product'])[9]")
    public MobileElement freeProduct;

}
