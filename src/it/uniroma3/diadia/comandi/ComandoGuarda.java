package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

public class ComandoGuarda extends AbstractComando implements Comando {

	@Override
	public void esegui(Partita partita) {
		partita.getIO().mostraMessaggio(partita.getStanzaCorrente().getDescrizione()+"\nCFU rimasti:"+ partita.getGiocatore().getCfu());
		return;
	}
	
	@Override
	public String getNome() {
		return "guarda";
	}
	
	
}
