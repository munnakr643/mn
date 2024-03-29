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
public class OnBoardingPage extends ScreenManager{

    public OnBoardingPage(AppiumDriver<MobileElement> driver)
    {
        super(driver);
    }

    /**
     * Owner: Aditya Nisal
     * On-Boarding 'Yes' & 'No'
     */

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='No']")
    @AndroidFindBy(xpath ="//android.widget.Button[@text='No']")
    public MobileElement noButton;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='Yes']")
    @AndroidFindBy(xpath ="//android.widget.Button[@text='Yes']")
    @CacheLookup
    public MobileElement yesButton;

    /**
     * SM Onboarding Page
     */

    @AndroidFindBy(xpath ="//android.widget.TextView[@text='NEXT']")
    @CacheLookup
    public MobileElement nextButton;

    @AndroidFindBy(xpath ="//android.widget.Button[@text='NEXT']")
    public MobileElement next1Button;


    @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='Return']")
    public MobileElement returnButtonIOS;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Store']")
    @AndroidFindBy(xpath ="//android.widget.TextView[contains(@resource-id,'alertTitle')]")
    public MobileElement alertTitle;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='plobal-sandbox-test']")
    @AndroidFindBy(xpath ="//android.widget.CheckedTextView[@text='plobal-sandbox-test']")
    public MobileElement sandboxStore;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Neovo-Commerce']")
    @AndroidFindBy(xpath ="//android.widget.CheckedTextView[@text='Neovo-Commerce']")
    public MobileElement neovoStore;


}
