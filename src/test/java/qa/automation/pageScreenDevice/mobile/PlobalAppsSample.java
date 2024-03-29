package qa.automation.pageScreenDevice.mobile;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.support.CacheLookup;
import qa.automation.pageScreenDevice.manager.ScreenManager;

/**
 * @deprecated
 */
@Getter
@Setter
public class PlobalAppsSample extends ScreenManager {


    public PlobalAppsSample(AppiumDriver<MobileElement> driver) {
        super(driver);
    }

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeCollectionView/XCUIElementTypeCell[@name='27']")
    @CacheLookup
    public MobileElement profileTab;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='Login']")
    @CacheLookup
    public MobileElement loginButtonInProfilePage;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeSecureTextField[@value = 'Password']")
    @CacheLookup
    public MobileElement passwordTextBox;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeTextField[@value = 'Email']")
    @CacheLookup
    public MobileElement emailTextBox;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name = 'LOGIN']")
    @CacheLookup
    public MobileElement loginButtonInLoginPage;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='Edit Profile']/preceding-sibling::XCUIElementTypeStaticText[2]")
    @CacheLookup
    public MobileElement nameOnProfilePage;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='Edit Profile']/preceding-sibling::XCUIElementTypeStaticText[1]")
    @CacheLookup
    public MobileElement emailOnProfilePage;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeCollectionView/XCUIElementTypeCell/XCUIElementTypeOther/XCUIElementTypeButton")
    @CacheLookup
    public MobileElement homeBannerImageButton;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='BUY NOW']/preceding-sibling::XCUIElementTypeButton")
    @CacheLookup
    public MobileElement buyNowButtonInPDP;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='CONTINUE']")
    public MobileElement continueButtonInCheckOut;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name='Card number']/following-sibling::XCUIElementTypeTextField")
    @CacheLookup
    public MobileElement cardNumberTextBoxInWebViewCheckout;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name='Name on card']/following-sibling::XCUIElementTypeTextField")
    @CacheLookup
    public MobileElement nameOnCardTextBoxInWebViewCheckout;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[contains(@name, 'Expiration date (MM / YY)')]/following-sibling::XCUIElementTypeTextField")
    public MobileElement expirationDateTextBoxInWebViewCheckout;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[contains(@name, 'Security code')]//following-sibling::XCUIElementTypeTextField")
    @CacheLookup
    public MobileElement securityCodeTextBoxInWebViewCheckout;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='Pay now']")
    @CacheLookup
    public MobileElement payNowButtonWebViewCheckout;


    public void clickHomeBanner(){
        homeBannerImageButton.click();
    }

    public void clickBuyNowButton(){
        buyNowButtonInPDP.click();
    }

    public void clickContinueButtonInCheckOut(){
        continueButtonInCheckOut.click();
    }

    public void enterName(String name){
        nameOnCardTextBoxInWebViewCheckout.sendKeys(name);
    }

    public void enterExpiration(String exp){
        expirationDateTextBoxInWebViewCheckout.sendKeys(exp);
    }

    public void enterCVV(String cvv){
        securityCodeTextBoxInWebViewCheckout.sendKeys(cvv);
    }

    public void enterCardNumber(String cardNum){
        cardNumberTextBoxInWebViewCheckout.sendKeys(cardNum);
    }

    public void clickOnProfileTab(){
        profileTab.click();
    }

    public void clickOnLoginButtonInProfilePage(){
        loginButtonInProfilePage.click();
    }

    public void clickOnLoginButtonInLoginPage(){
        loginButtonInLoginPage.click();
    }

    public void enterEmailTextBox(String email){
        emailTextBox.sendKeys(email);
    }

    public void enterPasswordTextBox(String password){
        passwordTextBox.sendKeys(password);
    }

    public boolean isEmailOnProfilePage(String email){
        return emailOnProfilePage.getText().contains(email);
    }

    public boolean isNameOnProfilePage(String name){
        return nameOnProfilePage.getText().contains(name);
    }

    public String getEmailOnProfilePage(){
        return emailOnProfilePage.getText();
    }

    public String getNameOnProfilePage(){
        return nameOnProfilePage.getText();
    }



}
