package it.uniroma3.diadia.comando;

import it.uniroma3.diadia.Partita;

public class ComandoFine implements Comando{

	@Override
	public void esegui(Partita partita) {		// si desidera smettere
		partita.getIOConsole().mostraMessaggio("Grazie di aver giocato!"); 
		partita.setFinita();
	}

	@Override 
	public void setParametro(String parametro) {
		return;
	}
}
