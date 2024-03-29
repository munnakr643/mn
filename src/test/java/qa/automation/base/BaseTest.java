package qa.automation.base;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.HasSettings;
import io.appium.java_client.MobileElement;
import io.appium.java_client.Setting;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestResult;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import qa.automation.integrations.ZephyrScaleUtil;
import qa.automation.utilities.ReadConfig;
import qa.automation.utilities.ScreenUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import static qa.automation.driverActivity.MobileDriverManager.browserStackCapabilities;
import static qa.automation.driverActivity.MobileDriverManager.capabilities;
import static qa.automation.integrations.JiraUtil.createJiraTicket;
import static qa.automation.utilities.ReadConfig.flag;

public class BaseTest {
    public static Logger logger = LogManager.getLogger(BaseTest.class);
    ReadConfig readconfig = new ReadConfig();

    public static String testId = null;
    public static String appName=null;
    public static String iosReset=null;
    public static boolean zephyrEnv = flag("updateTest");
    public static boolean slackNotification = flag("slack");


    private final static String GLOBAL_PROPERTIES = "/src/main/resources/global.properties";
    protected final static String TEST_DATA_PROPERTIES = "/src/main/resources/test-data.properties";
    private final static String LOG4J_PROPERTIES = "./src/main/resources/log4j.properties";
    private final static String START_LOGGING_TEXT = "------------------------------Start logging------------------------------";
    private final static String SCREENSHOTS_DIR = System.getProperty("user.dir") + "/target/Screenshots/";

    private static String deviceEnv = System.getenv("browserstack");
    private static String userId = System.getenv("BROWSERSTACK_USERNAME");
    private static String accessKey = System.getenv("BROWSERSTACK_ACCESS_KEY");
    static boolean browserstack = false;
    public static final String USERNAME = userId;
    public static final String AUTOMATE_KEY =accessKey ;
    public static final String URL = "https://"+USERNAME+":"+AUTOMATE_KEY+"@hub-cloud.browserstack.com/wd/hub";
    static{
        if(deviceEnv != null && deviceEnv.equalsIgnoreCase("true")){
            browserstack = true;
        }
    }
    public static boolean jiraEnv = flag("createJira");

    @BeforeMethod(alwaysRun = true)
    public void baseTestBeforeMethod(){
        File file = new File(SCREENSHOTS_DIR);
        if (file.exists()) {
            ScreenUtil.deleteFolder(file);
        }

    }


    @BeforeMethod(alwaysRun = true)
    public void beforeMethodBaseTest(Method method){
    Test testClass =  method.getAnnotation(Test.class);
        appName= testClass.groups()[0];
    }

    @BeforeMethod(alwaysRun = true)
    public void beforeMethodBaseTestResetIos(Method method){
        Test testClass =  method.getAnnotation(Test.class);
       int index= testClass.groups().length;
        iosReset=testClass.groups()[index-1];
    }

    /**
     * Initialises the appium Driver with its required capabilities
     * @return
     * @throws IOException
     */
    public AppiumDriver<MobileElement> initAppiumDriver() throws IOException {
        AppiumDriver<MobileElement> mobileDriver;
        logger = Logger.getLogger("PlobalApps");
        PropertyConfigurator.configure(LOG4J_PROPERTIES);
        logger.info(START_LOGGING_TEXT);
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + GLOBAL_PROPERTIES);
        Properties prop = new Properties();
        prop.load(fis);
        String port = prop.getProperty("port");
        String appiumServer = prop.getProperty("appiumServer");
        logger.info("Port Number: " + port);
        logger.info("Appium Server: " + appiumServer);
        logger.info("App Name: " + appName);
        if (browserstack){
            DesiredCapabilities bsCapabilities =browserStackCapabilities();
            if (System.getenv("platform").equalsIgnoreCase("Android")) {
                mobileDriver = new AndroidDriver<>(new URL("http://hub.browserstack.com/wd/hub"), bsCapabilities);
            }
            else {
                mobileDriver = new IOSDriver<>(new URL("http://hub.browserstack.com/wd/hub"), bsCapabilities);
            }

        }else {
            DesiredCapabilities capabilities = capabilities();
            if (System.getenv("platform").equalsIgnoreCase("Android"))
                mobileDriver = new AndroidDriver<>(new URL("http://" + appiumServer + ":" + port + "/wd/hub"), capabilities);
            else
                mobileDriver = new IOSDriver<>(new URL("http://" + appiumServer + ":" + port + "/wd/hub"), capabilities);
        }
        mobileDriver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
        ((HasSettings)mobileDriver).setSetting(Setting.WAIT_FOR_IDLE_TIMEOUT, 500);
        return mobileDriver;
    }

    /**
     * Initialises the Web Driver with its required capabilities
     * @return
     */
    public WebDriver initWebDriver() {
        WebDriver webDriver;
        logger=Logger.getLogger("PlobalApps");
        PropertyConfigurator.configure(LOG4J_PROPERTIES);
        logger.info(START_LOGGING_TEXT);
        io.github.bonigarcia.wdm.WebDriverManager.chromedriver().setup();
        // TODO: Handle options for other Web Browsers
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        options.addArguments("window-size= 1400,800");
//        options.addArguments("---headless");
        webDriver = new ChromeDriver(options);
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        return webDriver;
    }


    /**
     * Methods waits for number of seconds passed as a parameter using thread.sleep
     * @param seconds
     */
    public static void waitFor(double seconds) {
        try {
            Thread.sleep((int) (seconds * 1000));
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    /**
     * Method return random string of provided length
     * TODO : create utility class and add this method
     * @param length
     * @return
     */
    public String randomString(int length) {
        String generatedstring = RandomStringUtils.randomAlphabetic(length);
        return (generatedstring);
    }

    public void jiraCreationOnFailure( ITestResult result ) {
        if (result.getStatus() == ITestResult.FAILURE) {
            String issueSummary = "[Automation-bug] [" + readconfig.getPlatformName() + "] -" + result.getMethod().getConstructorOrMethod().getName()
                    + " got failed due to " + result.getThrowable().getClass();
            String issueDescription = testId+" : " + result.getMethod().getConstructorOrMethod().getName()
                    + " got failed due to " + result.getThrowable().getClass();
            if (jiraEnv) {
                try {
                    createJiraTicket("Bug", issueSummary + "!", issueDescription);
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            } else {
                logger.info("Jira ticket creation is disabled as the 'createJira' flag is not set to 'true'.");
            }
        }
    }


    public static void updateZephyrTestResult(ITestResult result){
        if (zephyrEnv) {
            ZephyrScaleUtil.updateTestResult(result);}
        else {
            logger.info("Zephyr Scale update test result is disabled as the 'updateTest' flag is not set to 'true'.");
        }
    }

}
