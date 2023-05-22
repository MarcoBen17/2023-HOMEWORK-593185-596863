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
	
	
}
