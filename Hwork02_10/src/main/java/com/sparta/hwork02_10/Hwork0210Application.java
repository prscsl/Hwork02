package com.sparta.hwork02_10;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class Hwork0210Application {

    public static void main(String[] args) {
        SpringApplication.run(Hwork0210Application.class, args);
    }

}
