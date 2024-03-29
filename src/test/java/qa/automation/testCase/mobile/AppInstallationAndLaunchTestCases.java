package qa.automation.testCase.mobile;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;
import qa.automation.base.MobileBaseTest;
import qa.automation.report.ExtentTestManager;

public class AppInstallationAndLaunchTestCases extends MobileBaseTest {

    private static final Logger logger= Logger.getLogger(AppInstallationAndLaunchTestCases.class);



    @Test(priority = -2, groups = {"neovo", "Regression"})
    public void verifyAppLoadingTime() {
        testId = "MAB-T951";
        logger.info( testId + ": This test verifies if app loads within 3 seconds when app launches the first time");
        ExtentTestManager.getTest().setDescription(testId + ": This test verifies if app loads within 3 seconds when app launches the first time");
        waitFor(3); //waiting for 3 seconds for the onboarding screen to load
        clickOnboardingScreenIsVisible();
    }

}
