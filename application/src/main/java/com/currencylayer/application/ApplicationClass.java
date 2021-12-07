package com.currencylayer.application;

import org.springframework.stereotype.Component;
import org.springframework.boot.CommandLineRunner;

@Component
public class ApplicationClass implements CommandLineRunner {
	
	public void run(String[] args) {
		System.out.println("Benvenuto");
	}

}
