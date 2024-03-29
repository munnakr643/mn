package qa.automation.testCase.mobile;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;
import qa.automation.base.MobileBaseTest;
import qa.automation.enums.StoreNameEnum;
import qa.automation.report.ExtentTestManager;

public class HeaderTestCases extends MobileBaseTest {

    private static final Logger logger= Logger.getLogger(HeaderTestCases.class);

    @Test(priority = 0, groups = {"neovo", "androidFailure", "regression"})
    public void checkHeaderLogoAvailability() {
        String testId="MAB-T14139";
        logger.info(testId+" : This test validate app logo on header");
        ExtentTestManager.getTest().setDescription(testId+" : This test validate app logo on header");
        clickNoButtonOnOnboardingScreen();
        verifyAppLogoOnHeader();
        waitFor(2);
        clickProfileTabOnHomePage();
        verifyAppLogoOnHeader();
        waitFor(2);
        clickCartTabOnHomePage();
        verifyAppLogoOnHeader();
        waitFor(2);
        clickMoreTabOnHomePage();
        verifyAppLogoOnHeader();
    }

    @Test(priority = 0, groups = {"sandbox","regression"})
    public void verifyDisabledAppLogofromHomePage() {
        String testId="MAB-T15399";
        logger.info(testId+" : This test validate app logo on header not visible while flag is disabled");
        ExtentTestManager.getTest().setDescription(testId+" : This test validate app logo on header not visible while flag is disabled");
        selectStore(StoreNameEnum.SANDBOX);
        verifyHomepage();
        isAppLogoNotDisplayed(0);
    }
}
