package qa.automation.pageScreenWeb.manager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;


/**
 * 
 *
 */

public abstract class ScreenManager {


	public ScreenManager(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}



}
