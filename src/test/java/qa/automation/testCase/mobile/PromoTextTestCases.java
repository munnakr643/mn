package qa.automation.testCase.mobile;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.testng.annotations.Test;
import qa.automation.base.MobileBaseTest;
import qa.automation.report.ExtentTestManager;

public class PromoTextTestCases extends MobileBaseTest {

    public static Logger logger = LogManager.getLogger(PromoTextTestCases.class);



    @Test(priority = 0, groups = {"neovo","regression", "androidOnly"})
    public void checkAdditionalLabelOnPDP(){
        testId="MAB-T15925";
        String headerText="Categories";
        String shoesCategoryName="Shoes";
        String sandalsCategoryName="Sandals";
        String additionalLabelAndroid="Extra Rs. 90.00 at Checkout Rs. 110.00 and Rs. 100.00 or Rs. 10.00";
        String additionalLabeliOS= "Extra Rs. 90,00  at Checkout Rs. 110,00  and Rs. 100,00  or Rs. 10,00 ";
        logger.info(testId +" : This test validates Additional label V2 presence on PDP");
        ExtentTestManager.getTest().setDescription(testId +" :This test validates Additional label V2 presence on PDP");
        clickNoButtonOnOnboardingScreen();
        clickCategoryTabOnHomePage();
        if (isPlatformNameAndroid) {
            verifyCategoriesHeaderTextOnCategoryPage(headerText);
        }
        clickShoesCollectionOnCollectionPage();
        verifyCategoriesHeaderTextOnCategoryPage(shoesCategoryName);
        clickSandalsCollectionOnCollectionPage();
        waitFor(1.4);
        verifyCategoriesHeaderTextOnCategoryPage(sandalsCategoryName);
        clickSandalsCollectionOnCollectionPage();
        if (isPlatformNameAndroid){
            verifyCategoriesHeaderTextOnCategoryPage(sandalsCategoryName);
        }
        clickFirstProductFromPLP();
        waitFor(6);
        if (isPlatformNameAndroid){
            verifyAdditionalTextOnPDPPage(additionalLabelAndroid);
        }
        else {
            verifyAdditionalTextOnPDPPage(additionalLabeliOS);
        }
    }


    @Test(priority = 0, groups = {"neovo","regression"})
    public void  checkAdditionalLabelOnPLP(){
        testId="MAB-T15924";
        String headerText="Categories";
        String shoesCategoryName="Shoes";
        String sandalsCategoryName="Sandals";
        String additionalLabelAndroid="Extra Rs. 90.00 at Checkout Rs. 110.00 and Rs. 100.00 or Rs. 10.00";
        String additionalLabeliOS= "Extra Rs. 90.00  at Checkout Rs. 110.00  and Rs. 100.00  or Rs. 10.00 ";
        logger.info(testId +" : This test validates Additional label V2 presence on PLP");
        ExtentTestManager.getTest().setDescription(testId +" :This test validates Additional label V2 presence on PLP");
        clickNoButtonOnOnboardingScreen();
        clickCategoryTabOnHomePage();
        if (isPlatformNameAndroid) {
            verifyCategoriesHeaderTextOnCategoryPage(headerText);
        }
        clickShoesCollectionOnCollectionPage();
        verifyCategoriesHeaderTextOnCategoryPage(shoesCategoryName);
        clickSandalsCollectionOnCollectionPage();
        if (isPlatformNameAndroid) {
            verifyCategoriesHeaderTextOnCategoryPage(sandalsCategoryName);
        }
        clickSandalsCollectionOnCollectionPage();
        if (isPlatformNameAndroid){
            verifyAdditionalTextOnPLPPage(additionalLabelAndroid);
        }
        else {
            verifyAdditionalTextOnPLPPage(additionalLabeliOS);
        }
    }

    @Test(priority = -1, groups = {"neovo","regression"})
    public void checkAdditionalLabelOnSearchPage(){
        testId="MAB-T15926";
        String searchText="allegra";
        String additionalLabelAndroid="Extra Rs. 90.00 at Checkout Rs. 110.00 and Rs. 100.00 or Rs. 10.00";
        String additionalLabelIOS= "Extra Rs. 90.00  at Checkout Rs. 110.00  and Rs. 100.00  or Rs. 10.00 ";
        logger.info(testId +" : This test validates Additional label V2 presence on Search Page");
        ExtentTestManager.getTest().setDescription(testId +" :This test validates Additional label V2 presence on Search Page");
        clickNoButtonOnOnboardingScreen();
        clickOnSearchIcon();
        waitFor(2);
        clickOnSearchIcon();
        enterTextOnSearchboxOnCollectionPage(searchText);
        waitFor(5);
        if (isPlatformNameAndroid) {
            verifyAdditionalTextOnSearchPage(additionalLabelAndroid);
        } else {
            verifyAdditionalTextOnSearchPage(additionalLabelIOS);
        }
    }

    @Test(priority = 0, groups = {"neovo","regression", "androidOnly"})
    public void checkAdditionalLableV2WhenShopifyRecommendationIsEnabled(){
        testId="MAB-T13894";
        String headerText="Categories";
        String shoesCategoryName="Shoes";
        String sandalsCategoryName="Sandals";
        String additionalLabelAndroid="Extra Rs. 90.00 at Checkout Rs. 110.00 and Rs. 100.00 or Rs. 10.00";
        String additionalLabeliOS= "Extra Rs. 90.00  at Checkout Rs. 110.00  and Rs. 100.00  or Rs. 10.00 ";
        logger.info(testId +" : This test validates Additional label V2 presence on PDP");
        ExtentTestManager.getTest().setDescription(testId +" :This test validates Additional label V2 presence on PDP");
        clickNoButtonOnOnboardingScreen();
        clickCategoryTabOnHomePage();
        if (isPlatformNameAndroid) {
            verifyCategoriesHeaderTextOnCategoryPage(headerText);
        }
        clickShoesCollectionOnCollectionPage();
        verifyCategoriesHeaderTextOnCategoryPage(shoesCategoryName);
        clickSandalsCollectionOnCollectionPage();
        waitFor(2);
        verifyCategoriesHeaderTextOnCategoryPage(sandalsCategoryName);
        clickSandalsCollectionOnCollectionPage();
        if (isPlatformNameAndroid){
            verifyCategoriesHeaderTextOnCategoryPage(sandalsCategoryName);
        }
        clickFirstProductFromPLP();
        waitFor(3);
        if (isPlatformNameAndroid){
            verifyAdditionalTextOnPDPPage(additionalLabelAndroid);
        }
        else {
            verifyAdditionalTextOnPDPPage(additionalLabeliOS);
        }
    }
}
