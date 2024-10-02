package com.pos.crackers.config;

import org.springframework.beans.factory.annotation.Autowired;
import software.amazon.awssdk.services.secretsmanager.SecretsManagerClient;

import java.util.HashMap;

public interface SecretUtil {

    static void createHashMapFromSecret(String secret, HashMap<String, String> secretsMap){

        secret = secret.substring(1, secret.length() - 1);
        String[] keyValuePairs = secret.split(",");

        for ( String pair: keyValuePairs){
            String[] entry = pair.split(":");

            secretsMap.put(
                    entry[0].trim().replaceAll("\"",""),
                    entry[1].trim().replaceAll("\"","")
            );
        }

    }

    String fetchSecret(String secretName);
}
