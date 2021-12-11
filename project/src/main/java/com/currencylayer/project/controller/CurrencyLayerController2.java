package com.currencylayer.project.controller;

import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;

import com.currencylayer.project.statistics.Statistics;

public class CurrencyLayerController2 {
	@Autowired
	Statistics s;
	
	@RequestMapping(value = "/a")
	public ResponseEntity<Object> getCurrencyList() throws ParseException {
		return new ResponseEntity<>(s.stampa(),HttpStatus.OK);
	} 

}
