package qa.automation.testCase.mobile;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;
import qa.automation.base.MobileBaseTest;
import qa.automation.enums.StoreNameEnum;
import qa.automation.report.ExtentTestManager;
import qa.automation.utilities.ReadConfig;

public class OrderPageTestCases extends MobileBaseTest {

    private static final Logger logger = Logger.getLogger(OrderPageTestCases.class);

    @Test(priority = 0, groups = {"neovo", "regression"})
    public void verifyMyOrdersSectionWithoutLogin() {
        testId = "MAB-T1041";
        logger.info(testId + " : This test validates My Orders Section Without Login");
        ExtentTestManager.getTest().setDescription(testId + " : This test validates My Orders Section Without Login");
        clickNoButtonOnOnboardingScreen();
        verifyHomepage();
        clickOnMyAccount();
        verifyMyAccountPage();
        clickMyOrder();
        verifyMyAccountPage();
    }


    @Test(priority = 0, groups = {"neovo", "regression", "iOSAppReset"})
    public void verifyMyOrdersSectionWithLogin() {
        testId = "MAB-T15258";
        logger.info(testId + " : This test validates My Orders Section With Login");
        ExtentTestManager.getTest().setDescription(testId + " : This test validates My Orders Section With Login");
        clickNoButtonOnOnboardingScreen();
        verifyHomepage();
        clickOnMyAccount();
        waitFor(1);
        verifyMyAccountPage();
        clickOnLoginButtonOnMyAccountPage();
        verifyLoginPage();
        enterEmailID("plobaltest281121@yopmail.com");
        enterPassword("test1234");
        clickLogin();
        verifyMyAccountPage();
        clickMyOrder();
        verifyMyOrdersPage();
        mobileDriver.resetApp();

    }

    @Test(priority = 0, groups = {"neovo", "regression", "iOSAppReset"})
    public void verifyMyOrdersSectionWithLoginWithoutAnyPlacedOrder() {
        testId = "MAB-T1040";
        logger.info(testId + " : This test validates My Orders Section With Login Without Any Placed Order");
        ExtentTestManager.getTest().setDescription(testId + " : This test validates My Orders Section With Login Without Any Placed Order");
        clickNoButtonOnOnboardingScreen();
        verifyHomepage();
        clickOnMyAccount();
        verifyMyAccountPage();
        clickOnLoginButtonOnMyAccountPage();
        verifyLoginPage();
        enterEmailID("plobaltest2811@yopmail.com");
        enterPassword("test1234");
        clickLogin();
        verifyMyAccountPage();
        clickMyOrder();
        verifyMyOrdersPageWithoutAnyPlacedOrder();
        mobileDriver.resetApp();

    }

    @Test(priority = 0, groups = {"sandbox", "regression", "iOSAppReset"})
    public void verifyVariantWisePriceOnOrderDetailsPage() {
        testId = "MAB-T15343";
        logger.info(testId + " : This test validates variant wise price is appearing on order details page");
        ExtentTestManager.getTest().setDescription(testId + " : This test validates variant wise price is appearing on order details page");
        selectStore(StoreNameEnum.SANDBOX);
        verifyHomepage();
        clickOnHamburgerButtonFromHomePage();
        clickOnMyProfileFromHamburger();
        waitFor(2);
        verifyMyAccountPage();
        clickLoginOrLogoutButtonOnProfilePage();
        verifyLoginPage();
        enterEmailID("vrushali1@gmail.com");
        enterPassword("test123");
        clickLogin();
        clickMyOrder();
        verifyMyOrdersPage();
        waitFor(2);
        if (isPlatformNameAndroid) {
            clickOnOrderId();
        } else {
            cooClick(170, 130);//This is temporary solution
        }
        waitFor(1.4);
        verifyOrderDetailsPage();
        verifyVariantWisePrice();
    }


    @Test(groups = {"sandbox", "Regression", "iOSAppReset"})
    public void verifyMyOrdersHeadingOnMyOrdersPage() {
        String email = "adityanisal@plobalapps.com";
        String password = "qwerty";
        testId = "MAB-T15334";
        logger.info(testId + " This test validates My orders header showing or not on order listing  page.");
        ExtentTestManager.getTest().setDescription(testId + " This test validates My orders header showing or not on order listing  page.");
        selectStore(StoreNameEnum.SANDBOX);
        verifyHomepage();
        clickOnHamburgerButtonFromHomePage();
        clickOnLoginButtonFromHamburgerMenu();
        enterEmailID(email);
        enterPassword(password);
        clickLogin();
        clickOnHamburgerButtonFromHomePage();
        clickOnMyProfileFromHamburger();
        clickMyOrder();
        verifyMyOrdersPage();
    }

    @Test(groups = {"sandbox", "Regression", "iOSAppReset"})
    public void verifyOrdersListingPageOnWithoutAnyOrderPlaced() {
        String email = "as@gmail.com";
        String password = "qwerty";
        testId = "MAB-T15273";
        logger.info(testId + " This test validates order listing page UI from the my profile page when no order placed yet");
        ExtentTestManager.getTest().setDescription(testId + " This test validates order listing page UI from the my profile page when no order placed yet");
        selectStore(StoreNameEnum.SANDBOX);
        verifyHomepage();
        clickOnHamburgerButtonFromHomePage();
        clickOnLoginButtonFromHamburgerMenu();
        enterEmailID(email);
        enterPassword(password);
        clickLogin();
        clickOnHamburgerButtonFromHomePage();
        clickOnMyProfileFromHamburger();
        clickMyOrder();
        verifyMyOrdersPage();
        isNoOrdersPlacedTextDisplayed();
    }

    @Test(priority = 0, groups = {"sandbox", "regression", "iOSFailure", "iOSAppReset"})
    public void verifyOrderListingUiAfterPlaceingOrder() {
        String email = "adityanisal@plobalapps.com";
        String password = "qwerty";
        String cc = "1";
        String firstname = "Test";
        String lastname = "Plobal";
        String expirydate_and = "09/26";
        String expirydate_ios = "0926";
        String cvv = "546";
        testId = "MAB-T15263";
        logger.info(testId + ": This test validates order listing page UI from my profile page when user recently placed any order");
        ExtentTestManager.getTest().setDescription(testId + ": This test validates order listing page UI from my profile page when user recently placed any order");
        selectStore(StoreNameEnum.SANDBOX);
        verifyHomepage();
        clickOnHamburgerButtonFromHomePage();
        clickOnLoginButtonFromHamburgerMenu();
        enterEmailID(email);
        enterPassword(password);
        clickLogin();
        clickOnProductFromGrid();
        clickAddToCartOnPDP();
        clickOnGoToCart();
        clickOnPlaceOrder();
        waitFor(3);
        verifyCTAPopup();
        clickOnCreditCardCTA();
        waitFor(5);
        checkNativeCheckoutPage();
        if (isPlatformNameAndroid) {
            clickOnPaymentOptionOnCheckoutPage();
        } else {
            clickOnContinueButtonOnCheckoutPage();
        }
        enterCreditCardNumber(cc);
        enterFirstNameOnCredit(firstname);
        enterLastNameOnCredit(lastname);
        enterCvvOnCredit(cvv);
        if (isPlatformNameAndroid) {
            enterDateOnCredit(expirydate_and);
            clickOnAddPaymentButtonOnCardDetails();
            clickOnPlaceOrder();
        } else {
            enterDateOnCredit(expirydate_ios);
            clickDoneButtonOnIOSKeyboard();
            clickOnPayNowButton();
        }
        waitFor(10);
        isOrderConfirmationPageDisplayed();
        if (!isPlatformNameAndroid) {
            scrollUpForIos();
        }
        String orderID = getOrderIdFromNewThankyouPage();
        if (isPlatformNameAndroid) {
            scrollToText("Continue Shopping");
        } else {
            waitFor(2);
            scrollForIos();
        }
        clickOnContinueShoppingButtonOnThankYouPage();
        waitFor(3);
        clickOnHamburgerButtonFromHomePage();
        clickOnMyProfileFromHamburger();
        verifyMyAccountPage();
        clickMyOrder();
        checkOrderIdFromOrderListingPage(orderID);
        verifyNewOrderListingPage();
    }

    @Test(priority = 0, groups = {"neovo", "regression", "iOSAppReset"})
    public void verifyUserableToReorderSpecificOrder() {
        String email = "keithsilva@plobalapps.com";
        String password = "Plobalapps@836";
        testId = "MAB-T15272";
        logger.info(testId + ": This test validates user able to reorder the specific product on click on reorder");
        ExtentTestManager.getTest().setDescription(testId + ": This test validates user able to reorder the specific product on click on reorder");
        clickNoButtonOnOnboardingScreen();
        verifyHomepage();
        clickProfileTabOnHomePage();
        verifyMyAccountPage();
        clickOnLoginButtonOnMyAccountPage();
        verifyLoginPage();
        enterEmailID(email);
        enterPassword(password);
        clickLogin();
        verifyMyAccountPage();
        clickMyOrder();
        verifyMyOrdersPage();
        waitFor(3);
        clickOnOrderId();
        waitFor(2);
        clickOnReorderButton();
        verifyCartPage();
    }

    @Test(priority = 0, groups = {"neovo", "regression","iOSAppReset"})
    public void verifyOrderActionButtonDisplay() {
        String email = "sahiltripathi@plobalapps.com";
        String password = "plobal123";
        testId = "MAB-T15268";
        logger.info(testId + ": This test validates all order action button should displayed (track order,cancel order,return order)");
        ExtentTestManager.getTest().setDescription(testId + ": This test validates all order action button should displayed (track order,cancel order,return order)");
        clickNoButtonOnOnboardingScreen();
        verifyHomepage();
        clickOnHamburgerButtonFromHomePage();
        clickOnLoginButtonFromHamburgerMenu();
        verifyLoginPage();
        enterEmailID(email);
        enterPassword(password);
        clickLogin();
        clickOnHamburgerButtonFromHomePage();
        clickOnMyProfileFromHamburger();
        clickMyOrder();
        verifyMyOrdersPage();
        waitFor(4);
        clickOnOrderId();
        checkTrackOrderButton();
        checkReorderButton();
        checkCancelOrderButton();
    }

    @Test(priority = 0, groups = {"neovo", "regression","iOSAppReset"})
    public void verifyConditionalOrderActionButtons() {
        testId = "MAB-T28249";
        String email = "sahiltripathi@plobalapps.com";
        String password = "plobal123";
        logger.info(testId + ": This test validates conditional order action button under My Order (track order,cancel order,return order)");
        ExtentTestManager.getTest().setDescription(testId + ": This test validates conditional order action button under My Order (track order,cancel order,return order)");
        clickNoButtonOnOnboardingScreen();
        verifyHomepage();
        clickOnHamburgerButtonFromHomePage();
        clickOnLoginButtonFromHamburgerMenu();
        verifyLoginPage();
        enterEmailID(email);
        enterPassword(password);
        clickLogin();
        clickProfilePage();
        clickMyOrder();
        verifyMyOrdersPage();
        clickOnOrderId();
        checkTrackOrderButton();
        checkReorderButton();
        checkCancelOrderButton();
    }
}
