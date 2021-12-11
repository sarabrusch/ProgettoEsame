package com.currencylayer.project.statistics;

import com.currencylayer.project.model.Currency;
import com.currencylayer.project.service.CurrencyLayerServiceImpl;

public class Statistics {

	CurrencyLayerServiceImpl zi = new CurrencyLayerServiceImpl();
	String name = zi.getCurrency();
	Double rate = zi.getValue();
	String acronym = zi.getAcronym();
	Currency ci = new Currency(name,acronym,rate);
	
	public String stampa() {
	String a = ci.toString();
	return a;
	}
}
