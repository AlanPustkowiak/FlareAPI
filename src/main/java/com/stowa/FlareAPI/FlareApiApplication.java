package com.stowa.FlareAPI;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class FlareApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(FlareApiApplication.class, args);
	}

}
