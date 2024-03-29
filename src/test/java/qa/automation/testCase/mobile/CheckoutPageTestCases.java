package qa.automation.testCase.mobile;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;
import qa.automation.base.MobileBaseTest;
import qa.automation.enums.StoreNameEnum;
import qa.automation.report.ExtentTestManager;

import static qa.automation.base.WebBaseTest.randomNum;

public class CheckoutPageTestCases extends MobileBaseTest {

    private static final Logger logger = Logger.getLogger(CheckoutPageTestCases.class);


    @Test(priority = 0, groups = {"neovo", "regression", "iOSFailure", "androidFailure", "iOSAppReset"})
    public void verifyThankyouPage() {
        String email = "adityanisal@plobalapps.com";
        String password = "qwerty";
        testId = "MAB-T1085";
        logger.info(testId + " Check Thank you page visibility after payment success");
        ExtentTestManager.getTest().setDescription(testId + " Check Thank you page visibility after payment success");
        clickNoButtonOnOnboardingScreen();
        verifyHomepage();
        clickOnHamburgerButtonFromHomePage();
        clickOnLoginButtonFromHamburgerMenu();
        enterEmailID(email);
        enterPassword(password);
        clickLogin();
        clickOnListIcon();
        clickOnFashionCollection();
        clickOnDressCollection();
        waitFor(1.4);
        verifyPlp();
        selectProductFromPLP();
        waitFor(1.4);
        verifyPdp();
        clickOnBuyNowFromPDP();
        if(isPlatformNameAndroid){
            clickOnConfirmBtn();
        }
        if (isPlatformNameAndroid) {
            scrollToText("Continue to shipping");
        } else {
            waitFor(2.5);
            swipeForIos("down");
            scrollForIos();
        }
        clickOnContinueButton();
        waitFor(5);
        if (isPlatformNameAndroid) {
            scrollToText("Continue to payment");
        }
        if (isPlatformNameAndroid) {
            scrollForIos();
        } else {
            waitFor(2.5);
            scrollDownToIOSElement(checkoutPage.getCardNumber());
        }
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
        waitFor(2);
        isOrderConfirmationPageDisplayed();
    }


    @Test(priority = 0, groups = {"neovo", "regression", "iOSFailure", "androidFailure", "iOSAppReset"})
    public void verifyOrderIDOnThankyouPage() {
        String email = "adityanisal@plobalapps.com";
        String password = "qwerty";
        testId = "MAB-T1086";
        logger.info(testId + " This testcases verifies that the order id on Payment success/thank you page");
        ExtentTestManager.getTest().setDescription(testId + " This testcases verifies that the order id on Payment success/thank you page");
        clickNoButtonOnOnboardingScreen();
        verifyHomepage();
        clickOnHamburgerButtonFromHomePage();
        clickOnLoginButtonFromHamburgerMenu();
        enterEmailID(email);
        enterPassword(password);
        clickLogin();
        clickOnListIcon();
        clickOnFashionCollection();
        clickOnDressCollection();
        verifyPlp();
        selectProductFromPLP();
        waitFor(1.4);
        verifyPdp();
        clickOnBuyNowFromPDP();
        if(isPlatformNameAndroid){
            clickOnConfirmBtn();
        }
        if (isPlatformNameAndroid) {
            scrollToText("Continue to shipping");
        } else {
            waitFor(2.5);
            swipeForIos("down");
            scrollForIos();
        }
        clickOnContinueButton();
        waitFor(5);
        if (isPlatformNameAndroid) {
            scrollToText("Continue to payment");
        }
        if (isPlatformNameAndroid) {
            scrollForIos();
        } else {
            waitFor(2.5);
            scrollDownToIOSElement(checkoutPage.getCardNumber());
        }
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
        waitFor(2);
        isOrderConfirmationPageDisplayed();
        waitFor(1.5);
        if (!isPlatformNameAndroid) {
            scrollUpForIos();
        }
        isOrderIdDisplayed();
    }


    @Test(priority = 0, groups = {"neovo", "regression", "iOSFailure", "androidFailure", "iOSAppReset"})
    public void VerifyNoShippingRate() {
        String email = "adityanisal@plobalapps.com";
        String password = "qwerty";
        String shippingMethod = "Free";
        testId = "MAB-T1073";
        logger.info(testId + " This testcases verifies that No shipping Rate available on checkout page for selected shipping address");
        ExtentTestManager.getTest().setDescription(testId + " This testcases verifies that No shipping Rate available on checkout page for selected shipping address");
        clickNoButtonOnOnboardingScreen();
        clickOnHamburgerButtonFromHomePage();
        clickOnLoginButtonFromHamburgerMenu();
        enterEmailID(email);
        enterPassword(password);
        clickLogin();
        clickOnListIcon();
        clickOnFashionCollection();
        clickOnDressCollection();
        waitFor(3);
        selectProductFromPLP();
        clickOnBuyNowFromPDP();
        if (isPlatformNameAndroid) {
            clickOnConfirm();
        }
        checkNoShippingRateOnCheckout(shippingMethod);
    }


    @Test(priority = 0, groups = {"neovo", "regression", "iOSFailure", "androidFailure", "iOSAppReset"})
    public void verifyLoginButtonOnWebviewCheckout() {
        testId = "MAB-T15578";
        logger.info(testId + " This testcases verifies that Guest flow->check 'Login' button appear if store has setup like accounts are optional or disable");
        ExtentTestManager.getTest().setDescription(testId + " This testcases verifies that Guest flow->check 'Login' button appear if store has setup like accounts are optional or disable");
        clickNoButtonOnOnboardingScreen();
        verifyHomepage();
        clickOnListIcon();
        clickOnFashionCollection();
        clickOnDressCollection();
        waitFor(3);
        selectProductFromPLP();
        clickOnBuyNowFromPDP();
        waitFor(5);
        if (isPlatformNameAndroid) {
            clickOnConfirm();
        }
        waitFor(7);
        checkLoginButtonOnWebviewCheckout();
    }


    @Test(priority = 0, groups = {"sandbox", "regression", "iOSOnly", "iOSAppReset"})
    public void verifyNativeCheckout() {
        String email = "adityanisal@plobalapps.com";
        String password = "qwerty";
        String creditNumber = "1";
        String firstName = "Leo";
        String lastName = "Messi";
        String date = "0826";
        String cvv = "456";
        testId = "MAB-T1606";
        logger.info(testId + ": This test validates checkout flow when native checkout is enabled");
        ExtentTestManager.getTest().setDescription(testId + ": This test validates checkout flow when native checkout is enabled");
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
        clickOnCreditCardCTA();
        checkNativeCheckoutPage();
        waitFor(5);
        if (isPlatformNameAndroid) {
            clickOnPaymentOptionOnCheckoutPage();
        } else {
            clickOnContinueButtonOnCheckoutPage();
        }
        enterCreditCardNumber(creditNumber);
        enterFirstNameOnCredit(firstName);
        enterLastNameOnCredit(lastName);
        enterDateOnCredit(date);
        enterCvvOnCredit(cvv);
        if (!isPlatformNameAndroid){
            clickDoneButtonOnIOSKeyboard();
    }
        clickOnPayNowButton();
        waitFor(7);
        verifyNewThankYouPage();
    }


    @Test(priority = 0, groups = {"neovo", "regression", "iOSFailure", "androidFailure", "iOSAppReset"})
    public void addCreditCartOnCheckoutPage() {
        testId = "MAB-T1078";
        logger.info(testId + ": This test validates add payment info(credit card) functionality on checkout page and observe behaviour");
        ExtentTestManager.getTest().setDescription(testId + ": This test validates add payment info(credit card) functionality on checkout page and observe behaviour");
        clickNoButtonOnOnboardingScreen();
        verifyHomepage();
        clickOnListIcon();
        clickOnFashionCollection();
        clickOnDressCollection();
        waitFor(3);
        verifyPlp();
        selectProductFromPLP();
        verifyPdp();
        clickOnBuyNowFromPDP();
        waitFor(10);
        if (isPlatformNameAndroid) {
            clickOnConfirm();
        }
        waitFor(8.5);
        clickLoginButtonOnWebviewCheckout();
        isLoginPageDisplayed();
        enterEmailID("plobaltest121@yopmail.com");
        enterPassword("test1234");
        clickLogin();
        waitFor(1.5);
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
            scrollDownToIOSElement(checkoutPage.getCardNumber());
        }
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
        waitFor(2);
        isOrderConfirmationPageDisplayed();
    }


    @Test(priority = 0, groups = {"neovo", "regression", "iOSFailure", "androidFailure", "iOSAppReset"})
    public void verifyWebviewCodPayment() {
        testId = "MAB-T1083";
        logger.info(testId + " This test validates payment through cod(webview)");
        ExtentTestManager.getTest().setDescription(testId + " This test validates payment through cod(webview)");
        clickNoButtonOnOnboardingScreen();
        verifyHomepage();
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
        checkItemCount("1");
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

    @Test(priority = 0, groups = {"neovo", "regression", "iOSFailure", "androidFailure", "iOSAppReset"})
    public void verifyUserAbletoApplyDiscountonCheckoutPage() {
        String DiscountCode = "AUTOMATE1";
        testId = "MAB-T1068";
        logger.info(testId + " This test validates that User should be Able to Add Discount on Checkout Page");
        ExtentTestManager.getTest().setDescription(testId + " This test validates that User should be Able to Add Discount on Checkout Page");
        clickNoButtonOnOnboardingScreen();
        verifyHomepage();
        clickOnListIcon();
        clickOnFashionCollection();
        clickOnDressCollection();
        waitFor(3);
        verifyPlp();
        selectProductFromPLP();
        verifyPdp();
        clickAddToCartOnPDP();
        if (isPlatformNameAndroid) {
            clickOnConfirm();
        }
        clickCartIconOnPdpPage();
        waitFor(1.5);
        clickOnPlaceOrder();
        waitFor(10);
        clickOnOrderSummary();
        applyDiscountOrGiftCardOnCheckoutPage(DiscountCode);
        clickArrowToApplyDiscountCode();
        waitFor(7);
        verifyDiscountAppliedOnCheckoutPage(DiscountCode);
    }

    @Test(priority = 0, groups = {"neovo", "regression", "iOSFailure", "androidFailure", "iOSAppReset"})
    public void verifyUserAbletoApplyGiftCardOnCheckoutPage() {
        String GiftCardCode = "q66cxtxtrky7wmcd";
        testId = "MAB-T14598";
        logger.info(testId + " This test validates that User should be Able to Add Gift Card Checkout Page");
        ExtentTestManager.getTest().setDescription(testId + " This test validates that User should be Able to Add Gift Card on Checkout Page");
        clickNoButtonOnOnboardingScreen();
        verifyHomepage();
        clickOnListIcon();
        clickOnFashionCollection();
        clickOnDressCollection();
        waitFor(3);
        verifyPlp();
        selectProductFromPLP();
        verifyPdp();
        clickAddToCartOnPDP();
        if (isPlatformNameAndroid) {
            clickOnConfirm();
        }
        clickCartIconOnPdpPage();
        waitFor(1.5);
        clickOnPlaceOrder();
        waitFor(10);
        clickOnOrderSummary();
        waitFor(3);
        applyDiscountOrGiftCardOnCheckoutPage(GiftCardCode);
        clickArrowToApplyDiscountCode();
        waitFor(7);
        verifyGiftCardVisibleOnCheckoutPage();
    }

    @Test(priority = 0, groups = {"neovo", "regression", "iOSFailure", "androidFailure", "iOSAppReset"})
    public void verifyUserAbleToRemoveDiscountOnCheckoutPage() {
        String DiscountCode = "AUTOMATE1";
        testId = "MAB-T14741";
        logger.info(testId + " This test validates that User should be Able to Remove Discount on Checkout Page");
        ExtentTestManager.getTest().setDescription(testId + " This test validates that User should be Able to Remove Discount on Checkout Page");
        clickNoButtonOnOnboardingScreen();
        verifyHomepage();
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
        waitFor(7);
        clickOnOrderSummary();
        applyDiscountOrGiftCardOnCheckoutPage(DiscountCode);
        clickArrowToApplyDiscountCode();
        waitFor(3);
        isDiscountCodeDisplayed(1);
        waitFor(5);
        verifyDiscountAppliedOnCheckoutPage(DiscountCode);
        waitFor(3);
        clickOnDiscountRemoveButtonOnCheckoutPage();
        waitFor(3);
        isDiscountCodeDisplayed(0);
    }

    @Test(priority = 0, groups = {"sandbox", "regression", "iOSAppReset"})
    public void verifyShippingDiscountOnCheckoutPage() {
        String email = "adityanisal@plobalapps.com";
        String password = "qwerty";
        String discount = "shippingautomate";
        testId = "MAB-T1606";
        logger.info(testId + ": This test validates shipping rate visibilty after applying discount on checkout page");
        ExtentTestManager.getTest().setDescription(testId + ": This test validates shipping rate visibilty after applying discount on checkout page");
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
        waitFor(2);
        clickOnGoToCart();
        verifyCartPage();
        waitFor(5);
        clickOnDiscountOnCartPage();
        checkDiscountPopupOnCartPage();
        enterDiscountOnPopupOnCartPage(discount);
        clickOnApplyCouponButtonOnCartPage();
        waitFor(10);
        clickOnPlaceOrder();
        verifyCTAPopup();
        clickOnCreditCardCTA();
        checkNativeCheckoutPage();
        checkFreeShippingChargeDiscountAppliedOnCheckoutPage();
    }

    @Test(priority = 0, groups = {"sandbox", "regression", "iOSAppReset"})
    public void verifyNewlyAddedBillingAddressOnCheckoutPage() {
        String email = "adityanisal@plobalapps.com";
        String password = "qwerty";
        testId = "MAB-T1077";
        logger.info(testId + ": This test validates Billing Address functionality on Checkout page");
        ExtentTestManager.getTest().setDescription(testId + ": This test validates Billing Address functionality on Checkout page");
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
        waitFor(2);
        clickOnGoToCart();
        verifyCartPage();
        waitFor(5);
        clickOnPlaceOrder();
        verifyCTAPopup();
        clickOnCreditCardCTA();
        checkNativeCheckoutPage();
        clickOnAddAddressPlusIconOnCheckoutPage();
        clickAddNewAddressButton();
        waitFor(3);
        enterFirstnameOnAddressPage("Plobal");
        String lname1 = "Test" + randomNum();
        enterLastnameOnAddressPage(lname1);
        enterCompanyNameOnAddressPage("Plobal");
        enterContactNumberOnAddressPage("91" + randomNum() + randomNum());
        enterAddressLine1OnAddressPage("Bavdhan");
        enterAddressLine2OnAddressPage("Sai Velocity");
        enterStateOnAddressPage("Maharashtra");
        enterCityOnAddressPage("Pune");
        enterZipcodeOnAddressPage("411021");
        waitFor(1);
        mobileDriver.hideKeyboard();
        clickSaveButtonOnAddAddressPage();
        if (isPlatformNameAndroid) {
            clickOnOkPopup();
        }
        waitFor(1);
        isAddressNameDisplayedOnAddressPage("Plobal " + lname1);
    }

    @Test(groups = {"sandbox", "regression", "iOSAppReset"})
    public void verifyAutomaticDiscountOnCheckOutPage() {
        testId = "MAB-T1070";
        logger.info(testId + " This test validates Automatic discount functionality on checkout page");
        ExtentTestManager.getTest().setDescription(testId + " This test validates Automatic discount functionality on checkout page");
        selectStore(StoreNameEnum.SANDBOX);
        verifyHomepage();
        clickOnAutomaticDiscountProduct();
        verifyPdp();
        clickAddToCartOnPDP();
        waitFor(2);
        if (isPlatformNameAndroid) {
            backBtn();
        } else {
            clickBackArrowOnNativeApp();
        }
        clickOnProductFromGrid();
        verifyPdp();
        clickAddToCartOnPDP();
        waitFor(3);
        clickOnGoToCart();
        waitFor(2);
        checkAutomaticDiscountOnCartPage();
        clickOnPlaceOrder();
        verifyCTAPopup();
        clickOnCreditCardCTA();
        waitFor(2);
        checkAutomaticDiscountOnCheckoutPage();
    }

    @Test(priority = 0, groups = {"sandbox", "regression", "iOSAppReset"})
    public void verifyPaymentValueAfterApplyingDiscountCode() {
        testId = "MAB-T24359";
        String email = "mayursavdekar@plobalapps.com";
        String password = "12345";
        String priceBeforeApplyingDiscount = "₹1,437.85";
        String priceAfterApplyingDiscount = "₹1,429.85";
        String discountCode = "BigSale";
        logger.info(testId + ": This test will validate payment value on checkout page after applying discount code");
        ExtentTestManager.getTest().setDescription(testId + ": This test will validate payment value on checkout page after applying discount code");
        selectStore(StoreNameEnum.SANDBOX);
        verifyHomepage();
        clickOnHamburgerButtonFromHomePage();
        clickOnLoginButtonFromHamburgerMenu();
        waitFor(2);
        verifyLoginPage();
        enterEmailID(email);
        enterPassword(password);
        clickLogin();
        clickOnProductFromGrid();
        waitFor(2);
        verifyPdp();
        clickAddToCartOnPDP();
        clickOnGoToCart();
        waitFor(3);
        verifyCartPage();
        clickOnPlaceOrder();
        waitFor(3);
        verifyCTAPopup();
        clickOnOtherButtonCTA();
        waitFor(5);
        clickOnContinueButtonOnCheckoutPage();
        waitFor(6);
        verifyPaymentValueOnOnWebviewCheckout(priceBeforeApplyingDiscount);
        applyDiscountOrGiftCardOnCheckoutPage(discountCode);
        clickArrowToApplyDiscountCode();
        waitFor(3);
        if (isPlatformNameAndroid) {
            scrollToText("plobal-sandbox-test");
        } else {
            scrollUpForIos();
        }
        verifyPaymentValueOnOnWebviewCheckout(priceAfterApplyingDiscount);
    }

    @Test(priority = 0, groups = {"sandbox", "regression", "iOSAppReset"})
    public void verifyPaymentValueAfterApplyingGiftCard() {
        testId = "MAB-T24360";
        String email = "mayursavdekar@plobalapps.com";
        String password = "12345";
        String priceBeforeApplyingGiftCard = "₹1,437.85";
        String priceAfterApplyingGiftCard = "₹1,337.85";
        String giftCard = "8rgh 8qjj whfx dvgx";
        logger.info(testId + ": This test will validate payment value on checkout page after applying Gift Card");
        ExtentTestManager.getTest().setDescription(testId + ": This test will validate payment value on checkout page after applying Gift Card");
        selectStore(StoreNameEnum.SANDBOX);
        verifyHomepage();
        clickOnHamburgerButtonFromHomePage();
        clickOnLoginButtonFromHamburgerMenu();
        waitFor(2);
        verifyLoginPage();
        enterEmailID(email);
        enterPassword(password);
        clickLogin();
        clickOnProductFromGrid();
        waitFor(3);
        verifyPdp();
        clickAddToCartOnPDP();
        clickOnGoToCart();
        waitFor(3);
        verifyCartPage();
        clickOnPlaceOrder();
        waitFor(5);
        verifyCTAPopup();
        clickOnOtherButtonCTA();
        waitFor(5);
        clickOnContinueButtonOnCheckoutPage();
        waitFor(6);
        verifyPaymentValueOnOnWebviewCheckout(priceBeforeApplyingGiftCard);
        applyDiscountOrGiftCardOnCheckoutPage(giftCard);
        clickArrowToApplyDiscountCode();
        waitFor(3);
        if (isPlatformNameAndroid) {
            scrollToText("plobal-sandbox-test");
        } else {
            scrollUpForIos();
        }
        verifyPaymentValueOnOnWebviewCheckout(priceAfterApplyingGiftCard);
    }

    @Test(priority = 0, groups = {"sandbox", "regression", "iOSAppReset"})
    public void verifyPaymentValueAfterEndingCheckoutAndAgainAddingMoreItems() {
        testId = "MAB-T24358";
        String email = "mayursavdekar@plobalapps.com";
        String password = "12345";
        String price_1;
        String price_2;
        logger.info(testId + ": This test will validate payment value by ending checkout and adding some more products and then again going to checkout");
        ExtentTestManager.getTest().setDescription(testId + ": This test will validate payment value by ending checkout and adding some more products and then again going to checkout");
        selectStore(StoreNameEnum.SANDBOX);
        verifyHomepage();
        clickOnHamburgerButtonFromHomePage();
        clickOnLoginButtonFromHamburgerMenu();
        waitFor(2);
        verifyLoginPage();
        enterEmailID(email);
        enterPassword(password);
        clickLogin();
        clickOnProductFromGrid();
        waitFor(3);
        verifyPdp();
        clickAddToCartOnPDP();
        clickOnGoToCart();
        waitFor(3);
        verifyCartPage();
        clickOnPlaceOrder();
        waitFor(5);
        verifyCTAPopup();
        clickOnOtherButtonCTA();
        waitFor(5);
        if (isPlatformNameAndroid) {
            scrollToText("Total");
        } else {
            scrollForIos();
        }
        price_1 = getTotalAmountFromNativeCheckoutPage();
        clickOnContinueButtonOnCheckoutPage();
        waitFor(5);
        verifyPaymentValueOnOnWebviewCheckout(price_1);
        clickOnCheckoutBackButton();
        waitFor(3);
        if (isPlatformNameAndroid) {
            clickYesOnPopup();
        }
        waitFor(3);
        if (isPlatformNameAndroid) {
            clickBackButtonIconOnTopLeft();
        } else {
            clickOnCheckoutBackButton();
        }
        waitFor(3);
        verifyCartPage();
        if (isPlatformNameAndroid) {
            clickBackButtonIconOnTopLeft();
        } else {
            clickOnBackButtonFromCartPage();
        }
        waitFor(2);
        verifyPdp();
        clickBackButtonIconOnTopLeft();
        waitFor(2);
        verifyHomepage();
        clickProductBannerOnHomePage();
        waitFor(2);
        verifyPdp();
        clickAddToCartOnPDP();
        clickOnGoToCart();
        waitFor(3);
        verifyCartPage();
        clickOnPlaceOrder();
        waitFor(5);
        verifyCTAPopup();
        clickOnOtherButtonCTA();
        waitFor(5);
        if (isPlatformNameAndroid) {
            scrollToText("Total");
        } else {
            scrollForIos();
        }
        price_2 = getTotalAmountFromNativeCheckoutPage();
        clickOnContinueButtonOnCheckoutPage();
        waitFor(5);
        verifyPaymentValueOnOnWebviewCheckout(price_2);
    }

    @Test(priority = 0, groups = {"sandbox", "regression", "iOSAppReset"})
    public void verifyDiscountOnWebviewCheckoutWhichIsAppliedOnCartPage() {
        testId = "MAB-T11371";
        String discountCode = "messi";
        String email = "adityanisal@plobalapps.com";
        String password = "qwerty";
        logger.info(testId + " :This testcases verifies whether discount Applied on native checkout page should appear on webview checkout page(Other Payment Mode)");
        ExtentTestManager.getTest().setDescription(testId + " :This testcases verifies whether discount Applied on native checkout page should appear on webview checkout page(Other Payment Mode)");
        selectStore(StoreNameEnum.SANDBOX);
        verifyHomepage();
        clickOnProductFromGrid();
        verifyPdp();
        clickAddToCartOnPDP();
        waitFor(2);
        clickOnGoToCart();
        waitFor(2);
        verifyCartPage();
        clickOnDiscountOnCartPage();
        checkDiscountPopupOnCartPage();
        enterDiscountOnPopupOnCartPage(discountCode);
        clickOnApplyCouponButtonOnCartPage();
        checkDiscountAplliedOnCartPage();
        waitFor(3);
        clickOnPlaceOrder();
        waitFor(5);
        verifyCTAPopup();
        clickOnOtherButtonCTA();
        waitFor(5);
        waitFor(3);
        clickLoginButtonOnNativeCheckout();
        waitFor(2);
        verifyLoginPage();
        enterEmailID(email);
        enterPassword(password);
        clickLogin();
        waitFor(5);
        clickOnContinueButtonOnCheckoutPage();
        waitFor(10);
        clickOnOrderSummary();
        waitFor(7);
        verifyDiscountOnWebviewcheckout();
    }

    public void verifyScriptDiscountOnCheckoutPage() {
        testId = "MAB-T1071";
        String email = "mayursavdekar@plobalapps.com";
        String password = "12345";
        logger.info(testId + ": This test will validate Script discount on webview checkout page");
        ExtentTestManager.getTest().setDescription(testId + ": This test will validate Script discount on webview checkout page");
        selectStore(StoreNameEnum.SANDBOX);
        verifyHomepage();
        clickOnSearchIcon();
        waitFor(2);
        enterTextOnSearchboxOnCollectionPage("script");
        waitFor(2);
        clickFirstProductOnSearchResultPage();
        verifyPdp();
        clickAddToCartOnPDP();
        clickOnGoToCart();
        waitFor(3);
        verifyCartPage();
        String text = fetchAutomaticDisocuntTextFromCartpage();
        clickOnPlaceOrder();
        waitFor(3);
        verifyCTAPopup();
        clickOnOtherButtonCTA();
        waitFor(3);
        clickLoginButtonOnNativeCheckout();
        waitFor(3);
        verifyLoginPage();
        enterEmailID(email);
        enterPassword(password);
        clickLogin();
        waitFor(5);
        clickOnContinueButtonOnCheckoutPage();
        waitFor(6);
        clickOnOrderSummary();
        verifyDiscountTextUnderShowOrderSummary(text);
    }

    @Test(priority = 0, groups = {"sandbox", "regression", "iOSAppReset"})
    public void verifyAddingNewAddressOnCheckout() {
        String email5 = "shashikant.m@plobalapps.com";
        String password5 = "123456";
        String Fname = "Shashi" + randomString(2);
        String Lname = "Moray" + randomString(3);
        String Company = "Plobal";
        String contact = "8237997505";
        String Address1 = "Rahatani";
        String Address2 = "Shree Velocity";
        String country = "India";
        String State = "Maharashtra";
        String City = "Pune";
        String Zipcode = "411017";
        testId = "MAB-T1077";
        logger.info(testId + ": This test validates adding new address on Checkout");
        ExtentTestManager.getTest().setDescription(testId + ": This test validates adding new address on Checkout");
        selectStore(StoreNameEnum.SANDBOX);
        verifyHomepage();
        clickOnHamburgerButtonFromHomePage();
        clickOnLoginButtonFromHamburgerMenu();
        verifyLoginPage();
        enterEmailID(email5);
        enterPassword(password5);
        clickLogin();
        waitFor(2);
        clickOnHamburgerButtonFromHomePage();
        clickOnMyProfileFromHamburger();
        clickAddressFeatureOnProfilePage();
        clickOnRemoveButtonFromProfilePage();
        if (isPlatformNameAndroid) {
            clickYesOnPopup();
        }
        waitFor(2);
        closeAddressPopup();
        waitFor(2);
        clickOnHamburgerButtonFromHomePage();
        waitFor(2);
        clickOnHomeButtonFromHamburger();
        clickOnProductFromGrid();
        clickAddToCartOnPDP();
        waitFor(2);
        clickOnGoToCart();
        verifyCartPage();
        clickOnPlaceOrder();
        verifyCTAPopup();
        clickOnCreditCardCTA();
        waitFor(3);
        clickOnAddAddressPlusIconOnCheckoutPage();
        enterFirstnameOnAddressPage(Fname);
        enterLastnameOnAddressPage(Lname);
        enterCompanyNameOnAddressPage(Company);
        enterContactNumberOnAddressPage(contact);
        enterAddressLine1OnAddressPage(Address1);
        enterAddressLine2OnAddressPage(Address2);
        if (isPlatformNameAndroid) {
            clickOnCountry();
            scrollToText("India");
            selectCountry();
        }
        waitFor(3);
        enterStateOnAddressPage(State);
        enterCityOnAddressPage(City);
        enterZipcodeOnAddressPage(Zipcode);
        mobileDriver.hideKeyboard();
        clickSaveButtonOnAddressPageCheckoutPage();
    }

    @Test(priority = 0, groups = {"sandbox", "regression", "iOSAppReset"})
    public void verifyPaymentFailedOnCheckoutPage() {
        String email9 = "shashikant.m@plobalapps.com";
        String password9 = "123456";
        String creditNumber = "1";
        String firstName = "Shashi";
        String lastName = "Moray";
        String date = "10/27";
        String dateiOS = "1027";
        String cvv = "1239";
        testId = "MAB-T1088";
        logger.info(testId + ": This test validates payment failed on Checkout");
        ExtentTestManager.getTest().setDescription(testId + ": This test validates payment failed on Checkout");
        selectStore(StoreNameEnum.SANDBOX);
        verifyHomepage();
        clickOnHamburgerButtonFromHomePage();
        clickOnLoginButtonFromHamburgerMenu();
        verifyLoginPage();
        enterEmailID(email9);
        enterPassword(password9);
        clickLogin();
        waitFor(2);
        clickOnProductFromGrid();
        clickAddToCartOnPDP();
        waitFor(2);
        clickOnGoToCart();
        verifyCartPage();
        clickOnPlaceOrder();
        verifyCTAPopup();
        clickOnCreditCardCTA();
        checkNativeCheckoutPage();
        clickOnPlusButtonForPaymentOnCheckoutPage();
        waitFor(5);
        enterCreditCardNumber(creditNumber);
        enterFirstNameOnCredit(firstName);
        enterLastNameOnCredit(lastName);
        if (isPlatformNameAndroid) {
            enterDateOnCredit(date);
        } else {
            enterDateOnCredit(dateiOS);
        }
        enterCvvOnCredit(cvv);
        clickOnAddPaymentButton();
        waitFor(5);
        if (isPlatformNameAndroid) {
            clickOnPlaceOrder();
        } else {
            clickOnContinueButtonForPlaceOrder();
        }
        waitFor(3);
        if (isPlatformNameAndroid) {
            checkAlertMessageForInvalidCreditCard();
        } else {
            checkNativeCheckoutPage();
        }
    }

    /**
     * TODO: Checkout is disabled for neovo store , once its enabled will complete this testacse
     * Partially Automated
     */
    @Test(priority = 0, groups = {"neovo", "regression", "iOSFailure", "androidFailure", "iOSAppReset"})
    public void verifyMultiCurrencyOnCheckoutPage() {
        testId = "MAB-T20929";
        logger.info(testId + " This test validates Multi currency behavior  on Checkout Page");
        ExtentTestManager.getTest().setDescription(testId + " This test validates that Multi currency behavior on Checkout Page");
        clickNoButtonOnOnboardingScreen();
        verifyHomepage();
        clickOnListIcon();
        clickOnFashionCollection();
        clickOnDressCollection();
        waitFor(3);
        verifyPlp();
        selectProductFromPLP();
        verifyPdp();
        clickAddToCartOnPDP();
        if (isPlatformNameAndroid) {
            clickOnConfirm();
        }
        clickCartIconOnPdpPage();
        if (isPlatformNameAndroid) {
            clickBackButtonIconOnTopLeft();
        } else {
            clickOnBackButtonFromCartPage();
        }
        waitFor(2);
        clickBackButtonIconOnTopLeft();
        waitFor(0.5);
        clickBackButtonIconOnTopLeft();
        waitFor(0.5);
        clickBackButtonIconOnTopLeft();
        waitFor(0.5);
        clickMoreTabOnHomePage();
        verifyMorePage();
        waitFor(7);
        clickOnCurrency();
        waitFor(2);
        selectUSD();
        waitFor(2);
        clickCartTabOnHomePage();
        verifyCartPage();
        isSelectedCurrencyDisplayedOnCart("$");

    }

    /**
     * TODO: Checkout is disabled for neovo store , once its enabled will complete this testacse
     * Partially Automated
     */
    @Test(priority = 0, groups = {"neovo", "regression", "iOSAppReset"})
    public void verifyOnClickOfLoginLinkFromcheckout() {
        testId = "MAB-T24379";
        logger.info(testId + ": This test Verifies if user click on 'Login' link from the checkout it redirect to the login page");
        ExtentTestManager.getTest().setDescription(testId + ": This test Verifies if user click on 'Login' link from the checkout it redirect to the login page");
        clickNoButtonOnOnboardingScreen();
        verifyHomepage();
        clickOnListIcon();
        clickOnFashionCollection();
        clickOnDressCollection();
        verifyPlp();
        clickFirstProductFromPLP();
        verifyPdp();
        clickAddToCartOnPDP();
        if (isPlatformNameAndroid) {
            clickOnConfirm();
        }
        clickBackButtonIconOnTopLeft();
        waitFor(2);
        clickBackButtonIconOnTopLeft();
        waitFor(2);
        clickBackButtonIconOnTopLeft();
        waitFor(2);
        clickOnCartIconOnPlP();
        clickOnPlaceOrder();
    }

    @Test(priority = 0, groups = {"sandbox", "regression", "iOSAppReset"})
    public void verifyTheCalculationOnWebviewCheckout() {
        testId = "MAB-T37895";
        logger.info(testId + ": This test Verify total payable on webview checkout page when discount is applied on cart");
        ExtentTestManager.getTest().setDescription(testId + ": This test Verifies Verify total payable on webview checkout page when discount is applied on cart");
        selectStore(StoreNameEnum.SANDBOX);
        verifyHomepage();
        clickOnProductFromGrid();
        verifyPdp();
        clickAddToCartOnPDP();
        waitFor(2);
        clickOnGoToCart();
        waitFor(2);
        verifyCartPage();
        waitFor(1);
        clickOnDiscountOnCartPage();
        enterDiscountOnPopupOnCartPage("test12345");
        clickOnApplyCouponButtonOnCartPage();
        checkDiscountAplliedOnCartPage();
        clickOnPlaceOrder();
        clickOnOtherButtonCTA();
        clickLoginButtonOnNativeCheckout();
        enterEmailID("vrushali1@gmail.com");
        enterPassword("test123");
        clickLogin();
        waitFor(10);
        if (isPlatformNameAndroid) {
            clickOnPlaceOrder();
        } else {
            clickOnContinueButtonOnCheckoutPage();
        }
        waitFor(5);
        String subtotal = fetchSubtotalFromCheckoutPage();
        String discount = fetchDiscountAmountFromCheckoutPage();
        waitFor(2);
        verifiyTotalPaybleFromCheckoutPage(subtotal, discount);
    }

    @Test(priority = 0, groups = {"sandbox", "regression", "iOSAppReset"})
    public void verifyDiscountAppliedOnCheckoutIsReflectedOnCart() {
        testId = "MAB-T24374";
        String email = "priya.n@plobalapps.com";
        String password = "qwerty";
        String discount = "TEST12345";
        logger.info(testId + ": This test validates if the discount code applied on checkout is reflected on cart");
        ExtentTestManager.getTest().setDescription(testId + ": This test validates if the discount code applied on checkout is reflected on cart");
        selectStore(StoreNameEnum.SANDBOX);
        verifyHomepage();
        clickOnHamburgerButtonFromHomePage();
        clickOnLoginButtonFromHamburgerMenu();
        waitFor(2);
        verifyLoginPage();
        enterEmailID(email);
        enterPassword(password);
        clickLogin();
        verifyHomepage();
        clickOnProductFromGrid();
        waitFor(2);
        verifyPdp();
        clickAddToCartOnPDP();
        clickOnGoToCart();
        waitFor(3);
        verifyCartPage();
        clickOnPlaceOrder();
        waitFor(3);
        verifyCTAPopup();
        clickOnOtherButtonCTA();
        waitFor(5);
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
            scrollToText("Gift card or discount code");
        } else {
            clickOnContinueButtonOnCheckoutPage();
            waitFor(2);
            scrollForIos();
            try {
                clickOnContinueButtonOnWebview();
                waitFor(2);
                scrollForIos();
                clickOnContinueButtonOnWebview();
            } catch (Exception e) {
            }
            scrollUpForIos();
        }
        applyDiscountOrGiftCardOnCheckoutPage(discount);
        clickArrowToApplyDiscountCode();
        waitFor(2);
        checkDiscountCodeOnCheckoutPage(discount);
        clickOnCheckoutBackButton();
        waitFor(3);
        if (isPlatformNameAndroid) {
            clickYesOnPopup();
        }
        waitFor(3);
        if (isPlatformNameAndroid) {
            clickBackButtonIconOnTopLeft();
        } else {
            clickOnCheckoutBackButton();
        }
        waitFor(5);
        checkDiscountAplliedOnCartPage();
    }

    @Test(priority = 0, groups = {"sandbox", "regression", "iOSAppReset"})
    public void checkAmountOffAutomaticDiscount() {
        testId = "MAB-T11405";
        String email = "mayursavdekar@plobalapps.com";
        String password = "12345";
        logger.info(testId + ": This test will validate amount off automatic discount on cart and checkout page");
        ExtentTestManager.getTest().setDescription(testId + ": This test will validate amount off automatic discount on cart and checkout page");
        selectStore(StoreNameEnum.SANDBOX);
        verifyHomepage();
        clickOnSearchIcon();
        waitFor(2);
        enterTextOnSearchboxOnCollectionPage("amount");
        waitFor(2);
        clickFirstProductOnSearchResultPage();
        verifyPdp();
        clickAddToCartOnPDP();
        clickOnGoToCart();
        waitFor(3);
        verifyCartPage();
        String automaticDiscountText;
        if (isPlatformNameAndroid) {
            automaticDiscountText = fetchAmountOffDiscountTextFromCartPage();
        } else {
            automaticDiscountText = fetchAmountOffDiscountTextFromCartPage().substring(0, 34);

        }
        clickOnPlaceOrder();
        waitFor(3);
        verifyCTAPopup();
        clickOnOtherButtonCTA();
        waitFor(3);
        clickLoginButtonOnNativeCheckout();
        waitFor(3);
        verifyLoginPage();
        enterEmailID(email);
        enterPassword(password);
        clickLogin();
        waitFor(5);
        clickOnContinueButtonOnCheckoutPage();
        waitFor(6);
        clickOnOrderSummary();
        verifyDiscountTextUnderShowOrderSummary(automaticDiscountText);
    }

    @Test(priority = 0, groups = {"neovo", "regression", "iOSAppReset"})
    public void verifyUserAbletoRemoveGiftCardOnCheckoutPage() {
        String GiftCardCode = "q66cxtxtrky7wmcd";
        testId = "MAB-T24370";
        logger.info(testId + " This test validates that User should be Able to remove Gift Card Checkout Page");
        ExtentTestManager.getTest().setDescription(testId + " This test validates that User should be Able to Remove Gift Card on Checkout Page");
        clickNoButtonOnOnboardingScreen();
        verifyHomepage();
        clickOnListIcon();
        clickOnFashionCollection();
        clickOnDressCollection();
        waitFor(3);
        verifyPlp();
        selectProductFromPLP();
        verifyPdp();
        clickAddToCartOnPDP();
        if (isPlatformNameAndroid) {
            clickOnConfirm();
        }
        clickCartIconOnPdpPage();
        waitFor(1.5);
        clickOnPlaceOrder();
        waitFor(9);
        clickOnOrderSummary();
        waitFor(3);
        applyDiscountOrGiftCardOnCheckoutPage(GiftCardCode);
        clickArrowToApplyDiscountCode();
        waitFor(7);
        verifyGiftCardVisibleOnCheckoutPage();
        clickOnDiscountRemoveButtonOnCheckoutPage();
        waitFor(4);
        verifyGiftLabelNotDisplayed();
    }

    @Test(priority = 0, groups = {"neovo", "regression", "iOSAppReset"})
    public void verifyOrderSummarySectionIsVisible() {
        testId = "MAB-T14598";
        logger.info(testId + " This test validates that User able to verify order section on checkout page");
        ExtentTestManager.getTest().setDescription(testId + " This test validates that User able to verify order section on checkout page");
        clickNoButtonOnOnboardingScreen();
        verifyHomepage();
        clickOnListIcon();
        clickOnFashionCollection();
        clickOnDressCollection();
        waitFor(3);
        verifyPlp();
        selectProductFromPLP();
        verifyPdp();
        clickAddToCartOnPDP();
        if (isPlatformNameAndroid) {
            clickOnConfirm();
        }
        clickCartIconOnPdpPage();
        waitFor(1.5);
        clickOnPlaceOrder();
        waitFor(10);
        clickOnOrderSummary();
        waitFor(3);
        checkOrderSectionIsVisible();
    }

    @Test(priority = 0, groups = {"neovo", "regression", "iOSAppReset"})
    public void verifyUserAbletoApplyInAppDiscountOnCheckoutPage() {
        String discountCode = "AppBanner";
        String discountCodeSuffix = "APPBANNER-APP-*****";
        testId = "MAB-T24368";
        logger.info(testId + " This test validates that User should be Able to Apply App-Discount on Checkout Page");
        ExtentTestManager.getTest().setDescription(testId + " This test validates that User should be Able to Apply App-Discount on Checkout Page");
        clickNoButtonOnOnboardingScreen();
        verifyHomepage();
        clickOnListIcon();
        clickOnFashionCollection();
        clickOnDressCollection();
        waitFor(3);
        verifyPlp();
        selectProductFromPLP();
        verifyPdp();
        clickAddToCartOnPDP();
        if (isPlatformNameAndroid) {
            clickOnConfirm();
        }
        clickCartIconOnPdpPage();
        waitFor(2);
        verifyCartPage();
        waitFor(1);
        clickOnDiscountOnCartPage();
        enterDiscountOnPopupOnCartPage(discountCode);
        clickOnApplyCouponButtonOnCartPage();
        checkDiscountAplliedOnCartPage();
        waitFor(1.5);
        clickOnPlaceOrder();
        waitFor(10);
        clickOnOrderSummary();
        verifyDiscountAppliedOnCheckoutPage(discountCodeSuffix);
    }
    @Test(priority = 0, groups = {"neovo", "regression", "iOSAppReset"})
    public void verifyUserIsLogoutFromNativeWhenItLogoutFromWebview() {
            testId = "MAB-T14598";
            String email="vrushali1@gmail.com";
            String password="test123";
            logger.info(testId + " This test validates that user getting logged out from native when logged out from webview");
            ExtentTestManager.getTest().setDescription(testId + " This test validates ");
            clickNoButtonOnOnboardingScreen();
            verifyHomepage();
            clickOnHamburgerButtonFromHomePage();
            clickOnCategoryOnHamburger();
            clickSubcategoryOnHamburger();
            clickFirstProductFromPLP();
            verifyPdp();
            clickAddToCartOnPDP();
            if (isPlatformNameAndroid) {
              clickOnConfirm();
            }
            clickBackButtonIconOnTopLeft();
            clickBackButtonIconOnTopLeft();
            clickProfileTabOnHomePage();
            verifyMyAccountPage();
            clickOnLoginButtonOnMyAccountPage();
            verifyLoginPage();
            enterEmailID(email);
            enterPassword(password);
            clickLogin();
            waitFor(5);
            clickOnCartIcon();
            verifyCartPage();
            clickOnPlaceOrder();
            clickonlogoutFromWebview();
            waitFor(5);
            clickOnCheckoutBackButton();
            waitFor(3);
            if (isPlatformNameAndroid) {
                clickYesOnPopup();
            }
            clickOnHomeIcon();
            clickProfileTabOnHomePage();
            verifyMyAccountPage();
            checkLoginButtonOnProfilepage();
    }


    @Test(priority = 0, groups = {"neovo", "regression","iOSAppReset"})
    public void verifyTaxOnCheckoutPageWhenUserCartIsUpdated() {
        testId = "MAB-T24383";
        String email = "vrushalibavale@plobalapps.com";
        String password = "test123";
        logger.info(testId + " This test validates that Tax is updated properly on checkout when user updated the cart");
        ExtentTestManager.getTest().setDescription(testId + " This test validates that Tax is updated properly on checkout when user updated the cart ");
        clickNoButtonOnOnboardingScreen();
        verifyHomepage();
        clickOnHamburgerButtonFromHomePage();
        clickOnCategoryOnHamburger();
        clickSubcategoryOnHamburger();
        clickFirstProductFromPLP();
        verifyPdp();
        clickAddToCartOnPDP();
        if (isPlatformNameAndroid) {
            clickOnConfirm();
        }
        clickBackButtonIconOnTopLeft();
        clickBackButtonIconOnTopLeft();
        clickProfileTabOnHomePage();
        verifyMyAccountPage();
        clickOnLoginButtonOnMyAccountPage();
        verifyLoginPage();
        enterEmailID(email);
        enterPassword(password);
        clickLogin();
        waitFor(5);
        clickOnCartIcon();
        verifyCartPage();
        clickOnPlaceOrder();
        waitFor(10);
        if (!isPlatformNameAndroid) {
            scrollForIos();
            try {
                clickOnContinueButtonOnWebview();
            } catch (Exception e) {
            }
        } else {
            try {
                scrollToText("Continue to shipping");
                clickOnContinueButtonOnWebview();
            } catch (Exception e) {
            }
        }
        waitFor(5);
        String subtotal = fetchSubtotalFromCheckoutPage();
        waitFor(5);
        verifyTaxOnCheckoutPage(subtotal);
        waitFor(2);
        clickOnCheckoutBackButton();
        waitFor(3);
        if (isPlatformNameAndroid) {
            clickYesOnPopup();
        }
        increaseItemOnCartPage();
        clickOnPlaceOrder();
        waitFor(10);
        if (!isPlatformNameAndroid) {
            scrollForIos();
            try {
                clickOnContinueButtonOnWebview();
            } catch (Exception e) {
            }
        } else {
            try {
                scrollToText("Continue to shipping");
                clickOnContinueButtonOnWebview();
            } catch (Exception e) {
            }
        }
        waitFor(5);
        String subtotal1 = fetchSubtotalFromCheckoutPage();
        verifyTaxOnCheckoutPage(subtotal1);
    }

    @Test(priority = 0, groups = {"sandbox", "regression", "iOSAppReset"})
    public void verifyBillingAddressSelectionSectionOnCheckoutPage() {
        testId = "MAB-T24384";
        String username="billingtc@gmail.com";
        String password="12345";
        logger.info(testId + ": This test verify billing address selection section on checkout page");
        ExtentTestManager.getTest().setDescription(testId + ": This test verify billing address selection section on checkout page");
        selectStore(StoreNameEnum.SANDBOX);
        waitFor(3);
        verifyHomepage();
        clickOnProductFromGrid();
        waitFor(1);
        verifyPdp();
        clickAddToCartOnPDP();
        waitFor(2);
        clickOnGoToCart();
        waitFor(2);
        verifyCartPage();
        clickOnPlaceOrder();
        waitFor(2);
        verifyCTAPopup();
        clickOnOtherButtonCTA();
        waitFor(3);
        clickLoginButtonOnNativeCheckout();
        enterEmailID(username);
        enterPassword(password);
        clickLogin();
        waitFor(5);
        clickOnContinueButtonOnCheckoutPage();
        //here the payment page should open under webview checkout, if other page opens then TC will fail
        waitFor(3);
        if(isPlatformNameAndroid) {
            scrollToText("Billing address");
        }
        else {
            scrollForIos();
        }
        checkBillingAddressSelectionSection();
    }

    @Test(priority = 0, groups = {"sandbox", "regression", "iOSAppReset"})
    public void verifyErrorMessageOnBillingAddressPage() {
        testId = "MAB-T45502";
        String username="billingtc@gmail.com";
        String password="12345";
        logger.info(testId + ": This test verify billing address selection section");
        ExtentTestManager.getTest().setDescription(testId + ": This test verify billing address selection section");
        selectStore(StoreNameEnum.SANDBOX);
        verifyHomepage();
        clickOnProductFromGrid();
        waitFor(1);
        verifyPdp();
        clickAddToCartOnPDP();
        waitFor(2);
        clickOnGoToCart();
        waitFor(2);
        verifyCartPage();
        clickOnPlaceOrder();
        waitFor(2);
        verifyCTAPopup();
        clickOnOtherButtonCTA();
        waitFor(3);
        clickLoginButtonOnNativeCheckout();
        enterEmailID(username);
        enterPassword(password);
        clickLogin();
        waitFor(7);
        clickOnContinueButtonOnCheckoutPage();
        waitFor(5);
        if(isPlatformNameAndroid) {
            scrollToText("Billing address");
        }
        else {
            scrollForIos();
        }
        clickOnUseDifferentBillingAddressRadioButton();
        waitFor(1);
        clickUseANewBillingAddress();
        if(isPlatformNameAndroid) {
            scrollToText("Pay now");
        }
        else {
            scrollForIos();
        }
        clickOnPayNow();
        waitFor(2);
        if(isPlatformNameAndroid) {
            scrollToText("All rights reserved plobal-sandbox-test");
        }
        else {
            scrollForIos();
        }
        waitFor(3);
        if (isPlatformNameAndroid){
            checkErrrorOnBillingAddressPage();
        }
        else{
            verifyTextOnPage("Enter a last name");
            verifyTextOnPage("Enter an address");
            verifyTextOnPage("Enter a city");
            verifyTextOnPage("Enter a ZIP / postal code");
        }
    }
}
