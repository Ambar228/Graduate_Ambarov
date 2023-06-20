package edu.bru.sponsormicroservce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class SponsorMicroService {
    public static void main(String[] args) {
        SpringApplication.run(SponsorMicroService.class, args);
    }
}
