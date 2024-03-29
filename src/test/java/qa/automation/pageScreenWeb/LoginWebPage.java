package qa.automation.pageScreenWeb;

import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import qa.automation.pageScreenWeb.manager.ScreenManager;

@Getter
@Setter
public class LoginWebPage extends ScreenManager {

    public LoginWebPage(WebDriver driver) {
        super(driver);
    }


	@FindBy(xpath="//a[@data-trekkie-id='Main Nav Login']")
    public WebElement shopifyLogin;

	@

    FindBy(xpath ="//a[@data-trekkie-id='Main Nav Logo']")
    public WebElement shopifyLogo;

	@FindBy(xpath ="//button[@data-trekkie-id='Main Nav Start Nav']")
    public WebElement start;

	@FindBy(xpath ="//button[@data-trekkie-id='Main Nav Sell Nav']")
    public WebElement sell;

	@FindBy(xpath ="//button[@data-trekkie-id='Main Nav Market Nav']")
    public WebElement market;

    @FindBy(xpath ="//button[@data-trekkie-id='Main Nav Manage Nav']")
    public WebElement manage;

    @FindBy(xpath="//button[@type='button']")
    public WebElement crmLoginButton;

    @FindBy(xpath = "//input[@aria-label='Enter your email']")
    public WebElement crmUserId;

    @FindBy(xpath = "//span[text()='Next']")
    public WebElement crmNext;

    @FindBy(xpath = "//input[@aria-label='Enter your password']")
    public WebElement crmPassword;

    @FindBy(xpath = "//input[contains(@aria-label,'Digit 1')]")
    public WebElement otpbox1;

    @FindBy(xpath = "//cr-toggle[@id='cookie-controls-toggle']")
    public WebElement cookiesToggle;

    @FindBy(xpath = "//h3[text()='LOG IN']")
    public WebElement logIn;

    @FindBy(xpath = "//input[@name='email']")
    public WebElement email;

    @FindBy(xpath = "//input[@name='password']")
    public WebElement password;

    @FindBy(xpath = "//input[@name='login']")
    public WebElement loginButton;

    @FindBy(xpath = "//span[contains(text(),'Select App')]")
    public WebElement select1stApp;

    @FindBy(xpath = "//label[text()='Design']")
    public WebElement design;

    @FindBy(xpath = "//label[text()='Preview']")
    public WebElement preview;

    @FindBy(xpath = "//label[text()='Publish']")
    public WebElement publish;

    @FindBy(xpath = "//label[text()='Engage']")
    public WebElement engage;

    @FindBy(xpath = "//label[text()='Analyze']")
    public WebElement analyze;

    @FindBy(xpath = "//input[@type = 'email']")
    public WebElement shopifyEmailTextField;

    @FindBy(xpath = "//input[@type='password']")
    public WebElement shopifyPasswordTextField;

    @FindBy(xpath = "//button[@type = 'submit']")
    public WebElement shopifySubmitButton;

    @FindBy(xpath = "//span[text() = '[Stage] Mobile App - Plobal']")
    public WebElement stagingSalesChannel;

    @FindBy(xpath = "(//md-toolbar[contains(@class, 'header-toolbar-custom')]/a)[1]")
    public WebElement designTab;

    @FindBy(xpath = "//a[contains(@id, 'navigationBlockArrow')]")
    public WebElement navigationTab;

    @FindBy(xpath = "//div[contains(text(), 'Hamburger')]")
    public WebElement hamburgerNavigationTab;

    @FindBy(xpath = "//md-switch[contains(@aria-label, 'Hamburger')]")
    public WebElement hamburgerNavigationToggle;

    @FindBy(xpath = "//button[contains(., 'Save')]")
    public WebElement saveButton;

    @FindBy(xpath = "//p[contains(., 'Saved successfully')]")
    public WebElement savedSuccessMessage;

    @FindBy(xpath = "(//md-toolbar[contains(@class, 'header-toolbar-custom')]/a)[3]")
    public WebElement publishTab;

    @FindBy(xpath = "//span[contains(., 'Publish')]")
    public WebElement publishButton;

    @FindBy(xpath = "//h3[contains(text(), 'Congratulations!')]")
	public WebElement publishSuccessMessage;

    @FindBy(xpath = "//button[contains(., 'Continue with Email')]")
    public WebElement continueWithEmailButton;

    @FindBy(xpath = "//input[@name ='login']")
    public WebElement loginButtonInShopify;


    @FindBy(xpath = "//span[contains(text(), 'Select App' )]")
    public WebElement selectApp;
}

