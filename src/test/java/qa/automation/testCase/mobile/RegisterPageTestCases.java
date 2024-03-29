package qa.automation.testCase.mobile;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;
import qa.automation.base.MobileBaseTest;
import qa.automation.enums.StoreNameEnum;
import qa.automation.report.ExtentTestManager;

import static qa.automation.base.WebBaseTest.randomNum;

public class RegisterPageTestCases extends MobileBaseTest {

    private static final Logger logger = Logger.getLogger(RegisterPageTestCases.class);

    @Test(priority = 0, groups = {"neovo", "regression", "iOSAppReset"})
    public void VerifyUserShouldBeAbleToAddNewUser() {
        testId = "MAB-T15584";
        logger.info(testId + " : This test validates add new user in store");
        ExtentTestManager.getTest().setDescription(testId + " : This test validates validates add new user in store");
        String FName = "Sahil";
        String LName = "Tripathi";
        String passowrd = "sahil123";
        String emailid = "plobal" + Math.random() + "@gmail.com";
        clickNoButtonOnOnboardingScreen();
        verifyHomepage();
        clickOnMyAccount();
        clickOnLoginButtonOnMyAccountPage();
        clickRegisterButton();
        enterFirstName(FName);
        enterLastName(LName);
        enterEmailID(emailid);
        enterPassword(passowrd);
        enterConfirmPasswordForNewUser(passowrd);
        selectPolicyCheckBox();
        clickRegisterBtnForNewUser();
    }


    @Test(priority = 0, groups = {"neovo", "regression", "iOSFailure", "androidFailure", "iOSAppReset"})
    public void registerFromWebviewCheckout() {
        String email = "plobaltest" + randomNum() + "@yopmail.com";
        testId = "MAB-T15243";
        logger.info(testId + "This testcases verifies that user is able to register new customer from checkout page when webview checkout is enabled");
        ExtentTestManager.getTest().setDescription(testId + "This testcases verifies that user is able to register new customer from checkout page when webview checkout is enabled");
        clickNoButtonOnOnboardingScreen();
        verifyHomepage();
        clickOnListIcon();
        clickOnFashionCollection();
        clickOnDressCollection();
        waitFor(3);
        selectProductFromPLP();
        clickOnBuyNowFromPDP();
        if (isPlatformNameAndroid) {
            clickOnConfirm();
        }
        waitFor(3);
        clickOnLoginButtonOnWebviewCheckout();
        verifyLoginPage();
        clickRegisterButton();
        waitFor(3);
        enterFirstName("Test" + randomNum());
        enterLastName("User");
        enterEmailID(email);
        enterPassword("test1234");
        enterConfirmPasswordForNewUser("test1234");
        selectPolicyCheckBox();
        clickRegisterBtnForNewUser();
        verifyRegistedUserOnWebviewCheckout(email);
    }

    @Test(priority = 0, groups = {"neovo", "regression", "iOSFailure", "iOSAppReset"})
    public void checkRegisterPageWhenUserEntersInvalidEmailAddress() {
        testId = "MAB-T15581";
        logger.info(testId + " : This test validates error message after entering invalid email on register page");
        ExtentTestManager.getTest().setDescription(testId + " :This test validates error message after entering invalid email on register page");
        String firstname = "Plobal";
        String lastname = "Automation";
        String invalidEmail = "hdgahgdha";
        String password = "12345";
        String errorMessage = "Please enter a valid email address";
        clickNoButtonOnOnboardingScreen();
        verifyHomepage();
        clickProfileTabOnHomePage();
        if (!isPlatformNameAndroid) {
            scrollForIos();
        }
        isMyProfileDispalyed();
        clickOnLoginButtonOnMyAccountPage();
        clickRegisterButton();
        enterFirstName(firstname);
        enterLastName(lastname);
        enterEmailID(invalidEmail);
        enterPassword(password);
        enterConfirmPasswordForNewUser(password);
        selectPolicyCheckBox();
        clickRegisterBtnForNewUser();
        verifyErrorMessage(errorMessage);
    }

    @Test(priority = 0, groups = {"neovo", "regression", "iOSAppReset"})
    public void checkUserAbleToRedirectToRegisterPage() {
        testId = "MAB-T24345";
        logger.info(testId + " : This test validates User able to Redirect to Register page from login page and Logout button should visible after sign up");
        ExtentTestManager.getTest().setDescription(testId + " :This test validates User able to Redirect to Register page from login page Logout button should visible after sign up");
        String FName = "Sahil";
        String LName = "Tripathi";
        String passowrd = "sahil123";
        String emailid = "plobal" + Math.random() + "@gmail.com";
        clickNoButtonOnOnboardingScreen();
        verifyHomepage();
        clickOnMyAccount();
        verifyMyAccountPage();
        clickOnLoginButtonOnMyAccountPage();
        verifyLoginPage();
        verifyRegisterButtonOnLoginPage();
        clickRegisterButton();
        verifyNewUserRegisterPage();
        enterFirstName(FName);
        enterLastName(LName);
        enterEmailID(emailid);
        enterPassword(passowrd);
        enterConfirmPasswordForNewUser(passowrd);
        selectPolicyCheckBox();
        clickRegisterBtnForNewUser();
        waitFor(5);
        verifyMyAccountPage();
        checkLogoutButtonOnProfilePage();
    }

    @Test(priority = 0, groups = {"sandbox", "regression", "iOSAppReset"})
    public void verifyUserRegistrationFromNativeCheckout() {
        testId = "MAB-T15244";
        logger.info(testId + ": This test validates User Registration from native checkout page");
        ExtentTestManager.getTest().setDescription(testId + ": This test validates User Registration from native checkout page");
        String FName = "Nanthni";
        String LName = "Priya";
        String password = "qwerty";
        String email = "priya" + randomString(5).toLowerCase() + randomNum() + "@yopmail.com";
        selectStore(StoreNameEnum.SANDBOX);
        verifyHomepage();
        clickOnProductFromGrid();
        waitFor(5);
        verifyPdp();
        clickOnBuyNowFromPDP();
        verifyCTAPopup();
        clickOnOtherButtonCTA();
        waitFor(2);
        clickLoginButtonOnNativeCheckout();
        verifyLoginPage();
        clickRegisterButton();
        verifyNewUserRegisterPage();
        enterFirstName(FName);
        enterLastName(LName);
        enterEmailID(email);
        enterPassword(password);
        enterConfirmPasswordForNewUser(password);
        clickRegisterBtnForNewUser();
        isUserEmailDisplayOnNativeCheckoutPage(email);
    }
}