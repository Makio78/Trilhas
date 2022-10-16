package br.com.mba.trilhas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class TrilhasApplication {

	public static void main(String[] args) {
		SpringApplication.run(TrilhasApplication.class, args);
	}

}
