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
       

    }

    @AfterMethod(alwaysRun = true)
    public void afterMethodWeb(ITestResult result) {
        extentReportsFailOperationWeb(result);
        webDriver.quit();
    }

    public void extentReportsFailOperationWeb(ITestResult result) {
       
    }

}
