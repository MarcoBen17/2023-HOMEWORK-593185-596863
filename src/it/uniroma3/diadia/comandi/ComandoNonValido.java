package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

public class ComandoNonValido implements Comando {
	private String parametro;

	@Override
	public void esegui(Partita partita) {
		partita.getIO().mostraMessaggio("Comando sconosciuto");
		return;
	}

	@Override
	public void setParametro(String parametro) {
		this.parametro= null;
		return;
	}
	
	@Override
	public String getNome() {
		return "comandononvalido";
	}
	
	@Override
	public String getParametro() {
		return null;
	}
}
