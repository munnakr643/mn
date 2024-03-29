package qa.automation.testCase.mobile;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;
import qa.automation.base.MobileBaseTest;
import qa.automation.enums.StoreNameEnum;
import qa.automation.report.ExtentTestManager;

public class PDPTestCases extends MobileBaseTest {
    private Logger logger = Logger.getLogger(PDPTestCases.class);

    @Test(priority = 0, groups = {"neovo", "regression", "iOSAppReset"})
    public void addToCartButtonTestOnPDP() {
        testId = "MAB-T14580";
        logger.info(testId + " : This test validate add to cart button on PDP");
        ExtentTestManager.getTest().setDescription(testId + " : This test validate add to cart button on PDP");
        clickNoButtonOnOnboardingScreen();
        clickProductBannerOnHomePage();
        waitFor(4);
        String productName = pDpPage.getProductTitleOnPdp().getText();
        clickAddToCartOnPDP();
        if (isPlatformNameAndroid) {
            clickConfirmButtonOnConfirmationPopUPAfterAddToCartClick();
        }
        clickCartIconOnPdpPage();
        waitFor(3);
        verifyFirstProductTitleOncartPage(productName);
    }

    @Test(priority = 0, groups = {"neovo", "regression", "iOSAppReset"})
    public void checkAddToFavoriteOnPDP() {
        String testId = "MAB-T14551";
        logger.info(testId + " : This test validate add to favorite on PDP");
        ExtentTestManager.getTest().setDescription(testId + " : This test validate add to favorite on PDP");
        clickNoButtonOnOnboardingScreen();
        clickProductBannerOnHomePage();
        waitFor(3);
        clickAddToFavoriteIconOnPDPPage();
        String productName = pDpPage.getProductTitleOnPdp().getText();
        clickBackArrowOnNativeApp();
        clickProfileTabOnHomePage();
        clickFavoriteFeatureOnProfilePage();
        waitFor(2);
        verifyProductOnFavoritePage(productName);
    }

    @Test(priority = 0, groups = {"neovo", "regression", "androidOnly"})
    public void checkBySwitchingTheProductVariantOnPDP() {
        testId = "MAB-T14560";
        logger.info(" This test validates switching between the product variants");
        ExtentTestManager.getTest().setDescription("This test validates switching between the product variants");
        clickNoButtonOnOnboardingScreen();
        clickProductBannerOnHomePage();
        waitFor(3);
        if (isPlatformNameAndroid) {
            swipe(500, 1000, 500, 300);
        }
        else {
            swipe(200, 630, 200, 300);
        }
        clickSVariantOnPDP();
        clickMVariantOnPDP();
        clickXSVariantOnPDP();
    }

    @Test(priority = 0, groups = {"neovo", "regression"})
    public void checkUserAbleToSwitchToParticularPDPPageBySelectingRelatedProduct() {
        testId = "MAB-T14557";
        String productTitle = "Pink Cami Jumpsuit";
        logger.info(testId + " : This test validates switching between the related product");
        ExtentTestManager.getTest().setDescription(testId + " : This test validates switching between the related product");
        clickNoButtonOnOnboardingScreen();
        verifyHomepage();
        clickProductBannerOnHomePage();
        waitFor(2);
        verifyPdp();
        clickRelatedProductOnPDP();
        waitFor(3);
        verifyProductTitleOnPDP(productTitle);
    }

    @Test(priority = 0, groups = {"neovo", "regression"})
    public void verifyAddTocartButtonOnPDP() {
        testId = "MAB-T14593";
        logger.info(testId + " This test validates Add to Cart CTA button should be available on PDP");
        ExtentTestManager.getTest().setDescription(testId + " This test validates Add to Cart CTA button should be available on PDP");
        clickNoButtonOnOnboardingScreen();
        verifyHomepage();
        clickOnListIcon();
        clickOnFashionCollection();
        clickOnDressCollection();
        waitFor(2);
        String productTitle = "White Mini Dress";
        clickProductInPLP(productTitle);
        waitFor(4);
        verifyPdp();
        waitFor(.5);
        isAddToCartButtonVisibleOnPdp();
    }

    @Test(priority = 0, groups = {"neovo", "regression"})
    public void verifyVariantOnPDP() {
        testId = "MAB-T14559";
        logger.info(testId + " This test validates variant on PD Page");
        ExtentTestManager.getTest().setDescription(testId + " This test validates variant on PD Page");
        clickNoButtonOnOnboardingScreen();
        verifyHomepage();
        clickOnListIcon();
        clickOnFashionCollection();
        clickOnDressCollection();
        waitFor(2);
        String productTitle = "White Mini Dress";
        clickProductInPLP(productTitle);
        waitFor(4);
        verifyPdp();
        waitFor(.5);
        isAddToCartButtonVisibleOnPdp();
        selectVariantFromPDP("M");
        selectVariantFromPDP("L");
    }

    @Test(priority = 0, groups = {"neovo", "regression"})
    public void verifyPDPLoadTime() {
        testId = "MAB-T994";
        logger.info(testId + " This test validates PDP load time should be not more than 3 seconds");
        ExtentTestManager.getTest().setDescription(testId + " This test validates PDP load time should be not more than 3 seconds");
        clickNoButtonOnOnboardingScreen();
        verifyHomepage();
        clickOnListIcon();
        clickOnFashionCollection();
        clickOnDressCollection();
        waitFor(2);
        String productTitle = "White Mini Dress";
        clickProductInPLP(productTitle);
        waitFor(4);
        verifyPdp();
        isAddToCartButtonVisibleOnPdp();
    }

    @Test(groups = {"neovo", "Regression"})
    public void verifySelectedVariantOnCartPage() {
        testId = "MAB-T999";
        logger.info(testId + " This test validates selected variant from PDP is getting added in to the cart");
        ExtentTestManager.getTest().setDescription(testId + " This test validates selected variant from PDP is getting added in to the cart");
        clickNoButtonOnOnboardingScreen();
        verifyHomepage();
        clickOnListIcon();
        clickOnFashionCollection();
        clickOnDressCollection();
        waitFor(2);
        String productTitle = "White Mini Dress";
        clickProductInPLP(productTitle);
        waitFor(4);
        verifyPdp();
        isAddToCartButtonVisibleOnPdp();
        selectVariantFromPDP("M");
        clickAddToCartOnPDP();
        if (isPlatformNameAndroid) {
            clickOnConfirm();
        }
        clickCartIconOnPdpPage();
        waitFor(.7);
        verifyVariantOnCartPage("M");
    }


    @Test(priority = 1, groups = {"neovo", "regression"})
    public void verifyCartIconOnPdpPage() {
        testId = "MAB-T14548";
        logger.info(testId + " : This test validates verifyPDPCartIcon");
        ExtentTestManager.getTest().setDescription(testId + " : This test validates verifyPDPCartIcon");
        clickNoButtonOnOnboardingScreen();
        verifyHomepage();
        clickOnListIcon();
        clickOnFashionCollection();
        clickOnDressCollection();
        waitFor(3);
        String productTitle = "White Mini Dress";
        clickProductInPLP(productTitle);
        waitFor(4);
        clickCartIconOnPdpPage();
    }


    @Test(priority = 1, groups = {"neovo", "regression"})
    public void verifyPdpProductTitle() {
        testId = "MAB-T14552";
        logger.info(testId + " : This test validates that Product title should be shown below image gallery in PDP page");
        ExtentTestManager.getTest().setDescription(testId + " : This test validates that Product title should be shown below image gallery in PDP page");
        clickNoButtonOnOnboardingScreen();
        verifyHomepage();
        clickOnListIcon();
        clickOnFashionCollection();
        clickOnDressCollection();
        waitFor(3);
        String productTitle = selectProductFromPLP();
        verifyProductTitleOnPDP(productTitle);
    }


    @Test(priority = 1, groups = {"neovo", "regression"})
    public void verifyPdpProductTitleOnTop() {
        testId = "MAB-T14546";
        logger.info(testId + " : This test validates Product name should reflect on top of the PDP page");
        ExtentTestManager.getTest().setDescription(testId + " : This test validates that Product name should reflect on top of the PDP page");
        clickNoButtonOnOnboardingScreen();
        verifyHomepage();
        clickOnListIcon();
        clickOnFashionCollection();
        clickOnDressCollection();
        waitFor(3);
        String productTitle = selectProductFromPLP();
        verifyProductTitleOnPDP(productTitle);
    }

    @Test(priority = 1, groups = {"neovo", "regression"})
    public void verifyPdpProductPrice() {
        testId = "MAB-T14589";
        logger.info(testId + " : This test validates verifyPDPCartIcon");
        ExtentTestManager.getTest().setDescription(testId + " : This test validates verifyPDPCartIcon");
        clickNoButtonOnOnboardingScreen();
        verifyHomepage();
        clickOnListIcon();
        clickOnFashionCollection();
        clickOnDressCollection();
        verifyPlp();
        String productPrice = getProductPriceFromPLP();
        String productTitle = "White Mini Dress";
        clickProductInPLP(productTitle);
        waitFor(4);
        checkProductPriceOnPdp(productPrice);
    }

    @Test(priority = 1, groups = {"neovo", "regression"})
    public void verifyVarientPriceOnPdp() {
        testId = "MAB-T14561";
        logger.info(testId + " : This test validates Product price should be reflect according to selected variant on PDP page");
        ExtentTestManager.getTest().setDescription(testId + " : This test validates Product price should be reflect according to selected variant on PDP page");
        clickNoButtonOnOnboardingScreen();
        verifyHomepage();
        clickOnListIcon();
        clickOnFashionCollection();
        clickOnDressCollection();
        waitFor(3);
        String productPrice = getProductPriceFromPLP();
        String productTitle = "White Mini Dress";
        clickProductInPLP(productTitle);
        waitFor(4);
        checkProductPriceOnPdp(productPrice);
        waitFor(1);
        checkVarientPriceOnPdpPage();
    }

    @Test(priority = 1, groups = {"neovo", "regression"})
    public void verifyCTAButtonOnPDPPage() {
        testId = "MAB-T14579";
        logger.info(testId + " This test verifies that CTA button on PDP page.");
        ExtentTestManager.getTest().setDescription(testId + " This test verifies that CTA button on PDP page.");
        clickNoButtonOnOnboardingScreen();
        verifyHomepage();
        clickProductBannerOnHomePage();
        verifyPdp();
        waitFor(1);
        checkCTAbuttOnPDPPage();
    }

    @Test(priority = 1, groups = {"neovo", "regression", "androidOnly"})
    public void verifyCartAndSearchButtonOnPDPPage() {
        testId = "MAB-T14547";
        logger.info(testId + ": This test verifies that cart and Search button on PDP Page");
        ExtentTestManager.getTest().setDescription(testId + ": This test verifies that cart and Search button on PDP Page");
        String productTitle = "White Mini Dress";
        clickNoButtonOnOnboardingScreen();
        verifyHomepage();
        clickOnListIcon();
        clickOnFashionCollection();
        clickOnDressCollection();
        waitFor(3); //Allowing all the products to load in the PLP Page
        clickProductInPLP(productTitle);
        waitFor(4);
        checkSearchButtOnPDPPage();
        isCartIconPresentOnPdpPage();
    }

    @Test(priority = 1, groups = {"neovo", "regression"})
    public void verifyShareButtonOnPDPPage() {
        testId = "MAB-T14550";
        logger.info(testId + ": This test verifies the functionality of the Share button on the PDP page.");
        ExtentTestManager.getTest().setDescription(testId + ": This test verifies the functionality of the Share button on the PDP page.");
        clickNoButtonOnOnboardingScreen();
        verifyHomepage();
        clickProductBannerOnHomePage();
        verifyPdp();
        clickOnShareButtonOnPDP();
        waitFor(1);
        cooClick(950, 1450);
        waitFor(1);
        if (isPlatformNameAndroid) {
            verifySharePopupOnHomePage();
        }
        checkCopyButtonOnSharePopUp();
    }

    @Test(priority = 0, groups = {"neovo", "regression"})
    public void verifyVendorNameOnPDPPage() {
        testId = "MAB-T14119";
        logger.info(testId + ": This test verifies that vendor name should be shown on pdp Page");
        ExtentTestManager.getTest().setDescription(testId + ": This test verifies that vendor name should be shown on pdp Page");
        clickNoButtonOnOnboardingScreen();
        verifyHomepage();
        clickOnListIcon();
        clickOnFashionCollection();
        clickOnTopsCollection();
        verifyPlp();
        clickFirstProductFromPLP();
        verifyPdp();
        if (isPlatformNameAndroid) {
            scrollToText("More Info");
        } else {
            scrollForIos();
        }
        isVendorNameDisplayed("StyleMore");
    }

    @Test(priority = 0, groups = {"neovo", "regression"})
    public void verifyImageStickersOnPDP() {
        testId = "MAB-T13933";
        logger.info(testId + " This test validates user is able to see the product image sticker on PDP");
        ExtentTestManager.getTest().setDescription(testId + " This test validates user is able to see the product image sticker on PDP");
        clickNoButtonOnOnboardingScreen();
        verifyHomepage();
        clickOnListIcon();
        clickOnFashionCollection();
        clickOnJacketAndJumpsuitCollection();
        verifyPdp();
        verifyImageStickerOnPDP();
    }


    @Test(priority = 0, groups = {"neovo", "regression"})
    public void checkCartRedirectionAfterClickingOnCartIcon() {
        testId = "MAB-T1017";
        logger.info(testId + " : This test validate Redirection on cart page after clicking on cart icon");
        ExtentTestManager.getTest().setDescription(testId + " : This test validate Redirection on cart page after clicking on cart icon");
        clickNoButtonOnOnboardingScreen();
        verifyHomepage();
        clickProductBannerOnHomePage();
        waitFor(3);
        verifyPdp();
        clickAddToCartOnPDP();
        if (isPlatformNameAndroid) {
            clickConfirmButtonOnConfirmationPopUPAfterAddToCartClick();
        }
        clickCartIconOnPdpPage();
        waitFor(3);
        verifyCartPage();
    }

    @Test(priority = 0, groups = {"neovo", "regression", "iOSAppReset"})
    public void checkUserAbleToAddProductToCart() {
        testId = "MAB-T1019";
        logger.info(testId + " : This test validate user able to add product to cart");
        ExtentTestManager.getTest().setDescription(testId + " :  This test validate user able to add product to cart");
        clickNoButtonOnOnboardingScreen();
        verifyHomepage();
        clickProductBannerOnHomePage();
        waitFor(4);
        verifyPdp();
        String productName = getProductTitleFromPDP();
        clickAddToCartOnPDP();
        if (isPlatformNameAndroid) {
            clickConfirmButtonOnConfirmationPopUPAfterAddToCartClick();
        }
        clickCartIconOnPdpPage();
        waitFor(3);
        verifyCartPage();
        verifyFirstProductTitleOncartPage(productName);
    }

    @Test(priority = 0, groups = {"neovo", "regression", "iOSAppReset"})
    public void verifyIncreaseInProductQuantityFromPDPToCartPage() {
        testId = "MAB-T1009";
        logger.info(testId + " : This test verifies the product count on Cart page when product quantity is increased on PDP page");
        ExtentTestManager.getTest().setDescription(testId + " : This test verifies the product count on Cart page when product quantity is increased on PDP page");
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
        int count = 2;
        increaseProductQuantityOnPDP(count);
        clickAddToCartOnPDP();
        if (isPlatformNameAndroid) {
            clickOnConfirm();
        }
        clickCartIconOnPdpPage();
        verifyCartPage();
        String countPlus=String.format("%d",(count + 1));
        checkItemCount(countPlus);
    }

    @Test(priority = 0, groups = {"sandbox", "regression", "iOSOnly", "iOSAppReset"})
    public void verifyNativeCheckoutThroughBuyNowFlow() {
        testId = "MAB-T1002";
        logger.info(testId + ": This test verifies the Native checkout through Buy Now Flow");
        ExtentTestManager.getTest().setDescription(testId + ": This test verifies the Native checkout through Buy Now Flow");
        selectStore(StoreNameEnum.SANDBOX);
        verifyHomepage();
        clickOnHamburgerButtonFromHomePage();
        clickOnLoginButtonFromHamburgerMenu();
        enterEmailID(testData.getProperty("emailID1"));
        enterPassword(testData.getProperty("password1"));
        clickLogin();
        clickOnProductFromGrid();
        waitFor(4);
        clickOnBuyNowFromPDP();
        verifyCTAPopup();
        clickOnCreditCardCTA();
        checkNativeCheckoutPage();
        waitFor(5);
        if (isPlatformNameAndroid) {
            clickOnPaymentOptionOnCheckoutPage();
        } else {
            clickOnContinueButtonOnCheckoutPage();
        }
        enterCreditCardNumber(testData.getProperty("creditCardNumber"));
        enterFirstNameOnCredit(testData.getProperty("firstName"));
        enterLastNameOnCredit(testData.getProperty("lastName"));
        enterCvvOnCredit(testData.getProperty("cvv"));
        if (isPlatformNameAndroid) {
            enterDateOnCredit(testData.getProperty("dateAndroid"));
            clickOnAddPaymentButtonOnCardDetails();
            clickOnPlaceOrder();
        } else {
            enterDateOnCredit(testData.getProperty("date"));
            clickDoneButtonOnIOSKeyboard();
            clickOnPayNowButton();
        }
        waitFor(8);
        isOrderConfirmationPageDisplayed();
    }

    @Test(priority = 0, groups = {"neovo", "regression"})
    public void verifyProductDescriptionOnPDP() {
        testId = "MAB-T1006";
        logger.info(testId + " This test verifies product description on PDP");
        ExtentTestManager.getTest().setDescription(testId + " This test verifies product description on PDP");
        clickNoButtonOnOnboardingScreen();
        verifyHomepage();
        clickOnListIcon();
        clickOnFashionCollection();
        clickOnDressCollection();
        waitFor(2);
        verifyPlp();
        String productTitle = "White Mini Dress";
        clickProductInPLP(productTitle);
        waitFor(4);
        verifyPdp();
        verifyDescriptionSectionOnPDP();
    }

    @Test(priority = 0, groups = {"neovo", "regression"})
    public void verifyDecreaseInProductQuantityOnPDP() {
        testId = "MAB-T14586";
        logger.info(testId + " : This test verifies the decrease button for product Quantity on PDP page");
        ExtentTestManager.getTest().setDescription(testId + " : This test verifies the decrease button for product Quantity on PDP page");
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
        int count = 2;
        increaseProductQuantityOnPDP(count);
        waitFor(2);
        decreaseProductQuantityOnPDP(count);
        checkProductQuantityOnPDP("1");
    }

    @Test(groups = {"sandbox", "regression", "androidOnly"})
    public void verifySearchRedirectionOnPDPPage() {
        testId = "MAB-T14585";
        logger.info(testId + ": This test verifies search redirection from PDP Page");
        ExtentTestManager.getTest().setDescription(testId + ": This test verifies search redirection from PDP Page");
        selectStore(StoreNameEnum.SANDBOX);
        verifyHomepage();
        clickBannerOnHomePageForSandboxStore();
        waitFor(3);
        verifyPdp();
        clickSearchButtonOnPDPPage();
        waitFor(3);
        checkSearchHeaderOnSandbox();
    }

    @Test(priority = 0, groups = {"neovo", "regression", "androidOnly"})
    public void verifyImageStickersOnRecommendationProductsOnPDP() {
        testId = "MAB-T13934";
        logger.info(testId + " This test validates  user is able to see the product image sticker on PDP page recommendation section");
        ExtentTestManager.getTest().setDescription(testId + " This test validates  user is able to see the product image sticker on PDP page recommendation section");
        clickNoButtonOnOnboardingScreen();
        verifyHomepage();
        clickOnListIcon();
        clickOnFashionCollection();
        clickOnDressCollection();
        verifyPlp();
        selectProductFromPLP();
        waitFor(1.2);
        verifyPdp();
        if (isPlatformNameAndroid) {
            scrollToText("Related");
        } else {
            scrollForIos();
        }
        verifyImageStickerOnPDP();
    }

    @Test(priority = 0, groups = {"neovo", "regression"})
    public void verifyAdditionallabelv2calculatioOnPDP() {
        testId = "MAB-T35108";
        String additionalLabelAndroid = "Extra Rs. 89.10 at Checkout Rs. 108.90 and Rs. 99.00 or Rs. 9.90";
        logger.info(testId + " : This test validate additional label v2 price calculation  on the pdp page");
        ExtentTestManager.getTest().setDescription(testId + " : This test validate additional label v2 price calculation  on the pdp page");
        clickNoButtonOnOnboardingScreen();
        verifyHomepage();
        clickOnListIcon();
        clickOnFashionCollection();
        clickOnJacketAndJumpsuitCollection();
        waitFor(8);
        verifyAdditinalLabelv2Calculation(additionalLabelAndroid);
    }

    @Test(priority = 0, groups = {"neovo", "regression", "iOSAppReset"})
    public void verifyVariantTitleOnPdp() {
        testId = "MAB-T14584";
        String productTitle = "Alice Green Jumpsuit";
        String productTitle2 = "Coral Print Jumpsuit";
        logger.info(testId + " This test validates variant name on PDP");
        ExtentTestManager.getTest().setDescription(testId + " This test validates variant name on PDP");
        clickNoButtonOnOnboardingScreen();
        verifyHomepage();
        clickProductBannerOnHomePage();
        waitFor(2);
        verifyPdp();
        waitFor(2);
        verifyProductTitleOnPDP(productTitle);
        clickVariantSwitch();
        verifyProductTitleOnPDP(productTitle2);
    }

    @Test(priority = 0, groups = {"sandbox", "regression"})
    public void checkVSKOnPDPPage() {
        testId = "MAB-T1008";
        String product_1 = "Black dress";
        String product_2 = "Regular Ankle Jeans";
        logger.info(testId + ": This test validates VSK and VIA on PDP Page");
        ExtentTestManager.getTest().setDescription(testId + ": This test validates VSK and VIA on PDP Page");
        selectStore(StoreNameEnum.SANDBOX);
        verifyHomepage();
        clickBannerOnHomePageForSandboxStore();
        waitFor(2);
        verifyPdp();
        clickSecondVSKOnPDPPage();
        waitFor(2);
        verifyProductTitleOnPDP(product_1);
        verifyPdp();
        clickThirdVSKOnPDPPage();
        waitFor(2);
        verifyProductTitleOnPDP(product_2);
    }

    @Test(priority = 0, groups = {"sandbox", "regression", "iOSAppReset"})
    public void verifyRecommendationProductOnPDPPage() {
        testId = "MAB-T37298";
        ExtentTestManager.getTest().setDescription(testId + ": This test product recommendation on PDP page");
        selectStore(StoreNameEnum.SANDBOX);
        verifyHomepage();
        clickBannerOnHomePageForSandboxStore();
        waitFor(2);
        verifyPdp();
        if (isPlatformNameAndroid) {
            scrollToText("For You");
        } else {
            scrollForIos();
        }
        verifyProductRecommendationOnPdpPage();
    }


    @Test(priority = 0, groups = {"neovo", "regression"})
    public void verifyUserAbleToSelectVariantUsingVIA() {
        testId = "MAB-T1015";
        logger.info(testId + " This test validates user able to invoke product variants when VIA is enabled");
        ExtentTestManager.getTest().setDescription(testId + " This test validates user able to invoke product variants when VIA is enabled");
        clickNoButtonOnOnboardingScreen();
        verifyHomepage();
        clickOnListIcon();
        clickOnFashionCollection();
        clickOnVariantProductCollection();
        verifyPdp();
        isAddToCartButtonVisibleOnPdp();
        selectVariantFromPDP("Red");
        selectVariantFromPDP("Blue");
        selectVariantFromPDP("Red & Blue");
    }

    @Test(priority = 0, groups = {"neovo", "regression", "iOSFailure", "androidFailure", "iOSAppReset"})
    public void verifySoldOutProductAbleToAddToFavoritesOnPDP() {
        String variantM = "M";
        String variantL = "L";
        String productTitle = "White Mini Dress";
        testId = "MAB-T33297";
        logger.info(testId + "This test verifies whether the sold product is able to be added to favorites from the PDP page.");
        ExtentTestManager.getTest().setDescription(testId + "This test verifies whether the sold product is able to be added to favorites from the PDP page.");
        selectStore(StoreNameEnum.NEOVO);
        clickNoButtonOnOnboardingScreen();
        verifyHomepage();
        clickOnListIcon();
        clickOnFashionCollection();
        clickOnDressCollection();
        verifyPlp();
        waitFor(2);
        clickProductInPLP(productTitle);
        waitFor(4);
        verifyPdp();
        waitFor(.5);
        String productName = pDpPage.getProductTitleOnPdp().getText();
        isAddToCartButtonVisibleOnPdp();
        waitFor(0.5);
        isAddToCartButtonVisibleOnPdp();
        selectVariantFromPDP(variantM);
        clickAddToCartOnPDP();
        if (isPlatformNameAndroid) {
            clickOnConfirm();
        }
        waitFor(4);
        selectVariantFromPDP(variantL);
        waitFor(4);
        clickAddToFavoriteIconOnPDPPage();
        clickBackArrowOnNativeApp();
        clickBackArrowOnNativeApp();
        clickBackArrowOnNativeApp();
        clickProfileTabOnHomePage();
        verifyMyAccountPage();
        clickFavoriteFeatureOnProfilePage();
        waitFor(2);
        verifyProductOnFavoritePage(productName);
    }
}

