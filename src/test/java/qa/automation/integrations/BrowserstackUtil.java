package qa.automation.integrations;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;

public class BrowserstackUtil {
    private static final Logger logger = Logger.getLogger(BrowserstackUtil.class);

    private static final String USERNAME = System.getenv("BROWSERSTACK_USERNAME");
    private static final String ACCESS_KEY = System.getenv("BROWSERSTACK_ACCESS_KEY");
    private static final String API_ENDPOINT = "https://api-cloud.browserstack.com/app-automate/upload";

    /**
     * Method upload the APK or IPA file to Browserstack
     * @param filePath
     * @return
     * @throws IOException
     */
    public static HttpResponse uploadAppOnBrowserstack(String filePath) throws IOException {

        // check that file exists and is readable
        File file = new File(filePath);
        if (!file.exists() || !file.canRead()) {
            logger.error("Error: File not found or not readable: " + filePath);
            System.exit(1);
        }

        // read file into a byte array
        byte[] fileBytes = Files.readAllBytes(Paths.get(filePath));

        // create HTTP client
        HttpClient httpClient = HttpClientBuilder.create().build();

        // create HTTP POST request
        HttpPost httpPost = new HttpPost(API_ENDPOINT);

        // set request headers
        httpPost.setHeader(HttpHeaders.AUTHORIZATION, "Basic " + encodeCredentials());

        // determine file type based on file extension
        String fileExtension = filePath.substring(filePath.lastIndexOf('.') + 1);
        String fileType = getFileType(fileExtension);

        // build multipart request entity
        HttpEntity entity = MultipartEntityBuilder.create()
                .addTextBody("data", "{\"custom_id\": \"myapp\"}", ContentType.APPLICATION_JSON)
                .addBinaryBody("file", fileBytes, ContentType.create(fileType), file.getName())
                .build();

        // set request entity
        httpPost.setEntity(entity);

        // execute request and get response
        HttpResponse response = httpClient.execute(httpPost);

        // extract response body as string
        String responseBody = EntityUtils.toString(response.getEntity());
        logger.info(responseBody);
        return response;
    }

    /**
     * Method encodes the credentials of the browserstack App Automate User
     * @return
     */
    private static String encodeCredentials() {
        String credentials = USERNAME + ":" + ACCESS_KEY;
        return Base64.getEncoder().encodeToString(credentials.getBytes());
    }

    /**
     * Method returns the file Type of the file extension passed
     * @param fileExtension
     * @return
     */
    private static String getFileType(String fileExtension) {
        switch (fileExtension.toLowerCase()) {
            case "apk":
                return "application/vnd.android.package-archive";
            case "ipa":
                return "application/octet-stream";
            default:
                throw new IllegalArgumentException("Unsupported file type: " + fileExtension);
        }
    }

    /**
     * Methods returns the Browserstack App Key after uploading the app through public download URL of the application [apk/ipa]
     * @param appURL
     * @return
     */
    public static String uploadAppOnBrowserstackThroughURL(String appURL) {

        try {
            // Create HTTP connection
            URL url = new URL(API_ENDPOINT);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Authorization", "Basic " + Base64.getEncoder().encodeToString((USERNAME + ":" + ACCESS_KEY).getBytes()));

            // Build JSON request
            JSONObject json = new JSONObject();
            json.put("url", appURL);
            json.put("custom_id", "APP_CUSTOM_ID");

            // Send request
            OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
            wr.write(json.toString());
            wr.flush();

            // Get response
            BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            StringBuilder response = new StringBuilder();
            while ((line = rd.readLine()) != null) {
                response.append(line);
            }
            rd.close();

            // Parse JSON response
            JSONParser parser = new JSONParser();
            JSONObject respJson = (JSONObject) parser.parse(response.toString());
            String appBSKey = (String) respJson.get("app_url");

            logger.info("App uploaded successfully! App BS Key: " + appBSKey);
            return   appBSKey;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * Method returns the browserstack's video download URL
     * @param sessionID
     * @return
     */
    public static String getVideoDownloadURL(String sessionID) {
        try {
            URL url = new URL("https://api.browserstack.com/app-automate/sessions/" + sessionID + ".json");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("GET");
            connection.setRequestProperty("Authorization", "Basic " + Base64.getEncoder().encodeToString((USERNAME + ":" + ACCESS_KEY).getBytes()));

            int responseCode = connection.getResponseCode();
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(response.toString());
            JSONObject automationSession = (JSONObject) jsonObject.get("automation_session");
            String videoURL = (String) automationSession.get("video_url");

            logger.info("Browserstack Session video download URL:  " + videoURL);
            return videoURL;
        } catch (Exception e) {
            logger.error(e);
            return null;
        }
    }


}
