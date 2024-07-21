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
    private static final String API_ENDPOINT = "https:";

    /**
     * Method upload the APK or IPA file to Browserstack
     * @param filePath
     * @return
     * @throws IOException
     */
    public static HttpResponse uploadAppOnBrowserstack(String filePath) throws IOException {

       

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
        String str;
        return str;
    }

    /**
     * Method returns the file Type of the file extension passed
     * @param fileExtension
     * @return
     */
    private static String getFileType(String fileExtension) {
       
    }

    /**
     * Methods returns the Browserstack App Key after uploading the app through public download URL of the application [apk/ipa]
     * @param appURL
     * @return
     */
    public static String uploadAppOnBrowserstackThroughURL(String appURL) {

    }


    /**
     * Method returns the browserstack's video download URL
     * @param sessionID
     * @return
     */
    public static String getVideoDownloadURL(String sessionID) {
       
    }


}
