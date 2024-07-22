package qa.automation.pageScreenDevice.mobile;

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
public class HomePage extends ScreenManager {



	public HomePage(AppiumDriver<MobileElement> driver)
	{
		super(driver);
	}

	@AndroidFindBy(xpath="//android.widget.TextView[@text='View More']")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='View More']")
	public MobileElement viewMoreButtonOnHomePage;

	
}
