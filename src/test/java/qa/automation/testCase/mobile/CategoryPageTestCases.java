package qa.automation.testCase.mobile;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;
import qa.automation.base.MobileBaseTest;
import qa.automation.enums.StoreNameEnum;
import qa.automation.report.ExtentTestManager;

public class CategoryPageTestCases extends MobileBaseTest {

    private static final Logger logger = Logger.getLogger(CategoryPageTestCases.class);


    @Test(priority = 0, groups = {"neovo", "regression"})
    public void verifyDressCategoryPage() {
        testId = "MAB-T15283";
        logger.info(testId + " This test validates Dress category on PLP Page");
        ExtentTestManager.getTest().setDescription(testId + " This test validates Dress category on PLP Page");
        clickNoButtonOnOnboardingScreen();
        verifyHomepage();
        clickOnListIcon();
        clickOnFashionCollection();
        clickOnDressCollection();
        verifyPlp();
        isProductTitleOnPLPDisplay("Dress");
        verifyCollectionPLP("Dress");
    }

    @Test(priority = 0, groups = {"sandbox", "regression"})
    public void verifyCollectionTextAfterCollectionTabSwitch() {
        testId = "MAB-T15260";
        logger.info(testId + " This test validates Collection Text After Collection Tab Switch");
        ExtentTestManager.getTest().setDescription(testId + " This test validates Collection Text After Collection Tab Switch");
        selectStore(StoreNameEnum.SANDBOX);
        verifyHomepage();
        clickOnListIcon();
        isSelectedCollectionDisplayed("Dress");
        clickOnCollectionTab2onHomepage();
        waitFor(.7);
        isSelectedCollectionDisplayed("Top");
    }
}