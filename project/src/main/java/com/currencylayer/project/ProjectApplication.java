package com.currencylayer.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/** Classe principale del programma che permette a tutta l'applicazione Spring Boot
 * di partire ed essere eseguita. 
 * @author Sara Bruschi
 * @author Marco Di Vita
 * */

@SpringBootApplication
public class ProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectApplication.class, args);
	}
}
