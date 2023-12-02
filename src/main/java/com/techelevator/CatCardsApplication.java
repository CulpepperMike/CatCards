package com.techelevator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;

@ComponentScan("com.techelevator")
@SpringBootApplication
public class CatCardsApplication {

    public static void main(String[] args) {
        SpringApplication.run(CatCardsApplication.class, args);
    }

}
