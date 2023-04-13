package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

public class ComandoGuarda implements Comando {

	@Override
	public void esegui(Partita partita) {
		partita.getIO().mostraMessaggio(partita.getStanzaCorrente().getDescrizione()+"\nCFU rimasti:"+ partita.getGiocatore().getCfu());
		return;
	}

	@Override
	public void setParametro(String parametro) {
		return;
	}
	
	@Override
	public String getNome() {
		return "guarda";
	}
	
	@Override
	public String getParametro() {
		return null;
	}
}
