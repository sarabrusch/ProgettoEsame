package com.currencylayer.project.model;

/** Classe Source che modella la valuta alla quale si fa sempre riferimento quando
 * si richiede il tasso di cambio di una coppia di valute.
 * Estende la superclasse Currency che modella una generica valuta, descritta
 * da nome e acronimo.
 * @author Sara Bruschi
 * @author Marco Di Vita
 * */

public class Source extends Currency {

	private static final String name = "United States Dollar";
	private static final String acronym = "USD";

	/** Generico costruttore che richiama il costruttore della superclasse
	 * inizializzandolo con nome e acronimo della source.
	 * */
	public Source() {
		super(name, acronym);
	}

}
