package qa.automation.testCase.mobile;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;
import qa.automation.base.MobileBaseTest;
import qa.automation.report.ExtentTestManager;

public class CTInboxTestCases extends MobileBaseTest {

    private static final Logger logger= Logger.getLogger(CTInboxTestCases.class);


    @Test(priority = 1, groups = {"neovo","regression","iOSAppReset"})
    public void verifyCtInboxOnMorePage(){
        testId = "MAB-T15575";
        logger.info(testId + " : This test validates CT inbox visibility when it is set on More feature tab");
        ExtentTestManager.getTest().setDescription(testId + " : This test validates CT inbox visibility when it is set on More feature tab");
        clickNoButtonOnOnboardingScreen();
        verifyHomepage();
        clickMoreTabOnHomePage();
        verifyMorePage();
        checkCtInboxFromMorePage();
    }

    @Test(priority = 1, groups = {"neovo","regression", "androidFailure", "androidOnly"})
    public void verifyPnOnCtInboxPage(){
        testId = "MAB-T15226";
        logger.info(testId + " : This test validates push notification visibility from CT inbox page");
        ExtentTestManager.getTest().setDescription(testId + " : This test validates push notification visibility from CT inbox page");
        clickNoButtonOnOnboardingScreen();
        verifyHomepage();
        clickMoreTabOnHomePage();
        verifyMorePage();
        checkCtInboxFromMorePage();
        clickOnCtInboxFromMorePage();
        verifyCtInboxPage();

    }

    @Test(priority = 0, groups = {"neovo","regression","iOSAppReset"})
    public void verifyCtInboxOnProfilePage() {
        testId = "MAB-T15224";
        logger.info(testId + " : This test validates CT inbox visibility under profile page");
        ExtentTestManager.getTest().setDescription(testId + " : This test validates CT inbox visibility under profile page");
        clickNoButtonOnOnboardingScreen();
        verifyHomepage();
        clickOnListIcon();
        clickOnFashionCollection();
        clickOnDressCollection();
        verifyPlp();
        selectProductFromPLP();
        waitFor(2);
        verifyPdp();
        addProductIntoCartFromPDP();
        if (isPlatformNameAndroid) {
            clickOnConfirm();
        }
        int i = 0;
        if(isPlatformNameAndroid){
            while (i < 3) {
                backBtn();
                i++;
            }
        }else {
            while (i < 3) {
                waitFor(1.5);
                clickBackArrowOnNativeApp();
                i++;
            }
        }
        clickProfileTabOnHomePage();
        isMyProfileDispalyed();
        checkCtInboxFromProfilePage();
    }


    @Test(priority = 0, groups = {"neovo","regression","iOSAppReset"})
    public void verifyCtNotificationPageAfterAddToCartEvent() {
        testId = "MAB-T32303";
        logger.info(testId + " : This test validates CT inbox page after add to cart event");
        ExtentTestManager.getTest().setDescription(testId + " : This test validates CT inbox page after add to cart event");
        clickNoButtonOnOnboardingScreen();
        verifyHomepage();
        clickProductBannerOnHomePage();
        verifyPdp();
        addProductIntoCartFromPDP();
        if (isPlatformNameAndroid) {
            clickOnConfirm();
            waitFor(1.7);
            backBtn();
        } else {
            clickBackArrowOnNativeApp();
        }
        clickProfileTabOnHomePage();
        isMyProfileDispalyed();
        checkCtInboxFromProfilePage();
        clickCtInboxFromProfilePage();
        isCtNotificationPageDisplayed();
    }

    @Test(priority = 0, groups = {"neovo","regression", "iOSFailure", "iOSAppReset"})
    public void verifyCtNotificationLinkRedirectToCartPageAfterAddToCartEvent() {
        testId = "MAB-T15406";
        logger.info(testId + " : This test validates CT notification link should be redirect to cart page after add to cart event");
        ExtentTestManager.getTest().setDescription(testId + " : This test validates CT notification link should be redirect to cart page after add to cart event");
        clickNoButtonOnOnboardingScreen();
        verifyHomepage();
        clickProductBannerOnHomePage();
        verifyPdp();
        addProductIntoCartFromPDP();
        if (isPlatformNameAndroid) {
            clickOnConfirm();
            waitFor(1.7);
            backBtn();
        } else {
            clickBackArrowOnNativeApp();
        }
        clickProfileTabOnHomePage();
        isMyProfileDispalyed();
        checkCtInboxFromProfilePage();
        clickCtInboxFromProfilePage();
        isCtNotificationPageDisplayed();
        clickOnCtNotificationPnLink();
        waitFor(1.7);
        clickNoButtonOnOnboardingScreen();
        waitFor(1.5);
        verifyCartPage();
    }

    @Test(priority = 0, groups = {"neovo","regression", "iOSAppReset"})
    public void verifyBackButtonFunctionalityFromCtNotificationPage() {
        testId = "MAB-T44624";
        logger.info(testId + " : This test validates back button functionality from CT notification page");
        ExtentTestManager.getTest().setDescription(testId + " : This test validates back button functionality from CT notification page");
        clickNoButtonOnOnboardingScreen();
        verifyHomepage();
        clickProductBannerOnHomePage();
        verifyPdp();
        addProductIntoCartFromPDP();
        if (isPlatformNameAndroid) {
            clickOnConfirm();
            waitFor(1.7);
            backBtn();
        } else {
            clickBackArrowOnNativeApp();
        }
        clickProfileTabOnHomePage();
        verifyMyAccountPage();
        checkCtInboxFromProfilePage();
        clickCtInboxFromProfilePage();
        isCtNotificationPageDisplayed();
        clickBackButtonIconOnTopLeft();
        verifyMyAccountPage();
    }

    @Test(priority = 0, groups = {"neovo","regression", "iOSAppReset"})
    public void verifyXButtonFunctionalityFromCtNotificationPage() {
        testId = "MAB-T44625";
        logger.info(testId + " : This test validates X button functionality from CT notification page");
        ExtentTestManager.getTest().setDescription(testId + " : This test validates X button functionality from CT notification page");
        clickNoButtonOnOnboardingScreen();
        verifyHomepage();
        clickProductBannerOnHomePage();
        verifyPdp();
        addProductIntoCartFromPDP();
        clickBackArrowOnNativeApp();
        clickProfileTabOnHomePage();
        verifyMyAccountPage();
        checkCtInboxFromProfilePage();
        clickCtInboxFromProfilePage();
        isCtNotificationPageDisplayed();
        clickXButtonIconOnCtNotification();
        verifyMyAccountPage();
    }
}
