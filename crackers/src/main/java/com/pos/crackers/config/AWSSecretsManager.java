package com.pos.crackers.config;

public interface AWSSecretsManager {
    String getSecretValue(String storeName, String secretKey);
}
