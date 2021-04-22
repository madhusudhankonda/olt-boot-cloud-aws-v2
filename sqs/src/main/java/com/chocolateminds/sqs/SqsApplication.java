package com.chocolateminds.sqs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SqsApplication {

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(SqsApplication.class, args);
    }

}
