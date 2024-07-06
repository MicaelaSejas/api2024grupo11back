package com.uade.tpo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
// import org.springframework.boot.actuate.autoconfigure.security.servlet.ManagementWebSecurityAutoConfiguration;
// import org.springframework.context.annotation.ComponentScan;


// @ComponentScan(basePackages = {"com.uade.tpo", "com.uade.tpo.request"}) 
// @SpringBootApplication(exclude = ManagementWebSecurityAutoConfiguration.class)
@SpringBootApplication
public class TpoApplication {

	public static void main(String[] args) {
		SpringApplication.run(TpoApplication.class, args);
	}

}
