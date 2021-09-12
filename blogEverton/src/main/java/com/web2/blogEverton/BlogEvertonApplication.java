package com.web2.blogEverton;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

import org.springframework.boot.SpringApplication;


//@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
@SpringBootApplication
public class BlogEvertonApplication  {

	public static void main(String[] args) {

		SpringApplication.run(BlogEvertonApplication.class, args);
	}

}
