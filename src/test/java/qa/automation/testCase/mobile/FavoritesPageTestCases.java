package qa.automation.testCase.mobile;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;
import qa.automation.base.MobileBaseTest;
import qa.automation.enums.StoreNameEnum;
import qa.automation.pageScreenDevice.FavoritePage;
import qa.automation.report.ExtentTestManager;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class FavoritesPageTestCases extends MobileBaseTest {

    private static final Logger logger = Logger.getLogger(NewOrderConfirmationPageTestCases.class);

    @Test(priority = 0, groups = {"neovo", "regression", "iOSAppReset"})
    public void verifyProductsAddedToFavorites() {
        testId = "MAB-T15150";
        logger.info(testId + " This test validates products added to Favorites.");
        ExtentTestManager.getTest().setDescription(testId + " This test validates products added to Favorites.");
        String firstProductTitle = "White Mini Dress";
        clickNoButtonOnOnboardingScreen();
        verifyHomepage();
        clickOnListIcon();
        clickOnFashionCollection();
        clickOnDressCollection();
        waitFor(3); //Allowing all the products to load in the PLP Page
        addProductToFavoritesOnPLP(firstProductTitle);
        clickBackButtonIconOnTopLeft();
        waitFor(2); //Allowing page elements to load
        clickBackButtonIconOnCollectionPage();
        clickProfileTabOnHomePage();
        clickFavoriteFeatureOnProfilePage();
        isFavoritePageLoaded();
        verifyProductTitleOnFavoritesPage(firstProductTitle);

    }


    @Test(priority = 0, groups = {"neovo", "regression", "iOSAppReset"})
    public void verifyAddMoreFromFavouritesFromCartPage() {
        testId = "MAB-T15162";
        logger.info(testId + " This test validates products added to Favorites from Cart page's Add more from Favourites button");
        ExtentTestManager.getTest().setDescription(testId + " This test validates products added to Favorites from Cart page's Add more from Favourites button");
        String productTitle = "White Mini Dress";
        clickNoButtonOnOnboardingScreen();
        verifyHomepage();
        clickOnListIcon();
        clickOnFashionCollection();
        clickOnDressCollection();
        waitFor(3); //Allowing all the products to load in the PLP Page
        if (!isPlatformNameAndroid)
            addProductToFavoritesOnPLP(productTitle);
        clickProductInPLP(productTitle);
        waitFor(7); //wait for PDP to load
        if (isPlatformNameAndroid)
            clickAddToFavoriteIconOnPDPPage();
        clickAddToCartOnPDP();
        if (isPlatformNameAndroid) {
            clickOnConfirm();
        } else {
            waitFor(2);
        }
        clickCartIconOnPdpPage();
        verifyCartPage();
        clickAddMoreFromFavoriteButtonInCartPage();
        isFavoritePageLoaded();
        verifyProductTitleOnFavoritesPage(productTitle);
        clickAddToCartOnFavoritesPage(productTitle);
        clickAddToCartPopUpOnFavoritesPage();
        clickBackButtonIconOnFavoritesPage();
        waitFor(5);
        verifyCartPageProductsQuantity(productTitle, "2");

    }

    @Test(priority = 1, groups = {"sandbox", "regression", "iOSAppReset"})
    public void verifyUserAbletoAddproductToFavoriteFromHomepage() {
        testId = "MAB-T33299";
        logger.info(testId + "This test validates user able to add product to favorite from Homepage");
        ExtentTestManager.getTest().setDescription(testId + " This test validates user able to add product to favorite from Homepage");
        String productName = "High Waist Jeans";
        selectStore(StoreNameEnum.SANDBOX);
        verifyHomepage();
        if (isPlatformNameAndroid) {
            scrollToText("Rs. 75.00 + Rs. 7.50");
        } else {
            scrollForIos();
        }
        clickOnFavoriteButtonOnHomepage();
        clickOnHamburgerButtonFromHomePage();
        clickOnMyProfileFromHamburger();
        clickFavoriteFeatureOnProfilePage();
        waitFor(2);
        isFavoritePageLoaded();
        verifyProductOnFavoritePage(productName);
    }


    @Test(groups = {"neovo", "regression", "iOSAppReset"})
    public void verifyUserABleToAddProductToFavoriteFromPLP() {
        String favProduct = "White Mini Dress";
        testId = "MAB-T33301";
        logger.info(testId + " This test validate whether user is able to add product to favourite from plp page ");
        ExtentTestManager.getTest().setDescription(testId + " This test validate whether user is able to add product to favourite from plp page  ");
        clickNoButtonOnOnboardingScreen();
        verifyHomepage();
        clickOnListIcon();
        clickOnFashionCollection();
        clickOnDressCollection();
        verifyPlp();
        addProductToFavoritesOnPLP(favProduct);
        clickBackArrowOnNativeApp();
        clickBackButtonIconOnTopLeft();
        clickOnHamburgerButtonFromHomePage();
        clickOnMyProfileFromHamburger();
        clickFavoriteFeatureOnProfilePage();
        isFavoritePageLoaded();
        waitFor(1);
        verifyProductOnFavoritePage(favProduct);
    }

    @Test(groups = {"neovo", "regression" ,"iOSAppReset"})
    public void verifyUserAbleToAddProductToFavoriteFromPDP() {
        String favProduct = "White Mini Dress";
        testId = "MAB-T33284";
        logger.info(testId + " This test validate whether user is able to add product to favourite from PDP ");
        ExtentTestManager.getTest().setDescription(testId + " This test validate whether user is able to add product to favourite from PDP ");
        clickNoButtonOnOnboardingScreen();
        verifyHomepage();
        clickOnListIcon();
        clickOnFashionCollection();
        clickOnDressCollection();
        clickFirstProductFromPLP();
        verifyPdp();
        clickAddToFavoriteIconOnPDPPage();
        clickBackButtonIconOnTopLeft();
        waitFor(0.5);
        clickBackButtonIconOnTopLeft();
        waitFor(0.5);
        clickBackButtonIconOnTopLeft();
        waitFor(2);
        clickProfileTabOnHomePage();
        waitFor(2);
        clickFavoriteFeatureOnProfilePage();
        isFavoritePageLoaded();
        waitFor(2);
        verifyProductOnFavoritePage(favProduct);
    }

    @Test(priority = 0,groups = {"neovo","regression", "iOSAppReset"})
    public void verifyProductGetsAddedIntoTheWishlistFromPdp(){
        testId = "MAB-T33302";
        logger.info(testId + " This test validates whether user is able to add product to favourite from pdp");
        ExtentTestManager.getTest().setDescription(testId + " This test validates whether user is able to add product to favourite from pdp");
        clickNoButtonOnOnboardingScreen();
        verifyHomepage();
        clickOnListIcon();
        clickOnFashionCollection();
        clickOnDressCollection();
        verifyPlp();
        selectProductFromPLP();
        waitFor(1.2);
        verifyPdp();
        clickAddToFavoriteIconOnPDPPage();
        clickAddToCartOnPDP();
        if (isPlatformNameAndroid) {
            clickConfirmButtonOnConfirmationPopUPAfterAddToCartClick();
        }
        clickCartIconOnPdpPage();
        waitFor(3);
        verifyCartPage();
        invokeFavoriteFromCartPage();
        checkProductNameOnFavoritePage("White Mini Dress");
    }

    @Test(groups = {"neovo", "regression" ,"iOSAppReset"})
    public void verifyUserAbleToRemoveProductFromFavoritePage() {
        String favProduct = "White Mini Dress";
        testId = "MAB-T33285";
        logger.info(testId + "This test validate whether user is able to remove product from favourite");
        ExtentTestManager.getTest().setDescription(testId + " This test validate whether user is able to remove product from favourite ");
        clickNoButtonOnOnboardingScreen();
        verifyHomepage();
        clickOnListIcon();
        clickOnFashionCollection();
        clickOnDressCollection();
        clickFirstProductFromPLP();
        verifyPdp();
        clickAddToFavoriteIconOnPDPPage();
        clickBackButtonIconOnTopLeft();
        waitFor(0.5);
        clickBackButtonIconOnTopLeft();
        waitFor(0.5);
        clickBackButtonIconOnTopLeft();
        waitFor(2);
        clickProfileTabOnHomePage();
        waitFor(2);
        clickFavoriteFeatureOnProfilePage();
        isFavoritePageLoaded();
        waitFor(2);
        verifyProductOnFavoritePage(favProduct);
        waitFor(2);
        assertThat(favoritePage.getCrossbuttonIcon().isDisplayed(),equalTo(true));
        favoritePage.getCrossbuttonIcon().click();
        waitFor(1);
        if(isPlatformNameAndroid){
            clickYesOnPopup();
        }
        verifyEmptyFavouritePage();
    }
    @Test(groups = {"neovo", "regression", "iOSAppReset"})
    public void verifyQuickAddProductToFavoriteFromPlp() {
        String favProduct = "White Mini Dress";
        testId = "MAB-T33175";
        logger.info(testId + " This test validate whether user is able to quick add product to wishlist from plp page ");
        ExtentTestManager.getTest().setDescription(testId + " This test validates whether user is able to quick add product to wishlist from plp");
        clickNoButtonOnOnboardingScreen();
        verifyHomepage();
        clickOnListIcon();
        clickOnFashionCollection();
        clickOnDressCollection();
        verifyPlp();
        addProductToFavoritesOnPLP(favProduct);
        clickBackArrowOnNativeApp();
        clickBackButtonIconOnTopLeft();
        clickOnHamburgerButtonFromHomePage();
        clickOnMyProfileFromHamburger();
        clickFavoriteFeatureOnProfilePage();
        isFavoritePageLoaded();
        waitFor(1.5);
        verifyProductOnFavoritePage(favProduct);
    }
}



