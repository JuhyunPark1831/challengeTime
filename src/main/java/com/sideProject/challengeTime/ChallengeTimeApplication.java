package com.sideProject.challengeTime;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class ChallengeTimeApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChallengeTimeApplication.class, args);
	}

}
