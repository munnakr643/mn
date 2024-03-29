package qa.automation.testCase.mobile;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;
import qa.automation.base.MobileBaseTest;
import qa.automation.enums.StoreNameEnum;
import qa.automation.report.ExtentTestManager;

import static qa.automation.base.WebBaseTest.randomNum;
import static qa.automation.enums.SortingOptionsEnum.PRICE_LOW_TO_HIGH;

public class CartPageTestCases extends MobileBaseTest {
    private static final Logger logger = Logger.getLogger(CartPageTestCases.class);


    @Test(priority = 0, groups = {"neovo", "regression", "iOSFailure", "iOSAppReset"})
    public void verifyUpdatedTotalAmountAfterIncreaseQuantityOnCartPage() {
        testId = "MAB-T14738";
        logger.info(testId + " : This test validates Total Amount After Increase Quantity On Cart Page");
        ExtentTestManager.getTest().setDescription(testId + " : This test validates Total Amount After Increase Quantity On Cart Page");
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
        verifyCartPage();
        String total = totalAmountOnCart();
        increaseProductQuantity(1);
        waitFor(1);
        verifyUpdatedTotalAmountAfterIncrease(total);
        mobileDriver.resetApp();
    }

    @Test(priority = 0, groups = {"neovo", "regression", "iOSFailure", "iOSAppReset"})
    public void verifyUpdatedTotalAmountAfterDecreaseQuantityOnCartPage() {
        testId = "MAB-T14739";
        logger.info(testId + " : This test validates Total Amount After Decrease Quantity On CartPage");
        ExtentTestManager.getTest().setDescription(testId + " : This test validates Total Amount After Decrease Quantity On Cart Page");
        clickNoButtonOnOnboardingScreen();
        verifyHomepage();
        clickProductBannerOnHomePage();
        clickAddToCartOnPDP();
        if (isPlatformNameAndroid) {
            clickOnConfirm();
        }
        clickCartIconOnPdpPage();
        waitFor(1);
        verifyCartPage();
        waitFor(1);
        increaseProductQuantity(1);
        String total = totalAmount();
        decreaseProductQuantity(1);
        waitFor(2);
        verifyUpdatedTotalAmountAfterDecrease(total);
        mobileDriver.resetApp();
    }


    @Test(priority = 0, groups = {"sandbox", "regression", "iOSAppReset"})
    public void verifyPlaceOrderCTA() {
        testId = "MAB-T1025 ";
        logger.info(testId + " : This test validates that Place order CTA when Native checkout is enabled");
        ExtentTestManager.getTest().setDescription(testId + " : This test validates that Place order CTA when Native checkout is enabled");
        selectStore(StoreNameEnum.SANDBOX);
        verifyHomepage();
        clickOnProductFromGrid();
        clickAddToCartOnPDP();
        clickOnGoToCart();
        clickOnPlaceOrder();
        verifyCTAPopup();
    }

    @Test(priority = 0, groups = {"neovo", "regression", "iOSAppReset"})
    public void VerifyUserShouldBeAbleToRemoveItemFromCartPage() {
        testId = "MAB-T13925";
        logger.info(testId + " : This test validates Merchant Should Be Able To Remove Item From Cart Page");
        ExtentTestManager.getTest().setDescription(testId + " : Merchant Should Be Able To Remove Item From Cart Page");
        clickNoButtonOnOnboardingScreen();
        verifyHomepage();
        clickProductBannerOnHomePage();
        clickAddToCartOnPDP();
        if (isPlatformNameAndroid) {
            clickOnConfirm();
        }
        clickCartIconOnPdpPage();
        waitFor(1);
        verifyCartPage();
        waitFor(1);
        increaseProductQuantity(1);
        waitFor(5);
        decreaseProductQuantity(1);
        waitFor(5);
    }

    @Test(priority = 0, groups = {"neovo", "regression", "iOSAppReset"})
    public void VerifyUserAbleToApplyDiscountOncart() {
        testId = "MAB-T13863";
        String Discount = "automate1";
        logger.info(testId + " : This test validates user is able to apply the discount on cart by entering valid coupon code");
        ExtentTestManager.getTest().setDescription(testId + " : User able to apply the discount on cart by entering valid coupon code");
        clickNoButtonOnOnboardingScreen();
        verifyHomepage();
        clickProductBannerOnHomePage();
        clickAddToCartOnPDP();
        if (isPlatformNameAndroid) {
            clickOnConfirm();
        }
        clickCartIconOnPdpPage();
        waitFor(1);
        verifyCartPage();
        waitFor(1);
        clickOnDiscountOnCartPage();
        enterDiscountOnPopupOnCartPage(Discount);
        clickOnApplyCouponButtonOnCartPage();
        checkDiscountAplliedOnCartPage();
    }


    @Test(priority = 0, groups = {"sandbox", "regression", "androidFailure", "iOSAppReset"})
    public void increaseQuantityOnCartPage() {
        String count = "2";
        testId = "MAB-T14929";
        logger.info(testId + " : This test validates that user able to increase the quantity in Cart page");
        ExtentTestManager.getTest().setDescription(testId + " : This test validates that user able to increase the quantity in Cart page");
        selectStore(StoreNameEnum.SANDBOX);
        verifyHomepage();
        clickOnProductFromGrid();
        clickAddToCartOnPDP();
        waitFor(2);
        clickOnGoToCart();
        waitFor(4.5);
        increaseItemOnCartPage();
        waitFor(1.5);
        checkItemCount(count);
    }

    @Test(priority = 0, groups = {"sandbox", "regression", "iOSAppReset"})
    public void decreaseQuantityOnCartPage() {
        testId = "MAB-T14927";
        logger.info(testId + " : This test validates that user able to decrease the quantity in Cart page");
        ExtentTestManager.getTest().setDescription(testId + " : This test validates that user able to decrease the quantity in Cart page");
        selectStore(StoreNameEnum.SANDBOX);
        verifyHomepage();
        clickOnProductFromGrid();
        clickAddToCartOnPDP();
        waitFor(2);
        clickOnGoToCart();
        waitFor(2);
        decreaseItemOnCartPage();
        verifyEmptyCartPage();
    }


    @Test(priority = 0, groups = {"neovo", "regression", "iOSAppReset"})
    public void verifyDiscountPriceOnCheckoutPage() {
        String discount = "Automate1";
        testId = "MAB-T14740";
        logger.info(testId + " :This testcases verifies that Discount price should be calculated correctly when user add the discount");
        ExtentTestManager.getTest().setDescription(testId + " :This testcases verifies that Discount price should be calculated correctly when user add the discount");
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
        verifyCartPage();
        clickOnDiscountOnCartPage();
        checkDiscountPopupOnCartPage();
        enterDiscountOnPopupOnCartPage(discount);
        clickOnApplyCouponButtonOnCartPage();
        checkDiscountAplliedOnCartPage();
        String discountAmount = fetchDiscountAmountFromCartPage();
        if (isPlatformNameAndroid) {
            scrollToText("Total");
        } else {
            scrollForIos();
        }
        String subtotal = fetchSubtotalFromCartPage();
        waitFor(2);
        comparingDiscountFromCartPage(discountAmount, subtotal);
    }


    @Test(priority = 0, groups = {"neovo", "regression", "iOSFailure", "androidFailure", "iOSAppReset"})
    public void placeOrderWithFreeProductThroughAddToCart() {
        testId = "MAB-T1029";
        logger.info(testId + ": This test validates place Order With Free Product(price is zero) Through Add To Cart");
        ExtentTestManager.getTest().setDescription(testId + ": This test validates place Order With Free Product(price is zero) Through Add To Cart");
        clickNoButtonOnOnboardingScreen();
        verifyHomepage();
        clickOnMyAccount();
        verifyMyAccountPage();
        clickOnLoginButtonOnMyAccountPage();
        verifyLoginPage();
        enterEmailID("plobaltest121@yopmail.com");
        enterPassword("test1234");
        clickLogin();
        waitFor(1);
        clickOnListIcon();
        clickOnFashionCollection();
        clickOnDressCollection();
        verifyPlp();
        clickOnSortButtonInPLP();
        selectSortingType(PRICE_LOW_TO_HIGH);
        waitFor(1);
        clickFirstProductFromPLP();
        waitFor(1);
        verifyPdp();
        clickAddToCartOnPDP();
        if (isPlatformNameAndroid) {
            clickOnConfirm();
        }
        clickCartIconOnPdpPage();
        totalAmountOnCart().contains("0.00");
        waitFor(1.3);
        clickOnPlaceOrder();
        waitFor(5);
        if (isPlatformNameAndroid) {
            scrollToText("Continue to payment");
        }
        clickOnContinueToPay();
        waitFor(3);
        if (isPlatformNameAndroid) {
            scrollToText("Complete order");
        } else {
            scrollForIos();
        }
        clickOnCompleteOrderButtonOnWebview();
        waitFor(10);
        isOrderConfirmationPageDisplayed();
        mobileDriver.resetApp();
    }


    @Test(priority = 0, groups = {"neovo", "regression", "androidFailure", "androidOnly"})
    public void placeOrderWithFreeProductThroughBuyNow() {
        testId = "MAB-T20375";
        logger.info(testId + ": This test validates place Order With Free Product(price is zero) Through Buy Now");
        ExtentTestManager.getTest().setDescription(testId + ": This test validates place Order With Free Product(price is zero) Through Buy Now");
        clickNoButtonOnOnboardingScreen();
        waitFor(2);
        verifyHomepage();
        clickOnMyAccount();
        verifyMyAccountPage();
        clickOnLoginButtonOnMyAccountPage();
        verifyLoginPage();
        enterEmailID("plobaltest121@yopmail.com");
        enterPassword("test1234");
        clickLogin();
        clickOnListIcon();
        clickOnFashionCollection();
        clickOnDressCollection();
        verifyPlp();
        clickOnSortButtonInPLP();
        selectSortingType(PRICE_LOW_TO_HIGH);
        waitFor(1);
        clickFirstProductFromPLP();
        verifyPdp();
        checkProductPriceOnPdp("Rs. 0.00");
        clickOnBuyNowFromPDP();
        waitFor(2);
        if (isPlatformNameAndroid) {
            clickOnConfirm();
        }
        waitFor(7.5);
        if (isPlatformNameAndroid) {
            scrollToText("Continue to payment");
        }
        clickOnContinueToPay();
        waitFor(5);
        if (isPlatformNameAndroid) {
            scrollToText("Complete order");
        } else {
            scrollForIos();
        }
        clickOnCompleteOrderButtonOnWebview();
        waitFor(30);
        isOrderConfirmationPageDisplayed();
        mobileDriver.resetApp();
    }

    @Test(groups = {"sandbox", "regression", "iOSAppReset"})
    public void verifyUserCanAbletoDecreaseQuantity() {
        String incCount = "2";
        String decCount = "1";
        testId = "MAB-T14926";
        logger.info(testId + " This test validate user can be able to decrease quantity by clicking - sign ");
        ExtentTestManager.getTest().setDescription(testId + " This test validate user can be able to decrease quantity by clicking - sign ");
        selectStore(StoreNameEnum.SANDBOX);
        verifyHomepage();
        clickOnProductFromGrid();
        clickAddToCartOnPDP();
        waitFor(3);
        clickOnGoToCart();
        waitFor(2);
        increaseItemOnCartPage();
        waitFor(3);
        checkItemCount(incCount);
        decreaseItemOnCartPage();
        waitFor(2);
        checkItemCount(decCount);
    }

    @Test(groups = {"neovo", "regression", "iOSAppReset"})
    public void verifyUserRedirectFavoriteScreenfromCartPage() {
        String Favproduct = "White Mini Dress";
        testId = "MAB-T14736";
        logger.info(testId + " This test validate user should redirected to wishlisted products when clicked on Add more from favourites option ");
        ExtentTestManager.getTest().setDescription(testId + " This test validate user should redirected to wishlisted products when clicked on Add more from favourites option ");
        clickNoButtonOnOnboardingScreen();
        verifyHomepage();
        clickOnListIcon();
        clickOnFashionCollection();
        clickOnDressCollection();
        verifyPlp();
        addProductToFavoritesOnPLP(Favproduct);
        clickFirstProductFromPLP();
//        selectProductFromPLP();
        addProductIntoCartFromPDP();
        if (isPlatformNameAndroid) {
            clickOnConfirm();
        }
        clickCartIconOnPdpPage();
        waitFor(1);
        verifyCartPage();
        waitFor(1);
        clickAddMoreFromFavoriteButtonInCartPage();
        isFavoritePageLoaded();
        verifyProductTitleOnFavoritesPage(Favproduct);
    }

    @Test(priority = 0, groups = {"neovo", "regression", "iOSFailure", "androidFailure", "iOSAppReset"})
    public void verifyClearCartAfterSuccessfullyPlacedOrder() {
        testId = "MAB-T1080";
        logger.info(testId + " This test validates cart gets clear after successfully placing order");
        ExtentTestManager.getTest().setDescription(testId + " This test validates cart gets clear after successfully placing order");
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
        waitFor(4.5);
        checkItemCount("1");
        clickOnPlaceOrder();
        waitFor(2);
        clickLoginButtonOnWebviewCheckout();
        isLoginPageDisplayed();
        enterEmailID("sahiltripathi@plobalapps.com");
        enterPassword("plobal123");
        clickLogin();
        waitFor(3.5);
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
        clickOnContinueToPay();
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
        if (isPlatformNameAndroid) {
            scrollToText("Continue Shopping");
        }else{
            scrollForIos();
        }
        clickOnContinueShopping();
        clickOnCartIcon();
        verifyEmptyCartPage();
}

    @Test(priority = 0, groups = {"neovo", "regression", "iOSAppReset"})
    public void verifyTotalPriceAfterUpdationOfQuantity() {
        testId = "MAB-T13927";
        logger.info(testId + " This test validates Cart total price calculated correctly when user update products qty on cart page");
        ExtentTestManager.getTest().setDescription(testId + " This test validates Cart total price calculated correctly when user update products qty on cart page");
        clickNoButtonOnOnboardingScreen();
        verifyHomepage();
        clickOnListIcon();
        clickOnFashionCollection();
        clickOnDressCollection();
        waitFor(3);
        verifyPlp();
        selectProductFromPLP();
        waitFor(1.5);
        verifyPdp();
        clickAddToCartOnPDP();
        if (isPlatformNameAndroid) {
            clickOnConfirm();
        }
        clickCartIconOnPdpPage();
        waitFor(3);
        checkItemCount("1");
        String total = fetchTotalPayableFromCartPage();
        increaseItemOnCartPage();
        waitFor(3);
        if (!isPlatformNameAndroid) {
            scrollForIos();
        }
        checkTotalPayableAmountAfterIncreasingQuantity(total);
    }

    @Test(priority = 0, groups = {"neovo", "regression"})
    public void checkAddMoreFromFavoritesVisiblilityOnCartPage() {
        testId = "MAB-T1024";
        logger.info(testId + " : This test validate Add More From Favorites Visiblility On Cart Page");
        ExtentTestManager.getTest().setDescription(testId + " : This test validate Add More From Favorites Visiblility On Cart Page");
        clickNoButtonOnOnboardingScreen();
        verifyHomepage();
        clickProductBannerOnHomePage();
        verifyPdp();
        clickAddToCartOnPDP();
        if (isPlatformNameAndroid) {
            clickConfirmButtonOnConfirmationPopUPAfterAddToCartClick();
        }
        clickCartIconOnPdpPage();
        verifyAddMoreFromFavoriteButtonInCartPage();
    }

    @Test(priority = 0, groups = {"neovo", "regression", "iOSAppReset"})
    public void checkInvalidDiscountDiscountOnCartPage() {
        testId = "MAB-T13864";
        String invalidDiscountCode = "abcde";
        String cartHeader = "Cart";
        String androidErrorMessage = "Not applicable! this code has been used already or invalid for this order. Try again with a valid code";
        String iOSErrorMessage = "Not applicable! this code has been used already or invalid for this order. Try again with a valid code.";
        logger.info(testId + " : This test validate Invalid Discount Discount On Cart Page");
        ExtentTestManager.getTest().setDescription(testId + " : This test validate Invalid Discount Discount On Cart Page");
        clickNoButtonOnOnboardingScreen();
        verifyHomepage();
        clickProductBannerOnHomePage();
        verifyPdp();
        clickAddToCartOnPDP();
        if (isPlatformNameAndroid) {
            clickConfirmButtonOnConfirmationPopUPAfterAddToCartClick();
        }
        clickCartIconOnPdpPage();
        waitFor(3);
        verifyCartHeaderOnCartPage(cartHeader);
        clickOnDiscountOnCartPage();
        checkDiscountPopupOnCartPage();
        enterDiscountOnPopupOnCartPage(invalidDiscountCode);
        if (isPlatformNameAndroid) {
            mobileDriver.hideKeyboard();
        }
        clickOnApplyCouponButtonOnCartPage();
        waitFor(2);
        if (isPlatformNameAndroid) {
            verifyDiscountCodeErrorOnCartPage(androidErrorMessage);
        } else {
            verifyDiscountCodeErrorOnCartPage(iOSErrorMessage);
        }
    }

    @Test(priority = 0, groups = {"sandbox", "regression", "iOSAppReset"})
    public void checkCartPageShouldShownInvokingHamburgerMenu() {
        testId = "MAB-T15911";
        logger.info(testId + " : This test validate to verify cart pag by invoke from Hamburger menu page");
        ExtentTestManager.getTest().setDescription(testId + " : This test validate to verify cart pag by invoke from Hamburger menu page ");
        selectStore(StoreNameEnum.SANDBOX);
        waitFor(1);
        verifyHomepage();
        clickOnHamburgerButtonFromHomePage();
        clickOnCartHamburgerMenu();
        waitFor(2);
        verifyEmptyCartPage();
    }

    @Test(priority = 0, groups = {"sandbox", "regression", "iOSAppReset"})
    public void checkUserabletoAddAddressOnCheckoutPage() {
        String emailID = "sahiltripathi" + randomNum() + "@plobalapps.com";
        String passowrd = "plobal123";
        String firstname = "Sahil" + randomString(4);
        String lastname = "Test";
        String Fname = "Sahill" + randomString(2);
        String Lname = "Tripathi" + randomString(3);
        String Company = "Plobal";
        String contact = "8209147857";
        String Address1 = "Bavdhan";
        String Address2 = "Sai Velocity";
        String State = "Maharashtra";
        String City = "Pune";
        String Zipcode = "411021";
        testId = "MAB-T1076";
        logger.info(testId + " : This test validate User able to add address on Checkout page");
        ExtentTestManager.getTest().setDescription(testId + " : This test validate User able to add address on Checkout page");
        selectStore(StoreNameEnum.SANDBOX);
        verifyHomepage();
        clickOnHamburgerButtonFromHomePage();
        clickOnLoginButtonFromHamburgerMenu();
        verifyLoginPage();
        clickRegisterButton();
        waitFor(1);
        verifyNewUserRegisterPage();
        enterFirstName(firstname);
        enterLastName(lastname);
        enterEmailID(emailID);
        enterPassword(passowrd);
        enterConfirmPasswordForNewUser(passowrd);
        clickRegisterBtnForNewUser();
        clickDrawerOnHomePage();
        isUserLoginDispalyed(firstname + " " + lastname);
        clickOnHomePageFromHamburger();
        clickOnProductFromGrid();
        clickAddToCartOnPDP();
        waitFor(2);
        clickOnGoToCart();
        clickOnPlaceOrder();
        clickOnCreditCardCTA();
        waitFor(3);
        clickAddAddressIconOnNativeCheckoutPage();
        enterFirstnameOnAddressPage(Fname);
        enterLastnameOnAddressPage(Lname);
        enterCompanyNameOnAddressPage(Company);
        enterContactNumberOnAddressPage(contact);
        enterAddressLine1OnAddressPage(Address1);
        enterAddressLine2OnAddressPage(Address2);
        enterStateOnAddressPage(State);
        enterCityOnAddressPage(City);
        enterZipcodeOnAddressPage(Zipcode);
        mobileDriver.hideKeyboard();
        clickSaveButtonOnAddressPageCheckoutPage();
    }

    @Test(priority = 0, groups = {"neovo", "regression", "iOSAppReset"})
    public void checkProductTitleShouldShown() {
        testId = "MAB-T14750";
        logger.info(testId + " : This test validate to check the product title on Cart Page");
        ExtentTestManager.getTest().setDescription(testId + " : This test validate to check the product title on Cart Page");
        clickNoButtonOnOnboardingScreen();
        verifyHomepage();
        clickProductBannerOnHomePage();
        String productName = pDpPage.getProductTitleOnPdp().getText();
        clickAddToCartOnPDP();
        if (isPlatformNameAndroid) {
            clickOnConfirm();
        }
        clickCartIconOnPdpPage();
        waitFor(1);
        verifyCartPage();
        waitFor(1);
        verifyFirstProductTitleOncartPage(productName);
    }

    @Test(priority = 0, groups = {"neovo", "regression", "iOSAppReset"})
    public void checkProductPriceShouldShown() {
        testId = "MAB-T14749";
        logger.info(testId + " : This test validate to check the product Price on Cart Page");
        ExtentTestManager.getTest().setDescription(testId + " : This test validate to check the product Price on Cart Page");
        clickNoButtonOnOnboardingScreen();
        verifyHomepage();
        waitFor(3);
        clickProductBannerOnHomePage();
        String productPrice = pDpPage.getProductPriceOnPdp().getText();
        waitFor(2);
        clickAddToCartOnPDP();
        if (isPlatformNameAndroid) {
            clickOnConfirm();
        }
        clickCartIconOnPdpPage();
        waitFor(1);
        verifyCartPage();
        waitFor(1);
        verifyFirstProductPriceOncartPage(productPrice);
    }


    @Test(priority = 0, groups = {"sandbox", "regression", "iOSAppReset"})
    public void verifyResponseTimeToLoadCartPage() {
        testId = "MAB-T15897";
        logger.info(testId + " : This test validate response time to load cart page when cart is added with products");
        ExtentTestManager.getTest().setDescription(testId + " : This test validate response time to load cart page when cart is added with products");
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
        waitFor(1);
        clickCartTabOnHomePage();
        waitFor(1);
        verifyCartPage();
    }

    @Test(priority = 0, groups = {"neovo", "regression", "iOSAppReset"})
    public void verifyUpdatedDiscountPriceOnCartPage() {
        String discount1 = "Automate2";
        String discount2 = "Automate1";
        testId = "MAB-T14742";
        logger.info(testId + " :This testcases verifies that Discount price should be calculated correctly when user update the applied discount");
        ExtentTestManager.getTest().setDescription(testId + " :This testcases verifies that Discount price should be calculated correctly when user update the applied discount");
        clickNoButtonOnOnboardingScreen();
        verifyHomepage();
        clickOnListIcon();
        clickOnFashionCollection();
        clickOnDressCollection();
        waitFor(2);
        verifyPlp();
        selectProductFromPLP();
        verifyPdp();
        addProductIntoCartFromPDP();
        if (isPlatformNameAndroid) {
            clickOnConfirm();
        }
        clickCartIconOnPdpPage();
        verifyCartPage();
        clickOnDiscountOnCartPage();
        checkDiscountPopupOnCartPage();
        enterDiscountOnPopupOnCartPage(discount1);
        clickOnApplyCouponButtonOnCartPage();
        checkDiscountAplliedOnCartPage();
        removeAppliedDiscountFromCartPage();
        if (isPlatformNameAndroid) {
            clickOnOK();
        }
        verifyCartPage();
        clickOnDiscountOnCartPage();
        checkDiscountPopupOnCartPage();
        enterDiscountOnPopupOnCartPage(discount2);
        clickOnApplyCouponButtonOnCartPage();
        checkDiscountAplliedOnCartPage();
        String discountAmount = fetchDiscountAmountFromCartPage();
        if (isPlatformNameAndroid) {
            scrollToText("Total");
        } else {
            scrollForIos();
        }
        String subtotal = fetchSubtotalFromCartPage();
        waitFor(2);
        comparingDiscountFromCartPage(discountAmount, subtotal);
    }

    @Test(groups = {"sandbox", "regression", "iOSAppReset"})
    public void verifyStaticMessageOnCartPage() {
        testId = "MAB-T37487";
        logger.info(testId + " This test validate static message on cart");
        ExtentTestManager.getTest().setDescription(testId + " This test validate static message on cart");
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
        if (isPlatformNameAndroid) {
            scrollToText("Hey! Grab a discount of 20% using code WELCOME20 on your first purchase");
        } else {
            scrollForIos();
        }
        verifyStaticMessageOnCart();
    }
    @Test(groups = {"sandbox", "regression", "iOSAppReset"})
    public void verifyAutomaticDiscountOnCartPage() {
        testId = "MAB-T11411";
        logger.info(testId + " This test validates automatic Buy x get y discount for specific collections on cart page and checkout page");
        ExtentTestManager.getTest().setDescription(testId + " This test validates automatic Buy x get y discount for specific collections on cart page and checkout page");
        selectStore(StoreNameEnum.SANDBOX);
        verifyHomepage();
        clickOnAutomaticDiscountProduct();
        verifyPdp();
        clickAddToCartOnPDP();
        waitFor(2);
        if(isPlatformNameAndroid){
            backBtn();
        }else {
            clickBackArrowOnNativeApp();
        }
        clickOnProductFromGrid();
        verifyPdp();
        clickAddToCartOnPDP();
        waitFor(3);
        clickOnGoToCart();
        waitFor(2);
        checkAutomaticDiscountOnCartPage();
    }

    @Test(groups = {"sandbox", "regression", "iOSAppReset"})
    public void verifyAcceptTermsConditionCheckboxOnCartPage() {
        testId = "MAB-T1681";
        logger.info(testId + " This test validates Accept terms Condition checkbox on cart");
        ExtentTestManager.getTest().setDescription(testId + " This test validates Accept terms Condition checkbox on cart");
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
        if (isPlatformNameAndroid) {
            scrollToText("Hey! Grab a discount of 20% using code WELCOME20 on your first purchase");
        } else {
            scrollForIos();
        }
        verifyStaticMessageOnCart();
        verifyAcceptTermsCheckboxOnCart();
    }

    @Test(priority = 0, groups = {"neovo", "regression", "iOSAppReset"})
    public void verifyMultiCurrencyOnCartPage() {
        testId = "MAB-T2952";
        logger.info(testId + " This test validates Multi currency behavior  on Cart Page");
        ExtentTestManager.getTest().setDescription(testId + " This test validates that Multi currency behavior on Cart Page");
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
        selectUSD();
        waitFor(2);
        clickCartTabOnHomePage();
        verifyCartPage();
        isSelectedCurrencyDisplayedOnCart("$");
    }

    @Test(priority = 0, groups = {"neovo", "regression", "iOSAppReset"})
    public void verifyUserAbleToUpdateQuantityOnCartPage() {
        testId = "MAB-T14930";
        String expectedQuantity = "1";;
        logger.info(testId + " This test validates user able to update the quantity in Cart page");
        ExtentTestManager.getTest().setDescription(testId + " This test validates that user able to update the quantity in Cart page");
        clickNoButtonOnOnboardingScreen();
        verifyHomepage();
        clickProductBannerOnHomePage();
        verifyPdp();
        clickAddToCartOnPDP();
        if (isPlatformNameAndroid) {
            clickOnConfirm();
        }
        clickCartIconOnPdpPage();
        waitFor(1);
        verifyCartPage();
        checkCurrentQuantityOnCartPage(expectedQuantity, true);
        increaseProductQuantity(1);
        expectedQuantity = "2";
        checkCurrentQuantityOnCartPage(expectedQuantity, false);
    }

    @Test(groups = {"sandbox", "regression", "iOSAppReset"})
    public void verifyRedirectionOfCTAButtonOnCartPage() {
        testId = "MAB-T14752";
        String email = "sahiltripathi@plobalapps.com";
        String password = "plobal123";
        logger.info(testId + " This test validate Redirection of CTA button on Cart page");
        ExtentTestManager.getTest().setDescription(testId + " This test validate Redirection of CTA button on Cart page");
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
        waitFor(2);
        clickOnContinueButtonOnCheckoutPage();
        waitFor(10);
        clickOnOrderSummary();
    }
    @Test(priority = 0, groups = {"neovo", "regression", "iOSAppReset"})
    public void checkOrderDetailShownOnCartPage() {
        testId = "MAB-T14931";
        logger.info(testId + " : This test validate to check the order details on Cart page");
        ExtentTestManager.getTest().setDescription(testId + " : This test validate to check the order details on Cart page");
        clickNoButtonOnOnboardingScreen();
        verifyHomepage();
        clickProductBannerOnHomePage();
        String productName = pDpPage.getProductTitleOnPdp().getText();
        String productPrice = pDpPage.getProductPriceOnPdp().getText();
        clickAddToCartOnPDP();
        if (isPlatformNameAndroid) {
            clickOnConfirm();
        }
        clickCartIconOnPdpPage();
        waitFor(1);
        verifyCartPage();
        waitFor(1);
        verifyFirstProductTitleOncartPage(productName);
        verifyFirstProductPriceOncartPage(productPrice);
    }
    @Test(priority = 0, groups = {"sandbox", "regression","iOSAppReset"})
    public void verifyCartPageFunctionality() {
        testId = "MAB-T1018";
        String discountCode="BigSale";
        logger.info(testId + " : This test validate Cart Page Functionality");
        ExtentTestManager.getTest().setDescription(testId + " : This test validate Cart Page Functionality");
        selectStore(StoreNameEnum.SANDBOX);
        verifyHomepage();
        clickProductFromHomepage();
        waitFor(2);
        verifyPdp();
        String productTitle = getProductTitleFromPDP();
        clickAddToCartOnPDP();
        clickOnGoToCart();
        waitFor(2);
        verifyCartPage();
        verifyFirstProductTitleOncartPage(productTitle);
        isProductImageIsDisplayedOnCartPage();
        clickOnDiscountOnCartPage();
        enterDiscountOnPopupOnCartPage(discountCode);
        clickOnApplyCouponButtonOnCartPage();
        waitFor(3);
        checkDiscountAplliedOnCartPage();
        String androidTotalAmount="67.50";
        String iOSTotalAmount="44.10";
        if (!isPlatformNameAndroid){
            scrollForIos();
            verifyTotalAmountOnCartPage(iOSTotalAmount);
        }
        else {
            verifyTotalAmountOnCartPage(androidTotalAmount);
        }
    }

    @Test(priority = 0, groups = {"sandbox", "regression", "iOSAppReset"})
    public void verifyAutomaticDiscountOnCartPageBelowProductPrice () {
        testId = "MAB-T11414";
        logger.info(testId + " : This test validate to check the automatic discount on Cart Page ");
        ExtentTestManager.getTest().setDescription(testId + " : This test validate to check the automatic discount on Cart Page");
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
        verifyCartPage();
        checkAutomaticDiscountOnCartPage();
    }

    @Test(priority = 0, groups = {"sandbox", "regression", "iOSAppReset"})
    public void verifyAddingAndDeletingProductFromCart () {
        String count = "3";
        int counting = Integer.parseInt(count)-1;
        String result = String.format("%d",counting);
        testId = "MAB-T33295";
        logger.info(testId + " : This test validates that user able to increase and descrease the quantity in Cart page");
        ExtentTestManager.getTest().setDescription(testId + " : This test validates that user able to increase the quantity in Cart page");
        selectStore(StoreNameEnum.SANDBOX);
        verifyHomepage();
        clickOnHamburgerButtonFromHomePage();
        clickOnLoginButtonFromHamburgerMenu();
        verifyLoginPage();
        enterEmailID(testData.getProperty("emailID1"));
        enterPassword(testData.getProperty("password1"));
        clickLogin();
        clickOnProductFromGrid();
        verifyPdp();
        clickAddToCartOnPDP();
        waitFor(2);
        clickOnGoToCart();
        waitFor(4.5);
        verifyCartPage();
        waitFor(2);
        increaseItemOnCartPage();
        waitFor(3);
        increaseItemOnCartPage();
        waitFor(3);
        checkItemCount(count);
        waitFor(5);
        decreaseItemOnCartPage();
        waitFor(5);
        checkItemCount(result);
        waitFor(5);
        clickOnBackButtonFromCartPage();
        if (isPlatformNameAndroid) {
            clickOnCheckoutBackButton();
        }else{
            clickBackButtonIconOnTopLeft();
        }
        clickOnHamburgerButtonFromHomePage();
        waitFor(3);
        clickOnProfileButtonFromHamburgerMenu();
        checkLogoutButtonOnProfilePage();
        clickLoginOrLogoutButtonOnProfilePage();
        if (isPlatformNameAndroid) {
            clickOnOK();
        }
        waitFor(3);
        clickOnHamburgerButtonFromHomePage();
        clickOnCartHamburgerMenu();
        waitFor(5);
        verifyCartPage();
        checkItemCount(result);
        waitFor(5);

    }

    @Test(priority = 0, groups = {"sandbox", "regression", "iOSAppReset"})
    public void verifyAddMoreFromFavoritesButtonOnCart () {
        testId = "MAB-T33296";
        logger.info(testId + " : This test verifies whether there is an Add to Favorites button on the cart page.");
        ExtentTestManager.getTest().setDescription(testId + " : This test verifies whether there is an Add to Favorites button on the cart page.");
        selectStore(StoreNameEnum.SANDBOX);
        verifyHomepage();
        clickOnHamburgerButtonFromHomePage();
        clickOnLoginButtonFromHamburgerMenu();
        waitFor(2);
        verifyLoginPage();
        enterEmailID(testData.getProperty("emailID1"));
        enterPassword(testData.getProperty("password1"));
        clickLogin();
        clickOnProductFromGrid();
        verifyPdp();
        clickAddToCartOnPDP();
        waitFor(2);
        clickOnGoToCart();
        waitFor(4.5);
        verifyCartPage();
        waitFor(2);
        verifyAddMoreFromFavoriteButtonInCartPage();

    }

}


