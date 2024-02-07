package com.michael.thirdpartyapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
public class ThirdPartyApiApplication {

	@Bean
	public WebClient webClient(){
		return  WebClient.create("https://dummyjson.com");
	}
	public static void main(String[] args) {
		SpringApplication.run(ThirdPartyApiApplication.class, args);
	}

}
