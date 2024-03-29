package qa.automation.testCase.mobile;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;
import qa.automation.base.MobileBaseTest;
import qa.automation.report.ExtentTestManager;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class MyProfileTestCases extends MobileBaseTest {

    private static final Logger logger = Logger.getLogger(MyProfileTestCases.class);

    @Test(priority = 0, groups = {"neovo", "regression", "iOSAppReset"})
    public void verifyUserCanBeAbleToDeleteAddress() {
        testId = "MAB-T14625";
        String emailid = "nanthni@mailinator.com";
        String password = "qwerty";
        String Fname = "Test";
        String Lname = "User" + randomString(3);
        String Company = "Plobal";
        String contact = "9999999999";
        String Address1 = "Test";
        String Address2 = "Address";
        String State = "Tamil Nadu";
        String City = "Chennai";
        String Zipcode = "600119";
        logger.info(testId + " This test validates delete address of user from my account page ");
        ExtentTestManager.getTest().setDescription(testId + " This test validates delete address of user from my account page ");
        clickNoButtonOnOnboardingScreen();
        clickOnMyAccount();
        clickOnLoginButtonOnMyAccountPage();
        enterEmailID(emailid);
        enterPassword(password);
        clickLogin();
        clickAddressFeatureOnProfilePage();
        waitFor(3);
        clickAddNewAddressButton();
        enterFirstnameAccountPage(Fname);
        enterLastnameAccountPage(Lname);
        enterCompanyNameOnAddressPage(Company);
        enterContactNumberOnAddressPage(contact);
        enterAddressLine1OnAddressPage(Address1);
        enterAddressLine2OnAddressPage(Address2);
        enterStateOnAddressPage(State);
        enterCityOnAddressPage(City);
        enterZipcodeOnAddressPage(Zipcode);
        if (isPlatformNameAndroid) {
            mobileDriver.hideKeyboard();
        } else {
            clickDoneButtonOnIOSKeyboard();
        }
        clickSaveButtonOnAddAddressPage();
        waitFor(3);
        if (isPlatformNameAndroid) {
            clickOkOnAddressAddedPopUp();
            clickAddressFeatureOnProfilePage();
            clickOnRemoveButtonFromProfilePage();
            clickYesOnPopup();
        }
        else {
            clickOnRemoveButtonFromProfilePage();
        }
        verifyEmptyAddressList();
    }


    @Test(priority = 0, groups = {"neovo", "regression", "iOSAppReset"})
    public void VerifyUserAbleToAddAddressonProfilePage() {
        testId = "MAB-T14618";
        String emailid = "sahiltripathi@plobalapps.com";
        String password = "plobal123";
        String Fname = "Sahill";
        String Lname = "Tripathi" + randomString(3);
        String Company = "Plobal";
        String contact = "8209147857";
        String Address1 = "Bavdhan";
        String Address2 = "Sai Velocity";
        String State = "Maharashtra";
        String City = "Pune";
        String Zipcode = "411021";
        logger.info(testId + " This test validates that user should able to add address on my profile page");
        ExtentTestManager.getTest().setDescription(testId + " This test validates that user should able to add address on my profile page ");
        clickNoButtonOnOnboardingScreen();
        clickOnMyAccount();
        clickOnLoginButtonOnMyAccountPage();
        enterEmailID(emailid);
        enterPassword(password);
        clickLogin();
        clickAddressFeatureOnProfilePage();
        waitFor(3);
        try {
            clickOnRemoveAddressFromCheckoutPage();
            if (isPlatformNameAndroid) {
                clickYesOnPopup();
            }
        } catch (Exception e) {
        }
        waitFor(3);
        clickAddNewAddressButton();
        enterFirstnameAccountPage(Fname);
        enterLastnameAccountPage(Lname);
        enterCompanyNameOnAddressPage(Company);
        enterContactNumberOnAddressPage(contact);
        enterAddressLine1OnAddressPage(Address1);
        enterAddressLine2OnAddressPage(Address2);
        enterStateOnAddressPage(State);
        enterCityOnAddressPage(City);
        enterZipcodeOnAddressPage(Zipcode);
        mobileDriver.hideKeyboard();
        clickSaveButtonOnAddAddressPage();
    }

    @Test(priority = 1, groups = {"neovo", "regression", "iOSAppReset"})
    public void verifyLogoutButtonOnProfilePage() {
        testId = "MAB-T14615";
        String email = "adityanisal@plobalapps.com";
        String password = "qwerty";
        logger.info(testId + " : This test validates logout button shown on my profile page if user logged in");
        ExtentTestManager.getTest().setDescription(testId + " : This test validates logout button shown on my profile page if user logged in");
        clickNoButtonOnOnboardingScreen();
        clickOnMyAccount();
        clickOnLoginButtonOnMyAccountPage();
        enterEmailID(email);
        enterPassword(password);
        clickLogin();
        checkLoginWithValidCreds();
        checkLogoutButtonOnProfilePage();
    }

    @Test(priority = 1, groups = {"neovo", "regression", "iOSAppReset"})
    public void verifyLogoutOnPasswordChange() {
        testId = "MAB-T14614";
        String email = "keithsilva@plobalapps.com";
        String password = "Plobalapps@836";
        logger.info(testId + ": This test verifies that the session is logged out when the user changes the password.");
        ExtentTestManager.getTest().setDescription(testId + ": This test verifies that the session is logged out when the user changes the password.");
        clickNoButtonOnOnboardingScreen();
        clickOnMyAccount();
        clickOnLoginButtonOnMyAccountPage();
        enterEmailID(email);
        enterPassword(password);
        clickLogin();
        waitFor(3);
        ClickOnChangePasswordButton();
        enterNewPasswrod(password);
        enterConfirmPassword(password);
        ChangePasswordOnHomePage();
        waitFor(3);
        if (isPlatformNameAndroid) {
            clickOnOK();
            scrollToText("LOGIN");
        } else
            scrollForIos();
        verifyLoginBtnOnMyProfilePage();
    }

    @Test(groups = {"neovo", "regression", "iOSAppReset"})
    public void verifyChangePasswordFlowsUI() {
        testId = "MAB-T14623";
        String email = "keithsilva@plobalapps.com";
        String password = "Plobalapps@836";
        logger.info(testId + " Test verifies the password change UI.");
        ExtentTestManager.getTest().setDescription(testId + " Test verifies the password change UI");
        clickNoButtonOnOnboardingScreen();
        clickOnMyAccount();
        clickOnLoginButtonOnMyAccountPage();
        enterEmailID(email);
        enterPassword(password);
        clickLogin();
        waitFor(3);
        ClickOnChangePasswordButton();
        enterNewPasswrod(password);
        enterConfirmPassword(password);
        ChangePasswordOnHomePage();
        if (isPlatformNameAndroid)
            verifyToastMessage("Password Successfully Updated", 10);
    }

    @Test(priority = 0, groups = {"neovo", "regression", "iOSAppReset"})
    public void verifyUserShouldbeAbleToEditProfileName() {
        testId = "MAB-T14613";
        String emailID = "sahiltripathi@plobalapps.com";
        String passowrd = "plobal123";
        String newfirstname = "sahill" + randomString(3);
        String newlastname = "Tripathi" + randomString(3);
        logger.info(testId + " : This test validates User can be able to edit profile details");
        ExtentTestManager.getTest().setDescription(testId + " : This test validates User can be able to edit profile details");
        clickNoButtonOnOnboardingScreen();
        clickOnMyAccount();
        clickOnLoginButtonOnMyAccountPage();
        enterEmailID(emailID);
        enterPassword(passowrd);
        clickLogin();
        clickOnEditProfileButtonOnProfilePage();
        waitFor(2);
        enterFirstNameonEditProfile(newfirstname);
        enterLastNameonEditProfile(newlastname);
        clickDoneButtonOnKeyboard();
        clickUpdateProfileButton();
        waitFor(2);
        clickDrawerOnHomePage();
        isUserLoginDispalyed(newfirstname + " " + newlastname);
        waitFor(2);
        mobileDriver.resetApp();
    }

    @Test(priority = 0, groups = {"neovo", "regression", "iOSAppReset"})
    public void checkUserAbleToLogout() {
        testId = "MAB-T14624";
        String email = "mayursavdekar@plobalapps.com";
        String password = "12345";
        logger.info(testId + " : This test validate logout function of user");
        ExtentTestManager.getTest().setDescription(testId + " : This test validate logout function of user");
        clickNoButtonOnOnboardingScreen();
        clickDrawerOnHomePage();
        clickOnSignIn();
        verifyLoginPage();
        enterEmailID(email);
        enterPassword(password);
        clickLogin();
        waitFor(3);
        clickProfileTabOnHomePage();
        clickLoginOrLogoutButtonOnProfilePage();
    }

    @Test(priority = 0, groups = {"neovo", "regression", "iOSAppReset"})
    public void verifyUserDetailsOnProfilePageWhenUserIsNotLoggedIn() {
        testId = "MAB-T14612";
        logger.info(testId + " : This test validate User Details On Profile Page When User Is Not Logged In");
        ExtentTestManager.getTest().setDescription(testId + " : This test validate User Details On Profile Page When User Is Not Logged In");
        clickNoButtonOnOnboardingScreen();
        clickProfileTabOnHomePage();
        waitFor(2);
        verifyNoUserIsLoggedInOnProfilePage();
    }

    @Test(priority = 0, groups = {"neovo", "regression", "iOSAppReset"})
    public void verifyLoginButtonOnProfilePageWhenUserNotLoggedIn() {
        testId = "MAB-T1034";
        logger.info(testId + " : This test validate login button is shown on my profile page when user is not logged in");
        ExtentTestManager.getTest().setDescription(testId + " : This test validate login button is shown on my profile page when user is not logged in");
        clickNoButtonOnOnboardingScreen();
        verifyHomepage();
        clickProfileTabOnHomePage();
        verifyMyAccountPage();
        verifyLoginBtnOnMyProfilePage();
    }

    @Test(priority = 0, groups = {"neovo", "regression", "iOSAppReset"})
    public void verifyUserDataNotDispalyedOnProfilePageWhenUserNotLoggedIn() {
        testId = "MAB-T13866";
        logger.info(testId + " : This test validate User details should not get displayed on my-profile page when user is not loggedIn");
        ExtentTestManager.getTest().setDescription(testId + " : This test validate User details should not get displayed on my-profile page when user is not loggedIn");
        clickNoButtonOnOnboardingScreen();
        verifyHomepage();
        clickProfileTabOnHomePage();
        verifyMyAccountPage();
        verifyLoginBtnOnMyProfilePage();
        isBeforeLoginMessageDisplayedOnProfilePage();
    }

    @Test(priority = 0, groups = {"neovo", "regression", "iOSAppReset"})
    public void verifyAddressFieldDisableBeforeUserLogin() {
        testId = "MAB-T14619";
        String email = "adityanisal@plobalapps.com";
        String password = "qwerty";
        logger.info(testId + " : This test validate user need to login for adding address under profile");
        ExtentTestManager.getTest().setDescription(testId + " : This test validate user need to login for adding address under profile");
        clickNoButtonOnOnboardingScreen();
        clickProfileTabOnHomePage();
        waitFor(2);
        verifyMyAccountPage();
        verifyNoUserIsLoggedInOnProfilePage();
        clickOnLoginButtonOnMyAccountPage();
        verifyLoginPage();
        enterEmailID(email);
        enterPassword(password);
        clickLogin();
        waitFor(3);
        clickProfileTabOnHomePage();
        clickAddressFeatureOnProfilePage();
    }

    @Test(priority = 0, groups = {"neovo", "regression", "iOSAppReset"})
    public void verifyMyOrderDisableBeforeUserLogin() {
        testId = "MAB-T1039";
        String email = "adityanisal@plobalapps.com";
        String password = "qwerty";
        logger.info(testId + " : This test validate click ability of My order section from my profile page when user is not logged in on the app");
        ExtentTestManager.getTest().setDescription(testId + " : This test validate click ability of My order section from my profile page when user is not logged in on the app");
        clickNoButtonOnOnboardingScreen();
        clickProfileTabOnHomePage();
        waitFor(2);
        verifyMyAccountPage();
        verifyNoUserIsLoggedInOnProfilePage();
        if (isPlatformNameAndroid) {
            checkClickablityOfMyOrderButtonOnProfilePage();
        } else {
            checkClickablityOfMyOrderButtonOnProfilePageIos();
            verifyNoUserIsLoggedInOnProfilePage();
        }
    }

    @Test(priority = 0, groups = {"neovo", "regression", "iOSAppReset"})
    public void verifyMyOrderIsClickableOnProfilePageWhenUserLoggedIn() {
        String emailID = "plobaltest121@yopmail.com";
        String password = "test1234";
        testId = "MAB-T15274";
        logger.info(testId + " : This test  validate Clickability of My order section from profile page when user is logged in");
        ExtentTestManager.getTest().setDescription(testId + " : This test  validate Clickability of My order section from profile page when user is logged in");
        clickNoButtonOnOnboardingScreen();
        verifyHomepage();
        clickProfileTabOnHomePage();
        verifyMyAccountPage();
        clickOnLoginButtonOnMyAccountPage();
        verifyLoginPage();
        enterEmailID(emailID);
        enterPassword(password);
        clickLogin();
        verifyMyAccountPage();
        clickMyOrder();
        verifyMyOrdersPage();
        waitFor(2);
        if (isPlatformNameAndroid) {
            clickOnOrderId();
        } else {
            cooClick(170, 130);//This is temporary solution
        }
        waitFor(2);
        verifyOrderDetailsPage();
    }

    @Test(priority = 0, groups = {"neovo", "regression", "iOSAppReset"})
    public void verifyUserDetailsOnProfilePage() {
        testId = "MAB-T15408";
        logger.info(testId + " : This test validate user details(email,Username) when user is logged in successfully from my profile feature tab");
        ExtentTestManager.getTest().setDescription(testId + " : This test validate user details(email,Username) when user is logged in successfully from my profile feature tab");
        clickNoButtonOnOnboardingScreen();
        verifyHomepage();
        clickProfileTabOnHomePage();
        verifyMyAccountPage();
        clickOnLoginButtonOnMyAccountPage();
        enterEmailID("vrushali1@gmail.com");
        enterPassword("test123");
        clickLogin();
        verifyMyAccountPage();
        if (!isPlatformNameAndroid) {
            scrollUpForIos();
        }
        verifyLoginUserDetailsShownAfterLogin("Vrushali QA", "vrushali1@gmail.com");
    }

    @Test(priority = 0, groups = {"neovo", "regression", "iOSAppReset"})
    public void verifyDeletingUserAndCreateNewUser() {
        String emailID = "sahil+tripathi@gmail.com";
        String password = "plobal123";
        String FName = "Sahil";
        String LName = "Tripathi";
        String favProduct = "White Mini Dress";
        testId = "MAB-T33292";
        logger.info(testId + " : This test validate by deleting the user and creating same user and invoke to favorite page");
        ExtentTestManager.getTest().setDescription(testId + " : This test validate by deleting the user and creating same user and invoke to favorite page ");
        clickNoButtonOnOnboardingScreen();
        verifyHomepage();
        clickOnHamburgerButtonFromHomePage();
        clickOnLoginButtonFromHamburgerMenu();
        verifyLoginPage();
        enterEmailID(emailID);
        enterPassword(password);
        clickLogin();
        clickOnListIcon();
        clickOnFashionCollection();
        clickOnDressCollection();
        verifyPlp();
        addProductToFavoritesOnPLP(favProduct);
        clickBackArrowOnNativeApp();
        waitFor(2);
        clickBackButtonIconOnTopLeft();
        clickOnHamburgerButtonFromHomePage();
        clickOnMyProfileFromHamburger();
        clickFavoriteFeatureOnProfilePage();
        isFavoritePageLoaded();
        waitFor(1.5);
        verifyProductOnFavoritePage(favProduct);
        clickBackButtonIconOnFavoritesPage();
        clickOnDeleteAccountOnProfilePage();
        clickOngetYesGoAheadButton();
        waitFor(5);
        verifyLoginBtnOnMyProfilePage();
        clickOnLoginButtonOnMyAccountPage();
        verifyRegisterButtonOnLoginPage();
        clickRegisterButton();
        verifyNewUserRegisterPage();
        enterFirstName(FName);
        enterLastName(LName);
        enterEmailID(emailID);
        enterPassword(password);
        enterConfirmPasswordForNewUser(password);
        selectPolicyCheckBox();
        clickRegisterBtnForNewUser();
        waitFor(5);
        verifyMyAccountPage();
        clickFavoriteFeatureOnProfilePage();
        verifyEmptyFavouritePage();
    }
}



