package com.users.management;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ManagementApplication {

    public static void main(final String[] args) {

        System.setProperty("server.servlet.context-path", "/api");
        SpringApplication.run(ManagementApplication.class, args);
    }
}
