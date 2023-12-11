package com.cpan252.distributioncentremanagersapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.CommandLineRunner;
import  com.cpan252.distributioncentremanagersapi.repository.ManagerRepository;
import  com.cpan252.distributioncentremanagersapi.model.ManagerModel;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
@SpringBootApplication
public class DistributioncentremanagersapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(DistributioncentremanagersapiApplication.class, args);
	}
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public CommandLineRunner createInitialManagers(ManagerRepository managerRepository, PasswordEncoder passwordEncoder) {
		return args -> {
			// Create a manager with username "admin" and a hashed password
			ManagerModel adminManager = ManagerModel.builder()
					.username("Manager A")
					.password(passwordEncoder.encode("adminPassword"))
					.build();
			managerRepository.save(adminManager);

			// Create a manager with username "user" and a hashed password
			ManagerModel userManager = ManagerModel.builder()
					.username("Manager B")
					.password(passwordEncoder.encode("userPassword"))
					.build();
			managerRepository.save(userManager);

			// You can add more managers as needed

			System.out.println("Managers created successfully!");
		};
	}
}
