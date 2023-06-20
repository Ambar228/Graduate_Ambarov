package edu.bru.tournamentmicroservice;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class TournamentMicroService {
    public static void main(String[] args) {
        SpringApplication.run(TournamentMicroService.class, args);
    }
}