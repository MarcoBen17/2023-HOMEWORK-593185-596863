package it.uniroma3.diadia.personaggi;

import it.uniroma3.diadia.Partita;

public abstract class AbstractPersonaggio {
	
	private String nome;
	private String descrizione;
	private Boolean haSalutato;
	
	public AbstractPersonaggio(String nome, String descr) {
		this.nome=nome;
		this.descrizione=descr;
		this.haSalutato=false;
	}
	
	public String getNome() {
		return this.nome;
	}
	
	public boolean haSalutato() {
		return this.haSalutato;
	}
	public String getDescrizione() {
		return this.descrizione;
	}
	public String saluta() {
		StringBuilder ris= new StringBuilder("Ciao, io sono ");
		ris.append(this.getNome()+".");
		if(!haSalutato()) {
			ris.append(this.getDescrizione());
		}
		else {
			ris.append("Ci siamo già presentati!");
		}
		this.haSalutato=true;
		return ris.toString();
	}
	abstract public String agisci(Partita partita);
	
	@Override
	public String toString() {
		return this.getNome();
	}
	
	

	
}
