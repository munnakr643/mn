package qa.automation.testCase.web;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.testng.annotations.Test;
import qa.automation.base.WebBaseTest;
import qa.automation.report.ExtentTestManager;

/**
 * @deprecated
 */
public class WebTestCase extends WebBaseTest {

    public static Logger logger = LogManager.getLogger(WebTestCase.class);

    @Test(priority = 0,groups = { "regression" })
    public void verifyLogin()  {
        logger.info("This test validates login activity");
        ExtentTestManager.getTest().setDescription("This test validates login activity");
        launchUrl("https://www.shopify.com/in");
        verifyShopifyWebHomePageDisplay();
        clickOnShopifyMarket();
    }

    @Test(priority = 0, groups = {"regression"})
    public void verifyEnableHamburgerMenuToggleFromStoreDashboard() {
        logger.info("This test validates enable hamburger menu toggle from store dashboard");
        ExtentTestManager.getTest().setDescription("This test validates enable hamburger menu toggle from store dashboard");
        launchUrl("https://staging.plobuild.com/diy/login");
        isPlobalStageLoginPageDisplayed();
        enterEmailInShopifyLogin("munna.k@plobalapps.com");
        enterPasswordInShopifyLogin("Admin@978");
        clickLoginButton();
        clickSelectForApp();
        clickDesigntab();
        clickNavigationMenu();
        clickHamburgerNavigationMenu();
        toggleHamburgerNavigationMenu(true);
        clickSaveButton();
        verifySaveSuccessMessage();
        clickPublishTab();
        clickPublishButton();
        verifyPublishSuccess();
        waitFor(2);
    }
}
