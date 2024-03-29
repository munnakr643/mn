package qa.automation.testCase.mobile;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;
import qa.automation.base.MobileBaseTest;
import qa.automation.report.ExtentTestManager;

public class MultiLanguageTestCases extends MobileBaseTest {

    private static final Logger logger= Logger.getLogger(MultiLanguageTestCases.class);

    @Test(priority = 1, groups = {"neovo","regression"})
    public void VerifyLanguagePopupOnMorePage(){
        testId = "MAB-T15401";
        logger.info(testId + " : This test validates language selection pop is shown when user clicks on the language section on the More feature tab");
        ExtentTestManager.getTest().setDescription(testId + " : This test validates language selection pop is shown when user clicks on the language section on the More feature tab");
        clickNoButtonOnOnboardingScreen();
        verifyHomepage();
        clickMoreTabOnHomePage();
        waitFor(3);
        verifyMorePage();
        waitFor(7);
        openlanguagePopupOnMorePage();
        verifyLanguagePopupOnMorePage();
    }


    @Test(priority = 1, groups = {"neovo","regression","iOSAppReset"})
    public void verifyDefaultLanguage(){
        String defaultLanguage= "English (en)";
        testId = "MAB-T15402";
        logger.info(testId + " : This test validates language selection pop is shown when user clicks on the language section on the More feature tab");
        ExtentTestManager.getTest().setDescription(testId + " : This test validates language selection pop is shown when user clicks on the language section on the More feature tab");
        clickNoButtonOnOnboardingScreen();
        verifyHomepage();
        clickMoreTabOnHomePage();
        waitFor(3);
        verifyMorePage();
        waitFor(7);
        openlanguagePopupOnMorePage();
        isDefaultLanguageSelected(defaultLanguage);
    }

    @Test(priority = 1, groups = {"neovo", "iOSFailure", "androidFailure", "regression"})
    public void verifyChangeAppLanguageOnMorePage(){
        testId = "MAB-T1064";
        logger.info(testId + " : This test validates user should  able to change app language from more page");
        ExtentTestManager.getTest().setDescription(testId + " : This test validates user should  able to change app language from more page");
        clickNoButtonOnOnboardingScreen();
        verifyHomepage();
        clickMoreTabOnHomePage();
        waitFor(5);
        verifyMorePage();
        waitFor(7);
        openlanguagePopupOnMorePage();
        verifyLanguagePopupOnMorePage();
        selectFrenchLanguage();
        waitFor(5);
        clickNoButtonOnOnboardingScreen();
        clickOnHamburgerButtonFromHomePage();
        clickOnMoreBtn();
        waitFor(7);
        isLanguageDisplayed("French");
        mobileDriver.resetApp();
    }
}
