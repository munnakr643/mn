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

    

    /**
     * Initialises the appium Driver with its required capabilities
     * @return
     * @throws IOException
     */
    public AppiumDriver<MobileElement> initAppiumDriver() throws IOException {
        AppiumDriver<MobileElement> mobileDriver;
        return mobileDriver;
    }

    /**
     * Initialises the Web Driver with its required capabilities
     * @return
     */
    public WebDriver initWebDriver() {
        WebDriver webDriver;
    
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
    
    }


    public static void updateZephyrTestResult(ITestResult result){
       
    }

}
