package com.example.rdv_medical;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAsync
public class RdvMedicalApplication {

	public static void main(String[] args) {
		SpringApplication.run(RdvMedicalApplication.class, args);
	}

}
