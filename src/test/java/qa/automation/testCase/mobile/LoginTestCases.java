package qa.automation.testCase.mobile;

import io.appium.java_client.HasSettings;
import io.appium.java_client.Setting;
import org.apache.log4j.Logger;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import qa.automation.base.MobileBaseTest;
import qa.automation.enums.StoreNameEnum;
import qa.automation.report.ExtentTestManager;

import static qa.automation.utilities.ReadConfig.flag;

public class LoginTestCases extends MobileBaseTest {
    private static final Logger logger= Logger.getLogger(LoginTestCases.class);

    private static boolean steve = flag("steve");

    @DataProvider(name = "testType")
    public static Object[][] testType() {
        return new Object[][] {
                {"SignIn", "MAB-T13179" },
                {"JoinIn", "MAB-T14108" }
        };
    }

    @DataProvider(name = "login_Logout")
    public static Object[][] login_Logout() {
        return new Object[][] {
                {"Logout", "MAB-T14099" },
                {"Login", "MAB-T13197" }
        };
    }



    @Test(priority = 0, groups = {"sm","regression"},dataProvider = "testType")
    public void verifyLoginPageThroughJoinNowAndSignINViaHamburgerMenu(String testType,String testCaseId) {
        testId=testCaseId;
        logger.info(testCaseId+" : This test validates verify Login page Through "+testType+" Btn Via Hamburger Menu");
        ExtentTestManager.getTest().setDescription(testCaseId+" : This test validates verify Login page Through "+testType+" Btn Via Hamburger Menu");
        ((HasSettings)mobileDriver).setSetting(Setting.WAIT_FOR_IDLE_TIMEOUT, 500);
        onBoardingNextButton();
        verifyHomepage();
        clickOnHambergerMenuforSM();
        if (testType.equalsIgnoreCase("SignIn")){
            clickOnSignIn();
        }else {
            clickOnJoinIn();
        }
        verifyLoginPagePageforSM();
        clickHomeBtn();
    }

    @Test(priority = 0, groups = {"sm","regression"},dataProvider = "login_Logout")
    public void verifyLoginAndLogoutThroughMoreBtnViaHamburgerMenu(String testType,String testCaseId) {
        testId=testCaseId;
        logger.info(testCaseId+" : This test validates verify "+testType+" Through moreBtn Via Hamburger Menu");
        ExtentTestManager.getTest().setDescription(testCaseId+" : This test validates verify "+testType+" Through moreBtn Btn Via Hamburger Menu");
        ((HasSettings)mobileDriver).setSetting(Setting.WAIT_FOR_IDLE_TIMEOUT, 500);
        onBoardingNextButton();
        verifyHomepage();
        clickOnHambergerMenuforSM();
        clickOnMoreBtn();
        waitFor(1.5);
        scrollToText("Login");
        clickOnLoginBtnOnHambergerMenu();
        verifyLoginPagePageforSM();
        enterEmailID("plobaltest@yopmail.com");
        enterPassword("test1234");
        clickLogin();
        verifyLogoutBtnforSm();
        if (testType.equalsIgnoreCase("Logout")){
            backBtn();
            clickOnHambergerMenuforSM();
            clickOnMoreBtn();
            waitFor(1.5);
            scrollToText("Logout");
            waitFor(1);
            clickOnLogoutBtnOnHambergerMenu();
            clickOnOK();
        }
        clickHomeBtn();
    }


    @Test(priority = 0, groups = {"sm","regression"})
    public void verifyStoreContactDetailsThroughMoreBtnViaHamburgerMenu() {
        testId = "MAB-T13190";
        logger.info(testId+" : This test validates verify Store Contact Details Through moreBtn Via Hamburger Menu");
        ExtentTestManager.getTest().setDescription(testId+" : This test validates verify Store Contact Details Through moreBtn Btn Via Hamburger Menu");
        ((HasSettings) mobileDriver).setSetting(Setting.WAIT_FOR_IDLE_TIMEOUT, 500);
        onBoardingNextButton();
        verifyHomepage();
        clickOnHambergerMenuforSM();
        clickOnMoreBtn();
        waitFor(1.5);
        clickOnContactUs();
        verifyStoreContactDeatilsPage();
        clickHomeBtn();
    }

    @Test(priority = 0, groups = {"neovo","regression","iOSAppReset"})
    public void verifyLoginPageElements() {
        testId = "MAB-T1043";
        logger.info(testId + " : This test validates the Login Page elements");
        ExtentTestManager.getTest().setDescription(testId + " : This test validates the Login Page elements");
        clickNoButtonOnOnboardingScreen();
        verifyHomepage();
        clickOnMyAccount();
        verifyMyAccountPage();
        clickOnLoginButtonOnMyAccountPage();
        verifyLoginPageUI();
    }

    @Test(priority = 0, groups = {"neovo","regression","iOSAppReset"})
    public void checkErrorMessageWhenUserEntersWrongEmailOnLoginPage(){
        testId = "MAB-T15433";
        logger.info(testId+" This test validate error message when user enter invalid email address on login page");
        ExtentTestManager.getTest().setDescription(testId+" This test validate error message when user enter invalid email address on login page");
        String email="mmmdshdjshjdhsjdh";
        String password="12345";
        String emailErrorMessage="Please enter a valid email address";
        clickNoButtonOnOnboardingScreen();
        verifyHomepage();
        clickProfileTabOnHomePage();
        isMyProfileDispalyed();
        clickLoginOrLogoutButtonOnProfilePage();
        waitFor(2);
        verifyLoginPage();
        enterEmailID(email);
        enterPassword(password);
        clickLoginForInvalidLoginDetails();
        verifyErrorMessage(emailErrorMessage);
    }
    @Test(priority = 0, groups = {"neovo","regression", "iOSFailure", "iOSAppReset"})
    public void checkErrorMessageWhenUserEntersWrongPassword(){
        testId = "MAB-T15410";
        logger.info(testId+" This test validate error message when user enter invalid password address on login page");
        ExtentTestManager.getTest().setDescription(testId+" This test validate error message when user enter invalid password address on login page");
        String email="mayursavdekar@plobalapps.com";
        String password="0";
        String androidPasswordErrorMessage="Password is too short. It should be at least 5 characters";
        String iOSPasswordErrorMessage="Password is too short (minimum is 5 characters)";
        clickNoButtonOnOnboardingScreen();
        verifyHomepage();
        clickProfileTabOnHomePage();
        isMyProfileDispalyed();
        clickLoginOrLogoutButtonOnProfilePage();
        verifyLoginPage();
        enterEmailID(email);
        enterPassword(password);
        clickLoginForInvalidLoginDetails();
        if (isPlatformNameAndroid){
            verifyErrorMessage(androidPasswordErrorMessage);
        }
        else{
            verifyErrorMessage(iOSPasswordErrorMessage);
        }
    }

    @Test(priority = 0, groups = {"neovo", "regression"})
    public void verifyLoginAndRegisterButtonOnLoginPage() {
        testId = "MAB-T15434";
        logger.info(testId + " : This test validates user is able to see the login and register button on the login screen when account setting is optional/required");
        ExtentTestManager.getTest().setDescription(testId + " : This test validates user is able to see the login and register button on the login screen when account setting is optional/required");
        selectStore(StoreNameEnum.SANDBOX);
        clickNoButtonOnOnboardingScreen();
        verifyHomepage();
        clickOnHamburgerButtonFromHomePage();
        clickOnLoginButtonFromHamburgerMenu();
        verifyLoginButtonOnLoginPage();
        verifyRegisterButtonOnLoginPage();
    }

    @Test(groups = {"neovo","regression","iOSAppReset"})
    public void verifyLoginScreenUIElements() {
        testId = "MAB-T1043";
        logger.info(testId + " : This test verifies login screen UI Elements");
        ExtentTestManager.getTest().setDescription(testId + " : This test verifies login screen UI Elements");
        clickNoButtonOnOnboardingScreen();
        verifyHomepage();
        clickOnMyAccount();
        verifyMyAccountPage();
        clickOnLoginButtonOnMyAccountPage();
        verifyLoginPageUI();
    }
}
