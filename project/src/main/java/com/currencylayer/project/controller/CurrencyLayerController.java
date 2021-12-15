package com.currencylayer.project.controller;

import java.util.*;
import com.currencylayer.project.service.*;
import com.currencylayer.project.statistics.Statistics;

import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/** Controller per la gestione delle richieste API relative ad informazioni sulle
 * varia valute e coppie di valute. 
 * @author Sara Bruschi
 * *******************  */

@RestController
public class CurrencyLayerController {

	@Autowired
	CurrencyLayerServiceImpl currencyLayerService;
	@Autowired
	Statistics statistics;
	@Autowired
	BetServiceImpl betService;

	@RequestMapping(value = "/live")
	public ResponseEntity<Object> getLiveQuotation() throws ParseException {
		return new ResponseEntity<>(currencyLayerService.getData("live"),HttpStatus.OK);
	} 

	@RequestMapping(value = "/list")
	public ResponseEntity<Object> getCurrencyList() throws ParseException {
		return new ResponseEntity<>(currencyLayerService.getData("list"),HttpStatus.OK);
	} 

	@RequestMapping(value = "/historical/{date}")
	public ResponseEntity<Object> getHistory(Map<String,Object> model,@PathVariable String date) throws ParseException {
		model.put("date", date);
		return new ResponseEntity<>(currencyLayerService.getHistoricalQuotation(date),HttpStatus.OK);
	}

	@RequestMapping(value = "/list/{acronym}")
	public ResponseEntity<Object> getCurrency(Map<String,Object> model,@PathVariable String acronym) throws ParseException {
		model.put("acronym", acronym);
		return new ResponseEntity<>(currencyLayerService.getCurrency(acronym),HttpStatus.OK);
	} 

	@RequestMapping(value = "/live/{acronym}")
	public ResponseEntity<Object> getCouple(Map<String,Object> model,@PathVariable String acronym) throws ParseException {
		model.put("acronym", acronym);
		return new ResponseEntity<>(currencyLayerService.getCouple(acronym),HttpStatus.OK);
	} 

	@RequestMapping(value = "/statistics/{acronym}")
	public ResponseEntity<Object> getStatistics(Map<String,Object> model,@PathVariable String acronym) throws ParseException {
		model.put("acronym", acronym);
		return new ResponseEntity<>(statistics.getStatistics(acronym),HttpStatus.OK);
	} 

	@GetMapping(value="/bet")
	public ResponseEntity<Object> doBet(@RequestParam(name="acronym1") String acronym1,@RequestParam(name="acronym2",required=false) String acronym2) throws ParseException {
		return new ResponseEntity<>(betService.doBet(acronym1,acronym2),HttpStatus.OK);
	} 

	@GetMapping("/betResult")
	public ResponseEntity<Object> betResult() {
		return new ResponseEntity<>(betService.betResult(),HttpStatus.OK);
	} 
}
