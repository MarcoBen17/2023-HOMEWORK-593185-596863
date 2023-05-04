package it.uniroma3.diadia.ambienti;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class LabirintoBuilder extends Labirinto{
	
	private Stanza stanzaCorrente;
	private Labirinto labirinto;
	
	
	public LabirintoBuilder addStanzaIniziale(String stanzaIniziale) {
		Stanza stanza= new Stanza(stanzaIniziale);
		this.labirinto= new Labirinto(stanza);
		this.stanzaCorrente= stanza;
		return this;
	}
	
	public LabirintoBuilder addStanzaVincente(String stanzaVincente) {
		Stanza stanza= new Stanza(stanzaVincente);
		this.labirinto.setStanzaVincente(stanza);
		this.stanzaCorrente= stanza;
		return this;
	}
	
	
	public Labirinto getLabirinto() {
		return this.labirinto;
	}
	
	public LabirintoBuilder addAttrezzo(String nome, int peso) {
		Attrezzo item= new Attrezzo(nome, peso);
		this.stanzaCorrente.addAttrezzo(item);
		return this;
	}
	
}
