package com.currencylayer.project.controller;

import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.currencylayer.project.service.BetServiceImpl;

@RestController
public class CurrencyLeyerBetController {
	
	@Autowired
	private BetServiceImpl betService;
	
	@GetMapping(value="/bet")
	public ResponseEntity<Object> doBet(@RequestParam(name="acronym1") String acronym1,@RequestParam(name="acronym2",required=false) String acronym2) throws ParseException {
		return new ResponseEntity<>(betService.doBet(acronym1,acronym2),HttpStatus.OK);
	}
	
	@GetMapping("/betResult")
	public ResponseEntity<Object> getStatistics() {
		return new ResponseEntity<>(betService.betResult(),HttpStatus.OK);
	}
	
	

}
