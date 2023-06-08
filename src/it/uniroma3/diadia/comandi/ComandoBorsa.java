package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.giocatore.Borsa;

public class ComandoBorsa extends AbstractComando {

	@Override
	public void esegui(Partita partita) {
		Borsa borsa = partita.getGiocatore().getBorsa();
		partita.getIO().mostraMessaggio(borsa.getContenutoOrdinatoPerPeso().toString());

	}


}
