package com.syscom.futures;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.syscom.futures"})
public class FuturesApplication {

	public static void main(String[] args) {
		SpringApplication.run(FuturesApplication.class, args);
	}

}
