package com.currencylayer.project.controller;

import java.util.*;
import com.currencylayer.project.service.*;

import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/** Controller per la gestione delle richieste API con relativa risposta. 
 * @author Sara Bruschi
 * *******************  */

@RestController
public class CurrencyLayerController {

	@Autowired
	private CurrencyLayerServiceImpl currencyLayerService;
	
	//TODO {source}
	@RequestMapping(value = "/live")
	public ResponseEntity<Object> getLiveQuotation() throws ParseException {
		return new ResponseEntity<>(currencyLayerService.getLiveOrList("live"),HttpStatus.OK);
	} 
	
	@RequestMapping(value = "/list")
	public ResponseEntity<Object> getCurrencyList() throws ParseException {
		return new ResponseEntity<>(currencyLayerService.getLiveOrList("list"),HttpStatus.OK);
	} 
	
	@RequestMapping(value = "/historical/{date}")
	public ResponseEntity<Object> getHistory(Map<String,Object> model,@PathVariable String date) throws ParseException {
		model.put("date", date);
		return new ResponseEntity<>(currencyLayerService.getHistoricalQuotation("historical",date),HttpStatus.OK);
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
	//TODO rotta /bet
}