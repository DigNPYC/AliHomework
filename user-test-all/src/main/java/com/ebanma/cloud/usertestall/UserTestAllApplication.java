package com.ebanma.cloud.usertestall;

import com.ebanma.cloud.config.EnableRegisterServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableRegisterServer
public class UserTestAllApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserTestAllApplication.class, args);
	}

}
