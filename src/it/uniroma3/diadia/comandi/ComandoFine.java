package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

public class ComandoFine extends AbstractComando{

	@Override
	public void esegui(Partita partita) {		// si desidera smettere
		partita.getIO().mostraMessaggio("Grazie di aver giocato!"); 
		partita.setFinita();
	}

	@Override
	public String getNome() {
		return "fine";
	}
	
}
