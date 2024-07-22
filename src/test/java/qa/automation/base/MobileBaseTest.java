package qa.automation.base;

import com.relevantcodes.extentreports.LogStatus;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.apache.log4j.Logger;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import qa.automation.integrations.AWSSecretsManagerUtils;
import qa.automation.pageScreenDevice.mobile.HomePage;
import qa.automation.report.ExtentTestManager;
import qa.automation.utilities.ReadConfig;
import qa.automation.utilities.ScreenUtil;




import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

import static java.time.Duration.ofSeconds;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;



public class MobileBaseTest extends BaseTest {
    private static final Logger logger = Logger.getLogger(MobileBaseTest.class);
    public static LoginPage loginPage;
    protected static HomePage homePage;


    public static ThankYouPage thankYouPage;

    protected AppiumDriver<MobileElement> mobileDriver;
    //TODO: Move all static variables to separate Class for easy retrieval
    private static String ANDROID_TOAST_MESSAGE_XPATH = "/hierarchy/android.widget.Toast";
    private static String IOS_TOAST_MESSAGE_XPATH = "//XCUIElementTypeStaticText[@name='Your products has been sorted by Price - High-Low']";
    private static String ANDROID_PRODUCT_PRICE_LIST_XPATH = "//android.widget.TextView[contains(@resource-id,'txt_price_discounted')]";
    private static String IOS_PRODUCT_PRICE_LIST_XPATH = "//XCUIElementTypeButton[@name='like icon']/parent::XCUIElementTypeOther/following-sibling::XCUIElementTypeStaticText[2]";
    private static String ANDROID_PRODUCT_TITLE_LIST_XPATH = "//android.widget.TextView[contains(@resource-id,'txt_product_title')]";
    private static String IOS_PRODUCT_TITLE_LIST_XPATH = "//XCUIElementTypeButton[@name='like icon']/parent::XCUIElementTypeOther/following-sibling::XCUIElementTypeStaticText[1]";

    public static boolean isPlatformNameAndroid = ReadConfig.isPlatformNameAndroid();

    public Properties testData;
    public static HashMap automationIntegrationTokens;

    @BeforeSuite(alwaysRun = true)
    public void beforeSuite() {
        if (jiraEnv || slackNotification || zephyrEnv) {
            automationIntegrationTokens = AWSSecretsManagerUtils.parseKeyValueString(AWSSecretsManagerUtils.getSecretValue("AutomationIntegrationTokens"));
        }

    }

    @BeforeMethod(alwaysRun = true)
    public void setupMobileDriver() throws IOException {
        /*Init Mobile Driver*/
        mobileDriver = initAppiumDriver();
        initMobilePages();
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + TEST_DATA_PROPERTIES);
         Properties testData1=new Properties();
        testData = new Properties();
        testData.load(fis);
        logger.info("Appium SessionID : " + mobileDriver.getSessionId());
    }

    public void initMobilePages() {
        homePage = new HomePage(mobileDriver);
        loginPage = new LoginPage(mobileDriver);
    
    }

    @AfterMethod(alwaysRun = true)
    public void afterMethodMobile(ITestResult result) {
        ExtentTestManager.getTest().log(LogStatus.INFO, "Appium SessionID " + mobileDriver.getSessionId());
        mobileDriver.closeApp();
        mobileDriver.quit();
    }

    public void extentReportsFailOperationMobile(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            String base64Screenshot = ScreenUtil.getBase64ScreenshotMobile(mobileDriver);
            ScreenUtil.convertBase64StringToImage(base64Screenshot, result.getMethod().getMethodName());
            ExtentTestManager.getTest().log(LogStatus.FAIL,
                    "Test " + result.getMethod() + " has failed",
                    ExtentTestManager.getTest().addBase64ScreenShot(base64Screenshot));
        }
    }

    public void clickHomeBtn() {
        ((AndroidDriver) mobileDriver).pressKey(new KeyEvent(AndroidKey.HOME));
    }

    public void clickDoneBtn() {
        ((AndroidDriver) mobileDriver).pressKey(new KeyEvent(AndroidKey.ENTER));
    }

    public void clickAppOverviewBtn() {
        ((AndroidDriver) mobileDriver).pressKey(new KeyEvent(AndroidKey.APP_SWITCH));
    }

    public void backBtn() {
        ((AndroidDriver) mobileDriver).pressKey(new KeyEvent(AndroidKey.BACK));
    }

    public void isLoginPageDisplayed() {
        waitElement(loginPage.getLoginPageText(), 10);
        assertThat(loginPage.getLoginPageText().isDisplayed(), equalTo(true));
        assertThat(loginPage.getEmailTextField().isDisplayed(), equalTo(true));
        assertThat(loginPage.getPasswordTextField().isDisplayed(), equalTo(true));
        logger.info("login page verify successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "login page verify successfully");
    }


    public void enterUserId(String userId) {
        waitElement(loginPage.getUserId(), 6);
        assertThat(loginPage.getUserId().isDisplayed(), equalTo(true));
        loginPage.getUserId().sendKeys(userId);
        loginPage.getUserId().sendKeys(userId);
        logger.info("phone number/emailId " + userId + " entered successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "phone number/emailId " + userId + " entered successfully");
    }

    public void clickOnContinueBtn() {
        waitElement(loginPage.getContinueBtn(), 6);
        assertThat(loginPage.getContinueBtn().isDisplayed(), equalTo(true));
        loginPage.getContinueBtn().click();
        logger.info("Continue clicked successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Continue clicked successfully");

    }
}
