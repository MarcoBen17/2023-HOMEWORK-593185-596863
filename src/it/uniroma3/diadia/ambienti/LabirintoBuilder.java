package it.uniroma3.diadia.ambienti;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class LabirintoBuilder extends Labirinto{
	
	private Stanza stanzaCorrente;
	private Labirinto labirinto;
	
	
	public LabirintoBuilder addStanzaIniziale(String stanzaIniziale) {
		Stanza stanza= new Stanza(stanzaIniziale);
		this.labirinto= new Labirinto(stanza);
		this.labirinto.putStanza(stanzaIniziale);
		this.stanzaCorrente= stanza;
		return this;
	}
	
	public LabirintoBuilder addStanzaVincente(String stanzaVincente) {
		Stanza stanza= new Stanza(stanzaVincente);
		this.labirinto.setStanzaVincente(stanza);
		this.labirinto.putStanza(stanzaVincente);
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
	
	public LabirintoBuilder addStanza(String stanza) {
		Stanza room= new Stanza(stanza);
		this.labirinto.putStanza(stanza);
		this.stanzaCorrente=room;
		return this;
	}
	
	public LabirintoBuilder addAdiacenza(String stanza1, String stanza2, String direzione) {
		Stanza room2 = this.labirinto.getStanza(stanza2);
		Stanza room1 = this.labirinto.getStanza(stanza1);
		room1.impostaStanzaAdiacente(direzione, room2);
		return this;
	}
	
}
