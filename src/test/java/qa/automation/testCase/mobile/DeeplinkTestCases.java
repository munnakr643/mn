package qa.automation.testCase.mobile;

import io.appium.java_client.AppiumDriver;
import org.apache.log4j.Logger;
import org.openqa.selenium.ScreenOrientation;
import org.testng.annotations.Test;
import qa.automation.base.MobileBaseTest;
import qa.automation.enums.DeeplinkPathEnum;
import qa.automation.enums.StoreNameEnum;
import qa.automation.report.ExtentTestManager;
import qa.automation.utilities.ReadConfig;


import java.time.Duration;

public class DeeplinkTestCases extends MobileBaseTest {

    private static final Logger logger = Logger.getLogger(DeeplinkTestCases.class);

    @Test(priority = 0, groups = {"neovo", "regression"})
    public void verifyCartpageThroughDeeplink() {
        testId = "MAB-T16084";
        logger.info(testId + ": This test validates user should be redirect to cart page via Deeplink ");
        ExtentTestManager.getTest().setDescription(testId + ": : This test validates user should be redirect to cart page via Deeplink");
        if (isPlatformNameAndroid) {
            openDeepLinkUrl(DeeplinkPathEnum.CART_AND);
        } else openDeepLinkUrl(DeeplinkPathEnum.CART_IOS);
        waitFor(3);
        clickNoButtonOnOnboardingScreen();
        waitFor(1);
        verifyEmptyCartPage();
        mobileDriver.resetApp();
    }

    @Test(priority = 0, groups = {"neovo", "regression"})
    public void verifyHomepageThroughDeeplink() {
        testId = "MAB-T20378";
        logger.info(testId + ": This test validates user should be redirect to Homepage via Deeplink");
        ExtentTestManager.getTest().setDescription(testId + ": : This test validates user should be redirect to Homepage via Deeplink");
        if (isPlatformNameAndroid) {
            openDeepLinkUrl(DeeplinkPathEnum.HOME_AND);
        } else openDeepLinkUrl(DeeplinkPathEnum.HOME_IOS);
        clickNoButtonOnOnboardingScreen();
        waitFor(1);
        verifyHomepage();
        mobileDriver.resetApp();
    }

    @Test(priority = 0, groups = {"neovo", "regression"})
    public void verifyLoginPageThroughDeeplink() {
        testId = "MAB-T20377";
        logger.info(testId + ": This test validates user should be redirect to login page via Deeplink");
        ExtentTestManager.getTest().setDescription(testId + ": : This test validates user should be redirect to login page via Deeplink");
        openDeepLinkUrl(DeeplinkPathEnum.LOGIN);
        clickNoButtonOnOnboardingScreen();
        waitFor(1);
        verifyLoginPage();
        mobileDriver.resetApp();
    }

    @Test(priority = 0, groups = {"neovo", "regression"})
    public void verifyWishlistThroughDeeplink() {
        testId = "MAB-T20379";
        logger.info(testId + ": This test validates user should be redirect to Wishlist page via Deeplink");
        ExtentTestManager.getTest().setDescription(testId + ": : This test validates user should be redirect to Wishlist page via Deeplink");
        String withoutProduct = "You currently do not have any Items added to Favorites";
        openDeepLinkUrl(DeeplinkPathEnum.WISHLIST);
        clickNoButtonOnOnboardingScreen();
        waitFor(1);
        isFavoritePageLoaded();
        verifyProductTitleOnFavoritesPage(withoutProduct);
        mobileDriver.resetApp();
    }

    @Test(priority = 0, groups = {"neovo", "regression"})
    public void verifyPDPageThroughDeeplink() {
        testId = "MAB-T16082";
        logger.info(testId + ": This test validates user should be redirect to particular PDP via Deeplink ");
        ExtentTestManager.getTest().setDescription(testId + ": : This test validates user should be redirect to particular PDP via Deeplink");
        openDeepLinkUrl(DeeplinkPathEnum.PRODUCT);
        clickNoButtonOnOnboardingScreen();
        waitFor(1);
        verifyPdp();
        verifyProductTitleOnPDP("Lapels Jacket");
        mobileDriver.resetApp();
    }

    @Test(priority = 0, groups = {"neovo", "regression"})
    public void verifyDressCollectionWithTitleThroughDeeplink() {
        testId = "MAB-T16083";
        logger.info(testId + ": This test validates user should be redirect to Dress Collection With Title via Deeplink ");
        ExtentTestManager.getTest().setDescription(testId + ": : This test validates user should be redirect to Dress Collection With Title via Deeplink");
        openDeepLinkUrl(DeeplinkPathEnum.COLLECTION_TITLE);
        clickNoButtonOnOnboardingScreen();
        waitFor(1);
        verifyPlp();
        verifyCollectionPLP("Dress");
        mobileDriver.resetApp();
    }

    @Test(priority = 0, groups = {"neovo", "regression"})
    public void verifyTopsCollectionWithoutTitleThroughDeeplink() {
        testId = "MAB-T20376";
        logger.info(testId + ": This test validates user should be redirect to Tops Collection Without Title via Deeplink");
        ExtentTestManager.getTest().setDescription(testId + ": : This test validates user should be redirect to Tops Collection Without Title via Deeplink");
        openDeepLinkUrl(DeeplinkPathEnum.COLLECTION);
        clickNoButtonOnOnboardingScreen();
        waitFor(1);
        verifyPlp();
        verifyCollectionPLP("Top");
        mobileDriver.resetApp();
    }

    /**
     * TODO: Use the CleverTap Integration to send the push notification with Product Deeplink
     * Partially Automated
     */
    @Test(priority = 0, groups = {"neovo", "androidFailure", "regression"})
    public void verifyTopsCollectionDeeplinkWhenAppInBackground() {
        testId = "MAB-T14932";
        logger.info(testId + ": This test validates user should be redirect to Tops Collection Without Title via Deeplink");
        ExtentTestManager.getTest().setDescription(testId + ": : This test validates user should be redirect to Tops Collection Without Title via Deeplink");
        clickNoButtonOnOnboardingScreen();
        verifyHomepage();
        mobileDriver.runAppInBackground(Duration.ofSeconds(5));
        //TODO: Click on Push notification from CT and open Deeplink Product Page
        openDeepLinkUrlWithoutAppTermination(DeeplinkPathEnum.COLLECTION);
        verifyPlp();
        verifyCollectionPLP("Top");
    }

    /**
     * TODO: Use the CleverTap Integration to send the push notification with Product Deeplink
     * Partially Automated
     */
    @Test(priority = 0, groups = {"neovo", "regression"})
    public void verifyTopsCollectionDeeplinkWhenAppIsKilled() {
        testId = "MAB-T14933";
        logger.info(testId + ": This test validates user should be redirect to Tops Collection Without Title via Deeplink");
        ExtentTestManager.getTest().setDescription(testId + ": : This test validates user should be redirect to Tops Collection Without Title via Deeplink");
        clickNoButtonOnOnboardingScreen();
        verifyHomepage();
        if (isPlatformNameAndroid) {
            mobileDriver.terminateApp("neovocommercefootware.android.staging");
        } else {
            terminateiOSApp();
        }
        waitFor(5);
        //TODO: Click on Push notification from CT and open Deeplink Product Page
        openDeepLinkUrlWithoutAppTermination(DeeplinkPathEnum.COLLECTION);
        clickNoButtonOnOnboardingScreen();
        verifyPlp();
        verifyCollectionPLP("Top");
    }

    /**
     * TODO: Use the CleverTap Integration to send the push notification with Product Deeplink
     * Partially Automated
     */
    @Test(groups = {"neovo", "regression", "androidFailure", "iOSAppReset"})
    public void verifyPushNotificationRedirectionWhenAppInBackground() {
        testId = "MAB-T14744";
        logger.info(testId + ": This test verifies Deeplink push notification redirection when app is in Background for specific user");
        ExtentTestManager.getTest().setDescription(testId + ": This test verifies Deeplink push notification redirection when app is in Background for specific user");
        clickNoButtonOnOnboardingScreen();
        verifyHomepage();
        clickOnHamburgerButtonFromHomePage();
        clickOnLoginButtonFromHamburgerMenu();
        enterEmailID(testData.getProperty("emailID1"));
        enterPassword(testData.getProperty("password1"));
        clickLogin();
        waitFor(3);
        mobileDriver.runAppInBackground(Duration.ofSeconds(3));
        //TODO: Click on Push notification from CT and open Deeplink Product Page
        openDeepLinkUrlWithoutAppTermination(DeeplinkPathEnum.PRODUCT);
        waitFor(2);
        verifyProductTitleOnPDP("Lapels Jacket");
    }

    /**
     * TODO: Use the CleverTap Integration to send the push notification with Product Deeplink
     * Partially Automated
     */
    @Test(groups = {"neovo", "regression", "androidFailure", "iOSAppReset"})
    public void verifyPushNotificationRedirectionWhenAppInForeground() {
        testId = "MAB-T1089";
        logger.info(testId + ": This test verifies Deeplink push notification redirection when app is in Background for specific user");
        ExtentTestManager.getTest().setDescription(testId + ": This test verifies Deeplink push notification redirection when app is in Background for specific user");
        clickNoButtonOnOnboardingScreen();
        verifyHomepage();
        clickOnHamburgerButtonFromHomePage();
        clickOnLoginButtonFromHamburgerMenu();
        enterEmailID(testData.getProperty("emailID1"));
        enterPassword(testData.getProperty("password1"));
        clickLogin();
        waitFor(3);
        //TODO: Click on Push notification from CT and open Deeplink Product Page
        openDeepLinkUrlWithoutAppTermination(DeeplinkPathEnum.PRODUCT);
        waitFor(2);
        verifyProductTitleOnPDP("Lapels Jacket");

    }


    @Test(priority = 0, groups = {"neovo", "regression"})
    public void verifyDeeplinkRedirectionFromThirdPartyBrowser() {
        testId = "MAB-T1092";
        logger.info(testId + ": This test validates user should be redirect to Homepage via Deeplink");
        ExtentTestManager.getTest().setDescription(testId + ": : This test validates user should be redirect to Homepage via Deeplink");
        clickNoButtonOnOnboardingScreen();
        String pdpDeeplink="http://staging-1289.appdeep.link/products/7224835178541";
        verifyHomepage();
        if (isPlatformNameAndroid) {
            mobileDriver.terminateApp(ReadConfig.getPackageName());
            waitFor(3.2);
            launchFirefoxBrowser();
            waitFor(2);
            enterDeeplinkUrlInFirefoxbrowser(pdpDeeplink);
        } else {
            terminateiOSApp();
            waitFor(5);
            openDeepLinkUrl(DeeplinkPathEnum.PRODUCT);
        }
        clickNoButtonOnOnboardingScreen();
        waitFor(1);
        verifyPdp();
        verifyProductTitleOnPDP("Lapels Jacket");
    }

    @Test(priority = 0, groups = {"sandbox", "iOSFailure", "regression"})
    public void verifyMultiStoreSwitchingThroughDeeplinkWhenAppIsKilled() {
        testId = "MAB-T15217";
        logger.info(testId + ": This test validates user should be redirect to Store2 from Store1 via Deeplink when app is in killed state");
        ExtentTestManager.getTest().setDescription(testId + ": This test validates user should be redirect to Store2 from Store1 via Deeplink when app is in killed state");
        String highJeansAndroid="http://staging-1515.appdeep.link/products/7867764277493";
        selectStore(StoreNameEnum.NEOVO);
        clickNoButtonOnOnboardingScreen();
        verifyHomepage();
        verifyHomepageForNeovoStore();
        waitFor(1);
        if (isPlatformNameAndroid) {
            mobileDriver.terminateApp(ReadConfig.getPackageName());
        } else {
            terminateiOSApp();
        }
        waitFor(1.4);
        openDeepLinkUrl(DeeplinkPathEnum.PRODUCT_SANDBOX);
        waitFor(1.5);
        //TODO: Click on Push notification from CT and open Deeplink Product Page
        clickNoButtonOnOnboardingScreen();
        verifyPdp();
        verifyProductTitleOnPDP("High Waist Jeans");
    }

    @Test(priority = 0, groups = {"sandbox", "iOSFailure", "regression"})
    public void verifyMultiStoreSwitchingThroughDeeplinkWhenAppIsOpen() {
        testId = "MAB-T15219";
        logger.info(testId + ": This test validates user should be redirect to Store2 from Store1 via Deeplink when App is open state");
        ExtentTestManager.getTest().setDescription(testId + ": This test validates user should be redirect to Store2 from Store1 via Deeplink when App is open state");
        String highJeansAndroid="http://staging-1515.appdeep.link/products/7867764277493";
        selectStore(StoreNameEnum.NEOVO);
        clickNoButtonOnOnboardingScreen();
        verifyHomepage();
        verifyHomepageForNeovoStore();
        waitFor(2);
        openDeepLinkUrl(DeeplinkPathEnum.PRODUCT_SANDBOX);
        waitFor(2);
        //TODO: Click on Push notification from CT and open Deeplink Product Page
        onBoardingNextButton();
        verifyPdp();
        verifyProductTitleOnPDP("High Waist Jeans");
    }

    @Test(priority =0 , groups = {"neovo", "regression"})
    public void verifyDeepLinkRedirectionSocialMediaInAppBackground(){
        testId = "MAB-T13869";
        logger.info(testId + ": This test validates deeplink redirection in app when app is in background");
        ExtentTestManager.getTest().setDescription(testId + ": : This test validates deeplink redirection in app when app is in background");
        clickNoButtonOnOnboardingScreen();
        verifyHomepage();
        waitFor(2);
        if(isPlatformNameAndroid) {
            mobileDriver.runAppInBackground(Duration.ofSeconds((5)));
            waitFor(3);
            openDeepLinkUrl(DeeplinkPathEnum.PRODUCT);
        }
        else {
            terminateiOSApp();
            waitFor(5);
            openDeepLinkUrl(DeeplinkPathEnum.PRODUCT);
            clickNoButtonOnOnboardingScreen();
        }
        verifyPdp();
        verifyProductTitleOnPDP("Lapels Jacket");
    }

    @Test(priority = 0, groups = {"neovo", "regression"})
    public void verifySearchPageForDressThroughDeeplink() {
        testId = "MAB-T31148";
        logger.info(testId + " : This test validates user should be redirect to Search page for dress via Deeplink");
        ExtentTestManager.getTest().setDescription(testId + " : This test validates user should be redirect to Search page for dress via Deeplink");
        openDeepLinkUrl(DeeplinkPathEnum.SEARCH_PAGE_WITH_QUERY);
        waitFor(1.4);
        clickNoButtonOnOnboardingScreen();
        waitFor(1);
        verifySearchRelatedPlp("Dress");
    }


    @Test(priority =4 , groups = {"neovo", "regression","iOSAppReset"})
    public void verifyMyOrdersPageThroughDeeplink(){
        testId = "MAB-T31149";
        logger.info(testId + " : This test validates user should be redirect to My orders page via Deeplink");
        ExtentTestManager.getTest().setDescription(testId + " : This test validates user should be redirect to My orders page via Deeplink");
        String email = "plobaltest121@yopmail.com";
        String password="test1234";
        clickNoButtonOnOnboardingScreen();
        verifyHomepage();
        clickOnHamburgerButtonFromHomePage();
        clickOnLoginButtonFromHamburgerMenu();
        enterEmailID(email);
        enterPassword(password);
        clickLogin();
        waitFor(3);
        openDeepLinkUrl(DeeplinkPathEnum.ORDERS);
        waitFor(5);
        if (isPlatformNameAndroid) {
            clickonFirstOrderFromOrderListing();
        } else {
            waitFor(2);
            clickNoButtonOnOnboardingScreen();
            waitFor(2);
            cooClick(170, 130);//This is temporary solution
        }
        verifyOrderDetailsPage();
    }

    @Test(priority =0 , groups = {"neovo", "regression","iOSAppReset"})
    public void verifyAddressPageThroughDeeplink() {
        testId = "MAB-T31150";
        logger.info(testId + " : This test validates user should be redirect to Address page via Deeplink");
        ExtentTestManager.getTest().setDescription(testId + " : This test validates user should be redirect to Address page via Deeplink");
        String email = "plobaltest121@yopmail.com";
        String password = "test1234";
        clickNoButtonOnOnboardingScreen();
        verifyHomepage();
        clickOnHamburgerButtonFromHomePage();
        clickOnLoginButtonFromHamburgerMenu();
        enterEmailID(email);
        enterPassword(password);
        clickLogin();
        waitFor(3.2);
        openDeepLinkUrl(DeeplinkPathEnum.ADDRESS);
        clickNoButtonOnOnboardingScreen();
        waitFor(1);
        isAddressPopPageDisplayed();
    }

    @Test(priority = 0, groups = {"neovo", "regression","iOSAppReset"})
    public void verifyRegisterPageThroughDeeplink() {
        testId = "MAB-T31147";
        logger.info(testId + " : This test validates user should be redirect to Register page via Deeplink");
        ExtentTestManager.getTest().setDescription(testId + " : This test validates user should be redirect to Register page via Deeplink");
        openDeepLinkUrl(DeeplinkPathEnum.REGISTER);
        clickNoButtonOnOnboardingScreen();
        waitFor(1);
        verifyNewUserRegisterPage();
    }
}
