package com.currencylayer.project.controller;

import java.util.Map;

import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.currencylayer.project.statistics.Statistics;

public class CurrencyLayerController2 {
	
	@Autowired
	Statistics statistics;
	
	@RequestMapping(value = "/average")
	public ResponseEntity<Object> getAverage() throws ParseException {
		return new ResponseEntity<>(statistics.getAverage(),HttpStatus.OK);
	} 

}
