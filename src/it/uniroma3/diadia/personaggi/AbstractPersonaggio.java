package it.uniroma3.diadia.personaggi;

public abstract class AbstractPersonaggio {

	private String nome;
	private String descrizione;

	private boolean haSalutato;
	
	public AbstractPersonaggio(String nome, String descrizione) {
		this.nome= nome;
		this.descrizione= descrizione;
		this.haSalutato= false;
	}
	
	public String getNome() {
		return nome;
	}
	
	public abstract void agisci();
	
	public boolean haSalutato() {
		return this.haSalutato;
	}
	
	public void saluta() {
		StringBuilder presentazione= new StringBuilder();
		
	}
	
	@Override
	public String toString() {
		return this.getNome();
	}
}
