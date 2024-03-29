package qa.automation.base;

import com.google.common.collect.ImmutableMap;
import com.relevantcodes.extentreports.LogStatus;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import qa.automation.enums.DeeplinkPathEnum;
import qa.automation.enums.FilterOptionsEnum;
import qa.automation.enums.SortingOptionsEnum;
import qa.automation.enums.StoreNameEnum;
import qa.automation.integrations.AWSSecretsManagerUtils;
import qa.automation.pageScreenDevice.*;
import qa.automation.pageScreenDevice.mobile.PlobalAppsSample;
import qa.automation.report.ExtentTestManager;
import qa.automation.utilities.ReadConfig;
import qa.automation.utilities.ScreenUtil;




import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.function.Function;

import static io.appium.java_client.touch.WaitOptions.waitOptions;
import static java.time.Duration.ofSeconds;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;



public class MobileBaseTest extends BaseTest {
    private static final Logger logger = Logger.getLogger(MobileBaseTest.class);
    protected static AllAppList allAppList;
    public static LoginPage loginPage;
    protected static HomePage homePage;
    public static CollectionPage collectionPage;
    public static PDPPage pDpPage;
    public static MyAccount myAccount;
    public static PLPPage pLpPage;

    public static CartPage cartPage;
    public static OnBoardingPage onBoardingPage;
    public static PlobalAppsSample plobalAppsSample;
    public static AddressPage addressPage;
    public static DrawerPage drawerPage;
    public static FavoritePage favoritePage;
    public static ProfilePage profilePage;
    public static CheckoutPage checkoutPage;
    public static MyOrdersPage myOrdersPage;

    public static SearchPage searchPage;

    public static MorePage morePage;

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
        allAppList = new AllAppList(mobileDriver);
        collectionPage = new CollectionPage(mobileDriver);
        onBoardingPage = new OnBoardingPage(mobileDriver);
        pDpPage = new PDPPage(mobileDriver);
        pLpPage = new PLPPage(mobileDriver);
        myAccount = new MyAccount(mobileDriver);
        plobalAppsSample = new PlobalAppsSample(mobileDriver);
        cartPage = new CartPage(mobileDriver);
        addressPage = new AddressPage(mobileDriver);
        drawerPage = new DrawerPage(mobileDriver);
        favoritePage = new FavoritePage(mobileDriver);
        profilePage = new ProfilePage(mobileDriver);
        checkoutPage = new CheckoutPage(mobileDriver);
        myOrdersPage = new MyOrdersPage(mobileDriver);
        morePage = new MorePage(mobileDriver);
        searchPage = new SearchPage(mobileDriver);
        thankYouPage = new ThankYouPage(mobileDriver);

    }

    @AfterMethod(alwaysRun = true)
    public void afterMethodMobile(ITestResult result) {
        updateZephyrTestResult(result);
        extentReportsFailOperationMobile(result);
        jiraCreationOnFailure(result);
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

    public void actionWithText(String classN1, String text) {
        waitFor(1);
        new FluentWait<>(mobileDriver).withTimeout(ofSeconds(30)).pollingEvery(ofSeconds(2))
                .ignoring(StaleElementReferenceException.class).until(new Function() {
                    @Override
                    public Object apply(Object arg0) {
                        WebElement e = mobileDriver.findElement(By.xpath("//" + classN1 + "[text()='" + text + "']"));
                        e.click();
                        return true;
                    }
                });
    }

    public void entreUserName() {
        waitElement(loginPage.getUserId(), 3);
        assertThat(loginPage.getUserId().isDisplayed(), equalTo(true));
        loginPage.getUserId().sendKeys(readconfig.getUsername());
        logger.info("userID " + readconfig.getUsername() + " Enter successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "userID " + readconfig.getUsername() + " Enter successfully");
    }

    public void clickOnConfirmBtn() {
        waitElement(loginPage.getConfirmBtn(), 3);
        assertThat(loginPage.getConfirmBtn().isDisplayed(), equalTo(true));
        loginPage.getConfirmBtn().click();
        logger.info("ConfirmBtn clicked successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "ConfirmBtn clicked successfully");
    }


    public void enterOtp() {
        waitElement(loginPage.getOtpbox(), 20);
        assertThat(loginPage.getOtpbox().isDisplayed(), equalTo(true));
        String otp = readconfig.getPassword();
        for (int i = 1; i <= otp.length(); i++) {
            String num = String.valueOf(otp.charAt(i - 1));
            int t = i + 1;
            MobileElement enterotp = mobileDriver.findElement(By.xpath("(//android.widget.FrameLayout[contains(@resource-id,'otpTextView')])[1]"));
            enterotp.sendKeys(num);
        }
        logger.info("otp " + otp + " Enter successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "otp " + otp + " Enter successfully");
        waitFor(1);
    }

    public void enterOtp(String password) {
        waitElement(loginPage.verifyOtp, 6);
        assertThat(loginPage.verifyOtp.isDisplayed(), equalTo(true));
        loginPage.getOtpbox().sendKeys(password);
        logger.info("otp " + password + " Enter successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Otp entered successfully");

    }

    public void enterOtp1() {
        waitFor(.5);
        cooClick(120, 1244);
        clickCoordinates(120, 1244);
        waitFor(2);
        ((AndroidDriver) mobileDriver).pressKey(new KeyEvent(AndroidKey.DIGIT_1));
        waitFor(.5);
        ((AndroidDriver) mobileDriver).pressKey(new KeyEvent(AndroidKey.DIGIT_1));
        waitFor(.5);
        ((AndroidDriver) mobileDriver).pressKey(new KeyEvent(AndroidKey.DIGIT_1));
        waitFor(.5);
        ((AndroidDriver) mobileDriver).pressKey(new KeyEvent(AndroidKey.DIGIT_1));
        waitFor(.5);
        ((AndroidDriver) mobileDriver).pressKey(new KeyEvent(AndroidKey.DIGIT_1));
        waitFor(.5);
        ((AndroidDriver) mobileDriver).pressKey(new KeyEvent(AndroidKey.DIGIT_1));
        waitFor(.5);
        ((AndroidDriver) mobileDriver).pressKey(new KeyEvent(AndroidKey.ENTER));
        waitFor(.6);

    }

    /**
     * App Name: SM
     * Assignee Name: Aditya Nisal
     * Verify homepage for SM and Platform
     */
    public void verifyHomepage() {
        if (appName.equalsIgnoreCase("Steve")) {
            waitElement(homePage.getCartForSM(), 16);
            assertThat(homePage.getAppLogoOnHomePage().isDisplayed(), equalTo(true));
            assertThat(homePage.getSearchProductsText().isDisplayed(), equalTo(true));
            assertThat(homePage.getHamburgerMenuForSM().isDisplayed(), equalTo(true));
        } else if (appName.equalsIgnoreCase("neovo")) {
            waitElement(homePage.getCart(), 10);
            assertThat(homePage.getCart().isDisplayed(), equalTo(true));
        } else {
            waitFor(2);
            waitElement(homePage.getProfileTabOnHomePage(), 10);
            assertThat(homePage.getSearchProductsText().isDisplayed(), equalTo(true));
        }
        logger.info(appName + " Home page verify successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, appName + " Home page verify successfully");
    }


    public void clickOnListIcon() {
        waitElement(loginPage.getListicon(), 6);
        assertThat(loginPage.getListicon().isDisplayed(), equalTo(true));
        loginPage.getListicon().click();
        logger.info("Clicked on collection icon tab");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Clicked on collection icon tab");
    }

    /**
     * App Name: SM
     * Assignee Name: Aditya Nisal
     * User is able to Fetch Product Price from plp
     */
    public String getProductPriceFromPLP() {
        String productPrice;
        if (appName.equals("Steve")) {
            waitElement(pLpPage.getCobraBlackLeatherPriceForSm(), 10);
            assertThat(pLpPage.getCobraBlackLeatherPriceForSm().isDisplayed(), equalTo(true));
            productPrice = pLpPage.getCobraBlackLeatherPriceForSm().getText();
        } else {
            waitElement(pLpPage.getWhiteMiniDressPrice(), 10);
            assertThat(pLpPage.getWhiteMiniDressPrice().isDisplayed(), equalTo(true));
            productPrice = pLpPage.getWhiteMiniDressPrice().getText();
        }
        logger.info("Featched Product Price from PLP successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Featched Product Price PLP successfully");
        return productPrice;
    }

    /**
     * App Name: SM
     * Assignee Name: Aditya Nisal
     * User is able to invoke PDP product from PLP for SM and platfrom
     */
    public String selectProductFromPLP() {
        String productTitle;
        if (appName.equals("Steve")) {
            waitElement(pLpPage.getProductPLPForSM(), 10);
            productTitle = pLpPage.getProductNameFromPLPForSM().getText();
            pLpPage.getProductNameFromPLPForSM().click();

        } else {
            waitElement(pLpPage.getWhiteMiniDress(), 10);
            assertThat(pLpPage.getWhiteMiniDress().isDisplayed(), equalTo(true));
            productTitle = pLpPage.getWhiteMiniDress().getText();
            pLpPage.getWhiteMiniDress().click();
        }
        logger.info("select product successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "select product successfully");
        return productTitle;
    }

    /**
     * App Name: SM
     * Assignee Name: Aditya Nisal
     * User is able to Fetch average star rating from plp
     */
    public String getAverageRatingFromPlp() {
        waitElement(pLpPage.getAverageRatingFromPlpForSm(), 10);
        assertThat(pLpPage.getAverageRatingFromPlpForSm().isDisplayed(), equalTo(true));
        String productAverageRating = pLpPage.getAverageRatingFromPlpForSm().getText();
        logger.info("Featched Product average rating from PLP successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Featched Product average rating PLP successfully");
        return productAverageRating;
    }

    public void addProductIntoCartFromPDP() {
        waitElement(pDpPage.getAddCart(), 6);
        assertThat(pDpPage.getAddCart().isDisplayed(), equalTo(true));
        pDpPage.getAddCart().click();
        logger.info(" product added in cart successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "product added in cart successfully");
    }

    public void clickOnBuyNowFromPDP() {
        waitElement(pDpPage.getBuyNow(), 6);
        assertThat(pDpPage.getBuyNow().isDisplayed(), equalTo(true));
        pDpPage.getBuyNow().click();
        logger.info("click on buy now successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "click on buy now successfully");
    }

    public void clickOnConfirm() {
        if (isPlatformNameAndroid) {
            waitElement(pDpPage.getConfirm(), 6);
            pDpPage.getConfirm().click();
        } else {
            waitFor(5); //Wait added to avoid breaking other test cases
            pDpPage.getConfirm().click();
        }
        logger.info(" click on Confirm button successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "click on Confirm button successfully");
    }

    public void clickOnCartIcon() {
        waitElement(homePage.getCartIcon(), 6);
        assertThat(homePage.getCartIcon().isDisplayed(), equalTo(true));
        homePage.getCartIcon().click();
        logger.info(" click on getCartIcon successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "click on getCartIcon successfully");
    }

    public void clickOnPlaceOrder() {
        if (appName.equalsIgnoreCase("Sandbox")) {
            waitElement(cartPage.getPlaceOrderNative(), 6);
            assertThat(cartPage.getPlaceOrderNative().isDisplayed(), equalTo(true));
            cartPage.getPlaceOrderNative().click();
            logger.info("Click on Place Order button successfully");
            ExtentTestManager.getTest().log(LogStatus.PASS, "Click on Place Order button successfully");
        } else {
            waitElement(loginPage.getPlaceOrder(), 6);
            assertThat(loginPage.getPlaceOrder().isDisplayed(), equalTo(true));
            loginPage.getPlaceOrder().click();
            logger.info(" click on getPlaceOrder successfully");
            ExtentTestManager.getTest().log(LogStatus.PASS, "click on getPlaceOrder successfully");
        }
    }

    public void enterFirstName(String fname) {
        waitElement(loginPage.getFName(), 6);
        assertThat(loginPage.getFName().isDisplayed(), equalTo(true));
        loginPage.getFName().sendKeys(fname);
        logger.info("Name " + fname + " entered successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "fname " + fname + " entered successfully");
    }

    public void enterLastName(String lname) {
        waitElement(loginPage.getLName(), 6);
        assertThat(loginPage.getLName().isDisplayed(), equalTo(true));
        loginPage.getLName().sendKeys(lname);
        logger.info("lname " + lname + " entered successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "lname " + lname + " entered successfully");
    }

    public void clickOnContinueButton() {
        waitElement(checkoutPage.getContinueButton(), 6);
        assertThat(checkoutPage.getContinueButton().isDisplayed(), equalTo(true));
        checkoutPage.getContinueButton().click();
        logger.info("Continue clicked successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Continue clicked successfully");

    }

    public void clickOnContinueToPay() {
        waitElement(loginPage.getContinueToPay(), 60);
        assertThat(loginPage.getContinueToPay().isDisplayed(), equalTo(true));
        loginPage.getContinueToPay().click();
        logger.info("Continue clicked successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Continue clicked successfully");

    }

    public void enterCardNumber(String card) {
        waitElement(checkoutPage.getCardNumber(), 60);
        assertThat(checkoutPage.getCardNumber().isDisplayed(), equalTo(true));
        checkoutPage.getCardNumber().sendKeys(card);
        logger.info("card " + card + " entered successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "pincode " + card + " entered successfully");
    }

    public void enterCardHolderName(String name) {
        waitElement(checkoutPage.getNameOnCard(), 6);
        assertThat(checkoutPage.getNameOnCard().isDisplayed(), equalTo(true));
        checkoutPage.getNameOnCard().sendKeys(name);
        logger.info("name " + name + " entered successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "name " + name + " entered successfully");
    }

    public void enterCardExpDate(String expDate) {
        waitElement(checkoutPage.getExpDate(), 6);
        assertThat(checkoutPage.getExpDate().isDisplayed(), equalTo(true));
        checkoutPage.getExpDate().sendKeys(expDate);
        logger.info("expDate " + expDate + " entered successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "expDate " + expDate + " entered successfully");
    }

    public void enterCardCvv(String cvv) {
        waitElement(checkoutPage.getCvv(), 6);
        assertThat(checkoutPage.getCvv().isDisplayed(), equalTo(true));
        checkoutPage.getCvv().sendKeys(cvv);
        logger.info("getCvv " + cvv + " entered successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "getCvv " + cvv + " entered successfully");
    }

    public void clickOnPayNow() {
        waitElement(checkoutPage.getPayNow(), 6);
        assertThat(checkoutPage.getPayNow().isDisplayed(), equalTo(true));
        checkoutPage.getPayNow().click();
        logger.info("getPayNow clicked successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "getPayNow clicked successfully");

    }

    public void isThankYouPageDisplayed() {
        waitFor(1);
        waitElement(checkoutPage.getThankyouPage(), 10);
        assertThat(checkoutPage.getThankyouPage().isDisplayed(), equalTo(true));
        assertThat(checkoutPage.getThankyouText().isDisplayed(), equalTo(true));
        assertThat(checkoutPage.getContinueShopping().isDisplayed(), equalTo(true));
        logger.info("ThankYouPage verified successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "ThankYouPage verified successfully");
    }

    public void clickOnContinueShopping() {
        waitElement(checkoutPage.getContinueShopping(), 3);
        assertThat(checkoutPage.getContinueShopping().isDisplayed(), equalTo(true));
        checkoutPage.getContinueShopping().click();
        logger.info("getContinueShopping clicked successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "getContinueShopping clicked successfully");
    }

    /**
     * This method enter text inside search field for platform
     */
    public void enterSearchText(String text) {
        waitElement(homePage.getEnterSearchKey(), 3);
        assertThat(homePage.getEnterSearchKey().isDisplayed(), equalTo(true));
        homePage.getEnterSearchKey().sendKeys(text);
        logger.info("Enter search text successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Enter search text successfully");
    }


    public void waitElement(MobileElement element, int timer) {
        WebDriverWait wait = new WebDriverWait(mobileDriver, timer);
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    private String getWebContext(AppiumDriver driver) {
        ArrayList<String> contexts = new ArrayList(driver.getContextHandles());
        for (String context : contexts) {
            if (!context.equals("NATIVE_APP")) {
                return context;
            }
        }
        return null;
    }

    public void scrollToTextNclick(String text) {
        mobileDriver.findElement(MobileBy
                        .AndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"" + text + "\"));"))
                .click();

    }

    /**
     * Method scrolls down to the text on Android Apps
     *
     * @param text
     */
    public void scrollToText(String text) {
        ((AndroidDriver) mobileDriver).findElement(MobileBy.AndroidUIAutomator(
                "new UiScrollable(new UiSelector().scrollable(true))" +
                        ".scrollIntoView(new UiSelector().text(\"" + text + "\"))"));
    }

    public void scrollForIos() {
        JavascriptExecutor js = (JavascriptExecutor) mobileDriver;
        HashMap<String, String> scrollObject = new HashMap<>();
        scrollObject.put("direction", "down");
        js.executeScript("mobile: scroll", scrollObject);
    }


    /**
     * Method scrolls down to the element passed.
     *
     * @param element
     */
    public void scrollDownToIOSElement(MobileElement element) {
        HashMap scrollObjects = new HashMap();
        scrollObjects.put("element", ((RemoteWebElement) element).getId());
        scrollObjects.put("direction", "down");
        mobileDriver.executeScript("mobile: scroll", scrollObjects);
    }

    /**
     * Method scrolls up to the element passed.
     *
     * @param element
     */
    public void scrollUpToIOSElement(MobileElement element) {
        HashMap scrollObjects = new HashMap();
        scrollObjects.put("element", ((RemoteWebElement) element).getId());
        scrollObjects.put("direction", "up");
        mobileDriver.executeScript("mobile: scroll", scrollObjects);
    }

    /**
     * Method scrolls up for iOS device
     */
    public void scrollUpForIos() {
        JavascriptExecutor js = (JavascriptExecutor) mobileDriver;
        HashMap<String, String> scrollObject = new HashMap<>();
        scrollObject.put("direction", "up");
        js.executeScript("mobile: scroll", scrollObject);
    }


    /**
     * Method swipe up for iOS device based on direction(down,up,left,right)
     */
    public void swipeForIos(String dir) {
        waitFor(.5);
        JavascriptExecutor js = (JavascriptExecutor) mobileDriver;
        HashMap<String, String> scrollObject = new HashMap<>();
        scrollObject.put("direction", dir);
        js.executeScript("mobile: scroll", scrollObject);
        waitFor(.5);

    }

    public void swipe(int fromX, int fromY, int toX, int toY) {
        new TouchAction(mobileDriver)
                .press(PointOption.point(fromX, fromY))
                .waitAction(waitOptions(ofSeconds(2)))
                .moveTo(PointOption.point(toX, toY))
                .release()
                .perform();
        logger.info("Page swipe Successfully");

    }

    public void clickCoordinates(int x, int y) {
        new TouchAction(mobileDriver).tap(new PointOption().withCoordinates(x, y)).perform();
    }

    public void cooClick(int x, int y) {
        TouchAction touchAction = new TouchAction(mobileDriver);
        touchAction.press(PointOption.point(x, y)).perform().release();
    }

    /**
     * Owner: Aditya Nisal
     * App used is This method is used to invoke cart icon which present on PDP & User is able to redirected to cart page for SM
     * App name: Neovo-commerce & SM
     * Assignee Name: Aditya Nisal
     */
    public void clickCartIconOnPdpPage() {
        if (appName.equals("Steve")) {
            waitElement(pDpPage.getCartIconForSM(), 3);
            pDpPage.getCartIconForSM().click();
        } else {
            waitElement(pDpPage.getCartIcon(), 10);
            pDpPage.getCartIcon().click();
        }
        logger.info("Redirection to cart page verified successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Redirection to cart page verified successfully");
    }

    /**
     * Owner: Aditya Nisal
     * Verify that User details(First Name, Last Name) are showing on hamburger menu after user register/login to app
     * App name: Neovo-commerce and SM
     */
    public void checkUserNameOnHamburger(String username) {
        waitElement(homePage.getUserNameOnHamburgerMenuForNeovo(), 10);
        Assert.assertTrue(homePage.getUserNameOnHamburgerMenuForNeovo().getText().contains(username));
        logger.info("User details(First Name, Last Name) are showing on hamburger menu after user register/login to app");
        ExtentTestManager.getTest().log(LogStatus.PASS, "User details(First Name, Last Name) are showing on hamburger menu after user register/login to app");
    }

    /**
     * Owner: Aditya Nisal
     * Verify that User details(First Name, Last Name) are showing on hamburger menu after user register/login to app
     * App name: Neovo-commerce and SM
     */
    public void clickOnMyProfileFromHamburger() {
        waitElement(homePage.getMyProfileOnHamburger(), 30);
        homePage.getMyProfileOnHamburger().click();
        logger.info("My profile invoked successfully from hamburger menu");
        ExtentTestManager.getTest().log(LogStatus.PASS, "My profile invoked successfully from hamburger menu");
    }

    /**
     * Methods click on Login button on webveiw checkout page
     * App name: Neovo-commerce and SM
     */
    public void clickOnLoginButtonOnWebviewCheckout() {
        waitElement(checkoutPage.getLoginOnWebviewCheckout(), 9);
        checkoutPage.getLoginOnWebviewCheckout().click();
        logger.info("Invoked login page from webview checkout");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Invoked login page from webview checkout");
    }

    /**
     * Check Login button on webveiw checkout page
     * App name: Neovo-commerce and SM
     */
    public void checkLoginButtonOnWebviewCheckout() {
        waitElement(checkoutPage.getLoginOnWebviewCheckout(), 5);
        assertThat(checkoutPage.getLoginOnWebviewCheckout().isDisplayed(), equalTo(true));
        logger.info("Invoked login page from webview checkout");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Invoked login page from webview checkout");
    }

    /**
     * Owner: Aditya Nisal
     * PDP product title verification for platform regression (Product title is appearing on PDP or not)
     * App name: Neovo-commerce and SM
     */
    public void checkProductTitleOnPdp(String productTitle) {
        if (appName.equals("Steve")) {
            waitElement(pDpPage.getProductTitleOnPdpForSM(), 5);
            assertThat(pDpPage.getProductTitleOnPdpForSM().getText(), is(equalTo(productTitle)));
        } else {
            waitElement(pDpPage.getProductTitleOnPdp(), 5);
            assertThat(pDpPage.getProductTitleOnPdp().getText(), is(equalTo(productTitle)));
        }
        logger.info("Product title on PDP verified successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Product title on PDP verified successfully");
    }

    /**
     * Owner: Aditya Nisal
     * Fashion Collection invokation
     * App name: Neovo-commerce
     */
    public void clickOnFashionCollection() {
        waitElement(collectionPage.getFashionCollection(), 3);
        collectionPage.getFashionCollection().click();
        logger.info("Fashion Collection clicked successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Fashion Collection clicked successfully");
    }

    /**
     * Owner: Aditya Nisal
     * Dress Collection invokation
     * App name: Neovo-commerce
     */
    public void clickOnDressCollection() {
        waitElement(collectionPage.getDressCollection(), 3);
        collectionPage.getDressCollection().click();
        logger.info("Dress Collection clicked successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Dress Collection clicked successfully");
    }

    /**
     * Owner: Aditya Nisal
     * Product Price verification on PDP when user invokes PDP
     * App name: Neovo-commerce & SM
     */
    public void checkProductPriceOnPdp(String productPrice) {
        if (appName.equals("Steve")) {
            waitElement(pDpPage.getProductPriceOnPdpForSM(), 5);
            assertThat(pDpPage.getProductPriceOnPdpForSM().getText(), is(equalTo(productPrice)));
        } else {
            waitElement(pDpPage.getProductPriceOnPdp(), 5);
            Assert.assertTrue(pDpPage.getProductPriceOnPdp().getText().contains(productPrice));
        }
        logger.info("Check Product Price should appear PDP page verified successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Check Product Price should appear PDP page verified successfully");
    }

    /**
     * Owner: Aditya Nisal
     * Verify varient Price on PDP, as if user changes varient on PDP the price of the product will also change likewise
     * App name: Neovo-commerce
     */
    public void checkVarientPriceOnPdpPage() {
        waitElement(pDpPage.getProductPriceOnPdp(), 10);
        String Price1 = pDpPage.getProductPriceOnPdp().getText();
        pDpPage.getProductSizeL().click();
        waitFor(1);
        String Price2 = pDpPage.getProductPriceOnPdp().getText();
        assertThat(Price1, not(equalTo(Price2)));
        logger.info("Check Product price should be reflect according to selected variant on PDP page");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Check Product price should be reflect according to selected variant on PDP page");
    }

    /**
     * Owner: Aditya Nisal
     * On-Boarding 'Yes' & 'No' handling for platform regression
     * 1st On-boarding page for SM 1
     * App name: Neovo-commerce & SM
     * Assignee Name: Aditya Nisal
     */
    public void clickNoButtonOnOnboardingScreen() {
        if (appName.equals("Steve")) {
            waitElement(onBoardingPage.getNextButton(), 10);
            assertThat(onBoardingPage.getNextButton().isDisplayed(), equalTo(true));
            onBoardingPage.getNextButton().click();
            logger.info("SM On-Boarding Page 1 verified successfully");
        } else {
            waitFor(4);
            waitElement(onBoardingPage.getNoButton(), 20);
            onBoardingPage.getNoButton().click();
            logger.info("On-Boarding Page verified successfully");
            ExtentTestManager.getTest().log(LogStatus.PASS, "On-Boarding Page verified successfully");
        }
    }

    /**
     * App Name: SM
     * Assignee Name: Aditya Nisal
     * 2nd On-boarding page for SM 2
     */
    public void clickOnOnboarding2ScreenForSM() {
        waitElement(onBoardingPage.getNext1Button(), 10);
        assertThat(onBoardingPage.getNext1Button().isDisplayed(), equalTo(true));
        onBoardingPage.getNext1Button().click();
        logger.info("SM On-Boarding Page 2 verified successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "SM On-Boarding Page 2 verified successfully");
    }

    /**
     * App Name: SM
     * Assignee Name: Aditya Nisal
     * User is able to invoke PLP from homepage element 1 for SM
     */
    public void clickOnHomepageElement() {
        assertThat(homePage.getHomepageElement1().isDisplayed(), equalTo(true));
        homePage.getHomepageElement1().click();
        logger.info("Invoke PLP from homepage by invoking 1st element on homepage");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Invoke PLP from homepage by invoking 1st element on homepage");
    }

    /**
     * App Name: SM
     * Assignee Name: Aditya Nisal
     * User is able to verify for PLP page
     */
    public void verifyPlp() {
        if (appName.equalsIgnoreCase("neovo")) {
            waitElement(pLpPage.getSortButton(), 10);
            assertThat(pLpPage.getFilterButton().isDisplayed(), equalTo(true));
        } else {
            waitElement(pLpPage.getSortButton(), 10);
            assertThat(pLpPage.getSortButton().isDisplayed(), equalTo(true));
        }
        logger.info("PLP verify successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "PLP verify successfully ");
    }

    /**
     * App Name: SM
     * Assignee Name: Aditya
     * Search Icon redirection on PDP verified successfully for SM
     */
    public void clickOnPDPSearchIconForSM() {
        waitElement(pDpPage.getSearchIcon(), 3);
        pDpPage.getSearchIcon().click();
        logger.info("Redirection to Search Page verified successfully for SM");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Redirection to search page verified successfully for SM");
    }

    /**
     * App Name: SM
     * Assignee Name: Aditya Nisal
     * star Ratings on PDP verified successfully for SM
     */
    public void checkStarRatingOnPdpForSM(String productAverageRating) {
        waitElement(pDpPage.getStarRating(), 5);
        assertThat(pDpPage.getStarRating().getText(), is(equalTo(productAverageRating)));
        logger.info("star Ratings on PDP verified successfully for SM");
        ExtentTestManager.getTest().log(LogStatus.PASS, "star Ratings on PDP verified successfully for SM");
    }

    /**
     * App Name: SM
     * Assignee Name: Aditya Nisal
     * Variant Option name before pricing below product title in PDP page for SM
     */
    public void getVariantOptionNameOnPdp(String productOptionName) {
        waitElement(pDpPage.getVariantOptionName(), 5);
        assertThat(pDpPage.getVariantOptionName().getText(), is(equalTo(productOptionName)));
        logger.info("Varient option name is verifed successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Varient option name is verifed successfully");
    }

    /**
     * App Name: SM
     * Assignee Name: Aditya Nisal
     * Variant Option name before pricing below product title in PDP page for SM
     */

    public void getVariantOptionNameOnPdpAfterProductSwitch(String productOptionName1) {
        waitElement(pDpPage.getVariantOptionName(), 5);
        assertThat(pDpPage.getVariantOptionName().getText(), is(equalTo(productOptionName1)));
        logger.info("Varient option name is verifed successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Varient option name is verifed successfully");
    }

    /**
     * App Name: SM Assignee
     * Name: Aditya Nisal
     * Check user able to switch the variants in PDP Page for SM
     */

    public void verifyVariantSwitchForSM() {
        waitElement(pDpPage.getVariantImageForSM(), 5);
        waitElement(pDpPage.getShareButton(), 10);
        pDpPage.getVariantImageForSM().click();
        logger.info("Check user able to switch the variants in PDP Page");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Check user able to switch the variants in PDP Page");
    }

    /**
     * App Name: SM
     * Assignee Name: Aditya Nisal
     * Verify PDP page for SM
     */

    public void verifyPdp() {
        waitElement(pDpPage.getBuyNow(), 6);
        assertThat(pDpPage.getAddToCartButtonOnPDP().isDisplayed(), equalTo(true));
        waitElement(pDpPage.getWishlistButton(), 7);
        waitElement(pDpPage.getShareButton(), 3);
        logger.info("Verify PDP Successsfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Verify PDP Successsfully");
    }

    /**
     * App Name: SM
     * Assignee Name: Aditya Nisal
     * Average Rating count check on PLP for SM
     */
    public void checkAverageRatingCountOnPlp(String productRatingCount) {
        waitElement(pLpPage.getAverageRatingCountPlp(), 5);
        assertThat(pLpPage.getAverageRatingCountPlp().getText(), is(equalTo(productRatingCount)));
        logger.info("Check number of average rating count shown in PLP page");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Check number of average rating count shown in PLP page");
    }

    /**
     * owner : Aditya Nisal
     * Checking cart button is present on PDP
     * App name: Steve Madden
     */
    public void checkCartButtonOnPdp() {
        assertThat(pDpPage.getCartIconForSM().isDisplayed(), equalTo(true));
        logger.info("Cart Icon appears on Top right corner on PDP");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Cart Icon appears on Top right corner on PDP");
    }

    /**
     * owner : Aditya Nisal
     * Checking search button is present on PDP
     * App name: Steve Madden
     */
    public void checkSearchButtonOnPdp() {
        assertThat(pDpPage.getSearchIcon().isDisplayed(), equalTo(true));
        logger.info("Search Icon appears on Top right corner on PDP");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Search Icon appears on Top right corner on PDP");
    }

    /**
     * owner : Aditya Nisal
     * hamburger menu gets closed
     * App name: Neovo
     */
    public void clickforHamburgerClose() {
        assertThat(homePage.getLoginButtonOnHamburgerMenuForNeovo().isDisplayed(), equalTo(true));
        homePage.getHamburgerClose().click();
        logger.info("Check that hamburger menu gets closed by tapping outside of the area");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Check that hamburger menu gets closed by tapping outside of the area");
    }

    /**
     * owner : Shashi More
     * Verify Discount price is available on Homepage
     * App name: Neovo-commerce
     */
    public void checkDiscountOnHomePage() {
        if (isPlatformNameAndroid) {
            scrollToText("View More");
        }
        assertThat(homePage.viewMoreButtonOnHomePage.isDisplayed(), equalTo(true));
        homePage.viewMoreButtonOnHomePage.click();
        logger.info("Discount verified on Home page successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Discount verified on HomePage successfully");
    }

    /**
     * owner : Shashi More
     * Verify Discount price is available on PLP Page
     * App name: Neovo-commerce
     */
    public void checkDiscountOnPLPPage() {
        assertThat(pLpPage.getPlpPoster().isDisplayed(), equalTo(true));
        pLpPage.getPlpPoster().click();
        logger.info("Discount verified on PLP page successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Discount verified on PLPPage successfully");
    }

    /**
     * owner : Shashi More
     * Verify Discount price is available on PDPpage
     * App name: Neovo-commerce
     */
    public void checkDiscountOnPDPPage() {
        assertThat(pDpPage.getSelectQuantityTextOnPDPPage().isDisplayed(), equalTo(true));
        logger.info("Discount verified on PDP page successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Discount verified on PDPPage successfully");
    }

    /**
     * owner : Shashi More
     * Verify CTA Button is available on PDPpage
     * App name: Neovo-commerce
     */
    public void checkCTAbuttOnPDPPage() {
        assertThat(pDpPage.getBuyNow().isDisplayed(), equalTo(true));
        assertThat(pDpPage.getCTAbutton().isDisplayed(), equalTo(true));
        logger.info("CTA button verified on PDPPage successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "CTA button verified on PDPPage successfully");
    }

    /**
     * owner : Shashi More
     * Verify search button is available on PDPpage
     * App name: Neovo-commerce
     */
    public void checkSearchButtOnPDPPage() {
        waitElement(pDpPage.getSearchButton(), 6);
        assertThat(pDpPage.getSearchButton().isDisplayed(), equalTo(true));
        logger.info("Search button verified on PDPPage successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Search button verified on PDPPage successfully");
    }

    /**
     * owner : Shashi More
     * The profile page has opened successfully.
     * App name: Neovo-commerce
     */
    public void clickProfilePage() {
        assertThat(homePage.getMyAccount().isDisplayed(), equalTo(true));
        homePage.getMyAccount().click();
        logger.info("Profile page has been opened successfully.");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Profile page has been opened successfully.");
    }

    /**
     * owner : Shashi More
     * The Login page has opened successfully.
     * App name: Neovo-commerce
     */
    public void clickOnLoginButton() {
        if (!isPlatformNameAndroid) {
            scrollDownToIOSElement(myAccount.getLoginButton());
        }
        myAccount.getLoginButton().click();
        logger.info("Login page has been opened successfully.");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Login page has been opened successfully.");
    }

    /**
     * owner : Shashi More
     * Enter valid Email ID Successfully
     * App name: Neovo-commerce
     */
    public void enterEmailID(String emailid) {
        if (appName.equalsIgnoreCase("steve")) {
            assertThat(loginPage.getEmailForSm().isDisplayed(), equalTo(true));
            loginPage.getEmailForSm().sendKeys(emailid);
        } else {
            waitElement(loginPage.getEmailTextField(), 6);
            loginPage.enterEmailID(emailid);
        }
        logger.info("Enter emailID as " + emailid + " successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Enter emailID as " + emailid + " successfully");
    }

    /**
     * owner : Shashi More
     * Enter valid Password Successfully
     * App name: Neovo-commerce
     */
    public void enterPassword(String password) {
        if (appName.equalsIgnoreCase("steve")) {
            assertThat(loginPage.getPasswordForSm().isDisplayed(), equalTo(true));
            loginPage.getPasswordForSm().sendKeys(password);
        } else {
            waitElement(loginPage.getPasswordTextField(), 6);
            loginPage.enterPassword(password);
        }
        logger.info("Enter password successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Enter password successfully");
    }

    /**
     * owner : Shashi More
     * User login successfully
     * App name: Neovo-commerce
     */
    public void clickLogin() {
        if (appName.equalsIgnoreCase("steve")) {
            assertThat(loginPage.getSubmitLogin().isDisplayed(), equalTo(true));
            loginPage.getSubmitLogin().click();
        } else {
            waitFor(1);
            assertThat(loginPage.getLoginButtonOnLoginPage().isDisplayed(), equalTo(true));
            loginPage.getLoginButtonOnLoginPage().click();
        }
        logger.info("Click on Login button successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Click on Login button successfully");

    }

    /**
     * owner : Shashi More
     * Login successfully
     * //TODO: fix method to make it dynamic
     * App name: Neovo-commerce
     */
    public void checkLoginWithValidCreds() {
        if (appName.equals("Steve")) {
            waitElement(myAccount.loginEmailIDForSteve, 6);
            String loginEmail = myAccount.getLoginEmailIDForSteve().getText();
            assertThat(myAccount.loginEmailIDForSteve.getText(), is(equalTo(loginEmail)));
        } else {
            waitElement(myAccount.loginEmailIDfield, 6);
            String loginEmail = myAccount.getLoginEmailIDfield().getText();
            assertThat(myAccount.loginEmailIDfield.getText(), is(equalTo(loginEmail)));
        }
        logger.info("Login successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Login successfully");
    }

    /**
     * owner : Shashi More
     * verify login creds
     * App name: Neovo-commerce
     */
    public void checkEmailIdOnProfilePage(String email) {
        waitElement(myAccount.loginEmailIDfield, 6);
        assertThat(myAccount.loginEmailIDfield.getText(), is(equalTo(email)));
        logger.info("Verified Email ID on Profile Page successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Verified Email ID on Profile Page successfully");
    }

    /**
     * owner : Shashi More
     * Change Password page has been opened successfully.
     * App name: Neovo-commerce
     */
    public void ClickOnChangePasswordButton() {
        if (!isPlatformNameAndroid)
            scrollUpForIos();
        waitElement(homePage.getChangePassword(), 15);
        homePage.getChangePassword().click();
        logger.info("Change Password page has been opened successfully.");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Change Password page has been opened successfully.");
    }

    /**
     * owner : Shashi More
     * Enter valid new password
     * App name: Neovo-commerce
     */
    public void enterNewPasswrod(String newPassword) {
        waitElement(homePage.getNewPassword(), 6);
        homePage.enterNewPassword(newPassword);
        logger.info("Enter New Password successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Enter New Password successfully");
    }

    /**
     * owner : Shashi More
     * Enter valid Confirm Password
     * App name: Neovo-commerce
     */
    public void enterConfirmPassword(String confirmPassword) {
        homePage.enterConfirmPassword(confirmPassword);
        logger.info("Enter confirm Password successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Enter confirm Password successfully");
    }

    /**
     * owner : Shashi More
     * Password successfully changed
     * App name: Neovo-commerce
     */
    public void ChangePasswordOnHomePage() {
        assertThat(homePage.getResetPasswordButton().isDisplayed(), equalTo(true));
        homePage.getResetPasswordButton().click();
        logger.info("Password successfully changed");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Password successfully changed");
    }

    /**
     * owner : Shashi More
     * Location allowed
     * App name: Steve Madden
     */
    public void clickOnPermission() {
        waitElement(homePage.getLocationAllowed(), 6);
        homePage.getLocationAllowed().click();
        logger.info("Location allowed");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Location allowed");
    }

    /**
     * owner : Shashi More
     * On-Boarding Page verified successfully
     * App name: Steve Madden
     */
    public void onBoardingNextButton() {
        waitElement(homePage.getOnBoardingNextButton1(), 6);
        homePage.getOnBoardingNextButton1().click();
        waitElement(homePage.getOnBoardingNextButton2(), 6);
        homePage.getOnBoardingNextButton2().click();
        logger.info("On-Boarding Page verified successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "On-Boarding Page verified successfully");
    }

    /**
     * owner : Shashi More
     * Verify App logo on Home Page
     * App name: Steve Madden
     */
    public void checkAppLogoOnHomePage() {
        assertThat(homePage.getAppLogoOnHomePage().isDisplayed(), equalTo(true));
        logger.info("Verified App Logo on home Page successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Verified App Logo on home Page successfully");
    }

    /**
     * owner : Shashi More
     * Click on Cart button from Homepage
     * App name: Steve Madden
     */
    public void clickOnCartButtonFromHomePage() {
        assertThat(homePage.getCartButtonOnHomePage().isDisplayed(), equalTo(true));
        homePage.getCartButtonOnHomePage().click();
        logger.info("Cart button verified on HomePage successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Cart button verified on HomePage successfully");
    }

    /**
     * owner : Shashi More
     * Verify cart page is open when tapping on Cart button from HomePage
     * App name: Steve Madden
     */
    public void checkCartPage(String cart) {
        assertThat(cartPage.getCartIconOnCartPage().getText(), is(equalTo(cart)));
        cartPage.getCartIconOnCartPage().click();
        logger.info("Cart page has been opened successfully.");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Cart page has been opened successfully.");
    }

    /**
     * owner : Shashi More and Aditya Nisal
     * Click on Hamburger button from Homepage
     * App name: Steve Madden and Neovo
     */
    public void clickOnHamburgerButtonFromHomePage() {
        if (appName.equalsIgnoreCase("Steve")) {
            assertThat(homePage.getHamburgerButtonOnHomePage().isDisplayed(), equalTo(true));
            homePage.getHamburgerButtonOnHomePage().click();
        } else if (appName.equalsIgnoreCase("Sandbox")) {
            waitElement(homePage.getDrawerForSandbox(), 12);
            assertThat(homePage.getDrawerForSandbox().isDisplayed(), equalTo(true));
            homePage.getDrawerForSandbox().click();
        } else {
            assertThat(homePage.getDrawerForNeovo().isDisplayed(), equalTo(true));
            homePage.getDrawerForNeovo().click();
        }
        logger.info("Hamburger menu has been opened successfully.");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Hamburger menu opened successfully");
    }

    /**
     * owner : Shashi More and Aditya Nisal
     * Login page has been opened successfully
     * App name: Steve Madden and Neovo
     */
    public void clickOnLoginButtonFromHamburgerMenu() {
        if (appName.equalsIgnoreCase("Steve")) {
            assertThat(homePage.getHamburgerButtonOnHomePage().isDisplayed(), equalTo(true));
            homePage.getHamburgerButtonOnHomePage().click();
        } else if (appName.equalsIgnoreCase("Sandbox")) {
            assertThat(homePage.getLoginButtonOnHamburgerMenuForSandbox().isDisplayed(), equalTo(true));
            homePage.getLoginButtonOnHamburgerMenuForSandbox().click();
        } else {
            assertThat(homePage.getLoginButtonOnHamburgerMenuForNeovo().isDisplayed(), equalTo(true));
            homePage.getLoginButtonOnHamburgerMenuForNeovo().click();
        }
        logger.info("Login page has been opened successfully.");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Login page has been opened successfully.");
    }

    /**
     * owner : Shashi More
     * Login page has been opened successfully
     * App name: Steve Madden
     */
    public void clickOnProfileButtonFromHamburgerMenu() {
        assertThat(homePage.getProfileButtonOnHamburgerMenu().isDisplayed(), equalTo(true));
        homePage.getProfileButtonOnHamburgerMenu().click();
        logger.info("Login page has been opened successfully.");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Login page has been opened successfully.");
    }

    /**
     * owner : Shashi More and Aditya nisal
     * Logout button is visible on Profile page.
     * App name: Steve Madden and Neovo
     */
    public void checkLogoutButtonOnProfilePage() {
        if (appName.equalsIgnoreCase("Steve")) {
            assertThat(loginPage.getLogoutButtonOnProfilePage().isDisplayed(), equalTo(true));
        } else {
            assertThat(myAccount.getLogoutButtonOnProfilePage().isDisplayed(), equalTo(true));
        }
        logger.info("Logout button is visible on Profile page.");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Logout button is visible on Profile page.");
    }

    /**
     * Continue Button Invokation on webview
     * App name: Steve Madden and Neovo
     */
    public void clickOnContinueButtonOnWebview() {
        waitElement(checkoutPage.getContinueButton(), 30);
        checkoutPage.getContinueButton().click();
        logger.info("Continue button clicked successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Continue button clicked successfully");
    }

    /**
     * COD Button click Invokation on webview
     * App name: Steve Madden and Neovo
     */
    public void clickOnCodButtonOnWebview() {
        waitElement(checkoutPage.getCodButton(), 10);
        checkoutPage.getCodButton().click();
        logger.info("COD button clicked successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "COD button clicked successfully");
    }

    /**
     * Complete Order button Invokation on checkout page
     * App name: Steve Madden and Neovo
     */

    public void clickOnCompleteOrderButtonOnWebview() {
        waitElement(checkoutPage.getCompleteOrderButton(), 10);
        checkoutPage.getCompleteOrderButton().click();
        logger.info("Complete Order button clicked successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Complete Order button clicked successfully");
    }

    /**
     * Verify thank you page
     * App name:Neovo
     */
    public void checkThankyouPage() {
        waitElement(checkoutPage.getTrackOrderButton(), 10);
        waitElement(checkoutPage.getContinueShoppingButton(), 10);
        logger.info("Thank you Page verified successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Thank you Page verified successfully");
    }

    /**
     * Track order button click Invokation on thank you page
     * App name: Neovo
     */
    public void clickOnTrackOrderButtonOnThankyouPage() {
        waitElement(checkoutPage.getTrackOrderButton(), 10);
        checkoutPage.getTrackOrderButton().click();
        logger.info("Track Order button clicked successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Track Order button clicked successfully");
    }

    /**
     * OrderID featching from thank you page
     * App name: Neovo
     */
    public void isOrderIdDisplayed() {
        waitElement(checkoutPage.getOrderIDString(), 5);
        assertThat(checkoutPage.getOrderIDString().getText(), containsString("Order"));
        logger.info("verify OrderID on thank you page successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "verify OrderID on thank you page successfully");
    }

    /**
     * Checking OrderID from thank you page
     * App name:Neovo
     */
    public void checkOrderIdFromMyOrdersPage(String orderID) {
        waitElement(myOrdersPage.getOrderIDFromNewOrderListing(), 30);
        Assert.assertTrue(myOrdersPage.getOrderIDFromNewOrderListing().getText().contains(orderID));
        logger.info("Order ID get verifed Succssfully from Thank you Page");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Order ID get verifed Succssfully from Thank you Page");
    }

    /**
     * Verify cart page
     * App name:Neovo
     */
    public void verifyCartPage() {
        waitElement(cartPage.getApplydiscountOnCartPage(), 10);
        waitElement(cartPage.getPlaceOrderButtonOnCartPage(), 10);
        logger.info("Cart page verified successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Cart page verified successfully");
    }

    /**
     * Invokation of apply discount pop-up on cart page
     * App name:Neovo
     */
    public void clickOnDiscountOnCartPage() {
        waitElement(cartPage.getApplydiscountOnCartPage(), 6);
        cartPage.getApplydiscountOnCartPage().click();
        logger.info("Disount pop-up gets open successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Disount pop-up gets open successfully");
    }

    /**
     * Verify/Check apply discount pop-up gets opened on cart page
     * App name:Neovo
     */
    public void checkDiscountPopupOnCartPage() {
        assertThat(cartPage.getApplyDiscountPopupOnCartPage().isDisplayed(), equalTo(true));
        logger.info("Discount apply pop-up gets opened successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Discount apply pop-up gets opened successfully");
    }

    /**
     * Verify/Check apply discount pop-up gets opened on cart page
     * App name:Neovo
     */
    public void enterDiscountOnPopupOnCartPage(String discount) {
        waitElement(cartPage.getApplyDiscountPopupOnCartPage(), 6);
        if (isPlatformNameAndroid) {
            cartPage.getApplyDiscountPopupOnCartPage().sendKeys(discount);
        } else {
            cartPage.enterDiscount(discount);
        }
        logger.info("Discount entered on discount pop-up successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Discount entered on discount pop-up successfully");
    }

    /**
     * Click on Apply Coupon button from discount pop-up on cart page
     * App name:Neovo
     */
    public void clickOnApplyCouponButtonOnCartPage() {
        assertThat(cartPage.getApplyCouponbutton().isDisplayed(), equalTo(true));
        cartPage.getApplyCouponbutton().click();
        logger.info("Apply coupon button clicked successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Apply coupon button clicked successfully");
    }

    /**
     * Verify/Check discount gets applied on cart page
     * App name:Neovo
     */
    public void checkDiscountAplliedOnCartPage() {
        waitElement(cartPage.getDiscountAppliedOnCartPage(), 10);
        assertThat(cartPage.getDiscountAppliedOnCartPage().isDisplayed(), equalTo(true));
        logger.info("Discount Applied successfully on cart page");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Discount Applied successfully on cart page");
    }

    /**
     * Fetching discount amount from applied discount
     * App name:Neovo
     */
    public String fetchDiscountAmountFromCartPage() {
        String discountAmount[];
        assertThat(cartPage.getDiscountAppliedStatusOnCartPage().isDisplayed(), equalTo(true));
        discountAmount = cartPage.getDiscountAppliedStatusOnCartPage().getText().split("Rs. ");
        logger.info("Fetched discount amount from cart page successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Fetched discount amount from cart page successfully");
        return discountAmount[1];
    }

    /**
     * Fetching Subtotal from cart page
     * App name:Neovo
     */

    public String fetchSubtotalFromCartPage() {
        String subtotal[] = null;
        assertThat(cartPage.getSubtotalAmountOnCartPage().isDisplayed(), equalTo(true));
        subtotal = cartPage.getSubtotalAmountOnCartPage().getText().split("Rs. ");
        logger.info("Fetched subtotal from cart page successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Fetched subtotal from cart page successfully");
        return subtotal[1];
    }

    /**
     * Method verifies More Page is opened successfully
     */
    public void verifyMorePage() {
        waitElement(morePage.getContactUs(), 10);
        logger.info("More page verified Successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "More page verified Successfully");
    }

    /**
     * Click on About us page on More page
     * App name: Neovo-commerce
     */
    public void clickOnAboutUsOnMorePage() {
        if (isPlatformNameAndroid) {
            scrollToText("Fashion");
        } else {
            scrollForIos();
        }
        waitElement(morePage.getAboutUs(), 10);
        morePage.getAboutUs().click();
        logger.info("About Us page opened Successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "About Us page opened Successfully");
    }

    /**
     * Click on More section from hamburger menu
     * App name: Neovo-commerce
     */
    public void ClickMoreOnHamburger() {
        waitElement(homePage.getMoreOnHamburger(), 30);
        homePage.getMoreOnHamburger().click();
        logger.info("More page opened Successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "More page opened Successfully");
    }

    /**
     * Verification of CT-Inbox page From Profile page
     * App name: Neovo-commerce
     */
    public void checkCtInboxFromProfilePage() {
        assertThat(myAccount.getCtInboxButton().isDisplayed(), equalTo(true));
        logger.info("CT Inbox verified Successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "CT Inbox page verified Successfully");
    }

    /**
     * Click on language section popup on more page
     * App name: Neovo-commerce
     */
    public void openlanguagePopupOnMorePage() {
        waitElement(morePage.getLanguageOnMorePage(), 10);
        morePage.getLanguageOnMorePage().click();
        logger.info("Language popup opens sucessfully on more page");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Language popup opens sucessfully on more page");
    }

    /**
     * Verify language popup on more page
     * App name: Neovo-commerce
     */
    public void verifyLanguagePopupOnMorePage() {
        assertThat(morePage.getLanguagePopupTitle().isDisplayed(), equalTo(true));
        assertThat(morePage.getFrenchLanguagePopup().isDisplayed(), equalTo(true));
        assertThat(morePage.getEnglishLanguageOnPopup().isDisplayed(), equalTo(true));
        logger.info("Language popup verified successfully on more page");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Language popup verified successfully on more page");
    }

    /**
     * Verify language popup on more page
     * App name: Neovo-commerce
     */
    public void isDefaultLanguageSelected(String defaultLanguage) {
        assertThat(morePage.getLanguagePopupTitle().isDisplayed(), equalTo(true));
        assertThat(morePage.getEnglishLanguageOnPopup().getText(), equalTo(defaultLanguage));
        logger.info("Default language" + defaultLanguage + " is successfully verified on more page");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Default language" + defaultLanguage + "is successfully verified on more page");
    }

    /**
     * PLP gets opened when invokes from sub-catagory of hamburger
     * App name: Neovo-commerce
     */
    public void clickOnCategoryOnHamburger() {
        waitElement(homePage.getCategoryOnHamburger(), 10);
        homePage.getCategoryOnHamburger().click();
        logger.info("Subcategory invoked successfully on hamburger menu");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Subcategory invoked successfully on hamburger menu");
    }

    /**
     * PLP gets opened when invokes from sub-catagory of hamburger
     * App name: Neovo-commerce
     */
    public void clickSubcategoryOnHamburger() {
        waitElement(homePage.getSubCategoryOnHamburger(), 10);
        homePage.getSubCategoryOnHamburger().click();
        logger.info("PLP gets opened successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "PLP gets opened successfully");
    }

    /**
     * Click on CT app Inbox page from Profile page
     * App name: Neovo-commerce
     */
    public void clickCtInboxFromProfilePage() {
        waitElement(myAccount.getCtInboxButton(), 10);
        myAccount.getCtInboxButton().click();
        logger.info("CT Inbox page opened Successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "CT Inbox page opened Successfully");
    }

    /**
     * Verify CT app Inbox page from More page
     * App name: Neovo-commerce
     */
    public void checkCtInboxFromMorePage() {
        waitElement(morePage.getCtInboxOnMorePage(), 10);
        assertThat(morePage.getCtInboxOnMorePage().isDisplayed(), equalTo(true));
        logger.info("CT Inbox page verified Successfully on More Page");
        ExtentTestManager.getTest().log(LogStatus.PASS, "CT Inbox page verified Successfully on More Page");
    }

    /**
     * Click on CT app Inbox page from More page
     * App name: Neovo-commerce
     */
    public void clickOnCtInboxFromMorePage() {
        waitElement(morePage.getCtInboxOnMorePage(), 10);
        morePage.getCtInboxOnMorePage().click();
        logger.info("CT Inbox page opened Successfully from More Page");
        ExtentTestManager.getTest().log(LogStatus.PASS, "CT Inbox page open Successfully from More Page");
    }

    /**
     * Verify CT app Inbox page
     * App name: Neovo-commerce
     */
    public void verifyCtInboxPage() {
        String logText = "CT Notification page verified successfully";
        waitElement(morePage.getCtInboxTitle(), 10);
        if (isPlatformNameAndroid) {
            assertThat(morePage.getCtInboxTitle().isDisplayed(), equalTo(true));
        } else {
            assertThat(morePage.getCtInboxTitle().isDisplayed(), equalTo(true));
            try {
                assertThat(morePage.getCtInboxicon().isDisplayed(), equalTo(true));
            } catch (Exception e) {
                logText = "CT Notification Title is verified but CT Notification Icon is not configured for store";
            }
        }
        logger.info(logText);
        ExtentTestManager.getTest().log(LogStatus.PASS, logText);
    }

    /**
     * Verify Push Notifications in CT app Inbox page
     * App name: Neovo-commerce
     */
    public void verifyPnReceivedInCtInboxPage() {
        waitElement(morePage.getCtInboxTitle(), 10);
        assertThat(myAccount.getPnTimestampOnCtInboxPage().isDisplayed(), equalTo(true));
        assertThat(myAccount.getPnMessageOnCtInboxPage().isDisplayed(), equalTo(true));
        assertThat(myAccount.getPnTitleOnCtInboxPage().isDisplayed(), equalTo(true));
    }

    /**
     * Verify About Us page present on More page
     * App name: Neovo-commerce
     */
    public void isAboutUsOnMorePageDisplayed() {
        waitElement(morePage.getInAboutUsPage(), 15);
        logger.info("About Us Page verified Successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "About Us Page verified Successfully");
    }

    /**
     * Verify users email and gets verifed on webview checkout
     * App name: Neovo-commerce
     */
    public void verifyRegistedUserOnWebviewCheckout(String email) {
        waitFor(10);
        waitElement(checkoutPage.getUserEmailIdOnWebviewCheckout(), 30);
        assertThat(checkoutPage.getUserEmailIdOnWebviewCheckout().getText().contains(email), equalTo(true));
        logger.info("Newly Registed User have logged in successfully from webview checkout");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Newly Registed User have logged in successfully from webview checkout");
    }

    /**
     * Verify 'No shipping' rate available on checkout. (Free Shipping)
     * App name: Neovo-commerce
     */
    public void checkNoShippingRateOnCheckout(String shippingMethod) {
        waitElement(checkoutPage.getNoshipping(), 30);
        assertThat(checkoutPage.getNoshipping().getText().contains(shippingMethod), equalTo(true));
        logger.info("Free Shipping method verified successfully on Webview Checkout");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Free Shipping method verified successfully on Webview Checkout");
    }

    /**
     * Calculating discount to verify (Applied amount - subtotal)
     * App name:Neovo
     */
    public void comparingDiscountFromCartPage(String discountAmount, String subtotal) {
        assertThat(cartPage.getDiscountedAmountOnCartPage().isDisplayed(), equalTo(true));
        float discountedTotalAmt = Float.parseFloat(subtotal) - Float.parseFloat(discountAmount.substring(0, 5).trim());
        String finalDiscountString = String.format("%.2f", discountedTotalAmt);
        assertThat(cartPage.getDiscountedAmountOnCartPage().getText().contains(finalDiscountString), equalTo(true));
        logger.info("Discount applied successfully with the correct amount");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Discount applied successfully with the correct amount");
    }



    /**
     * owner : Shashi More
     * Edit Profile page has been opened successfully.
     * App name: Steve Madden
     */
    public void clickOnEditProfileButtonOnProfilePage() {
        assertThat(myAccount.getEditProfileButtonOnProfilePage().isDisplayed(), equalTo(true));
        myAccount.getEditProfileButtonOnProfilePage().click();
        logger.info("Edit Profile page has been opened successfully.");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Edit Profile page has been opened successfully.");
    }

    /**
     * owner : Shashi More
     * Verify FirstName, LastName, EmailID fields on Edit Profile page
     * App name: Steve Madden
     */
    public void checkEditProfilePage(String fname, String lname, String email) {
        assertThat(myAccount.getFirstNameOnEditProfilePage().getText(), is(equalTo(fname)));
        assertThat(myAccount.getLastNameOnEditProfilePage().getText(), is(equalTo(lname)));
        assertThat(myAccount.getEmailIdOnEditProfilePage().getText(), is(equalTo(email)));
        logger.info("FirstName, LastName, EmailID fields are successfully verified on Edit Profile page.");
        ExtentTestManager.getTest().log(LogStatus.PASS, "FirstName, LastName, EmailID fields are successfully verified on Edit Profile page..");
    }

    /**
     * owner : Sahil Tripathi
     * This method is used to click on Update profile on edit profile page
     */
    public void clickonUpdateProfile() {
        myAccount.getUpdateProfileOnEditProilePage().click();
        logger.info("Successfully Updated profile on Edit Profile page.");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Successfully Updated profile on Edit Profile page.....");
    }

    /**
     * owner : Shashi More
     * Click on Delete button
     * App name: Steve Madden
     */
    public void clickOnDeleteAccountOnProfilePage() {
        waitFor(1);
        if (isPlatformNameAndroid) {
            scrollToText("Delete Account");
        }
        assertThat(myAccount.getDeleteAccountButtonOnProfilePage().isDisplayed(), equalTo(true));
        myAccount.getDeleteAccountButtonOnProfilePage().click();
        logger.info("Click on Delete button successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Click on Delete button successfully");
    }

    /**
     * owner : Shashi More
     * Click on Delete button
     * App name: Steve Madden
     */
    public void clickOnCofirmDeleteButtonOnProfilePage() {
        waitElement(myAccount.getConfirmDeleteButtonOnProfilePage(), 8);
        assertThat(myAccount.getConfirmDeleteButtonOnProfilePage().isDisplayed(), equalTo(true));
        myAccount.getConfirmDeleteButtonOnProfilePage().click();
        logger.info("Click on Confirm Delete button successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Click on Confirm Delete button successfully");
    }

    /**
     * Method clicks on Sort Button in the PLP page
     * App - Neovo
     */
    public void clickOnSortButtonInPLP() {
        waitElement(pLpPage.getSortButton(), 5);
        pLpPage.clickSortButton();
        logger.info("Sort Button Clicked");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Sort Button Clicked");
    }

    /**
     * owner : Mayur Savdekar
     * Click PDP Redirectable Banner on Homepage
     * App name: Neovo-commerce
     */
    public void clickProductBannerOnHomePage() {
        waitElement(homePage.getHomePoster(), 6);
        homePage.getHomePoster().click();
        logger.info("PDP Redirectable Banner On HomePage Clicked Successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, " PDP Redirectable Banner On HomePage Clicked Successfully");
    }

    /**
     * owner : Mayur Savdekar
     * Click PLP Redirectable Banner on Homepage
     * App name: Neovo-commerce
     */
    public void clickPLPRedirectableBannerOnHomePage() {
        assertThat(homePage.getPlpRedirectableBannerOnHomePage().isDisplayed(), equalTo(true));
        homePage.getPdpRedirectableBannerOnHomePage().click();
        logger.info("PLP Redirectable Banner Clicked Successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, " PLP Redirectable Banner Clicked Successfully");
    }

    /**
     * owner : Mayur Savdekar
     * Click Drawer on Homepage
     * App name: Neovo-commerce
     */
    public void clickDrawerOnHomePage() {
        assertThat(homePage.getDrawerOnHomePage().isDisplayed(), equalTo(true));
        homePage.getDrawerOnHomePage().click();
        logger.info("Drawer on HomePage Clicked successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, " Drawer on HomePage Clicked successfully");
    }

    /**
     * owner : Mayur Savdekar
     * Click Profile tab on Homepage
     * App name: Neovo-commerce
     */
    public void clickProfileTabOnHomePage() {
        assertThat(homePage.getProfileTabOnHomePage().isDisplayed(), equalTo(true));
        homePage.getProfileTabOnHomePage().click();
        logger.info("Profile tab on HomePage Clicked successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, " Profile tab on HomePage Clicked successfully");
    }

    /**
     * owner : Mayur Savdekar
     * Click Category Tab on Homepage
     * App name: Neovo-commerce
     */
    public void clickCategoryTabOnHomePage() {
        assertThat(homePage.getCategoryTabOnHomePage().isDisplayed(), equalTo(true));
        homePage.getCategoryTabOnHomePage().click();
        logger.info("Category tab on HomePage Clicked successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, " Category tab on HomePage Clicked successfully");
    }

    /**
     * owner : Mayur Savdekar
     * Click Cart Tab on Homepage
     * App name: Neovo-commerce
     */
    public void clickCartTabOnHomePage() {
        if (appName.equalsIgnoreCase("neovo")) {
            assertThat(homePage.getCartTabOnHomePage().isDisplayed(), equalTo(true));
            homePage.getCartTabOnHomePage().click();
        } else {
            assertThat(homePage.getCartIconForSandbox().isDisplayed(), equalTo(true));
            homePage.getCartIconForSandbox().click();
        }
        logger.info("Cart tab on HomePage Clicked successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, " Cart tab on HomePage Clicked successfully");
    }

    /**
     * Click More Tab on Homepage
     */
    public void clickMoreTabOnHomePage() {
        if (appName.equalsIgnoreCase("neovo")) {
            assertThat(homePage.getMoreTabOnHomePage().isDisplayed(), equalTo(true));
            homePage.getMoreTabOnHomePage().click();
        } else if (appName.equalsIgnoreCase("sandbox")) {
            assertThat(homePage.getMoreTabOnHomePageSandboxStore().isDisplayed(), equalTo(true));
            homePage.getMoreTabOnHomePageSandboxStore().click();
        }
        logger.info("More tab on HomePage Clicked successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, " More tab on HomePage Clicked successfully");
    }

    /**
     * owner : Mayur Savdekar
     * Verify app logo on header
     * App name: Neovo-commerce
     */
    public void verifyAppLogoOnHeader() {
        waitElement(homePage.getAppLogoOnHeader(), 3);
        homePage.getAppLogoOnHeader().click();
        logger.info("App logo verified successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, " App logo verified successfully");
    }

    /**
     * owner : Mayur Savdekar
     * Enter FirstName on Address Page
     * App name: Neovo-commerce
     */
    public void enterFirstnameOnAddressPage(String firstName) {
        if (appName.equalsIgnoreCase("sandbox")) {
            waitElement(addressPage.getFirstNameOnAddAddressPageSanbox(), 9);
            addressPage.getFirstNameOnAddAddressPageSanbox().sendKeys(firstName);
        } else {
            waitElement(addressPage.getFirstNameTextboxOnAddAddressPage(), 9);
            addressPage.getFirstNameTextboxOnAddAddressPage().sendKeys(firstName);
        }
        logger.info(firstName + " as Firstname Added Successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, firstName + " as Firstname Added Successfully");
    }

    /**
     * owner : Mayur Savdekar
     * Enter LastName on Address Page
     * App name: Neovo-commerce
     */
    public void enterLastnameOnAddressPage(String lastName) {
        if (appName.equalsIgnoreCase("sandbox")) {
            assertThat(addressPage.getLastNameOnAddAddressPageSandbox().isDisplayed(), equalTo(true));
            addressPage.getLastNameOnAddAddressPageSandbox().sendKeys(lastName);
        } else {
            assertThat(addressPage.getLastNameTextboxOnAddAddressPage().isDisplayed(), equalTo(true));
            addressPage.getLastNameTextboxOnAddAddressPage().sendKeys(lastName);
        }
        logger.info(lastName + " as Lastname Added Successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, lastName + " as Lastname Added Successfully");
    }


    // This method is used to add the company name in Address page
    public void enterCompanyNameOnAddressPage(String companyname) {
        assertThat(addressPage.getCompanyTextboxOnAddAddressPage().isDisplayed(), equalTo(true));
        addressPage.enterCompanyName(companyname);
        logger.info(companyname + " as Company Name added successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, companyname + " as Company Name added successfully");
    }

    /**
     * owner : Mayur Savdekar
     * Click Edit Address on Address Page
     * App name: Neovo-commerce
     */
    public void clickEditAddressOnAddressPage() {
        waitElement(addressPage.getEditAddressOnAddressPage(), 6);
        addressPage.getEditAddressOnAddressPage().click();
        logger.info(" Edit address clicked Successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, " Edit address clicked Successfully");
    }

    /**
     * owner : Mayur Savdekar
     * Enter Contact Number on Address Page
     * App name: Neovo-commerce
     */
    public void enterContactNumberOnAddressPage(String number) {
        assertThat(addressPage.getContactNumberTextboxOnAddAddressPage().isDisplayed(), equalTo(true));
        addressPage.enterContactNumber(number);
        logger.info(number + " Contact Added Successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, number + " Contact Added Successfully");
        logger.info(number + " as Contact number Added Successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, number + " as Contact number Added Successfully");
    }

    /**
     * owner : Mayur Savdekar
     * Click Save Button on Address Page
     * App name: Neovo-commerce
     */
    public void clickSaveButtonOnAddAddressPage() {
        waitFor(.7);
        waitElement(addressPage.getSaveButtonOnAddAddressPage(), 7);
        assertThat(addressPage.getSaveButtonOnAddAddressPage().isDisplayed(), equalTo(true));
        addressPage.getSaveButtonOnAddAddressPage().click();
        logger.info(" Save address clicked Successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, " Save address clicked Successfully");
    }

    /**
     * owner : Mayur Savdekar
     * Click Add New Address On Address Page
     * App name: Neovo-commerce
     */
    public void clickAddNewAddressButton() {
        waitElement(addressPage.getAddNewAddressButton(), 9);
        addressPage.getAddNewAddressButton().click();
        logger.info(" Add New Address Clicked Successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Add New Address Clicked Successfully");
    }

    /**
     * owner : Mayur Savdekar
     * Enter Address Line 1 on Address Page
     * App name: Neovo-commerce
     */
    public void enterAddressLine1OnAddressPage(String address1) {
        assertThat(addressPage.getAddressLine1TextboxOnAddAddressPage().isDisplayed(), equalTo(true));
        addressPage.enterAddressLine1(address1);
        logger.info(address1 + " as Address Line1 Added Successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, address1 + " as Address Line1 Added Successfully");
    }

    /**
     * owner : Mayur Savdekar
     * Enter Address Line 2 on Address Page
     * App name: Neovo-commerce
     */
    public void enterAddressLine2OnAddressPage(String address2) {
        assertThat(addressPage.getAddressLine2TextboxOnAddAddressPage().isDisplayed(), equalTo(true));
        addressPage.enterAddressLine2(address2);
        logger.info(address2 + " as Address Line2 Added Successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, address2 + " as Address Line2 Added Successfully");
    }

    /**
     * owner : Mayur Savdekar
     * Enter State on Address Page
     * App name: Neovo-commerce
     */
    public void enterStateOnAddressPage(String state) {
        assertThat(addressPage.getStateTextboxOnAddAddressPage().isDisplayed(), equalTo(true));
        addressPage.enterState(state);
        logger.info(state + " as State Added Successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, state + " as State Added Successfully");
    }

    /**
     * owner : Mayur Savdekar
     * Enter City on Address Page
     * App name: Neovo-commerce
     */
    public void enterCityOnAddressPage(String city) {
        assertThat(addressPage.getCityTextboxOnAddAddressPage().isDisplayed(), equalTo(true));
        addressPage.enterCity(city);
        logger.info(city + " as City Added Successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, city + " as City Added Successfully");

    }

    /**
     * owner : Mayur Savdekar
     * Enter Zipcode on Address Page
     * App name: Neovo-commerce
     */
    public void enterZipcodeOnAddressPage(String zipcode) {
        assertThat(addressPage.getZipcodeTextboxOnAddAddressPage().isDisplayed(), equalTo(true));
        addressPage.enterZipCode(zipcode);
        logger.info(zipcode + " as Zipcode Added Successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, zipcode + " as Zipcode Added Successfully");
    }

    /**
     * owner : Mayur Savdekar
     * Clear contact Number textbox on Address Page
     * App name: Neovo-commerce
     */
    public void clearContactNumberTextboxOnAddAddressPage() {
        assertThat(addressPage.getContactNumberTextboxOnAddAddressPage().isDisplayed(), equalTo(true));
        addressPage.getContactNumberTextboxOnAddAddressPage().clear();
        logger.info("Contact Number Cleared Successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Contact Number Cleared Successfully");
    }

    /**
     * owner : Mayur Savdekar
     * Click Login and Register on Drawer
     * App name: Neovo-commerce
     */
    public void clickloginAndRegisterOnDrawer() {
        waitElement(drawerPage.getLoginAndRegisterOnDrawer(), 3);
        drawerPage.getLoginAndRegisterOnDrawer().click();
        logger.info("Login and Register on Drawer Clicked successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Login and Register on Drawer Clicked successfully");
    }

    /**
     * owner : Mayur Savdekar
     * Verify Login Page
     * App name: Neovo-commerce
     */
    public void verifyLoginPage() {
        waitElement(loginPage.getLoginTextOnLoginPage(), 30);
        assertThat(loginPage.getLoginTextOnLoginPage().isDisplayed(), equalTo(true));
        assertThat(loginPage.getForgotPassword().isDisplayed(), equalTo(true));
        assertThat(loginPage.getLoginButtonOnLoginPage().isDisplayed(), equalTo(true));
        logger.info("Login page Verified successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Login page Display Verified successfully");

    }

    /**
     * owner : Mayur Savdekar
     * Click register button on Login Page
     * App name: Neovo-commerce
     */
    public void clickRegisterButton() {
        waitElement(loginPage.getRegisterButton(), 9);
        loginPage.getRegisterButton().click();
        logger.info("Register Button  Clicked successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Register Button  Clicked successfully");
    }

    /**
     * owner : Mayur Savdekar
     * Click AddToCart Button On PDP
     * App name: Neovo-commerce
     */
    public void clickAddToCartOnPDP() {
        assertThat(pDpPage.getAddToCartButtonOnPDP().isDisplayed(), equalTo(true));
        pDpPage.getAddToCartButtonOnPDP().click();
        logger.info("AddToCartOnPDP Clicked successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "AddToCartOnPDP Clicked successfully");

    }

    /**
     * Click Go to cart Button On PDP
     * App name: Sandbox
     */
    public void clickOnGoToCart() {
        waitFor(2);
        waitElement(pDpPage.getGoToCartPageFromPdp(), 10);
        pDpPage.getGoToCartPageFromPdp().click();
        logger.info("Go to cart page invoked successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Go to cart page invoked successfully");
    }

    /**
     * owner : Mayur Savdekar
     * Click Confirm Button After add to cart
     * App name: Neovo-commerce
     */
    public void clickConfirmButtonOnConfirmationPopUPAfterAddToCartClick() {
        waitElement(pDpPage.getConfirmButtonAfterAddToCartClick(), 6);
        pDpPage.getConfirmButtonAfterAddToCartClick().click();
        logger.info("ConfirmButtonOnConfirmationPopUPAfterAddToCartClick Clicked Successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "ConfirmButtonOnConfirmationPopUPAfterAddToCartClick Clicked Successfully");
    }

    /**
     * owner : Mayur Savdekar
     * Click AddTofavorite On PDP Page
     * App name: Neovo-commerce
     */
    public void clickAddToFavoriteIconOnPDPPage() {
        waitFor(1.5);
        assertThat(pDpPage.getAddToFavoriteIconOnPDPPage().isDisplayed(), equalTo(true));
        pDpPage.getAddToFavoriteIconOnPDPPage().click();
        logger.info(" AddToFavoriteIconOnPDPPage Clicked Successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "AddToFavoriteIconOnPDPPage Clicked Successfully");
    }

    /**
     * owner : Mayur Savdekar
     * Click Back Arrow On Native Page
     * App name: Neovo-commerce
     */
    public void clickBackArrowOnNativeApp() {
        waitElement(pDpPage.getBackArrowOnNativeApp(), 6);
        assertThat(pDpPage.getBackArrowOnNativeApp().isDisplayed(), equalTo(true));
        pDpPage.getBackArrowOnNativeApp().click();
        logger.info(" Back arrow on native app Clicked Successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Back arrow on native app Clicked Successfully");
    }

    /**
     * owner : Mayur Savdekar
     * Click XS Variant on PDP
     * App name: Neovo-commerce
     */
    public void clickXSVariantOnPDP() {
        assertThat(pDpPage.getXSVariantOnPDP().isDisplayed(), equalTo(true));
        pDpPage.getXSVariantOnPDP().click();
        logger.info(" XS Variant on PDP Clicked Successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "XS Variant on PDP Clicked Successfully");
    }

    /**
     * owner : Mayur Savdekar
     * Click S Variant on PDP
     * App name: Neovo-commerce
     */
    public void clickSVariantOnPDP() {
        assertThat(pDpPage.getSVariantOnPDP().isDisplayed(), equalTo(true));
        pDpPage.getSVariantOnPDP().click();
        logger.info(" S Variant on PDP Clicked Successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "S Variant on PDP Clicked Successfully");
    }

    /**
     * owner : Mayur Savdekar
     * Click M Variant on PDP
     * App name: Neovo-commerce
     */
    public void clickMVariantOnPDP() {
        assertThat(pDpPage.getMVariantOnPDP().isDisplayed(), equalTo(true));
        pDpPage.getMVariantOnPDP().click();
        logger.info(" M Variant on PDP Clicked Successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "M Variant on PDP Clicked Successfully");
    }

    /**
     * owner : Mayur Savdekar
     * Click Related Products on PDP
     * App name: Neovo-commerce
     */
    public void clickRelatedProductOnPDP() {
        waitElement(pDpPage.getRelatedProductOnPDP(), 15);
        pDpPage.getRelatedProductOnPDP().click();
        logger.info(" Related Product on PDP Clicked Successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Related Product on PDP Clicked Successfully");
    }

    /**
     * owner : Mayur Savdekar
     * Click Login Or Logout Button On Profile Page
     * App name: Neovo-commerce
     */
    public void clickLoginOrLogoutButtonOnProfilePage() {
        waitElement(profilePage.getLoginOrLogoutButtonOnProfilePage(), 3);
        profilePage.getLoginOrLogoutButtonOnProfilePage().click();
        logger.info("Login or Logout Button Clicked successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Login or Logout Button Clicked successfully");

    }


    /**
     * owner : Mayur Savdekar
     * Click Favorite Feature On Profile Page
     * App name: Neovo-commerce
     */
    public void clickFavoriteFeatureOnProfilePage() {
        waitElement(profilePage.getFavoriteFeatureOnProfilePage(), 5);
        profilePage.getFavoriteFeatureOnProfilePage().click();
        logger.info("Favorite feature on profile page clicked successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Favorite feature on profile page clicked successfully");

    }

    /**
     * owner : Mayur Savdekar
     * Verify Username And Email On Profile Page
     * App name: Neovo-commerce
     */
    public void verifyUsernameAndEmailOnProfilePage(String username, String email) {
        waitElement(profilePage.getUserNameOnProfilePage(), 3);
        logger.info("Username and Email Verified successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Username and Email Verified successfully");
    }

    /**
     * owner : Mayur Savdekar
     * Click Address Feature On Profile Page
     * App name: Neovo-commerce
     */
    public void clickAddressFeatureOnProfilePage() {
        waitElement(profilePage.getAddressFeatureOnProfilePage(), 7);
        assertThat(profilePage.getAddressFeatureOnProfilePage().isDisplayed(), equalTo(true));
        profilePage.getAddressFeatureOnProfilePage().click();
        logger.info("Address feature on profile page clicked successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Address feature on profile page clicked successfully");
    }

    /**
     * owner : Mayur Savdekar
     * Verify First Product Title On cart Page
     * App name: Neovo-commerce
     */
    public void verifyFirstProductTitleOncartPage(String productName) {
        waitElement(cartPage.getFirstProductTitleOnCartPaga(), 3);
        Assert.assertEquals(cartPage.getFirstProductTitleOnCartPaga().getText(), productName);
        logger.info("Product Name on Cart Verified successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Product Name on Cart Verified successfully");
    }

    /**
     * owner : Mayur Savdekar
     * verify Product On Favorite Page
     * App name: Neovo-commerce
     */
    public void verifyProductOnFavoritePage(String productName) {
        waitElement(favoritePage.getFirstProductTextOnFavoritePage(), 6);
        Assert.assertEquals(favoritePage.getFirstProductTextOnFavoritePage().getText(), productName);
        logger.info("Product Name on Favorite Page Verified successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Product Name on Favorite Page Verified successfully");
    }

    /**
     * owner : Mayur Savdekar
     * verify Text Present In Address
     * App name: Neovo-commerce
     */
    public void verifyTextPresentInAddress(String text) {
        waitElement(addressPage.getFullAddressOnAddressPage(), 3);
        assertThat(addressPage.getFullAddressOnAddressPage().getText().contains(text), equalTo(true));
        logger.info(text + " is Present In Address And Address Verified Successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, text + " is Present In Address And Address Verified Successfully");
    }

    /**
     * owner : Mayur Savdekar
     * Verify Product Title On PDP
     * App name: Neovo-commerce
     */
    public void verifyProductTitleOnPDP(String title) {
        waitElement(pDpPage.getProductTitleOnPdp(), 3);
        assertThat(pDpPage.getProductTitleOnPdp().getText(), containsString(title));
        logger.info(title + " is Present on PDP Page");
        ExtentTestManager.getTest().log(LogStatus.PASS, title + " product is Present on PDP Page");
    }

    public void verifyNoUserIsLoggedInOnProfilePage() {
        if (isPlatformNameAndroid) {
            assertThat(profilePage.getAddressFeatureOnProfilePage().isEnabled(), equalTo(false));
            assertThat(profilePage.getMyOrdersFeatureOnProfilePage().isEnabled(), equalTo(false));
        }
        Assert.assertEquals(profilePage.getLoginOrLogoutButtonOnProfilePage().getText().toUpperCase(), "LOGIN");
        if (isPlatformNameAndroid) {
            logger.info("Login button is present on profile page also My Orders and My Address are disabled hence user is not logged into the app verified successfully ");
            ExtentTestManager.getTest().log(LogStatus.PASS, "Login button is present on profile page also My Orders and My Address are disabled hence user is not logged into the app verified successfully ");
        } else {
            logger.info("Login button is present on profile page verified successfully ");
            ExtentTestManager.getTest().log(LogStatus.PASS, "Login button is present on profile page verified successfully");
        }
    }

    /**
     * Method selects the sorting option passed.
     * App - Neovo
     *
     * @param sortingOptionsEnum
     */
    public void selectSortingType(SortingOptionsEnum sortingOptionsEnum) {
        switch (sortingOptionsEnum) {
            case PRICE_HIGH_TO_LOW:
                waitElement(pLpPage.getPriceHighToLowSorting(), 5);
                pLpPage.getPriceHighToLowSorting().click();
                break;
            case PRICE_LOW_TO_HIGH:
                waitElement(pLpPage.getPriceLowToHighSorting(), 5);
                pLpPage.getPriceLowToHighSorting().click();
                break;
            case ALPHABETICALLY_A_TO_Z:
                waitElement(pLpPage.getAlphabeticallyAToZSorting(), 5);
                pLpPage.getAlphabeticallyAToZSorting().click();
                break;
            case ALPHABETICALLY_Z_TO_A:
                waitElement(pLpPage.getAlphabeticallyZToASorting(), 5);
                pLpPage.getAlphabeticallyZToASorting().click();
                break;
            case BY_DATE_NEW_TO_OLD:
                waitElement(pLpPage.getDateNewToOldSorting(), 5);
                pLpPage.getDateNewToOldSorting().click();
                break;
            case BY_DATE_OLD_TO_NEW:
                waitElement(pLpPage.getDateOldToNewSorting(), 5);
                pLpPage.getDateOldToNewSorting().click();
                break;
            case BESTSELLING:
                waitElement(pLpPage.getBestsellingSorting(), 5);
                pLpPage.getBestsellingSorting().click();
                break;
            case MANUALLY:
                waitElement(pLpPage.getManuallySorting(), 5);
                pLpPage.getManuallySorting().click();
                break;
        }
        logger.info(sortingOptionsEnum + " option selected");
        ExtentTestManager.getTest().log(LogStatus.PASS, sortingOptionsEnum + " option selected");
    }

    /**
     * Method verifies the Toast message for sorting applied on PLP page
     * App - Neovo
     *
     * @param sortingOptionsEnum
     */
    public void verifySortingToastMessage(SortingOptionsEnum sortingOptionsEnum) {
        if (ReadConfig.getMobilePlatformName().equalsIgnoreCase("Android")) {
            waitElementTillPresenceOfElementLocated(ANDROID_TOAST_MESSAGE_XPATH, 10);
            MobileElement toastMessage = mobileDriver.findElement((By.xpath(ANDROID_TOAST_MESSAGE_XPATH)));
            switch (sortingOptionsEnum) {
                case PRICE_HIGH_TO_LOW:
                    assertThat(toastMessage.getText(), is(equalTo("Your products has been sorted by  Price - High-Low")));
                    break;
                case PRICE_LOW_TO_HIGH:
                    assertThat(toastMessage.getText(), is(equalTo("Your products has been sorted by  Price - Low-High")));
                    break;
                case ALPHABETICALLY_A_TO_Z:
                    assertThat(toastMessage.getText(), is(equalTo("Your products has been sorted by  Alphabetically A to Z")));
                    break;
                case ALPHABETICALLY_Z_TO_A:
                    assertThat(toastMessage.getText(), is(equalTo("Your products has been sorted by  Alphabetically Z to A")));
                    break;
                case BY_DATE_NEW_TO_OLD:
                    assertThat(toastMessage.getText(), is(equalTo("Your products has been sorted by  By date: Newest to Oldest")));
                    break;
                case BY_DATE_OLD_TO_NEW:
                    assertThat(toastMessage.getText(), is(equalTo("Your products has been sorted by  By date: Oldest to Newest")));
                    break;
                case BESTSELLING:
                    assertThat(toastMessage.getText(), is(equalTo("Your products has been sorted by  By bestselling")));
                    break;
                case MANUALLY:
                    assertThat(toastMessage.getText(), is(equalTo("Your products has been sorted by  Manually")));
                    break;
            }
        } else {
            String commonToastXpathPreString = "//XCUIElementTypeStaticText[@name='Your products have been sorted by ";
            String commonToastXpathPostString = "']";
            switch (sortingOptionsEnum) {
                case PRICE_HIGH_TO_LOW:
                    waitElementTillPresenceOfElementLocated(commonToastXpathPreString + "\"Price - High-Low\"" + commonToastXpathPostString, 10);
                    break;
                case PRICE_LOW_TO_HIGH:
                    waitElementTillPresenceOfElementLocated(commonToastXpathPreString + "\"Price - Low-High\"" + commonToastXpathPostString, 10);
                    break;
                case ALPHABETICALLY_A_TO_Z:
                    waitElementTillPresenceOfElementLocated(commonToastXpathPreString + "\"Alphabetically A to Z\"" + commonToastXpathPostString, 10);
                    break;
                case ALPHABETICALLY_Z_TO_A:
                    waitElementTillPresenceOfElementLocated(commonToastXpathPreString + "\"Alphabetically Z to A\"" + commonToastXpathPostString, 10);
                    break;
                case BY_DATE_NEW_TO_OLD:
                    waitElementTillPresenceOfElementLocated(commonToastXpathPreString + "\"By date: Newest to Oldest\"" + commonToastXpathPostString, 10);
                    break;
                case BY_DATE_OLD_TO_NEW:
                    waitElementTillPresenceOfElementLocated(commonToastXpathPreString + "\"By date: Oldest to Newest\"" + commonToastXpathPostString, 10);
                    break;
                case BESTSELLING:
                    waitElementTillPresenceOfElementLocated(commonToastXpathPreString + "\"By bestselling\"" + commonToastXpathPostString, 10);
                    break;
                case MANUALLY:
                    waitElementTillPresenceOfElementLocated(commonToastXpathPreString + "\"Manually\"" + commonToastXpathPostString, 10);
                    break;
            }

        }
        logger.info("Toast message successfully verified");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Toast message successfully verified");
    }

    /**
     * Method waits for element to be located for the xpath passed.
     *
     * @param xpath
     * @param timer
     */
    public void waitElementTillPresenceOfElementLocated(String xpath, int timer) {
        WebDriverWait wait = new WebDriverWait(mobileDriver, timer);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
    }

    public void waitElementRefreshedPresenceOfElementLocated(String xpath, int timer) {
        WebDriverWait wait = new WebDriverWait(mobileDriver, timer);
        wait.until(ExpectedConditions.refreshed(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath))));
    }

    /**
     * Method verifies the sorting for the first 4 products with respect to price
     *
     * @param sortingOptionsEnum
     */
    public void verifyPriceSorting(SortingOptionsEnum sortingOptionsEnum) {
        List<MobileElement> listOfPrice;
        if (ReadConfig.getMobilePlatformName().equalsIgnoreCase("Android"))
            listOfPrice = mobileDriver.findElements(By.xpath(ANDROID_PRODUCT_PRICE_LIST_XPATH));
        else
            listOfPrice = mobileDriver.findElements(By.xpath(IOS_PRODUCT_PRICE_LIST_XPATH));
        String[] previousProductPriceString = listOfPrice.get(0).getText().split(" ");
        float previousProductPrice = Float.parseFloat(previousProductPriceString[1]);
        for (int i = 1; i < listOfPrice.size(); i++) {
            String[] currentProductPriceString = listOfPrice.get(i).getText().split(" ");
            float currentProductPrice = Float.parseFloat(currentProductPriceString[1]);
            switch (sortingOptionsEnum) {
                case PRICE_HIGH_TO_LOW:
                    assertThat(currentProductPrice < previousProductPrice, equalTo(true));
                    break;
                case PRICE_LOW_TO_HIGH:
                    assertThat(currentProductPrice > previousProductPrice, equalTo(true));
                    break;
                default:
                    logger.error("Incorrect Parameter passed.");
                    throw new RuntimeException("Incorrect Parameter Passed");
            }
            previousProductPrice = currentProductPrice;
        }
        logger.info("Sorting done successfully. Checked the first 4 products");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Sorting done successfully. Checked the first 4 products");
    }

    /**
     * Method verifies the sorting for the first 4 products with respect to product title
     *
     * @param sortingOptionsEnum
     */
    public void verifyTitleSorting(SortingOptionsEnum sortingOptionsEnum) {
        List<MobileElement> listOfPrice;
        if (ReadConfig.getMobilePlatformName().equalsIgnoreCase("Android"))
            listOfPrice = mobileDriver.findElements(By.xpath(ANDROID_PRODUCT_TITLE_LIST_XPATH));
        else
            listOfPrice = mobileDriver.findElements(By.xpath(IOS_PRODUCT_TITLE_LIST_XPATH));
        String previousProductTitleString = listOfPrice.get(0).getText();
        for (int i = 1; i < listOfPrice.size(); i++) {
            String currentProductTitleString = listOfPrice.get(i).getText();
            switch (sortingOptionsEnum) {
                case ALPHABETICALLY_A_TO_Z:
                    assertThat(previousProductTitleString.compareTo(currentProductTitleString) < 0, equalTo(true));
                    break;
                case ALPHABETICALLY_Z_TO_A:
                    assertThat(currentProductTitleString.compareTo(previousProductTitleString) < 0, equalTo(true));
                    break;
                default:
                    logger.error("Incorrect Parameter passed.");
                    throw new RuntimeException("Incorrect Parameter Passed");
            }
            previousProductTitleString = currentProductTitleString;
        }
        logger.info("Sorting done successfully. Checked the first 4 products");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Sorting done successfully. Checked the first 4 products");
    }

    /**
     * Verifies if the elements in the home page are loaded
     */
    public void verifyHomePageElementsLoadingTime() {
        assertThat(homePage.getCart().isDisplayed(), equalTo(true));
        assertThat(homePage.getDrawerForNeovo().isDisplayed(), equalTo(true));
        assertThat(homePage.getProfileTabOnHomePage().isDisplayed(), equalTo(true));
        assertThat(homePage.getHomePoster().isDisplayed(), equalTo(true));
        logger.info("Home elements Successfully loaded");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Home elements Successfully loaded");
    }

    /**
     * Methods clicks on the like button in the PLP page for the product title string passed
     *
     * @param productTitle
     */
    public void addProductToFavoritesOnPLP(String productTitle) {
        String likeButtonXPath;
        String productXPath;
        String logText = " product added to favorites";
        if (ReadConfig.getMobilePlatformName().equalsIgnoreCase("Android")) {
            productXPath = "//android.widget.TextView[@text = '" + productTitle + "']";
            likeButtonXPath = "(" + productXPath + "/ancestor::android.widget.LinearLayout/preceding-sibling::android.widget.RelativeLayout//android.widget.ImageView[contains(@resource-id, 'product_action_item_imgView')])[1]";
        } else {
            productXPath = "//XCUIElementTypeStaticText[@name='" + productTitle + "']/preceding-sibling::XCUIElementTypeOther/XCUIElementTypeButton";
            likeButtonXPath = productXPath + "[@name='like icon']";
        }
        waitElementTillPresenceOfElementLocated(productXPath, 5);
        try {
            MobileElement likeButtonElement = mobileDriver.findElement(By.xpath(likeButtonXPath));
            likeButtonElement.click();
        } catch (Exception e) {
            //Product already added to favorites
            logText = " product already added to favorites. No click action performed";
        }
        logger.info(productTitle + logText);
        ExtentTestManager.getTest().log(LogStatus.PASS, productTitle + logText);
    }

    /**
     * Method clicks on back button at the top left of the page on PLP page. Works in multiple pages.
     */
    public void clickBackButtonIconOnTopLeft() {
        waitElement(pLpPage.getBackButtonIcon(), 5);
        assertThat(pLpPage.getBackButtonIcon().isDisplayed(), equalTo(true));
        pLpPage.getBackButtonIcon().click();
        logger.info("Clicked on back button in the top left of the page");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Clicked on back button in the top left of the page");
    }

    /**
     * Method verifies if the product title exists in the Favorites Page
     *
     * @param productTitle
     */
    public void verifyProductTitleOnFavoritesPage(String productTitle) {
        String productTitleXPath;
        if (ReadConfig.getMobilePlatformName().equalsIgnoreCase("Android"))
            productTitleXPath = "//android.widget.TextView[@text='" + productTitle + "']";
        else
            productTitleXPath = "//XCUIElementTypeStaticText[@name='" + productTitle + "']";
        MobileElement productTileElement = mobileDriver.findElement(By.xpath(productTitleXPath));
        assertThat(productTileElement.getText(), is(equalTo(productTitle)));
        logger.info(productTitle + " product exists in favorites Page.");
        ExtentTestManager.getTest().log(LogStatus.PASS, productTitle + " product exists in favorites Page.");
    }

    /**
     * Method verifies if the Favorite page opened successfully
     */
    public void isFavoritePageLoaded() {
        waitElement(favoritePage.getFavoriteTextOnFavoritePage(), 5);
        logger.info("Favorite Page opened successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Favorite Page opened successfully");
    }

    /**
     * Method clicks on back button at the top left of the page. Works in multiple pages.
     */
    public void clickBackButtonIconOnCollectionPage() {
        assertThat(collectionPage.getBackButtonIcon().isDisplayed(), equalTo(true));
        collectionPage.getBackButtonIcon().click();
        logger.info("Clicked on back button in the top left of the page");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Clicked on back button in the top left of the page");
    }

    /**
     * This Method Click on Hamberger Menu for Steve Madden
     */
    public void clickOnHambergerMenuforSM() {
        assertThat(homePage.getHamburgerMenuForSM().isDisplayed(), equalTo(true));
        homePage.getHamburgerMenuForSM().click();
        logger.info("Click on Hamberger Menu for Steve Madden successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Click on Hamberger Menu for Steve Madden successfully");
    }

    /**
     * This Method Click on SignIn for Steve Madden
     */
    public void clickOnSignIn() {
        waitElement(loginPage.getSignNow(), 16);
        loginPage.getSignNow().click();
        logger.info("click on SignIn successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "click on SignIn successfully");
    }

    /**
     * This Method Click on Join now for Steve Madden
     */
    public void clickOnJoinIn() {
        waitElement(loginPage.getJoinNow(), 16);
        loginPage.getJoinNow().click();
        logger.info("click on JoinNow successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "click on JoinNow successfully");
    }

    /**
     * This Method Click on More button for Steve Madden
     */
    public void clickOnMoreBtn() {
        assertThat(loginPage.getMoreBtn().isDisplayed(), equalTo(true));
        loginPage.getMoreBtn().click();
        logger.info("click on More Btn successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "click on More Btn successfully");
    }


    /**
     * This Method Click on Login button for Steve Madden
     */
    public void clickOnLoginBtnOnHambergerMenu() {
        waitElement(loginPage.getLoginPageText(), 16);
        assertThat(loginPage.getLoginPageText().isDisplayed(), equalTo(true));
        loginPage.getLoginPageText().click();
        logger.info("click on Login Btn In Hamberger Menu successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "click on Login Btn In Hamberger Menu successfully");
    }

    /**
     * This Method Click on Logout button for Steve Madden
     */
    public void clickOnLogoutBtnOnHambergerMenu() {
        assertThat(loginPage.getLogoutBtnInHamMenu().isDisplayed(), equalTo(true));
        loginPage.getLogoutBtnInHamMenu().click();
        logger.info("click on Logout Btn In Hamberger Menu successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "click on Logout Btn In Hamberger Menu successfully");
    }

    /**
     * This Method verifies Login Page for Steve Madden
     */
    public void verifyLoginPagePageforSM() {
        waitElement(loginPage.getLoginPageText(), 16);
        assertThat(loginPage.getLoginPageText().isDisplayed(), equalTo(true));
        assertThat(loginPage.getSteveMadden().isDisplayed(), equalTo(true));
        assertThat(loginPage.getEmailForSm().isDisplayed(), equalTo(true));
        assertThat(loginPage.getPasswordForSm().isDisplayed(), equalTo(true));
        assertThat(loginPage.getSubmitLogin().isDisplayed(), equalTo(true));
        assertThat(loginPage.getRegisterButton().isDisplayed(), equalTo(true));
        logger.info("Steve Madden Login page verify successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Steve Madden Login page verify successfully");
    }

    /**
     * This Method verifies Logout btn text for Steve Madden
     */
    public void verifyLogoutBtnforSm() {
        waitElement(loginPage.getLogoutBtnInHamMenu(), 16);
        assertThat(loginPage.getLogoutBtnInHamMenu().isDisplayed(), equalTo(true));
        logger.info("Logout button verify successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Logout button verify successfully");
    }

    /**
     * This Method verifies Login btn text for Steve Madden
     */
    public void verifyLoginBtnforSm() {
        waitElement(loginPage.getLoginPageText(), 10);
        assertThat(loginPage.getLoginPageText().isDisplayed(), equalTo(true));
        logger.info("Login button verify successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Login button verify successfully");
    }

    /**
     * This Method click on ok button while displaying popUp page
     */
    public void clickOnOK() {
        waitElement(loginPage.getOkPopup(), 16);
        assertThat(loginPage.getOkPopup().isDisplayed(), equalTo(true));
        loginPage.getOkPopup().click();
        logger.info("click on ok successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "click on ok successfully");
    }

    /**
     * This Method click on Contact Us button for SM
     */
    public void clickOnContactUs() {
        waitElement(morePage.getContactUsBtn(), 15);
        morePage.getContactUsBtn().click();
        logger.info("click on ContactUsBtn successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "click on ContactUsBtn successfully");
    }

    /**
     * This Method verifies Store Contact Deatils Page on contact page for Steve Madden
     */
    public void verifyStoreContactDeatilsPage() {
        waitElement(loginPage.getContactUsPageText(), 16);
        assertThat(loginPage.getContactUsPageText().isDisplayed(), equalTo(true));
        assertThat(loginPage.getCallUs().isDisplayed(), equalTo(true));
        assertThat(loginPage.getCallSM().isDisplayed(), equalTo(true));
        assertThat(loginPage.getSmEmail().getText(), equalTo("info@stevemaddendirect.com"));
        assertThat(loginPage.getReachUs().isDisplayed(), equalTo(true));
        assertThat(loginPage.getSmLtd().isDisplayed(), equalTo(true));
        logger.info("verify Store Contact Deatils page for SM successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "verify Store Contact Deatils page for SM successfully");
    }

    /**
     * This method enter text inside search field for platform
     */
    public void clickOnSearchIcon() {
        waitElement(homePage.getSearchProductsText(), 10);
        homePage.getSearchProductsText().click();
        logger.info("click on search icon successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "click on search icon successfully");
    }

    /**
     * This method enter text inside search field for platform
     */
    public void verifySearchRelatedPlp(String searctText) {
        waitElement(pLpPage.getPlp1stProduct(), 20);
        assertThat(pLpPage.getPlp1stProduct().getText(), containsString(searctText));
        logger.info("Search Related Text verify on Plp listed product successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Search Related Text verify on Plp listed product successfully");
    }

    /**
     * Method clicks on filter Button in the PLP page
     */
    public void clickOnFilterButtonInPLP() {
        waitElement(pLpPage.getFilterButton(), 15);
        pLpPage.getFilterButton().click();
        logger.info("Filter Button Clicked successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Sort Button Clicked successfully");
    }

    /**
     * This Method verifies Filter Options on plp page
     */
    public void verifyFilterOptions() {
        waitElement(pLpPage.getFilterPageText(), 10);
        assertThat(pLpPage.getAvailability().isDisplayed(), equalTo(true));
        assertThat(pLpPage.getPrice().isDisplayed(), equalTo(true));
        logger.info("verify filter options successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "verify filter options successfully");
    }

    /**
     * This Method verifies Address Page
     */
    public void verifyAddressPage() {
        waitElement(addressPage.getContactInfo(), 16);
        assertThat(addressPage.getContactInfo().isDisplayed(), equalTo(true));
        assertThat(addressPage.getShippingAddress().isDisplayed(), equalTo(true));
        assertThat(addressPage.getFirstNameTextboxOnAddAddressPage().isDisplayed(), equalTo(true));
        logger.info("verify Address Page successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "verify Address Page successfully");
    }

    /**
     * This Method verifies default country display or not
     */
    public void isDefaultCountryDisplay(String country) {
        waitElement(addressPage.getCountry(), 16);
        assertThat(addressPage.getCountry().getText(), equalTo(country));
        logger.info("verify Address Page successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "verify Address Page successfully");
    }

    /**
     * This Method click on country dropdown
     */
    public void clickOnCountry() {
        if (isPlatformNameAndroid) {
            waitElement(addressPage.getCountry_region(), 16);
        }
        addressPage.getCountry_region().click();
        logger.info("click on country successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "click on country successfully");
    }

    /**
     * This Method select country from dropdown
     */
    public void selectCountry() {
        waitElement(addressPage.getCountry_India(), 10);
        addressPage.getCountry_India().click();
        logger.info("India as country selected successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "India as country selected successfully");
    }

    /**
     * This Method click on state dropdown
     */
    public void clickOnState() {
        waitElement(addressPage.getState(), 16);
        addressPage.getState().click();
        logger.info("click on state successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "click on state successfully");
    }

    /**
     * This Method verifies all state list based on default country
     */
    public void isListOfStateDisplay(String state) {
        waitElement(addressPage.getSecondStateformList(), 16);
        assertThat(addressPage.getSecondStateformList().getText(), equalTo(state));
        logger.info("verify state List Page successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "verify state List Page successfully");
    }

    /**
     * This Method click MyAccount logo on homepage
     */
    public void clickOnMyAccount() {
        assertThat(loginPage.getMyAccount().isDisplayed(), equalTo(true));
        loginPage.getMyAccount().click();
        logger.info("click MyAccount logo on homepage  successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "click MyAccount logo on homepage successfully");
    }

    /**
     * This Method verifies New User Register Page
     */
    public void verifyNewUserRegisterPage() {
        waitElement(loginPage.getRegisterText(), 10);
        assertThat(loginPage.getRegisterText().isDisplayed(), equalTo(true));
        assertThat(loginPage.getRegisterBtn().isDisplayed(), equalTo(true));
        if (appName.equalsIgnoreCase("neovo")) {
            assertThat(loginPage.getPolicyCheckBoxOnRegisterPage().isDisplayed(), equalTo(true));
        }
        assertThat(loginPage.getConfirmPassword().isDisplayed(), equalTo(true));
        logger.info("verify New User Register Page successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "verify New User Register Page successfully");
    }

    /**
     * This Method click on Login Button On MyAccount Page
     */
    public void clickOnLoginButtonOnMyAccountPage() {
        waitElement(myAccount.getLoginBtnMyProfile(), 16);
        assertThat(myAccount.getLoginBtnMyProfile().isDisplayed(), equalTo(true));
        myAccount.getLoginBtnMyProfile().click();
        logger.info("click on Login Button On MyAccount Page successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "click on Login Button On MyAccount Page successfully");
    }

    /**
     * This method Enter confirm Password Successfully
     */
    public void enterConfirmPasswordForNewUser(String password) {
        waitElement(loginPage.getConfirmPassword(), 6);
        assertThat(loginPage.getConfirmPassword().isDisplayed(), equalTo(true));
        loginPage.getConfirmPassword().sendKeys(password);
        logger.info("Enter confirm Password successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Enter confirm Password successfully");
    }

    /**
     * This method select Policy CheckBox Successfully
     */
    public void selectPolicyCheckBox() {
        if (isPlatformNameAndroid) {
            assertThat(loginPage.getPolicyCheckBoxOnRegisterPage().isDisplayed(), equalTo(true));
            loginPage.getPolicyCheckBoxOnRegisterPage().click();
        } else {
            mobileDriver.hideKeyboard();
            waitFor(1);
            clickCoordinates(40, 524);
        }
        logger.info("selected Policy CheckBox Successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "selected Policy CheckBox Successfully");
    }

    /**
     * This method select Policy CheckBox Successfully
     */
    public void clickRegisterBtnForNewUser() {
        assertThat(myAccount.getRegisterBtn().isDisplayed(), equalTo(true));
        myAccount.getRegisterBtn().click();
        logger.info("Registered Button clicked successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Registered Button clicked successfully");
    }

    /**
     * This Method verifies MyAccount page
     */
    public void verifyMyAccountPage() {
        waitElement(myAccount.getMyOrders(), 16);
        assertThat(myAccount.getMyOrders().isDisplayed(), equalTo(true));
        assertThat(myAccount.getFavorites().isDisplayed(), equalTo(true));
        assertThat(myAccount.getSettings().isDisplayed(), equalTo(true));
        if (isPlatformNameAndroid) {
            scrollToText("Delete Account");
        } else {
            scrollForIos();
        }
        assertThat(myAccount.getDeleteAccountButtonOnProfilePage().isDisplayed(), equalTo(true));
        logger.info("verify MyAccount page successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "verify MyAccount page successfully");
    }

    /**
     * This Method verifies Login btn On MyProfile Page
     */
    public void verifyLoginBtnOnMyProfilePage() {
        waitElement(myAccount.getLoginBtnMyProfile(), 10);
        assertThat(myAccount.getLoginBtnMyProfile().isDisplayed(), equalTo(true));
        logger.info("Login btn On MyProfile Page verify successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Login btn On MyProfile Page verify successfully");
    }

    /**
     * This Method click on My Orders
     */
    public void clickMyOrder() {
        assertThat(myAccount.getMyOrders().isDisplayed(), equalTo(true));
        myAccount.getMyOrders().click();
        logger.info("click on My Orders successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "click on My Orders successfully");
    }

    /**
     * This Method verifies My Orders page with no orders
     */
    public void verifyMyOrdersPageWithoutAnyPlacedOrder() {
        waitElement(myAccount.getMyOrderPageText(), 6);
        assertThat(myAccount.getMyOrderPageText().isDisplayed(), equalTo(true));
        assertThat(myAccount.getNoOrderMessage().getText(), containsString("No order found for the associated Email ID"));
        logger.info("My Orders page without any placed order verify successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "My Orders page without any placed order verify successfully");
    }

    /**
     * This Method verifies My Orders page
     */
    public void verifyMyOrdersPage() {
        waitElement(myAccount.getMyOrderPageText(), 6);
        logger.info("My Orders page verified successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "My Orders page verified successfully");
    }

    /**
     * Verify Delete Account On Profile Page
     * App name: Neovo-commerce
     */
    public void verifyDeleteAccountOnProfilePage(String deleteAccountText) {
        waitElement(myAccount.getDeleteAccountButtonOnProfilePage(), 10);
        assertThat(myAccount.getDeleteAccountButtonOnProfilePage().getText(), equalTo(deleteAccountText));
        logger.info("Delete Account on Profile page button verified successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Delete Account on Profile page button verified successfully");
    }

    /**
     * verify Address Saved Successfully Text On Address Page Popup
     * App name: Neovo-commerce
     */
    public void verifyAddressSavedSuccessfullyTextOnAddressPagePopup(String addressSavedMessage) {
        waitElement(addressPage.getAddressSavedTextOnPopup(), 9);
        assertThat(addressPage.getAddressSavedTextOnPopup().getText(), is(equalTo(addressSavedMessage)));
        logger.info("Address saved message verified successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Address saved message verified successfully");
    }


    /**
     * This Method helps to remove the addresss from profile page
     */
    public void clickOnRemoveButtonFromProfilePage() {
        waitElement(myAccount.getAddressPage(), 3);
        assertThat(myAccount.getRemoveaddress().isDisplayed(), equalTo(true));
        myAccount.getRemoveaddress().click();
        logger.info("Clicked on remove button address from profile page successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Clicked on remove button address from profile page successfully");
    }

    /**
     * This Method calculates total amount after product quantity update on cart
     */
    public String totalAmountOnCart() {
        waitElement(cartPage.getTotalAmount(), 6);
        assertThat(cartPage.getTotalAmount().isDisplayed(), equalTo(true));
        if (isPlatformNameAndroid) {
            return cartPage.getTotalAmount().getText().replaceAll("[^.,0-9 ]", "").substring(3);
        } else {
            return cartPage.getTotalAmount().getText().replaceAll("[^.,0-9 ]", "").substring(2);
        }

    }

    /**
     * This Method return product quantity on cart
     */
    public String totalAmount() {
        waitFor(1);
        waitElement(cartPage.getProductsQuantity(), 6);
        String qty = cartPage.getProductsQuantity().getText();
        String totalAmt = totalAmountOnCart().replaceAll(",", ".");
        logger.info(totalAmt);
        float totalPrice = (Float.valueOf(totalAmt) / (Float.valueOf(qty)));
        return String.format("%.2f", totalPrice);
    }

    /**
     * This Method increase product quantity on cart page
     */
    public void increaseProductQuantity(int i) {
        waitElement(cartPage.getProductsQuantity(), 6);
        while (i > 0) {
            cartPage.getIncreaseQuantity().click();
            waitFor(1);
            i--;
        }
        logger.info("Product Quantity increase successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Product Quantity increase successfully");
    }

    /**
     * This Method decrease product quantity on cart page
     */
    public void decreaseProductQuantity(int i) {
        waitElement(cartPage.getProductsQuantity(), 6);
        while (i > 0) {
            cartPage.getDecreaseQuantity().click();
            waitFor(1);
            i--;
        }
        logger.info("Product Quantity decrese successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Product Quantity decrease successfully");
    }

    /**
     * This Method verifies calculates total amount after increase product quantity on cart
     */
    public void verifyUpdatedTotalAmountAfterIncrease(String totalAmt) {
        waitElement(cartPage.getProductsQuantity(), 6);
        assertThat(cartPage.getProductsQuantity().isDisplayed(), equalTo(true));
        String qty = cartPage.getProductsQuantity().getText();
        float totalPrice = (Float.valueOf(totalAmt.replace(",", ".")) * (Float.valueOf(qty)));
        String total = String.format("%.2f", totalPrice);
        if (isPlatformNameAndroid) {
            assertThat(cartPage.getTotalAmount().getText().replaceAll("[^.,0-9 ]", "").substring(3), equalTo(total));
        } else {
            assertThat(cartPage.getTotalAmount().getText().replaceAll("[^.,0-9 ]", "").replace(",", ".").substring(2).trim(), equalTo(total));
        }
        logger.info("Updated Total Amount After Increase Quantity verified successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Updated Total Amount After Increase Quantity verified successfully");
    }

    /**
     * This Method verifies calculates total amount after increase product quantity on cart
     */
    public void verifyUpdatedTotalAmountAfterDecrease(String totalAmt) {
        waitElement(cartPage.getProductsQuantity(), 6);
        assertThat(cartPage.getProductsQuantity().isDisplayed(), equalTo(true));
        if (isPlatformNameAndroid) {
            assertThat(cartPage.getTotalAmount().getText().replaceAll("[^.,0-9 ]", "").substring(3), equalTo(totalAmt));
        } else {
            assertThat(cartPage.getTotalAmount().getText().replaceAll("[^.,0-9 ]", "").replaceAll(",", ".").substring(2).trim(), equalTo(totalAmt));
        }

        logger.info("Updated Total Amount After Increase Quantity verified successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Updated Total Amount After Increase Quantity verified successfully");
    }

    /**
     * This Method click on orderId on myOrder page
     */
    public void clickOnOrderId() {
        waitElement(myAccount.getOrderId(), 6);
        assertThat(myAccount.getOrderId().isDisplayed(), equalTo(true));
        myAccount.getOrderId().click();
        logger.info("click on order id successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "click on order id successfully");
    }

    /**
     * This Method click on orderId on myOrder page
     */
    public void verifyOrderDetailsPage() {
        waitElement(myAccount.getOrderId(), 6);
        assertThat(myAccount.getProductDetails().isDisplayed(), equalTo(true));
        logger.info("Order Details Page verified successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Order details page verified successfully");
    }

    /**
     * This Method click on orderId on myOrder page
     */
    public void verifyVariantWisePrice() {
        waitElement(myAccount.getProductPrice(), 6);
        assertThat(myAccount.getProductVariant().isDisplayed(), equalTo(true));
        assertThat(myAccount.getProductPrice().getText(), containsString("Rs."));
        logger.info("Order Details Page verified successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Order details page verified successfully");

    }

    /**
     * Verify Related Product Header Text
     * App name: Neovo-commerce
     */
    public void verifyRelatedProductHeaderText() {
        waitElement(pDpPage.getRelatedProductHeaderText(), 15);
        logger.info("Related Product Header Text verified successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Related Product Header Text verified successfully");
    }

    /**
     * Click Cancle Button OnMobile Keyboard
     * App name: Neovo-commerce
     */
    public void clickCancleButtonOnMobileKeyboard() {
        waitElement(addressPage.getCancelButtonOnMobileKeyboard(), 6);
        addressPage.getCancelButtonOnMobileKeyboard().click();
        logger.info("Cancle Button OnMobile Keyboard Clicked Successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Cancle Button OnMobile Keyboard Clicked Successfully");
    }

    /**
     * Method validates the toast message passed with wait time
     *
     * @param toastMessage
     * @param waitInSeconds
     */
    public void verifyToastMessage(String toastMessage, int waitInSeconds) {
        if (isPlatformNameAndroid) {
            waitElementTillPresenceOfElementLocated(ANDROID_TOAST_MESSAGE_XPATH, waitInSeconds);
            MobileElement toastElement = mobileDriver.findElement((By.xpath(ANDROID_TOAST_MESSAGE_XPATH)));
            assertThat(toastElement.getText(), is(equalTo(toastMessage)));
        } else {
            String toastXpathPreString = "//XCUIElementTypeStaticText[@name='";
            String toastXpathPostString = "']";
            waitElementTillPresenceOfElementLocated(toastXpathPreString + toastMessage + toastXpathPostString, waitInSeconds);
        }
        logger.info("Toast message verified successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Toast message verified successfully");
    }


    /**
     * Method clicks on Add more from favorite button in cart page
     */
    public void clickAddMoreFromFavoriteButtonInCartPage() {
        waitElement(cartPage.getAddMoreFromFavoriteButton(), 10);
        cartPage.getAddMoreFromFavoriteButton().click();
        logger.info("Add more from favorite button clicked on Cart Page");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Add more from favorite button clicked on Cart Page");
    }

    /**
     * Method clicks on the add to cart for the product title passed
     *
     * @param productTitle
     */
    public void clickAddToCartOnFavoritesPage(String productTitle) {
        String productTitleXPath;
        if (ReadConfig.getMobilePlatformName().equalsIgnoreCase("Android"))
            productTitleXPath = "//android.widget.TextView[@text='" + productTitle + "']/following-sibling::android.widget.Button[@text = 'ADD TO CART']";
        else
            productTitleXPath = "//XCUIElementTypeStaticText[@name='" + productTitle + "']/following-sibling::XCUIElementTypeButton";
        MobileElement productTileElement = mobileDriver.findElement(By.xpath(productTitleXPath));
        assertThat(productTileElement.isDisplayed(), equalTo(true));
        productTileElement.click();
        logger.info(productTitle + "'s add to cart button is clicked");
        ExtentTestManager.getTest().log(LogStatus.PASS, productTitle + "'s add to cart button is clicked");
    }

    /**
     * Method clicks on add to cart pop up on favorites page
     */
    public void clickAddToCartPopUpOnFavoritesPage() {
        waitElement(favoritePage.getAddToCartButtonOnPopUp(), 10);
        favoritePage.getAddToCartButtonOnPopUp().click();
        logger.info("Add more from favorite button clicked on Cart Page");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Add more from favorite button clicked on Cart Page");
    }

    /**
     * Method clicks on the add to cart for the product title passed
     *
     * @param productTitle
     */
    public void verifyCartPageProductsQuantity(String productTitle, String quantity) {
        String productTitleXPath;
        if (isPlatformNameAndroid)
            productTitleXPath = "//android.widget.TextView[@text='" + productTitle + "']/following-sibling::android.widget.RelativeLayout/android.widget.EditText[contains(@resource-id, 'product_item_addtocart_total_quantity')]";
        else
            productTitleXPath = "//XCUIElementTypeStaticText[@name='" + productTitle + "']/following-sibling::XCUIElementTypeOther/XCUIElementTypeButton[@name='-']/following-sibling::XCUIElementTypeTextField";
        MobileElement productTileElement = mobileDriver.findElement(By.xpath(productTitleXPath));
        assertThat(productTileElement.getText(), is(equalTo(quantity)));
        logger.info("The quantity of the product in cart page is verified");
        ExtentTestManager.getTest().log(LogStatus.PASS, "The quantity of the product in cart page is verified");
    }

    /**
     * Method clicks on the the product in the PLP page
     *
     * @param productTitle
     */
    public void clickProductInPLP(String productTitle) {
        String productTitleXPath;
        if (ReadConfig.getMobilePlatformName().equalsIgnoreCase("Android"))
            productTitleXPath = "//android.widget.TextView[@text='" + productTitle + "']";
        else
            productTitleXPath = "//XCUIElementTypeStaticText[@name='" + productTitle + "']";
        MobileElement productTileElement = mobileDriver.findElement(By.xpath(productTitleXPath));
        assertThat(productTileElement.isDisplayed(), equalTo(true));
        productTileElement.click();
        logger.info(productTitle + " product is clicked on PLP");
        ExtentTestManager.getTest().log(LogStatus.PASS, productTitle + " product is clicked on PLP");
    }

    /**
     * Method clicks on back button at the top left of the page on Favorites Page
     */
    public void clickBackButtonIconOnFavoritesPage() {
        waitElement(favoritePage.getBackButtonIcon(), 5);
        assertThat(favoritePage.getBackButtonIcon().isDisplayed(), equalTo(true));
        favoritePage.getBackButtonIcon().click();
        logger.info("Clicked on back button in the top left of the page");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Clicked on back button in the top left of the page");
    }


    /**
     * App name: Sandbox
     * Invoke product from homepage from product icon grid
     */
    public void clickOnProductFromGrid() {
        waitElement(homePage.getProductFromHomepageForSandbox(), 30);
        homePage.getProductFromHomepageForSandbox().click();
        logger.info("Product invoked successfully from product grid from homepage");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Product invoked successfully from product grid from homepage");
    }

    /**
     * App name: Sandbox
     * Invoke product from homepage from product icon grid
     */
    public void verifyCTAPopup() {
        waitFor(2);
        assertThat(cartPage.getShopPayPopup().isDisplayed(), equalTo(true));
        assertThat(cartPage.getOtherPopup().isDisplayed(), equalTo(true));
        assertThat(cartPage.getCreditCardPopup().isDisplayed(), equalTo(true));
        logger.info("CTA popup verified Successfully on Place Order button");
        ExtentTestManager.getTest().log(LogStatus.PASS, "CTA popup verified Successfully on Place Order button");
    }

    /**
     * Click Shoes Collection On Collection Page
     * App name: Neovo-commerce
     */
    public void clickShoesCollectionOnCollectionPage() {
        waitElement(collectionPage.getShoesCollection(), 6);
        collectionPage.getShoesCollection().click();
        logger.info("Successfully Clicked on Shoes Collection On Collection Page");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Successfully Clicked on Shoes Collection On Collection Page");
    }

    /**
     * Click Sandals Collection On Collection Page
     * App name: Neovo-commerce
     */
    public void clickSandalsCollectionOnCollectionPage() {
        waitElement(collectionPage.getSandalsCollection(), 6);
        collectionPage.getSandalsCollection().click();
        logger.info("Successfully Clicked on Sandals Collection On Collection Page");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Successfully Clicked on Sandals Collection On Collection Page");
    }

    /**
     * Verify Additional Text On PDP Page
     * App name: Neovo-commerce
     */
    public void verifyAdditionalTextOnPDPPage(String text) {
        waitElement(pDpPage.getAdditionalPromoLabelOnPDP(), 15);
        assertThat(pDpPage.getAdditionalPromoLabelOnPDP().getText(), is(equalTo(text)));
        logger.info(text + " : Additional promo label V2  On PDP Page  verified successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, text + " : Additional promo label V2 On PDP Page verified successfully");
    }

    /**
     * Click First Product on Sandals PLP Page
     * App name: Neovo-commerce
     */
    public void clickFirstProductFromPLP() {
        waitFor(.7);
        waitElement(pLpPage.getPlpPoster(), 9);
        pLpPage.getPlpPoster().click();
        logger.info("Successfully Clicked on First Product on PLP Page");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Successfully Clicked on First Product on PLP Page");
    }

    /**
     * Verify Additional Text On PLP Page
     * App name: Neovo-commerce
     */
    public void verifyAdditionalTextOnPLPPage(String text) {
        waitElement(pLpPage.getAdditionalLabelOnPLP(), 15);
        assertThat(pLpPage.getAdditionalLabelOnPLP().getText(), is(equalTo(text)));
        logger.info(text + " : Additional promo label V2 On PLP Page verified successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, text + " : Additional promo label V2 On PLP Page verified successfully");
    }

    /**
     * Enter searchtext on searchbox on collection page
     * App name: Neovo-commerce
     */
    public void enterTextOnSearchboxOnCollectionPage(String searchText) {
        waitElement(collectionPage.getSearchOnCollectionPage(), 10);
        collectionPage.getSearchOnCollectionPage().sendKeys(searchText);
        logger.info(searchText + " : text entered in on search bar successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, searchText + " : text entered in on search bar successfully");
    }

    /**
     * Verify Additional Text On Search Page
     * App name: Neovo-commerce
     */
    public void verifyAdditionalTextOnSearchPage(String text) {
        waitElement(searchPage.getAdditionalPromoLabelOnSearchPage(), 15);
        assertThat(searchPage.getAdditionalPromoLabelOnSearchPage().getText(), containsString(text));
        logger.info(text + " :Additional promo label V2 On Search Page verified successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, text + " : Additional promo label V2 On Search Page verified successfully");
    }

    /**
     * Verify Categories Header Text On Category Page
     * App name: Neovo-commerce
     */
    public void verifyCategoriesHeaderTextOnCategoryPage(String headerText) {
        waitElement(collectionPage.getCategoryHeaderTextOnCategoryPage(), 15);
        assertThat(collectionPage.getCategoryHeaderTextOnCategoryPage().getText(), is(equalTo(headerText)));
        logger.info(headerText + " : Header Text On category Page verified successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, headerText + " : Header Text On category Page verified successfully");
    }

    /**
     * Verify Search Header Text On Search Page
     * App name: Neovo-commerce
     */
    public void verifySearchHeaderTextOnSearchPage() {
        waitElement(searchPage.getSearchHeaderTextOnSearchPage(), 10);
        logger.info("Header Text On Search Page verified successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Header Text On Search Page verified successfully");
    }

    /**
     * Method selects the filter option passed.
     *
     * @param filter
     */
    public void selectFilterOption(FilterOptionsEnum filter) {
        switch (filter) {
            case IN_STOCk:
                assertThat(pLpPage.getInStockFilterOption().isDisplayed(), equalTo(true));
                pLpPage.getInStockFilterOption().click();
                break;
            case OUT_OF_STOCK:
                assertThat(pLpPage.getOutOfStockFilterOption().isDisplayed(), equalTo(true));
                pLpPage.getOutOfStockFilterOption().click();
                break;
        }
        logger.info(filter + " filter option clicked.");
        ExtentTestManager.getTest().log(LogStatus.PASS, filter + " filter option clicked.");
    }

    /**
     * Method clicks on APPLY button in the filter page of PLP
     */
    public void clickApplyFilterButton() {
        assertThat(pLpPage.getApplyFilterButton().isDisplayed(), equalTo(true));
        pLpPage.getApplyFilterButton().click();
        logger.info("Apply button in filter page clicked");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Apply button in filter page clicked");
    }

    /**
     * Method checks if the product is available on PDP in the first few places
     *
     * @param productTitle
     */
    public void isProductPresentOnPLP(String productTitle) {
        String productTitleXPath;
        if (isPlatformNameAndroid)
            productTitleXPath = "//android.widget.TextView[contains(@text,'" + productTitle + "')]";
        else
            productTitleXPath = "//XCUIElementTypeStaticText[contains(@name,'" + productTitle + "')]";
        MobileElement productTileElement = mobileDriver.findElement(By.xpath(productTitleXPath));
        assertThat(productTileElement.isDisplayed(), equalTo(true));
        logger.info(productTitle + " product is available on PLP");
        ExtentTestManager.getTest().log(LogStatus.PASS, productTitle + " product is available on PLP");
    }

    /**
     * Method verifies the Address form text boxes in the Profile tab
     */
    public void verifyAddressFormFields() {
        waitElement(myAccount.getFirstNameAccountPage(), 15);
        assertThat(myAccount.getLastNameAccountPage().isDisplayed(), equalTo(true));
        assertThat(addressPage.getCompanyTextboxOnAddAddressPage().isDisplayed(), equalTo(true));
        assertThat(addressPage.getContactNumberTextboxOnAddAddressPage().isDisplayed(), equalTo(true));
        assertThat(addressPage.getAddressLine1TextboxOnAddAddressPage().isDisplayed(), equalTo(true));
        assertThat(addressPage.getAddressLine2TextboxOnAddAddressPage().isDisplayed(), equalTo(true));
        assertThat(addressPage.getStateTextboxOnAddAddressPage().isDisplayed(), equalTo(true));
        assertThat(addressPage.getCityTextboxOnAddAddressPage().isDisplayed(), equalTo(true));
        if (!isPlatformNameAndroid)
            scrollForIos();
        assertThat(addressPage.getZipcodeTextboxOnAddAddressPage().isDisplayed(), equalTo(true));
    }

    /**
     * Method clears the search box in the collection page
     */
    public void clearSearchBoxInCollectionPage() {
        assertThat(collectionPage.getSearchOnCollectionPage().isDisplayed(), equalTo(true));
        collectionPage.getSearchOnCollectionPage().clear();
        logger.info("Search box cleared successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Search box cleared successfully");
    }

    /**
     * This method redirect to specific deeplink url page
     */
    public void openDeepLinkUrl(DeeplinkPathEnum pathToRedirectDeeplink) {
        if (isPlatformNameAndroid) {
            mobileDriver.executeScript("mobile:deepLink",
                    ImmutableMap.of(
                            "url", readconfig.getBaseUrlForAndroid() + pathToRedirectDeeplink.getValue(),
                            "package", readconfig.getPackageName()
                    ));
            logger.info("Deeplink url : " + readconfig.getBaseUrlForAndroid() + pathToRedirectDeeplink.getValue());
            ExtentTestManager.getTest().log(LogStatus.PASS, "Deeplink url : " + readconfig.getBaseUrlForAndroid() + pathToRedirectDeeplink.getValue());
        } else {
            String iosUrl = readconfig.getBaseUrlForIos() + pathToRedirectDeeplink.getValue();
            terminateiOSApp();
            waitFor(1);
            launchSafariApp();
            verifySafariHomePage();
            clickOnSafariSeacrhBar();
            enterTextInSafariSeacrhBar(iosUrl);
            clickOnGoFromKeyword();
            logger.info("Deeplink url : " + iosUrl);
            ExtentTestManager.getTest().log(LogStatus.PASS, "Deeplink url : " + iosUrl);
        }

    }

    /**
     * this method verify Dress Collection PLP
     */
    public void verifyCollectionPLP(String product) {
        if (isPlatformNameAndroid) {
            waitElement(pLpPage.getPlpPageTitle(), 10);
            assertThat(pLpPage.getPlpPageTitle().isDisplayed(), equalTo(true));
        } else {
            waitElement(pLpPage.getPlp1stProduct(), 10);
            assertThat(pLpPage.getPlp1stProduct().isDisplayed(), equalTo(true));
        }
        boolean b = pLpPage.getPlp1stProduct().getText().contains(product) || pLpPage.getPlp1stProduct().getText().contains("Jacket");
        if (b == false) {
            Assert.fail("Unable to verify Dress Collection PLP");
        }
        logger.info("Dress Collection PLP verify successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Dress Collection PLP verify successfully");
    }

    /**
     * this method verify empty cart page
     */
    public void verifyEmptyCartPage() {
        waitElement(cartPage.getContinueShopping(), 10);
        if (isPlatformNameAndroid) {
            assertThat(cartPage.getContinueShopping().getText(), equalTo("Continue Shopping"));
        } else {
            assertThat(cartPage.getContinueShopping().getText(), equalTo("CONTINUE SHOPPING"));
        }
        assertThat(cartPage.getEmptyCartMessage().isDisplayed(), equalTo(true));
        logger.info("Empty cart page verify successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Empty cart page verify successfully");
    }

    /**
     * This method is used to click on yes button on Popup
     */
    public void clickYesOnPopup() {
        waitElement(addressPage.getClickYesButtonOnPopUp(), 6);
        addressPage.getClickYesButtonOnPopUp().click();
        logger.info("Clicked Yes on popup successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Clicked Yes on popup successfully");
    }

    /**
     * App Name: Sandbox
     * Verify Order Cancel button should appear under order deatils page
     */
    public void checkCancelOrderButton() {
        waitElement(myOrdersPage.getOrderCancelButton(), 30);
        assertThat(myOrdersPage.getOrderCancelButton().isDisplayed(), equalTo(true));
        logger.info("Cancel order button appears under order details page");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Cancel order button appears under order details page");
    }

    /**
     * App Name: Sandbox
     * Verify Track Order button should appear under order deatils page
     */
    public void checkTrackOrderButton() {
        waitElement(myOrdersPage.getTrackOrderButton(), 30);
        assertThat(myOrdersPage.getTrackOrderButton().isDisplayed(), equalTo(true));
        logger.info("Track order button appears under order details page");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Track order button appears under order details page");
    }

    /**
     * App Name: Sandbox
     * Verify Reorder button should appear under order deatils page
     */
    public void checkReorderButton() {
        waitElement(myOrdersPage.getReorderButton(), 10);
        assertThat(myOrdersPage.getReorderButton().isDisplayed(), equalTo(true));
        logger.info("Reorder button appears under order details page");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Reorder button appears under order details page");
    }

    /**
     * Method to enter first name in edit profile
     */
    public void enterFirstNameonEditProfile(String fname) {
        waitElement(myAccount.getFirstNameOnEditProfilePage(), 3);
        assertThat(myAccount.getFirstNameOnEditProfilePage().isDisplayed(), equalTo(true));
        myAccount.getFirstNameOnEditProfilePage().click();
        myAccount.getFirstNameOnEditProfilePage().clear();
        myAccount.getFirstNameOnEditProfilePage().sendKeys(fname);
        logger.info("User Entered First name on Edit profile Page successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "User Entered First name on Edit profile Page successfully");
    }

    /**
     * Method to enter last name in edit profile
     */
    public void enterLastNameonEditProfile(String lname) {
        waitElement(myAccount.getLastNameOnEditProfilePage(), 3);
        assertThat(myAccount.getLastNameOnEditProfilePage().isDisplayed(), equalTo(true));
        myAccount.getLastNameOnEditProfilePage().click();
        myAccount.getLastNameOnEditProfilePage().clear();
        myAccount.getLastNameOnEditProfilePage().sendKeys(lname);
        logger.info("User Entered last name on Edit profile Page successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "User Entered last name on Edit profile Page successfully");
    }

    /**
     * Method to update profile details on edit profile page
     */
    public void clickUpdateProfileButton() {
        waitElement(myAccount.getUpdateProfileOnEditProilePage(), 2);
        assertThat(myAccount.getUpdateProfileOnEditProilePage().isDisplayed(), equalTo(true));
        myAccount.getUpdateProfileOnEditProilePage().click();
        logger.info("User clicked on update profile button in Edit profile Page successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "User clicked on update profile button in Edit profile Page successfully");
    }

    /**
     * Method to hide keyboard for android and iOS
     */
    public void clickDoneButtonOnKeyboard() {
        if (!isPlatformNameAndroid) {
            onBoardingPage.getReturnButtonIOS().click();
        } else {
            mobileDriver.hideKeyboard();
        }
        logger.info("Keyboards get hide successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Keyboards get hide successfully");
    }


    /**
     * Method verifies if the cart icon is present on the PDP page
     */
    public void isCartIconPresentOnPdpPage() {
        assertThat(pDpPage.getCartIcon().isDisplayed(), equalTo(true));
        logger.info("Cart Icon is present on PDP page");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Cart Icon is present on PDP page");
    }

    /**
     * Method verifies if the onboarding screen is loaded on Neovo app
     */
    public void clickOnboardingScreenIsVisible() {
        assertThat(onBoardingPage.getNoButton().isDisplayed(), equalTo(true));
        logger.info("On-Boarding Page verified successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "On-Boarding Page verified successfully");
    }

    /**
     * Method verifies Homepage For Neovo Store
     */
    public void verifyHomepageForNeovoStore() {
        waitElement(homePage.getFootwareLogoText(), 10);
        assertThat(homePage.getFootwareLogoText().isDisplayed(), equalTo(true));
        assertThat(homePage.getFootwareText().getText(), equalTo("Footware"));
        assertThat(homePage.getFashionText().getText(), equalTo("Fashion"));
        verifyAppLogoOnHeader();
        logger.info("verify Homepage For Neovo Store successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "verify Homepage For Neovo Store successfully");
    }

    /**
     * This Method select store from onboarding page
     */
    public void selectStore(StoreNameEnum storeName) {
        try {
            switch (storeName) {
                case NEOVO:
                    waitElement(onBoardingPage.getNeovoStore(), 9);
                    onBoardingPage.getNeovoStore().click();
                    break;
                case SANDBOX:
                    waitElement(onBoardingPage.getSandboxStore(), 9);
                    onBoardingPage.getSandboxStore().click();
                    break;
                default:
                    break;
            }
        } catch (Exception e) {
            logger.error("Please put correct store name");
        }
        logger.info(storeName + " Store selected successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, storeName + " Store selected successfully");
    }

    /**
     * This method click on current store
     */
    public void clickOnCurrentStore() {
        waitFor(7);
        if (isPlatformNameAndroid) {
            scrollToText("Language");
        }
        waitElement(morePage.getSelectedStoreName(), 10);
        morePage.getSelectedStoreName().click();
        logger.info("Clicked on current store successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Clicked on current store successfully");
    }

    /**
     * This Method click MyAccount logo For Sandbox on homepage
     */
    public void clickOnMyAccountForSandbox() {
        waitElement(myAccount.getMyAccountLogoSandbox(), 6);
        assertThat(myAccount.getMyAccountLogoSandbox().isDisplayed(), equalTo(true));
        myAccount.getMyAccountLogoSandbox().click();
        logger.info("Click MyAccount logo on homepage For Sandbox successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Click MyAccount logo on homepage For Sandbox successfully");
    }

    /**
     * Verify MorePage For Sandbox
     */
    public void verifyMorePageForSandbox() {
        waitElement(morePage.getMyProfile(), 10);
        assertThat(morePage.getHomepage().isDisplayed(), equalTo(true));
        assertThat(morePage.getCategory().isDisplayed(), equalTo(true));
        if (isPlatformNameAndroid) {
            scrollToText("Language");
        }
        assertThat(morePage.getSelectedStoreName().getText(), equalTo("plobal-sandbox-test"));
        logger.info("More page verified For Sandbox Successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "More page verified For Sandbox Successfully");
    }

    /**
     * Verify MorePage For Sandbox
     */
    public void verifyMorePageForNeovo() {
        waitElement(morePage.getCtInboxOnMorePage(), 30);
        assertThat(morePage.getCtInboxOnMorePage().isDisplayed(), equalTo(true));
        assertThat(morePage.getContactUs().isDisplayed(), equalTo(true));
        assertThat(morePage.getSelectedStoreName().getText(), equalTo("Neovo-Commerce"));
        logger.info("More page verified For neovo Successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "More page verified For neovo Successfully");
    }


    /**
     * App Name: Sandbox
     * Invoke Credit card option in CTA option from cart page
     */
    public void clickOnCreditCardCTA() {
        waitFor(2);
        assertThat(cartPage.getCreditCardPopup().isDisplayed(), equalTo(true));
        cartPage.getCreditCardPopup().click();
        logger.info("Clicked successfully on Credit card option");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Clicked successfully on Credit card option");
    }

    /**
     * App Name: Sandbox
     * Verify Native checkout page
     */
    public void checkNativeCheckoutPage() {
        waitElement(checkoutPage.getPaymentIcon(), 30);
        assertThat(checkoutPage.getPaymentIcon().isDisplayed(), equalTo(true));
        assertThat(checkoutPage.getShippingAddressIcon().isDisplayed(), equalTo(true));
        logger.info("Native checkout page verified successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Native checkout page verified successfully");
    }

    /**
     * App Name: Sandbox
     * Click on payment option on native checkout page
     */
    public void clickOnPaymentOptionOnCheckoutPage() {
        waitElement(checkoutPage.getPaymentIcon(), 30);
        assertThat(checkoutPage.getPaymentIcon().isDisplayed(), equalTo(true));
        checkoutPage.getPaymentIcon().click();
        logger.info("Payment option clicked successfully on native checkout page");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Payment option clicked successfully on native checkout page");
    }

    /**
     * App Name: Sandbox
     * Enter credit card Number
     */
    public void enterCreditCardNumber(String creditNumber) {
        assertThat(checkoutPage.getCreditCardNumber().isDisplayed(), equalTo(true));
        checkoutPage.getCreditCardNumber().sendKeys(creditNumber);
        logger.info("Credit card number entered Successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Credit card number entered Successfully");
    }

    /**
     * App Name: Sandbox
     * Enter firstname for credit card
     */
    public void enterFirstNameOnCredit(String firstName) {
        checkoutPage.getFirstNameOnCredit().sendKeys(firstName);
        logger.info("First name entered Successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "First name entered Successfully");
    }

    /**
     * App Name: Sandbox
     * Enter lastname for credit card
     */
    public void enterLastNameOnCredit(String lastName) {
        checkoutPage.getLastNameOnCredit().sendKeys(lastName);
        logger.info("Last name entered Successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Last name entered Successfully");
    }

    /**
     * App Name: Sandbox
     * Enter Month and Year for credit card
     */
    public void enterDateOnCredit(String date) {
        checkoutPage.getMonthAndYearOnCredit().sendKeys(date);
        logger.info("Date entered Successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Date entered Successfully");
    }

    /**
     * App Name: Sandbox
     * Enter CVV Number
     */
    public void enterCvvOnCredit(String cvv) {
        checkoutPage.getCvvOnCredit().sendKeys(cvv);
        logger.info("CVV entered Successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "CVV entered Successfully");
    }

    /**
     * App Name: Sandbox
     * Click on Pay Now button
     */
    public void clickOnPayNowButton() {
        waitElement(checkoutPage.getPayNowButton(), 30);
        waitFor(2);
        checkoutPage.getPayNowButton().click();
        logger.info("Pay now button clicked Successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Pay now button clicked Successfully");
    }

    /**
     * App Name: Sandbox
     * Click on Place order button/Continue button Successfully
     */
    public void clickOnContinueButtonOnCheckoutPage() {
        waitElement(checkoutPage.getContinueButtonOnCheckout(), 30);
        assertThat(checkoutPage.getContinueButtonOnCheckout().isDisplayed(), equalTo(true));
        checkoutPage.getContinueButtonOnCheckout().click();
        logger.info("Click on Place order button/Continue button Successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Click on Place order button/Continue button Successfully");
    }

    /**
     * App Name: Sandbox
     * Verify New thank you page.
     */
    public void verifyNewThankYouPage() {
        if (!isPlatformNameAndroid) {
            if (isRatingPopUpDisplayed()) {
                clickNotNowOnRatingPopUp();
            }
        }
        waitElement(thankYouPage.getOrderConfrimText(), 30);
        assertThat(thankYouPage.getCustomerInfoText().isDisplayed(), equalTo(true));
        //assertThat(thankYouPage.getProductName().isDisplayed(),equalTo(true));
        logger.info("New Thank you Page verified Successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "New Thank you Page verified Successfully");
    }

    /**
     * App Name: Sandbox
     * Click on list icon for sandbox
     */

    public void ClickOnListIconForSandbox() {
        waitElement(homePage.getListIconForSandbox(), 30);
        homePage.getListIconForSandbox().click();
        logger.info("Clicked on collection icon successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Clicked on collection icon successfully");
    }

    /**
     * App Name: Sandbox
     * Click on Dress collection for sandbox
     */
    public void clickOnDressCollectionForSandbox() {
        waitElement(homePage.getDressCollectionForSandbox(), 30);
        homePage.getDressCollectionForSandbox().click();
        waitElement(homePage.getDressCollection1ForSandbox(), 30);
        homePage.getDressCollection1ForSandbox().click();
        logger.info("Clicked on Dress Collection successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Clicked on Dress Collection successfully");
    }

    /**
     * App Name: Sandbox
     * Verify Shop the look button on PLP
     */
    public void checkShopTheLookOnPLP() {
        waitElement(pLpPage.getShopTheLookButton(), 30);
        assertThat(pLpPage.getShopTheLookButton().isDisplayed(), equalTo(true));
        logger.info("Shop the look button appeared Successfully on PLP");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Shop the look button appeared Successfully on PLP");
    }

    /**
     * App Name: Sandbox
     * Click on Other button CTA
     */
    public void clickOnOtherButtonCTA() {
        waitElement(cartPage.getOtherPopup(), 30);
        assertThat(cartPage.getOtherPopup().isDisplayed(), equalTo(true));
        cartPage.getOtherPopup().click();
        logger.info("Click on 'Other' Option successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Click on 'Other' Option successfully");
    }

    /**
     * App Name: Sandbox
     * Click on my Order button from New order confirmation page
     */
    public void clickMyOrderButtonOnThankYouPage() {
        waitElement(thankYouPage.getMyOrderOnThankYouPage(), 30);
        thankYouPage.getMyOrderOnThankYouPage().click();
        logger.info("My Orders button clicked successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "My Orders button clicked successfully");
    }

    /**
     * This method launch safari App
     */
    public void launchSafariApp() {
        mobileDriver.executeScript("mobile:launchApp",
                ImmutableMap.of(
                        "bundleId", readconfig.getSafariBundleId()
                ));
        logger.info("launch Safari App successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "launch Safari App successfully");
    }

    /**
     * This method terminate iOS App with help of bundleId
     */
    public void terminateiOSApp() {
        waitFor(1);
        String bundleId = readconfig.getBundleId();
        logger.info(bundleId);
        mobileDriver.terminateApp(bundleId);
        logger.info("iOS App terminated with bundleId " + readconfig.getBundleId() + " successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "iOS App terminated with bundleId " + readconfig.getBundleId() + " successfully");
    }

    /**
     * This method Verify Safari Home Page
     */
    public void verifySafariHomePage() {
        waitElement(homePage.getSafariTabOverview(), 30);
        assertThat(homePage.getSafariTabOverview().isDisplayed(), equalTo(true));
        assertThat(homePage.getSafariShareButton().isDisplayed(), equalTo(true));
        assertThat(homePage.getSafariSerachBar().isDisplayed(), equalTo(true));
        logger.info("Safari Home Page verified Successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Safari Home Page verified Successfully");
    }

    /**
     * This method click on Safari search bar
     */
    public void clickOnSafariSeacrhBar() {
        waitElement(homePage.getSafariSerachBar(), 5);
        homePage.getSafariSerachBar().click();
        logger.info("click on Safari search bar Successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "click on Safari search bar Successfully");
    }

    /**
     * This method enter text in Safari Seacrh Bar
     */
    public void enterTextInSafariSeacrhBar(String ulr) {
        waitFor(1);
        homePage.getEnterTextInSafariSerachBar().sendKeys(ulr);
        logger.info(ulr + " entered in Safari Seacrh Bar Successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, ulr + " entered in Safari Seacrh Bar Successfully");
    }

    /**
     * This method click On Go From Keyword in Safari
     */
    public void clickOnGoFromKeyword() {
        waitElement(homePage.getGoButton_IosKeyBoard(), 5);
        homePage.getGoButton_IosKeyBoard().click();
        logger.info("click On Go From Keyword in Safari Successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "click On Go From Keyword in Safari Successfully");
    }

    /**
     * This method click On open From popup in Safari
     */
    public void clickOnOpen() {
        waitFor(1.4);
        assertThat(homePage.getOpenPop_upSafari().isDisplayed(), equalTo(true));
        homePage.getOpenPop_upSafari().click();
        logger.info("click On open From popup in Safari Successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "click On Go From Keyword in Safari Seacrh Bar Successfully");
    }

    /**
     * App Name: Sandbox
     * Click on Continue Shopping button from New order confirmation page
     */
    public void clickOnContinueShoppingButtonOnThankYouPage() {
        waitElement(thankYouPage.getContinueShoppingButton(), 30);
        thankYouPage.getContinueShoppingButton().click();
        logger.info("Continue Shopping button clicked successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Continue Shopping button clicked successfully");
    }

    /**
     * OrderID  featching from new thank you page
     * App name: Neovo
     */
    public String getOrderIdFromNewThankyouPage() {
        String orderID[];
        assertThat(thankYouPage.getOrderId().isDisplayed(), equalTo(true));
        orderID = thankYouPage.getOrderId().getText().split("#");
        logger.info("OrderID featched successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "OrderID featched successfully");
        return orderID[1];
    }

    /**
     * Checking OrderID from new thank you page
     * App name:Neovo
     */
    public void checkOrderIdFromOrderListingPage(String orderID) {
        waitElement(myOrdersPage.getOrderIDOnNewOrderDetails(), 10);
        Assert.assertTrue(myOrdersPage.getOrderIDOnNewOrderDetails().getText().contains(orderID));
        logger.info("Order ID get verifed Succssfully from Thank you Page");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Order ID get verifed Succssfully from Thank you Page");
    }

    public void isProductAddToFavoritesBtnPresentOnPLP(String productTitle) {
        String likeButtonXPath;
        String productXPath;
        if (isPlatformNameAndroid) {
            productXPath = "//android.widget.TextView[@text = '" + productTitle + "']";
            likeButtonXPath = "(" + productXPath + "/ancestor::android.widget.LinearLayout/preceding-sibling::android.widget.RelativeLayout//android.widget.ImageView[contains(@resource-id, 'product_action_item_imgView')])[1]";
        } else {
            productXPath = "//XCUIElementTypeStaticText[@name='" + productTitle + "']/preceding-sibling::XCUIElementTypeOther/XCUIElementTypeButton";
            likeButtonXPath = productXPath + "[@name='like icon']";
        }
        MobileElement likeButtonElement = mobileDriver.findElement(By.xpath(likeButtonXPath));
        assertThat(likeButtonElement.isDisplayed(), equalTo(true));
        logger.info(productTitle + " favorites is present.");
        ExtentTestManager.getTest().log(LogStatus.PASS, productTitle + " favorites is present.");
    }

    /**
     * Methods checks if the add to cart button is present on PLP page for the product title string passed
     *
     * @param productTitle
     */
    public void isProductAddToCartBtnPresentOnPLP(String productTitle) {
        String addToCartButtonXPath;
        String productXPath;
        if (isPlatformNameAndroid) {
            productXPath = "//android.widget.TextView[@text = '" + productTitle + "']";
            addToCartButtonXPath = "(" + productXPath + "/ancestor::android.widget.LinearLayout/preceding-sibling::android.widget.RelativeLayout//android.widget.ImageView[contains(@resource-id, 'product_action_item_imgView')])[2]";
        } else {
            productXPath = "//XCUIElementTypeStaticText[@name='" + productTitle + "']/preceding-sibling::XCUIElementTypeOther/XCUIElementTypeButton";
            addToCartButtonXPath = productXPath + "[@name='ProductAddToCart']";
        }
        MobileElement addToCartButtonElement = mobileDriver.findElement(By.xpath(addToCartButtonXPath));
        assertThat(addToCartButtonElement.isDisplayed(), equalTo(true));
        logger.info(productTitle + " add to cart button is present.");
        ExtentTestManager.getTest().log(LogStatus.PASS, productTitle + " add to cart button is present.");
    }

    /**
     * Methods verifies if the price is present for the produce string passed
     *
     * @param productTitle
     */
    public void isProductPricePresentOnPLP(String productTitle) {
        String productTitleXPath;
        if (isPlatformNameAndroid)
            productTitleXPath = "//android.widget.TextView[contains(@text,'" + productTitle + "')]/parent::android.widget.LinearLayout/following-sibling::android.widget.LinearLayout//android.widget.TextView";
        else
            productTitleXPath = "//XCUIElementTypeStaticText[contains(@name,'" + productTitle + "')]/following-sibling::XCUIElementTypeStaticText";
        MobileElement productTileElement = mobileDriver.findElement(By.xpath(productTitleXPath));
        assertThat(productTileElement.isDisplayed(), equalTo(true));
        logger.info(productTitle + " product is available on PLP");
        ExtentTestManager.getTest().log(LogStatus.PASS, productTitle + " product is available on PLP");

    }

    /**
     * Method verifies if the Select Size option is displayed on the PDP page
     */
    public void verifySelectSizeIsDisabledInPdp() {
        try {
            if (pDpPage.getSelectSizeText().isDisplayed()) {
                logger.info("Select Size option is displayed. Test Failed");
                ExtentTestManager.getTest().log(LogStatus.PASS, "Select Size option is displayed. Test Failed");
                Assert.fail("Select Size option is Displayed");

            }
        } catch (NoSuchElementException e) {
            logger.info("Select Size option is not present");
            ExtentTestManager.getTest().log(LogStatus.PASS, "Select Size option is not present");
        }
    }

    /**
     * Method checks if the count of the products is correct
     *
     * @param search
     * @param resultMinimumCountOnScreen
     */
    public void isSearchResultLoaded(String search, int resultMinimumCountOnScreen) {
        String productTitleXPath;
        if (isPlatformNameAndroid)
            productTitleXPath = "//android.widget.TextView[contains(@text,'" + search + "')]";
        else
            productTitleXPath = "//XCUIElementTypeStaticText[contains(@name,'" + search + "')]";
        List<MobileElement> productTileElement = mobileDriver.findElements(By.xpath(productTitleXPath));
        if (productTileElement.size() < resultMinimumCountOnScreen) {
            logger.info("Search result response has less than " + resultMinimumCountOnScreen + " searches");
            ExtentTestManager.getTest().log(LogStatus.PASS, "Search result response has less than " + resultMinimumCountOnScreen + " searches");
            Assert.fail();
        }
        logger.info("Search result response has more than " + resultMinimumCountOnScreen + " searches");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Search result response has more than " + resultMinimumCountOnScreen + " searches");
    }

    /**
     * Method clicks on Search button on the IOS keyboard
     */
    public void clickSearchButtonOnIOSKeyboard() {
        assertThat(searchPage.getSearchButtonOnIOSKeyboard().isDisplayed(), equalTo(true));
        searchPage.getSearchButtonOnIOSKeyboard().click();
        logger.info("Search Button on IOS keyboard is clicked");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Search Button on IOS keyboard is clicked");
    }

    /**
     * App Name: Sandbox
     * Method clicks on Search button on the IOS keyboard
     */
    public void clickOnBackButtonFromCartPage() {
        waitElement(cartPage.getBackButtonFromCart(), 6);
        assertThat(cartPage.getBackButtonFromCart().isDisplayed(), equalTo(true));
        cartPage.getBackButtonFromCart().click();
        logger.info("Clicked on back button from cart page successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Clicked on back button from cart page successfully");
    }

    /**
     * App Name: Sandbox
     * Method which removes the adderess from address page
     */
    public void clickOnRemoveAddressFromProfilePage() {
        waitFor(1);
        waitElement(addressPage.getRemoveAddress(), 6);
        assertThat(addressPage.getRemoveAddress().isDisplayed(), equalTo(true));
        addressPage.getRemoveAddress().click();
        logger.info("Adderess Removed successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Adderess Removed successfully");
    }

    /**
     * Method clicks on Done button on the IOS keyboard
     */
    public void clickDoneButtonOnIOSKeyboard() {
        assertThat(checkoutPage.getDoneButtonOnIOSKeyboard().isDisplayed(), equalTo(true));
        checkoutPage.getDoneButtonOnIOSKeyboard().click();
        logger.info("Done Button on IOS keyboard is clicked");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Done Button on IOS keyboard is clicked");
    }

    /**
     * App Name: Sandbox
     * Method which click on address section from native checkout page
     */
    public void clickOnShippingAddressOnCheckoutPage() {
        assertThat(checkoutPage.getShippingAddressIcon().isDisplayed(), equalTo(true));
        checkoutPage.getShippingAddressIcon().click();
        logger.info("Shipping Address opened successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Shipping Address opened successfully");
    }

    /**
     * App Name: Sandbox
     * Method which removes the adderess from native checkout page
     */
    public void clickOnRemoveAddressFromCheckoutPage() {
        assertThat(checkoutPage.getRemoveAddress().isDisplayed(), equalTo(true));
        checkoutPage.getRemoveAddress().click();
        logger.info("Adderess Removed successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Adderess Removed successfully");
    }

    /**
     * App Name: Sandbox
     * verify empty text message on checkout page
     */
    public void checkEmptyAddressSectionOnCheckoutPage() {
        waitElement(checkoutPage.getNoAddressText(), 30);
        assertThat(checkoutPage.getNoAddressText().isDisplayed(), equalTo(true));
        checkoutPage.getNoAddressText().click();
        logger.info("Verified No address text on checkout page successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Verified No address text on checkout page successfully");
    }

    /**
     * App Name: Sandbox
     * Increase product quantity on cart page
     */
    public void increaseItemOnCartPage() {
        waitElement(cartPage.getIncreaseQuantity(), 10);
        assertThat(cartPage.getIncreaseQuantity().isDisplayed(), equalTo(true));
        cartPage.getIncreaseQuantity().click();
        logger.info("Quantity increased by 1");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Quantity increased by 1");
    }

    public void decreaseItemOnCartPage() {
        waitElement(checkoutPage.getDecreaseItem(), 30);
        assertThat(checkoutPage.getDecreaseItem().isDisplayed(), equalTo(true));
        checkoutPage.getDecreaseItem().click();
        logger.info("Quantity decreased by 1");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Quantity decreased by 1");
    }

    /**
     * App Name: Sandbox
     * Verify item count on cartpage
     */
    public void checkItemCount(String count) {
        waitElement(checkoutPage.getItemCount(), 10);
        assertThat(checkoutPage.getItemCount().isDisplayed(), equalTo(true));
        assertThat(checkoutPage.getItemCount().getText(), equalTo(count));
        logger.info("Verified item count successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Verified item count successfully");
    }

    /**
     * Method Clicks on ADD PAYMENT button (Note: NOT WORKING)
     */
    public void clickOnAddPaymentButtonOnCardDetails() {
        waitElement(checkoutPage.getAddPaymentButton(), 15);
        checkoutPage.getAddPaymentButton().click();
        logger.info("ADD PAYMENT button clicked Successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "ADD PAYMENT button clicked Successfully");
    }

    /**
     * Verifies the new order listing page
     * App: Sandbox
     */
    public void verifyNewOrderdeatailsPage() {
        assertThat(myOrdersPage.getOrderIDOnNewOrderDetails().isDisplayed(), equalTo(true));
        assertThat(myOrdersPage.getOrderItemOnNewOrderDetails().isDisplayed(), equalTo(true));
        assertThat(myOrdersPage.getOrderImageOnNewOrderDetails().isDisplayed(), equalTo(true));
        assertThat(myOrdersPage.getOrderPriceOnNewOrderDetails().isDisplayed(), equalTo(true));
        assertThat(myOrdersPage.getOrderQuantityOnNewOrderDetails().isDisplayed(), equalTo(true));
        logger.info("New order details page verified");
        ExtentTestManager.getTest().log(LogStatus.PASS, "New order details page verified");
    }

    /**
     * Click on hompage from hamburger
     * App: Sandbox
     */
    public void clickOnHomePageFromHamburger() {
        waitElement(drawerPage.getHomePage(), 30);
        drawerPage.getHomePage().click();
        logger.info("Clicked on hompage successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Clicked on hompage successfully");
    }

    /**
     * Close address pop
     * App: Sandbox
     */
    public void closeAddressPopup() {
        waitElement(addressPage.getCloseAddressPopup(), 10);
        addressPage.getCloseAddressPopup().click();
        logger.info("Address popup closed successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Address popup closed successfully");
    }

    /**
     * Close popup for android
     * App: Sandbox
     */
    public void clickOnOkPopup() {
        waitElement(addressPage.getOkPopup(), 30);
        addressPage.getOkPopup().click();
        logger.info("Address popup closed successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Address popup closed successfully");
    }

    /**
     * App Name: Sandbox
     * Verify Add to cart button on PDP
     */
    public void isAddToCartButtonVisibleOnPdp() {
        waitElement(pDpPage.getAddToCartButtonOnPDP(), 3);
        assertThat(pDpPage.getAddToCartButtonOnPDP().isDisplayed(), equalTo(true));
        logger.info("Verified add to cart button on pdp successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Verified add to cart button on pdp successfully");
    }

    /**
     * Methods selected variant from pdp based string
     */
    public void selectVariantFromPDP(String variant) {
        String variantXPath;
        if (isPlatformNameAndroid) {
            variantXPath = "//android.widget.TextView[@text = '" + variant + "']";
        } else {
            variantXPath = "//XCUIElementTypeStaticText[@name='" + variant + "']";
        }
        MobileElement variantPath = mobileDriver.findElement(By.xpath(variantXPath));
        assertThat(variantPath.isDisplayed(), equalTo(true));
        variantPath.click();
        logger.info(variant + " variant selected from pdp successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, variant + " variant selected from pdp successfully");
    }

    /**
     * Method verifies variant on cart page
     */
    public void verifyVariantOnCartPage(String variant) {
        waitFor(1.6);
        waitElement(cartPage.getVariantCartPage(), 7);
        assertThat(cartPage.getVariantCartPage().getText().trim(), equalTo(variant));
        logger.info("Verify variant on cart page successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Verify variant on cart page successfully");
    }

    /**
     * Method verifies product price on PLP
     */
    public void isProductPriceOnPLP() {
        waitElement(pLpPage.getProductPriceListInPLP(), 7);
        assertThat(pLpPage.getProductPriceListInPLP().isDisplayed(), equalTo(true));
        assertThat(pLpPage.getProductPriceListInPLP().getText(), containsString("Rs. "));
        assertThat(pLpPage.getProductPriceListInPLP().getText(), containsString(".00"));
        logger.info("Verify product price on PLP successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Verify product price on PLP successfully");
    }

    /**
     * this method verify product title PLP
     */
    public void isProductTitleOnPLPDisplay(String product) {
        if (isPlatformNameAndroid) {
            waitElement(pLpPage.getPlpPageTitle(), 10);
            assertThat(pLpPage.getPlpPageTitle().isDisplayed(), equalTo(true));
        } else {
            waitElement(pLpPage.getPlp1stProduct(), 10);
            assertThat(pLpPage.getPlp1stProduct().isDisplayed(), equalTo(true));
        }
        boolean b = pLpPage.getPlp1stProduct().getText().contains(product) || pLpPage.getPlp1stProduct().getText().contains("Jacket");
        if (b == false) {
            Assert.fail("Unable to verify product title PLP");
        }
        logger.info("Dress Collection PLP verify successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "product title on PLP verify successfully");
    }

    /**
     * Method click on cart on hamburger menu
     */
    public void clickOnCartHamburgerMenu() {
        waitElement(homePage.getCartOnHamMenu(), 7);
        assertThat(homePage.getCartOnHamMenu().isDisplayed(), equalTo(true));
        homePage.getCartOnHamMenu().click();
        logger.info("click on cart on hamburger menu successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "click on cart on hamburger menu successfully");
    }

    /**
     * This method Verify my profile under hamburger menu
     */
    public void isMyProfileDispalyed() {
        waitElement(homePage.getMyProfileOnHamburger(), 30);
        assertThat(homePage.getMyProfileOnHamburger().isDisplayed(), equalTo(true));
        assertThat(homePage.getMyProfileOnHamburger().getText(), equalTo("My Profile"));
        logger.info("my profile verified under hamburger menu  successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "my profile verified under hamburger menu  successfully");
    }

    /**
     * This method Verify login button under hamburger menu
     */
    public void isUserLoginDispalyed(String text) {
        waitElement(loginPage.getSignNow(), 30);
        assertThat(loginPage.getSignNow().isDisplayed(), equalTo(true));
        assertThat(loginPage.getSignNow().getText().trim(), equalTo(text));
        logger.info("verify user text " + text + " under hamburger menu successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "verify user text " + text + " under hamburger menu successfully");
    }

    /**
     * This method Verify my orders page when no order is placed
     */
    public void isNoOrdersPlacedTextDisplayed() {
        waitElement(myOrdersPage.getNoOrderPlacedYetOnMyOrderSection(), 10);
        logger.info("No order placed text appears successfully on myorders page");
        ExtentTestManager.getTest().log(LogStatus.PASS, "No order placed text appears successfully on myorders page");
    }

    /**
     * This method is used to click back button on edit profile page
     */

    public void clickBackButtonOnEditProfile() {
        waitElement(myAccount.getBackButtonOnEditProfilePage(), 3);
        assertThat(myAccount.getBackButtonOnEditProfilePage().isDisplayed(), equalTo(true));
        myAccount.getBackButtonOnEditProfilePage().click();
        logger.info("clicked on back button on edit profile page successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "clicked on back button on edit profile page successfully");
    }

    /**
     * This method is used to check reorder button functionality on order deatils page
     */
    public void clickOnReorderButton() {
        waitElement(myOrdersPage.getReorderButton(), 30);
        assertThat(myOrdersPage.getReorderButton().isDisplayed(), equalTo(true));
        myOrdersPage.getReorderButton().click();
        logger.info("Clicked on Reorder Button");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Clicked on Reorder Button");
    }

    /**
     * This method is used to check Track Order button fuctionality on order deatils page
     */
    public void clickOnTrackOrderButton() {
        waitElement(myOrdersPage.getTrackOrderButton(), 30);
        assertThat(myOrdersPage.getTrackOrderButton().isDisplayed(), equalTo(true));
        myOrdersPage.getTrackOrderButton().click();
        logger.info("Clicked on Track Order Button");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Clicked on Track Order Button");
    }

    /**
     * This method is used to verify Track Order page on order details page
     */
    public void verifyTrackOrderPage() {
        waitElement(myOrdersPage.getPaymentTextOnTrackOrder(), 30);
        waitElement(myOrdersPage.getFulfillmentTextOnTrackOrder(), 30);
        logger.info("Track Order page verified successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Track Order page verified successfully");
    }

    /**
     * This method is used to verify Order listing page
     */
    public void verifyNewOrderListingPage() {
        waitElement(myOrdersPage.getMyOrderText(), 10);
        assertThat(myOrdersPage.getMyOrderText().isDisplayed(), equalTo(true));
        assertThat(myOrdersPage.getCartIcon().isDisplayed(), equalTo(true));
        logger.info("New order listing page verified successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "New order listing page verified successfully");
    }

    /**
     * This method is used to invoke Add to cart popup from PLP
     */
    public void clickOnAddToCartIconOnPlp() {
        waitElement(pLpPage.getAddToCartIconOnPlp(), 10);
        pLpPage.getAddToCartIconOnPlp().click();
        logger.info("Click Add to cart icon successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Click Add to cart icon successfully");
    }

    /**
     * This method is used to verify Add to cart popup from PLP
     */
    public void verifyAddToCartPopupOnPlp() {
        waitElement(pLpPage.getAddToCartButtonOnAddToCartPopup(), 10);
        assertThat(pLpPage.getAddToCartButtonOnAddToCartPopup().isDisplayed(), equalTo(true));
        assertThat(pLpPage.getSelectSizeTextOnAddToCartPopup().isDisplayed(), equalTo(true));
        logger.info("Verified Add to cart Popup successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Verified Add to cart Popup successfully");
    }

    /**
     * This method is used to select L varient from add to cart popup
     */
    public void clickOnLVarientFromAddToCartPopupOnPlp() {
        waitElement(pLpPage.getSelectLVarientFromAddToCartPopup(), 10);
        assertThat(pLpPage.getSelectLVarientFromAddToCartPopup().isDisplayed(), equalTo(true));
        pLpPage.getSelectLVarientFromAddToCartPopup().click();
        logger.info("selected L varient from add to cart popup Successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "selected L varient from add to cart popup Successfully");
    }

    /**
     * This method is used to click on Add to cart button from add to cart popup
     */
    public void clickOnAddToCartButtonFromAddToCartPopupOnPlp() {
        waitElement(pLpPage.getAddToCartButtonOnAddToCartPopup(), 10);
        assertThat(pLpPage.getAddToCartButtonOnAddToCartPopup().isDisplayed(), equalTo(true));
        pLpPage.getAddToCartButtonOnAddToCartPopup().click();
        logger.info("clicked on Add to cart button from add to cart popup Successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "clicked on Add to cart button from add to cart popup Successfully");
    }

    /**
     * This method is used to cart icon with no items in it.
     */
    public void veifyCartIconWithNoItemAdded() {
        waitElement(homePage.getCartIconForSandbox(), 10);
        assertThat(homePage.getCartIconForSandbox().isDisplayed(), equalTo(true));
        logger.info("Verified that cart is empty");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Verified that cart is empty");
    }

    /**
     * This method is verify hamburger menu page
     */

    public void isHamburgerMenuPageDispalyed() {
        waitElement(homePage.getCategoryOnHamburger(), 10);
        assertThat(homePage.getCartOnHamMenu().isDisplayed(), equalTo(true));
        assertThat(homePage.getMyProfileOnHamburger().isDisplayed(), equalTo(true));
        assertThat(homePage.getCartOnHamMenu().isDisplayed(), equalTo(true));
        assertThat(homePage.getMoreOnHamburger().isDisplayed(), equalTo(true));
        logger.info("Hamburger Menu Page Verified Successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Hamburger Menu Page Verified Successfully");
    }

    /**
     * This method verify Add New Address button On Address Page
     */
    public void isAddNewAddressButtonDisplayed() {
        waitElement(addressPage.getAddNewAddressButton(), 9);
        assertThat(addressPage.getAddNewAddressButton().isDisplayed(), equalTo(true));
        logger.info(" verify Add New Address button Successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "verify Add New Address button Successfully");
    }

    /**
     * Check Login button on webveiw checkout page
     */
    public void clickLoginButtonOnWebviewCheckout() {
        waitElement(checkoutPage.getLoginOnWebviewCheckout(), 10);
        assertThat(checkoutPage.getLoginOnWebviewCheckout().isDisplayed(), equalTo(true));
        checkoutPage.getLoginOnWebviewCheckout().click();
        logger.info("Click on log in button successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Click on log in button successfully");
    }

    public void checkTotalPayableAmountAfterIncreasingQuantity(String total) {
        waitElement(cartPage.getTotalAmount(), 10);
        String qty = cartPage.getProductsQuantity().getText();
        String totalAmt = cartPage.getTotalAmount().getText().replaceAll(",", ".");
        float totalPrice = (Float.valueOf(total) * (Float.valueOf(qty)));
        String totalAmtAfterCal = String.format("%.2f", totalPrice);
        logger.info(cartPage.getProductsQuantity().getText());
        waitElement(cartPage.getTotalAmount(), 10);
        assertThat(totalAmt, containsString(totalAmtAfterCal));
        logger.info("Verified total payable amount after updating the item quantity");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Verified total payable amount after updating the item quantity");
    }

    public String fetchTotalPayableFromCartPage() {
        String totalPayable[] = null;
        assertThat(cartPage.getTotalAmount().isDisplayed(), equalTo(true));
        totalPayable = cartPage.getTotalAmount().getText().split(" ");
        logger.info("Fetched Total Payable from cart page successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Fetched Total Payable from cart page successfully");
        if (isPlatformNameAndroid) {
            return totalPayable[2].replace(",", ".");
        } else {
            return totalPayable[1].replace(",", ".");
        }
    }

    /**
     * Check add payment button on native checkout page
     */
    public void clickOnAddPaymentButton() {
        waitElement(checkoutPage.getAddPaymentButton(), 10);
        checkoutPage.getAddPaymentButton().click();
        logger.info("Click on add payment button successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Click on add payment button successfully");
    }

    /**
     * App Name: Neovo Commerce
     * Method verify Add more from favorite on cart page
     */
    public void verifyAddMoreFromFavoriteButtonInCartPage() {
        waitElement(cartPage.getAddMoreFromFavoriteButton(), 10);
        assertThat(cartPage.getAddMoreFromFavoriteButton().isDisplayed(), equalTo(true));
        logger.info("Add more from favorite button verified on Cart Page");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Add more from favorite button verified on Cart Page");
    }

    /**
     * App Name: Neovo Commerce
     * Method verify Cart Header on cart page
     */
    public void verifyCartHeaderOnCartPage(String text) {
        waitElement(cartPage.getCartHeaderTextOnCartPage(), 9);
        assertThat(cartPage.getCartHeaderTextOnCartPage().getText(), is(equalTo(text)));
        logger.info("Cart Header verified on Cart Page");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Cart Header verified on Cart Page");
    }

    /**
     * App Name: Neovo Commerce
     * Method verify discount code error on cart page
     */
    public void verifyDiscountCodeErrorOnCartPage(String errorMessage) {
        waitElement(cartPage.getDiscountCodeErrorMessage(), 9);
        assertThat(cartPage.getDiscountCodeErrorMessage().getText(), is(equalTo(errorMessage)));
        logger.info("Invalid discount code error message on cart page verified successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Invalid discount code error message on cart page verified successfully");
    }

    /**
     * This method is used to click on Yes Go Aheadbutton on Popup
     */
    public void clickOngetYesGoAheadButton() {
        waitElement(myAccount.getYesGoAhead(), 6);
        assertThat(myAccount.getYesGoAhead().isDisplayed(), equalTo(true));
        myAccount.getYesGoAhead().click();
        logger.info("clicked Yes Go Ahead Button on popup successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "clicked Yes on popup successfully");
    }

    /**
     * App Name: Sandbox
     * Method clicks on Search button on the IOS keyboard
     */
    public void clickOnCheckoutBackButton() {
        waitElement(checkoutPage.getCheckoutBack(), 6);
        assertThat(checkoutPage.getCheckoutBack().isDisplayed(), equalTo(true));
        checkoutPage.getCheckoutBack().click();
        logger.info("Back button successfully clicked");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Back button successfully clicked");
    }

    /**
     * Clicks on Not Now button if password save prompt is shown on iOS devices
     */
    public void clickNotNowOnPasswordSaveIOSPrompt() {
        try {
            waitElement(loginPage.getNotNowButton(), 10);
            loginPage.getNotNowButton().click();
            logger.info("Not Now button clicked on Password Save Prompt");
            ExtentTestManager.getTest().log(LogStatus.PASS, "Not Now button clicked on Password Save Prompt");
        } catch (Exception e) {
            logger.info("Password Save Prompt not displayed");
        }
    }

    /**
     * Click on share button on PDP page
     */
    public void clickOnShareButtonOnPDP() {
        waitElement(pDpPage.getShareButton(), 10);
        pDpPage.getShareButton().click();
        logger.info("Click on share button successfully on PDP Page");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Click on share button successfully on PDP Page");
    }

    /**
     * Verify Check out button on Share page.
     * This method will click on checkout button because some samsung devices show pop-up on share page so need to disappear that pop-up.
     */
    public void clickOnCheckOutButtonOnSharePopUp() {
        waitElement(pDpPage.getCheckOutIconOnSharePage(), 3);
        pDpPage.getCheckOutIconOnSharePage().click();
        logger.info("Click on Check out button successfully on Share Page");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Click on Check out button successfully on Share Page");
    }

    /**
     * Verify copy button on Share page
     */
    public void checkCopyButtonOnSharePopUp() {
        waitElement(pDpPage.getCopyButtonOnSharePage(), 5);
        assertThat(pDpPage.getCopyButtonOnSharePage().isDisplayed(), equalTo(true));
        logger.info("Copy button verified on Share Page");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Copy button verified on Share Page");
    }

    /**
     * Verify that the share page has disappeared
     */
    public void clickOnCloseButtonOnSharePopUp() {
        waitElement(pDpPage.getCloseSharePage(), 5);
        pDpPage.getCloseSharePage().click();
        logger.info("Click on Close button successfully on Share Page");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Click on Close button successfully on Share Page");
    }

    /**
     * Click on share button on homepage
     */
    public void clickOnShareButtonOnHomePage() {
        waitElement(homePage.getShareButtonOnHomepage(), 5);
        homePage.getShareButtonOnHomepage().click();
        logger.info("Click on Share button successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Click on Share button successfully");
    }

    /**
     * Verify Share button Popup
     */
    public void verifySharePopupOnHomePage() {
        assertThat(homePage.getSharePopOnHomepage().isDisplayed(), equalTo(true));
        assertThat(homePage.getNearbyShare().isDisplayed(), equalTo(true));
        logger.info("Verified Share Popup successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Verified Share Popup successfully");
    }

    /**
     * Verify Add to cart button on homepage
     */
    public void verifyAddToCartButtonVisibleOnHomepage() {
        waitElement(homePage.getAddToCartButtonOnHomepage(), 5);
        logger.info("Verified Add to cart button successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Verified Add to cart button successfully");
    }

    /**
     * click on favorite button on homepage
     */
    public void clickOnFavoriteButtonOnHomepage() {
        waitElement(homePage.getFavoriteButtonOnHomepage(), 5);
        homePage.getFavoriteButtonOnHomepage().click();
        logger.info("Verified favorite button successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Verified favorite button successfully");
    }

    /**
     * Click on Multitab on homepage
     */
    public void clickOnMultitabFashion() {
        if (isPlatformNameAndroid) {
            waitElement(homePage.getMultitabOnHomepage(), 5);
        }
        homePage.getMultitabOnHomepage().click();
        logger.info("Click on Multitab on homepage");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Click on Multitab on homepage");
    }

    /**
     * Click on Multitab on homepage
     */
    public void clickOnDynamicShelfOnHomepage() {
        waitElement(homePage.getDynamicShelfOnHomepage(), 5);
        homePage.getDynamicShelfOnHomepage().click();
        logger.info("Clicked successfully on Dynamic shelf from homepage");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Clicked successfully on Dynamic shelf from homepage");
    }

    /**
     * Click on Multitab on homepage
     */
    public void clickOnAnimatedPromoBannerOnHomepage() {
        waitElement(homePage.getDynamicShelfOnHomepage(), 5);
        homePage.getDynamicShelfOnHomepage().click();
        logger.info("Clicked successfully on Animated Promo Banner from homepage");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Clicked successfully on Animated Promo Banner from homepage");
    }

    /**
     * Verify product in the cart page
     */
    public void verifyProductInCartpage(String productName) {
        waitElement(cartPage.getFirstProductTitleOnCartPaga(), 5);
        Assert.assertTrue(cartPage.getFirstProductTitleOnCartPaga().getText().contains(productName));
        logger.info("Product is verified on cart page");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Product is verified on cart page");
    }

    /**
     * click on add to cart button on homepage
     */
    public void clickOnAddToCartButtonOnHomepage() {
        waitElement(homePage.getAddToCartButtonOnHomepage(), 5);
        homePage.getAddToCartButtonOnHomepage().click();
        logger.info("Click on Add to cart button successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Click on Add to cart button successfully");
    }

    /**
     * Verify login button on login page
     */
    public void verifyLoginButtonOnLoginPage() {
        waitElement(loginPage.getLoginButtonOnLoginPage(), 5);
        logger.info("Verifed Login button successfully on login page");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Verifed Login button successfully on login page");
    }

    /**
     * Verify register button on login page
     */
    public void verifyRegisterButtonOnLoginPage() {
        waitElement(loginPage.getRegisterButton(), 5);
        logger.info("Verifed register button successfully on login page");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Verifed register button successfully on login page");
    }

    /**
     * verify discount on searched products
     */
    public void verifyDiscountOnSearchPage() {
        waitElement(searchPage.getDiscountOnSearchPage(), 5);
        logger.info("Verifed discount for searched products");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Verifed discount for searched products");
    }

    /**
     * Verify Address from Contact US page for Android as well as iOS.
     */
    public void checkAddressOnContactUsPage() {
        assertThat(morePage.getContactUsAddressOnContactUsPage().isDisplayed(), equalTo(true));
        logger.info("Contact Us verified successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Contact Us verified successfully");
    }

    /**
     * This method is used to click on shipping address on Native checkout page
     */
    public void clickAddAddressIconOnNativeCheckoutPage() {
        waitElement(cartPage.getAddAddressIconNativeCheckoutPage(), 3);
        cartPage.getAddAddressIconNativeCheckoutPage().click();
        logger.info("Clicked on Add Address icon on Checkout Page Successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Clicked on Add Address icon on Checkout Page Successfully");
    }

    /**
     * This method is used to click on Save button on Address checkout page
     */

    public void clickSaveButtonOnAddressPageCheckoutPage() {
        waitElement(cartPage.getClickSaveButton(), 3);
        cartPage.getClickSaveButton().click();
        logger.info("Address Added Successfully on Checkout Page");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Address Added Successfully on Checkout Page");
    }

    /**
     * Method verifies if the Info section is visible on More Page
     */
    public void verifyInfoSectionVisibleOnMorePage() {
        waitElement(morePage.getInfoSectionOn(), 5);
        assertThat(morePage.getInfoSectionOn().isDisplayed(), equalTo(true));
        logger.info("Info Section is displayed on More Page");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Info Section is displayed on More Page");
    }

    /**
     * Method verifies all the elements in the Login Page
     * App: Neovo
     */
    public void verifyLoginPageUI() {
        waitElement(loginPage.getLoginTextOnLoginPage(), 10);
        assertThat(loginPage.getLoginTextOnLoginPage().isDisplayed(), equalTo(true));
        assertThat(loginPage.getEmailTextField().isDisplayed(), equalTo(true));
        assertThat(loginPage.getPasswordTextField().isDisplayed(), equalTo(true));
        assertThat(loginPage.getForgotPassword().isDisplayed(), equalTo(true));
        assertThat(loginPage.getLoginButtonOnLoginPage().isDisplayed(), equalTo(true));
        assertThat(loginPage.getRegisterButton().isDisplayed(), equalTo(true));
        logger.info("Login page elements verified successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Login page elements verified successfully");

    }


    /**
     * Method to click on order summary on checkout page
     * App: Neovo
     */
    public void clickOnOrderSummary() {
        waitElement(checkoutPage.getShowOrderSummary(), 7);
        checkoutPage.getShowOrderSummary().click();
        logger.info("Click on Show order Summary Successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Click on Show order Summary Successfully");
    }

    /**
     * Method to apply discount code on checkout page
     * App: Neovo
     */

    public void applyDiscountOrGiftCardOnCheckoutPage(String discountcode) {
        waitElement(checkoutPage.getDiscountOnCheckoutPage(), 3);
        checkoutPage.getDiscountOnCheckoutPage().click();
        checkoutPage.getDiscountOnCheckoutPage().sendKeys(discountcode);
        logger.info("User able to write Discount Code/Gift Card successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "User able to write Discount Code/Gift Card successfully");
    }

    /**
     * Method to click arrow to apply discount code
     * App: Neovo
     */

    public void clickArrowToApplyDiscountCode() {
        waitElement(checkoutPage.getClickArrowOnDiscountPage(), 3);
        checkoutPage.getClickArrowOnDiscountPage().click();
        logger.info("User clicked on Arrow button successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "User clicked on Arrow button successfully");
    }

    /**
     * Method to verify the discount code on checkout page
     * App: Neovo
     */

    public void verifyDiscountAppliedOnCheckoutPage(String Discount) {
        waitElement(checkoutPage.getDiscountlabel(), 10);
        Assert.assertTrue(checkoutPage.getDiscountlabel().getText().contains(Discount));
        logger.info("Verified Discount Code on Checkout Page Successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Verified Discount Code on Checkout Page Successfully");
    }

    /**
     * Method to verify the GiftCard coupon on checkout page
     * App: Neovo
     */
    public void verifyGiftCardVisibleOnCheckoutPage() {
        waitElement(checkoutPage.getGiftLabel(), 3);
        assertThat(checkoutPage.getGiftLabel().isDisplayed(), equalTo(true));
        logger.info("Gift card displayed on checkout page successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Gift card displayed on checkout page successfully");
    }


    /**
     * Method redirects user to the provided Deeplink url
     */
    public void openDeepLinkUrlWithoutAppTermination(DeeplinkPathEnum pathToRedirectDeeplink) {
        if (isPlatformNameAndroid) {
            mobileDriver.executeScript("mobile:deepLink",
                    ImmutableMap.of(
                            "url", readconfig.getBaseUrlForAndroid() + pathToRedirectDeeplink.getValue(),
                            "package", readconfig.getPackageName()
                    ));
        } else {
            String iosUrl = readconfig.getBaseUrlForIos() + pathToRedirectDeeplink.getValue();
            launchSafariApp();
            verifySafariHomePage();
            clickOnSafariSeacrhBar();
            enterTextInSafariSeacrhBar(iosUrl);
            clickOnGoFromKeyword();
        }
    }

    /**
     * This method verifies if search icon is present on the Home Page
     */
    public void verifySearchOptionPresentOnHomePage() {
        waitElement(homePage.getSearchProductsText(), 10);
        logger.info("Verified search option on Home Page Successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Verified search option on Home Page Successfully");
    }

    /**
     * This method click on login button when user enters invalid login details
     * App name: Neovo-commerce
     */
    public void clickLoginForInvalidLoginDetails() {
        if (appName.equalsIgnoreCase("steve")) {
            assertThat(loginPage.getSubmitLogin().isDisplayed(), equalTo(true));
            loginPage.getSubmitLogin().click();
        } else {
            assertThat(loginPage.getLoginButtonOnLoginPage().isDisplayed(), equalTo(true));
            loginPage.getLoginButtonOnLoginPage().click();
        }
        logger.info("Click on Login button successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Click on Login button successfully");
    }

    /**
     * This method verify error message on login page
     * App name: Neovo-commerce
     */
    public void verifyErrorMessage(String errorMessage) {
        Assert.assertEquals(loginPage.getErrorMessage().getText(), errorMessage);
        logger.info("Error Messsage {" + errorMessage + "} verified successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Error Messsage {" + errorMessage + "} verified successfully");
    }

    /**
     * Method clicks on tops collection from home page
     */
    public void clickOnTopsCollection() {
        waitElement(homePage.getTopsCollection(), 6);
        homePage.getTopsCollection().click();
        logger.info("Clicked on tops collection from home page successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Clicked on tops collection  from home page successfully");
    }

    /**
     * This Method select french language from more page
     */
    public void selectFrenchLanguage() {
        waitElement(morePage.getFrenchLanguagePopup(), 6);
        morePage.getFrenchLanguagePopup().click();
        logger.info("french language selected from more page successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "french language selected from more page successfully");
    }

    /**
     * This Method verify language from more page
     */
    public void isLanguageDisplayed(String lang) {
        waitElement(morePage.getFrench(), 6);
        assertThat(morePage.getFrench().getText(), containsString(lang));
        logger.info(lang + " language verified from more page successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, lang + "flanguage verifiede from more page successfully");
    }


    /**
     * This Method verify VARIANT display or not
     */
    public void isSizeVariantNotDisplayed(int size) {
        String xpath;
        if (isPlatformNameAndroid) {
            xpath = "//android.widget.TextView[@text='Select Size']";
        } else {
            xpath = "//XCUIElementTypeStaticText[@name='Select Size']";
        }
        assertThat(mobileDriver.findElements(By.xpath(xpath)).size(), equalTo(size));
        logger.info(size + " hide variant verified successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, size + " hide variant verified successfully");
    }

    /**
     * This Method Particular Size Variant verify VARIANT display or not
     */
    public void isParticularSizeVariantNotDisplayed(String text, int size) {
        String xpath;
        if (isPlatformNameAndroid) {
            xpath = "//android.widget.TextView[@text='" + text + "']";
        } else {
            xpath = "//XCUIElementTypeStaticText[@name='" + text + "']";
        }
        assertThat(mobileDriver.findElements(By.xpath(xpath)).size(), equalTo(size));
        logger.info(size + " hide Particular variant verified successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, size + " hide variant Particular verified successfully");
    }

    /**
     * This Method verify App logo not displaying on homepage
     */
    public void isAppLogoNotDisplayed(int size) {
        String logoxpath;
        if (isPlatformNameAndroid) {
            logoxpath = "//android.widget.ImageView[contains(@resource-id,'app_logo')]";
        } else {
            logoxpath = "//XCUIElementTypeNavigationBar[@name='TopTabBarView']/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeImage";
        }
        assertThat(mobileDriver.findElements(By.xpath(logoxpath)).size(), equalTo(size));
        logger.info(size + " App logo not displaying on homepage verified successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, size + " App logo not displaying on homepage verified successfully");
    }

    /**
     * This Method verify Sold Out Product not displaying on homepage
     */
    public void isSoldOutProductNotDisplayed(int size) {
        String xpath;
        if (isPlatformNameAndroid) {
            xpath = "//android.widget.TextView[@text='Sold Out']";
        } else {
            xpath = "//XCUIElementTypeStaticText[@name='Sold Out']";
        }
        assertThat(mobileDriver.findElements(By.xpath(xpath)).size(), equalTo(size));
        logger.info(size + " Sold Out Product not displaying on homepage verified successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, size + " Sold Out Product not displaying on homepage verified successfully");
    }


    /**
     * This Method verify vendor name from pdp page successfully
     */
    public void isVendorNameDisplayed(String vendorName) {
        waitElement(pDpPage.getVendorName(), 6);
        assertThat(pDpPage.getVendorName().getText(), containsString(vendorName));
        logger.info(vendorName + "verified as vendor name from pdp page successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, vendorName + " verified as vendor name from pdp page successfully");
    }

    /**
     * Method clicks on currency from more page
     */
    public void clickOnCurrency() {
        waitElement(morePage.getCurrency(), 6);
        morePage.getCurrency().click();
        logger.info("Clicked on currency from more page successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Clicked on currency from more page successfully");
    }

    /**
     * This Method select usd currency from more page
     */
    public void selectUSD() {
        waitElement(morePage.getUsDollar(), 6);
        morePage.getUsDollar().click();
        logger.info("USD currency selected from more page successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "USD currency selected from more page successfully");
    }

    /**
     * Method verifies all the elements in the Login Page
     * App: Neovo
     */
    public void isSelectedCurrencyDisplayedOnPLP(String currency) {
        waitElement(pLpPage.getProductPricePLPDollar(), 10);
        assertThat(pLpPage.getProductPricePLPDollar().getText(), containsString(currency));
        logger.info(currency + " currency verified on plp successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, currency + " currency verified on plp successfully");

    }

    /**
     * Click on discount remove button on checkout page
     */
    public void clickOnDiscountRemoveButtonOnCheckoutPage() {
        waitElement(checkoutPage.getDiscountRemoveButton(), 6);
        checkoutPage.getDiscountRemoveButton().click();
        logger.info("Successfully click on Discount Remove button on Checkout page");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Successfully click on Discount Remove button on Checkout page");
    }

    /**
     * Method to verify the discount code remove on checkout page
     */

    public void isDiscountCodeDisplayed(int size) {
        String xpath;
        if (isPlatformNameAndroid) {
            xpath = "//android.widget.TextView[@text='AUTOMATE1']";
        } else {
            xpath = "(//XCUIElementTypeStaticText[@name='AUTOMATE1'])[2]";
        }
        assertThat(mobileDriver.findElements(By.xpath(xpath)).size(), equalTo(size));
        logger.info("Discount code validate with " + size + " element/locator on checkout page successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Discount code validate with " + size + " element/locator on checkout page successfully");
    }

    /**
     * Methods to fetch the product price on cart page
     */
    public void verifyFirstProductPriceOncartPage(String productPrice) {
        waitElement(cartPage.getFirstProductPriceOnCartPage(), 5);
        Assert.assertEquals(cartPage.getFirstProductPriceOnCartPage().getText(), productPrice);
        logger.info("Product Price on Cart Verified successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Product Price" + productPrice + "on Cart Verified successfully");
    }

    public void clickAddAddressOnCheckoutPageNative() {
        waitElement(checkoutPage.getAddAddressOnCheckoutNativePage(), 3);
        checkoutPage.getAddAddressOnCheckoutNativePage().click();
        logger.info("User clicked on Add Address in Native checkout Successfully ");
        ExtentTestManager.getTest().log(LogStatus.PASS, "User clicked on Add Address in Native checkout Successfully ");
    }

    public void enterFirstnameAccountPage(String firstName) {
        assertThat(myAccount.getFirstNameAccountPage().isDisplayed(), equalTo(true));
        myAccount.getFirstNameAccountPage().sendKeys(firstName);
        logger.info(firstName + " as First name entered Successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, firstName + " as First name entered Successfully");
    }

    public void enterLastnameAccountPage(String lastName) {
        assertThat(myAccount.getLastNameAccountPage().isDisplayed(), equalTo(true));
        myAccount.getLastNameAccountPage().sendKeys(lastName);
        logger.info(lastName + " as Last name entered Successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, lastName + " as Last name entered Successfully");
    }

    /**
     * Method use to verify image stickers on searched products
     */
    public void verifyImageStickerOnSearchPage() {
        assertThat(searchPage.getImageStickerOnSearchPage().isDisplayed(), equalTo(true));
        logger.info("Image sticker appeared on search product successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Image sticker appeared on search product successfully");
    }

    /**
     * Method verifies all the elements in the Ct-Notification Page
     */
    public void isCtNotificationPageDisplayed() {
        waitElement(myAccount.getCtPnLink(), 10);
        assertThat(myAccount.getPnTitleOnCtInboxPage().isDisplayed(), equalTo(true));
        assertThat(myAccount.getPnMessageOnCtInboxPage().getText(), equalTo("Added to cart"));
        assertThat(myAccount.getPnTimestampOnCtInboxPage().isDisplayed(), equalTo(true));
        logger.info("Ct-Notification Page verified successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Ct-Notification Page verified successfully");
    }

    /**
     * This method Click on Link button on ct-notification page
     */
    public void clickOnCtNotificationPnLink() {
        waitElement(myAccount.getCtPnLink(), 6);
        myAccount.getCtPnLink().click();
        logger.info("Clicked on Ct-Notification Pn Link Successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Clicked on Ct-Notification Pn Link Successfully");
    }

    /**
     * Method verifies before login message on profile Page
     */
    public void isBeforeLoginMessageDisplayedOnProfilePage() {
        waitElement(myAccount.getBeforeLoginMessage(), 10);
        assertThat(myAccount.getBeforeLoginMessage().isDisplayed(), equalTo(true));
        logger.info("before login message " + myAccount.getBeforeLoginMessage().getText() + " verified successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "before login message " + myAccount.getBeforeLoginMessage().getText() + " verified successfully");
    }

    /**
     * Method use to verify image stickers on PLP
     */
    public void verifyImageStickerOnPLP() {
        assertThat(pLpPage.getImageStickerOnPLP().isDisplayed(), equalTo(true));
        logger.info("Image sticker appeared on PLP successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Image sticker appeared on PLP successfully");
    }

    /**
     * Method use to verify image stickers on PDP
     */
    public void verifyImageStickerOnPDP() {
        assertThat(pDpPage.getImageStickerOnPDP().isDisplayed(), equalTo(true));
        logger.info("Image sticker appeared on PDP successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Image sticker appeared on PDP successfully");
    }

    /**
     * Method use to invoke Jacket and Jumpsuit collection
     */
    public void clickOnJacketAndJumpsuitCollection() {
        assertThat(collectionPage.getJacketAndJumpSuitCollection().isDisplayed(), equalTo(true));
        collectionPage.getJacketAndJumpSuitCollection().click();
        logger.info("Click on Jacket and Jump suit collection successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Click on Jacket and Jump suit collection successfully");
    }

    /**
     * Method use to verify image stickers on Homepoge
     */
    public void verifyImageStickerOnHomepage() {
        assertThat(homePage.getImageStickerOnHomepage().isDisplayed(), equalTo(true));
        logger.info("Image sticker appeared on Homepage successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Image sticker appeared on Homepage successfully");
    }

    /**
     * Method returns the product title on PDP
     */
    public String getProductTitleFromPDP() {
        waitElement(pDpPage.getProductTitleOnPdp(), 10);
        String productTitle = pDpPage.getProductTitleOnPdp().getText();
        logger.info("Featched Product Title from PDP successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Featched Product Title from PDP successfully");
        return productTitle;
    }

    /* Method increases count of product on PDP
     * @param quantity
     */
    public void increaseProductQuantityOnPDP(int quantity) {
        if (isPlatformNameAndroid) {
            scrollToText("Select Quantity");
        } else {
            swipe(250, 666, 250, 370);
        }
        assertThat(pDpPage.getIncreaseQuantityButton().isDisplayed(), equalTo(true));
        while (quantity > 0) {
            pDpPage.getIncreaseQuantityButton().click();
            waitFor(1);
            quantity--;
        }
        logger.info("Product Quantity increase successfully on PDP");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Product Quantity increase successfully on PDP");
    }

    /**
     * Method verifies Description section Header on PDP
     */
    public void verifyDescriptionSectionOnPDP() {
        if (isPlatformNameAndroid) {
            scrollToText("Select Quantity");
        } else {
            swipe(250, 666, 250, 370);
        }
        assertThat(pDpPage.getDescriptionHeader().isDisplayed(), equalTo(true));
        logger.info("Description section visibility on PDP verified");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Description section visibility on PDP verified");
    }

    /**
     * Method decreases count of product on PDP
     *
     * @param quantity
     */
    public void decreaseProductQuantityOnPDP(int quantity) {
        if (isPlatformNameAndroid) {
            scrollToText("Select Quantity");
        } else {
            swipe(250, 666, 250, 370);
        }
        assertThat(pDpPage.getDecreaseQuantityButton().isDisplayed(), equalTo(true));
        while (quantity > 0) {
            pDpPage.getDecreaseQuantityButton().click();
            waitFor(1);
            quantity--;
        }
        logger.info("Product Quantity decrease successfully on PDP");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Product Quantity decrease successfully on PDP");
    }

    /**
     * Method verifies product quantity on PDP
     */
    public void checkProductQuantityOnPDP(String quantity) {
        assertThat(pDpPage.getProductQuantity().getText(), equalTo(quantity));
        logger.info("Verified product count successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Verified product count successfully");
    }

    /**
     * This method click on home icon
     */
    public void clickOnHomeIcon() {
        waitElement(homePage.getHomeIcon(), 6);
        homePage.getHomeIcon().click();
        logger.info("Clicked on home icon Successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Clicked on home icon Successfully");
    }


    /**
     * Method verifies name on address page for sandbox
     */
    public void isAddressNameDisplayedOnAddressPage(String name) {
        waitElement(addressPage.getNameTextSandbox(), 6);
        assertThat(addressPage.getNameTextSandbox().getText(), equalTo(name));
        logger.info("Verified Name on address page successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Verified Name on address page successfully");
    }

    /**
     * App Name: Sandbox
     * Method which click on address icon from native checkout page
     */
    public void clickOnAddAddressPlusIconOnCheckoutPage() {
        assertThat(checkoutPage.getAddAddressPlusIcon().isDisplayed(), equalTo(true));
        checkoutPage.getAddAddressPlusIcon().click();
        logger.info("click on Add Address Plus Icon successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "click on Add Address Plus Icon successfully");
    }

    /**
     * App Name: Sandbox
     * This Method click on Collection Tab 2 on homepage page
     */
    public void clickOnCollectionTab2onHomepage() {
        waitElement(homePage.getCollectionIcon2(), 6);
        homePage.getCollectionIcon2().click();
        logger.info("Clicked on collection tab 2 successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Clicked on collection tab 2 successfully");
    }

    /**
     * App Name: Sandbox
     * This Method click on Collection Tab 3 on homepage page
     */
    public void clickOnCollectionTab3onHomepage() {
        waitElement(homePage.getCollectionIcon3(), 6);
        homePage.getCollectionIcon3().click();
        logger.info("Clicked on collection tab 3 successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Clicked on collection tab 3 successfully");
    }


    /**
     * Method verifies collection text on collection page
     */
    public void isSelectedCollectionDisplayed(String collectionName) {
        waitElement(collectionPage.getCollectionText(), 6);
        assertThat(collectionPage.getCollectionText().getText(), equalTo(collectionName));
        logger.info("Verified collection text on collection page successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Verified collection text on collection page successfully");
    }

    /**
     * Method invoke android app with the help of package name and activity name
     */
    public void launchAndroidApp(String appPackage, String appActivity) {
        if (browserstack) {
            try {
                Activity activity = new Activity(appPackage, appActivity);
                ((AndroidDriver) mobileDriver).startActivity(activity);
            } catch (Exception e) {
                logger.info("There is a Exception while lunching startActivity");
            }
        } else {
            String command = "am start -n " + appPackage + "/" + appActivity;
            mobileDriver.executeScript("mobile:shell",
                    ImmutableMap.of(
                            "command", command
                    ));
        }
        waitFor(5);
        logger.info("App launched with package name " + appPackage + " with app activity " + appActivity);
        ExtentTestManager.getTest().log(LogStatus.PASS, "App launched with package name " + appPackage + " with app activity " + appActivity);
    }

    /**
     * this method inoke deeplink url from browser satck
     */
    public void enterDeeplinkUrlInFirefoxbrowser(String url) {
        clickOnSearchIconFirefoxBrowser();
        waitFor(.7);
        enterUrlFirefoxBrowser(url);
        waitFor(.7);
        ((AndroidDriver) mobileDriver).pressKey(new KeyEvent(AndroidKey.ENTER));
        waitFor(1.7);
        clickOnMenuIconFirefoxBrowser();
        waitFor(.7);
        clickOnOpenInAppFirefoxBrowser();
        logger.info("Deeplink url redirect to app from browser successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Deeplink url redirect to app from browser successfully");
    }

    /**
     * Method launch Firefox Browser
     */
    public void launchFirefoxBrowser() {
        launchAndroidApp("org.mozilla.firefox", "org.mozilla.gecko.LauncherActivity");
        logger.info("launch Firefox Browser successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "launch Firefox Browser successfully");
    }

    /**
     * Method click On Search Icon Firefox Browser
     */
    public void clickOnSearchIconFirefoxBrowser() {
        waitElement(homePage.getSearchIcon(), 6);
        homePage.getSearchIcon().click();
        logger.info("click on search bar in Firefox Browser successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "click on search bar in Firefox Browser successfully");
    }

    /**
     * Method enter Url Firefox Browser
     */
    public void enterUrlFirefoxBrowser(String url) {
        waitElement(homePage.getEnterText(), 6);
        homePage.getEnterText().sendKeys(url);
        logger.info("enter deeplink url in search bar Firefox Browser successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "enter deeplink url in search bar Firefox Browser successfully");
    }

    /**
     * Method click On Menu Icon Firefox Browser
     */
    public void clickOnMenuIconFirefoxBrowser() {
        waitElement(homePage.getFirefoxMenu(), 6);
        homePage.getFirefoxMenu().click();
        logger.info("click on menu icon in Firefox Browser successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "click on menu icon in Firefox Browser Firefox Browser successfully");
    }

    /**
     * Method click On Open In App Firefox Browser
     */
    public void clickOnOpenInAppFirefoxBrowser() {
        waitElement(homePage.getOpenInApp(), 6);
        homePage.getOpenInApp().click();
        logger.info("click on open in App in Firefox Browser successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "click on open in App in Firefox Browser Firefox Browser successfully");
    }

    /*
     * Method clicks on search icon on PDP
     */
    public void clickSearchButtonOnPDPPage() {
        waitElement(pDpPage.getSearchButton(), 7);
        assertThat(pDpPage.getSearchButton().isDisplayed(), equalTo(true));
        pDpPage.getSearchButton().click();
        logger.info("Search button clicked on PDPPage successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Search button clicked on PDPPage successfully");
    }

    /**
     * Methods verifies the header of the Page
     *
     * @param headerText
     */
    public void verifyPageHeaderText(String headerText) {
        String headerXPath;
        if (isPlatformNameAndroid)
            headerXPath = "//android.widget.TextView[@text='" + headerText + "']";
        else
            headerXPath = "//XCUIElementTypeStaticText[@name='" + headerText + "']";
        MobileElement productTileElement = mobileDriver.findElement(By.xpath(headerXPath));
        assertThat(productTileElement.isDisplayed(), equalTo(true));
        logger.info("Header of the " + headerText + " page verified successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Header of the " + headerText + " page verified successfully");
    }

    /**
     * Method clicks on the home tab icon
     */
    public void clickHomeTab() {
        assertThat(homePage.getHomeTab().isDisplayed(), equalTo(true));
        homePage.getHomeTab().click();
        logger.info("Home tab clicked");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Home tab clicked");
    }

    /**
     * Methods verifies the Text on the Page
     *
     * @param text
     */
    public void verifyTextOnPage(String text) {
        String headerXPath;
        if (isPlatformNameAndroid)
            headerXPath = "//android.widget.TextView[@text='" + text + "']";
        else
            headerXPath = "//XCUIElementTypeStaticText[@name='" + text + "']";
        MobileElement productTileElement = mobileDriver.findElement(By.xpath(headerXPath));
        assertThat(productTileElement.isDisplayed(), equalTo(true));
        logger.info(text + " on page verified successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, text + " on page verified successfully");
    }

    /**
     * Method verifies if the More Page option is displayed on Hamburger Menu
     */
    public void verifyMorePageOnHamburger() {
        waitElement(homePage.getMoreOnHamburger(), 10);
        logger.info("More page displayed Successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "More page displayed Successfully");
    }

    /**
     * Methods clicks the Text on the Page
     *
     * @param text
     */
    public void clickTextOnPage(String text) {
        String headerXPath;
        if (isPlatformNameAndroid)
            headerXPath = "//android.widget.TextView[@text='" + text + "']";
        else
            headerXPath = "//XCUIElementTypeStaticText[@name='" + text + "']";
        MobileElement productTileElement = mobileDriver.findElement(By.xpath(headerXPath));
        assertThat(productTileElement.isDisplayed(), equalTo(true));
        productTileElement.click();
        logger.info(text + " on page clicked verified successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, text + " on page clicked verified successfully");
    }

    /**
     * Method verifies all the elements in the Ct-Notification Page
     */
    public void isAddressPopPageDisplayed() {
        waitElement(addressPage.getAddressPageTitle(), 10);
        assertThat(addressPage.getAddNewAddressButton().isDisplayed(), equalTo(true));
        assertThat(addressPage.getEditAddressOnAddressPage().getText(), equalTo("Edit"));
        assertThat(addressPage.getRemoveAddress().getText(), equalTo("Remove"));
        logger.info("Address Page verified successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Address Page verified successfully");
    }

    /**
     * Method verifies order confirmation page
     */
    public void isOrderConfirmationPageDisplayed() {
        if (!isPlatformNameAndroid) {
            if (isRatingPopUpDisplayed()) {
                clickNotNowOnRatingPopUp();
            }
        }
        waitElement(thankYouPage.getThankYouText(), 10);
        assertThat(thankYouPage.getTotalPayable().isDisplayed(), equalTo(true));
        assertThat(thankYouPage.getOrderConfrimText().getText(), equalTo("Your order is confirmed"));
        if (isPlatformNameAndroid) {
            scrollToText("My Order");
        } else {
            waitFor(2);
            scrollForIos();
        }
        assertThat(thankYouPage.getContinueShoppingButton().isDisplayed(), equalTo(true));
        assertThat(thankYouPage.getMyOrderOnThankYouPage().isDisplayed(), equalTo(true));
        logger.info("Order Confirmation Page verified successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Order Confirmation Page verified successfully");
    }

    /*
     * This method verifies category tab under Under Hamburger menu
     */
    public void isCategoryTabDisplayUnderHamburger() {
        waitElement(homePage.getCategoryOnHamburger(), 10);
        assertThat(homePage.getCategoryOnHamburger().getText(), equalTo("Category"));
        logger.info("Category tab under Under Hamburger menu verified successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Category tab under Under Hamburger menu verified success");
    }

    /**
     * This method removes applied discount from cart page
     */
    public void removeAppliedDiscountFromCartPage() {
        waitElement(cartPage.getRemoveDiscountFromCartPage(), 10);
        assertThat(cartPage.getRemoveDiscountFromCartPage().isDisplayed(), equalTo(true));
        cartPage.getRemoveDiscountFromCartPage().click();
        logger.info("The discount has been successfully removed from the cart page.");
        ExtentTestManager.getTest().log(LogStatus.PASS, "The discount has been successfully removed from the cart page.");
    }

    /**
     * Method use to verify image stickers on PDP
     */
    public void checkImageStickerOnOrderDetailsPage() {
        assertThat(pDpPage.getImageStickerOnPDP().isDisplayed(), equalTo(true));
        logger.info("Image sticker appeared on Order Details Page successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Image sticker appeared on Order Details Page successfully");
    }

    /**
     * Method use to check clickablity of My Order on profile page
     */
    public void checkClickablityOfMyOrderButtonOnProfilePage() {
        assertThat(myAccount.getMyOrdersBlock().isDisplayed(), equalTo(true));
        assertThat(myAccount.getMyOrdersBlock().isEnabled(), equalTo(false));
        logger.info("My orders block is not clickable");
        ExtentTestManager.getTest().log(LogStatus.PASS, "My orders block is not clickable");
    }

    /**
     * Method use to check clickablity of My Order on profile page
     */
    public void checkContactUsPageUiVisibility() {
        assertThat(morePage.getContactUsHeader().isDisplayed(), equalTo(true));
        assertThat(morePage.getContactUsAddressOnContactUsPage().isDisplayed(), equalTo(true));
        logger.info("UI elements appears correctly on Contact Us page");
        ExtentTestManager.getTest().log(LogStatus.PASS, "UI elements appears correctly on Contact Us page");
    }

    /**
     * Method use to check free shipping charge on checkout page
     */
    public void checkFreeShippingChargeDiscountAppliedOnCheckoutPage() {
        assertThat(checkoutPage.getFreeshippingCharge().isDisplayed(), equalTo(true));
        assertThat(checkoutPage.getShippingChargeName().isDisplayed(), equalTo(true));
        logger.info("Free shipping discount successfully applied on checkout page");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Free shipping discount successfully applied on checkout page");
    }

    /**
     * Click Login button on native checkout page
     */
    public void clickLoginButtonOnNativeCheckout() {
        waitElement(checkoutPage.getLoginButtonOnNativeCheckoutPage(), 3);
        checkoutPage.getLoginButtonOnNativeCheckoutPage().click();
        logger.info("Login Button on Native Checkout page clicked successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Login Button on Native Checkout page clicked successfully");
    }

    /**
     * Method use to invoke product for automatic discount
     */
    public void clickOnAutomaticDiscountProduct() {
        assertThat(homePage.getProductForAutomaticDiscount().isDisplayed(), equalTo(true));
        homePage.getProductForAutomaticDiscount().click();
        logger.info("Product opened successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Product opened successfully");
    }

    /**
     * Method use to verify automatic discount on cart page
     */
    public void checkAutomaticDiscountOnCartPage() {
        assertThat(cartPage.getAutomaticDiscountAppliedStringOnCartPage().isDisplayed(), equalTo(true));
        logger.info("Automatic discount applied on cart page successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Automatic discount applied on cart page successfully");
    }

    /**
     * Method use to verify automatic discount on checkout page
     */
    public void checkAutomaticDiscountOnCheckoutPage() {
        waitElement(checkoutPage.getAutomaticDisccountAppliedStringOnCheckoutPage(), 8);
        assertThat(checkoutPage.getAutomaticDisccountAppliedStringOnCheckoutPage().isDisplayed(), equalTo(true));
        logger.info("Automatic discount applied on checkout page successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Automatic discount applied on checkout page successfully");
    }

    /**
     * Method use to verify cart icon on PlP Page
     */
    public void clickOnCartIconOnPlP() {
        waitElement(pLpPage.getCartIconOnPlp(), 6);
        assertThat(pLpPage.getCartIconOnPlp().isDisplayed(), equalTo(true));
        pLpPage.getCartIconOnPlp().click();
        logger.info(" click on getCartIcon successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "click on getCartIcon successfully");
    }

    /**
     * Method use to verify decrease quantity is displayed on cart page
     */

    public void isDecreaseProductQuantityDisplayOnCart() {
        assertThat(pDpPage.getDecreaseQuantityButton().isDisplayed(), equalTo(true));
        logger.info("Verified product decrease quantity Icon successfully on cart ");
        ExtentTestManager.getTest().log(LogStatus.PASS, " verified Product decrease Quantity Icon successfully on cart ");
    }

    /**
     * Method use to verify Additional label v2 on the pdp page
     */
    public void verifyAdditinalLabelv2Calculation(String additionalLabelAndroid) {
        String str = additionalLabelAndroid;
        String strArray[] = str.split(" ");
        Float price = Float.parseFloat(strArray[2]);
        Float price1 = Float.parseFloat(strArray[6]);
        Float price2 = Float.parseFloat(strArray[9]);
        Float price3 = Float.parseFloat(strArray[12]);
        Float price_plus_extra = price2 + price3;
        Float price_minus_extra = price2 - price3;
        Float pric = price2;
        Float extra = price2 % 10;
        assertThat(pDpPage.getAdditionallabelv2OnPDP().getText(), containsString(String.format("%.2f",price_plus_extra)));
        assertThat(pDpPage.getAdditionallabelv2OnPDP().getText(), containsString(String.format("%.2f",price_minus_extra)));
        assertThat(pDpPage.getAdditionallabelv2OnPDP().getText(), containsString(String.format("%.2f",pric)));
        assertThat(pDpPage.getAdditionallabelv2OnPDP().getText(), containsString(String.format("%.2f",extra)));
        logger.info("verified Additional promo label V2 successfully on pdp");
        ExtentTestManager.getTest().log(LogStatus.PASS, "verified Additional promo label V2 successfully on pdp");
    }

    /**
     * This method verifies payment value webview checkout
     */
    public void verifyPaymentValueOnOnWebviewCheckout(String price) {
        if (isPlatformNameAndroid) {
            assertThat(checkoutPage.getShowOrderSummaryHeaderBar().getText().contains(price), equalTo(true));
        } else {
            assertThat(checkoutPage.getShowOrderSummaryHeaderBar().getAttribute("label").contains(price), equalTo(true));
        }

        logger.info("payment value " + price + " on payment page on webview checkout verified Successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "payment value " + price + " on payment page on webview checkout verified Successfully");
    }

    /**
     * This method returns the total payment value from native checkout
     */
    public String getTotalAmountFromNativeCheckoutPage() {
        String totalPrice, finalPrice;
        assertThat(checkoutPage.getTotalAmountOnNativeCheckoutPage().isDisplayed(), equalTo(true));
        if (isPlatformNameAndroid) {
            totalPrice = checkoutPage.getTotalAmountOnNativeCheckoutPage().getText();
        } else {
            totalPrice = checkoutPage.getTotalAmountOnNativeCheckoutPage().getAttribute("label");
        }
        String[] price = totalPrice.split("Rs.");
        if (!isPlatformNameAndroid) {
            finalPrice = price[1].trim().replace(",", ".").replaceFirst("[.]", ",");
        } else {
            finalPrice = price[1].trim();
        }
        logger.info("Total Price " + finalPrice + " From Native checkout Page fetched Successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Total Price " + finalPrice + " From Native checkout Page fetched Successfully");
        return finalPrice;
    }

    /**
     * This method verifies Size chart is display on More page
     */
    public void verifySizeChartIsDisplayOnMorePage() {
        assertThat(morePage.getSizeChart().isDisplayed(), equalTo(true));
        logger.info("Size chart is displayed on more page successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Size chart is displayed on more page successfully");
    }

    /**
     * This method used to click on Sizechartoption
     */
    public void verifyUserClickOnSizeChart() {
        assertThat(morePage.getSizeChart().isDisplayed(), equalTo(true));
        morePage.getSizeChart().click();
        logger.info("User clicked on Size chart option successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "User clicked on Size chart option successfully");
    }

    /**
     * This method used to verify the size chart table
     */
    public void verifySizeChartTableIsDisplayed(int size) {
        String sizeChartxpath;
        if (isPlatformNameAndroid) {
            sizeChartxpath = "//android.widget.TextView[@text='Size Chart Table']";
        } else {
            sizeChartxpath = "//XCUIElementTypeStaticText[@name='Size Chart Table']";
        }
        assertThat(mobileDriver.findElements(By.xpath(sizeChartxpath)).size(), equalTo(size));
        logger.info(size + " Size chart is not  displaying on More Page verified successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, size + " Size chart is not  displaying on More Page verified successfully");
    }

    /**
     * This method used to verify the variant popup shows on Quickly add to cart option
     */
    public void verifyVariantPopupOnHomePage() {
        assertThat(pLpPage.getAddToCartButtonOnAddToCartPopup().isDisplayed(), equalTo(true));
        assertThat(pLpPage.getSelectSizeTextOnAddToCartPopup().isDisplayed(), equalTo(true));
        logger.info("Verified Variant Popup successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Verified Variant Popup successfully");
    }

    /**
     * This method invokes favorite from cart page
     */

    public void invokeFavoriteFromCartPage() {
        assertThat(cartPage.getFavoriteButtonOnCartPage().isDisplayed(), equalTo(true));
        cartPage.getFavoriteButtonOnCartPage().click();
        logger.info("Favroite page invoked successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Favroite page invoked successfully");
    }

    /**
     * This method checks product on favorite page
     */

    public void checkProductNameOnFavoritePage(String name) {
        assertThat(favoritePage.getProductNameOnWishlist().isDisplayed(), equalTo(true));
        assertThat(favoritePage.getProductNameOnWishlist().getText(), equalTo(name));
        logger.info("Automatic discount applied on checkout page successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Automatic discount applied on checkout page successfully");
    }

    /**
     * Method verifies USD currency on Wishlist
     * App: Neovo
     */
    public void isSelectedCurrencyDisplayedOnWishlist(String currency) {
        waitElement(favoritePage.getProductPriceFavDollar(), 10);
        assertThat(favoritePage.getProductPriceFavDollar().getText(), containsString(currency));
        logger.info(currency + " currency verified on Wishlist successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, currency + " currency verified on Wishlist successfully");
    }

    /**
     * Method verifies addtional label v2 on PLP
     * App: Neovo
     */
    public void checkAdditionLabelCalculationOnPlp(String name) {
        assertThat(pLpPage.getAddtionalLabelV2().isDisplayed(), equalTo(true));
        assertThat(pLpPage.getAddtionalLabelV2().getText(), containsString(name));
        logger.info("Addtional label V2 verifed on PLP successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Addtional label V2 verifed on PLP successfully");
    }

    /**
     * Method verifies addtional label v2 on Homepage
     * App: Neovo
     */
    public void checkAdditionLabelCalculationOnHomepage(String name) {
        assertThat(homePage.getAdditionalLabelV2OnHomepage().isDisplayed(), equalTo(true));
        assertThat(homePage.getAdditionalLabelV2OnHomepage().getText(), containsString(name));
        logger.info("Addtional label V2 verifed on Homepage successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Addtional label V2 verifed on Homepage successfully");
    }

    /**
     * Method verifies addtional label v2 on Search page
     * App: Neovo
     */
    public void checkAdditionLabelCalculationOnSearchPage(String name) {
        assertThat(searchPage.getAddtionalLabelV2OnSearchPage().isDisplayed(), equalTo(true));
        assertThat(searchPage.getAddtionalLabelV2OnSearchPage().getText(), containsString(name));
        logger.info("Addtional label V2 verifed on Search Page successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Addtional label V2 verifed on Search page successfully");
    }

    /**
     * Click banner from homepage
     * App name: Neovo-commerce
     */
    public void clickProductFromHomepage() {
        waitElement(homePage.getHomePoster(), 15);
        homePage.getHomePoster().click();
        logger.info("Banner is invoked successfully from Homepage");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Banner is invoked successfully from Homepage");
    }

    /**
     * Switch between variant on PDP
     * App name: Neovo-commerce
     */
    public void clickVariantSwitch() {
        waitElement(pDpPage.getVariantSwitch(), 15);
        pDpPage.getVariantSwitch().click();
        logger.info("Switched between swatch options on PDP successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Switched between swatch options on PDP successfully");
    }

    /**
     * Method use to verify increase  quantity is displayed on cart page
     */

    public void isIncreaseProductQuantityDisplayOnCart() {
        assertThat(pDpPage.getIncreaseQuantityButton().isDisplayed(), equalTo(true));
        logger.info("Verified Quantity increase icon successfully on cart");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Verified Product Quantity increase Icon successfully displayed on cart");
    }

    public void verifyEmptyFavouritePage() {

        if (isPlatformNameAndroid) {
            assertThat(favoritePage.getEmptyfavouritepage().isDisplayed(), equalTo(true));
        }
        assertThat(favoritePage.getEmptyfavouritepage().isDisplayed(), equalTo(true));

        logger.info("Empty favourite page verified sucessfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Empty favourite page verified sucessfully");

    }

    /**
     * Method click banner on Home page for sandbox store
     * Store Name : Sandbox
     */
    public void clickBannerOnHomePageForSandboxStore() {
        assertThat(homePage.getBannerOnHomePage().isDisplayed(), equalTo(true));
        homePage.getBannerOnHomePage().click();
        logger.info("Banner On Home Page Clicked Successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Banner On Home Page Clicked Successfully");
    }

    /**
     * Method click on second VSK on PDP Page
     */
    public void clickSecondVSKOnPDPPage() {
        assertThat(pDpPage.getSecondVSKOnPDP().isDisplayed(), equalTo(true));
        pDpPage.getSecondVSKOnPDP().click();
        logger.info("Second VSK on PDP Page Clicked Successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Second VSK on PDP Page Clicked Successfully");
    }

    /**
     * Method click on third VSK on PDP Page
     */
    public void clickThirdVSKOnPDPPage() {
        assertThat(pDpPage.getThirdVSKOnPDP().isDisplayed(), equalTo(true));
        pDpPage.getThirdVSKOnPDP().click();
        logger.info("Third VSK on PDP Page Clicked Successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Third VSK on PDP Page Clicked Successfully");
    }

    /**
     * Method fetch product title from PLP Page
     */
    public String fetchProductTitleFromPLPPage() {
        assertThat(pLpPage.getProductTitleOnPLP().isDisplayed(), equalTo(true));
        String productTitle = pLpPage.getProductTitleOnPLP().getText();
        logger.info("Product Name { " + productTitle + " } Fetched Successfully.");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Product Name { " + productTitle + " } Fetched Successfully.");
        return productTitle;
    }

    /**
     * Method click on add to favorite icon on PLP page
     * Store Name : Neovo
     */
    public void clickAddToFavoriteIconOnPLPPage() {
        assertThat(pLpPage.getAddToFavoriteIconOnPLPForNeovo().isDisplayed(), equalTo(true));
        pLpPage.getAddToFavoriteIconOnPLPForNeovo().click();
        logger.info("Add To Favorite icon on PLP Page Clicked Successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Add To Favorite icon on PLP Page Clicked Successfully");
    }

    /**
     * Method click on add to Cart icon on PLP page
     * Store Name : Neovo
     */
    public void clickAddToCartIconOnPLPPage() {
        assertThat(pLpPage.getAddToCartIconOnPLPForNeovo().isDisplayed(), equalTo(true));
        pLpPage.getAddToCartIconOnPLPForNeovo().click();
        logger.info("Add To Cart icon on PLP Page Clicked Successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Add To Cart icon on PLP Page Clicked Successfully");
    }

    /**
     * Method to verify static message on cart
     * Store Name : Sandbox
     */
    public void verifyStaticMessageOnCart() {
        assertThat(cartPage.getStaticMessageOnCart().isDisplayed(), equalTo(true));
        logger.info("Static message have been verified successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Static message have been verified successfully");

    }

    /**
     * Method Verified user details successfully when user is logged in
     * Store Name : Neovo
     */
    public void verifyLoginUserDetailsShownAfterLogin(String userName, String userEmail) {
        assertThat(profilePage.getUserNameOnProfile().getText(), equalTo(userName));
        assertThat(profilePage.getUserNameEmail().getText(), equalTo(userEmail));
        logger.info("Verified user details successfully shown when user is logged in");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Verified user details successfully shwon when user is logged in");
    }

    /**
     * Method to verify Accept Terms Checkbox on cart
     * Store Name : Sandbox
     */
    public void verifyAcceptTermsCheckboxOnCart() {
        assertThat(cartPage.getAccceptTermsOnCart().isDisplayed(), equalTo(true));
        logger.info("Accept Terms Checkbox on cart have been verified successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Accept Terms Checkbox on cart have been verified successfully");
    }

    /**
     * Method to verify Discount on webview checkout
     * Store Name : Sandbox
     */
    public void verifyDiscountOnWebviewcheckout() {
        assertThat(checkoutPage.getDiscountOnCheckoutPages().isDisplayed(), equalTo(true));
        logger.info("Discount have been verified successfully on webview checkout");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Discount have been verified successfully on webview checkout");
    }

    /**
     * App Name: Sandbox
     * Verify user email id on Native checkout page
     */
    public void isUserEmailDisplayOnNativeCheckoutPage(String email) {
        assertThat(checkoutPage.getUserEmailId().getText(), containsString(email));
        logger.info("Newly registered email id is verified on native checkout page successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Newly registered email id is verified on native checkout page successfully");
    }

    /**
     * Method click on first product on search result page
     */
    public void clickFirstProductOnSearchResultPage() {
        waitElement(searchPage.getFirstProductOnSearchPage(), 6);
        searchPage.getFirstProductOnSearchPage().click();
        logger.info("First Product On Searched Result Page Clicked Successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "First Product On Searched Result Page Clicked Successfully");
    }

    /**
     * Method fetch Automatic discount text from cart page
     */
    public String fetchAutomaticDisocuntTextFromCartpage() {
        String text;
        assertThat(cartPage.getAutomaticDiscountTextOnCartPage().isDisplayed(), equalTo(true));
        if (isPlatformNameAndroid) {
            text = cartPage.getAutomaticDiscountTextOnCartPage().getText().toUpperCase().substring(0, 7);
        } else {
            text = cartPage.getAutomaticDiscountTextOnCartPage().getAttribute("label").toUpperCase().substring(1, 8);
        }
        logger.info(" { " + text + " } Script Based Automatic Discount fetched Successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, " { " + text + " } Script Based Automatic Discount fetched Successfully");
        return text;
    }

    /**
     * Method verifies the discount code text on checout page under show order summary
     */
    public void verifyDiscountTextUnderShowOrderSummary(String text) {
        waitElement(checkoutPage.getDiscountCodeTextUnderShowOrderSummary(), 6);
        assertThat(checkoutPage.getDiscountCodeTextUnderShowOrderSummary().getText().replaceAll("[^a-zA-Z0-9]", "").contains(text), equalTo(true));
        logger.info(" { " + text + " } discount code text is present on checkout page under show order summary ");
        ExtentTestManager.getTest().log(LogStatus.PASS, " { " + text + " } discount code text is present on checkout page under show order summary");
    }

    /**
     * App Name: Sandbox
     * Click Add New Address On Address Page via Checkout Page
     */
    public void clickOnAddNewAddressButtonViaCheckoutPage() {
        waitElement(checkoutPage.getAddNewAddresButton(), 9);
        checkoutPage.getAddNewAddresButton().click();
        logger.info(" Add New Address Clicked Successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Add New Address Clicked Successfully");
    }

    /**
     * App Name: Sandbox
     * Clicks on plus button to add payment details on checkout page
     */
    public void clickOnPlusButtonForPaymentOnCheckoutPage() {
        waitElement(checkoutPage.getAddNewPaymentdetails(), 5);
        checkoutPage.getAddNewPaymentdetails().click();
        logger.info("Successfully clicked plus button to add payment details on checkout page");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Successfully clicked plus button to add payment details on checkout page");
    }

    /**
     * App Name: Sandbox
     * Method verifies alert message for invalid Credit card on Checkout Page
     */
    public void checkAlertMessageForInvalidCreditCard() {
        waitElement(checkoutPage.getAlertMessageForInvalidCreditCard(), 5);
        assertThat(checkoutPage.getAlertMessageForInvalidCreditCard().isDisplayed(), equalTo(true));
        logger.info("Message verified for invalid credit card");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Message verified for invalid credit card");
    }

    /**
     * App Name: Sandbox
     * Method verifies continue button on checkout page
     */
    public void clickOnContinueButtonForPlaceOrder() {
        waitElement(checkoutPage.getContinuePlaceOrder(), 6);
        checkoutPage.getContinuePlaceOrder().click();
        logger.info("Successfully click on the continue button.");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Successfully click on the continue button.");
    }

    /**
     * owner : Shashi More
     * Click on Home button from Bottom Menu
     * App name: Sandbox
     */
    public void clickOnHomeButtonFromHamburger() {
        waitElement(homePage.getHomeButtonFromHamburger(), 2);
        homePage.getHomeButtonFromHamburger().click();
        logger.info("Home Page has been opened successfully.");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Home Page has been opened successfully.");
    }

    /**
     * Method verifies USD currency on Cart page
     * App: Neovo
     */
    public void isSelectedCurrencyDisplayedOnCart(String currency) {
        waitElement(cartPage.getProductPriceOnCartPage(), 10);
        assertThat(cartPage.getProductPriceOnCartPage().getText(), containsString(currency));
        logger.info(currency + " currency verified on cart successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, currency + " currency verified on cart successfully");
    }

    /**
     * Method to verify cart quantity after updating values
     * App: sandbox
     */
    public void checkCurrentQuantityOnCartPage(String expectedQuantity, boolean isBeforeUpdating) {
        waitElement(cartPage.getProductsQuantity(), 4);
        String currentQuantity = cartPage.getProductsQuantity().getText();
        String message;
        if (isBeforeUpdating) {
            message = "Cart value before updating is ";
        } else {
            message = "Cart value updated to ";
        }
        logger.info(message + currentQuantity);
        ExtentTestManager.getTest().log(LogStatus.PASS, message + currentQuantity + "Updating value on cart page verified successfully");
        assertThat(currentQuantity.contains(expectedQuantity), equalTo(true));
    }

    /**
     * App Name: Sandbox
     * Verify Native checkout page for other payments
     */
    public void verifyNativeCheckoutPageForOtherPayments() {
        assertThat(checkoutPage.getUserEmailId().isDisplayed(), equalTo(true));
        assertThat(checkoutPage.getShippingAddressIcon().isDisplayed(), equalTo(true));
        assertThat(checkoutPage.getShippingChargeIcon().isDisplayed(), equalTo(true));
        logger.info("Native checkout page verified successfully for other payments");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Native checkout page verified successfully for other payments");
    }

    /**
     * Method use to check clickablity of My Order on profile page
     */
    public void checkClickablityOfMyOrderButtonOnProfilePageIos() {
        assertThat(myAccount.getMyOrdersBlock().isDisplayed(), equalTo(true));
        myAccount.getMyOrdersBlock().click();
        logger.info("My orders block is not clickable");
        ExtentTestManager.getTest().log(LogStatus.PASS, "My orders block is not clickable");
    }

    /**
     * Method verifies is product image is present on cart page
     */
    public void isProductImageIsDisplayedOnCartPage() {
        assertThat(cartPage.getProductImageOnCartPage().isDisplayed(), equalTo(true));
        logger.info("Product Image is Present on Cart Page");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Product Image is Present on Cart Page");
    }

    /**
     * This Method verifies total amount on cart page
     */
    public void verifyTotalAmountOnCartPage(String totalAmount) {
        assertThat(cartPage.getTotalAmount().getText().contains(totalAmount), equalTo(true));
        logger.info("Total amount on cart page verified successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Total amount on cart page verified successfully");
    }

    /**
     * This Method verifies total payable amount on order confirmation page
     */
    public void verifyTotalPayableAmountOnOrderConfirmationPage(String totalPayableAmount) {
        if (isPlatformNameAndroid) {
            assertThat(thankYouPage.getTotalPayableAmount().getText().contains(totalPayableAmount), equalTo(true));
        } else {
            assertThat(thankYouPage.getTotalPayableAmount().getAttribute("name").contains(totalPayableAmount), equalTo(true));
        }
        logger.info("Total payable amount { " + totalPayableAmount + " } on order confirmation page verified successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Total payable amount { " + totalPayableAmount + " } on order confirmation page verified successfully");
    }

    /**
     * This Method Product recommendation on PDP page
     */
    public void verifyProductRecommendationOnPdpPage() {
        waitElement(pDpPage.getProductRecommendationOnPDP(), 5);
        assertThat(pDpPage.getProductRecommendationOnPDP().isDisplayed(), equalTo(true));
        logger.info("Product recommendation on PDP page is successfully displayed");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Product recommendation on PDP page is successfully displayed");
    }

    public String fetchSubtotalFromCheckoutPage() {
        String subtotal = null;
        checkoutPage.getShowOrderSummaryHeaderBar().click();
        assertThat(checkoutPage.getSubtotalOnCheckoutPage().isDisplayed(), equalTo(true));
        subtotal = checkoutPage.getSubtotalOnCheckoutPage().getText().replace("₹", "");
        logger.info("Fetched subtotal from checkout page successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Fetched subtotal from checkout page successfully");
        return subtotal;
    }

    public String fetchDiscountAmountFromCheckoutPage() {
        String discountAmount = null;
        assertThat(checkoutPage.getDiscountAmountOnCheckoutPage().isDisplayed(), equalTo(true));
        discountAmount = checkoutPage.getDiscountAmountOnCheckoutPage().getText().replace("− ₹", "");
        logger.info("Fetched discount amount from checkout page successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Fetched discount amount from checkout page successfully");
        return discountAmount;
    }

    public void verifiyTotalPaybleFromCheckoutPage(String subtotal, String discount) {
        String totalamount = null;
        assertThat(checkoutPage.getDiscountAmountOnCheckoutPage().isDisplayed(), equalTo(true));
        assertThat(checkoutPage.getSubtotalOnCheckoutPage().isDisplayed(), equalTo(true));
        float TotalpaybleAmt = (Float.valueOf(subtotal) - (Float.valueOf(discount)));
        if (isPlatformNameAndroid) {
            totalamount = checkoutPage.getTotalOnCheckoutPage().getText().replace("₹", "");
            assertThat(totalamount, equalTo(String.format("%.2f", TotalpaybleAmt)));
        } else {
            totalamount = checkoutPage.getTotalOnCheckoutPage().getText().replace("INR", "").replace("₹", "").replace("Updated total price: ", "").trim();
            assertThat(totalamount, equalTo(String.format("%.2f", TotalpaybleAmt)));
        }
        logger.info(" Total payble amount calculated correctly on checkout page successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "total payble amount calculated correctly on checkout page successfully");
    }

    /**
     * This Method click MyAccount logo on homepage
     * Store: Sandbox
     */
    public void clickProfileTabOnHomepageForSandbox() {
        waitElement(loginPage.getProfileTab(), 3);
        loginPage.getProfileTab().click();
        logger.info("click MyAccount logo on homepage  successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "click MyAccount logo on homepage successfully");
    }

    /**
     * Method Verified user details successfully when user is logged in
     * Store Name : Sandbox
     */
    public void checkUserDetailsOnLoginPage(String username, String email) {
        assertThat(profilePage.getLoggedInUserNameOnProfilePage().getText().trim(), equalTo(username));
        assertThat(profilePage.getEmailOnProfilePage().getText(), equalTo(email));
        logger.info("Verified user details successfully shown when user is logged in");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Verified user details successfully shown when user is logged in");
    }

    /**
     * Method Verifies discount applied on webview checkout
     * Store Name : Sandbox
     */
    public void checkDiscountCodeOnCheckoutPage(String discount) {
        waitElement(checkoutPage.getDiscountCodeOnWebviewCheckout(), 10);
        assertThat(checkoutPage.getDiscountCodeOnWebviewCheckout().getText(), equalTo(discount));
        logger.info("Verified Discount Code on Checkout Page Successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Verified Discount Code on Checkout Page Successfully");
    }

    public void clickBackButtonIconOnSearchPage() {
        waitElement(searchPage.getBackarrowIconOnSearchPage(), 10);
        searchPage.getBackarrowIconOnSearchPage().click();
        logger.info(" click on back arrow from search page sucessfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, " click on back arrow from search page sucessfully");
    }

    public void ClickOnWishlisticonFromSearchPage() {
        waitElement(searchPage.getWishlistIconOnSearchPage(), 10);
        searchPage.getWishlistIconOnSearchPage().click();
        logger.info(" product added in wishlist sucessfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "  product added in wishlist sucessfully");
    }

    /**
     * This method will verify the Product original price and compare at price on home page
     * For Android please pass which type of price you want to verify e.g. original Price or Compare at price
     */
    public void verifyProductPriceOnHomePage(String typeOfPrice, String price) {
        if (!isPlatformNameAndroid) {
            waitElement(homePage.getProductOriginalPriceAndCompareAtPrice(), 10);
            assertThat(homePage.getProductOriginalPriceAndCompareAtPrice().getText().contains(price), equalTo(true));
        } else {
            if (typeOfPrice.equalsIgnoreCase("originalprice") || typeOfPrice.contains("original")) {
                waitElement(homePage.getProductOriginalPrice(), 6);
                assertThat(homePage.getProductOriginalPrice().getText().contains(price), equalTo(true));
            } else if (typeOfPrice.equalsIgnoreCase("compareatprice") || typeOfPrice.contains("compare")) {
                waitElement(homePage.getCompareAtPrice(), 6);
                assertThat(homePage.getCompareAtPrice().getText().contains(price), equalTo(true));
            }
        }

        logger.info(" Product Price { " + price + " } On Home Page Verified Successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Product Price { " + price + " } On Home Page Verified Successfully");
    }

    /**
     * Method fetch Automatic discount text from cart page
     */
    public String fetchAmountOffDiscountTextFromCartPage() {
        String text;
        assertThat(cartPage.getAutomaticDiscountTextOnCartPage().isDisplayed(), equalTo(true));
        if (isPlatformNameAndroid) {
            text = cartPage.getAutomaticDiscountTextOnCartPage().getText().toUpperCase().replaceAll("RS", "").replaceAll("[^a-zA-Z0-9]", "");
        } else {
            text = cartPage.getAutomaticDiscountTextOnCartPage().getAttribute("label").toUpperCase().replaceAll("RS", "").replaceAll("[^a-zA-Z0-9]", "");
        }
        logger.info(" { " + text + " } Script Based Automatic Discount fetched Successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, " { " + text + " } Script Based Automatic Discount fetched Successfully");
        return text;
    }

    /**
     * Method Verify to show empty result in PLP
     * Store Name : Sandbox
     */

    public void checkEmptyResultOnPLP() {
        waitElement(pLpPage.getEmptyResultOnPLP(), 6);
        assertThat(pLpPage.getEmptyResultOnPLP().isDisplayed(), equalTo(true));
        logger.info("Verified empty result display on PLP page");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Verified empty result display on PLP page");
    }

    /**
     * Method Verify to click on Clear all button On PLP Page
     * Store Name : Sandbox
     */

    public void clickClearButtonOnFilterPage() {
        waitElement(pLpPage.getClearAllButtonOnFilter(), 6);
        assertThat(pLpPage.getClearAllButtonOnFilter().isDisplayed(), equalTo(true));
        pLpPage.getClearAllButtonOnFilter().click();
        logger.info("Verified empty result display on PLP page");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Verified empty result display on PLP page");
    }


    /**
     * Method Verify to verify Search header page of Sandbox
     * Store Name : Sandbox
     */

    public void checkSearchHeaderOnSandbox() {
        waitElement(searchPage.getSearchHeaderTextOnSearchPageSandbox(), 10);
        assertThat(searchPage.getSearchHeaderTextOnSearchPageSandbox().isDisplayed(), equalTo(true));
        logger.info("Header Text On Search Page On Sandbox verified successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Header Text On Search Page On Sandbox verified successfully");
    }

    public void checkScrollDownPageForBothOS(int scrollStart, int scrollEnd) {
        TouchAction touchAction = new TouchAction(mobileDriver);
        touchAction.press(PointOption.point(0, scrollStart))
                .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1)))
                .moveTo(PointOption.point(0, scrollEnd))
                .release()
                .perform();

    }

    /**
     * Method to verify the GiftCard should not display on checkout page
     * App: Neovo
     */

    public void verifyGiftLabelNotDisplayed() {
        boolean isGiftLabelDisplayed = false;

        try {
            isGiftLabelDisplayed = checkoutPage.giftLabel.isDisplayed();
        } catch (NoSuchElementException | TimeoutException e) {
            // Handle exception if the element is not found or not displayed
        }

        if (!isGiftLabelDisplayed) {
            logger.info("Gift label is not displayed");
            ExtentTestManager.getTest().log(LogStatus.PASS, "Gift label is not displayed");
        } else {
            logger.error("Gift label is displayed");
            ExtentTestManager.getTest().log(LogStatus.FAIL, "Gift label is displayed");
            Assert.fail();
        }
    }


    /**
     * Method to user able to very order section on checkout page
     * App: Neovo
     */
    public void checkOrderSectionIsVisible() {
        waitElement(checkoutPage.getOrderSummarySection(), 10);
        assertThat(checkoutPage.getOrderSummarySection().isDisplayed(), equalTo(true));
        logger.info("User able to expand and check order section successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "User able to expand and check order section successfully");
    }


    public void clickonFirstOrderFromOrderListing() {
        assertThat(myOrdersPage.getFirstOrderFromOrderListing().isDisplayed(), equalTo(true));
        myOrdersPage.getFirstOrderFromOrderListing().click();
        logger.info("opened order details page of the first order sucessfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "opened order details page of the first order sucessfully");
    }

    /**
     * Method to user able to very additional label is not displayed on Homepage for exclude screen
     * App: Neovo
     */

    public void verifyAdditionalLabelShouldNotDisplayOnHomePage(int size) {
        String labelxpath;
        if (isPlatformNameAndroid) {
            labelxpath = "//android.widget.TextView[contains(@text, 'Extra Rs. 449.10')]";
        } else {
            labelxpath = "//XCUIElementTypeStaticText[contains(@name, 'Extra Rs. 449.10')]";
        }
        assertThat(mobileDriver.findElements(By.xpath(labelxpath)).size(), equalTo(size));
        logger.info(size + " Additional label is not  displaying on homepage verified successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, size + " App logo not displaying on homepage verified successfully");
    }

    /**
     * Method to user able to click on Variant Product Collection
     * App: Neovo
     */

    public void clickOnVariantProductCollection() {
        waitElement(collectionPage.getVariantProductCollection(), 3);
        collectionPage.getVariantProductCollection().click();
        logger.info("Variant Product Collection clicked successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Variant Product Collection clicked successfully");
    }

    /**
     * Free product invoking
     * App name: Neovo-commerce
     */
    public void clickFreeProductOnCollection() {
        waitElement(collectionPage.getFreeProduct(), 3);
        collectionPage.getFreeProduct().click();
        logger.info("Free product is clicked successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Free product is clicked successfully");
    }

    /**
     * Verify total payable for free product
     * App name:Neovo
     */
    public void checkTotalPayableForFreeProductOnCart() {
        assertThat(cartPage.getTotalPayableForFreeProduct().isDisplayed(), equalTo(true));
        logger.info("Total payable for free product is verified");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Total payable for free product is verified");
    }

    /**
     * This verifies free payment message
     * App name: Neovo
     */

    public void checkFreePaymentMessageOnCheckout() {
        if (isPlatformNameAndroid) {
            waitElement(checkoutPage.getFreePaymentMessage(), 3);
            assertThat(checkoutPage.getFreePaymentMessage().isDisplayed(), equalTo(true));
        } else {
            waitElement(checkoutPage.getFreePaymentMessage1(), 3);
            assertThat(checkoutPage.getFreePaymentMessage1().isDisplayed(), equalTo(true));
            assertThat(checkoutPage.getFreePaymentMessage2().isDisplayed(), equalTo(true));
            assertThat(checkoutPage.getFreePaymentMessage3().isDisplayed(), equalTo(true));
        }
        logger.info("Free payment message is verified successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Free payment message is verified successfully");
    }

    /**
     * Method verifies total payable for free product on order confirmation page
     */
    public void checkTotalPayableForFreeProductOnOrderConfirmationPage() {
        waitElement(thankYouPage.getTotalPayableForFreeProductOnOrderConfirmation(), 10);
        assertThat(thankYouPage.getTotalPayableForFreeProductOnOrderConfirmation().isDisplayed(), equalTo(true));
        logger.info("Free product total payable is verified successfully on order conformation page");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Free product total payable is verified successfully on order conformation page");
    }

    /**
     * Method return boolean value for rating pop-up
     */
    public boolean isRatingPopUpDisplayed() {
        waitElement(thankYouPage.getRatingPopUp(), 12);
        boolean a = thankYouPage.getRatingPopUp().isDisplayed();
        return a;
    }

    /**
     * Method clicks on not now on rating pop-up
     */
    public void clickNotNowOnRatingPopUp() {
        assertThat(thankYouPage.getNotNowOnRatingPopUp().isDisplayed(), equalTo(true));
        thankYouPage.getNotNowOnRatingPopUp().click();
        logger.info("Not now is clicked successfully on rating pop-up");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Not now is clicked successfully on rating pop-up");
    }

    /**
     * Method clicks on X button at the top right of the page on CT notification
     */
    public void clickXButtonIconOnCtNotification() {
        assertThat(myAccount.getXButtonOnCtNotification().isDisplayed(), equalTo(true));
        myAccount.getXButtonOnCtNotification().click();
        logger.info("Clicked on x button in the top right of the CT notification page");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Clicked on x button in the top right of the CT notification page");
    }

    /**
     * * This method used to check able to logout from webview
     * */
    public void clickonlogoutFromWebview(){
        assertThat(checkoutPage.logoutFromWebview.isDisplayed(), equalTo(true));
        checkoutPage.logoutFromWebview.click();
        logger.info("On click of the logout button User logout successfully from webview checkout ");
        ExtentTestManager.getTest().log(LogStatus.PASS, " On click of the logout button User logout successfully from webview checkout ");
    }

    /**
     * * This method used to check login button on profile page
     */
      public void checkLoginButtonOnProfilepage() {
        assertThat(profilePage.getLoginButtonOnProfile().isDisplayed(), equalTo(true));
        logger.info("checking login button visibility on the profile page");
        ExtentTestManager.getTest().log(LogStatus.PASS, "checking login button visibility on the profile page");
     }


    /**
     * This Method clicks ok on address added pop-up
     */
    public void clickOkOnAddressAddedPopUp() {
        waitElement(myAccount.getAddressAddedPopUp(), 3);
        assertThat(myAccount.getAddressAddedPopUp().isDisplayed(), equalTo(true));
        myAccount.getOkOnAddressAddedPopUp().click();
        logger.info(" Clicked ok on address added pop-up successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, " Clicked ok on address added pop-up successfully");
    }

    /**
     * This Method verifies address deletion
     */
    public void verifyEmptyAddressList() {
        waitElement(myAccount.getNoAddressList(), 3);
        assertThat(myAccount.getNoAddressList().isDisplayed(), equalTo(true));
        logger.info("Address page is verified successfully with nil address");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Address page is verified successfully with nil address");
    }

    /**
     * This Method verifies tax on the checkout page
     */
    public void verifyTaxOnCheckoutPage(String subtotal) {
        Float tax = (Float.valueOf(subtotal) / 100) * 18;
        assertThat(checkoutPage.getTaxOnCheckout().getText().replace("₹", ""), equalTo(String.format("%.2f", tax)));
        logger.info("Tax calculated properly on the checkout page");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Tax calculated properly on the checkout page");
    }
    /** This method used to check the "same As Shipping Address Radio Button" and "Use Different Billing Address Radio Button" on webview checkout page
     */
    public void checkBillingAddressSelectionSection() {
        waitElement(checkoutPage.getSameAsShippingAddressRadioButton(),7);
        waitElement(checkoutPage.getUseDifferentBillingAddressRadioButton(),7);
        assertThat(checkoutPage.getSameAsShippingAddressRadioButton().isDisplayed(), equalTo(true));
        assertThat(checkoutPage.getUseDifferentBillingAddressRadioButton().isDisplayed(), equalTo(true));
        logger.info("Billing address selection section on checkout page verified successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Billing address selection section on checkout page verified successfully");
    }

    /**
     * Method clicks Use different billing address radio button
     */
    public void clickOnUseDifferentBillingAddressRadioButton() {
        assertThat(checkoutPage.getUseDifferentBillingAddressRadioButton().isDisplayed(), equalTo(true));
        checkoutPage.getUseDifferentBillingAddressRadioButton().click();
        logger.info("Use diffferent billing address radio button on webview checout clicked successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Use diffferent billing address radio button on webview checout clicked successfully");
    }

    /**
     * This method verify erron on billing address page
     */
    public void checkErrrorOnBillingAddressPage(){
        assertThat(checkoutPage.getErrorMessageOnCheckoutPageForBillingAddress().isDisplayed(), equalTo(true));
        logger.info("error message success");
        ExtentTestManager.getTest().log(LogStatus.PASS, "error message success");
    }

    /**
     * Method clicks Use different billing address radio button
     */
    public void clickUseANewBillingAddress() {
        waitElement(checkoutPage.getDropDownForBillingAddressSelection(), 6);
        checkoutPage.getDropDownForBillingAddressSelection().click();
        waitFor(1);
        checkoutPage.getUseANewAddressOptionUnderDropdown().click();
        logger.info("Use a new Billing Address Clicked Successfully clicked successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Use a new Billing Address Clicked Successfully clicked successfully");

    }

    /**
     * Method clicks on back button on order details page
     */
    public void clickBackButtonOnOrderDetails() {
        waitElement(checkoutPage.getBackOnOrderDetails(), 6);
        assertThat(checkoutPage.getBackOnOrderDetails().isDisplayed(), equalTo(true));
        checkoutPage.getBackOnOrderDetails().click();
        logger.info("Back button successfully clicked");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Back button successfully clicked");
    }
    /**
     * Method to scroll down the page
     */
    public static void scrollDown(AppiumDriver<MobileElement> driver) {
        if(isPlatformNameAndroid) {
            Dimension dimension = driver.manage().window().getSize();
            int scrollStart = (int) (dimension.getHeight() * 0.5); // start from middle of the height
            int scrollEnd = (int) (dimension.getHeight() * 0.15);   // end at 80% of the height
            new TouchAction<>(driver)
                    .press(PointOption.point(dimension.width / 2, scrollStart))
                    .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1)))
                    .moveTo(PointOption.point(dimension.width / 2, scrollEnd))
                    .release()
                    .perform();
        } else {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            HashMap<String, String> scrollObject = new HashMap<>();
            scrollObject.put("direction", "down");
            js.executeScript("mobile:scroll", scrollObject);
        }
    }

    /**
     * Method use to check whether user landed on payment page on webview checkout
     */
    public boolean isPaymentPageIsDisplayedOnWebviewCheckoutPage(){
        int creditCardElementsCount=0;
        if (isPlatformNameAndroid){
            creditCardElementsCount = mobileDriver.findElements(By.xpath("//android.widget.TextView[contains(@text,'Credit card')]")).size();
        }
        else{
            creditCardElementsCount = mobileDriver.findElements(By.xpath("//XCUIElementTypeStaticText[contains(@name,'Credit card')]")).size();
        }
        if (creditCardElementsCount>0){
            logger.info("Landed on payment page on webview checkout successfully");
            ExtentTestManager.getTest().log(LogStatus.PASS, "Landed on payment page on webview checkout successfully");
            return true;
        }
        else{
            logger.info("Not Landed on payment page on webview checkout");
            ExtentTestManager.getTest().log(LogStatus.PASS, "Not Landed on payment page on webview checkout");
            return false;
        }
    }

    /**
     * Method use to Click Continue Button on Information and Shippping page together on checkout page
     */
    public void clickContinueButtonOnInformationAndShippingPageOnWebviewCheckout(){
        if(isPlatformNameAndroid){
            scrollToText("Continue to shipping");
        }
        else{
            scrollForIos();
        }
        checkoutPage.getContinueButton().click();
        logger.info("Continue Button on Information Page On Webview Checkout Clicked Successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Continue Button on Information Page On Webview Checkout Clicked Successfully");
        waitFor(4);
        if(isPlatformNameAndroid){
            scrollToText("Continue to payment");
        }
        else{
            scrollForIos();
        }
        checkoutPage.getContinueButton().click();
        logger.info("Continue Button on Shipping Page On Webview Checkout Clicked Successfully");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Continue Button on Shippung Page On Webview Checkout Clicked Successfully");
        waitFor(4);
    }
}