package qa.automation.pageScreenDevice;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.support.CacheLookup;
import qa.automation.pageScreenDevice.manager.ScreenManager;
@Getter
@Setter
public class LoginPage extends ScreenManager {


	public LoginPage(AppiumDriver<MobileElement> driver)
	{
		super(driver);
	}

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Welcome Back']")
	@CacheLookup
	public MobileElement welcomeBack;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Log In']")
	@CacheLookup
	public MobileElement loginText;

	@AndroidFindBy(xpath="//android.widget.TextView[contains(@text,'Phone Number')]")
	@CacheLookup
	public MobileElement phoneEmail;

	@AndroidFindBy(xpath="//android.widget.EditText[contains(@text,'Enter Phone ')]")
	@CacheLookup
	public MobileElement userId;

	@AndroidFindBy(xpath="//android.widget.TextView[contains(@text,'Sign Up')]")
	@CacheLookup
	public MobileElement signUp;

	@AndroidFindBy(xpath="//android.widget.Button[@text='Continue']")
	@CacheLookup
	public MobileElement continueBtn;

	@AndroidFindBy(xpath = "//android.widget.TextView[contains(@resource-id,'textinput_error')]")
	@iOSXCUITFindBy(xpath = "(//XCUIElementTypeStaticText[contains(@name,'staging Neovo-Commerce')])[2]//following-sibling::XCUIElementTypeStaticText")
	public MobileElement errorMessage;

	@AndroidFindBy(xpath="//android.widget.ImageView[contains(@resource-id,'backPress')]")
	@CacheLookup
	public MobileElement backBtn;

	@AndroidFindBy(xpath="//android.widget.TextView[contains(@resource-id,'selectedCountry')]")
	@CacheLookup
	public MobileElement countryCode;

	@AndroidFindBy(xpath="//android.widget.LinearLayout[contains(@resource-id,'flag')]")
	@CacheLookup
	public MobileElement flag;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Verify OTP']")
	@CacheLookup
	public MobileElement verifyOtp;

	@AndroidFindBy(xpath="//android.widget.TextView[contains(@text,'Please enter the')]")
	@CacheLookup
	public MobileElement otpMessage;

	@AndroidFindBy(xpath="//android.widget.Button[@text='Confirm']")
	@CacheLookup
	public MobileElement confirmBtn;

	@AndroidFindBy(xpath="//android.widget.FrameLayout[contains(@resource-id,'otpTextView')]")
	@CacheLookup
	public MobileElement otpbox;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeCell[@name='25']")
	@AndroidFindBy(xpath="//android.widget.RelativeLayout[contains(@resource-id,'layout_cart')]//parent::android.widget.LinearLayout/android.widget.ImageView[2]")
	public MobileElement listicon;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeCell[@name='27']")
	@AndroidFindBy(xpath="//android.widget.RelativeLayout[contains(@resource-id,'layout_cart')]//parent::android.widget.LinearLayout/android.widget.RelativeLayout")
	public MobileElement myAccount;

	@AndroidFindBy(xpath="(//android.widget.ImageView[contains(@resource-id,'popular_offer_1')])[2]")
	@CacheLookup
	public MobileElement sales;

	@AndroidFindBy(xpath="//android.widget.Button[@text='CHANGE']")
	@CacheLookup
	public MobileElement change;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='PLACE ORDER']")
	@AndroidFindBy(xpath="//android.widget.Button[@text='PLACE ORDER']")
	public MobileElement placeOrder;

	@AndroidFindBy(xpath="//android.view.View[@text='Email or mobile phone number']")
	public MobileElement emailCheckout;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeTextField[@value='First Name']")
	@AndroidFindBy(xpath="//android.widget.EditText[@text='Name']")
	public MobileElement fName;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeTextField[@value='Last Name']")
	@AndroidFindBy(xpath="//android.widget.EditText[@text='Last Name']")
	public MobileElement lName;

	@AndroidFindBy(xpath="//android.view.View[@text='Address']")
	@CacheLookup
	public MobileElement address;

	@AndroidFindBy(xpath="//android.view.View[contains(@text,'Apartment')]")
	@CacheLookup
	public MobileElement apartment;

	@AndroidFindBy(xpath="//android.view.View[@text='City']")
	@CacheLookup
	public MobileElement city;

	@AndroidFindBy(xpath="//android.view.View[@text='PIN code']")
	@CacheLookup
	public MobileElement pincode;

	@AndroidFindBy(xpath="//android.widget.CheckBox[contains(@text,'Save this information')]")
	@CacheLookup
	public MobileElement saveInfo;

	@AndroidFindBy(xpath="//android.widget.Button[contains(@resource-id,'continue_button')]")
	@CacheLookup
	public MobileElement continueButton;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='Continue to payment']")
	@AndroidFindBy(xpath="//android.widget.Button[@text='Continue to payment']")
	public MobileElement continueToPay;

	@AndroidFindBy(id="neovocommercefootware.android.staging:id/imageView_navigation_view")
	@CacheLookup
	public MobileElement hamburgerMenu;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Phone Number']")
	@CacheLookup
	public MobileElement PhoneNumber;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Sign In']")
	@CacheLookup
	public MobileElement signIn;

	@AndroidFindBy(xpath="//android.widget.EditText[contains(@text,'Email')]")
	@CacheLookup
	public MobileElement enterEmailAddress;

	@AndroidFindBy(xpath="//android.widget.TextView[contains(@text,'Complete Your Profile')]")
	@CacheLookup
	public MobileElement completeYourProfile;

	@AndroidFindBy(xpath="//android.widget.TextView[contains(@text,'Add name to')]")
	@CacheLookup
	public MobileElement adNameTo;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Full Name']")
	@CacheLookup
	public MobileElement fullNameTxt;

	@AndroidFindBy(xpath="//android.widget.EditText[@text='Enter Full Name']")
	@CacheLookup
	public MobileElement enterFullName;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Email Address']")
	@CacheLookup
	public MobileElement emailAddressTxt;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='City']")
	@CacheLookup
	public MobileElement CityTxt;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Bengaluru']")
	@CacheLookup
	public MobileElement bengaluruCity;

	@AndroidFindBy(xpath="//android.widget.Button[contains(@resource-id,'signUpContinue')]")
	@CacheLookup
	public MobileElement signUpContinueBtn;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Search']")
	@CacheLookup
	public MobileElement search;

	@AndroidFindBy(xpath="//android.widget.EditText[@text='Search']")
	@CacheLookup
	public MobileElement searchEdit;

	@AndroidFindBy(xpath="//android.widget.ImageView[contains(@resource-id,'app_icon')]")
	@CacheLookup
	public MobileElement appIcon1;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='LIVE']")
	@CacheLookup
	public MobileElement liveIcon;

	/**
	 * Owner : Shashi More
	 * Check Login and change password functionality
	 */

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeTextField[@value='Email']")
	@AndroidFindBy(xpath="//android.widget.EditText[@text='Email']")
	public MobileElement emailTextField;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeSecureTextField[@value='Password']")
	@AndroidFindBy(xpath="//android.widget.EditText[@text='Password']")
	public MobileElement passwordTextField;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Login' or @name='LOGIN']")
	@AndroidFindBy(xpath="//android.widget.TextView[contains(@resource-id,'btn_login')]")
	public MobileElement login;

	@AndroidFindBy(xpath="//android.widget.Button[@text='LOGOUT']")
	@CacheLookup
	public MobileElement logoutButtonOnProfilePage;


	public void enterEmailID(String email){
		emailTextField.sendKeys(email);
	}
	public void enterPassword(String password){
		passwordTextField.sendKeys(password);
	}

	@iOSXCUITFindBy(xpath = "(//XCUIElementTypeStaticText[@name='Login'])[2]")
	@AndroidFindBy(xpath = "(//android.widget.TextView[@resource-id,'textview_title'])[1]")
	MobileElement loginTextOnLoginPage;

	@iOSXCUITFindBy(xpath = "(//XCUIElementTypeStaticText[@name='Login'])[3] | //XCUIElementTypeButton[@name='Login' or @name='LOGIN']")
	@AndroidFindBy(xpath = "//android.widget.TextView[contains(@resource-id,'btn_login')]")
	MobileElement loginButtonOnLoginPage;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Register']")
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Register']")
	MobileElement registerButton;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeNavigationBar[@name='TopTabBarView']/following-sibling::XCUIElementTypeTable/XCUIElementTypeCell[1]/XCUIElementTypeStaticText[1]")
	@AndroidFindBy(xpath = "//android.widget.TextView[contains(@resource-id,'textView_left_title')]")
	MobileElement signNow;

	@AndroidFindBy(xpath = "//android.widget.TextView[contains(@resource-id,'textView_right_title')]")
	@CacheLookup
	MobileElement joinNow;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[contains(@name,'More')]")
	@AndroidFindBy(xpath = "//android.widget.TextView[contains(@text,'More')]")
	MobileElement moreBtn;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Show Your Love']")
	@CacheLookup
	MobileElement showYourLove;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Info']")
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Info']")
	MobileElement infotext;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Logout']")
	MobileElement logoutBtnInHamMenu;

	@iOSXCUITFindBy(xpath = "(//XCUIElementTypeStaticText[@name='Login'])[2]")
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Login']")
	MobileElement loginPageText;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Steve Madden']")
	@CacheLookup
	MobileElement steveMadden;

	@AndroidFindBy(xpath = "//android.widget.EditText[@text='Email']")
	@CacheLookup
	MobileElement emailForSm;

	@AndroidFindBy(xpath = "//android.widget.EditText[@text='Password']")
	@CacheLookup
	MobileElement passwordForSm;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Forgot Password']")
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Forgot Password']")
	MobileElement forgotPassword;

	@AndroidFindBy(xpath = "//android.widget.TextView[contains(@resource-id,'id/btn_login')]")
	@CacheLookup
	MobileElement SubmitLogin;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='REGISTER']")
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Register']")
	MobileElement registerBtn;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Reset Password']")
	MobileElement resetPswBtn;

	@AndroidFindBy(xpath = "//android.widget.Button[@text='OK']")
	MobileElement okPopup;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Contact Us']")
	@CacheLookup
	MobileElement contactUsPageText;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Call Us']")
	@CacheLookup
	MobileElement callUs;

	@AndroidFindBy(xpath = "//android.widget.TextView[contains(@text,'SMADDEN')]")
	@CacheLookup
	MobileElement callSM;

	@AndroidFindBy(xpath = "//android.widget.TextView[contains(@text,'stevemaddendirect.com')]")
	@CacheLookup
	MobileElement smEmail;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Reach Us At']")
	@CacheLookup
	MobileElement reachUs;

	@AndroidFindBy(xpath = "//android.widget.TextView[contains(@text,'Steve Madden Ltd ')]")
	@CacheLookup
	MobileElement smLtd;

	@iOSXCUITFindBy(xpath = "(//XCUIElementTypeStaticText[@name='Register'])[2]")
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Register']")
	MobileElement registerText;

	@iOSXCUITFindBy(xpath = "(//XCUIElementTypeButton[@name='checkbox']//parent::XCUIElementTypeOther/XCUIElementTypeButton)[1]")
	@AndroidFindBy(xpath = "//android.widget.CheckBox[contains(@resource-id,'ui_checkbox')]")
	MobileElement policyCheckBoxOnRegisterPage;

	@iOSXCUITFindBy(xpath ="//XCUIElementTypeSecureTextField[@value='Confirm Password']")
	@AndroidFindBy(xpath="//android.widget.EditText[@text='Confirm Password']")
	@CacheLookup
	public MobileElement confirmPassword;

	//Not Now button in the save password prompt
	@AndroidFindBy(xpath = "//android.widget.Button[@text='Not now']")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='Not Now']")
	public MobileElement notNowButton;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeCell[@name='27']")
	@AndroidFindBy(xpath="//android.widget.HorizontalScrollView[contains(@resource-id,'sliding_tabs')]/android.widget.LinearLayout/android.widget.ImageView[4]")
	public MobileElement profileTab;

}
