package qa.automation.testCase.mobileWeb;

import io.appium.java_client.HasSettings;
import io.appium.java_client.Setting;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.testng.annotations.Test;
import qa.automation.base.MobileWebBaseTest;
import qa.automation.report.ExtentTestManager;

/**
 * @deprecated
 */
public class MobileWithWebTestCases extends MobileWebBaseTest {
    public static Logger logger = LogManager.getLogger(MobileWithWebTestCases.class);

    @Test(priority = 0, groups = {"regression"})
    public void verifyPlaceOrderUsingBuyNowOnDeviceAndWeb(){
        logger.info("This test validates Place Order Using BuyNow btn");
        ExtentTestManager.getTest().setDescription("This test validates Place Order Using BuyNow btn");
        ((HasSettings)mobileDriver).setSetting(Setting.WAIT_FOR_IDLE_TIMEOUT, 500);
        clickNoButtonOnOnboardingScreen();
        verifyHomepage();
        clickOnListIcon();
        clickOnFashionCollection();
        clickOnDressCollection();
        clickOnFilterButtonInPLP();
        verifyFilterOptions();
        webBaseTest.launchUrl("https://www.shopify.com/in");
        webBaseTest.verifyShopifyWebHomePageDisplay();
        webBaseTest.clickOnShopifyMarket();

    }
}
