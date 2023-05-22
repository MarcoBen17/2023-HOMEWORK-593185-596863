package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

public class ComandoNonValido extends AbstractComando implements Comando {
	private String parametro;

	@Override
	public void esegui(Partita partita) {
		partita.getIO().mostraMessaggio("Comando sconosciuto");
		return;
	}

	
	
	@Override
	public String getNome() {
		return "comandononvalido";
	}
	
	
}
