package com.currencylayer.project.exceptions;

/** Eccezione personalizzata che permette di verificare che la data richiesta
 * dall'utente in ingresso esista o semplicemente che sia scritta in modo corretto.
 * @author Sara Bruschi
 * @author Marco Di Vita
 * */
public class InvalidFormatDateException extends Exception {

	private static final long serialVersionUID = 1L;

	public InvalidFormatDateException (String message) {
		super(message);
	}
}
