package com.ebanma.cloud.usertestall;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication// 来标注一个主程序类，说明这是一个Spring Boot应用
@ServletComponentScan
public class UserTestAllApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserTestAllApplication.class, args);
    }

}
