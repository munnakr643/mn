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
        File dsf = driver.getScreenshotAs(OutputType.FILE);
        String fileName = "Method_" + methodName + ".jpg";
        File newFile = new File(path + fileName);
        try {
            FileUtils.copyFile(dsf, newFile);
        } catch (IOException e) {
            logger.error("Error generating the screenshot", e);
        }
    }


    public static String getBase64ScreenshotMobile( AppiumDriver<MobileElement> driver ) {
        String base64Screenshot = "data:image/png;base64,"
                + ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
        return base64Screenshot;
    }

    public static String getBase64ScreenshotWeb( WebDriver driver) {
        String base64Screenshot = "data:image/png;base64,"
                + ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
        return base64Screenshot;
    }
    public static void takeScreenShotAsImage( AppiumDriver<MobileElement> driver, String fileWithPath ) {
        TakesScreenshot scrShot = driver;
        File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);
        File DestFile = new File(fileWithPath);
        try {
            FileUtils.copyFile(SrcFile, DestFile);
        } catch (IOException e) {
            logger.error("Error generating the screenshot", e);
            e.printStackTrace();
        }
    }

    /**
     * Method converts the Base64 string image into a image file and saves in target/Screenshots directory
     * @param base64String
     * @param fileName
     * @return
     */
    public static String convertBase64StringToImage(String base64String, String fileName){
        String[] strings = base64String.split(",");
        String extension;
        switch (strings[0]) {
            case "data:image/jpeg;base64":
                extension = ".jpeg";
                break;
            case "data:image/png;base64":
                extension = ".png";
                break;
            default:
                extension = ".jpg";
                break;
        }
        String screenshotDir = System.getProperty("user.dir") + "/target/Screenshots";
        String path = screenshotDir + "/" + fileName + RandomStringUtils.randomNumeric(5) + extension;
        //convert base64 string to binary data
        try {
            Files.createDirectories(Paths.get(screenshotDir));
            byte[] data = Base64.getMimeDecoder().decode(strings[1]);
            BufferedImage bufImg = null;
            bufImg = ImageIO.read(new ByteArrayInputStream(data));

            File imgOutFile = new File(path);
            ImageIO.write(bufImg, "png", imgOutFile);
        } catch (IOException e) {
            logger.error(e);
            throw new RuntimeException(e);
        }
        return path;
    }

    /**
     * Method deletes the directory and its files recursively if it exists.
     * @param file
     */
    public static void deleteFolder(File file) {
        for (File subFile : file.listFiles()) {
            if (subFile.isDirectory()) {
                deleteFolder(subFile);
            } else {
                subFile.delete();
            }
        }
        file.delete();
    }

}
