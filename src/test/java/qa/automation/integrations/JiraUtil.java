package qa.automation.integrations;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.relevantcodes.extentreports.LogStatus;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import qa.automation.base.MobileBaseTest;
import qa.automation.report.ExtentTestManager;
import qa.automation.utilities.ReadConfig;

import java.io.File;

import static io.restassured.RestAssured.given;


public class JiraUtil {
    
    public JiraUtil() {
    }

    public static String createJiraTicket(String issueType, String summary, String description) throws JsonProcessingException {
        String key = String.format("%d",jsonTree.get("key"));
        return key;

    }

    public static void getProjectDetails() {
      
    }

    public static void addAattachment(String jiraId) {
       
    }

}
