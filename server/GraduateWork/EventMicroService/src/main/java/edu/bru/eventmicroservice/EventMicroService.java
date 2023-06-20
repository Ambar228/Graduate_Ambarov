package edu.bru.eventmicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class EventMicroService {
    public static void main(String[] args) {
        SpringApplication.run(EventMicroService.class, args);
    }
}
