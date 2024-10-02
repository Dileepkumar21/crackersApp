package com.pos.crackers;

import com.pos.crackers.repo.CrackersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CrackersApplication {

	@Autowired
	CrackersRepository crackerRepo;

	public static void main(String[] args) {
		SpringApplication.run(CrackersApplication.class, args);
	}
}

