package edu.bru.commentmicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class CommentMicroService {
    public static void main(String[] args) {
        SpringApplication.run(CommentMicroService.class, args);
    }

}