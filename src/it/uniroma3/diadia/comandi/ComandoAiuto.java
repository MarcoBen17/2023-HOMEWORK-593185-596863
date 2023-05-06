package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;


public class ComandoAiuto implements Comando {

	static final private String[] elencoComandi = {"vai", "aiuto", "fine", "prendi", "posa", "guarda", "borsa"};

	// implementazioni dei comandi dell'utente:

	/**
	 * Stampa informazioni di aiuto.
	 */
	@Override 
	public void esegui(Partita partita) {
		for(int i=0; i< elencoComandi.length; i++) 
			partita.getIO().mostraMessaggio(elencoComandi[i]+" ");
		partita.getIO().mostraMessaggio("------");
	}


	@Override 
	public void setParametro(String parametro) {
		return;
	}
	
	@Override
	public String getNome() {
		return "aiuto";
	}
	
	@Override
	public String getParametro() {
		return null;
	}
}
