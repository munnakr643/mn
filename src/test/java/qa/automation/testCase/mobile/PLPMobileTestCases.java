package qa.automation.testCase.mobile;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;
import qa.automation.base.MobileBaseTest;
import qa.automation.enums.StoreNameEnum;
import qa.automation.report.ExtentTestManager;
import qa.automation.enums.FilterOptionsEnum;
import qa.automation.enums.SortingOptionsEnum;

public class PLPMobileTestCases extends MobileBaseTest {
    private static final Logger logger = Logger.getLogger(PLPMobileTestCases.class);


    @Test(priority = 0, groups = {"neovo", "regression"})
    public void verifyPriceHighToLowSorting() {
        testId = "MAB-T985";
        logger.info(testId + " This test validates price high to low sorting and its toast message");
        ExtentTestManager.getTest().setDescription(testId + "This test validates price high to low sorting and its toast message");
        clickNoButtonOnOnboardingScreen();
        verifyHomepage();
        clickOnListIcon();
        clickOnFashionCollection();
        clickOnDressCollection();
        waitFor(3); //Allowing all the products to load in the PLP Page
        clickOnSortButtonInPLP();
        selectSortingType(SortingOptionsEnum.PRICE_HIGH_TO_LOW);
        verifySortingToastMessage(SortingOptionsEnum.PRICE_HIGH_TO_LOW);
        verifyPriceSorting(SortingOptionsEnum.PRICE_HIGH_TO_LOW);
    }

    @Test(priority = 0, groups = {"neovo", "iOSFailure", "regression"})
    public void verifyPriceLowToHighSorting() {
        testId = "MAB-T27629";
        logger.info(testId + " This test validates price low to high sorting and its toast message");
        ExtentTestManager.getTest().setDescription(testId + " This test validates price low to high sorting and its toast message");
        clickNoButtonOnOnboardingScreen();
        verifyHomepage();
        clickOnListIcon();
        clickOnFashionCollection();
        clickOnDressCollection();
        waitFor(3); //Allowing all the products to load in the PLP Page
        clickOnSortButtonInPLP();
        selectSortingType(SortingOptionsEnum.PRICE_LOW_TO_HIGH);
        verifySortingToastMessage(SortingOptionsEnum.PRICE_LOW_TO_HIGH);
        verifyPriceSorting(SortingOptionsEnum.PRICE_LOW_TO_HIGH);
    }

    @Test(priority = 0, groups = {"neovo", "regression"})
    public void verifyAlphabeticalAToZSorting() {
        testId = "MAB-T27630";
        logger.info(testId + " This test validates Alphabetical sorting from A to Z and its toast message");
        ExtentTestManager.getTest().setDescription(testId + " This test validates Alphabetical sorting from A to Z and its toast message");
        clickNoButtonOnOnboardingScreen();
        verifyHomepage();
        clickOnListIcon();
        clickOnFashionCollection();
        clickOnDressCollection();
        waitFor(3); //Allowing all the products to load in the PLP Page
        clickOnSortButtonInPLP();
        selectSortingType(SortingOptionsEnum.ALPHABETICALLY_A_TO_Z);
        verifySortingToastMessage(SortingOptionsEnum.ALPHABETICALLY_A_TO_Z);
        verifyTitleSorting(SortingOptionsEnum.ALPHABETICALLY_A_TO_Z);
    }

    @Test(priority = 0, groups = {"neovo", "regression"})
    public void verifyAlphabeticalZToASorting() {
        testId = "MAB-T27631";
        logger.info(testId + " This test validates Alphabetical sorting from Z to A and its toast message");
        ExtentTestManager.getTest().setDescription(testId + " This test validates Alphabetical sorting from Z to A and its toast message");
        clickNoButtonOnOnboardingScreen();
        verifyHomepage();
        clickOnListIcon();
        clickOnFashionCollection();
        clickOnDressCollection();
        waitFor(3); //Allowing all the products to load in the PLP Page
        clickOnSortButtonInPLP();
        selectSortingType(SortingOptionsEnum.ALPHABETICALLY_Z_TO_A);
        verifySortingToastMessage(SortingOptionsEnum.ALPHABETICALLY_Z_TO_A);
        verifyTitleSorting(SortingOptionsEnum.ALPHABETICALLY_Z_TO_A);
    }

    @Test(priority = 0, groups = {"neovo", "regression"})
    public void verifyDateNewToOldSorting() {
        testId = "MAB-T27632";
        logger.info(testId + " This test validates date sorting from new to old and its toast message");
        ExtentTestManager.getTest().setDescription(testId + " This test validates date sorting from new to old and its toast message");
        clickNoButtonOnOnboardingScreen();
        verifyHomepage();
        clickOnListIcon();
        clickOnFashionCollection();
        clickOnDressCollection();
        waitFor(3); //Allowing all the products to load in the PLP Page
        clickOnSortButtonInPLP();
        selectSortingType(SortingOptionsEnum.BY_DATE_NEW_TO_OLD);
        verifySortingToastMessage(SortingOptionsEnum.BY_DATE_NEW_TO_OLD);
    }

    @Test(priority = 0, groups = {"neovo", "regression"})
    public void verifyDateOldToNewSorting() {
        testId = "MAB-T27633";
        logger.info(testId + " This test validates date sorting from Old to new and its toast message");
        ExtentTestManager.getTest().setDescription(testId + " This test validates date sorting from Old to new and its toast message");
        clickNoButtonOnOnboardingScreen();
        verifyHomepage();
        clickOnListIcon();
        clickOnFashionCollection();
        clickOnDressCollection();
        waitFor(3); //Allowing all the products to load in the PLP Page
        clickOnSortButtonInPLP();
        selectSortingType(SortingOptionsEnum.BY_DATE_OLD_TO_NEW);
        verifySortingToastMessage(SortingOptionsEnum.BY_DATE_OLD_TO_NEW);
    }

    @Test(priority = 0, groups = {"neovo", "regression"})
    public void verifyBestsellingSorting() {
        testId = "MAB-T27634";
        logger.info(testId + "MAB-T985 This test validates bestselling sorting toast message");
        ExtentTestManager.getTest().setDescription(testId + "MAB-T985 This test validates bestselling sorting toast message");
        clickNoButtonOnOnboardingScreen();
        verifyHomepage();
        clickOnListIcon();
        clickOnFashionCollection();
        clickOnDressCollection();
        waitFor(3); //Allowing all the products to load in the PLP Page
        clickOnSortButtonInPLP();
        selectSortingType(SortingOptionsEnum.BESTSELLING);
        verifySortingToastMessage(SortingOptionsEnum.BESTSELLING);
    }

    @Test(priority = 0, groups = {"neovo", "regression"})
    public void verifyManuallySorting() {
        testId = "MAB-T27635";
        logger.info(testId + " This test validates Manually sorting toast message");
        ExtentTestManager.getTest().setDescription(testId + "MAB-T985 This test validates bestselling Manually toast message");
        clickNoButtonOnOnboardingScreen();
        verifyHomepage();
        clickOnListIcon();
        clickOnFashionCollection();
        clickOnDressCollection();
        waitFor(3); //Allowing all the products to load in the PLP Page
        clickOnSortButtonInPLP();
        selectSortingType(SortingOptionsEnum.MANUALLY);
        verifySortingToastMessage(SortingOptionsEnum.MANUALLY);
    }


    @Test(priority = 0, groups = {"neovo", "regression"})
    public void verifyFilterOptionsOnPlpPage() {
        testId = "MAB-T14596";
        logger.info(testId + " : This test validates Filter Options On PlpPage");
        ExtentTestManager.getTest().setDescription(testId + " : This test validates Filter Options On PlpPage");
        clickNoButtonOnOnboardingScreen();
        verifyHomepage();
        clickOnListIcon();
        clickOnFashionCollection();
        clickOnDressCollection();
        clickOnFilterButtonInPLP();
        verifyFilterOptions();

    }

    @Test(priority = 0, groups = {"neovo", "regression"})
    public void verifySortingAndFilterCombination() {
        testId = "MAB-T14604";
        String product = "Jade Jacket";
        logger.info(testId + " This test validates combination of filter with sorting");
        ExtentTestManager.getTest().setDescription(testId + " This test validates combination of filter with sorting");
        clickNoButtonOnOnboardingScreen();
        verifyHomepage();
        clickOnListIcon();
        clickOnFashionCollection();
        clickOnDressCollection();
        waitFor(3); //Allowing all the products to load in the PLP Page
        clickOnSortButtonInPLP();
        selectSortingType(SortingOptionsEnum.PRICE_LOW_TO_HIGH);
        verifySortingToastMessage(SortingOptionsEnum.PRICE_LOW_TO_HIGH);
        clickOnFilterButtonInPLP();
        verifyFilterOptions();
        selectFilterOption(FilterOptionsEnum.IN_STOCk);
        clickApplyFilterButton();
        waitFor(3); //wait for products to load
        isProductPresentOnPLP(product);
    }


    @Test(priority = 0, groups = {"sandbox", "regression", "iOSAppReset"})
    public void verifyShopTheLookButtonOnPLP() {
        testId = "MAB-T14733";
        logger.info(testId + ": This test validates Shop the look button must be available on PLP");
        ExtentTestManager.getTest().setDescription(testId + ": : This test validates Shop the look button must be available on PLP");
        selectStore(StoreNameEnum.SANDBOX);
        verifyHomepage();
        ClickOnListIconForSandbox();
        clickOnDressCollectionForSandbox();
        if (isPlatformNameAndroid) {
            scrollToText("Shop The Look");
        }
        checkShopTheLookOnPLP();
    }

    @Test(priority = 0, groups = {"neovo", "regression"})
    public void verifyProductLoadTimeOnPLPPage() {
        testId = "MAB-T982";
        String product = "White Mini Dress";
        logger.info(testId + " This test validates the product load time in the PLP page");
        ExtentTestManager.getTest().setDescription(testId + " This test validates product load time in the PLP page");
        clickNoButtonOnOnboardingScreen();
        verifyHomepage();
        clickOnListIcon();
        clickOnFashionCollection();
        clickOnDressCollection();
        waitFor(3);
        isProductPresentOnPLP(product);
        isProductAddToFavoritesBtnPresentOnPLP(product);
        isProductAddToCartBtnPresentOnPLP(product);
        isProductPricePresentOnPLP(product);
    }


    @Test(priority = 0, groups = {"neovo", "regression"})
    public void verifyProductPriceOnPLP() {
        testId = "MAB-T14632";
        logger.info(testId + " This test validates product price on PLP");
        ExtentTestManager.getTest().setDescription(testId + " This test validates product price on PLP");
        clickNoButtonOnOnboardingScreen();
        verifyHomepage();
        clickOnListIcon();
        clickOnFashionCollection();
        clickOnDressCollection();
        waitFor(1);
        isProductPriceOnPLP();
    }

    @Test(priority = 0, groups = {"neovo", "regression"})
    public void verifyProductTitleOnPLP() {
        testId = "MAB-T14633";
        logger.info(testId + " This test validates product Title on PLP");
        ExtentTestManager.getTest().setDescription(testId + " This test validates product Title on PLP");
        clickNoButtonOnOnboardingScreen();
        verifyHomepage();
        clickOnListIcon();
        clickOnFashionCollection();
        clickOnDressCollection();
        isProductTitleOnPLPDisplay("Dress");

    }

    @Test(priority = 0, groups = {"sandbox", "regression", "iOSAppReset"})
    public void isAddToCartPopupVisibleFromPLP() {
        testId = "MAB-T983";
        logger.info(testId + " This test validates whether user able to add any variant from variant chooser");
        ExtentTestManager.getTest().setDescription(testId + " This test validates whether user able to add any variant from variant chooser");
        selectStore(StoreNameEnum.SANDBOX);
        verifyHomepage();
        ClickOnListIconForSandbox();
        clickOnDressCollectionForSandbox();
        waitFor(3);
        verifyPlp();
        clickOnAddToCartIconOnPlp();
        waitFor(1);
        if (!isPlatformNameAndroid) {
            clickOnAddToCartIconOnPlp();
        }
        verifyAddToCartPopupOnPlp();
    }

    @Test(priority = 0, groups = {"sandbox", "regression", "iOSFailure", "androidFailure", "iOSAppReset"})
    public void isSoldoutMessegeVisibleOnPLP() {
        testId = "MAB-T14628";
        logger.info(testId + " This test validates whether sold out messege is loading for sold variant");
        ExtentTestManager.getTest().setDescription(testId + " This test validates whether sold out messege is loading for sold variant");
        selectStore(StoreNameEnum.NEOVO);
        clickNoButtonOnOnboardingScreen();
        verifyHomepage();
        clickOnListIcon();
        clickOnFashionCollection();
        clickOnDressCollection();
        waitFor(3);
        clickAddToCartIconOnPLPPage();
        verifyAddToCartPopupOnPlp();
        clickOnLVarientFromAddToCartPopupOnPlp();
        clickOnAddToCartButtonFromAddToCartPopupOnPlp();
        waitFor(0.5);
        verifyToastMessage("Item is currently sold out. Please try again later.",3);
    }

    @Test(priority = 0, groups = {"sandbox", "regression"})
    public void isUiElementsVisibleAfterSorting() {
        testId = "MAB-T991";
        logger.info(testId + " This test validates whether all product UI contents are loading on PLP after applying Sort");
        ExtentTestManager.getTest().setDescription(testId + " This test validates whether all product UI contents are loading on PLP after applying Sort");
        selectStore(StoreNameEnum.NEOVO);
        clickNoButtonOnOnboardingScreen();
        verifyHomepage();
        clickOnListIcon();
        clickOnFashionCollection();
        clickOnDressCollection();
        waitFor(3);
        verifyPlp();
        clickOnSortButtonInPLP();
        selectSortingType(SortingOptionsEnum.ALPHABETICALLY_A_TO_Z);
        verifySortingToastMessage(SortingOptionsEnum.ALPHABETICALLY_A_TO_Z);
        verifyTitleSorting(SortingOptionsEnum.ALPHABETICALLY_A_TO_Z);
        verifyPlp();
    }

    @Test(priority = 0, groups = {"neovo", "regression"})
    public void isUiElementsVisibleAfterFilters() {
        testId = "MAB-T987";
        logger.info(testId + " This test validates whether all product UI contents are loading on PLP after applying filter");
        ExtentTestManager.getTest().setDescription(testId + " This test validates whether all product UI contents are loading on PLP after applying filter");
        clickNoButtonOnOnboardingScreen();
        verifyHomepage();
        clickOnListIcon();
        clickOnFashionCollection();
        clickOnDressCollection();
        waitFor(3);
        verifyPlp();
        clickOnFilterButtonInPLP();
        verifyFilterOptions();
        selectFilterOption(FilterOptionsEnum.IN_STOCk);
        clickApplyFilterButton();
        waitFor(2);
        verifyPlp();
    }

    @Test(priority = 0, groups = {"neovo", "regression"})
    public void verifyImageStickersOnPLP() {
        testId = "MAB-T14104";
        logger.info(testId + " This test validates user is able to see the product image sticker on PLP");
        ExtentTestManager.getTest().setDescription(testId + " This test validates user is able to see the product image sticker on PLP");
        clickNoButtonOnOnboardingScreen();
        verifyHomepage();
        clickOnListIcon();
        clickOnFashionCollection();
        clickOnDressCollection();
        verifyPlp();
        verifyImageStickerOnPLP();
    }


    @Test(priority = 0, groups = {"sandbox", "regression", "iOSAppReset"})
    public void verifyQuickAddToCartFromPLP() {
        testId = "MAB-T33178";
        logger.info(testId + " This test validates user is able to quick add the  product from plp ");
        ExtentTestManager.getTest().setDescription(testId + " This test validates user is able to quick add the product from plp");
        selectStore(StoreNameEnum.SANDBOX);
        verifyHomepage();
        ClickOnListIconForSandbox();
        clickOnDressCollectionForSandbox();
        waitFor(1);
        verifyPlp();
        clickOnAddToCartIconOnPlp();
        verifyAddToCartPopupOnPlp();
        clickOnAddToCartButtonFromAddToCartPopupOnPlp();
        if (isPlatformNameAndroid) {
            clickOnCartIconOnPlP();
        } else {
            cooClick(356, 72);//This is temporary solution
        }
        verifyCartPage();
        isDecreaseProductQuantityDisplayOnCart();
        isIncreaseProductQuantityDisplayOnCart();
    }

    @Test(priority = 0, groups = {"neovo", "regression", "iOSAppReset"})
    public void verifyAdditionallabelv2calculatioOnPlp() {
        testId = "MAB-T35109";
        String value = "89.10";
        logger.info(testId + " : This test validate additional label v2 price calculation  on the PLP");
        ExtentTestManager.getTest().setDescription(testId + " : This test validate additional label v2 price calculation  on the PLP");
        clickNoButtonOnOnboardingScreen();
        verifyHomepage();
        clickOnListIcon();
        clickOnFashionCollection();
        clickOnDressCollection();
        verifyPlp();
        checkAdditionLabelCalculationOnPlp(value);
    }

    @Test(priority = 0, groups = {"neovo", "regression", "iOSAppReset"})
    public void verifyContentOnPLPPage() {
        testId = "MAB-T1003";
        logger.info(testId + " : This test validate Content On PLP as well as Add To favorite and Quick Add to cart Functionality on PLP Page");
        ExtentTestManager.getTest().setDescription(testId + " : This test validate Content On PLP as well as Add To favorite and Quick Add to cart Functionality on PLP Page");
        clickNoButtonOnOnboardingScreen();
        verifyHomepage();
        clickOnListIcon();
        clickOnFashionCollection();
        clickOnDressCollection();
        verifyPlp();
        String productTitle = fetchProductTitleFromPLPPage();
        clickAddToFavoriteIconOnPLPPage();
        waitFor(3);
        clickAddToCartIconOnPLPPage();
        if (isPlatformNameAndroid) {
            clickOnAddToCartButtonFromAddToCartPopupOnPlp();
        }
        clickBackButtonIconOnCollectionPage();
        clickBackButtonIconOnCollectionPage();
        clickProfileTabOnHomePage();
        clickFavoriteFeatureOnProfilePage();
        waitFor(2);
        verifyProductOnFavoritePage(productTitle);
        clickBackButtonIconOnFavoritesPage();
        clickCartTabOnHomePage();
        verifyCartPage();
        verifyProductInCartpage(productTitle);
    }

    @Test(priority = 0, groups = {"neovo", "regression"})
    public void verifyClearFilteriSWorking() {
        testId = "MAB-T14610";
        String product = "Lunar Bisou Top";
        logger.info(testId + " This test validates clear option is working on PLP filters");
        ExtentTestManager.getTest().setDescription(testId + " This test validates clear option is working on PLP filters");
        clickNoButtonOnOnboardingScreen();
        verifyHomepage();
        clickOnListIcon();
        clickOnFashionCollection();
        clickOnTopsCollection();
        waitFor(3); //Allowing all the products to load in the PLP Page
        clickOnFilterButtonInPLP();
        verifyFilterOptions();
        selectFilterOption(FilterOptionsEnum.OUT_OF_STOCK);
        clickApplyFilterButton();
        waitFor(3); //wait for products to load
        checkEmptyResultOnPLP();
        clickOnFilterButtonInPLP();
        clickClearButtonOnFilterPage();
        clickApplyFilterButton();
        waitFor(2);
        isProductPresentOnPLP(product);
    }

    @Test(priority = 0, groups = {"neovo", "regression"})
    public void verifySortingAfterApplyingFilter() {
        testId = "MAB-T988";
        String product = "Lunar Bisou Top";
        logger.info(testId + " This test validates to check behaviour on PLP after applying filter and then sort");
        ExtentTestManager.getTest().setDescription(testId + " This test validates to check behaviour on PLP after applying filter and then sort");
        clickNoButtonOnOnboardingScreen();
        verifyHomepage();
        clickOnListIcon();
        clickOnFashionCollection();
        clickOnTopsCollection();
        waitFor(3); //Allowing all the products to load in the PLP Page
        clickOnFilterButtonInPLP();
        verifyFilterOptions();
        selectFilterOption(FilterOptionsEnum.IN_STOCk);
        clickApplyFilterButton();
        waitFor(3); //wait for products to load
        isProductPresentOnPLP(product);
        clickOnSortButtonInPLP();
        selectSortingType(SortingOptionsEnum.PRICE_HIGH_TO_LOW);
        verifySortingToastMessage(SortingOptionsEnum.PRICE_HIGH_TO_LOW);
        verifyPriceSorting(SortingOptionsEnum.PRICE_HIGH_TO_LOW);
    }
}


