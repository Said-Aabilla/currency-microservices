package com.mydomain.namingregistryservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class NamingRegistryServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(NamingRegistryServiceApplication.class, args);
    }

}
