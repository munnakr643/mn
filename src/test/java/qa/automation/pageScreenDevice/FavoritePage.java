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
public class FavoritePage extends ScreenManager {
    public static Logger logger = LogManager.getLogger(FavoritePage.class);

    public FavoritePage(AppiumDriver<MobileElement> driver)
    {
        super(driver);
    }

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeNavigationBar[@name='Favorites']")
    @AndroidFindBy(xpath = "(//android.widget.TextView[text(),'Favorites'])[1]")
    MobileElement favoriteTextOnFavoritePage;

    @iOSXCUITFindBy(xpath = "(//XCUIElementTypeNavigationBar[@name='Favorites']/following-sibling::XCUIElementTypeOther//XCUIElementTypeStaticText[1])[1]")
    @AndroidFindBy(xpath = "(//android.widget.TextView[contains(@resource-id,'txtView_product_name')])")
    MobileElement firstProductTextOnFavoritePage;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@value='ADD TO CART']")
    @AndroidFindBy(xpath = "//android.widget.Button[contains(@resource-id, 'btn_add_to_cart')]")
    MobileElement addToCartButtonOnPopUp;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeNavigationBar[@name='Favorites']/XCUIElementTypeButton[1]")
    @AndroidFindBy(xpath = "//android.view.ViewGroup[contains(@resource-id, 'toolbar')]/android.widget.ImageButton")
    MobileElement backButtonIcon;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='White Mini Dress']")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='White Mini Dress']")
    MobileElement productNameOnWishlist;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[contains(@name,'$')]")
    @AndroidFindBy(xpath="//android.widget.TextView[contains(@resource-id,'txt_price_discounted')]")
    public MobileElement productPriceFavDollar;

    @AndroidFindBy(xpath= "//android.widget.ImageView[contains(@resource-id,'imgView_remove_product')]")
    @iOSXCUITFindBy(xpath ="//XCUIElementTypeButton[@name='closeicon']")
    public MobileElement crossbuttonIcon;

    @AndroidFindBy(xpath= "//android.widget.TextView[@text='You currently do not have any Items added to Favorites']")
    @iOSXCUITFindBy(xpath ="//XCUIElementTypeStaticText[@name='You currently do not have any Items added to Favorites']")
    public MobileElement emptyfavouritepage;
}
