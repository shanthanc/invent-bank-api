package com.shanthan.inventbank;

import org.springframework.boot.ApplicationContextFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import java.util.Arrays;

@SpringBootApplication
@EnableWebSecurity
public class InventBankApplication {

    public static void main(String[] args) {

        SpringApplication.run(InventBankApplication.class, args);
    }

}
