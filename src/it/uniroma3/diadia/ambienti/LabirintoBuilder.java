package it.uniroma3.diadia.ambienti;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;
import it.uniroma3.diadia.personaggi.Mago;

public class LabirintoBuilder extends Labirinto{
	
	private Stanza stanzaCorrente;
	

	private Labirinto labirinto;
	static final private List<String> DIREZIONI= new ArrayList<String>(List.of("nord", "sud", "est", "ovest"));
	
	public Stanza getStanzaCorrente() {
		return stanzaCorrente;
	}
	
	public void setStanzaCorrente(Stanza stanzaCorrente) {
		this.stanzaCorrente = stanzaCorrente;
	}
	
	public void setLabirinto(Labirinto labirinto) {
		this.labirinto = labirinto;
	}
	
	public LabirintoBuilder() {
		this.labirinto= new Labirinto();
	}
	
	public LabirintoBuilder addStanzaIniziale(String stanzaIniziale) {
		Stanza stanza= new Stanza(stanzaIniziale);
		this.labirinto.setStanzaIniziale(stanza);
		this.labirinto.putStanza(stanza);
		this.stanzaCorrente= stanza;
		return this;
	}
	
	public LabirintoBuilder addStanzaVincente(String stanzaVincente) {
		Stanza stanza= new Stanza(stanzaVincente);
		this.labirinto.setStanzaVincente(stanza);
		this.labirinto.putStanza(stanza);
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
	
	public LabirintoBuilder addStanzaMagica(String nome, int soglia) {
		Stanza magica= new StanzaMagica(nome, soglia);
		this.labirinto.putStanza(magica);
		this.stanzaCorrente= magica;
		return this;
	}
	
	public LabirintoBuilder addStanzaBuia(String nome, String attrezzo) {
		Stanza buia= new StanzaBuia(nome, attrezzo);
		this.labirinto.putStanza(buia);
		this.stanzaCorrente= buia;
		return this;
	}
	
	public LabirintoBuilder addMago(String nome, String descrizione, String nomeAttrezzo, int peso) {
		Attrezzo att= new Attrezzo(nomeAttrezzo, peso);
		AbstractPersonaggio mago= new Mago(nome, descrizione, att);
		this.stanzaCorrente.setPersonaggio(mago);
		return this;
	}
	
	public LabirintoBuilder addStanza(String stanza) {
		Stanza room= new Stanza(stanza);
		this.labirinto.putStanza(room);
		this.stanzaCorrente=room;
		return this;
	}
	
	public LabirintoBuilder addStanzaBloccata(String nome, String direzione, String nomeAttrezzo) {
		Stanza bloccata= new StanzaBloccata(nome, direzione, nomeAttrezzo);
		this.labirinto.putStanza(bloccata);
		this.stanzaCorrente= bloccata;
		return this;
	}
	
	public LabirintoBuilder addAdiacenza(String stanza1, String stanza2, String direzione) {
		Stanza room1 = this.labirinto.getStanza(stanza1);
		Stanza room2 = this.labirinto.getStanza(stanza2);
		if (DIREZIONI.contains(direzione))
			room1.impostaStanzaAdiacente(direzione, room2);  
		return this;
	}
	
	public Map<String, Stanza> getMappaStanze(){
		return this.labirinto.getMappaStanze();
	}
}
