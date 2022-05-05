package com.erotsx.rollcall;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.erotsx.rollcall.dao")
public class RollcallApplication {

    public static void main(String[] args) {
        SpringApplication.run(RollcallApplication.class, args);
    }

}
