package qa.automation.testCase.mobile;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;
import qa.automation.base.MobileBaseTest;
import qa.automation.enums.StoreNameEnum;
import qa.automation.report.ExtentTestManager;

import static qa.automation.base.WebBaseTest.randomNum;


public class AddressPageTestCases extends MobileBaseTest {
    private static final Logger logger= Logger.getLogger(AddressPageTestCases.class);


    @Test(priority = 0, groups = {"neovo", "iOSFailure", "androidFailure", "regression"})
    public void verifyDefaultCountryBasedOnUserLocation() {
        testId = "MAB-T15758";
        logger.info(testId+" : This test validates Default Country Based On User Location On Address Page");
        ExtentTestManager.getTest().setDescription(testId+" : This test validates Default Country Based On User Location On Address Page");
        clickNoButtonOnOnboardingScreen();
        verifyHomepage();
        clickProductBannerOnHomePage();
        waitFor(1);
        clickOnBuyNowFromPDP();
        if(isPlatformNameAndroid){
        clickOnConfirm();}
        waitFor(3);
        verifyAddressPage();
        clickOnCountry();
        isDefaultCountryDisplay("India");
    }

    @Test(priority = 0, groups = {"neovo", "iOSFailure", "androidFailure", "regression"})
    public void verifyStateDropdownBasedOnDefaultCountry() {
        testId = "MAB-T15753";
        logger.info(testId+" : This test validates State Dropdown Based On Default Country On Address Page");
        ExtentTestManager.getTest().setDescription(testId+" : This test validates State Dropdown Based On Default Country On Address Page");
        clickNoButtonOnOnboardingScreen();
        verifyHomepage();
        clickProductBannerOnHomePage();
        waitFor(1);
        verifyPdp();
        clickOnBuyNowFromPDP();
        if (isPlatformNameAndroid) {
            clickOnConfirm();
        }
        waitFor(7);
        verifyAddressPage();
        if (isPlatformNameAndroid){
            scrollToText("State");
        }else {
            scrollForIos();
        }
        clickOnState();
        isListOfStateDisplay("Andhra Pradesh");
    }


    @Test(priority = 0, groups = { "neovo","regression", "iOSAppReset"})
    public void verifyAddressForm() {
        testId = "MAB-T15106";
        String email = "keithsilva@plobalapps.com";
        String password = "Plobalapps@836";
        logger.info(testId + " Test verifies the password change UI.");
        ExtentTestManager.getTest().setDescription(testId + " Test verifies the password change UI");
        clickNoButtonOnOnboardingScreen();
        verifyHomepage();
        clickProfilePage();
        isMyProfileDispalyed();
        clickOnLoginButtonOnMyAccountPage();
        enterEmailID(email);
        enterPassword(password);
        clickLogin();
        waitFor(3);
        clickAddressFeatureOnProfilePage();
        clickAddNewAddressButton();
        verifyAddressFormFields();
    }

    @Test(priority = 0, groups = {"neovo","regression","iOSAppReset"})
    public void verifyDeleteAlreadyAddedAddress() {
        String email = "leo121@gmail.com";
        String password = "qwerty";
        String Fname = "Sahil";
        String Lname = "Tripathi";
        String Company = "Plobal";
        String contact = "1234567890";
        String Address1 = "Bavdhan";
        String Address2 = "Sai Velocity";
        String State = "Maharashtra";
        String City = "Pune";
        String Zipcode = "411021";
        testId = "MAB-T14935";
        logger.info(testId + ": This test validate whether user is able to delete already added address on address page");
        ExtentTestManager.getTest().setDescription(testId + ": This test validate whether user is able to delete already added address on address page");
        clickNoButtonOnOnboardingScreen();
        verifyHomepage();
        clickOnHamburgerButtonFromHomePage();
        clickOnSignIn();
        enterEmailID(email);
        enterPassword(password);
        clickLogin();
        clickOnHamburgerButtonFromHomePage();
        clickOnMyProfileFromHamburger();
        clickAddressFeatureOnProfilePage();
        waitFor(5);
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
        waitFor(1);
        mobileDriver.hideKeyboard();
        clickSaveButtonOnAddAddressPage();
        if(isPlatformNameAndroid){
            waitFor(1.5);
            clickOnOK();
            waitFor(2);
            clickAddressFeatureOnProfilePage();
        }
        clickOnRemoveAddressFromProfilePage();
        if(isPlatformNameAndroid){
            clickYesOnPopup();
            waitFor(2);
        }
        waitFor(5);
        isAddNewAddressButtonDisplayed();
    }

    @Test(priority = 0, groups = {"neovo","regression", "androidOnly"})
    public void checkUserCanEditAddress() {
        testId = "MAB-T1047";
        String email="mayursavdekar@plobalapps.com";
        String password="12345";
        String contact="869878"+randomNum();
        logger.info("This test validate address update functionality");
        ExtentTestManager.getTest().setDescription("This test validate address update functionality");
        clickNoButtonOnOnboardingScreen();
        verifyHomepage();
        clickProfileTabOnHomePage();
        isMyProfileDispalyed();
        clickLoginOrLogoutButtonOnProfilePage();
        verifyLoginPage();
        enterEmailID(email);
        enterPassword(password);
        clickLogin();
        clickAddressFeatureOnProfilePage();
        clickEditAddressOnAddressPage();
        clearContactNumberTextboxOnAddAddressPage();
        enterContactNumberOnAddressPage(contact);
        clickSaveButtonOnAddAddressPage();
        waitFor(3);
        verifyTextPresentInAddress(contact);
    }


    @Test(priority = 0, groups = {"neovo","regression","iOSAppReset"})
    public void checkByAddingNewAddressForNewlyRegisteredUser(){
        String OS=System.getenv("platform");
        testId="MAB-T13887";
        String addressSavedMessage="Your address have been added to your account.";
        logger.info(testId +" : This test validates Add address functionality for newly registered user");
        ExtentTestManager.getTest().setDescription(testId +" :This test validates Add address functionality for newly registered user");
        String firstname="Plobal";
        String lastname="Automation";
        String email="plobalautomation"+randomNum()+""+randomNum()+"@gmail.com"; // added randomNum() method twice so that the chances of getting unique mail ID increases.
        String password="12345";
        String contactNumber="1234567890";
        String addressLine1="abcd";
        String addressLine2="wxyz";
        String state="MH";
        String city="Pune";
        String zipcode="123456";
        clickNoButtonOnOnboardingScreen();
        verifyHomepage();
        clickProfileTabOnHomePage();
        if(!isPlatformNameAndroid){
            scrollForIos();
        }
        isMyProfileDispalyed();
        clickOnLoginButtonOnMyAccountPage();
        clickRegisterButton();
        enterFirstName(firstname);
        enterLastName(lastname);
        enterEmailID(email);
        enterPassword(password);
        enterConfirmPasswordForNewUser(password);
        selectPolicyCheckBox();
        clickRegisterBtnForNewUser();
        waitFor(5);
        clickAddressFeatureOnProfilePage();
        clickAddNewAddressButton();
        enterFirstnameAccountPage(firstname);
        enterLastnameAccountPage(lastname);
        enterContactNumberOnAddressPage(contactNumber);
        enterAddressLine1OnAddressPage(addressLine1);
        enterAddressLine2OnAddressPage(addressLine2);
        enterStateOnAddressPage(state);
        enterCityOnAddressPage(city);
        enterZipcodeOnAddressPage(zipcode);
        if(!isPlatformNameAndroid){
            clickCancleButtonOnMobileKeyboard();
        }
        clickSaveButtonOnAddAddressPage();
        waitFor(1.4);
        if(isPlatformNameAndroid){
            verifyAddressSavedSuccessfullyTextOnAddressPagePopup(addressSavedMessage);
            clickOnOK();
        }
        else{
            verifyTextPresentInAddress(contactNumber);
            verifyTextPresentInAddress(city);
            verifyTextPresentInAddress(addressLine1);
            verifyTextPresentInAddress(addressLine2);
            verifyTextPresentInAddress(state);
        }
    }

    @Test(priority = 0, groups = {"sandbox","regression","iOSAppReset"})
    public void verifyRemoveAddressFromCheckoutPage() {
        String email = "leo@gmail.com";
        String password = "qwerty";
        String Fname = "Sahil";
        String Lname = "Tripathi";
        String Company = "Plobal";
        String contact = "1234567890";
        String Address1 = "Bavdhan";
        String Address2 = "Sai Velocity";
        String State = "Maharashtra";
        String City = "Pune";
        String Zipcode = "411021";
        testId = "MAB-T13891";
        logger.info(testId + " : This test validates that deleting address from checkout page when only 1 address available in the user profile");
        ExtentTestManager.getTest().setDescription(testId + " : This test validates that deleting address from checkout page when only 1 address available in the user profile");
        selectStore(StoreNameEnum.SANDBOX);
        verifyHomepage();
        clickOnHamburgerButtonFromHomePage();
        clickOnLoginButtonFromHamburgerMenu();
        enterEmailID(email);
        enterPassword(password);
        clickLogin();
        clickOnHamburgerButtonFromHomePage();
        clickOnMyProfileFromHamburger();
        clickAddressFeatureOnProfilePage();
        waitFor(3);
        try {
            clickOnRemoveAddressFromCheckoutPage();
            if(isPlatformNameAndroid){
                clickYesOnPopup();
            }
        }catch (Exception e){

        }
        waitFor(3);
        clickAddNewAddressButton();
        enterFirstnameOnAddressPage(Fname);
        enterLastnameOnAddressPage(Lname);
        enterCompanyNameOnAddressPage(Company);
        enterContactNumberOnAddressPage(contact);
        enterAddressLine1OnAddressPage(Address1);
        enterAddressLine2OnAddressPage(Address2);
        enterStateOnAddressPage(State);
        enterCityOnAddressPage(City);
        enterZipcodeOnAddressPage(Zipcode);
        mobileDriver.hideKeyboard();
        clickSaveButtonOnAddAddressPage();
        waitFor(3);
        if(isPlatformNameAndroid){
            clickOnOkPopup();
        }
        else {
            waitFor(1);
            closeAddressPopup();
        }
        clickOnHamburgerButtonFromHomePage();
        clickOnHomePageFromHamburger();
        clickOnProductFromGrid();
        clickAddToCartOnPDP();
        waitFor(2);
        clickOnGoToCart();
        clickOnPlaceOrder();
        verifyCTAPopup();
        clickOnCreditCardCTA();
        checkNativeCheckoutPage();
        clickOnShippingAddressOnCheckoutPage();
        if(isPlatformNameAndroid) {
            waitFor(3);
            clickOnRemoveAddressFromCheckoutPage();
            clickYesOnPopup();
        }
        else{
            scrollForIos();
            waitFor(5);
            clickOnRemoveAddressFromCheckoutPage();
        }
        waitFor(1);
        checkEmptyAddressSectionOnCheckoutPage();
    }
    @Test(priority = 0,groups = {"neovo","regression","iOSAppReset"})
    public void checkAddAddressFunctionalityForExistingUser() {
        testId = "MAB-T13890";
        logger.info(testId + " : This test validates Add address functionality for existing user ");
        ExtentTestManager.getTest().setDescription(testId + " :This test validates Add address functionality for existing user");
        String firstname = "Plobal" + randomNum();
        String lastname = "Automation" + randomNum();
        String companyName="Plobal Tech";
        String emailForiOS = "mgs@gmail.com";
        String emailForAndroid = "lgs@gmail.com";
        String password = "12345";
        String contactNumber = "91"+randomNum()+""+randomNum();
        String addressLine1 = "abcd"+randomNum();
        String addressLine2 = "wxyz"+randomNum();
        String state = "MH"+randomNum();
        String city = "Pune"+randomNum();
        String zipcode = randomNum()+""+randomNum();
        clickNoButtonOnOnboardingScreen();
        verifyHomepage();
        clickProfileTabOnHomePage();
        if (!isPlatformNameAndroid) {
            scrollForIos();
        }
        clickLoginOrLogoutButtonOnProfilePage();
        if(isPlatformNameAndroid){
            enterEmailID(emailForAndroid);
        }
        else{
            enterEmailID(emailForiOS);
        }
        enterPassword(password);
        clickLogin();
        waitFor(2);
        clickAddressFeatureOnProfilePage();
        waitFor(3);
        clickAddNewAddressButton();
        enterFirstnameAccountPage(firstname);
        enterLastnameAccountPage(lastname);
        enterCompanyNameOnAddressPage(companyName);
        enterContactNumberOnAddressPage(contactNumber);
        enterAddressLine1OnAddressPage(addressLine1);
        enterAddressLine2OnAddressPage(addressLine2);
        enterStateOnAddressPage(state);
        enterCityOnAddressPage(city);
        enterZipcodeOnAddressPage(zipcode);
        if (!isPlatformNameAndroid) {
            clickDoneButtonOnIOSKeyboard();
        }
        clickSaveButtonOnAddAddressPage();
        waitFor(3);
        verifyTextPresentInAddress(addressLine1);
        verifyTextPresentInAddress(addressLine2);
        verifyTextPresentInAddress(city);
        verifyTextPresentInAddress(state);
        verifyTextPresentInAddress(zipcode);
        verifyTextPresentInAddress(contactNumber);
        verifyTextPresentInAddress(companyName);
        if (isPlatformNameAndroid){
            clickOnRemoveButtonFromProfilePage();
            clickYesOnPopup();
        }
    }

    @Test(priority = 0, groups = {"sandbox","regression","iOSAppReset"})
    public void verifyAddAddressFunctionalityForNewRegisteredUserFromNativeCheckoutPage() {
        testId = "MAB-T15104";
        logger.info(testId+" : This test validates address add functionality for new registered user from checkout page (Native checkout)");
        ExtentTestManager.getTest().setDescription(testId+" : This test validates address add functionality for new registered user from checkout page (Native checkout)");
        String email= "plobaltest"+randomNum()+randomNum()+"@yopmail.com";
        selectStore(StoreNameEnum.SANDBOX);
        verifyHomepage();
        clickOnMyAccountForSandbox();
        verifyMyAccountPage();
        clickOnLoginButtonOnMyAccountPage();
        verifyLoginPage();
        clickRegisterButton();
        verifyNewUserRegisterPage();
        enterFirstName("Plobal"+randomNum());
        enterLastName("Test");
        enterEmailID(email);
        enterPassword("test1234");
        enterConfirmPasswordForNewUser("test1234");
        clickRegisterBtnForNewUser();
        waitFor(2);
        clickOnHomeIcon();
        clickOnProductFromGrid();
        verifyPdp();
        clickOnBuyNowFromPDP();
        waitFor(1.5);
        clickOnOtherButtonCTA();
        waitFor(3.5);
        clickOnAddAddressPlusIconOnCheckoutPage();
        waitFor(3);
        enterFirstnameOnAddressPage("Plobal");
        String lname1="Test"+randomNum();
        enterLastnameOnAddressPage(lname1);
        enterCompanyNameOnAddressPage("Plobal");
        enterContactNumberOnAddressPage("91"+randomNum()+randomNum());
        enterAddressLine1OnAddressPage("Bavdhan");
        enterAddressLine2OnAddressPage("Sai Velocity");
        enterStateOnAddressPage("Maharashtra");
        enterCityOnAddressPage("Pune");
        enterZipcodeOnAddressPage("411021");
        waitFor(1);
        mobileDriver.hideKeyboard();
        clickSaveButtonOnAddAddressPage();
        if(isPlatformNameAndroid){
            clickOnOkPopup();
        }
        waitFor(1);
        isAddressNameDisplayedOnAddressPage("Plobal "+lname1);
    }

}

