package qa.automation.driverActivity;

import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.remote.DesiredCapabilities;
import qa.automation.utilities.ReadConfig;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;

import static qa.automation.base.BaseTest.appName;
import static qa.automation.base.BaseTest.iosReset;

public class MobileDriverManager {

    public static Logger logger = LogManager.getLogger(MobileDriverManager.class);
    ReadConfig readconfig = new ReadConfig();

    protected String username = readconfig.getUsername();
    protected String password = readconfig.getPassword();

    private static String userId = System.getenv("BROWSERSTACK_USERNAME");
    private static String accessKey = System.getenv("BROWSERSTACK_ACCESS_KEY");

    public static AppiumDriverLocalService service;


    public static DesiredCapabilities capabilities() throws IOException {
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "/src/main/resources/global.properties");
        Properties prop = new Properties();
        prop.load(fis);
        DesiredCapabilities capabilities = new DesiredCapabilities();
        String platformName = System.getenv("platform");
        String packageName;
        String activityName;
        String bundleName;

        if (platformName.equalsIgnoreCase("Android")) {
            capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, (String) prop.get("deviceName"));
            capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, (String) prop.get("automationName"));
            capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 120);
            capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, (String) prop.get("platformVersion"));
            capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, platformName);
            capabilities.setCapability(MobileCapabilityType.ORIENTATION, (String) prop.get("orientation"));
            if(appName.equalsIgnoreCase("neovo")){
                packageName= prop.getProperty("neovoPackageName");
                activityName= prop.getProperty("neovoAppActivityName");
            }else {
                packageName= prop.getProperty("sandboxPackageName");
                activityName= prop.getProperty("sandboxAppActivityName");
            }
            capabilities.setCapability("appPackage", packageName);
            capabilities.setCapability("appActivity", activityName);
            capabilities.setCapability("autoGrantPermissions", "true");
            logger.info("Capabilities: "+ capabilities);

        }
        else {
            capabilities.setCapability("platformName", prop.getProperty("platformNameIOS"));
            if (iosReset != null && iosReset.equalsIgnoreCase("iOSAppReset")){
                capabilities.setCapability("fullReset", true);
            }
            capabilities.setCapability("useNewWDA", false);
            capabilities.setCapability("platformVersion",  prop.getProperty("platformVersionIOS"));
            capabilities.setCapability("deviceName", prop.getProperty("deviceNameIOS"));
            capabilities.setCapability("udid", prop.getProperty("udid")); //can use "auto" if only 1 device is connected
            capabilities.setCapability("newCommandTimeout", "100");
            if(appName.equalsIgnoreCase("neovo")){
                bundleName= prop.getProperty("neovoBundleId");
                if (iosReset != null && iosReset.equalsIgnoreCase("iOSAppReset")){
                    capabilities.setCapability("app", System.getProperty("user.dir") + "/src/main/resources/Neovo_rcJan_v2_3feb.ipa");
                }
            }else {
                bundleName= prop.getProperty("sandboxBundleId");
                if (iosReset != null && iosReset.equalsIgnoreCase("iOSAppReset")){
                    capabilities.setCapability("app", System.getProperty("user.dir") + "/src/main/resources/sandbox_rcJan_v2_3feb.ipa");
                }
            }
            capabilities.setCapability("bundleId", bundleName);
            capabilities.setCapability("xcodeOrgId", prop.getProperty("xcodeOrgId"));
            capabilities.setCapability("xcodeSigningId", prop.getProperty("xcodeSigningId"));
            capabilities.setCapability("isRealMobile", true);
            capabilities.setCapability("automationName", prop.getProperty("automationNameIOS"));
            capabilities.setCapability("shouldTerminateApp", true);
            capabilities.setCapability("autoLaunch", "true");
            capabilities.setCapability("autoAcceptAlerts", "true");
            logger.info("Capabilities: "+ capabilities);
        }
        return capabilities;

    }


    public static DesiredCapabilities browserStackCapabilities() throws IOException {
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "/src/main/resources/global.properties");
        Properties prop = new Properties();
        prop.load(fis);
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("browserstack.user", userId);// Set your access credentials
        capabilities.setCapability("browserstack.key", accessKey);
        capabilities.setCapability("project",prop.getProperty("project") );
        capabilities.setCapability("build", prop.getProperty("build")+" "+new SimpleDateFormat("MMM dd").format(Calendar.getInstance().getTime()));
        capabilities.setCapability("name", prop.getProperty("name"));
        capabilities.setCapability("browserstack.appium_version", "1.22.0");
       // capabilities.setCapability("browserstack.geoLocation", "IN");
        capabilities.setCapability("browserstack.idleTimeout", "210");

        if (System.getenv("platform").equalsIgnoreCase("Android")) {
            String neovoAndroidAppBS = System.getenv("neovoAndroid");
            String sandboxAndroidAppBS = System.getenv("sandboxAndroid");

            if (neovoAndroidAppBS.isEmpty() || sandboxAndroidAppBS.isEmpty()){
                logger.info("Please add broweserStack app env name 'neovoAndroid and sandboxAndroid' and bs//: app key");
                System.exit(0);
            }else {
                if(appName.equalsIgnoreCase("neovo")){
                    capabilities.setCapability("app", neovoAndroidAppBS);// Set URL of the application under test
                }else {
                    capabilities.setCapability("app",sandboxAndroidAppBS);
                }
            }
            capabilities.setCapability("os_version", prop.getProperty("platformVersionBS"));
            capabilities.setCapability("device", prop.getProperty("deviceNameBS"));// Specify device and os_version for testing
            capabilities.setCapability("autoGrantPermissions", "true");
        }
        else {
            String neovoiOSAppBS = System.getenv("neovoiOS");
            String sandboxiOSAppBS = System.getenv("sandboxiOS");

            if (neovoiOSAppBS.isEmpty() || sandboxiOSAppBS.isEmpty()){
                logger.info("Please add broweserStack app env name 'neovoiOS and sandboxiOS' and bs//: app key");
                System.exit(0);
            }else {
                if(appName.equalsIgnoreCase("neovo")){
                    capabilities.setCapability("app", neovoiOSAppBS);
                }else {
                    capabilities.setCapability("app", sandboxiOSAppBS);
                }
            }
            capabilities.setCapability("os_version", prop.getProperty("platformVersionBSiOS"));
            capabilities.setCapability("device", prop.getProperty("deviceNameBSiOS"));
            capabilities.setCapability("autoAcceptAlerts", "true");
        }
        logger.info("Capabilities: "+ capabilities);
        return capabilities;
    }
}
