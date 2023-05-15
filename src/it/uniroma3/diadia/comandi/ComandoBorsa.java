package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.giocatore.Borsa;

public class ComandoBorsa implements Comando {

	@Override
	public void esegui(Partita partita) {
		Borsa borsa = partita.getGiocatore().getBorsa();
		partita.getIO().mostraMessaggio(borsa.getContenutoOrdinatoPerPeso().toString());
	}

	@Override
	public void setParametro(String parametro) {
		 return;
	}

	@Override
	public String getParametro() {
		return null;
	}

	@Override
	public String getNome() {
		return "borsa";
	}

}
