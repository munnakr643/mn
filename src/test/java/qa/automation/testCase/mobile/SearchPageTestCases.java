package qa.automation.testCase.mobile;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;
import qa.automation.base.MobileBaseTest;
import qa.automation.enums.StoreNameEnum;
import qa.automation.report.ExtentTestManager;

public class SearchPageTestCases extends MobileBaseTest {

    private static final Logger logger= Logger.getLogger(SearchPageTestCases.class);

    @Test(priority = 1, groups = {"neovo","regression"})
    public void verifyAlgoliaSearchSuggestedProduct() {
        testId = "MAB-T15121";
        logger.info(testId + " : This test validates Algolia Search Suggested Product On plp Page");
        ExtentTestManager.getTest().setDescription(testId + " : This test validates Algolia Search Suggested Product On plp Page");
        clickNoButtonOnOnboardingScreen();
        verifyHomepage();
        clickOnSearchIcon();
        clickOnSearchIcon();
        enterSearchText("Dress");
        verifySearchRelatedPlp("Dress");
    }

    @Test(priority = 1, groups = {"neovo","regression"})
    public void verifySearchResultResponseTime(){
        testId="MAB-T15115";
        String product = "Jade Jacket";
        logger.info(testId +" : This test validates the search result response time with limit of 3 seconds");
        ExtentTestManager.getTest().setDescription(testId +" :This test validates the search result response time with limit of 3 seconds");
        clickNoButtonOnOnboardingScreen();
        verifyHomepage();
        clickOnSearchIcon();
        waitFor(1);
        clickOnSearchIcon();
        waitFor(3);
        enterTextOnSearchboxOnCollectionPage(product);
        waitFor(3);
        isProductPresentOnPLP(product);
    }

    @Test(priority = 1, groups = {"neovo", "androidFailure", "regression"})
    public void verifySearchResultUpdatedKeyword(){
        testId="MAB-T15124";
        String product1 = "Jade Jacket";
        String product2 = "Rachel Dress";
        logger.info(testId +" : This test validates the search result with updated keyword");
        ExtentTestManager.getTest().setDescription(testId +" :This test validates the search result with updated keyword");
        clickNoButtonOnOnboardingScreen();
        verifyHomepage();
        clickOnSearchIcon();
        waitFor(1);
        clickOnSearchIcon();
        waitFor(3);
        enterTextOnSearchboxOnCollectionPage(product1);
        waitFor(3);
        isProductPresentOnPLP(product1);
        clearSearchBoxInCollectionPage();
        enterTextOnSearchboxOnCollectionPage(product2);
        waitFor(3);
        isProductPresentOnPLP(product2);
    }

    @Test(priority = 1, groups = {"neovo", "androidFailure", "regression"})
    public void verifySearchResultResponseProductCount(){
        testId="MAB-T15122";
        String product = "Dress";
        logger.info(testId +" : This test validates the search result displays correctly after scrolling.");
        ExtentTestManager.getTest().setDescription(testId +" : This test validates the search result displays correctly after scrolling");
        clickNoButtonOnOnboardingScreen();
        verifyHomepage();
        clickOnSearchIcon();
        waitFor(1);
        clickOnSearchIcon();
        waitFor(2);
        enterTextOnSearchboxOnCollectionPage(product);
        if(isPlatformNameAndroid)
            mobileDriver.hideKeyboard();
        else{
            clickSearchButtonOnIOSKeyboard();
        }
        waitFor(3);
        isSearchResultLoaded(product, 3);
        swipe(250, 666, 250, 370);
        isSearchResultLoaded(product, 3);
    }

    @Test(priority = 1, groups = {"neovo","regression"})
    public void verifyPricesDisplayedInSearchPage(){
        testId="MAB-T15131";
        String product = "Jade Jacket";
        logger.info(testId +" : This test verifies that prices are displayed in search page");
        ExtentTestManager.getTest().setDescription(testId +" : This test verifies that prices are displayed in search page");
        clickNoButtonOnOnboardingScreen();
        verifyHomepage();
        clickOnSearchIcon();
        waitFor(1);
        clickOnSearchIcon();
        waitFor(3);
        enterTextOnSearchboxOnCollectionPage(product);
        waitFor(3);
        isProductPricePresentOnPLP(product);
    }

    @Test(priority = 1, groups = {"sandbox","regression"})
    public void verifyPDPPageThroughProductSearch(){
        testId="MAB-T15125";
        logger.info(testId +" : This test verifies that prices are displayed in search page");
        ExtentTestManager.getTest().setDescription(testId +" : This test verifies that prices are displayed in search page");
        selectStore(StoreNameEnum.SANDBOX);
        verifyHomepage();
        clickOnSearchIcon();
        waitFor(3);
        enterTextOnSearchboxOnCollectionPage("Dress");
        waitFor(3);
        isProductPricePresentOnPLP("Dress");
        clickFirstProductFromPLP();
        verifyPdp();
        verifyProductTitleOnPDP("Dress");
    }

    @Test(priority = 0, groups = {"neovo", "regression"})
    public void verifyDiscountOnSearchedProducts() {
        String product = "Dress";
        testId = "MAB-T15149";
        logger.info(testId + " : This test validates discount should appear for searched products");
        ExtentTestManager.getTest().setDescription(testId + " : This test validates discount should appear for searched products");
        clickNoButtonOnOnboardingScreen();
        verifyHomepage();
        clickOnSearchIcon();
        clickOnSearchIcon();
        waitFor(2);
        enterTextOnSearchboxOnCollectionPage(product);
        if(isPlatformNameAndroid)
            mobileDriver.hideKeyboard();
        else{
            clickSearchButtonOnIOSKeyboard();
        }
        verifyDiscountOnSearchPage();
    }

    @Test(priority = 0, groups = {"neovo", "iOSFailure", "androidFailure", "regression"})
    public void verifyAdditionalLabelForLiquidSearchOnSearchedProducts() {
        String product = "allegra";
        testId = "MAB-T15156";
        String additionalLabelAndroid="Extra Rs. 90.00 at Checkout Rs. 110.00 and Rs. 100.00 or Rs. 10.00";
        String additionalLabelIOS= "Extra Rs. 90.00  at Checkout Rs. 110.00  and Rs. 100.00  or Rs. 10.00 ";
        logger.info(testId + " : This test validates additional label(format v2) showing or not on search page when the liquid search integration is enable");
        ExtentTestManager.getTest().setDescription(testId + " : This test validates additional label(format v2) showing or not on search page when the liquid search integration is enable");
        clickNoButtonOnOnboardingScreen();
        verifyHomepage();
        clickOnSearchIcon();
        clickOnSearchIcon();
        waitFor(2);
        enterTextOnSearchboxOnCollectionPage(product);
        waitFor(1);
        if(isPlatformNameAndroid)
            mobileDriver.hideKeyboard();
        else{
            clickSearchButtonOnIOSKeyboard();
        }
        waitFor(5);
        if(isPlatformNameAndroid){
            verifyAdditionalTextOnSearchPage(additionalLabelAndroid);
        } else {
            verifyAdditionalTextOnSearchPage(additionalLabelIOS);
        }

    }

    @Test(priority = 0, groups = {"neovo", "regression"})
    public void verifyImageStickersOnSearchedProducts() {
        String product = "Black";
        testId = "MAB-T15144";
        int i = 0;
        logger.info(testId + " : This test validates image stickers should appear for searched products");
        ExtentTestManager.getTest().setDescription(testId + " : This test validates image stickers should appear for searched products");
        clickNoButtonOnOnboardingScreen();
        verifyHomepage();
        clickOnSearchIcon();
        clickOnSearchIcon();
        waitFor(2);
        enterTextOnSearchboxOnCollectionPage(product);
        if(isPlatformNameAndroid)
            mobileDriver.hideKeyboard();
        else{
            clickSearchButtonOnIOSKeyboard();
        }
        waitFor(5);
        verifyImageStickerOnSearchPage();
    }

    @Test(groups = {"neovo","regression"})
    public void checkAdditionalLabelOnSearchPageResult(){
        testId="MAB-T977";
        String searchText="allegra";
        String additionalLabelAndroid="Extra Rs. 90.00 at Checkout Rs. 110.00 and Rs. 100.00 or Rs. 10.00";
        String additionalLabeliOS= "Extra Rs. 90.00  at Checkout Rs. 110.00  and Rs. 100.00  or Rs. 10.00 ";
        logger.info(testId +" : This test validates Additional label V2 on search result");
        ExtentTestManager.getTest().setDescription(testId +" :This test validates Additional label V2 on search result");
        clickNoButtonOnOnboardingScreen();
        clickOnSearchIcon();
        waitFor(2);
        clickOnSearchIcon();
        enterTextOnSearchboxOnCollectionPage(searchText);
        waitFor(5);
        if(isPlatformNameAndroid){
            verifyAdditionalTextOnSearchPage(additionalLabelAndroid);
        } else {
            verifyAdditionalTextOnSearchPage(additionalLabeliOS);
        }
    }

    @Test(priority = 0, groups = {"neovo","regression","iOSAppReset"})
    public void verifyAdditionallabelv2calculatioOnSearchPage() {
        testId = "MAB-T35109";
        String value="89.10", searchText="i";
        logger.info(testId+" : This test validate additional label v2 price calculation  on the Search Page");
        ExtentTestManager.getTest().setDescription(testId+" : This test validate additional label v2 price calculation  on the Search page");
        clickNoButtonOnOnboardingScreen();
        verifyHomepage();
        clickOnSearchIcon();
        waitFor(2);
        clickOnSearchIcon();
        enterTextOnSearchboxOnCollectionPage(searchText);
        waitFor(5);
        checkAdditionLabelCalculationOnSearchPage(value);
    }

    @Test(priority = 1, groups = {"sandbox", "regression"})
    public void verifyShopifySearchResult(){
        testId="MAB-T37497";
        String product1 = "Maroon Top";
        String product2 = "Skinny High Jeans";
        logger.info(testId +" : This test validates the search result with updated keyword for Shopify Search");
        ExtentTestManager.getTest().setDescription(testId +" :This test validates the search result with updated keyword for Shopify Search");
        selectStore(StoreNameEnum.SANDBOX);
        verifyHomepage();
        clickOnSearchIcon();
        waitFor(3);
        enterTextOnSearchboxOnCollectionPage(product1);
        waitFor(3);
        isProductPresentOnPLP(product1);
        clearSearchBoxInCollectionPage();
        enterTextOnSearchboxOnCollectionPage(product2);
        waitFor(3);
        isProductPresentOnPLP(product2);
    }

    @Test(priority = 1, groups = {"sandbox", "regression"})
    public void verifyAbleToAddProductInWishlistFromSearch(){
        testId="MAB-T33300";
        String searchText="zee";
        logger.info(testId +" : This test validates User able to add the product into wishlist from search page");
        ExtentTestManager.getTest().setDescription(testId +" :This test validates User able to add the product into wishlist from search page");
        selectStore(StoreNameEnum.SANDBOX);
        verifyHomepage();
        clickOnSearchIcon();
        waitFor(3);
        enterTextOnSearchboxOnCollectionPage(searchText);
        waitFor(7);
        isProductPricePresentOnPLP("Zee Self Design Round Neck top");
        ClickOnWishlisticonFromSearchPage();
        clickBackButtonIconOnSearchPage();
        waitFor(7);
        verifyHomepage();
        waitFor(7);
        clickProfileTabOnHomepageForSandbox();
        clickFavoriteFeatureOnProfilePage();
        waitFor(5);
        isFavoritePageLoaded();
        waitFor(3);
        verifyProductOnFavoritePage("Zee Self Design Round Neck top");
    }
}
