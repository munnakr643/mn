package qa.automation.pageScreenDevice;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import lombok.Getter;
import lombok.Setter;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import qa.automation.pageScreenDevice.manager.ScreenManager;

@Getter
@Setter
public class DrawerPage extends ScreenManager {

    public static Logger logger = LogManager.getLogger(DrawerPage.class);

    public DrawerPage(AppiumDriver<MobileElement> driver) {
        super(driver);
    }

    @iOSXCUITFindBy(xpath ="//XCUIElementTypeApplication[@name='staging Neovo-Commerce']/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeTable/XCUIElementTypeCell[1]/XCUIElementTypeButton[1]" )
    @AndroidFindBy(xpath = "(//android.widget.ImageView[@esource-id,'linearlayout_custom_toolbar'])[2]")
    MobileElement loginAndRegisterOnDrawer;

    @AndroidFindBy(xpath = "(//android.widget.RelativeLayout[@index='0'])[5]")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Homepage']")
    MobileElement homePage;
}
