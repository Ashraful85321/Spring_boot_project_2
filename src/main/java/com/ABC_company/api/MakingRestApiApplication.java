package com.ABC_company.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class MakingRestApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(MakingRestApiApplication.class, args);
	}
	@Bean
	public PlatformTransactionManager tran(MongoDatabaseFactory dbFactory){
		return new MongoTransactionManager(dbFactory);
	}

}
