package edu.bru.usermicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class UserMicroService {
    public static void main(String[] args) {
        SpringApplication.run(UserMicroService.class, args);
    }
}
