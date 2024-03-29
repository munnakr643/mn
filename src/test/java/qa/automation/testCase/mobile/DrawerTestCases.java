package qa.automation.testCase.mobile;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;
import qa.automation.base.MobileBaseTest;
import qa.automation.enums.StoreNameEnum;
import qa.automation.report.ExtentTestManager;

public class DrawerTestCases extends MobileBaseTest {

    private static final Logger logger = Logger.getLogger(DrawerTestCases.class);


    @Test(groups = {"neovo", "Regression"})
    public void verifyEmptyCartFromHamburgerMenu() {
        testId = "MAB-T15913";
        logger.info(testId + " This test validates Invoke cart page from Hamburger Menu when cart is empty");
        ExtentTestManager.getTest().setDescription(testId + " This test validates Invoke cart page from Hamburger Menu when cart is empty");
        clickNoButtonOnOnboardingScreen();
        verifyHomepage();
        clickDrawerOnHomePage();
        clickOnCartHamburgerMenu();
        verifyEmptyCartPage();
    }

    @Test(priority = 1, groups = {"neovo", "regression"})
    public void clickOnMorePageFromHamburger() {
        testId = "MAB-T15923";
        logger.info(testId + " : This test validates when the user taps on More from the hamburger menu able to redirect to more page.");
        ExtentTestManager.getTest().setDescription(testId + " : This test validates when the user taps on More from the hamburger menu able to redirect to more page.");
        clickNoButtonOnOnboardingScreen();
        verifyHomepage();
        clickOnHamburgerButtonFromHomePage();
        ClickMoreOnHamburger();
        verifyMorePage();
    }

    @Test(priority = 1, groups = {"neovo", "regression"})
    public void verifySubcategoryOnHamburger() {
        testId = "MAB-T15922";
        logger.info(testId + " : This test validates that when the user taps on one or more categories from the hamburger menu, subcategories will open after taping on subcategories Product List Page(PLP) should appear.");
        ExtentTestManager.getTest().setDescription(testId + " : This test validates that when the user taps on one or more categories from the hamburger menu, subcategories will open after taping on subcategories Product List Page(PLP) should appear.");
        clickNoButtonOnOnboardingScreen();
        verifyHomepage();
        clickOnHamburgerButtonFromHomePage();
        clickOnCategoryOnHamburger();
        clickSubcategoryOnHamburger();
        verifyPlp();
    }

    @Test(priority = 1, groups = {"neovo", "regression"})
    public void verifyHamburgerMenu() {
        testId = "MAB-T15411";
        logger.info(testId + " : This test validates Hamburger menu icon is visible on the top left side of the home page.");
        ExtentTestManager.getTest().setDescription("This test validates Hamburger menu icon is visible on the top left side of the home page.");
        clickNoButtonOnOnboardingScreen();
        verifyHomepage();
        clickOnHamburgerButtonFromHomePage();
    }

    @Test(priority = 1, groups = {"neovo", "regression"})
    public void openHamburgerMenu() {
        testId = "MAB-T15412";
        logger.info(testId + " : Verify Hamburger menu can be able to open by tapping the Hamburger Menu icon on the left side of the home page.");
        ExtentTestManager.getTest().setDescription("Verify Hamburger menu can be able to open by tapping the Hamburger Menu icon on the left side of the home page.");
        clickNoButtonOnOnboardingScreen();
        verifyHomepage();
        clickOnHamburgerButtonFromHomePage();
    }

    @Test(priority = 1, groups = {"neovo", "regression"})
    public void verifyHamburgerMenuClosed() {
        testId = "MAB-T15413";
        logger.info(testId + " : Check that hamburger menu gets closed by tapping outside of the area");
        ExtentTestManager.getTest().setDescription(testId + " : Check that hamburger menu gets closed by tapping outside of the area");
        clickNoButtonOnOnboardingScreen();
        verifyHomepage();
        clickOnHamburgerButtonFromHomePage();
        clickforHamburgerClose();
    }

    @Test(priority = 1, groups = {"neovo", "regression"})
    public void verifySignInOnHamburgerMenu() {
        testId = "MAB-T15419";
        logger.info(testId + " : This test validates Sign in redirection Hamburger menu on homepage");
        ExtentTestManager.getTest().setDescription("This test validates Sign in redirection Hamburger menu on homepage");
        clickNoButtonOnOnboardingScreen();
        verifyHomepage();
        clickOnHamburgerButtonFromHomePage();
        clickOnLoginButtonFromHamburgerMenu();
        verifyLoginPage();
    }

    @Test(priority = 1, groups = {"neovo", "regression", "iOSAppReset"})
    public void verifyUserNameOnHamburgerMenu() {
        testId = "MAB-T15426";
        String email = "adityanisal@plobalapps.com";
        String password = "qwerty";
        String username = "Aditya Nisal";
        logger.info(testId + " : This test validates that User details(First Name, Last Name) are showing on hamburger menu after user register/login to app");
        ExtentTestManager.getTest().setDescription(testId + ": This test validates that User details(First Name, Last Name) are showing on hamburger menu after user register/login to app");
        clickNoButtonOnOnboardingScreen();
        verifyHomepage();
        clickOnHamburgerButtonFromHomePage();
        clickOnLoginButtonFromHamburgerMenu();
        enterEmailID(email);
        enterPassword(password);
        clickLogin();
        waitFor(2);
        clickOnHamburgerButtonFromHomePage();
        checkUserNameOnHamburger(username);
    }

    @Test(priority = 0, groups = {"neovo", "regression", "iOSAppReset"})
    public void verifyLoginButtonAfterSessionLogoutUnderHamburgerMenu() {
        testId = "MAB-T15901";
        logger.info(testId + " This test validates The login button should appear again after user session logout");
        ExtentTestManager.getTest().setDescription(testId + " This test validates The login button should appear again after user session logout");
        clickNoButtonOnOnboardingScreen();
        verifyHomepage();
        clickOnMyAccount();
        verifyMyAccountPage();
        clickOnLoginButtonOnMyAccountPage();
        verifyLoginPage();
        enterEmailID("plobaltest121@yopmail.com");
        enterPassword("test1234");
        clickLogin();
        verifyMyAccountPage();
        clickDrawerOnHomePage();
        isUserLoginDispalyed("Dev Test");
        clickOnSignIn();
        clickLoginOrLogoutButtonOnProfilePage();
        if (isPlatformNameAndroid) {
            clickOnOK();
        }
        waitFor(4.5);
        clickDrawerOnHomePage();
        isUserLoginDispalyed("Login or Register");
    }


    @Test(priority = 0, groups = {"neovo", "regression"})
    public void verifyMyprofilePageUnderHamburgerMenu() {
        testId = "MAB-T15908";
        logger.info(testId + " This test validates Invoke cart page from Hamburger Menu when cart is empty");
        ExtentTestManager.getTest().setDescription(testId + " This test validates Invoke cart page from Hamburger Menu when cart is empty");
        clickNoButtonOnOnboardingScreen();
        verifyHomepage();
        clickDrawerOnHomePage();
        clickOnMyProfileFromHamburger();
        isMyProfileDispalyed();
    }

    @Test(priority = 0, groups = {"neovo", "regression", "iOSAppReset"})
    public void verifyHamburgerMenuAfterSwipingLeftToRight() {
        testId = "MAB-T15415";
        logger.info(testId + " This test validates Hamburger menu is able to open by swiping left to right side");
        ExtentTestManager.getTest().setDescription(testId + " This test validates Hamburger menu is able to open by swiping left to right side");
        clickNoButtonOnOnboardingScreen();
        verifyHomepage();
        if (isPlatformNameAndroid) {
            swipe(10, 1000, 900, 1000);
        } else {
            swipeForIos("left");
        }
        isHamburgerMenuPageDispalyed();
        isUserLoginDispalyed("Login or Register");
    }

    @Test(groups = {"neovo", "regression"})
    public void verifyMorePageAppearOnHamburger() {
        testId = "MAB-T15914";
        logger.info(testId + " This test verified More Page option appear on Hamburger Menu");
        ExtentTestManager.getTest().setDescription(testId + " This test verified More Page option appear on Hamburger Menu");
        clickNoButtonOnOnboardingScreen();
        verifyHomepage();
        clickDrawerOnHomePage();
        verifyMorePageOnHamburger();
    }

    @Test(groups = {"neovo", "regression", "iOSAppReset"})
    public void verifyHamburgerMenuCloseOnRightToLeftSwipe() {
        testId = "MAB-T15416";
        logger.info(testId + " This test validates Hamburger menu is able to close on right to left swipe");
        ExtentTestManager.getTest().setDescription(testId + " This test validates Hamburger menu is able to close on right to left swipe");
        clickNoButtonOnOnboardingScreen();
        verifyHomepage();
        if (isPlatformNameAndroid) {
            swipe(10, 1000, 900, 1000);
        } else {
            swipeForIos("left");
        }
        isHamburgerMenuPageDispalyed();
        if (isPlatformNameAndroid) {
            swipe(900, 1000, 10, 1000);
        } else {
            swipeForIos("right");
        }
        verifyHomepage();
    }

    @Test(groups = {"neovo", "Regression", "iOSAppReset"})
    public void verifyCartPageNavigationWhenProductsInCart() {
        testId = "MAB-T15912";
        logger.info(testId + " This test verifies if the navigation from Hamburger menu to cart page works when products are in cart.");
        ExtentTestManager.getTest().setDescription(testId + " This test verifies if the navigation from Hamburger menu to cart page works when products are in cart.");
        clickNoButtonOnOnboardingScreen();
        verifyHomepage();
        clickOnListIcon();
        clickOnFashionCollection();
        clickOnDressCollection();
        waitFor(2);
        verifyPlp();
        String productName = "White Mini Dress";
        clickProductInPLP(productName);
        waitFor(4);
        clickAddToCartOnPDP();
        if (isPlatformNameAndroid) {
            clickOnConfirm();
        }
        clickBackButtonIconOnTopLeft();
        waitFor(2);
        clickBackButtonIconOnTopLeft();
        waitFor(2);
        clickBackButtonIconOnCollectionPage();
        waitFor(2);
        clickHomeTab();
        waitFor(2);
        if (isPlatformNameAndroid) {
            swipe(10, 1000, 900, 1000);
        } else {
            swipeForIos("left");
        }
        clickOnCartHamburgerMenu();
        verifyCartPage();
        verifyProductInCartpage(productName);
    }

    @Test(groups = {"neovo", "regression"})
    public void verifyCategoryRedirectionfromHamburgerMenuWhenOnCategoryPage() {
        testId = "MAB-T15907";
        logger.info(testId + " This test verifies if user is on category page check whether user is able to invoke catagory page from hamburger menu");
        ExtentTestManager.getTest().setDescription(testId + " This test verifies if user is on category page check whether user is able to invoke catagory page from hamburger menu");
        clickNoButtonOnOnboardingScreen();
        verifyHomepage();
        clickOnListIcon();
        clickOnHamburgerButtonFromHomePage();
        waitFor(2);
        clickOnCategoryOnHamburger();
        waitFor(2);
        clickTextOnPage("Dress");
        verifyPlp();
    }

    @Test(groups = {"neovo", "regression"})
    public void verifyHomepageInvocationOnHamburgerMenuWhenOnHomePage() {
        testId = "MAB-T15904";
        logger.info(testId + " This test verifies if user redirection to Homepage from hamburger menu when already on Homepage");
        ExtentTestManager.getTest().setDescription(testId + " This test verifies if user redirection to Homepage from hamburger menu when already on Homepage");
        clickNoButtonOnOnboardingScreen();
        verifyHomepage();
        clickOnHamburgerButtonFromHomePage();
        clickOnHomePageFromHamburger();
        verifyTextOnPage("Footware");
    }

    @Test(groups = {"neovo", "regression", "iOSAppReset"})
    public void verifyLoginPageInvocationFromHamburgerMenu() {
        testId = "MAB-T15899";
        logger.info(testId + " This test verifies invoking login button from hamburger menu user should get redirected to login page");
        ExtentTestManager.getTest().setDescription(testId + " This test verifies invoking login button from hamburger menu user should get redirected to login page");
        clickNoButtonOnOnboardingScreen();
        verifyHomepage();
        clickOnHamburgerButtonFromHomePage();
        isHamburgerMenuPageDispalyed();
        isUserLoginDispalyed("Login or Register");
        clickOnSignIn();
        isLoginPageDisplayed();
    }

    @Test(groups = {"neovo", "regression"})
    public void verifyCategoryTabUnderHamburgerMenu() {
        testId = "MAB-T15905";
        logger.info(testId + " This test verifies Category tab should appear under hamburger menu");
        ExtentTestManager.getTest().setDescription(testId + " This test verifies Category tab should appear under hamburger menu");
        clickNoButtonOnOnboardingScreen();
        verifyHomepage();
        clickOnHamburgerButtonFromHomePage();
        isHamburgerMenuPageDispalyed();
        isCategoryTabDisplayUnderHamburger();

    }
    @Test(groups = {"neovo", "regression"})
    public void verifySubcategoryOnClickOnCategoryFromHamburger() {
        testId = "MAB-T15431";
        logger.info(testId + " This test Verifies subcategories is displayed when user click on the category from the hamburger menu");
        ExtentTestManager.getTest().setDescription(testId + " This test Verifies subcategories is displayed when user click on the category from the hamburger menu");
        clickNoButtonOnOnboardingScreen();
        verifyHomepage();
        clickOnHamburgerButtonFromHomePage();
        isHamburgerMenuPageDispalyed();
        isCategoryTabDisplayUnderHamburger();
        clickOnCategoryOnHamburger();
        clickSubcategoryOnHamburger();
    }
}

