package qa.automation.testCase.mobile;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;
import qa.automation.base.MobileBaseTest;
import qa.automation.enums.StoreNameEnum;
import qa.automation.report.ExtentTestManager;

public class MorePageTestCases extends MobileBaseTest {

    private static final Logger logger = Logger.getLogger(MorePageTestCases.class);

    @Test(priority = 1, groups = {"sandbox", "regression"})
    public void verifyInfoSectionOnMorePage() {
        testId = "MAB-T1055";
        logger.info(testId + " : This test validates all pages linked and it redirect properly or not in info section from more page");
        ExtentTestManager.getTest().setDescription(testId + " : This test validates all pages linked and it redirect properly or not in info section from more page");
        selectStore(StoreNameEnum.SANDBOX);
        verifyHomepage();
        clickMoreTabOnHomePage();
        waitFor(3);
        verifyMorePage();
        clickOnAboutUsOnMorePage();
        isAboutUsOnMorePageDisplayed();
    }

    @Test(priority = 1, groups = {"neovo", "regression", "iOSAppReset"})
    public void verifyContactPageFromMorePage() {
        testId = "MAB-T1054";
        logger.info(testId + " : This test validates Contact Page");
        ExtentTestManager.getTest().setDescription(testId + " :This test validates Contact Page");
        clickNoButtonOnOnboardingScreen();
        verifyHomepage();
        clickMoreTabOnHomePage();
        verifyMorePage();
        clickOnContactUs();
        waitFor(5);
        checkAddressOnContactUsPage();
    }

    @Test(groups = {"sandbox", "regression"})
    public void verifyInfoSectionOnMorePageIsVisible() {
        testId = "MAB-T1058";
        logger.info(testId + " : This test validates if the info section is visible on More page");
        ExtentTestManager.getTest().setDescription(testId + " : This test validates if the info section is visible on More page");
        selectStore(StoreNameEnum.SANDBOX);
        verifyHomepage();
        clickMoreTabOnHomePage();
        verifyMorePage();
        if (isPlatformNameAndroid)
            scrollToText("Info");
        else
            scrollForIos();
        verifyInfoSectionVisibleOnMorePage();
    }

    @Test(priority = 1, groups = {"neovo", "regression"})
    public void verifyChangeCurrencyFromMorePage() {
        testId = "MAB-T1063";
        logger.info(testId + " : This test validates user able to change currency from more page");
        ExtentTestManager.getTest().setDescription(testId + " : This test validates user able to change currency from more page");
        clickNoButtonOnOnboardingScreen();
        verifyHomepage();
        clickMoreTabOnHomePage();
        waitFor(5);
        verifyMorePage();
        waitFor(7);//waiting for loading more page
        clickOnCurrency();
        selectUSD();
        waitFor(1);
        clickOnListIcon();
        clickOnFashionCollection();
        clickOnDressCollection();
        verifyPlp();
        isSelectedCurrencyDisplayedOnPLP("$");
        mobileDriver.resetApp();
    }

    @Test(priority = 1, groups = {"neovo", "regression"})
    public void verifyContactUsPageUiElements() {
        testId = "MAB-T1054";
        logger.info(testId + " : This test validates contact us section ,navigation and all UI elements on contact us page");
        ExtentTestManager.getTest().setDescription(testId + " : This test validates contact us section ,navigation and all UI elements on contact us page");
        clickNoButtonOnOnboardingScreen();
        verifyHomepage();
        clickMoreTabOnHomePage();
        waitFor(5);
        verifyMorePage();
        waitFor(7);//waiting for loading more page
        clickOnContactUs();
        checkContactUsPageUiVisibility();
    }


    @Test(priority = 1, groups = {"neovo", "regression"})
    public void verifySizeChartDisplayOnMorePage() {
        testId = "MAB-T33169";
        logger.info(testId + " : This test validates check size chart Inches link on more page");
        ExtentTestManager.getTest().setDescription(testId + " : This test validates check size chart Inches link on more page");
        clickNoButtonOnOnboardingScreen();
        verifyHomepage();
        clickMoreTabOnHomePage();
        waitFor(5);
        verifyMorePage();
        if (isPlatformNameAndroid)
            scrollToText("Size chart");
        else
            scrollForIos();
        verifySizeChartIsDisplayOnMorePage();
    }

    @Test(priority = 1, groups = {"neovo", "regression"})
    public void verifyUserAbletoVerifySizeChartTable() {
        int size = 1;
        testId = "MAB-T33170";
        logger.info(testId + " : This test validates user able to invoke Size chart properly or not.");
        ExtentTestManager.getTest().setDescription(testId + " : This test validates user able to invoke Size chart properly or not");
        clickNoButtonOnOnboardingScreen();
        verifyHomepage();
        clickMoreTabOnHomePage();
        waitFor(5);
        verifyMorePage();
        waitFor(5);
        if (isPlatformNameAndroid)
            scrollToText("Size chart");
        else
            scrollForIos();
        verifySizeChartIsDisplayOnMorePage();
        waitFor(2);
        verifyUserClickOnSizeChart();
        waitFor(7);
        verifySizeChartTableIsDisplayed(size);
    }
        @Test(priority = 1, groups = {"neovo", "regression", "iOSAppReset"})
        public void verifyChangedCurrencyOnFavoritePage(){
            testId = "MAB-T33303";
            logger.info(testId + " : This test validates changed currency after adding product to favorite page");
            ExtentTestManager.getTest().setDescription(testId + " : This test validates changed currency after adding product to favorite page");
            clickNoButtonOnOnboardingScreen();
            verifyHomepage();
            clickMoreTabOnHomePage();
            waitFor(8);
            verifyMorePage();
            waitFor(7);//waiting for loading more page
            clickOnCurrency();
            selectUSD();
            waitFor(1);
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
            isSelectedCurrencyDisplayedOnWishlist("$");
            mobileDriver.resetApp();
        }
    }



