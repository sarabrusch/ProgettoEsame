package com.currencylayer.project.controller;

import java.util.Map;

import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.currencylayer.project.statistics.Statistics;

@RestController
public class CurrencyLayerController2 {
	
	@Autowired
	Statistics statistics;
	
	@RequestMapping(value = "/average/{acronym}")
	public ResponseEntity<Object> getAverage(Map<String,Object> model,@PathVariable String acronym) throws ParseException {
		model.put("acronym", acronym);
		return new ResponseEntity<>(statistics.getAverage(acronym),HttpStatus.OK);
	}
	
	@RequestMapping(value = "/ave")
	public ResponseEntity<Object> exchangeRates() throws ParseException {
		return new ResponseEntity<>(statistics.exchangeRates(),HttpStatus.OK);
	} 
}
