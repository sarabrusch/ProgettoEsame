package com.currencylayer.project.controller;

import java.util.Map;

import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.currencylayer.project.statistics.Statistics;

/** Controller secondario che gestische le rotte e le chiamate all'API per
 * la restituzione di dati relativi alle statistiche*/

@RestController
public class CurrencyLayerStatisticsController {
	
	@Autowired
	Statistics statistics;
	
	/*@RequestMapping("/statistics/{acronym}")
	public ResponseEntity<Object> getStatistics(Map<String,Object> model,@PathVariable String acronym) throws ParseException {
		model.put("acronym", acronym);
		return new ResponseEntity<>(statistics.getStatistics(acronym),HttpStatus.OK);
	}*/
	
	@GetMapping("/statistics")
	public ResponseEntity<Object> getStatistics(@RequestParam(name="acronym",defaultValue = "EUR") String acronym) {
		return new ResponseEntity<>(statistics.getStatistics(acronym),HttpStatus.OK);
	}
	
	
}
