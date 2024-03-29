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
public class PLPPage extends ScreenManager {

    /**
     * Assignee Name: Aditya
     * PLP locators
     */

    public PLPPage(AppiumDriver<MobileElement> driver) {
        super(driver);
    }

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='White Mini Dress']")
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='White Mini Dress']")
    public MobileElement whiteMiniDress;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[contains(@name,'Rs. ')]")
    @AndroidFindBy(xpath = "(//android.widget.TextView[contains(@resource-id,'txt_price_discounted')])[1]")
    public MobileElement whiteMiniDressPrice;

    @AndroidFindBy(xpath = "(//android.widget.ImageView[@resource-id='stevemadden.android.app:id/imageview_product'])[1]")
    public MobileElement ProductPLPForSM;

    @AndroidFindBy(xpath = "(//android.widget.TextView[@resource-id='stevemadden.android.app:id/txt_product_title'])[1]")
    public MobileElement ProductNameFromPLPForSM;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='FILTER']")
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='FILTER']")
    public MobileElement filterButton;

    @AndroidFindBy(xpath = "(//android.widget.TextView[@resource-id='stevemadden.android.app:id/product_review_TextView'])[1]")
    public MobileElement averageRatingCountPlp;

    @AndroidFindBy(id = "stevemadden.android.app:id/product_review_ratingbar")
    @CacheLookup
    public MobileElement starRating;

    @AndroidFindBy(id = "stevemadden.android.app:id/txt_product_title")
    @CacheLookup
    public MobileElement productTitleOnPlpForSM;

    @AndroidFindBy(xpath = "(//android.widget.TextView[@resource-id='stevemadden.android.app:id/txt_price_discounted'])[1]")
    @CacheLookup
    public MobileElement cobraBlackLeatherPriceForSm;

    @AndroidFindBy(xpath = "(//android.widget.RatingBar[@resource-id ='stevemadden.android.app:id/product_review_ratingbar'])[1]")
    @CacheLookup
    public MobileElement averageRatingFromPlpForSm;

    @iOSXCUITFindBy(xpath = "(//XCUIElementTypeStaticText[contains(@name,'Rs')])[1]/preceding-sibling::XCUIElementTypeImage")
    @AndroidFindBy(xpath = "(//android.widget.ImageView[contains(@resource-id,'imageview_product')])[1]")
    public MobileElement plpPoster;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='SORT']")
    @AndroidFindBy(xpath = "//android.widget.TextView[contains(@resource-id,'textView_sort')]")
    public MobileElement sortButton;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Price - High-Low']")
    @AndroidFindBy(xpath = "//android.widget.TextView[contains(@text,'Price - High-Low')]")
    @CacheLookup
    public MobileElement priceHighToLowSorting;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Price - Low-High']")
    @AndroidFindBy(xpath = "//android.widget.TextView[contains(@text,'Price - Low-High')]")
    @CacheLookup
    public MobileElement priceLowToHighSorting;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Alphabetically A to Z']")
    @AndroidFindBy(xpath = "//android.widget.TextView[contains(@text,'Alphabetically A to Z')]")
    @CacheLookup
    public MobileElement alphabeticallyAToZSorting;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Alphabetically Z to A']")
    @AndroidFindBy(xpath = "//android.widget.TextView[contains(@text,'Alphabetically Z to A')]")
    @CacheLookup
    public MobileElement alphabeticallyZToASorting;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='By date: Newest to Oldest']")
    @AndroidFindBy(xpath = "//android.widget.TextView[contains(@text,'By date: Newest to Oldest')]")
    @CacheLookup
    public MobileElement dateNewToOldSorting;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='By date: Oldest to Newest']")
    @AndroidFindBy(xpath = "//android.widget.TextView[contains(@text,'By date: Oldest to Newest')]")
    @CacheLookup
    public MobileElement dateOldToNewSorting;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='By bestselling']")
    @AndroidFindBy(xpath = "//android.widget.TextView[contains(@text,'By bestselling')]")
    @CacheLookup
    public MobileElement bestsellingSorting;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Manually']")
    @AndroidFindBy(xpath = "//android.widget.TextView[contains(@text,'Manually')]")
    @CacheLookup
    public MobileElement ManuallySorting;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[contains(@name,'Rs. ')]")
    @AndroidFindBy(xpath = "//android.widget.TextView[contains(@resource-id,'txt_price_discounted')]")
    public MobileElement productPriceListInPLP;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='SORT']")
    @AndroidFindBy(xpath = "//android.widget.TextView[contains(@resource-id,'txt_product_title')]")
    @CacheLookup
    public MobileElement productTitleListInPLP;

    @AndroidFindBy(xpath = "//android.widget.TextView[contains(@resource-id,'textview_title')]")
    @CacheLookup
    MobileElement plpHeader;

    @AndroidFindBy(xpath = "(//android.widget.ImageView[contains(@resource-id,'popular_offer_1')])[2]")
    @CacheLookup
    MobileElement firstProductOnPLPPage;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='Back']")
    @AndroidFindBy(xpath = "//android.view.ViewGroup[contains(@resource-id, 'toolbar')]/android.widget.ImageButton")
    MobileElement backButtonIcon;

    public void clickSortButton() {
        sortButton.click();
    }

    @iOSXCUITFindBy(xpath = "(//XCUIElementTypeStaticText[contains(@name, 'Dress') or contains(@name, 'Top')])[2]")
    @AndroidFindBy(xpath = "//android.widget.TextView[contains(@resource-id,'txt_product_title')]")
    MobileElement plp1stProduct;

    @iOSXCUITFindBy(xpath = "(//XCUIElementTypeStaticText[@name='Filter By'])[2]")
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Filter By']")
    public MobileElement filterPageText;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Size']")
    @CacheLookup
    public MobileElement size;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Color']")
    @CacheLookup
    public MobileElement color;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Brand']")
    @CacheLookup
    public MobileElement brand;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Availability']")
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Availability']")
    public MobileElement availability;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Price']")
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Price']")
    public MobileElement price;

    @iOSXCUITFindBy(xpath = "(//XCUIElementTypeOther[@name='Vertical scroll bar, 1 page']/parent::XCUIElementTypeScrollView/following-sibling::XCUIElementTypeCollectionView//XCUIElementTypeStaticText)[3]")
    @AndroidFindBy(id = "txt_discount_message")
    MobileElement additionalLabelOnPLP;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='In stock']")
    @AndroidFindBy(xpath = "//android.widget.TextView[@text = 'In stock']")
    MobileElement inStockFilterOption;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Out of stock']")
    @AndroidFindBy(xpath = "//android.widget.TextView[@text = 'Out of stock']")
    MobileElement outOfStockFilterOption;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='APPLY']")
    @AndroidFindBy(xpath = "//android.widget.TextView[@text ='APPLY']")
    MobileElement applyFilterButton;


    @AndroidFindBy(xpath = "(//androidx.recyclerview.widget.RecyclerView[@id,'product_recyclerview']//android.widget.RelativeLayout[@id,'linear_layout_image'])[2]")
    MobileElement firstProductOnSandalPLPPage;

    @AndroidFindBy(xpath = "//android.widget.TextView[contains(@resource-id,'textview_title')]")
    MobileElement plpPageTitle;


    /**
     * App Name: Sandbox
     */

    @AndroidFindBy(id = "plobalsandboxtest.android.staging:id/btn_shopthelook")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='Shop The Look']")
    MobileElement shopTheLookButton;

    @iOSXCUITFindBy(xpath = "(//XCUIElementTypeButton[@name='ProductAddToCart'])[2]")
    @AndroidFindBy(xpath = "(//android.widget.ImageView[contains(@resource-id,'product_action_item_imgView')])[3]")
    MobileElement addToCartIconOnPlp;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Select Size']")
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Select Size']")
    MobileElement selectSizeTextOnAddToCartPopup;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='ADD TO CART']")
    @AndroidFindBy(xpath = "//android.widget.Button[@text='ADD TO CART']")
    MobileElement addToCartButtonOnAddToCartPopup;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='L']")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='L']")
    MobileElement selectLVarientFromAddToCartPopup;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[contains(@name,'$')]")
    @AndroidFindBy(xpath = "//android.widget.TextView[contains(@resource-id,'txt_price_discounted')]")
    public MobileElement productPricePLPDollar;

    @iOSXCUITFindBy(xpath = "(//XCUIElementTypeStaticText[contains(@name,'Rs.')]/parent::XCUIElementTypeOther/parent::XCUIElementTypeCell/XCUIElementTypeOther/XCUIElementTypeImage)[2]")
    @AndroidFindBy(xpath = "(//android.widget.ImageView[contains(@resource-id,'pdp_image_sticker')])[1]")
    public MobileElement imageStickerOnPLP;


    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Extra Rs. 89.10  at Checkout Rs. 108.90  and Rs. 99.00  or Rs. 9.90 ']")
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Extra Rs. 89.10 at Checkout Rs. 108.90 and Rs. 99.00 or Rs. 9.90']")
    public MobileElement addtionalLabelV2;


    @iOSXCUITFindBy(xpath = "//XCUIElementTypeCell[@name='26']/XCUIElementTypeOther")
    @AndroidFindBy(xpath = "//android.widget.ImageView[contains(@resource-id,'cart_custom_menu')]")
    public MobileElement CartIconOnPlp;

    @AndroidFindBy(xpath = "//android.widget.LinearLayout[contains(@resource-id,'linear_layout_product_title')]/android.widget.TextView")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[contains(@name,'Rs.')]//preceding-sibling:: XCUIElementTypeStaticText")
    public MobileElement productTitleOnPLP;

    @AndroidFindBy(xpath = "(//android.widget.ImageView[contains(@resource-id,'product_action_item_imgView')])[1]")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[contains(@name,'like icon')]")
    public MobileElement addToFavoriteIconOnPLPForNeovo;

    @AndroidFindBy(xpath = "(//android.widget.ImageView[contains(@resource-id,'product_action_item_imgView')])[2]")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[contains(@name,'ProductAddToCart')]")
    public MobileElement addToCartIconOnPLPForNeovo;

    @AndroidFindBy(xpath = "//android.widget.TextView[contains(@resource-id,'error_screen_description')]")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Currently there are no Items available for the applied filter']")
    public MobileElement emptyResultOnPLP;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='CLEAR ALL']")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='CLEAR ALL']")
    public MobileElement clearAllButtonOnFilter;
}