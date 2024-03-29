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
public class HomePage extends ScreenManager {



	public HomePage(AppiumDriver<MobileElement> driver)
	{
		super(driver);
	}

	@AndroidFindBy(xpath="//android.view.View[@content-desc='Mobiles']")
	public MobileElement Mobiles;

	@AndroidFindBy(xpath="//android.view.View[@content-desc='Electronics']")
	public MobileElement Electronics;

	@AndroidFindBy(xpath="//android.view.View[@content-desc='Home']")
	public MobileElement Home;

	@AndroidFindBy(xpath="//android.view.View[@content-desc='Mobiles']")
	public MobileElement Appliances;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='GOT IT']")
	public MobileElement gotIt;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Leads']")
	public MobileElement leads;

	@AndroidFindBy(xpath="//android.widget.Button[@text='Quote']")
	public MobileElement quoteUnderLeads;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeNavigationBar[@name='TopTabBarView']/XCUIElementTypeButton[2]")
	@AndroidFindBy(id="neovocommercefootware.android.staging:id/cart_custom_menu_imageView")
	public MobileElement cart;

	@iOSXCUITFindBy(xpath = "(//XCUIElementTypeCollectionView/XCUIElementTypeCell/XCUIElementTypeOther/XCUIElementTypeImage/following-sibling::XCUIElementTypeButton)[1]")
	@AndroidFindBy(xpath="(//android.widget.ImageView[contains(@resource-id,'popular_offer_1')])[2]")
	public MobileElement homePoster;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='View More']")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='View More']")
	public MobileElement viewMoreButtonOnHomePage;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeCell[@name='27']/XCUIElementTypeOther/XCUIElementTypeImage[3]")
	@AndroidFindBy(xpath="(//android.widget.RelativeLayout[@index='2'])[3]")
	@CacheLookup
	public MobileElement myAccount;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[contains(@name, 'Change Password')]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Change Password']")
	public MobileElement changePassword;

	@AndroidFindBy(xpath="//android.widget.EditText[contains(@resource-id,'editText_password')]")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeSecureTextField[@value = 'New Password']")
	@CacheLookup
	public MobileElement newPassword;

	@AndroidFindBy(xpath="//android.widget.EditText[contains(@resource-id,'editText_confirm_password')]")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeSecureTextField[@value = 'Confirm Password']")
	@CacheLookup
	public MobileElement confirmPassword;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='RESET PASSWORD']")
	@AndroidFindBy(xpath="//android.widget.TextView[contains(@resource-id,'btnEdit_profile')]")
	public MobileElement resetPasswordButton;

	public void enterNewPassword(String newPasswordEnter){
		newPassword.sendKeys(newPasswordEnter);
	}
	public void enterConfirmPassword(String confirmPasswordEnter){
		confirmPassword.sendKeys(confirmPasswordEnter);
	}

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeNavigationBar[@name='TopTabBarView']/XCUIElementTypeButton[1]")
	@AndroidFindBy(id= "neovocommercefootware.android.staging:id/imageView_navigation_view")
	public MobileElement drawerForNeovo;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeNavigationBar[@name='TopTabBarView']/XCUIElementTypeButton[1]")
	@AndroidFindBy(id= "plobalsandboxtest.android.staging:id/imageView_navigation_view")
	public MobileElement drawerForSandbox;

	@AndroidFindBy(xpath = "(//android.widget.RelativeLayout)[6]")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeApplication[@name='staging plobal-sandbox-test']/XCUIElementTypeWindow/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeTable/XCUIElementTypeCell[2]")
	public MobileElement homeButtonFromHamburger;

	@iOSXCUITFindBy(id = "  Login or Register")
	@AndroidFindBy(id= "neovocommercefootware.android.staging:id/imageView_app_logo")
	public MobileElement loginButtonOnHamburgerMenuForNeovo;

	@iOSXCUITFindBy(id = "  Login or Register")
	@AndroidFindBy(id= "plobalsandboxtest.android.staging:id/imageView_navigation_view")
	public MobileElement loginButtonOnHamburgerMenuForSandbox;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Homepage']/parent::XCUIElementTypeCell/parent::XCUIElementTypeTable/XCUIElementTypeCell[1]/XCUIElementTypeStaticText")
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Aditya Nisal']")
	public MobileElement UserNameOnHamburgerMenuForNeovo;

	/**
	App name: Steve Madden
	 */
	@AndroidFindBy(xpath="//android.view.View[@index=4]")
	@CacheLookup
	public MobileElement onBoardingNextButton1;

	@AndroidFindBy(xpath="//android.widget.Button[@text='NEXT']")
	@CacheLookup
	public MobileElement onBoardingNextButton2;

	@AndroidFindBy(xpath="//android.widget.Button[@text='While using the app']")
	@CacheLookup
	public MobileElement locationAllowed;

	@AndroidFindBy(xpath="//android.widget.ImageView[contains(@resource-id,'imageView_app_logo')]")
	@CacheLookup
	public MobileElement appLogoOnHomePage;

	@AndroidFindBy(xpath="//android.widget.ImageView[contains(@resource-id,'id/cart_custom_menu')]")
	@CacheLookup
	public MobileElement cartButtonOnHomePage;

	@AndroidFindBy(xpath="//android.widget.ImageView[@index=1]")
	@CacheLookup
	public MobileElement hamburgerButtonOnHomePage; //Hamburger Menu Button on Home Page

	@AndroidFindBy(xpath="(//android.widget.ImageView[@index=0])[2]")
	@CacheLookup
	public MobileElement loginButtonOnHamburgerMenu; //Login Button on Hamburger Menu

	@AndroidFindBy(xpath="//android.widget.ImageView[contains(@resource-id,'imageView_navigation_view')]")
	@iOSXCUITFindBy(xpath="//XCUIElementTypeStaticText[@name='My Profile']")
	public MobileElement profileButtonOnHamburgerMenu; //Profile Button on Hamburger Menu

	@AndroidFindBy(xpath="//android.widget.ImageView[contains(@resource-id,'id/cart_custom_menu')]")
	public MobileElement cartForSM;

	@AndroidFindBy(xpath="//android.widget.ImageView[contains(@resource-id,'navigation_view')]")
	public MobileElement hamburgerMenuForSM;

	@AndroidFindBy(xpath="(//android.widget.ImageView[contains(@resource-id,'id/popular_offer_1')])[1]")
	public MobileElement homepageElement1;

	@iOSXCUITFindBy(xpath = "(//XCUIElementTypeButton)[4]")
	@AndroidFindBy(xpath = "(//android.widget.ImageView[contains(@resource-id,'popular_offer_1')])[position()=2]")
	@CacheLookup
	public MobileElement pdpRedirectableBannerOnHomePage;

	@AndroidFindBy(xpath = "(//android.widget.ImageView[contains(@resource-id,'popular_offer_1')])[position()=3]")
	public MobileElement plpRedirectableBannerOnHomePage;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeNavigationBar[@name='TopTabBarView']/XCUIElementTypeButton[1]")
	@AndroidFindBy(xpath = "(//android.widget.ImageView[@index=1])[1]")
	@CacheLookup
	MobileElement drawerOnHomePage;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeCell[@name='25']/XCUIElementTypeOther/XCUIElementTypeImage[3]")
	@AndroidFindBy(xpath = "(//android.widget.ImageView[@index=1])[2]")
	@CacheLookup
	MobileElement categoryTabOnHomePage;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeCell[@name='27']")
	@AndroidFindBy(xpath = "(//android.widget.RelativeLayout[contains(@resource-id,'layout_cart_items')])[1]")
	MobileElement profileTabOnHomePage;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeCell[@name='26']/XCUIElementTypeOther/XCUIElementTypeImage[3]")
	@AndroidFindBy(xpath = "(//android.widget.RelativeLayout[contains(@resource-id,'layout_cart_items')])[2]")
	MobileElement cartTabOnHomePage;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeCell[@name='28']//XCUIElementTypeImage[3]")
	@AndroidFindBy(xpath = "//android.widget.RelativeLayout[contains(@resource-id,'layout_cart')]//parent::android.widget.LinearLayout/android.widget.ImageView[3]")
	MobileElement moreTabOnHomePage;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeCell[@name='28']//XCUIElementTypeImage[3]")
	@AndroidFindBy(xpath = "//android.widget.RelativeLayout[contains(@resource-id, 'layout_cart')]/following-sibling::android.widget.ImageView")
	MobileElement moreTabOnHomePageSandboxStore;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeNavigationBar[@name='TopTabBarView']//XCUIElementTypeImage")
	@AndroidFindBy(xpath = "(//android.widget.ImageView[@resource-id,'imageView_app_logo'])[1]")
	MobileElement appLogoOnHeader;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Search products']/parent::XCUIElementTypeOther/parent::XCUIElementTypeOther/following-sibling::XCUIElementTypeButton" +
			"| //XCUIElementTypeStaticText[@name='Search products']/preceding-sibling::XCUIElementTypeButton")
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Search products']")
	MobileElement searchProductsText;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeSearchField[@name='Search products']")
	@AndroidFindBy(xpath = "//android.widget.EditText[@text='Search products']")
	MobileElement enterSearchKey;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeCell[@name='28']/XCUIElementTypeOther/XCUIElementTypeImage[3]")
	@AndroidFindBy(xpath = "//android.widget.ImageView[@index='4']")
	MobileElement hamburgerClose;

	@AndroidFindBy(xpath = "(//android.widget.LinearLayout[@index='0'])[9]")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[contains(@value,'More')]")
	MobileElement moreOnHamburger;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='My Profile']")
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='My Profile']")
	MobileElement myProfileOnHamburger;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Category']")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Category']")
	MobileElement categoryOnHamburger;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Dress']")
	@AndroidFindBy(xpath= "//android.widget.LinearLayout[contains(@resource-id,'homescr51_appbarlayout')]")
	MobileElement subCategoryOnHamburger;

	/**
	 App name: Sandbox-Store
	 */

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='High Waist Jeans']")
	@AndroidFindBy(xpath = "(//android.widget.ImageView[@resource-id='plobalsandboxtest.android.staging:id/popular_offer_1'])[3]")
	MobileElement productFromHomepageForSandbox;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeNavigationBar[@name='TopTabBarView']/XCUIElementTypeButton[2]")
	@AndroidFindBy(xpath = "//android.widget.ImageView[contains(@resource-id,'cart_custom_menu_imageView')]")
	MobileElement cartIconForSandbox;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Multi2']")
	@AndroidFindBy(xpath = "//android.widget.TextView[contains(@resource-id, 'textview_title')]")
	MobileElement footwareLogoText;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Fashion']")
	@AndroidFindBy(xpath = "//android.widget.TextView[@text= 'Fashion']")
	MobileElement fashionText;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Footware']")
	@AndroidFindBy(xpath = "//android.widget.LinearLayout[@content-desc='Footware']/android.widget.TextView")
	MobileElement footwareText;

	@AndroidFindBy(xpath = "//android.widget.HorizontalScrollView[contains(@resource-id, 'sliding_tabs')]/android.widget.LinearLayout/android.widget.ImageView[2]")
	@iOSXCUITFindBy(xpath = "(//XCUIElementTypeCell[@name='25'])[1]/XCUIElementTypeOther/XCUIElementTypeImage[3]")
	MobileElement listIconForSandbox;

	@AndroidFindBy(id = "plobalsandboxtest.android.staging:id/viewtype_category_title_textview")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Dress']")
	MobileElement DressCollectionForSandbox;

	@AndroidFindBy(id = "plobalsandboxtest.android.staging:id/viewtype_category_title_textview")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Dress1']")
	MobileElement DressCollection1ForSandbox;

	@AndroidFindBy(xpath = "(//android.widget.ImageView[contains(@resource-id,'product_action_item_imgView')])[1]")
	@iOSXCUITFindBy(xpath = "(//XCUIElementTypeButton[@name='shareHomeProdList'])[1]")
	MobileElement shareButtonOnHomepage;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Copy']")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='plobal-sandbox-test.myshopify.com']")
	MobileElement sharePopOnHomepage;

	@AndroidFindBy(xpath = "(//android.widget.ImageView[contains(@resource-id,'product_action_item_imgView')])[3]")
	@iOSXCUITFindBy(xpath = "(//XCUIElementTypeButton[@name='ProductAddToCart'])[1]")
	MobileElement addToCartButtonOnHomepage;

	@AndroidFindBy(xpath = "(//android.widget.ImageView[contains(@resource-id,'product_action_item_imgView')])[2]")
	@iOSXCUITFindBy(xpath = "(//XCUIElementTypeButton[@name='like icon'])[1]")
	MobileElement favoriteButtonOnHomepage;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Fashion']")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Fashion']")
	MobileElement multitabOnHomepage;

	@AndroidFindBy(xpath = "//android.widget.ImageView[contains(@resource-id,'popular_offer_1')]")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeCollectionView[@index='2']")
	MobileElement dynamicShelfOnHomepage;

	@AndroidFindBy(xpath = "(//android.widget.ImageView[contains(@resource-id,'popular_offer_1')])[1]")
	@iOSXCUITFindBy(xpath = "(//XCUIElementTypeImage[@index='0'])[7]")
	MobileElement animatedPromoBannerOnHomepage;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='TabOverviewButton']")
	MobileElement safariTabOverview;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Safari']")
	MobileElement safariText;

	@iOSXCUITFindBy(xpath = "///XCUIElementTypeButton[@name='ShareButton']")
	MobileElement SafariShareButton;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeTextField[@label='Address']")
	MobileElement safariSerachBar;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeApplication[@name='Safari']/XCUIElementTypeWindow[4]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[1] | //XCUIElementTypeApplication[@name='Safari']/XCUIElementTypeWindow[3]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[1]")
	MobileElement enterTextInSafariSerachBar;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='Go']")
	MobileElement goButton_IosKeyBoard;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Open']")
	MobileElement openPop_upSafari;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Cart page']")
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Cart page']")
	MobileElement cartOnHamMenu;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeCell[@name='26']/XCUIElementTypeOther")
	@AndroidFindBy(xpath = "(//android.widget.RelativeLayout[contains(@resource-id,'layout_cart_items')])[2]")
	public MobileElement cartIcon;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Tops']")
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Tops']")
	public MobileElement topsCollection;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Nearby Share']")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='Copy']")
	MobileElement nearbyShare;

	@AndroidFindBy(xpath = "(//android.widget.ImageView[contains(@resource-id,'pdp_image_sticker')])[1]")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[contains(@name,'Rs.')]/parent::XCUIElementTypeOther/parent::XCUIElementTypeCell/XCUIElementTypeOther[1]/XCUIElementTypeImage")
	MobileElement imageStickerOnHomepage;

	@AndroidFindBy(xpath = "//android.widget.RelativeLayout[contains(@resource-id,'layout_cart')]//parent::android.widget.LinearLayout/android.widget.ImageView")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeCell[@name='24']")
	MobileElement homeIcon;

	@iOSXCUITFindBy(xpath = "(//XCUIElementTypeCell[@name='25'])[2]")
	@AndroidFindBy(xpath="//android.widget.RelativeLayout[contains(@resource-id,'layout_cart')]//parent::android.widget.LinearLayout/android.widget.ImageView[3]")
	public MobileElement collectionIcon2;

	@iOSXCUITFindBy(xpath = "(//XCUIElementTypeCell[@name='25'])[3]")
	@AndroidFindBy(xpath="//android.widget.RelativeLayout[contains(@resource-id,'layout_cart')]//parent::android.widget.LinearLayout/android.widget.ImageView[4]")
	public MobileElement collectionIcon3;

	//#Firefox browser locator
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Search or enter address']")
	public MobileElement searchIcon;

	@AndroidFindBy(xpath="//android.widget.EditText[@text='Search or enter address']")
	public MobileElement enterText;

	@AndroidFindBy(xpath="(//android.widget.Button)[3]")
	public MobileElement firefoxNotNow;

	@AndroidFindBy(xpath="//android.widget.ImageView[@content-desc='Menu']")
	public MobileElement firefoxMenu;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Open in app']")
	public MobileElement openInApp;

	@AndroidFindBy(xpath = "//android.widget.HorizontalScrollView[contains(@resource-id,'sliding_tabs')]/android.widget.LinearLayout/android.widget.ImageView")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeCell[@name='24']")
	MobileElement homeTab;

	@AndroidFindBy(xpath = "(//android.widget.ImageView[@resource-id='plobalsandboxtest.android.staging:id/popular_offer_1'])[2]")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Black dress']")
	MobileElement productForAutomaticDiscount;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Extra Rs. 449.10 at Checkout Rs. 548.90 and Rs. 499.00 or Rs. 49.90']")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Extra Rs. 449.10  at Checkout Rs. 548.90  and Rs. 499.00  or Rs. 49.90 ']")
	MobileElement additionalLabelV2OnHomepage;

	@AndroidFindBy(xpath = "(//android.widget.ImageView[contains(@resource-id,'popular_offer_1')])[10]")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeNavigationBar[contains(@name,'TopTabBarView')]//following-sibling:: XCUIElementTypeOther//XCUIElementTypeCell[10]")
	public MobileElement bannerOnHomePage;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[contains(@name,'Rs. ')]")
	public MobileElement productOriginalPriceAndCompareAtPrice;

	@AndroidFindBy(xpath = "//android.widget.TextView[contains(@resource-id,'txt_price_discounted')]")
	public MobileElement compareAtPrice;

	@AndroidFindBy(xpath = "//android.widget.TextView[contains(@resource-id,'txt_price_mrp')]")
	public MobileElement productOriginalPrice;
}
