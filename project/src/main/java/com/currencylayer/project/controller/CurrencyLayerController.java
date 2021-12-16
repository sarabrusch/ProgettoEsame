package com.currencylayer.project.controller;

import java.util.*;

import com.currencylayer.project.exceptions.CurrencyNotFoundException;
import com.currencylayer.project.filters.Filters;
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

/** Classe Controller per la gestione delle richieste API relative ad informazioni sulle
 * varie valute e coppie di valute. Definisce le rotte e la risposta che ogni richiesta
 * restituisce. Le richieste devono essere fatte all'indirizzo localhost:8080.
 * @author Sara Bruschi
 * @author Marco Di Vita
 */

@RestController
public class CurrencyLayerController {

	@Autowired
	CurrencyLayerServiceImpl currencyLayerService;
	@Autowired
	Statistics statistics;
	@Autowired
	BetServiceImpl betService;
	@Autowired
	Filters filters;

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

	@RequestMapping(value = "/statistics/{acronym}")
	public ResponseEntity<Object> getStatistics(Map<String,Object> model,@PathVariable String acronym) throws ParseException {
		model.put("acronym", acronym);
		return new ResponseEntity<>(statistics.getStatistics(acronym),HttpStatus.OK);
	} 

	@GetMapping(value="/bet")
	public ResponseEntity<Object> doBet(@RequestParam(name="acronym1") String acronym1, @RequestParam(name="acronym2",required=false) String acronym2, @RequestParam(name="acronym3",required=false) String acronym3) throws ParseException, CurrencyNotFoundException {
		return new ResponseEntity<>(betService.doBet(acronym1,acronym2,acronym3),HttpStatus.OK);
	} 

	@GetMapping("/betResult")
	public ResponseEntity<Object> betResult() {
		return new ResponseEntity<>(betService.betResult(),HttpStatus.OK);
	} 
	
	@RequestMapping(value = "/currencyFilter/{acronym}")
	public ResponseEntity<Object> currencyFilter(Map<String,Object> model,@PathVariable String acronym) throws ParseException {
		model.put("acronym", acronym);
		return new ResponseEntity<>(filters.currencyFilter(acronym),HttpStatus.OK);
	} 
	
	@GetMapping(value="/historicalFilter")
	public ResponseEntity<Object> historicalFilter(@RequestParam(name="date") String date, @RequestParam(name="acronym1") String acronym1,@RequestParam(name="acronym2",required=false) String acronym2) throws ParseException {
		return new ResponseEntity<>(filters.historicalFilter(date,acronym1,acronym2),HttpStatus.OK);
	} 
}
