package com.currencylayer.project.test;

import org.json.simple.JSONObject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.currencylayer.project.exceptions.CurrencyNotFoundException;
import com.currencylayer.project.exceptions.InvalidFormatDateException;
import com.currencylayer.project.filters.Filters;
import com.currencylayer.project.service.BetServiceImpl;
import com.currencylayer.project.service.CurrencyLayerServiceImpl;
import com.currencylayer.project.utilis.FileAnalysis;

import junit.framework.TestCase;

/** Classe di test necessario a controllare il corretto funzionamento
 * di alcune parti del programma
 * @author Marco Di Vita
 * @author Sara Bruschi
 * */

class CurrencyLayerTest extends TestCase {
	
	private static CurrencyLayerServiceImpl currencyService;
	private static BetServiceImpl betService;
	private static Filters filter;
	private static FileAnalysis file;

	/** Metodo che inizializza gli attributi prima di iniziare i test 
	 * @throws Exception
	 * */
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		try {
			currencyService = new CurrencyLayerServiceImpl();
			file = new FileAnalysis();
			filter = new Filters();
			betService = new BetServiceImpl();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/** Metodo che si occupa di terminare i test una volta finiti
	 * @throws Exception
	 * */
	@AfterEach
	public void tearDown() throws Exception {}

	/** Metodo per controllare il corretto funzionamento del filtro che restituisce
	 * il nome della valuta
	 * @throws CurrencyNotFoundException
	 * */ 
	@Test
	@DisplayName("Controllo correttezza filtro per nome")
	void testFilter() throws CurrencyNotFoundException {
		assertEquals(currencyService.getCurrency("EUR"),"Euro");
	}
	
	/** Metodo per controllare il corretto funzionamento del metodo che restituisce
	 * gli storici dei tassi di cambio.
	 * @throws InvalidFormatDateException
	 * */ 
	@Test
	@DisplayName("Controllo correttezza historical")
	void testHistorical() throws InvalidFormatDateException {
		JSONObject historical = (JSONObject) file.readFile("2021-12-12","quotes");
		assertEquals(historical.get("USDEUR"),0.88406);
	}
	
	/** Metodo per controllare il corretto funzionamento dell'eccezione
	 * personalizzata che viene lanciata quando l'acronimo inserito non 
	 * e' corretto.
	 * */ 
	@Test
	@DisplayName("Controllo lancio eccezione acronimo")
	void testExceptionAcronym() {
		try {
			betService.doBet("eur", "BTC", "GBP");
			fail("No exception generated");
		} catch (CurrencyNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/** Metodo per controllare il corretto funzionamento dell'eccezione
	 * personalizzata che viene lanciata quando la data inserita non 
	 * e' corretta.
	 * */ 
	@Test
	@DisplayName("Controllo lancio eccezione data")
	void testExceptionDate() {
		try {
			filter.historicalFilter("12-12-2021", "EUR", "GBP");
			fail("No exception generated");
		} catch (InvalidFormatDateException | CurrencyNotFoundException e) {
			e.printStackTrace();
		}
	}
	

}
