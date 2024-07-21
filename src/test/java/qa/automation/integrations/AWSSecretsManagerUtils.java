package qa.automation.integrations;

import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.secretsmanager.SecretsManagerClient;
import software.amazon.awssdk.services.secretsmanager.model.GetSecretValueRequest;
import software.amazon.awssdk.services.secretsmanager.model.GetSecretValueResponse;

import java.util.HashMap;

public class AWSSecretsManagerUtils {

    static Region region = Region.of("eu-north-1");

    /**
     * Method returns the key value pairs of the secrets stored in Secrets Manager
     *
     * @param secretName
     * @return
     */
    public static String getSecretValue(String secretName) {
        /*
        In environment variables pass the following strings:
        AWS_ACCESS_KEY_ID and AWS_SECRET_ACCESS_KEY
         */
       

        String secret = getSecretValueResponse.secretString();

        return secret;
    }

    /**
     * Method parses the key value pairs string and converts it into a HashMap
     *
     * @param secretKeyValueString
     * @return
     */
    public static HashMap<String, String> parseKeyValueString(String secretKeyValueString) {
        // Remove leading and trailing braces
        
        HashMap<String, String> keyValueMap = new HashMap<>();

        // Iterate through the key-value pairs
        for (String pair : keyValuePairs) {
            // Split each pair into key and value
            String[] keyValue = pair.split(":");

          

            // Put the key-value pair into the map
            keyValueMap.put(key, value);
        }

        return keyValueMap;
    }

    /**
     * Method returns the value for the key passed for the HashMap
     *
     * @param key
     * @param keyValueMap
     * @return
     */
    public static String getValueForKey(String key, HashMap<String, String> keyValueMap) {
        return keyValueMap.get(key);
    }


}


