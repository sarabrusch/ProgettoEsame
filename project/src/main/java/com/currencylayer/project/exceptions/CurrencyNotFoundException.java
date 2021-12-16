package com.currencylayer.project.exceptions;

/** Eccezione personalizzata che permette di verificare che la currency richiesta
 * dall'utente in ingresso esista e sia presente nella lista di tutte le currency
 * o semplicemente che sia scritta in modo corretto.
 * @author Sara Bruschi
 * @author Marco Di Vita
 * */
public class CurrencyNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;

	public CurrencyNotFoundException (String message) {
		super(message);
	}
}
