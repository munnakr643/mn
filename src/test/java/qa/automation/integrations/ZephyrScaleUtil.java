package qa.automation.integrations;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.ITestResult;
import qa.automation.base.MobileBaseTest;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static qa.automation.base.BaseTest.testId;

public class ZephyrScaleUtil {
    private static final Logger logger = Logger.getLogger(ZephyrScaleUtil.class);

    private static String testCycleKey = System.getenv("testCycleId");

    private static final String zephyrScaleToken = AWSSecretsManagerUtils.getValueForKey("ZephyrToken", MobileBaseTest.automationIntegrationTokens);
    public static String zephyrScaleBaseURI = "https://api.zephyrscale.smartbear.com/v2";
    public static String endPoint = "/testexecutions";
    public static String projectKey ="MAB";
    public static String testSummary = "Test Result updated successfully";


    public static void updateTestResult(ITestResult result) {
        RestAssured.baseURI = zephyrScaleBaseURI;
        RequestSpecification httpRequest = RestAssured
                .given()
                .contentType(ContentType.JSON).header("Authorization", zephyrScaleToken);

        JSONObject parentJson = new JSONObject();
        parentJson.put("projectKey", projectKey);
        parentJson.put("testCaseKey", testId);
        parentJson.put("testCycleKey", testCycleKey);
        parentJson.put("statusName", getStatus(result));
        JSONArray parentArr = new JSONArray();
        JSONObject chinld1Json = new JSONObject();
        chinld1Json.put("statusName", getStatus(result));
        chinld1Json.put("actualEndDate", dateformate());
        chinld1Json.put("actualResult", testSummary);
        parentArr.put(chinld1Json);
        parentJson.put("testScriptResults", parentArr);

        Response response = httpRequest.body(parentJson.toString()).
                request(Method.POST, endPoint);

        logger.info(response.getBody().asString());
        logger.info("StatusCode -  "+response.getStatusCode());
    }

    public static String getStatus(ITestResult result){
        if (result.isSuccess())
            return "Pass";
        else
            return "Fail";
    }

    public static String dateformate(){
        Date dNow = new Date( );
        Calendar cal = Calendar.getInstance();
        cal.setTime(dNow);
        cal.add(Calendar.MINUTE, -330);
        dNow = cal.getTime();
        SimpleDateFormat ft=new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        return ft.format(dNow);
    }

}
