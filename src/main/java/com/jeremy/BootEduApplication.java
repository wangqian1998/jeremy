package com.jeremy;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.jeremy.dao")
public class BootEduApplication {

    public static void main(String[] args) {
        SpringApplication.run(BootEduApplication.class, args);
    }

}
