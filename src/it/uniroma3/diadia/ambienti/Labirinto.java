package it.uniroma3.diadia.ambienti;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.uniroma3.diadia.attrezzi.*;

public class Labirinto {

	private Stanza stanzaVincente;
	private Stanza stanzaIniziale;
	private Map<String, Stanza> mappaStanze;

	/**
	 * Crea tutte le stanze e le porte di collegamento
	 */
	public Labirinto() {

		/* crea gli attrezzi */
		Attrezzo lanterna = new Attrezzo("lanterna",3);
		Attrezzo osso = new Attrezzo("osso",1);

		/* crea stanze del labirinto */
		Stanza atrio = new Stanza("Atrio"); 
		Stanza aulaN11 = new StanzaMagica("Aula N11"); 
		Stanza aulaN10 = new Stanza("Aula N10");
		Stanza laboratorio = new StanzaBuia("Laboratorio Campus","lanterna");
		Stanza biblioteca = new Stanza("Biblioteca");
		Stanza mensa= new StanzaBloccata("Mensa", "est", "osso");

		/* collega le stanze */
		atrio.impostaStanzaAdiacente("nord", biblioteca);
		atrio.impostaStanzaAdiacente("est", aulaN11);
		atrio.impostaStanzaAdiacente("sud", aulaN10);
		atrio.impostaStanzaAdiacente("ovest", laboratorio);
		aulaN11.impostaStanzaAdiacente("est", laboratorio);
		aulaN11.impostaStanzaAdiacente("ovest", atrio);
		aulaN10.impostaStanzaAdiacente("nord", atrio);
		aulaN10.impostaStanzaAdiacente("est", aulaN11);
		aulaN10.impostaStanzaAdiacente("ovest", laboratorio);
		laboratorio.impostaStanzaAdiacente("est", atrio);
		laboratorio.impostaStanzaAdiacente("ovest", aulaN11);
		laboratorio.impostaStanzaAdiacente("nord", mensa);
		biblioteca.impostaStanzaAdiacente("sud", atrio);
		biblioteca.impostaStanzaAdiacente("ovest", mensa);
		mensa.impostaStanzaAdiacente("sud", laboratorio);
		mensa.impostaStanzaAdiacente("est", biblioteca);
		

		/* pone gli attrezzi nelle stanze */
		aulaN10.addAttrezzo(lanterna);
		atrio.addAttrezzo(osso);

		/* setta l'entrata e l'uscita del labirinto */
		stanzaVincente = biblioteca;
		stanzaIniziale = atrio;  
		
		/* aggiungo le stanze nella mappa delle stanze */
		this.mappaStanze= new HashMap<String, Stanza>();
		this.mappaStanze.put("Atrio", atrio);
		this.mappaStanze.put("Aula N11", aulaN11);
		this.mappaStanze.put("Aula N10", aulaN10);
		this.mappaStanze.put("Mensa", mensa);
		this.mappaStanze.put("Laboratorio Campus", laboratorio);
		this.mappaStanze.put("Biblioteca", biblioteca);
	}
	
	
	public Labirinto(Stanza stanzaIniziale) {
		this.mappaStanze= new HashMap<String, Stanza>();
		this.stanzaIniziale= stanzaIniziale;
	}

	public Stanza getStanzaVincente() {
		return stanzaVincente;
	}

	public void setStanzaVincente(Stanza uscita) {
		this.stanzaVincente = uscita;
	}

	public Stanza getStanzaIniziale() {
		return stanzaIniziale;
	}

	public void setStanzaIniziale(Stanza entrata) {
		this.stanzaIniziale = entrata;
	}

	public Stanza getStanza(String stanza) {
		return this.mappaStanze.get(stanza);
	}
	
	public Stanza putStanza(String stanza) {
		return this.mappaStanze.put(stanza, new Stanza(stanza));
	}
	
	public Map<String, Stanza> getListaStanze(){
		return this.mappaStanze;
	}
}
