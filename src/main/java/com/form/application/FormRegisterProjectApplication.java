package com.form.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication(scanBasePackages = "com.form.controllers")
@EnableJpaRepositories(basePackages = "com.form.repository")
@EntityScan(basePackages = "com.form.entity")
@Import(SecurityConfig.class)
public class FormRegisterProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(FormRegisterProjectApplication.class, args);
	}

}
