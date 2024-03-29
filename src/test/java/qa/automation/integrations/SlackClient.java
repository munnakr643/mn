package qa.automation.integrations;

import com.github.seratch.jslack.Slack;
import com.github.seratch.jslack.api.webhook.Payload;
import com.github.seratch.jslack.api.webhook.WebhookResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.HttpResponse;

import java.io.File;

import org.apache.log4j.Logger;


import java.io.IOException;

public class SlackClient {

    private static final Logger logger = Logger.getLogger(SlackClient.class);

    private static String slackWebHook = "https://hooks.slack.com/services/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";
    private static String fileUploadBaseUrl = "https://slack.com/api/files.upload";

    /**
     * Send test execution status to the Slack channel
     *
     * @param message
     */
    public void sendMessageToSlackChannel(String message, String channelName, String slackWebHook) {
        try {
            StringBuilder messageBuilder = new StringBuilder();
            messageBuilder.append(message);
            Payload payload = Payload.builder().channel(channelName).text(messageBuilder.toString()).build();

            WebhookResponse webhookResponse = Slack.getInstance().send(slackWebHook, payload);
            logger.info(webhookResponse.getMessage());
        } catch (IOException e) {
            logger.error("Unexpected Error! WebHook:" + slackWebHook);
        }
    }

    /**
     * Uploads file and String message to the slack channel
     * @param filePath
     * @param messageString
     * @param channelName
     * @param botUserOAuthAccessToken
     */
    public void sendFileWithMessageToSlackChannel(String filePath, String messageString, String channelName, String botUserOAuthAccessToken) {
        try {
            HttpClient httpclient = HttpClientBuilder.create().disableContentCompression().build();
            HttpPost httppost = new HttpPost(fileUploadBaseUrl);
            MultipartEntityBuilder reqEntity = MultipartEntityBuilder.create();
            reqEntity.addBinaryBody("file", new File(filePath));
            reqEntity.addTextBody("channels", channelName);
            reqEntity.addTextBody("token", botUserOAuthAccessToken);
            reqEntity.addTextBody("media", "file");
            reqEntity.addTextBody("initial_comment", messageString);

            httppost.setEntity(reqEntity.build());
            HttpResponse execute = httpclient.execute(httppost);
            logger.info("File uploaded with status code: " + execute.getStatusLine().getStatusCode());
        } catch (Exception e) {
            logger.error("Error uploading files to slack channel.\n", e);
        }
    }
}
