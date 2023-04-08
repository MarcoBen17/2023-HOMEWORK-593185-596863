package it.uniroma3.diadia.comando;

import it.uniroma3.diadia.Partita;


public class ComandoAiuto implements Comando {

	static final private String[] elencoComandi = {"vai", "aiuto", "fine", "prendi", "posa", "guarda"};

	// implementazioni dei comandi dell'utente:

	/**
	 * Stampa informazioni di aiuto.
	 */
	@Override 
	public void esegui(Partita partita) {
		for(int i=0; i< elencoComandi.length; i++) 
			partita.getIOConsole().mostraMessaggio(elencoComandi[i]+" ");
		partita.getIOConsole().mostraMessaggio("------");
	}


	@Override 
	public void setParametro(String parametro) {
		return;
	}

}
