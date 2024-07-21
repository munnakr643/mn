package qa.automation.base;

import com.relevantcodes.extentreports.LogStatus;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import qa.automation.pageScreenWeb.LoginWebPage;
import qa.automation.report.ExtentTestManager;
import qa.automation.utilities.ScreenUtil;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;
import java.util.function.Function;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class WebBaseTest extends BaseTest {
    public static Logger logger = LogManager.getLogger(WebBaseTest.class);

    public WebDriver webDriver;
    LoginWebPage loginWebPage;

    @BeforeMethod(alwaysRun = true)
    public void setupWebDriver() {
        webDriver = initWebDriver();
        initPages();
    }

    public void initPages() {
        loginWebPage = new LoginWebPage(webDriver);
    }


    /**
     * Returns the webDriver Object
     *
     * @return
     */
    public WebDriver getWebDriver() {
        return webDriver;
    }

    public void captureScreen(WebDriver driver, String tname) throws IOException {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        File target = new File(System.getProperty("user.dir") + "/Screenshots/" + tname + ".png");
        FileUtils.copyFile(source, target);
        System.out.println("Screenshot taken");
    }

    public void waitElement(WebElement element, int timer) {
        waitForElement(element, timer);
    }

    public void waitForElement(WebElement element, long timer) {
        WebDriverWait wait = new WebDriverWait(webDriver, timer);
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void waitElementForClickable(WebElement element, long timer) {
        WebDriverWait wait = new WebDriverWait(webDriver, timer);
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public static void waitFor(double seconds) {
        try {
            Thread.sleep((int) (seconds * 1000));
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    public String randomString(int length) {
        String generatedstring = RandomStringUtils.randomAlphabetic(length);
        return (generatedstring);
    }

    public static String randomNum() {
        String generatedString2 = RandomStringUtils.randomNumeric(4);
        return (generatedString2);
    }

    public static String randomNum8() {
        String generatedString2 = RandomStringUtils.randomNumeric(8);
        return (generatedString2);
    }

    @AfterMethod
    public void extentReportsFailOperation(ITestResult result) throws IOException {

      
        logger.info("browser tear down successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "browser tear down successfully");
    }


   
}
