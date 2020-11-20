package com.comit;

import com.comit.model.ERole;
import com.comit.model.Role;
import com.comit.repository.RoleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;



@SpringBootApplication
public class ComitApplication {

	private static final Logger log = LoggerFactory.getLogger(ComitApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(ComitApplication.class, args);
	}

	@Bean
	public CommandLineRunner initDatabase(RoleRepository repository) {
		return args -> {
			log.info("Preloading " + repository.save(new Role(ERole.USER)));
			log.info("Preloading " + repository.save(new Role(ERole.ADMIN)));
		};
	}

}
