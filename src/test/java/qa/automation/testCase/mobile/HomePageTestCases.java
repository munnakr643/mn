package qa.automation.testCase.mobile;

import io.appium.java_client.HasSettings;
import io.appium.java_client.Setting;
import org.apache.log4j.Logger;
import org.openqa.selenium.Dimension;
import org.testng.annotations.Test;
import qa.automation.base.MobileBaseTest;
import qa.automation.report.ExtentTestManager;
import qa.automation.enums.StoreNameEnum;

public class HomePageTestCases extends MobileBaseTest {
	private static final Logger logger = Logger.getLogger(HomePageTestCases.class);

	String emailID = "qa" + randomString(4) + "@gmail.com";


	@Test(priority = 1, groups = {"sm", "regression"})
	public void clickOnpdpCartIconForSm() {
		testId = "MAB-T13047";
		logger.info(testId + " : This test validates redirection on Cart page on the click of cart icon in PDP page");
		ExtentTestManager.getTest().setDescription(testId + " : This test validates redirection on Cart page on the click of cart icon in PDP page");
		clickNoButtonOnOnboardingScreen();
		clickOnOnboarding2ScreenForSM();
		verifyHomepage();
		clickOnHomepageElement();
		verifyPlp();
		selectProductFromPLP();
		verifyPdp();
		clickCartIconOnPdpPage();
	}

	@Test(priority = 1, groups = {"sm", "regression"})
	public void verifyProductPriceOnPDPForSm() {
		testId = "MAB-T13962";
		logger.info(testId + " : This test validates Product Price should be displayed in PDP page");
		ExtentTestManager.getTest().setDescription(testId + " : This test validates Product Price should be displayed in PDP page");
		clickNoButtonOnOnboardingScreen();
		clickOnOnboarding2ScreenForSM();
		verifyHomepage();
		clickOnHomepageElement();
		verifyPlp();
		selectProductFromPLP();
		String productPrice = getProductPriceFromPLP();
		verifyPdp();
		checkProductPriceOnPdp(productPrice);
	}

	@Test(priority = 1, groups = {"sm", "regression"})
	public void verifyPdpProductTitleForSm() {
		testId = "MAB-T13051";
		logger.info(testId + " : This test validates Product title on PDP verified successfully");
		ExtentTestManager.getTest().setDescription(testId + " : This test validates Product title on PDP verified successfully");
		clickNoButtonOnOnboardingScreen();
		clickOnOnboarding2ScreenForSM();
		verifyHomepage();
		clickOnHomepageElement();
		verifyPlp();
		String productTitle = selectProductFromPLP();
		verifyPdp();
		checkProductTitleOnPdp(productTitle);
	}

	@Test(priority = 1, groups = {"sm", "regression"})
	public void verifyProductTitleOnTopOfPdpForSm() {
		testId = "MAB-T13044";
		logger.info(testId + " : This test validates Product name should reflect on top of the PDP page verified successfully");
		ExtentTestManager.getTest().setDescription(testId + " : This test validates Product name should reflect on top of the PDP page verified successfully");
		clickNoButtonOnOnboardingScreen();
		clickOnOnboarding2ScreenForSM();
		verifyHomepage();
		clickOnHomepageElement();
		verifyPlp();
		String productTitle = selectProductFromPLP();
		verifyPdp();
		checkProductTitleOnPdp(productTitle);
	}

	@Test(priority = 1, groups = {"sm", "regression"})
	public void clickOnPDPSearchIconForSm() {
		testId = "MAB-T13910";
		logger.info(testId + " : This test validates redirection on Search pages from PDP page on the click of Search icon");
		ExtentTestManager.getTest().setDescription(testId + " : This test validates redirection on Search pages from PDP page on the click of Search icon");
		clickNoButtonOnOnboardingScreen();
		clickOnOnboarding2ScreenForSM();
		verifyHomepage();
		clickOnHomepageElement();
		verifyPlp();
		selectProductFromPLP();
		verifyPdp();
		clickOnPDPSearchIconForSM();
	}

	@Test(priority = 1, groups = {"sm", "regression"})
	public void verifyStarRatingOnPdpForSm() {
		testId = "MAB-T13910";
		logger.info(testId + " : This test validates average Star rating should be shown below product price in PDP page");
		ExtentTestManager.getTest().setDescription(testId + " : This test validates average Star rating should be shown below product price in PDP page");
		clickNoButtonOnOnboardingScreen();
		clickOnOnboarding2ScreenForSM();
		verifyHomepage();
		clickOnHomepageElement();
		verifyPlp();
		selectProductFromPLP();
		String productAverageRating = getAverageRatingFromPlp();
		verifyPdp();
		checkStarRatingOnPdpForSM(productAverageRating);
	}

	@Test(priority = 1, groups = {"sm", "regression"})
	public void verifyVariantOptionNameOnPdpForSm() {
		testId = "MAB-T13084";
		String productOptionName = "Color - BLACK LEATHER";
		logger.info(testId + " : This test validates Variant Option name before pricing below product title in PDP page");
		ExtentTestManager.getTest().setDescription(testId + " : This test validates Variant Option name before pricing below product title in PDP page");
		clickNoButtonOnOnboardingScreen();
		clickOnOnboarding2ScreenForSM();
		verifyHomepage();
		clickOnHomepageElement();
		verifyPlp();
		selectProductFromPLP();
		verifyPdp();
		getVariantOptionNameOnPdp(productOptionName);
	}

	@Test(priority = 1, groups = {"sm", "regression"})
	public void verifyVariantSwitchForSm() {
		testId = "MAB-T13060";
		String productOptionName = "Color - BLACK LEATHER";
		String productOptionName1 = "Color - GLITTER MULTI";
		logger.info(testId + " : This test validates user able to switch the variants in PDP Page");
		ExtentTestManager.getTest().setDescription(testId + " : This test validates user able to switch the variants in PDP Page");
		clickNoButtonOnOnboardingScreen();
		clickOnOnboarding2ScreenForSM();
		verifyHomepage();
		clickOnHomepageElement();
		verifyPlp();
		selectProductFromPLP();
		verifyPdp();
		getVariantOptionNameOnPdp(productOptionName);
		swipe(250, 890, 250, 370);
		verifyVariantSwitchForSM();
		waitFor(5);
		getVariantOptionNameOnPdpAfterProductSwitch(productOptionName1);
	}

	@Test(priority = 1, groups = {"sm", "regression"})
	public void verifyAverageRatingCountOnPlpForSm() {
		testId = "MAB-T13028";
		String productRatingCount = "(354)";
		logger.info(testId + " : This test validates number of average rating count shown in PLP page");
		ExtentTestManager.getTest().setDescription(testId + " : This test validates number of average rating count shown in PLP page");
		clickNoButtonOnOnboardingScreen();
		clickOnOnboarding2ScreenForSM();
		verifyHomepage();
		clickOnHomepageElement();
		verifyPlp();
		checkAverageRatingCountOnPlp(productRatingCount);
	}

	@Test(priority = 1, groups = {"sm", "regression"})
	public void verifyCartAndSearchIconForSm() {
		testId = "MAB-T13028";
		logger.info(testId + " : This test validates Cart & Search button should be available on top Right in PDP page");
		ExtentTestManager.getTest().setDescription(testId + " : This test validates Cart & Search button should be available on top Right in PDP page");
		clickNoButtonOnOnboardingScreen();
		clickOnOnboarding2ScreenForSM();
		verifyHomepage();
		clickOnHomepageElement();
		verifyPlp();
		selectProductFromPLP();
		verifyPdp();
		checkCartButtonOnPdp();
		checkSearchButtonOnPdp();
	}

	@Test(priority = 1, groups = {"sm", "regression"})
	public void verifyAppLogoOnHomePage() {
		logger.info("This test verifies that App Logo On Home Page.");
		ExtentTestManager.getTest().setDescription("This test verifies that App Logo On Home Page.");
		((HasSettings) mobileDriver).setSetting(Setting.WAIT_FOR_IDLE_TIMEOUT, 500);
		clickOnPermission();
		onBoardingNextButton();
		checkAppLogoOnHomePage();
		verifyHomepage();
	}

	@Test(priority = 1, groups = {"sm", "regression"})
	public void verifyCartPageAfterTappingOnCartButtonFromHomePage() {
		String cart = "Cart";
		logger.info("This test verifies that cart page after tapping on cart button from home page.");
		ExtentTestManager.getTest().setDescription("This test verifies that cart page after tapping on cart button from home page.");
		((HasSettings) mobileDriver).setSetting(Setting.WAIT_FOR_IDLE_TIMEOUT, 500);
		clickOnPermission();
		onBoardingNextButton();
		clickOnCartButtonFromHomePage();
		checkCartPage(cart);
	}

	@Test(priority = 1, groups = {"sm", "regression"})
	public void verifyChangePasswordForSteveMadden() {
		String email = "shashi@gmail.com";
		String newPassword = "test123";
		String enterPassword = "123456";
		logger.info("This test verifies that the Change Password functionality for SteveMadden App.");
		ExtentTestManager.getTest().setDescription("This test verifies that the Change Password functionality for SteveMadden App.");
		((HasSettings) mobileDriver).setSetting(Setting.WAIT_FOR_IDLE_TIMEOUT, 500);
		clickOnPermission();
		onBoardingNextButton();
		clickOnHamburgerButtonFromHomePage();
		clickOnLoginButtonFromHamburgerMenu();
		enterEmailID(email);
		enterPassword(enterPassword);
		clickLogin();
		checkLoginWithValidCreds();
		clickOnHamburgerButtonFromHomePage();
		clickOnProfileButtonFromHamburgerMenu();
		ClickOnChangePasswordButton();
		enterNewPasswrod(newPassword);
		enterConfirmPassword(newPassword);
		ChangePasswordOnHomePage();
	}

	@Test(priority = 1, groups = {"sm", "regression"})
	public void verifyLogoutButtonFromProfilePage() {
		String email = "shashi@gmail.com";
		String enterPassword = "test123";
		logger.info("This test verifies that the log-out button from the profile page.");
		ExtentTestManager.getTest().setDescription("This test verifies that the log-out button from the profile page.");
		((HasSettings) mobileDriver).setSetting(Setting.WAIT_FOR_IDLE_TIMEOUT, 500);
		clickOnPermission();
		onBoardingNextButton();
		clickOnHamburgerButtonFromHomePage();
		clickOnLoginButtonFromHamburgerMenu();
		enterEmailID(email);
		enterPassword(enterPassword);
		clickLogin();
		checkLoginWithValidCreds();
		clickOnHamburgerButtonFromHomePage();
		clickOnProfileButtonFromHamburgerMenu();
		checkLogoutButtonOnProfilePage();
	}

	@Test(priority = 1, groups = {"sm", "regression"})
	public void verifyFieldsOnEditProfilePage() {
		String email = "shashi@gmail.com";
		String enterPassword = "test123";
		String fname = "shashi";
		String lname = "moray";
		logger.info("This test verifies that all fields on the Edit Profile page.");
		ExtentTestManager.getTest().setDescription("This test verifies that all fields on the Edit Profile page.");
		((HasSettings) mobileDriver).setSetting(Setting.WAIT_FOR_IDLE_TIMEOUT, 500);
		clickOnPermission();
		onBoardingNextButton();
		clickOnHamburgerButtonFromHomePage();
		clickOnLoginButtonFromHamburgerMenu();
		enterEmailID(email);
		enterPassword(enterPassword);
		clickLogin();
		checkLoginWithValidCreds();
		clickOnHamburgerButtonFromHomePage();
		clickOnProfileButtonFromHamburgerMenu();
		clickOnEditProfileButtonOnProfilePage();
		checkEditProfilePage(fname, lname, email);
	}

	@Test(priority = 1, groups = {"sm", "regression"})
	public void verifyDeleteAccount() {
		String email = "shashi@gmail.com";
		String enterPassword = "test123";
		logger.info("This test verifies that the account functionality on the profile page has been removed.");
		ExtentTestManager.getTest().setDescription("TThis test verifies that the account functionality on the profile page has been removed.");
		((HasSettings) mobileDriver).setSetting(Setting.WAIT_FOR_IDLE_TIMEOUT, 500);
		clickOnPermission();
		onBoardingNextButton();
		clickOnHamburgerButtonFromHomePage();
		clickOnLoginButtonFromHamburgerMenu();
		enterEmailID(email);
		enterPassword(enterPassword);
		clickLogin();
		checkLoginWithValidCreds();
		clickOnHamburgerButtonFromHomePage();
		clickOnProfileButtonFromHamburgerMenu();
		clickOnDeleteAccountOnProfilePage();
		clickOnCofirmDeleteButtonOnProfilePage();
	}

	@Test(priority = 1, groups = {"neovo", "regression"})
	public void verifyHomePageLoadingTime() {
		testId = "MAB-T963";
		logger.info(testId + " This test verifies if the Home page loads in 1-3 seconds");
		ExtentTestManager.getTest().setDescription(testId + " This test verifies if the Home page loads in 1-3 seconds");
		clickNoButtonOnOnboardingScreen();
		waitFor(3);
		verifyHomePageElementsLoadingTime();
	}

	@Test(priority = 1, groups = {"sandbox", "iOSFailure", "regression"})
	public void verifyRedirectionOfShareButtonFromHomePage() {
		testId = "MAB-T972";
		logger.info(testId + " This test verifies behaviour on the click of share actions from home page");
		ExtentTestManager.getTest().setDescription(testId + " This test verifies behaviour on the click of share actions from home page");
		selectStore(StoreNameEnum.SANDBOX);
		verifyHomepage();
		if (isPlatformNameAndroid) {
			scrollToText("Rs. 80.00 + Rs. 8.00");
		} else {
			scrollForIos();
		}
		clickOnShareButtonOnHomePage();
		waitFor(1);
		cooClick(950, 1450);
		waitFor(2);
		verifySharePopupOnHomePage();
	}

	@Test(priority = 1, groups = {"sandbox", "regression"})
	public void verifyRedirectionOfAddCartButtonFromHomePage() {
		testId = "MAB-T15163";
		logger.info(testId + " This test verifies Add to cart Home page element showing properly or not");
		ExtentTestManager.getTest().setDescription(testId + " This test verifies Add to cart Home page element showing properly or not");
		selectStore(StoreNameEnum.SANDBOX);
		verifyHomepage();
		if (isPlatformNameAndroid) {
			scrollToText("Rs. 80.00 + Rs. 8.00");
		} else {
			scrollForIos();
		}
		verifyAddToCartButtonVisibleOnHomepage();
	}

	@Test(priority = 1, groups = {"sandbox", "regression", "iOSAppReset"})
	public void verifyFavoriteButtonFunctionalityFromHomepage() {
		String productName = "High Waist Jeans";
		String productPrice = "Rs. 80.00 + Rs. 8.00";
		testId = "MAB-T15161";
		logger.info(testId + " This test verifies Favorite action button's functionality on homepage");
		ExtentTestManager.getTest().setDescription(testId + " This test verifies Favorite action button's functionality on homepage");
		selectStore(StoreNameEnum.SANDBOX);
		verifyHomepage();
		if (isPlatformNameAndroid) {
			scrollToText(productPrice);
		} else {
			scrollForIos();
		}
		clickOnFavoriteButtonOnHomepage();
		clickOnHamburgerButtonFromHomePage();
		clickOnMyProfileFromHamburger();
		clickFavoriteFeatureOnProfilePage();
		waitFor(5);
		verifyProductOnFavoritePage(productName);
	}

	@Test(priority = 1, groups = {"neovo", "androidFailure", "regression"})
	public void verifyDynamicShelfBannerFromMultiTabHomepage() {
		testId = "MAB-T15253";
		logger.info(testId + " This test verifies Dynamic Shelf Banner Home Element UI and redirection from multitab Home page");
		ExtentTestManager.getTest().setDescription(testId + " This test verifies Dynamic Shelf Banner Home Element UI and redirection from multitab Home page");
		clickNoButtonOnOnboardingScreen();
		verifyHomepage();
		waitFor(1.5);
		clickOnMultitabFashion();
		waitFor(4);
		clickOnDynamicShelfOnHomepage();
		waitFor(3);
		verifyPdp();
	}

	@Test(priority = 1, groups = {"neovo", "regression"})
	public void verifySearchIconVisibleOnHomePage() {
		testId = "MAB-T964";
		logger.info(testId + " : This test verifies search icon visible on Home Page");
		ExtentTestManager.getTest().setDescription(testId + " : This test verifies search icon visible on Home Page");
		clickNoButtonOnOnboardingScreen();
		verifyHomepage();
		verifySearchOptionPresentOnHomePage();
	}

	@Test(priority = 0, groups = {"neovo", "androidFailure", "regression"})
	public void verifyRedirectionOfSquarePromoLinkToCollectionPage() {
		testId = "MAB-T22360";
		logger.info(testId + " : This test validate redirection on the collection page after tapping on the square promo banner on Home page");
		ExtentTestManager.getTest().setDescription(testId + " : This test validate redirection on the collection page after tapping on the square promo banner on Home page");
		clickNoButtonOnOnboardingScreen();
		verifyHomepage();
		if (isPlatformNameAndroid) {
			scrollToText("Tops");
		} else {
			scrollForIos();
			waitFor(1);
		}
		clickOnTopsCollection();
		waitFor(1);
		verifyPlp();
		verifyCollectionPLP("Top");
	}

	@Test(priority = 1, groups = {"sandbox", "regression", "iOSAppReset"})
	public void verifyPerticularProductOnCartAfterAddingFromHomepage() {
		String productName = "High Waist Jeans";
		String count = "2";
		testId = "MAB-T15160";
		logger.info(testId + " This test verifies Add to Cart action button's functionality on homepage for (layout_2)");
		ExtentTestManager.getTest().setDescription(testId + " This test verifies Add to Cart action button's functionality on homepage for (layout_2)");
		selectStore(StoreNameEnum.SANDBOX);
		verifyHomepage();
		if (isPlatformNameAndroid) {
			scrollToText("Rs. 80.00 + Rs. 8.00");
		} else {
			scrollForIos();
		}
		verifyAddToCartButtonVisibleOnHomepage();
		clickOnAddToCartButtonOnHomepage();
		verifyAddToCartPopupOnPlp();
		clickOnAddToCartButtonFromAddToCartPopupOnPlp();
		clickOnHamburgerButtonFromHomePage();
		clickOnCartHamburgerMenu();
		verifyCartPage();
		verifyProductInCartpage(productName);
		increaseItemOnCartPage();
		waitFor(1.5);
		checkItemCount(count);
	}

	@Test(priority = 1, groups = {"sandbox", "regression", "iOSAppReset"})
	public void verifyVariantPopupGetsLoadOnHomepage() {
		String productName = "High Waist Jeans";
		testId = "MAB-T13860";
		logger.info(testId + " This test verifies Add to Cart action button's functionality on homepage");
		ExtentTestManager.getTest().setDescription(testId + " This test verifies Add to Cart action button's functionality on homepage");
		selectStore(StoreNameEnum.SANDBOX);
		verifyHomepage();
		if (isPlatformNameAndroid) {
			scrollToText("Rs. 80.00 + Rs. 8.00");
		} else {
			scrollForIos();
		}
		verifyAddToCartButtonVisibleOnHomepage();
		clickOnAddToCartButtonOnHomepage();
		verifyAddToCartPopupOnPlp();
		clickOnAddToCartButtonFromAddToCartPopupOnPlp();
		clickOnHamburgerButtonFromHomePage();
		clickOnCartHamburgerMenu();
		verifyCartPage();
		verifyProductInCartpage(productName);
	}

	@Test(priority = 1, groups = {"neovo", "regression", "iOSAppReset"})
	public void verifyAnimatedPromoBannerRedirectionFromMultiTabHomepage() {
		testId = "MAB-T15255";
		logger.info(testId + " This test verifies Animated Promo Banner Home Element[All Link-Type] UI and redirection from multitab Home page.");
		ExtentTestManager.getTest().setDescription(testId + " This test verifies Animated Promo Banner Home Element[All Link-Type] UI and redirection from multitab Home page.");
		clickNoButtonOnOnboardingScreen();
		verifyHomepage();
		clickOnAnimatedPromoBannerOnHomepage();
		verifyPdp();
	}

	@Test(priority = 0, groups = {"neovo", "regression"})
	public void verifySoldOutProductShouldNotVisibleOnHomePage() {
		testId = "MAB-T973";
		logger.info(testId + " : This test validate out of stock products get hide on Home page");
		ExtentTestManager.getTest().setDescription(testId + " : This test validate out of stock products get hide on Home page");
		clickNoButtonOnOnboardingScreen();
		verifyHomepage();
		waitFor(2);
		if (isPlatformNameAndroid) {
			scrollToText("New Arrival");
			waitFor(3);
			swipe(550, 1850, 550, 500);
		} else {
			scrollForIos();
			waitFor(1);
		}
		isSoldOutProductNotDisplayed(0);
	}

	@Test(priority = 0, groups = {"neovo", "regression"})
	public void verifyImageStickersOnHomepage() {
		testId = "MAB-T15183";
		logger.info(testId + " This test validates Product Stickers for all product are showing in feature product sections on the HomePage");
		ExtentTestManager.getTest().setDescription(testId + " This test validates Product Stickers for all product are showing in feature product sections on the HomePage");
		clickNoButtonOnOnboardingScreen();
		verifyHomepage();
		if (isPlatformNameAndroid) {
			scrollToText("Allegra K Women's Peep Toe Slip On Stacked Heel Slingback Mules Brown (size 7)");
		} else {
			scrollForIos();
			waitFor(1.5);
		}
		verifyImageStickerOnHomepage();
	}

	@Test(groups = {"neovo", "androidFailure", "regression"})
	public void verifyMultiTabScrollable() {
		Dimension dimension = mobileDriver.manage().window().getSize();
		int scrollStart = (int) (dimension.getHeight() * 0.5); // start from 50% of the height
		int scrollEnd = (int) (dimension.getHeight() * 0.05);   // end at 05% of the height
		testId = "MAB-T15246";
		logger.info(testId + " : This test verifies if the multi tabs are scrollable");
		ExtentTestManager.getTest().setDescription(testId + " : This test verifies if the multi tabs are scrollable");
		clickNoButtonOnOnboardingScreen();
		verifyHomepage();
		if (isPlatformNameAndroid) {
			swipe(500, 1970, 500, 370);
		} else {
			swipeForIos("down");
		}
		verifyTextOnPage("New Arrival");
		clickOnMultitabFashion();
		waitFor(1);
		if (isPlatformNameAndroid) {
			scrollToText("Featured Products");
		} else {
			checkScrollDownPageForBothOS(scrollStart, scrollEnd);
		}
		waitFor(1);
		verifyTextOnPage("Featured Products");
	}

	@Test(priority = 0, groups = {"sandbox", "regression", "iOSAppReset"})
	public void verifyVariantPopupShowsOnQuickAddtoCart() {
		testId = "MAB-T33174";
		logger.info(testId + " : This test validate Variant option shows up when user clicks on Quick add to cart on Homepage");
		ExtentTestManager.getTest().setDescription(testId + " : This test validate Variant option shows up when user clicks on Quick add to cart on Homepage");
		selectStore(StoreNameEnum.SANDBOX);
		verifyHomepage();
		if (isPlatformNameAndroid) {
			scrollToText("Rs. 80.00 + Rs. 8.00");
		} else {
			scrollForIos();
		}
		verifyAddToCartButtonVisibleOnHomepage();
		clickOnAddToCartButtonOnHomepage();
		verifyVariantPopupOnHomePage();
	}
		@Test(priority = 0, groups = {"neovo", "regression", "iOSAppReset"})
		public void verifyAdditionallabelv2calculatioOnHomepage(){
			testId = "MAB-T35109";
			String value = "449.10";
			logger.info(testId + " : This test validate additional label v2 price calculation  on the Homepage");
			ExtentTestManager.getTest().setDescription(testId + " : This test validate additional label v2 price calculation  on the Homepage");
			clickNoButtonOnOnboardingScreen();
			verifyHomepage();
			if (isPlatformNameAndroid) {
				scrollToText("Rs. 499.00");
			} else {
				scrollForIos();
				waitFor(8);
			}
			checkAdditionLabelCalculationOnHomepage(value);
		}

	@Test(priority = 0, groups = {"sandbox", "regression"})
	public void checkProductPriceAndCompareAtPriceOnHomePage() {
		testId = "MAB-15171";
		String productOriginalPriceAndCompareAtPrice="Rs. 80.00  + Rs. 8.00 Rs. 5,999.00";
		String compareAtPrice="Rs. 80.00 + Rs. 8.00";
		String originalPrice="5,999.00";
		logger.info(testId + " : This test validate Product Price and Compare at Price on Home Page");
		ExtentTestManager.getTest().setDescription(testId + " : This test validate Product Price and Compare at Price on Home Page");
		selectStore(StoreNameEnum.SANDBOX);
		verifyHomepage();
		waitFor(2);
		if (isPlatformNameAndroid) {
			scrollToText("Rs. 80.00 + Rs. 8.00");
			waitFor(2);
			verifyProductPriceOnHomePage("originalprice",originalPrice);
			waitFor(2);
			verifyProductPriceOnHomePage("compare",compareAtPrice);
		} else {
			scrollForIos();
			waitFor(2);
			verifyProductPriceOnHomePage("randomText",productOriginalPriceAndCompareAtPrice);
		}
	}

	@Test(priority = 0, groups = {"sandbox", "regression", "iOSAppReset"})
	public void verifyAdditionallabelShouldNotDisplayOnHomePage(){
		testId = "MAB-T13901";
		logger.info(testId + " : This test validate additional label should not displayed on excluded screen (Homepage)");
		ExtentTestManager.getTest().setDescription(testId + " : This test validate additional label should not displayed on excluded screen (Homepage)");
		selectStore(StoreNameEnum.SANDBOX);
		verifyHomepage();
		if (isPlatformNameAndroid) {
			scrollToText("High Waist Jeans");
		} else {
			scrollForIos();
			waitFor(8);
		}
		verifyAdditionalLabelShouldNotDisplayOnHomePage(0);
	}
}

