package com.ciklum.tuitechtest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class TuiTechtestApplication {

    public static void main(String[] args) {
        SpringApplication.run(TuiTechtestApplication.class, args);
    }

}
