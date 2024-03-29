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

        if (result.getStatus() == ITestResult.FAILURE) {
            String base64Screenshot = ScreenUtil.getBase64ScreenshotWeb(webDriver);
            ScreenUtil.convertBase64StringToImage(base64Screenshot, result.getMethod().getMethodName());
            ExtentTestManager.getTest().log(LogStatus.FAIL,
                    "Test " + result.getMethod() + " has failed",
                    ExtentTestManager.getTest().addBase64ScreenShot(base64Screenshot));
        }
        jiraCreationOnFailure(result);
        webDriver.quit();
        logger.info("browser tear down successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "browser tear down successfully");
    }


    public void verifyShopifyWebHomePageDisplay() {
        waitElement(loginWebPage.getShopifyLogo(), 30);
        assertThat(loginWebPage.getShopifyLogo().isDisplayed(), equalTo(true));
        assertThat(loginWebPage.getShopifyLogin().isDisplayed(), equalTo(true));
        assertThat(loginWebPage.getMarket().isDisplayed(), equalTo(true));
        assertThat(loginWebPage.getManage().isDisplayed(), equalTo(true));
        assertThat(loginWebPage.getSell().isDisplayed(), equalTo(true));
        assertThat(loginWebPage.getStart().isDisplayed(), equalTo(true));
        logger.info("verify Shopify website Homepage successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "verify Shopify website Homepage successfully");
        waitFor(1);
    }

    public void clickOnShopifyLoginButton() {
        waitElement(loginWebPage.getShopifyLogin(), 3);
        assertThat(loginWebPage.getShopifyLogin().isDisplayed(), equalTo(true));
        loginWebPage.getShopifyLogin().click();
        logger.info("ShopifyLogin button clicked successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "ShopifyLogin button clicked successfully");
    }

    public void clickOnShopifyMarket() {
        waitElement(loginWebPage.getMarket(), 30);
        assertThat(loginWebPage.getMarket().isDisplayed(), equalTo(true));
        loginWebPage.getMarket().click();
        logger.info("ShopifyMarket button clicked successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "ShopifyMarket button clicked successfully");

    }



    public void isShopifyLoginPageLoaded(){
        waitElement(loginWebPage.getShopifyEmailTextField(), 10);
        logger.info("Shopify login page is loaded");
        ExtentTestManager.getTest().log(LogStatus.PASS, "ShopifyMarket button clicked successfully");
    }

    public void enterEmailInShopifyLogin(String email){
        waitElement(loginWebPage.getShopifyEmailTextField(), 9);
        assertThat(loginWebPage.getShopifyEmailTextField().isDisplayed(), equalTo(true));
        loginWebPage.getShopifyEmailTextField().sendKeys(email);
        logger.info("Email is entered");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Email is entered");
    }

    public void clickContinueWithEmailButton(){
        assertThat(loginWebPage.getContinueWithEmailButton().isDisplayed(), equalTo(true));
        loginWebPage.getContinueWithEmailButton().click();
        logger.info("Continue with email button clicked");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Continue with email button clicked");
    }

    public void enterPasswordInShopifyLogin(String password){
        waitElement(loginWebPage.getShopifyPasswordTextField(), 10);
        assertThat(loginWebPage.getShopifyPasswordTextField().isDisplayed(), equalTo(true));
        loginWebPage.getShopifyPasswordTextField().sendKeys(password);
        logger.info("Password is entered");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Password is entered");
    }

    public void clickLoginButton(){
        assertThat(loginWebPage.getLoginButtonInShopify().isDisplayed(), equalTo(true));
        loginWebPage.getLoginButtonInShopify().click();
        logger.info("Login Button Clicked");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Login Button Clicked");
    }

    public void clickStagingSalesChannel(){
        waitElement(loginWebPage.getStagingSalesChannel(), 10);
        loginWebPage.getStagingSalesChannel().click();
        logger.info("Staging sales channel opened");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Staging sales channel opened");
    }

    public void clickDesigntab(){
        waitElement(loginWebPage.getDesignTab(), 10);
        loginWebPage.getDesignTab().click();
        logger.info("Design tab opened");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Design tab opened");
    }

    public void clickNavigationMenu(){
        waitElement(loginWebPage.getNavigationTab(), 10);
        loginWebPage.getNavigationTab().click();
        logger.info("NavigationTab tab opened");
        ExtentTestManager.getTest().log(LogStatus.PASS, "NavigationTab tab opened");
    }

        public void scrollUpDown(int xOffset, int yOffset) {
            waitFor(2);
            JavascriptExecutor js = (JavascriptExecutor) webDriver;
            waitFor(1);
            js.executeScript("window.scrollBy(" + xOffset + ", " + yOffset + ")");
            waitFor(1);
        }

        public void scrollToText(String text) {
            WebElement ele = webDriver.findElement(By.xpath("//*[text()='" + text + "']"));
            ((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView(true);", ele);
            waitFor(2);
        }

        public void clickByCoordinate(int xCoordinate, int YCoordinate) {
            Actions action = new Actions(webDriver);
            action.moveByOffset(xCoordinate, YCoordinate).doubleClick().perform();

        }

        public void scrollToButtom() {
            waitFor(1);
//		((JavascriptExecutor) driver)
//				.executeScript("window.scrollTo(0, document.body.scrollHeight)");
            webDriver.findElement(By.tagName("body")).sendKeys(Keys.END);
            waitFor(1);
        }


        public void scrollToTop() {
            waitFor(1);
            webDriver.findElement(By.tagName("body")).sendKeys(Keys.HOME);
            waitFor(1);
        }

        public void switchToChildWindow() {
            waitFor(3);
            logger.info(webDriver.getWindowHandles().size());
            String parentWindow = webDriver.getWindowHandle();
            Set<String> s1 = webDriver.getWindowHandles();
            Iterator<String> i1 = s1.iterator();
            while (i1.hasNext()) {
                String childWindow = i1.next();
                if (!parentWindow.equalsIgnoreCase(childWindow)) {
                    webDriver.switchTo().window(childWindow);
                }
            }

            logger.info("Child window selected successfully");
            ExtentTestManager.getTest().log(LogStatus.PASS, "Child window selected successfully");
        }

        public void switchToTab(int i) {
            ArrayList<String> tabs = new ArrayList<String>(webDriver.getWindowHandles());
            waitFor(1);
            webDriver.switchTo().window(tabs.get(i));
            logger.info("parent window selected successfully");
            ExtentTestManager.getTest().log(LogStatus.PASS, "parent window selected successfully");
        }

        public void launchUrl(String url) {
            webDriver.get(url);
            logger.info("url " + url + " lunch successfully");
            ExtentTestManager.getTest().log(LogStatus.PASS, "url " + url + " lunch successfully");
        }

        public void closeCurrentTab() {
            waitFor(1);
            webDriver.close();
            logger.info("current tab closed successfully");
            ExtentTestManager.getTest().log(LogStatus.PASS, "current tab closed successfully");
        }

        public void actionWithText(String text) {
            waitFor(1);
            new FluentWait<>(webDriver).withTimeout(Duration.ofSeconds(30)).pollingEvery(Duration.ofSeconds(2))
                    .ignoring(StaleElementReferenceException.class).until(new Function() {
                        @Override
                        public Object apply(Object arg0) {
                            WebElement e = webDriver.findElement(By.xpath("//*[text()='" + text + "']"));
                            Actions action = new Actions(webDriver);
                            action.moveToElement(e).click().perform();
                            return true;
                        }
                    });
        }

        public void refreshPage() {
            waitFor(1);
            webDriver.navigate().refresh();
            logger.info("page refresh successfully");
            waitElement(loginWebPage.getNavigationTab(), 10);
            loginWebPage.getNavigationTab().click();
            logger.info("Navigation sub tab opened");
            ExtentTestManager.getTest().log(LogStatus.PASS, "Navigation sub tab opened");
        }

        public void clickHamburgerNavigationMenu(){
            waitElement(loginWebPage.getHamburgerNavigationTab(), 10);
            loginWebPage.getHamburgerNavigationTab().click();
            logger.info("Hamburger Navigation sub tab opened");
            ExtentTestManager.getTest().log(LogStatus.PASS, "Hamburger Navigation sub tab opened");
        }

        public void toggleHamburgerNavigationMenu(Boolean value) {
            waitElement(loginWebPage.getHamburgerNavigationToggle(), 10);
            if (value) {
                if (loginWebPage.getHamburgerNavigationToggle().getAttribute("aria-checked").equals("false")) {
                    loginWebPage.getHamburgerNavigationToggle().click();
                    logger.info("Hamburger Navigation toggled ON");
                    ExtentTestManager.getTest().log(LogStatus.PASS, "Hamburger Navigation toggled ON");
                }
            } else {
                if (loginWebPage.getHamburgerNavigationToggle().getAttribute("aria-checked").equals("true")) {
                    loginWebPage.getHamburgerNavigationToggle().click();
                    logger.info("Hamburger Navigation toggled ON");
                    ExtentTestManager.getTest().log(LogStatus.PASS, "Hamburger Navigation toggled ON");
                }
            }
        }


        public void clickSaveButton(){
            assertThat(loginWebPage.getSaveButton().isDisplayed(), equalTo(true));
            loginWebPage.getSaveButton().click();
            logger.info("Save button clicked");
            ExtentTestManager.getTest().log(LogStatus.PASS, "Save button clicked");
        }

        public void verifySaveSuccessMessage(){
            waitElement(loginWebPage.getSavedSuccessMessage(), 30);
            logger.info("Store successfully saved");
            ExtentTestManager.getTest().log(LogStatus.PASS, "Store successfully saved");
        }

        public void clickPublishTab(){
            assertThat(loginWebPage.getPublishTab().isDisplayed(), equalTo(true));
            loginWebPage.getPublishTab().click();
            logger.info("Publish tab clicked");
            ExtentTestManager.getTest().log(LogStatus.PASS, "Publish tab clicked");
        }

        public void clickPublishButton(){
            waitElement(loginWebPage.getPublishButton(), 10);
            loginWebPage.getPublishButton().click();
            logger.info("Publish Button clicked");
            ExtentTestManager.getTest().log(LogStatus.PASS, "Publish Button clicked");
        }

        public void verifyPublishSuccess(){
            waitElement(loginWebPage.getPublishSuccessMessage(), 20);
            logger.info("Store successfully published");
            ExtentTestManager.getTest().log(LogStatus.PASS, "Store successfully published");
        }


        public void clickSelectForApp(){
            waitElement(loginWebPage.getSelectApp(), 9);
            loginWebPage.getSelectApp().click();
            logger.info("Neovo app selected");
            ExtentTestManager.getTest().log(LogStatus.PASS, "Neovo app selected");
        }

    public void isPlobalStageLoginPageDisplayed(){
        waitElement(loginWebPage.getLogIn(), 10);
        assertThat(loginWebPage.getLogIn().isDisplayed(), equalTo(true));
        assertThat(loginWebPage.getEmail().isDisplayed(), equalTo(true));
        assertThat(loginWebPage.getPassword().isDisplayed(), equalTo(true));
        assertThat(loginWebPage.getLoginButton().isDisplayed(), equalTo(true));
        logger.info("verify Plobal Stage Login PageStore successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "verify Plobal Stage Login PageStore successfully");
    }
}
