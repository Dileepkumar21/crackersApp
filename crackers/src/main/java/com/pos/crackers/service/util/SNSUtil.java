package com.pos.crackers.service.util;

import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sns.SnsClient;
import software.amazon.awssdk.services.sns.model.PublishRequest;
import software.amazon.awssdk.services.sns.model.PublishResponse;

public class SNSUtil {

    public static String publishMessage(String message) {

        // Create an SNS client
        SnsClient snsClient = SnsClient.builder()
                .region(Region.US_EAST_1)  // Specify your AWS region
                .build();

        // The ARN of the SNS topic you want to publish to
        String topicArn = "arn:aws:sns:us-east-1:381492071928:OrderTopic";

        // Publish the message to the SNS topic
        PublishRequest publishRequest = PublishRequest.builder()
                .message(message)
                .topicArn(topicArn)
                .build();

        PublishResponse publishResponse = snsClient.publish(publishRequest);
        
        // Close the SNS client
        snsClient.close();
        // Print the Message ID of the message
        return publishResponse.messageId();
    }
}
