package it.uniroma3.diadia.comando;

import it.uniroma3.diadia.Partita;

public class ComandoNonValido implements Comando {
	private String parametro;

	@Override
	public void esegui(Partita partita) {
		partita.getIOConsole().mostraMessaggio("Comando sconosciuto");
		return;
	}

	@Override
	public void setParametro(String parametro) {
		this.parametro= null;
		return;
	}

}
