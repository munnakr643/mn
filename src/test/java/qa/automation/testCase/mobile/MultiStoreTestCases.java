package qa.automation.testCase.mobile;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;
import qa.automation.base.MobileBaseTest;
import qa.automation.enums.StoreNameEnum;
import qa.automation.report.ExtentTestManager;

public class MultiStoreTestCases extends MobileBaseTest {

    private static final Logger logger= Logger.getLogger(MultiStoreTestCases.class);

    @Test(priority = 0, groups = {"sandbox","regression","iOSAppReset"})
    public void verifyAppOnlyDiscountForMultistore() {
        String discount = "plobalneovo";
        testId = "MAB-T15207";
        logger.info(testId + " : This test validates that User applies in-app discount code on native store1 and switches store2");
        ExtentTestManager.getTest().setDescription(testId + " : This test validates User applies in-app discount code on native store1 and switches store2");
        selectStore(StoreNameEnum.NEOVO);
        clickNoButtonOnOnboardingScreen();
        verifyHomepage();
        clickOnListIcon();
        clickOnFashionCollection();
        clickOnDressCollection();
        waitFor(2);
        selectProductFromPLP();
        addProductIntoCartFromPDP();
        if (isPlatformNameAndroid) {
            clickOnConfirm();
        }
        clickCartIconOnPdpPage();
        waitFor(1);
        verifyCartPage();
        waitFor(1);
        clickOnDiscountOnCartPage();
        enterDiscountOnPopupOnCartPage(discount);
        clickOnApplyCouponButtonOnCartPage();
        checkDiscountAplliedOnCartPage();
        waitFor(1);
        clickOnBackButtonFromCartPage();
        int i=0;
        while(i<3){
            waitFor(1.5);
            clickBackArrowOnNativeApp();
            i++;
        }
        clickMoreTabOnHomePage();
        waitFor(1.5);
        clickOnCurrentStore();
        selectStore(StoreNameEnum.SANDBOX);
        waitFor(1.3);
        verifyHomepage();
        clickOnProductFromGrid();
        clickAddToCartOnPDP();
        waitFor(2);
        clickOnGoToCart();
        waitFor(2);
        verifyCartPage();
    }


    @Test(priority = 0, groups = {"sandbox", "Regression","iOSAppReset"})
    public void verifyHomeScreenAfterSwitchStore1ToStore2() {
        testId = "MAB-T15586";
        logger.info( testId + ": This test validate home screen changes according to the setup when user switches store1 to store2");
        ExtentTestManager.getTest().setDescription(testId + ": This test validate home screen changes according to the setup when user switches store1 to store2");
        selectStore(StoreNameEnum.SANDBOX);
        verifyHomepage();
        clickOnHamburgerButtonFromHomePage();
        clickOnMoreBtn();
        verifyMorePageForSandbox();
        clickOnCurrentStore();
        selectStore(StoreNameEnum.NEOVO);
        clickNoButtonOnOnboardingScreen();
        verifyHomepageForNeovoStore();
    }


    @Test(priority = 0, groups = {"sandbox", "Regression","iOSAppReset"})
    public void verifyWishlistUpdatesAfterSwitchStore1ToStore2() {
        testId = "MAB-T15746";
        logger.info( testId + ": This test validate wishlist updates changes when user switches store1 to store2");
        ExtentTestManager.getTest().setDescription(testId + ": This test validate wishlist updates changes when user switches store1 to store2");
        String withoutProduct="You currently do not have any Items added to Favorites";
        selectStore(StoreNameEnum.SANDBOX);
        verifyHomepage();
        clickOnProductFromGrid();
        waitFor(1.3);
        verifyPdp();
        clickAddToFavoriteIconOnPDPPage();
        clickBackButtonIconOnTopLeft();
        waitFor(1);
        clickOnMyAccountForSandbox();
        clickFavoriteFeatureOnProfilePage();
        isFavoritePageLoaded();
        verifyProductTitleOnFavoritesPage("High Waist Jeans");
        clickBackButtonIconOnFavoritesPage();
        clickOnHamburgerButtonFromHomePage();
        clickOnMoreBtn();
        verifyMorePageForSandbox();
        clickOnCurrentStore();
        selectStore(StoreNameEnum.NEOVO);
        clickNoButtonOnOnboardingScreen();
        verifyHomepageForNeovoStore();
        clickOnMyAccount();
        clickFavoriteFeatureOnProfilePage();
        isFavoritePageLoaded();
        verifyProductTitleOnFavoritesPage(withoutProduct);
    }

    @Test(priority = 0, groups = {"sandbox", "Regression","iOSAppReset"})
    public void verifyMorePageAfterSwitchStore1ToStore2() {
        testId = "MAB-T15748";
        logger.info( testId + ": This test validate More page should be appear according to setup of store2");
        ExtentTestManager.getTest().setDescription(testId + ": This test validate More page should be appear according to setup of store2");
        String withoutProduct="You currently do not have any Items added to Favorites";
        selectStore(StoreNameEnum.SANDBOX);
        verifyHomepage();
        clickOnHamburgerButtonFromHomePage();
        clickOnMoreBtn();
        verifyMorePageForSandbox();
        clickOnCurrentStore();
        selectStore(StoreNameEnum.NEOVO);
        clickNoButtonOnOnboardingScreen();
        verifyHomepageForNeovoStore();
        clickOnHamburgerButtonFromHomePage();
        clickOnMoreBtn();
        verifyMorePageForNeovo();
    }

    @Test(priority = 0, groups = {"sandbox", "regression","androidOnly"})
    public void verifyWebviewCheckoutOnMultistore() {
        testId = "MAB-T15188";
        String email = "adityanisal@plobalapps.com";
        String password = "qwerty";
        logger.info(testId + " : This test validates that Multistore should work with webview checkout");
        ExtentTestManager.getTest().setDescription(testId + " : This test validates Multistore should work with webview checkout");
        selectStore(StoreNameEnum.SANDBOX);
        verifyHomepage();
        clickOnHamburgerButtonFromHomePage();
        clickOnLoginButtonFromHamburgerMenu();
        verifyLoginPage();
        enterEmailID(email);
        enterPassword(password);
        clickLogin();
        clickOnProductFromGrid();
        verifyPdp();
        clickAddToCartOnPDP();
        clickOnGoToCart();
        waitFor(2);
        verifyCartPage();
        clickOnPlaceOrder();
        waitFor(3);
        verifyCTAPopup();
        clickOnOtherButtonCTA();
        waitFor(10);
        clickOnCheckoutBackButton();
        waitFor(1);
        clickOnBackButtonFromCartPage();
        waitFor(1);
        clickOnBackButtonFromCartPage();
        waitFor(1.5);
        if(!isPlatformNameAndroid){
        clickBackArrowOnNativeApp();}
        waitFor(.5);
        clickOnHamburgerButtonFromHomePage();
        clickOnMoreBtn();
        waitFor(1);
        clickOnCurrentStore();
        selectStore(StoreNameEnum.NEOVO);
        clickNoButtonOnOnboardingScreen();
        verifyHomepage();
        clickOnListIcon();
        clickOnFashionCollection();
        clickOnDressCollection();
        waitFor(3);
        selectProductFromPLP();
        clickOnBuyNowFromPDP();
        if(isPlatformNameAndroid) {
            clickOnConfirm();
        }
    }

    @Test(priority = 0, groups = {"sandbox", "regression","androidOnly"})
    public void verifyCatagoryPageForMultistore() {
        testId = "MAB-T15747";
        logger.info(testId + " : This test validates that whether the category page changes when user switches store1 to store2");
        ExtentTestManager.getTest().setDescription(testId + " : This test validates that whether the category page changes when user switches store1 to store2");
        selectStore(StoreNameEnum.NEOVO);
        waitFor(2);
        clickNoButtonOnOnboardingScreen();
        verifyHomepageForNeovoStore();
        clickOnListIcon();
        waitFor(3);
        clickOnFashionCollection();
        clickOnDressCollection();
        int i=0;
        while(i<2){
            waitFor(1.4);
            clickBackArrowOnNativeApp();
            i++;
        }
        clickMoreTabOnHomePage();
        waitFor(1.2);
        clickOnCurrentStore();
        selectStore(StoreNameEnum.SANDBOX);
        verifyHomepage();
        ClickOnListIconForSandbox();
        clickOnDressCollectionForSandbox();
    }

    @Test(priority = 0, groups = {"sandbox", "regression","androidOnly"})
    public void verifyDiscountForMultistore() {
        String discount = "automate1";
        testId = "MAB-T15203";
        logger.info(testId + " : This test validates that user applies discount code on native store1 and switches store2");
        ExtentTestManager.getTest().setDescription(testId + " : This test validates user applies discount code on native store1 and switches store2");
        selectStore(StoreNameEnum.NEOVO);
        clickNoButtonOnOnboardingScreen();
        verifyHomepage();
        clickOnListIcon();
        clickOnFashionCollection();
        clickOnDressCollection();
        waitFor(3);
        selectProductFromPLP();
        addProductIntoCartFromPDP();
        if (isPlatformNameAndroid) {
            clickOnConfirm();
        }
        clickCartIconOnPdpPage();
        waitFor(1);
        verifyCartPage();
        waitFor(1);
        clickOnDiscountOnCartPage();
        enterDiscountOnPopupOnCartPage(discount);
        clickOnApplyCouponButtonOnCartPage();
        checkDiscountAplliedOnCartPage();
        clickOnBackButtonFromCartPage();
        int i=0;
        while(i<3){
            waitFor(1.4);
            clickBackArrowOnNativeApp();
            i++;
        }
        clickMoreTabOnHomePage();
        clickOnCurrentStore();
        selectStore(StoreNameEnum.SANDBOX);
        verifyHomepage();
        clickOnProductFromGrid();
        clickAddToCartOnPDP();
        waitFor(2);
        clickOnGoToCart();
        waitFor(2);
        verifyCartPage();
    }

    @Test(priority = 0, groups = {"sandbox", "regression","iOSAppReset"})
    public void verifyLoginSessionForMultistore() {
        String email = "adityanisal@plobalapps.com";
        String password = "qwerty";
        testId = "MAB-T15195";
        logger.info(testId + " : This test validates that logins on store1 and switchs to store2 and viseversa");
        ExtentTestManager.getTest().setDescription(testId + " : This test validates that logins on store1 and switchs to store2 and viseversa");
        selectStore(StoreNameEnum.SANDBOX);
        verifyHomepage();
        clickOnHamburgerButtonFromHomePage();
        clickOnLoginButtonFromHamburgerMenu();
        enterEmailID(email);
        enterPassword(password);
        clickLogin();
        clickMoreTabOnHomePage();
        if(isPlatformNameAndroid){
            scrollToText("Language");
        }
        waitFor(3);
        clickOnCurrentStore();
        selectStore(StoreNameEnum.NEOVO);
        clickNoButtonOnOnboardingScreen();
        verifyHomepage();
        clickOnHamburgerButtonFromHomePage();
        clickOnLoginButtonFromHamburgerMenu();
    }

    @Test(priority = 0, groups = {"sandbox","regression", "iOSFailure", "androidFailure", "iOSAppReset"})
    public void verifyCheckoutsOnMultistore() {
        testId = "MAB-T15190";
        String email = "adityanisal@plobalapps.com";
        String password = "qwerty";
        logger.info(testId + " : This test validates that user is able to complete checkout on both store without inter-relating between the stores");
        ExtentTestManager.getTest().setDescription(testId + " : This test validates that user is able to complete checkout on both store without inter-relating between the stores");
        selectStore(StoreNameEnum.SANDBOX);
        verifyHomepage();
        clickOnHamburgerButtonFromHomePage();
        clickOnLoginButtonFromHamburgerMenu();
        enterEmailID(email);
        enterPassword(password);
        clickLogin();
        clickOnProductFromGrid();
        clickAddToCartOnPDP();
        waitFor(2);
        clickOnGoToCart();
        clickOnPlaceOrder();
        verifyCTAPopup();
        clickOnOtherButtonCTA();
        waitFor(10);
        clickOnContinueButtonOnCheckoutPage();
        if(isPlatformNameAndroid) {
            waitFor(10);
            scrollToText("Cash on Delivery (COD)");
        }
        else {
            waitFor(9);
            scrollForIos();
        }
        clickOnCodButtonOnWebview();
        clickOnCompleteOrderButtonOnWebview();
        waitFor(10);
        verifyNewThankYouPage();
        if(isPlatformNameAndroid){
            scrollToText("Continue Shopping");
        }
        else{
            waitFor(2);
            scrollForIos();
        }
        waitFor(3);
        clickOnContinueShoppingButtonOnThankYouPage();
        waitFor(3);
        verifyHomepage();
        clickOnHamburgerButtonFromHomePage();
        clickOnMoreBtn();
        verifyMorePageForSandbox();
        clickOnCurrentStore();
        selectStore(StoreNameEnum.NEOVO);
        clickNoButtonOnOnboardingScreen();
        clickOnListIcon();
        clickOnFashionCollection();
        clickOnDressCollection();
        waitFor(3);
        verifyPlp();
        selectProductFromPLP();
        waitFor(1.2);
        verifyPdp();
        clickAddToCartOnPDP();
        if (isPlatformNameAndroid) {
            clickOnConfirm();
        }
        clickCartIconOnPdpPage();
        waitFor(1.5);
        clickOnPlaceOrder();
        waitFor(2);
        clickLoginButtonOnWebviewCheckout();
        isLoginPageDisplayed();
        enterEmailID("plobaltest121@yopmail.com");
        enterPassword("test1234");
        clickLogin();
        waitFor(2.5);
        if (isPlatformNameAndroid) {
            scrollToText("Continue to shipping");
        } else {
            waitFor(2.5);
            swipeForIos("down");
            scrollForIos();
        }
        clickOnContinueButton();
        waitFor(1);
        if (isPlatformNameAndroid) {
            scrollToText("Continue to payment");
        }
        clickOnContinueButton();
        waitFor(1);
        if (isPlatformNameAndroid) {
            scrollToText("Security code");
        } else {
            waitFor(2.5);
            scrollForIos();
        }
        clickOnCodButtonOnWebview();
        clickOnCompleteOrderButtonOnWebview();
        waitFor(2);
        isThankYouPageDisplayed();
    }

    @Test(priority = 0, groups = {"sandbox", "Regression","iOSAppReset"})
    public void verifyLoginSessionClearedAfterSwitchStore1ToStore2() {
        String email = "priya.n@plobalapps.com";
        String password = "qwerty";
        String username = "Nanthni Priya";
        testId = "MAB-T24372";
        logger.info( testId + ": This test validate login details gets cleared when user switches store1 to store2");
        ExtentTestManager.getTest().setDescription(testId + ": This test validate login details gets cleared when user switches store1 to store2");
        String withoutProduct="You currently do not have any Items added to Favorites";
        selectStore(StoreNameEnum.SANDBOX);
        verifyHomepage();
        clickProfileTabOnHomepageForSandbox();
        verifyNoUserIsLoggedInOnProfilePage();
        clickOnLoginButtonOnMyAccountPage();
        verifyLoginPage();
        enterEmailID(email);
        enterPassword(password);
        clickLogin();
        verifyMyAccountPage();
        checkUserDetailsOnLoginPage(username, email);
        clickMoreTabOnHomePage();
        verifyMorePageForSandbox();
        clickOnCurrentStore();
        selectStore(StoreNameEnum.NEOVO);
        clickNoButtonOnOnboardingScreen();
        verifyHomepageForNeovoStore();
        clickProfileTabOnHomePage();
        verifyNoUserIsLoggedInOnProfilePage();
    }
}
