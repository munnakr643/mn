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

   
    /**
     * Send test execution status to the Slack channel
     *
     * @param message
     */
    public void sendMessageToSlackChannel(String message, String channelName, String slackWebHook) {
        
    }

    /**
     * Uploads file and String message to the slack channel
     * @param filePath
     * @param messageString
     * @param channelName
     * @param botUserOAuthAccessToken
     */
    public void sendFileWithMessageToSlackChannel(String filePath, String messageString, String channelName, String botUserOAuthAccessToken) {
        
    }
}
