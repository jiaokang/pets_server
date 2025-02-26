package com.lwj.pets;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.lwj.pets.mapper")
public class PetsServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(PetsServerApplication.class, args);
    }

}
