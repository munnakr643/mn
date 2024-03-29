package qa.automation.testCase.mobile;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.testng.annotations.Test;
import qa.automation.base.MobileBaseTest;
import qa.automation.report.ExtentTestManager;
import qa.automation.testCase.mobileWeb.MobileWithWebTestCases;

import static qa.automation.base.WebBaseTest.randomNum;

public class DeleteAccountTestCases extends MobileBaseTest {

    public static Logger logger = LogManager.getLogger(MobileWithWebTestCases.class);

    @Test(priority = 0, groups = {"neovo","regression","iOSAppReset"})
    public void verifyAccountDeletionFromApp() {
        testId = "MAB-T12132";
        logger.info(testId+" : This test validates user should be able delete there Account");
        ExtentTestManager.getTest().setDescription(testId+" : This test validates user should be able delete there Account");
        String email= "plobaltest"+randomNum()+"@yopmail.com";
        clickNoButtonOnOnboardingScreen();
        verifyHomepage();
        clickOnMyAccount();
        verifyMyAccountPage();
        clickOnLoginButtonOnMyAccountPage();
        verifyLoginPage();
        clickRegisterButton();
        waitFor(1);
        verifyNewUserRegisterPage();
        enterFirstName("Test"+randomNum());
        enterLastName("User");
        enterEmailID(email);
        enterPassword("test1234");
        enterConfirmPasswordForNewUser("test1234");
        selectPolicyCheckBox();
        clickRegisterBtnForNewUser();
        checkLogoutButtonOnProfilePage();
        clickOnDeleteAccountOnProfilePage();
        clickOngetYesGoAheadButton();
        verifyLoginBtnOnMyProfilePage();
        verifyMyAccountPage();
    }

    @Test(priority = 0, groups = {"neovo","regression","iOSAppReset"})
    public void verifyReloginFromDeletedUserId() {
        testId = "MAB-T12134";
        logger.info(testId+" : This test validates Re-login From Deleted UserId");
        ExtentTestManager.getTest().setDescription(testId+" : This test validates Re-login From Deleted UserId");
        String email= "plobaltest"+randomNum()+"@yopmail.com";
        clickNoButtonOnOnboardingScreen();
        verifyHomepage();
        clickOnMyAccount();
        verifyMyAccountPage();
        clickOnLoginButtonOnMyAccountPage();
        verifyLoginPage();
        clickRegisterButton();
        verifyNewUserRegisterPage();
        enterFirstName("Test"+randomNum());
        enterLastName("User");
        enterEmailID(email);
        enterPassword("test1234");
        enterConfirmPasswordForNewUser("test1234");
        selectPolicyCheckBox();
        clickRegisterBtnForNewUser();
        checkLogoutButtonOnProfilePage();
        clickOnDeleteAccountOnProfilePage();
        clickOngetYesGoAheadButton();
        verifyLoginBtnOnMyProfilePage();
        verifyMyAccountPage();
        waitFor(1.5);
        clickOnLoginButtonOnMyAccountPage();
        enterEmailID(email);
        enterPassword("test1234");
        clickLogin();
        verifyLoginPage();
    }

    @Test(priority = 0, groups = {"neovo","regression","iOSAppReset"})
    public void checkDeleteAccountButtonOnProfilePage(){
        testId ="MAB-T12129";
        String email="mayursavdekar@plobalapps.com";
        String password="12345";
        String deleteAccountText= "Delete Account";
        logger.info(testId+" : This test validates delete account button presence on Profile Page");
        ExtentTestManager.getTest().setDescription(testId +" : This test validates delete account button presence on Profile Page");
        clickNoButtonOnOnboardingScreen();
        clickProfileTabOnHomePage();
        clickLoginOrLogoutButtonOnProfilePage();
        waitFor(2);
        enterEmailID(email);
        enterPassword(password);
        clickLogin();
        waitFor(2);
        if(isPlatformNameAndroid){
            scrollToText("Delete Account");
        }
        verifyDeleteAccountOnProfilePage(deleteAccountText);
    }

}
