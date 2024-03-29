package qa.automation.testCase.mobile;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;
import qa.automation.base.MobileBaseTest;
import qa.automation.enums.StoreNameEnum;
import qa.automation.report.ExtentTestManager;
import qa.automation.utilities.ReadConfig;

public class NewOrderConfirmationPageTestCases extends MobileBaseTest {

    private static final Logger logger = Logger.getLogger(NewOrderConfirmationPageTestCases.class);

    @Test(priority = 0, groups = {"sandbox", "regression", "iOSAppReset"})
    public void verifyCancelOrderButton() {
        String email = "adityanisal@plobalapps.com";
        String password = "qwerty";
        testId = "MAB-T15886";
        logger.info(testId + " : This test validates cancel order button availability in order details page");
        ExtentTestManager.getTest().setDescription(testId + " : This test validates cancel order button availability in order details page");
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
        waitFor(2);
        if (isPlatformNameAndroid) {
            clickOnOrderId();
        } else {
            cooClick(170, 130);//This is temporary solution
        }
        waitFor(1.4);
        verifyOrderDetailsPage();
        checkCancelOrderButton();
    }


    @Test(priority = 0, groups = {"sandbox", "regression", "iOSAppReset"})
    public void verifyTrackOrderButton() {
        String email = "adityanisal@plobalapps.com";
        String password = "qwerty";
        testId = "MAB-T15884";
        logger.info(testId + " : This test validates Track order button availability in order details page");
        ExtentTestManager.getTest().setDescription(testId + " : This test validates Track order button availability in order details page");
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
        waitFor(2);
        if (isPlatformNameAndroid) {
            clickOnOrderId();
        } else {
            cooClick(170, 130);//This is temporary solution
        }
        waitFor(1.4);
        verifyOrderDetailsPage();
        checkTrackOrderButton();
    }

    @Test(priority = 0, groups = {"sandbox", "regression", "iOSAppReset"})
    public void verifyReorderButton() {
        String email = "adityanisal@plobalapps.com";
        String password = "qwerty";
        testId = "MAB-T15885";
        logger.info(testId + " : This test validates Reorder button availability in order details page");
        ExtentTestManager.getTest().setDescription(testId + " : This test validates Reorder button availability in order details page");
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
        waitFor(2);
        if (isPlatformNameAndroid) {
            clickOnOrderId();
        } else {
            cooClick(170, 130);//This is temporary solution
        }
        waitFor(1.4);
        verifyOrderDetailsPage();
        checkReorderButton();
    }

    @Test(priority = 0, groups = {"sandbox", "regression", "iOSFailure", "iOSAppReset"})
    public void verifyNewThankYouPageForSandbox() {
        String email = "adityanisal@plobalapps.com";
        String password = "qwerty";
        testId = "MAB-T15854";
        logger.info(testId + ": This test validates Order confirmation page is visible after successful payment with all the detail when user place order via Native checkout using other payment mode");
        ExtentTestManager.getTest().setDescription(testId + ": This test validates Order confirmation page is visible after successful payment with all the detail when user place order via Native checkout using other payment mode");
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
        clickOnOtherButtonCTA();
        waitFor(6);
        clickOnContinueButtonOnCheckoutPage();
        waitFor(5);
        if (isPlatformNameAndroid) {
            if (!isPaymentPageIsDisplayedOnWebviewCheckoutPage()){
                clickContinueButtonOnInformationAndShippingPageOnWebviewCheckout();
            }
            scrollToText("Security code");
        } else {
            if (!isPaymentPageIsDisplayedOnWebviewCheckoutPage()){
                clickContinueButtonOnInformationAndShippingPageOnWebviewCheckout();
            }
            scrollDownToIOSElement(checkoutPage.getCardNumber());
        }
        waitFor(1);
        enterCardNumber("1");
        enterCardHolderName("test Plobal");
        enterCardExpDate("1234");
        enterCardCvv("123");
        if (!isPlatformNameAndroid) {
            clickDoneButtonOnIOSKeyboard();
        }
        if (isPlatformNameAndroid) {
            scrollToText("Pay now");
        } else {
            waitFor(2.5);
            swipeForIos("down");
        }
        clickOnPayNow();
        waitFor(10);
        verifyNewThankYouPage();
    }


    @Test(priority = 0, groups = {"sandbox", "regression", "iOSFailure", "androidFailure", "iOSAppReset"})
    public void myOrderButtonOnThankYouPage() {
        String email = "adityanisal@plobalapps.com";
        String password = "qwerty";
        testId = "MAB-T15856";
        logger.info(testId + ": This test validates My order button on the Thank you page is clickable when user places order via Native checkout using Other payment mode");
        ExtentTestManager.getTest().setDescription(testId + ": This test validates My order button on the Thank you page is clickable when user places order via Native checkout using Other payment mode");
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
        waitFor(1.5);
        clickOnPlaceOrder();
        waitFor(3);
        clickOnOtherButtonCTA();
        waitFor(6);
        clickOnContinueButtonOnCheckoutPage();
        waitFor(4);
        if (isPlatformNameAndroid) {
            if (!isPaymentPageIsDisplayedOnWebviewCheckoutPage()){
                clickContinueButtonOnInformationAndShippingPageOnWebviewCheckout();
            }
            scrollToText("Security code");
        } else {
            if (!isPaymentPageIsDisplayedOnWebviewCheckoutPage()){
                clickContinueButtonOnInformationAndShippingPageOnWebviewCheckout();
            }
            scrollDownToIOSElement(checkoutPage.getCardNumber());
        }
        waitFor(1);
        enterCardNumber("1");
        enterCardHolderName("test Plobal");
        enterCardExpDate("1234");
        enterCardCvv("123");
        if (!isPlatformNameAndroid) {
            clickDoneButtonOnIOSKeyboard();
        }
        if (isPlatformNameAndroid) {
            scrollToText("Pay now");
        } else {
            waitFor(2.5);
            swipeForIos("down");
        }
        clickOnPayNow();
        waitFor(10);
        verifyNewThankYouPage();
        if (isPlatformNameAndroid) {
            scrollToText("Continue Shopping");
        } else {
            waitFor(3);
            scrollForIos();
        }
        clickMyOrderButtonOnThankYouPage();
    }


    @Test(priority = 0, groups = {"sandbox", "regression", "iOSFailure", "androidFailure", "iOSAppReset"})
    public void verifyOrderListingPageFromThankYouPage() {
        String email = "adityanisal@plobalapps.com";
        String password = "qwerty";
        testId = "MAB-T15855";
        logger.info(testId + ": This test validates Order listing page is visible after successful payment with all the detail when user place order via Native checkout using Other payment mode");
        ExtentTestManager.getTest().setDescription(testId + ": This test validates Order listing page is visible after successful payment with all the detail when user place order via Native checkout using Other payment mode");
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
        clickOnOtherButtonCTA();
        waitFor(6);
        clickOnContinueButtonOnCheckoutPage();
        waitFor(5);
        if (isPlatformNameAndroid) {
            if (!isPaymentPageIsDisplayedOnWebviewCheckoutPage()){
                clickContinueButtonOnInformationAndShippingPageOnWebviewCheckout();
            }
            scrollToText("Security code");
        } else {
            if (!isPaymentPageIsDisplayedOnWebviewCheckoutPage()){
                clickContinueButtonOnInformationAndShippingPageOnWebviewCheckout();
            }
            scrollDownToIOSElement(checkoutPage.getCardNumber());
        }
        waitFor(1);
        enterCardNumber("1");
        enterCardHolderName("test Plobal");
        enterCardExpDate("1234");
        enterCardCvv("123");
        if (!isPlatformNameAndroid) {
            clickDoneButtonOnIOSKeyboard();
        }
        if (isPlatformNameAndroid) {
            scrollToText("Pay now");
        } else {
            waitFor(2.5);
            swipeForIos("down");
        }
        clickOnPayNow();
        waitFor(10);
        verifyNewThankYouPage();
        String orderID = getOrderIdFromNewThankyouPage();
        if (isPlatformNameAndroid) {
            scrollToText("Continue Shopping");
        } else {
            waitFor(2);
            scrollForIos();
        }
        clickOnContinueShoppingButtonOnThankYouPage();
        waitFor(3);
        clickProfileTabOnHomepageForSandbox();
        clickMyOrder();
        waitFor(2);
        if(!isPlatformNameAndroid){
            cooClick(170, 130);//This is temporary solution
        }
        else {
            checkOrderIdFromOrderListingPage(orderID);
        }
    }

    /**
     * TODO: Partial Test case - Firebase Integration required
     */
    @Test(priority = 0, groups = {"neovo", "regression", "iOSAppReset"})
    public void verifyFireBaseEventForMyOrderPage() {
        testId = "MAB-T15882";
        logger.info(testId + ": This test validates Order listing page redirection event on Firebase");
        ExtentTestManager.getTest().setDescription(testId + ": This test validates Order listing page is visible after successful payment with all the detail when user place order via Native checkout using Other payment mode");
        clickNoButtonOnOnboardingScreen();
        verifyHomepage();
        clickOnMyAccount();
        clickOnLoginButtonOnMyAccountPage();
        String email = "keithsilva@plobalapps.com";
        String password = "Plobalapps@836";
        enterEmailID(email);
        enterPassword(password);
        clickLogin();
        waitFor(3);
        clickMyOrder();
        //TODO: check event on Firebase
    }


    /**
     * TODO: check event on Clever Tap - CleverTap Integration required
     */
    @Test(priority = 0, groups = {"sandbox", "regression", "iOSAppReset"})
    public void verifyCTEventOnMYOrderButtonOnThankYouPage() {
        String email = "keithsilva@plobalapps.com";
        String password = "Plobalapps@836";
        testId = "MAB-T15863";
        logger.info(testId + ": This test validates CT Event for My order button on the Thank you page ");
        ExtentTestManager.getTest().setDescription(testId + ": This test validates CT Event for My order button on the Thank you page ");
        selectStore(StoreNameEnum.SANDBOX);
        verifyHomepage();
        clickOnHamburgerButtonFromHomePage();
        clickOnLoginButtonFromHamburgerMenu();
        enterEmailID(email);
        enterPassword(password);
        clickLogin();
        clickOnProductFromGrid();
        waitFor(5);
        clickAddToCartOnPDP();
        waitFor(2);
        clickCartIconOnPdpPage();
        clickOnPlaceOrder();
        waitFor(3);
        verifyCTAPopup();
        clickOnOtherButtonCTA();
        waitFor(6);
        clickOnContinueButtonOnCheckoutPage();
        waitFor(5);
        if (isPlatformNameAndroid) {
            if (!isPaymentPageIsDisplayedOnWebviewCheckoutPage()){
                clickContinueButtonOnInformationAndShippingPageOnWebviewCheckout();
            }
            scrollToText("Security code");
        } else {
            if (!isPaymentPageIsDisplayedOnWebviewCheckoutPage()){
                clickContinueButtonOnInformationAndShippingPageOnWebviewCheckout();
            }
            scrollDownToIOSElement(checkoutPage.getCardNumber());
        }
        waitFor(1);
        enterCardNumber("1");
        waitFor(2);
        enterCardHolderName("test Plobal");
        enterCardExpDate("1234");
        enterCardCvv("123");
        if (!isPlatformNameAndroid) {
            clickDoneButtonOnIOSKeyboard();
        }
        if (isPlatformNameAndroid) {
            scrollToText("Pay now");
        } else {
            waitFor(2.5);
            swipeForIos("down");
        }
        clickOnPayNow();
        waitFor(10);
        verifyNewThankYouPage();
        if (isPlatformNameAndroid) {
            scrollToText("Contact Us");
        } else {
            waitFor(3);
            scrollForIos();
        }
        clickMyOrderButtonOnThankYouPage();
        //TODO: check event on Clever Tap
    }


    @Test(priority = 0, groups = {"sandbox", "regression", "iOSOnly", "iOSAppReset"})
    public void verifyMyOrderButtonOnThankYouPageNativeCheckoutCreditFlow() {
        String email = "keithsilva@plobalapps.com";
        String password = "Plobalapps@836";
        String creditNumber = "1";
        String firstName = "Leo";
        String lastName = "Messi";
        String date = "0826";
        String cvv = "456";
        testId = "MAB-T15850";
        logger.info(testId + ": This test validates My order button on thank you page for credit card payments done through Native Checkout ");
        ExtentTestManager.getTest().setDescription(testId + ": This test validates My order button on thank you page for credit card payments done through Native Checkout");
        selectStore(StoreNameEnum.SANDBOX);
        verifyHomepage();
        clickOnHamburgerButtonFromHomePage();
        clickOnLoginButtonFromHamburgerMenu();
        enterEmailID(email);
        enterPassword(password);
        clickLogin();
        clickOnProductFromGrid();
        waitFor(5);
        clickAddToCartOnPDP();
        clickOnGoToCart();
        clickOnPlaceOrder();
        verifyCTAPopup();
        clickOnCreditCardCTA();
        checkNativeCheckoutPage();
        if (isPlatformNameAndroid) {
            waitFor(4);
            clickOnPaymentOptionOnCheckoutPage();
            enterCreditCardNumber(creditNumber);
            enterFirstNameOnCredit(firstName);
            enterLastNameOnCredit(lastName);
            enterDateOnCredit(date);
            enterCvvOnCredit(cvv);
            mobileDriver.hideKeyboard();
            clickOnAddPaymentButtonOnCardDetails();
            verifyNewThankYouPage();
        } else {
            waitFor(4);
            clickOnContinueButtonOnCheckoutPage();
        }
        if (!isPlatformNameAndroid) {
            enterCreditCardNumber(creditNumber);
            enterFirstNameOnCredit(firstName);
            enterLastNameOnCredit(lastName);
            enterDateOnCredit(date);
            enterCvvOnCredit(cvv);
            clickDoneButtonOnIOSKeyboard();
            clickOnPayNowButton();
            verifyNewThankYouPage();
        }
        if (isPlatformNameAndroid) {
            scrollToText("My Order");
        } else {
            waitFor(2);
            scrollForIos();
        }
        clickMyOrderButtonOnThankYouPage();
        waitFor(2);
    }


    @Test(priority = 0, groups = {"sandbox", "regression", "iOSOnly", "iOSAppReset"})
    public void verifyNewOrderDetailsPageForNativeCheckoutCreditFlow() {
        String email = "keithsilva@plobalapps.com";
        String password = "Plobalapps@836";
        String creditNumber = "1";
        String firstName = "Leo";
        String lastName = "Messi";
        String date = "0826";
        String cvv = "456";
        testId = "MAB-T15751";
        logger.info(testId + ": This test validates My order button on thank you page for credit card payments done through Native Checkout ");
        ExtentTestManager.getTest().setDescription(testId + ": This test validates My order button on thank you page for credit card payments done through Native Checkout");
        selectStore(StoreNameEnum.SANDBOX);
        verifyHomepage();
        clickOnHamburgerButtonFromHomePage();
        clickOnLoginButtonFromHamburgerMenu();
        enterEmailID(email);
        enterPassword(password);
        clickLogin();
        clickOnProductFromGrid();
        waitFor(5);
        clickAddToCartOnPDP();
        clickOnGoToCart();
        clickOnPlaceOrder();
        verifyCTAPopup();
        clickOnCreditCardCTA();
        checkNativeCheckoutPage();
        if (isPlatformNameAndroid) {
            waitFor(4);
            clickOnPaymentOptionOnCheckoutPage();
            enterCreditCardNumber(creditNumber);
            enterFirstNameOnCredit(firstName);
            enterLastNameOnCredit(lastName);
            enterDateOnCredit(date);
            enterCvvOnCredit(cvv);
            mobileDriver.hideKeyboard();
            clickOnAddPaymentButtonOnCardDetails();
            verifyNewThankYouPage();
        } else {
            waitFor(4);
            clickOnContinueButtonOnCheckoutPage();
        }
        if (!isPlatformNameAndroid) {
            enterCreditCardNumber(creditNumber);
            enterFirstNameOnCredit(firstName);
            enterLastNameOnCredit(lastName);
            enterDateOnCredit(date);
            enterCvvOnCredit(cvv);
            clickDoneButtonOnIOSKeyboard();
            clickOnPayNowButton();
            verifyNewThankYouPage();
        }
        if (isPlatformNameAndroid) {
            scrollToText("My Order");
        } else {
            waitFor(2);
            scrollForIos();
        }
        clickMyOrderButtonOnThankYouPage();
        waitFor(2);
        verifyNewOrderdeatailsPage();
    }

    @Test(priority = 0, groups = {"sandbox", "regression", "iOSAppReset"})
    public void verifyOrderConfirmationPageThroughPaymentPollingUsingCreditCard() {
        testId = "MAB-T15858";
        logger.info(testId + ": This test validates Order confirmation page UI in case of payment polling when ordered via native checkout flow using credit card");
        ExtentTestManager.getTest().setDescription(testId + ": This test validates Order confirmation page UI in case of payment polling when ordered via native checkout flow using credit card");
        String email = "adityanisal@plobalapps.com";
        String password = "qwerty";
        selectStore(StoreNameEnum.SANDBOX);
        verifyHomepage();
        clickOnHamburgerButtonFromHomePage();
        clickOnLoginButtonFromHamburgerMenu();
        enterEmailID(email);
        enterPassword(password);
        clickLogin();
        clickOnProductFromGrid();
        waitFor(5);
        clickAddToCartOnPDP();
        clickOnGoToCart();
        clickOnPlaceOrder();
        verifyCTAPopup();
        clickOnCreditCardCTA();
        checkNativeCheckoutPage();
        waitFor(5);
        if (isPlatformNameAndroid) {
            clickOnPaymentOptionOnCheckoutPage();
        } else {
            clickOnContinueButtonOnCheckoutPage();
        }
        enterCreditCardNumber("1");
        enterFirstNameOnCredit("Test");
        enterLastNameOnCredit("Plobal");
        if (isPlatformNameAndroid) {
            enterDateOnCredit("09/26");
            enterCvvOnCredit("546");
            clickOnAddPaymentButtonOnCardDetails();
            clickOnPlaceOrder();
            waitFor(5.5);
            mobileDriver.terminateApp(ReadConfig.getPackageName());
            waitFor(13);
            launchAndroidApp(ReadConfig.getPackageName(), ReadConfig.getActvityName());
        } else {
            waitFor(1.4);
            enterDateOnCredit("0926");
            enterCvvOnCredit("546");
            clickDoneButtonOnIOSKeyboard();
            clickOnPayNowButton();
            waitFor(5);
            terminateiOSApp();
            waitFor(9);
            mobileDriver.activateApp(ReadConfig.getBundleId());
        }
        waitFor(3);
        isOrderConfirmationPageDisplayed();
    }


    @Test(priority = 0, groups = {"sandbox", "regression", "iOSAppReset"})
    public void verifyOrderConfirmationPageThroughPaymentPollingUsingOther_COD() {
        testId = "MAB-T15859";
        logger.info(testId + ": This test validates Order confirmation page UI in case of payment polling when ordered via native checkout flow using Other payment mode ");
        ExtentTestManager.getTest().setDescription(testId + ": This test validates Order confirmation page UI in case of payment polling when ordered via native checkout flow using Other payment mode");
        String email = "adityanisal@plobalapps.com";
        String password = "qwerty";
        selectStore(StoreNameEnum.SANDBOX);
        verifyHomepage();
        clickOnHamburgerButtonFromHomePage();
        clickOnLoginButtonFromHamburgerMenu();
        enterEmailID(email);
        enterPassword(password);
        clickLogin();
        clickOnProductFromGrid();
        waitFor(5);
        clickAddToCartOnPDP();
        clickOnGoToCart();
        clickOnPlaceOrder();
        verifyCTAPopup();
        clickOnOtherButtonCTA();
        waitFor(5);
        if (isPlatformNameAndroid) {
            clickOnPlaceOrder();
            waitFor(1.7);
            try {
                scrollToText("Continue to shipping");
                clickOnContinueButtonOnWebview();
                waitFor(2);
                scrollToText("Continue to payment");
                clickOnContinueButtonOnWebview();
            } catch (Exception e) {
            }
            waitFor(2);
            scrollToText("Pay now");
            clickOnCodButtonOnWebview();
            clickOnCompleteOrderButtonOnWebview();
            waitFor(3.5);
            mobileDriver.terminateApp(ReadConfig.getPackageName());
            waitFor(13);
            launchAndroidApp(ReadConfig.getPackageName(), ReadConfig.getActvityName());
        } else {
            clickOnContinueButtonOnCheckoutPage();
            waitFor(2);
            scrollForIos();
            waitFor(1);
            scrollForIos();
            try {
                clickOnContinueButtonOnWebview();
                waitFor(2);
                scrollForIos();
                clickOnContinueButtonOnWebview();
                waitFor(2);
                scrollForIos();
            } catch (Exception e) {

            }
            clickOnCodButtonOnWebview();
            clickOnCompleteOrderButtonOnWebview();
            waitFor(5);
            terminateiOSApp();
            waitFor(9);
            mobileDriver.activateApp(ReadConfig.getBundleId());
        }
        waitFor(3);
        isOrderConfirmationPageDisplayed();
    }

    @Test(priority = 0, groups = {"neovo", "regression", "androidOnly"})
    public void verifyImageStickersOnRecommendationProductsOnOrderDetailsPage() {
        testId = "MAB-T15892";
        String email = "adityanisal@plobalapps.com";
        String password = "qwerty";
        logger.info(testId + " This test validates user is able to see the product image sticker on order details page's recommendation section");
        ExtentTestManager.getTest().setDescription(testId + " This test validates  user is able to see the product image sticker on order details page's recommendation section");
        clickNoButtonOnOnboardingScreen();
        clickOnHamburgerButtonFromHomePage();
        clickOnLoginButtonFromHamburgerMenu();
        verifyLoginPage();
        enterEmailID(email);
        enterPassword(password);
        clickLogin();
        clickProfileTabOnHomePage();
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
        checkImageStickerOnOrderDetailsPage();
    }

    @Test(priority = 0, groups = {"sandbox", "regression", "iOSAppReset"})
    public void verifyPaymentPollingThroughCartAndCreditCardFlow() {
        testId = "MAB-T15166";
        logger.info(testId + ": This test validates Payment Success page in case of payment polling when ordered via Cart and native checkout flow using credit card");
        ExtentTestManager.getTest().setDescription(testId + ": This test validates Payment Success page in case of payment polling when ordered via Cart and native checkout flow using credit card");
        String email = "keithsilva@plobalapps.com";
        String password = "Plobalapps@836";
        selectStore(StoreNameEnum.SANDBOX);
        verifyHomepage();
        clickOnProductFromGrid();
        waitFor(2);
        verifyPdp();
        clickAddToCartOnPDP();
        clickOnGoToCart();
        verifyCartPage();
        clickOnPlaceOrder();
        verifyCTAPopup();
        clickOnCreditCardCTA();
        waitFor(3);
        clickLoginButtonOnNativeCheckout();
        waitFor(2);
        verifyLoginPage();
        enterEmailID(email);
        enterPassword(password);
        clickLogin();
        waitFor(5);
        checkNativeCheckoutPage();
        if (isPlatformNameAndroid) {
            clickOnPaymentOptionOnCheckoutPage();
        } else {
            clickOnContinueButtonOnCheckoutPage();
        }
        enterCreditCardNumber("1");
        enterFirstNameOnCredit("Test");
        enterLastNameOnCredit("Plobal");
        if (isPlatformNameAndroid) {
            enterDateOnCredit("09/26");
            enterCvvOnCredit("546");
            clickOnAddPaymentButtonOnCardDetails();
            clickOnPlaceOrder();
            waitFor(5.5);
            mobileDriver.terminateApp(ReadConfig.getPackageName());
            waitFor(13);
            launchAndroidApp(ReadConfig.getPackageName(), ReadConfig.getActvityName());
        } else {
            waitFor(1.4);
            enterDateOnCredit("0926");
            enterCvvOnCredit("546");
            clickDoneButtonOnIOSKeyboard();
            clickOnPayNowButton();
            waitFor(4);
            terminateiOSApp();
            waitFor(9);
            mobileDriver.activateApp(ReadConfig.getBundleId());
        }
        waitFor(3);
        isOrderConfirmationPageDisplayed();
    }

    @Test(priority = 0, groups = {"sandbox", "regression", "iOSAppReset"})
    public void verifyNewThankYouPageWithCreditCardForSandbox() {
        String email = "priya.n@plobalapps.com";
        String password = "qwerty";
        String creditNumber = "1";
        String firstName = "Bogus";
        String lastName = "Gateway";
        String date = "1227";
        String cvv = "111";
        testId = "MAB-T15750";
        logger.info(testId + ": This test validates Order confirmation page is visible after successful payment with all the detail when user place order via Native checkout using Credit Card mode");
        ExtentTestManager.getTest().setDescription(testId + ": This test validates Order confirmation page is visible after successful payment with all the detail when user place order via Native checkout using Credit Card mode");
        selectStore(StoreNameEnum.SANDBOX);
        verifyHomepage();
        clickOnHamburgerButtonFromHomePage();
        clickOnLoginButtonFromHamburgerMenu();
        verifyLoginPage();
        enterEmailID(email);
        enterPassword(password);
        clickLogin();
        clickOnProductFromGrid();
        clickAddToCartOnPDP();
        clickOnGoToCart();
        waitFor(2);
        verifyCartPage();
        clickOnPlaceOrder();
        waitFor(3);
        verifyCTAPopup();
        waitFor(6);
        clickOnCreditCardCTA();
        checkNativeCheckoutPage();
        waitFor(4);
        if (isPlatformNameAndroid) {
            clickOnPaymentOptionOnCheckoutPage();
        } else {
            clickOnContinueButtonOnCheckoutPage();
        }
        enterCreditCardNumber(creditNumber);
        enterFirstNameOnCredit(firstName);
        enterLastNameOnCredit(lastName);
        enterCvvOnCredit(cvv);
        if (isPlatformNameAndroid) {
            enterDateOnCredit("12/27");
            mobileDriver.hideKeyboard();
            clickOnAddPaymentButtonOnCardDetails();
            clickOnPlaceOrder();
        } else {
            enterDateOnCredit(date);
            clickDoneButtonOnIOSKeyboard();
            clickOnPayNowButton();
        }
        waitFor(7);
        verifyNewThankYouPage();
    }

    @Test(priority = 0, groups = {"sandbox", "regression", "iOSAppReset"})
    public void verifyPaymentPollingThroughBuyNowAndCreditCardFlow() {
        testId = "MAB-T15180";
        logger.info(testId + ": This test validates Payment Success page in case of payment polling when ordered via native checkout and Buy Now flow using credit card");
        ExtentTestManager.getTest().setDescription(testId + ": This test validates Payment Success page in case of payment polling when ordered via native checkout and Buy Now flow using credit card");
        String email = "mayursavdekar@plobalapps.com";
        String password = "12345";
        selectStore(StoreNameEnum.SANDBOX);
        verifyHomepage();
        clickOnProductFromGrid();
        waitFor(2);
        verifyPdp();
        clickOnBuyNowFromPDP();
        verifyCTAPopup();
        clickOnCreditCardCTA();
        waitFor(3);
        clickLoginButtonOnNativeCheckout();
        waitFor(2);
        verifyLoginPage();
        enterEmailID(email);
        enterPassword(password);
        clickLogin();
        waitFor(5);
        checkNativeCheckoutPage();
        waitFor(5);
        if (isPlatformNameAndroid) {
            clickOnPaymentOptionOnCheckoutPage();
        } else {
            clickOnContinueButtonOnCheckoutPage();
        }
        enterCreditCardNumber("1");
        enterFirstNameOnCredit("Test");
        enterLastNameOnCredit("Plobal");
        if (isPlatformNameAndroid) {
            enterDateOnCredit("09/26");
            enterCvvOnCredit("546");
            clickOnAddPaymentButtonOnCardDetails();
            clickOnPlaceOrder();
            waitFor(5.5);
            mobileDriver.terminateApp(ReadConfig.getPackageName());
            waitFor(13);
            launchAndroidApp(ReadConfig.getPackageName(), ReadConfig.getActvityName());
        } else {
            waitFor(1.4);
            enterDateOnCredit("0926");
            enterCvvOnCredit("546");
            clickDoneButtonOnIOSKeyboard();
            clickOnPayNowButton();
            waitFor(4);
            terminateiOSApp();
            waitFor(9);
            mobileDriver.activateApp(ReadConfig.getBundleId());
        }
        waitFor(3);
        isOrderConfirmationPageDisplayed();
    }

    @Test(priority = 0, groups = {"sandbox", "regression", "iOSAppReset"})
    public void verifyPaymentPollingThroughCartAndCODFlow() {
        testId = "MAB-T15170";
        logger.info(testId + ": This test validates Payment Success page in case of payment polling when ordered via native checkout and Cart flow using COD");
        ExtentTestManager.getTest().setDescription(testId + ": This test validates Payment Success page in case of payment polling when ordered via native checkout and Cart flow using COD");
        String email = "mayursavdekar@plobalapps.com";
        String password = "12345";
        selectStore(StoreNameEnum.SANDBOX);
        verifyHomepage();
        clickOnProductFromGrid();
        waitFor(2);
        verifyPdp();
        clickAddToCartOnPDP();
        clickOnGoToCart();
        verifyCartPage();
        clickOnPlaceOrder();
        waitFor(3);
        verifyCTAPopup();
        clickOnOtherButtonCTA();
        waitFor(3);
        clickLoginButtonOnNativeCheckout();
        waitFor(2);
        verifyLoginPage();
        enterEmailID(email);
        enterPassword(password);
        clickLogin();
        waitFor(5);
        clickOnContinueButtonOnCheckoutPage();
        waitFor(3);
        if (isPlatformNameAndroid) {
            waitFor(5);
            scrollToText("Cash on Delivery (COD)");
        } else {
            waitFor(5);
            scrollForIos();
        }
        clickOnCodButtonOnWebview();
        clickOnCompleteOrderButtonOnWebview();
        if (isPlatformNameAndroid) {
            waitFor(3);
            mobileDriver.terminateApp(ReadConfig.getPackageName());
            waitFor(9);
            launchAndroidApp(ReadConfig.getPackageName(), ReadConfig.getActvityName());
        } else {
            waitFor(2);
            terminateiOSApp();
            waitFor(9);
            mobileDriver.activateApp(ReadConfig.getBundleId());
        }
        waitFor(3);
        isOrderConfirmationPageDisplayed();
    }

    @Test(priority = 0, groups = {"sandbox", "regression", "iOSAppReset"})
    public void verifyRedirectionAfterClickingOnContinueShoppingButtonOnOrderConfirmationPage() {
        testId = "MAB-T1087";
        logger.info(testId + ": This test validates redirection after clicking on continue shopping button on order confirmation page");
        ExtentTestManager.getTest().setDescription(testId + ": This test validates redirection after clicking on continue shopping button on order confirmation page");
        String email = "mayursavdekar@plobalapps.com";
        String password = "12345";
        selectStore(StoreNameEnum.SANDBOX);
        verifyHomepage();
        clickOnProductFromGrid();
        waitFor(2);
        verifyPdp();
        clickAddToCartOnPDP();
        clickOnGoToCart();
        verifyCartPage();
        clickOnPlaceOrder();
        waitFor(3);
        verifyCTAPopup();
        clickOnOtherButtonCTA();
        waitFor(3);
        clickLoginButtonOnNativeCheckout();
        waitFor(2);
        verifyLoginPage();
        enterEmailID(email);
        enterPassword(password);
        clickLogin();
        waitFor(5);
        clickOnContinueButtonOnCheckoutPage();
        waitFor(5);
        if (isPlatformNameAndroid) {
            if (!isPaymentPageIsDisplayedOnWebviewCheckoutPage()){
                clickContinueButtonOnInformationAndShippingPageOnWebviewCheckout();
            }
            scrollToText("Security code");
        } else {
            if (!isPaymentPageIsDisplayedOnWebviewCheckoutPage()){
                clickContinueButtonOnInformationAndShippingPageOnWebviewCheckout();
            }
            scrollDownToIOSElement(checkoutPage.getCardNumber());
        }
        waitFor(1);
        enterCardNumber("1");
        enterCardHolderName("test Plobal");
        enterCardExpDate("1234");
        enterCardCvv("123");
        if (!isPlatformNameAndroid) {
            clickDoneButtonOnIOSKeyboard();
        }
        if (isPlatformNameAndroid) {
            scrollToText("Pay now");
        } else {
            waitFor(2.5);
            swipeForIos("down");
        }
        clickOnPayNow();
        isOrderConfirmationPageDisplayed();
        if (isPlatformNameAndroid) {
            scrollToText("Contact Us");
        } else {
            scrollForIos();
        }
        clickOnContinueShopping();
        waitFor(2);
        verifyHomepage();
    }

    @Test(priority = 0, groups = {"sandbox","regression","iOSAppReset","iOSOnly"})
    public void verifyPaymentPollingThroughWebviewCheckout() {
        testId = "MAB-T1631";
        logger.info(testId + ": This test validates whether Payment success page is shown when payment on webview checkout page is done by user and user relaunches the app(payment polling)");
        ExtentTestManager.getTest().setDescription(testId + ": This test validates whether Payment success page is shown when payment on webview checkout page is done by user and user relaunches the app(payment polling)");
        String email = "mayursavdekar@plobalapps.com";
        String password = "12345";
        selectStore(StoreNameEnum.SANDBOX);
        verifyHomepage();
        clickOnProductFromGrid();
        waitFor(2);
        verifyPdp();
        clickAddToCartOnPDP();
        clickOnGoToCart();
        verifyCartPage();
        clickOnPlaceOrder();
        waitFor(3);
        verifyCTAPopup();
        clickOnOtherButtonCTA();
        waitFor(3);
        clickLoginButtonOnNativeCheckout();
        waitFor(2);
        verifyLoginPage();
        enterEmailID(email);
        enterPassword(password);
        clickLogin();
        waitFor(5);
        clickOnContinueButtonOnCheckoutPage();
        waitFor(5);
        if (isPlatformNameAndroid) {
            if (!isPaymentPageIsDisplayedOnWebviewCheckoutPage()){
                clickContinueButtonOnInformationAndShippingPageOnWebviewCheckout();
            }
            scrollToText("Security code");
        } else {
            if (!isPaymentPageIsDisplayedOnWebviewCheckoutPage()){
                clickContinueButtonOnInformationAndShippingPageOnWebviewCheckout();
            }
            scrollDownToIOSElement(checkoutPage.getCardNumber());
        }
        waitFor(1);
        enterCardNumber("1");
        waitFor(2);
        enterCardHolderName("test Plobal");
        enterCardExpDate("1234");
        enterCardCvv("123");
        if (!isPlatformNameAndroid) {
            clickDoneButtonOnIOSKeyboard();
        }
        if (isPlatformNameAndroid) {
            scrollToText("Pay now");
        } else {
            waitFor(2.5);
            swipeForIos("down");
        }
        clickOnPayNow();
        if (isPlatformNameAndroid) {
            waitFor(3);
            mobileDriver.terminateApp(ReadConfig.getPackageName());
            waitFor(9);
            launchAndroidApp(ReadConfig.getPackageName(),ReadConfig.getActvityName());
        } else {
            waitFor(2);
            terminateiOSApp();
            waitFor(9);
            mobileDriver.activateApp(ReadConfig.getBundleId());
        }
        waitFor(3);
        isOrderConfirmationPageDisplayed();
    }

    @Test(priority = 0, groups = {"sandbox", "regression", "iOSAppReset"})
    public void verifyOrderConfirmationThroughBuyNowPaymentPollingUsingCod() {
        testId = "MAB-T15182";
        logger.info(testId + ": This test validates Order confirmation page in case of payment polling when ordered via native checkout flow through buy now using Other payment mode ");
        ExtentTestManager.getTest().setDescription(testId + ": This test validates Order confirmation page in case of payment polling when ordered via native checkout flow through buy now using Other payment mode");
        String email = "priya.n@plobalapps.com";
        String password = "qwerty";
        selectStore(StoreNameEnum.SANDBOX);
        verifyHomepage();
        clickOnHamburgerButtonFromHomePage();
        clickOnLoginButtonFromHamburgerMenu();
        verifyLoginPage();
        enterEmailID(email);
        enterPassword(password);
        clickLogin();
        clickOnProductFromGrid();
        waitFor(5);
        verifyPdp();
        clickOnBuyNowFromPDP();
        verifyCTAPopup();
        clickOnOtherButtonCTA();
        waitFor(7);
        verifyNativeCheckoutPageForOtherPayments();
        if (isPlatformNameAndroid) {
            clickOnPlaceOrder();
            waitFor(1.7);
            try {
                scrollToText("Continue to shipping");
                clickOnContinueButtonOnWebview();
                waitFor(2);
                scrollToText("Continue to payment");
                clickOnContinueButtonOnWebview();
            } catch (Exception e) {
            }
            waitFor(2);
            scrollToText("Pay now");
            clickOnCodButtonOnWebview();
            clickOnCompleteOrderButtonOnWebview();
            waitFor(3.5);
            mobileDriver.terminateApp(ReadConfig.getPackageName());
            waitFor(13);
            launchAndroidApp(ReadConfig.getPackageName(), ReadConfig.getActvityName());
        } else {
            clickOnContinueButtonOnCheckoutPage();
            waitFor(2);
            scrollForIos();
            try {
                clickOnContinueButtonOnWebview();
                waitFor(2);
                scrollForIos();
                clickOnContinueButtonOnWebview();
                waitFor(6);
                scrollForIos();
            } catch (Exception e) {
            }
            clickOnCodButtonOnWebview();
            clickOnCompleteOrderButtonOnWebview();
            if (isPlatformNameAndroid) {
                waitFor(3);
                String package1 = ReadConfig.getPackageName();
                String actName = ReadConfig.getActvityName();
                mobileDriver.terminateApp(ReadConfig.getPackageName());
                waitFor(9);
                launchAndroidApp(package1, actName);
            } else {
                waitFor(2);
                terminateiOSApp();
                waitFor(9);
                mobileDriver.activateApp(ReadConfig.getBundleId());
            }
            waitFor(5);
            isOrderConfirmationPageDisplayed();
        }
    }

    @Test(priority = 0, groups = {"sandbox","regression","iOSAppReset"})
    public void verifyDetailsOnOrderConfirmationPage(){
        testId = "MAB-T24357";
        logger.info(testId + ": This test validate details on order confirmation page via webview checkout");
        ExtentTestManager.getTest().setDescription(testId + ":  This test validate details on order confirmation page via webview checkout");
        String email = "mayursavdekar@plobalapps.com";
        String password = "12345";
        selectStore(StoreNameEnum.SANDBOX);
        verifyHomepage();
        clickOnProductFromGrid();
        waitFor(2);
        verifyPdp();
        clickAddToCartOnPDP();
        clickOnGoToCart();
        waitFor(2);
        verifyCartPage();
        clickOnPlaceOrder();
        waitFor(3);
        verifyCTAPopup();
        clickOnOtherButtonCTA();
        waitFor(3);
        clickLoginButtonOnNativeCheckout();
        waitFor(2);
        verifyLoginPage();
        enterEmailID(email);
        enterPassword(password);
        clickLogin();
        waitFor(5);
        if (isPlatformNameAndroid){
            scrollToText("Order Details");
        }
        else{
            scrollForIos();
        }
        String totalAmountOnNativeCheckoutPage = getTotalAmountFromNativeCheckoutPage();
        if(isPlatformNameAndroid){
            clickOnContinueButtonOnCheckoutPage();
        }else {
            clickOnContinueButtonOnCheckoutPage();
        }
        waitFor(4);
        if (isPlatformNameAndroid) {
            if (!isPaymentPageIsDisplayedOnWebviewCheckoutPage()){
                clickContinueButtonOnInformationAndShippingPageOnWebviewCheckout();
            }
            scrollToText("Security code");
        } else {
            if (!isPaymentPageIsDisplayedOnWebviewCheckoutPage()){
                clickContinueButtonOnInformationAndShippingPageOnWebviewCheckout();
            }
            scrollDownToIOSElement(checkoutPage.getCardNumber());
        }
        waitFor(1);
        enterCardNumber("1");
        enterCardHolderName("test Plobal");
        enterCardExpDate("1234");
        enterCardCvv("123");
        if (!isPlatformNameAndroid) {
            clickDoneButtonOnIOSKeyboard();
        }
        if (isPlatformNameAndroid) {
            scrollToText("Pay now");
        } else {
            waitFor(2.5);
            swipeForIos("down");
        }
        clickOnPayNow();
        waitFor(10);
        verifyTotalPayableAmountOnOrderConfirmationPage(totalAmountOnNativeCheckoutPage);
        isOrderConfirmationPageDisplayed();
    }

    @Test(priority = 0, groups = {"neovo", "regression", "iOSAppReset"})
    public void verifyOrderPlacementForFreeProduct() {
        testId = "MAB-T24371";
        logger.info(testId + ": This test validates checkout page and order placement for a free product");
        ExtentTestManager.getTest().setDescription(testId + ": This test validates checkout page and order placement for a free product");
        String email = "priya.n@plobalapps.com";
        String password = "qwerty";
        clickNoButtonOnOnboardingScreen();
        verifyHomepage();
        clickOnHamburgerButtonFromHomePage();
        clickOnLoginButtonFromHamburgerMenu();
        verifyLoginPage();
        enterEmailID(email);
        enterPassword(password);
        clickLogin();
        verifyHomepage();
        clickOnListIcon();
        clickOnFashionCollection();
        clickFreeProductOnCollection();
        waitFor(3);
        verifyPdp();
        clickAddToCartOnPDP();
        clickOnGoToCart();
        verifyCartPage();
        checkTotalPayableForFreeProductOnCart();
        clickOnPlaceOrder();
        waitFor(3);
        if (isPlatformNameAndroid) {
            scrollToText("Continue to payment");
            clickOnContinueButtonOnWebview();
            waitFor(2);
            scrollToText("Billing address");
            checkFreePaymentMessageOnCheckout();
            clickOnCompleteOrderButtonOnWebview();
        } else {
            scrollForIos();
            try {
                clickOnContinueButtonOnWebview();
                waitFor(2);
                scrollForIos();
                clickOnContinueButtonOnWebview();
                waitFor(6);
                scrollForIos();
            } catch (Exception e) {
            }
            checkFreePaymentMessageOnCheckout();
            clickOnCompleteOrderButtonOnWebview();
            isOrderConfirmationPageDisplayed();
            if (isPlatformNameAndroid) {
                scrollToText("Hide Order Summary");
            }
            else{
                scrollUpForIos();
            }
            checkTotalPayableForFreeProductOnOrderConfirmationPage();
        }
    }
}