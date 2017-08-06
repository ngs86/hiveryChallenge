package com.hivery.challenge;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

@SpringBootApplication
public class ChallengeApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChallengeApplication.class, args);
	}

	@Bean
	public MongoDatabase getMongoDatabase(){
		MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
		MongoDatabase mongoDatabase = mongoClient.getDatabase("ngsdb");
		return mongoDatabase;
	}
}
