package qa.automation.pageScreenDevice;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import lombok.Getter;
import lombok.Setter;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.support.CacheLookup;
import qa.automation.pageScreenDevice.manager.ScreenManager;

@Getter
@Setter
public class AddressPage extends ScreenManager{

        public static Logger logger = LogManager.getLogger(AddressPage.class);

        public AddressPage(AppiumDriver<MobileElement> driver)
        {
            super(driver);
        }

        @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Add New Address']")
        @AndroidFindBy(id = "add_address_title_textView")
        MobileElement addAddressTextOnAddAddressPage;

        @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Remove']")
        @AndroidFindBy(xpath = "//android.widget.TextView[@text='Remove']")
        public MobileElement removeAddress;

        @iOSXCUITFindBy(xpath = "//XCUIElementTypeTextField[@name='First name (optional)']")
        @AndroidFindBy(xpath = "(//android.widget.EditText[@resource-id,'ui_editText'])[1]")
        MobileElement firstNameTextboxOnAddAddressPage;

        @iOSXCUITFindBy(xpath = "//XCUIElementTypeTextField[@name='Last name']")
        @AndroidFindBy(xpath = "(//android.widget.EditText[@resource-id,'ui_editText'])[2]")
        MobileElement lastNameTextboxOnAddAddressPage;

        @iOSXCUITFindBy(xpath = "//XCUIElementTypeTextField[@value='Company']")
        @AndroidFindBy(xpath = "(//android.widget.EditText[@resource-id,'ui_editText'])[3]")
        @CacheLookup
        MobileElement companyTextboxOnAddAddressPage;

        @iOSXCUITFindBy(xpath = "//XCUIElementTypeTextField[@value='Contact Number']")
        @AndroidFindBy(xpath = "(//android.widget.EditText[@resource-id,'ui_editText'])[4]")
        MobileElement contactNumberTextboxOnAddAddressPage;

        @iOSXCUITFindBy(xpath = "//XCUIElementTypeTextField[@value='Address line 1*']")
        @AndroidFindBy(xpath = "(//android.widget.EditText[@resource-id,'ui_editText'])[5]")
        MobileElement addressLine1TextboxOnAddAddressPage;

        @iOSXCUITFindBy(xpath = "//XCUIElementTypeTextField[@value='Address line 2']")
        @AndroidFindBy(xpath = "(//android.widget.EditText[@resource-id,'ui_editText'])[6]")
        MobileElement addressLine2TextboxOnAddAddressPage;

        @iOSXCUITFindBy(xpath = "//XCUIElementTypeTextField[@value='State*']")
        @AndroidFindBy(xpath = "(//android.widget.EditText[@resource-id,'ui_editText'])[7]")
        MobileElement stateTextboxOnAddAddressPage;

        @iOSXCUITFindBy(xpath = "//XCUIElementTypeTextField[@value='City*']")
        @AndroidFindBy(xpath = "(//android.widget.EditText[@resource-id,'ui_editText'])[8]")
        MobileElement cityTextboxOnAddAddressPage;

        @iOSXCUITFindBy(xpath = "//XCUIElementTypeTextField[@value='Zip / Postal Code*']")
        @AndroidFindBy(xpath = "(//android.widget.EditText[@resource-id,'ui_editText'])[9]")
        @CacheLookup
        MobileElement zipcodeTextboxOnAddAddressPage;

        @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[contains(@name,'SAVE')]")
        @AndroidFindBy(xpath = "//android.widget.TextView[@text='Save']")
        MobileElement saveButtonOnAddAddressPage;


        @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='Edit']")
        @AndroidFindBy(xpath = "//android.widget.TextView[@text='Edit']")
        MobileElement editAddressOnAddressPage;

        @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='Edit']/preceding-sibling:: XCUIElementTypeStaticText[1]")
        @AndroidFindBy(id = "myaddresses_item_row_address")
        MobileElement fullAddressOnAddressPage;

        @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Add New Address']")
        @AndroidFindBy(xpath = "//android.widget.TextView[@text='Add New Address']")
        MobileElement addNewAddressButton;

        @AndroidFindBy(xpath = "//android.widget.TextView[@text='Your address have been added to your account.']")
        MobileElement addressSavedTextOnPopup;

        public void enterFirstname(String firstName){ getFirstNameTextboxOnAddAddressPage().sendKeys(firstName);}

        public void enterLastname(String lastName){
                getLastNameTextboxOnAddAddressPage().sendKeys(lastName);
        }

        public void enterCompanyName(String company){getCompanyTextboxOnAddAddressPage().sendKeys(company);}

        public void enterContactNumber(String number){
                getContactNumberTextboxOnAddAddressPage().sendKeys(number);
        }

        public void enterAddressLine1(String address1){
                getAddressLine1TextboxOnAddAddressPage().sendKeys(address1);
        }

        public void enterAddressLine2(String address2){
                getAddressLine2TextboxOnAddAddressPage().sendKeys(address2);
        }

        public void enterCity(String city){
                getCityTextboxOnAddAddressPage().sendKeys(city);
        }

        public void enterState(String state){
                getStateTextboxOnAddAddressPage().sendKeys(state);
        }

        public void enterZipCode(String zipcode){
                getZipcodeTextboxOnAddAddressPage().sendKeys(zipcode);
        }




        @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Contact information']")
        @AndroidFindBy(xpath = "//android.widget.TextView[@text='Contact information']")
        MobileElement contactInfo;

        @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Shipping address']")
        @AndroidFindBy(xpath = "//android.widget.TextView[@text='Shipping address']")
        MobileElement shippingAddress;

        @iOSXCUITFindBy(xpath = "//XCUIElementTypeApplication[@name=\"staging plobal-sandbox-test\"]/XCUIElementTypeWindow/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeScrollView/XCUIElementTypeOther[7]")
        @AndroidFindBy(xpath = "(//android.widget.Spinner[@resource-id,'ui_spinner'])[1]")
        MobileElement country_region;

        @AndroidFindBy(xpath = "//android.widget.TextView[@text='India']")
        MobileElement country_India;

        @iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name='Shipping address, region']/XCUIElementTypeOther[2]/XCUIElementTypeOther[2]")
        @AndroidFindBy(xpath = "//android.widget.FrameLayout[@resource-id='android:id/custom']/android.widget.ListView/android.widget.CheckedTextView[1]")
        MobileElement country;

        @iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name='Shipping address, region']/XCUIElementTypeOther[8]/XCUIElementTypeOther[2]")
        @AndroidFindBy(xpath = "//android.view.View[@text='State']")
        MobileElement state;

        @iOSXCUITFindBy(xpath = "//XCUIElementTypeCollectionView[@type='XCUIElementTypeCollectionView']")
        @AndroidFindBy(xpath = "//android.widget.CheckedTextView[@text='State']")
        MobileElement stateList;

        @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='Andhra Pradesh']")
        @AndroidFindBy(xpath = "//android.widget.FrameLayout[@resource-id='android:id/custom']/android.widget.ListView/android.widget.CheckedTextView[2]")
        MobileElement secondStateformList;

        @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='Cancel']")
        MobileElement cancelButtonOnMobileKeyboard;

        @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='Yes']")
        @AndroidFindBy(xpath = "//android.widget.Button[@text='YES']")
        MobileElement clickYesButtonOnPopUp;

        @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='close20']")
        @AndroidFindBy(xpath = "(//android.view.View[@resource-id,'touch_outside'])[1]")
        MobileElement closeAddressPopup;

        @AndroidFindBy(xpath = "//android.widget.Button[@text='OK']")
        MobileElement okPopup;

        @iOSXCUITFindBy(xpath = "//XCUIElementTypeTextField[@value='First Name*']")
        @AndroidFindBy(xpath = "(//android.widget.EditText[@resource-id,'ui_editText'])[1]")
        MobileElement firstNameOnAddAddressPageSanbox;

        @iOSXCUITFindBy(xpath = "//XCUIElementTypeTextField[@value='Last Name*']")
        @AndroidFindBy(xpath = "(//android.widget.EditText[@resource-id,'ui_editText'])[2]")
        MobileElement lastNameOnAddAddressPageSandbox;

        @iOSXCUITFindBy(xpath = "(//XCUIElementTypeStaticText[@name='Shipping Address']/parent::XCUIElementTypeOther//following-sibling::XCUIElementTypeStaticText)[2]")
        @AndroidFindBy(xpath = "//android.widget.TextView[contains(@resource-id,'address_textview_name')]")
        MobileElement nameTextSandbox;

        @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Choose Address']")
        @AndroidFindBy(xpath = "//android.widget.TextView[@text='My Addresses']")
        MobileElement addressPageTitle;
    }
