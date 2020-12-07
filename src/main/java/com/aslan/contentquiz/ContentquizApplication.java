package com.aslan.contentquiz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;

@EnableAutoConfiguration(exclude = {MongoAutoConfiguration.class})
@SpringBootApplication
public class ContentquizApplication {

	public static void main(String[] args) {
		MongoController.init();

		SpringApplication.run(ContentquizApplication.class, args);
	}

}
