package com.cornell.se.bom.bomRecom;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.cornell.se.bom.controller.CDPOSController;

@SpringBootApplication
@ComponentScan(basePackageClasses = CDPOSController.class)
@EnableJpaRepositories("com.cornell.se.bom.repository")
@EntityScan(basePackages = {"com.cornell.se.bom.model"})
public class BomRecomApplication {

	public static void main(String[] args) {
		SpringApplication.run(BomRecomApplication.class, args);
	}

}
