package com.infnet.microorder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = "com.infnet.microorder.client")
public class MicroOrderApplication {

	public static void main(String[] args) {

		SpringApplication.run(MicroOrderApplication.class, args);
		System.out.println("MicroOrder API Rodando");
	}
}
