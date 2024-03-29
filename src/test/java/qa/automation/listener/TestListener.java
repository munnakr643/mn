package qa.automation.listener;

import com.google.common.collect.Lists;
import com.relevantcodes.extentreports.LogStatus;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import qa.automation.base.BaseTest;
import qa.automation.base.MobileBaseTest;
import qa.automation.integrations.AWSSecretsManagerUtils;
import qa.automation.integrations.SlackClient;
import qa.automation.report.ExtentReportManager;
import qa.automation.report.ExtentTestManager;

import java.io.FileInputStream;
import java.util.Arrays;
import java.util.Properties;

import static qa.automation.utilities.ReadConfig.flag;


public class TestListener extends BaseTest implements ITestListener {

    private final static Logger logger = LogManager.getLogger(TestListener.class);

    private static boolean slackNotification = flag("slack");
    private static String reportsPath = "target/Reports/MobileQAExtentReport.html";



    @Override
    public void onTestStart(ITestResult result) {
        logger.info("Start Testing: " + getTestMethodName(result) + Arrays.toString(result.getParameters()));
        ExtentTestManager.startTest(getTestMethodName(result) + "-" + Arrays.toString(result.getParameters()), "");

    }

    private String getTestMethodName(ITestResult iTestResult) {
        String className = iTestResult.getTestClass().getName();
        className = className.substring(className.lastIndexOf('.') + 1);
        return className + " - " + iTestResult.getMethod().getConstructorOrMethod().getName();
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        logger.info("Test: " + getTestMethodName(result) + Arrays.toString(result.getParameters()) + " has succeeded");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Test " + getTestMethodName(result) + " has passed");
        ExtentTestManager.endTest();
    }

    @Override
    public void onTestFailure(ITestResult result) {
        logger.info("Test: " + getTestMethodName(result) + Arrays.toString(result.getParameters()) + " has failed");
        ExtentTestManager.getTest().log(LogStatus.FAIL, "Test " + getTestMethodName(result) + " has failed",
                result.getThrowable());
        ExtentTestManager.endTest();

    }

    @Override
    public void onTestSkipped(ITestResult result) {
        logger.info("Test: " + getTestMethodName(result) + Arrays.toString(result.getParameters()) + " has skipped");
        ExtentTestManager.getTest().log(LogStatus.SKIP, "Test " + getTestMethodName(result) + " has skipped");
        ExtentTestManager.endTest();
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        // TODO Auto-generated method stub

    }

    // Before starting all tests, below method runs
    @Override
    public void onStart(ITestContext context) {
        logger.debug("Starting Listener - context: " + context.getName());
        logger.debug("context: " + context.getName() + " has the following groups Included: ");
        Lists.newArrayList(context.getIncludedGroups()).forEach(logger::debug);
        logger.debug("context: " + context.getName() + " has the following groups Excluded: ");
        Lists.newArrayList(context.getExcludedGroups()).forEach(logger::debug);
    }

    // After ending all tests, below method runs
    @Override
    public void onFinish(ITestContext context) {
        logger.debug("Finishing Listener - context: " + context.getName());
        ExtentReportManager.getReporter().flush();
        logger.info("ExtentReportManager has flushed, file: " + ExtentReportManager.reportPath);
        if (slackNotification) {
            try {
                FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "/src/main/resources/global.properties");
                Properties prop = new Properties();
                prop.load(fis);
                SlackClient slackClient = new SlackClient();
                String slackToken = AWSSecretsManagerUtils.getValueForKey("AutomationSlackBotToken",MobileBaseTest.automationIntegrationTokens);
                slackClient.sendFileWithMessageToSlackChannel(reportsPath, getFinalReportString(context), prop.getProperty("channelName"), slackToken);
            }catch (Exception e){
                logger.error("Exception caught while sending slack notification.\n" + e);
            }
        }else{
            logger.info("Slack Notifications are disabled.");
        }

    }

    /**
     * Return the message of the test run status
     * @param context
     * @return
     */
    private String getFinalReportString(ITestContext context) {
        int totalTest = context.getAllTestMethods().length;
        int passed = context.getPassedTests().getAllResults().size();
        int failed = context.getFailedTests().getAllResults().size();
        int skipped = context.getSkippedTests().getAllResults().size();
        String suiteName = context.getSuite().getName();
        String platform = System.getenv("platform");
        String platformName = (platform == null)? "default": platform;
        StringBuilder messageBuider = new StringBuilder();
        messageBuider.append("========================" + "\n\n" + "Suite Name: " + suiteName + " || Platform: " + platformName + "\n\n" + "Total Test Cases Run: " + totalTest +
                "\n\n" + "Passed Test Cases:  " + passed + "\n\n" + "Failed Test Cases:    " + failed + "\n\n" + "Skipped Test Cases: " + skipped);
        return messageBuider.toString();
    }

}
