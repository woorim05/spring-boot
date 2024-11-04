package com.springtour.example.chapter02;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Chapter02Application {

    public static void main(String[] args) {
        // SpringApplication.setLazyInitialization() 을 사용하면 지연 초기화 가능함
        SpringApplication.run(Chapter02Application.class, args);
    }

}
