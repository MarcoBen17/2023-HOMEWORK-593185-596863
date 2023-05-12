package it.uniroma3.diadia.ambienti;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class LabirintoBuilder extends Labirinto{
	
	private Stanza stanzaCorrente;
	private Labirinto labirinto;
	
	public LabirintoBuilder() {
		this.labirinto= new Labirinto();
	}
	
	public LabirintoBuilder addStanzaIniziale(String stanzaIniziale) {
		Stanza stanza= new Stanza(stanzaIniziale);
		this.labirinto.setStanzaIniziale(stanza);
		Map<String, Stanza> mappa = this.labirinto.getMappa();
		mappa.put(stanzaIniziale,stanza);
		this.stanzaCorrente= stanza;
		return this;
	}
	
	public LabirintoBuilder addStanzaVincente(String stanzaVincente) {
		Stanza stanza= new Stanza(stanzaVincente);
		this.labirinto.setStanzaVincente(stanza);
		Map<String, Stanza> mappa = this.labirinto.getMappa();
		mappa.put(stanzaVincente, stanza);
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
	public LabirintoBuilder addAdiacenza(String stanza,String adiacenza,String direzione) {
		Stanza room1= this.labirinto.getStanza(stanza);
		Stanza room2= this.labirinto.getStanza(adiacenza);
		if(direzione.equals("nord") || direzione.equals("est") || direzione.equals("ovest")|| direzione.equals("sud") )
		room1.impostaStanzaAdiacente(direzione, room2);		
		return this;
	}

	public LabirintoBuilder addStanza(String string) {
		Stanza stanza = new Stanza(string);
		this.labirinto.getMappa().put(string, stanza);
		this.stanzaCorrente=stanza;
		return this;
	}

	public Map<String, Stanza> getListaStanze() {		
		return this.labirinto.getMappa();
	}
	public LabirintoBuilder addStanzaMagica(String nome, int soglia) {
		Stanza stanzaMagica= new StanzaMagica(nome, soglia);
		this.labirinto.getMappa().put(nome, stanzaMagica);
		this.stanzaCorrente= stanzaMagica;
		return this;
	}
	public LabirintoBuilder addStanzaBuia(String nome, String attrezzo) {
		Stanza stanzaBuia= new StanzaBuia(nome, attrezzo);
		this.labirinto.getMappa().put(nome, stanzaBuia);
		this.stanzaCorrente= stanzaBuia;
		return this;
	}
	public LabirintoBuilder addStanzaBloccata(String nome, String direzione, String attrezzo ) {
		Stanza stanzaBloccata= new StanzaBloccata(nome, direzione, attrezzo);
		this.labirinto.getMappa().put(nome, stanzaBloccata);
		this.stanzaCorrente= stanzaBloccata;
		return this;
	}
	
	
	
}
