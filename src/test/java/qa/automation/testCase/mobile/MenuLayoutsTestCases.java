package qa.automation.testCase.mobile;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;
import qa.automation.base.MobileBaseTest;
import qa.automation.report.ExtentTestManager;

public class MenuLayoutsTestCases extends MobileBaseTest {

    private static final Logger logger = Logger.getLogger(MenuLayoutsTestCases.class);

    @Test(priority = -5, groups = {"neovo","regression"})
    public void verifyRedirectionOfMenuTabs() {
        testId = "MAB-T1096";
        logger.info(testId + " This test verifies redirection of all menu tabs");
        ExtentTestManager.getTest().setDescription(testId + " This test verifies redirection of all menu tabs");
        clickNoButtonOnOnboardingScreen();
        verifyHomepage();
        clickOnListIcon();
        waitFor(2);
        verifyPageHeaderText("Categories");
        clickProfileTabOnHomePage();
        waitFor(2);
        verifyPageHeaderText("My Profile");
        clickCartTabOnHomePage();
        waitFor(2);
        verifyPageHeaderText("Cart");
        clickMoreTabOnHomePage();
        waitFor(2);
        verifyPageHeaderText("More");
        clickHomeTab();
        waitFor(2);
        if(isPlatformNameAndroid){
            verifyPageHeaderText("Footware");
        }else{
            verifyPageHeaderText("Multi2");
        }

    }

}
