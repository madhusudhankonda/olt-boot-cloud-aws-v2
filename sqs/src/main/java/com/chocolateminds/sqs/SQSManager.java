package com.chocolateminds.sqs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.*;

@Service
@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
public class SQSManager implements CommandLineRunner {

    @Autowired
    private SqsClient sqsClient;

    @Bean
    private SqsClient sqlClient(){
        return SqsClient.builder().build();
    }
    private void createQueue(String qName){
        CreateQueueRequest queueRequest = CreateQueueRequest.builder()
                .queueName(qName)
                .build();
        CreateQueueResponse createQueueResponse =
                sqsClient.createQueue(queueRequest);

        System.out.println("Queue Created: "+createQueueResponse.toString());
    }

    private void deleteQueue(String queue){
        DeleteQueueRequest deleteQueueRequest = DeleteQueueRequest.builder()
                .queueUrl(queue)
                .build();

        DeleteQueueResponse deleteQueueResponse = sqsClient.deleteQueue(deleteQueueRequest);
        System.out.println("Queue deleted:"+deleteQueueResponse.toString());
    }

    private void sendMessage(String queueUrl,String message){
        SendMessageRequest sendMessageRequest =
                SendMessageRequest
                        .builder()
                        .queueUrl(queueUrl)
                        .messageBody(message)
                        .build();

        SendMessageResponse sendMessageResponse =
                sqsClient.sendMessage(sendMessageRequest);
        System.out.println("Message Sent: "+sendMessageResponse.messageId());

    }

    @Override
    public void run(String... args) throws Exception {
       String QUEUE_URL =  "https://sqs.eu-west-2.amazonaws.com/304593876351/olt-boot-cloud-aws-v2-queue";

//       createQueue("olt-boot-cloud-aws-v2-queue");
//        deleteQueue(QUEUE_URL);
       sendMessage(QUEUE_URL,"Hello, Cloud AWS!");
    }
}
