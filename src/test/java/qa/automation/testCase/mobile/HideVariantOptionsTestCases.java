package qa.automation.testCase.mobile;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.testng.annotations.Test;
import qa.automation.base.MobileBaseTest;
import qa.automation.report.ExtentTestManager;

public class HideVariantOptionsTestCases extends MobileBaseTest {

    public static Logger logger = LogManager.getLogger(HideVariantOptionsTestCases.class);



    @Test(priority = 0, groups = {"neovo","regression"})
    public void verifySingleVariantOptionsAreNotDsiplayed() {
        testId = "MAB-T1105";
        logger.info(testId + " This test validates that PDP page does not show Size when single variants are present");
        ExtentTestManager.getTest().setDescription(testId + " This test validates that PDP page does not show Size when single variants are present");
        String productTitle = "Black Bodycon Dress";
        clickNoButtonOnOnboardingScreen();
        verifyHomepage();
        clickOnListIcon();
        clickOnFashionCollection();
        clickOnDressCollection();
        waitFor(3); //Allowing all the products to load in the PLP Page
        if(isPlatformNameAndroid)
            scrollToText(productTitle);
        else
            swipe(250, 666, 250, 370);
        clickProductInPLP(productTitle);
        waitFor(7); //wait for PDP to load
        verifySelectSizeIsDisabledInPdp();
    }

    @Test(priority = 0, groups = {"neovo","regression"})
    public void verifyHideAllVariantOptionOnPDPPage() {
        testId = "MAB-T1103";
        logger.info(testId + ": This test verifies that hide all variant options from PDP Page");
        ExtentTestManager.getTest().setDescription(testId + ": This test verifies that hide all variant options from PDP Page");
        clickNoButtonOnOnboardingScreen();
        verifyHomepage();
        clickOnListIcon();
        clickOnFashionCollection();
        clickOnTopsCollection();
        waitFor(2);
        verifyPlp();
        if(isPlatformNameAndroid) {
            scrollToText("Knitted Jumpsuit Top");
        }else {
            scrollForIos();
        }
        clickProductInPLP("Knitted Jumpsuit Top");
        verifyPdp();
        if(isPlatformNameAndroid) {
            scrollToText("Select Quantity");
        }else {
            scrollForIos();
        }
        waitFor(1.2);
        isSizeVariantNotDisplayed(0);
    }

    @Test(priority = 0, groups = {"neovo","regression"})
    public void verifyHideListedVariantOnPDPPage() {
        testId = "MAB-T1104";
        logger.info(testId + ": This test verifies that hide particular listed variant from PDP Page");
        ExtentTestManager.getTest().setDescription(testId + ": This test verifies that hide particular listed variant from PDP Page");
        clickNoButtonOnOnboardingScreen();
        verifyHomepage();
        clickOnListIcon();
        clickOnFashionCollection();
        clickOnTopsCollection();
        waitFor(5);
        verifyPlp();
        clickProductInPLP("Wander Festival Yellow Top");
        verifyPdp();
        waitFor(5);
        isParticularSizeVariantNotDisplayed("XL",0);
    }

}
