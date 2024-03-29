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
    private static final Logger logger = Logger.getLogger(JiraUtil.class);
    private static final String jiraToken = AWSSecretsManagerUtils.getValueForKey("JiraToken", MobileBaseTest.automationIntegrationTokens);
    public static String jiraURI = "https://plobalapps.atlassian.net/rest/api/3";
    public static String projectKey = ReadConfig.getJiraProjectKey();

    public JiraUtil() {
    }

    public static String createJiraTicket(String issueType, String summary, String description) throws JsonProcessingException {
        RestAssured.baseURI = jiraURI;
        RequestSpecification httpRequest = RestAssured
                .given()
                .contentType(ContentType.JSON).header("Authorization", jiraToken);
        JSONObject paragraphContentJson = new JSONObject();
        paragraphContentJson.put("text", description);
        paragraphContentJson.put("type", "text");
        JSONArray paragraphContentArr = new JSONArray();
        paragraphContentArr.put(paragraphContentJson);
        JSONObject docContentJson = new JSONObject();
        docContentJson.put("content", paragraphContentArr);
        docContentJson.put("type", "paragraph");
        JSONArray docContentArr = new JSONArray();
        docContentArr.put(docContentJson);
        JSONObject descriptionJson = new JSONObject();
        descriptionJson.put("content", docContentArr);
        descriptionJson.put("type", "doc");
        descriptionJson.put("version", 1);
        JSONObject customModuleJson = new JSONObject();
        customModuleJson.put("id", "10181");
        customModuleJson.put("value", "None");
        JSONObject summaryProjectJson = new JSONObject();
        JSONObject nameJson = new JSONObject();
        JSONObject keyJson = new JSONObject();
        JSONArray labelsArr = new JSONArray();
        nameJson.put("name", issueType);
        keyJson.put("key", projectKey);
        summaryProjectJson.put("summary", summary);
        summaryProjectJson.put("issuetype", nameJson);
        summaryProjectJson.put("project", keyJson);
        labelsArr.put("Automation_Bug");
        summaryProjectJson.put("labels", labelsArr);
        summaryProjectJson.put("description", descriptionJson);
        summaryProjectJson.put("customfield_10701", customModuleJson);
        JSONObject fieldsJsonl = new JSONObject();
        fieldsJsonl.put("fields", summaryProjectJson);
        String jsonBody = fieldsJsonl.toString();
        Response response = httpRequest.body(jsonBody).
                request(Method.POST, "/issue");
        String responseBody = response.getBody().asString();
        logger.info(responseBody);
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonTree = objectMapper.readTree(responseBody);
        String key = String.format("%d",jsonTree.get("key"));
        String jiraId= key.replaceAll("\"", "");
        logger.info("Ticket Url ----------- https://plobalapps.atlassian.net/browse/" + jiraId);
        ExtentTestManager.getTest().log(LogStatus.INFO, "Ticket Url ----------- https://plobalapps.atlassian.net/browse/" + jiraId);
        addAattachment(jiraId);
        jsonTree.get("self");
        logger.info("jiraId ----------------- " + jiraId);
        return key;

    }

    public static void getProjectDetails() {
        RestAssured.baseURI = jiraURI;
        given().header("Authorization", jiraToken)
                .when().get("project").then().assertThat().statusCode(200).log().all();
    }

    public static void addAattachment(String jiraId) {
        File folder = new File("./target/Screenshots");
        File[] listOfFiles = folder.listFiles();
        for (int i = 0; i < listOfFiles.length; i++) {
            HttpResponse response = Unirest.post(jiraURI + "/issue/" + jiraId + "/attachments")
                    .header("Authorization", jiraToken)
                    .header("Accept", "application/json")
                    .header("X-Atlassian-Token", "no-check")
                    .field("file", new File("./target/Screenshots/" + listOfFiles[i].getName()))
                    .asJson();
        }
    }

}
