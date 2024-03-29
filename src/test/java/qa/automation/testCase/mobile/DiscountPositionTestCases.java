package qa.automation.testCase.mobile;

import io.appium.java_client.HasSettings;
import io.appium.java_client.Setting;
import org.apache.log4j.Logger;
import org.testng.annotations.Test;
import qa.automation.base.MobileBaseTest;
import qa.automation.report.ExtentTestManager;

public class DiscountPositionTestCases extends MobileBaseTest {

    private static final Logger logger= Logger.getLogger(DiscountPositionTestCases.class);

    @Test(priority = 1, groups = {"neovo", "iOSFailure", "regression"})
    public void verifyDiscountOnAllPages() {
        testId="MAB-T1106";
        logger.info(testId + "This test verifies that discount on all pages.");
        ExtentTestManager.getTest().setDescription(testId + "This test verifies that discount on all pages.");
        ((HasSettings) mobileDriver).setSetting(Setting.WAIT_FOR_IDLE_TIMEOUT, 500);
        clickNoButtonOnOnboardingScreen();
        if(isPlatformNameAndroid){
            swipe(500, 1970, 500, 370);
            waitFor(3);
            swipe(500, 1970, 500, 370);
            waitFor(3);
            swipe(500, 1970, 500, 370);
        }
        else{
            swipeForIos("down");
            waitFor(3);
            swipeForIos("down");
        }
        checkDiscountOnHomePage();
        checkDiscountOnPLPPage();
        if(isPlatformNameAndroid){
            swipe(500, 1970, 500, 370);
            waitFor(3);
            swipe(500, 1970, 500, 370);
        }
        else{
            swipeForIos("down");
            waitFor(3);
        }
        checkDiscountOnPDPPage();
    }
}
