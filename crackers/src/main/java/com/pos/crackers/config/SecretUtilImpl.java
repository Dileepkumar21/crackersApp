package com.pos.crackers.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.services.secretsmanager.SecretsManagerClient;
import software.amazon.awssdk.services.secretsmanager.model.GetSecretValueRequest;
import software.amazon.awssdk.services.secretsmanager.model.GetSecretValueResponse;
import software.amazon.awssdk.services.secretsmanager.model.SecretsManagerException;

@Component
public class SecretUtilImpl implements SecretUtil{

    @Autowired
    private SecretsManagerClient secretsManagerClient;

    @Override
    public String fetchSecret(String secretName) {
        String returnSecret = "";

        try{
            GetSecretValueRequest secretValueRequest = GetSecretValueRequest.builder().secretId(secretName)
                                                        .build();
            GetSecretValueResponse secretValueResponse = secretsManagerClient.getSecretValue(secretValueRequest);
            returnSecret = secretValueResponse.secretString();
        }catch (SecretsManagerException e){
            System.out.println(e);
        }

        return returnSecret;
    }
}
