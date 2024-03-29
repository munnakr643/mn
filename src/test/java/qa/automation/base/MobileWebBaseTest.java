package qa.automation.base;

import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import qa.automation.report.ExtentTestManager;
import qa.automation.utilities.ScreenUtil;


public class MobileWebBaseTest extends MobileBaseTest{
    /* Test cases that require both Web and Mobile drivers should extend to this base class*/
    WebDriver webDriver;
    protected WebBaseTest webBaseTest;
    @BeforeMethod(alwaysRun = true)
    public void beforeMethodInMobileWeb() {
       webBaseTest = new WebBaseTest();
       webBaseTest.setupWebDriver();
       webDriver = webBaseTest.getWebDriver();

    }

    @AfterMethod(alwaysRun = true)
    public void afterMethodWeb(ITestResult result) {
        extentReportsFailOperationWeb(result);
        webDriver.quit();
    }

    public void extentReportsFailOperationWeb(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            String base64Screenshot = ScreenUtil.getBase64ScreenshotWeb(webDriver);
            ScreenUtil.convertBase64StringToImage(base64Screenshot, result.getMethod().getMethodName());
            ExtentTestManager.getTest().log(LogStatus.FAIL,
                    "Test " + result.getMethod() + " has failed",
                    ExtentTestManager.getTest().addBase64ScreenShot(base64Screenshot));
        }
    }

}
