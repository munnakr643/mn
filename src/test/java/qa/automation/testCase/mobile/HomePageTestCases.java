package qa.automation.testCase.mobile;

import io.appium.java_client.HasSettings;
import io.appium.java_client.Setting;
import org.apache.log4j.Logger;
import org.openqa.selenium.Dimension;
import org.testng.annotations.Test;
import qa.automation.base.MobileBaseTest;
import qa.automation.report.ExtentTestManager;
import qa.automation.enums.StoreNameEnum;

public class HomePageTestCases extends MobileBaseTest {
	private static final Logger logger = Logger.getLogger(HomePageTestCases.class);



	@Test(priority = 0, groups = {"sandbox", "regression", "iOSAppReset"})
	public void verifyAdditionallabelShouldNotDisplayOnHomePage(){
		testId = "MAB-T13901";
		logger.info(testId + " : This test validate additional label should not displayed on excluded screen (Homepage)");
		ExtentTestManager.getTest().setDescription(testId + " : This test validate additional label should not displayed on excluded screen (Homepage)");
		selectStore(StoreNameEnum.SANDBOX);
		verifyHomepage();
		if (isPlatformNameAndroid) {
			scrollToText("High Waist Jeans");
		} else {
			scrollForIos();
			waitFor(8);
		}
		verifyAdditionalLabelShouldNotDisplayOnHomePage(0);
	}
}

