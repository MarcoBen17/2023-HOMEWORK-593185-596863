package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.giocatore.Borsa;

public class ComandoRegala extends AbstractComando {

	private String parametro;

	@Override
	public void esegui(Partita partita) {
		Borsa borsa = partita.getGiocatore().getBorsa();
		if (this.parametro != null && borsa.hasAttrezzo(this.parametro)) {
			partita.getIO().mostraMessaggio(partita.getStanzaCorrente().getPersonaggio().riceviRegalo(borsa.removeAttrezzo(parametro), partita));
		}
		else partita.getIO().mostraMessaggio("Spiacente ma non hai questo attrezzo nella borsa...");
	}

	@Override 
	public void setParametro(String parametro) {
		this.parametro=parametro;
	}
	
	@Override
	public String getParametro() {
		return this.parametro;
	}
}
