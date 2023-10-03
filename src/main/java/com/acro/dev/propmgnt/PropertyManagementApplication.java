package com.acro.dev.propmgnt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PropertyManagementApplication {

	public static void main(String[] args) {

		SpringApplication.run(PropertyManagementApplication.class, args);
	}
	/*@Scheduled(fixedRate = 1000)
	public void scheduleFixedRateTask() {
		System.out.println(
				"Fixed rate task - " + System.currentTimeMillis() / 1000);
	}
*/
}
