package com.pos.crackers.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class AWSSecretsManagerImpl implements AWSSecretsManager{

    @Autowired
    private SecretUtil secretUtil;

    @Override
    public String getSecretValue(String storeName, String secretKey) {

        final String secret = secretUtil.fetchSecret(storeName);
        HashMap<String, String> map = new HashMap<>();
        SecretUtil.createHashMapFromSecret(secret, map);
        return map.get(secretKey);
    }
}
