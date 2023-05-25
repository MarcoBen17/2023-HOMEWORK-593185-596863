package it.uniroma3.diadia.personaggi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public abstract class AbstractPersonaggio {

	private String nome;
	private String descrizione;

	private boolean haSalutato;
	
	public AbstractPersonaggio(String nome, String descrizione) {
		this.nome= nome;
		this.descrizione= descrizione;
		this.haSalutato= false;
	}
	
	public abstract String riceviRegalo(Attrezzo attrezzo, Partita partita);
	
	public String getNome() {
		return nome;
	}
	
	public abstract String agisci(Partita partita);
	
	public boolean haSalutato() {
		return this.haSalutato;
	}
	
	public String saluta() {
		StringBuilder risposta= new StringBuilder("Salve, io sono ");
		risposta.append(this.getNome()+".");
		
		if (this.haSalutato()) risposta.append("Ci siamo gia' presentati, non ricordi?");
		
		else risposta.append(this.descrizione);
		
		this.haSalutato=true;
		return risposta.toString();
		
	}
	
	@Override
	public String toString() {
		return this.getNome();
	}
}
