package com.chocolateminds.s3;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.CreateBucketRequest;
import software.amazon.awssdk.services.s3.model.CreateBucketResponse;

@Service
public class S3Manager implements CommandLineRunner {

    private S3Client getS3Client(){
        return S3Client.builder()
                .region(Region.EU_WEST_2)
                .build();
    }

    private void createBucket(String bucketName){
        CreateBucketRequest createBucketRequest =
                CreateBucketRequest.builder()
                .bucket(bucketName)
                .build();
        CreateBucketResponse createBucketResponse =
                getS3Client().createBucket(createBucketRequest);

        System.out.println("Bucket created:"+createBucketResponse.toString());
    }
    @Override
    public void run(String... args) throws Exception {
        createBucket("my-temp-bucket909090");
    }
}
