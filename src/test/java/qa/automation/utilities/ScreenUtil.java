package qa.automation.utilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.TakesScreenshot;


import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;

public class ScreenUtil {
    private final static Logger logger = LogManager.getLogger(ScreenUtil.class);


    public static void captureScreenshot( AppiumDriver<MobileElement> driver, final String path,
                                          final String methodName ) {
    }


    public static String getBase64ScreenshotMobile( AppiumDriver<MobileElement> driver ) {
       
        return base64Screenshot;
    }

    public static String getBase64ScreenshotWeb( WebDriver driver) {
       
        return base64Screenshot;
    }
    public static void takeScreenShotAsImage( AppiumDriver<MobileElement> driver, String fileWithPath ) {
       
    }

    /**
     * Method converts the Base64 string image into a image file and saves in target/Screenshots directory
     * @param base64String
     * @param fileName
     * @return
     */
    public static String convertBase64StringToImage(String base64String, String fileName){
        String path = null;
        return path;
    }

    /**
     * Method deletes the directory and its files recursively if it exists.
     * @param file
     */
    public static void deleteFolder(File file) {
    }

}
