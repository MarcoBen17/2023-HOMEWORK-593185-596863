package it.uniroma3.diadia.comando;

import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;


public class ComandoAiuto implements Comando {

	static final private String[] elencoComandi = {"vai", "aiuto", "fine", "prendi", "posa"};
	private IOConsole ioconsole;

	// implementazioni dei comandi dell'utente:

	/**
	 * Stampa informazioni di aiuto.
	 */
	@Override 
	public void esegui(Partita partita) {
		for(int i=0; i< elencoComandi.length; i++) 
			ioconsole.mostraMessaggio(elencoComandi[i]+" ");
		ioconsole.mostraMessaggio("------");
	}


	@Override 
	public void setParametro(String parametro) {
		return;
	}

}
