package it.uniroma3.diadia.ambienti;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

import it.uniroma3.diadia.attrezzi.*;

public class Labirinto {

	private Stanza stanzaVincente;
	private Stanza stanzaIniziale;
	private Map<String, Stanza> mappaStanze;
	private Labirinto lab;

	/**
	 * Crea tutte le stanze e le porte di collegamento
	 */
	public Labirinto() {
		this.mappaStanze= new HashMap<String, Stanza>();
	}
	public Labirinto(String nomeFile) throws FileNotFoundException, FormatoFileNonValidoException {
		CaricatoreLabirinto c= new CaricatoreLabirinto(nomeFile);
		c.carica();			
		this.stanzaIniziale=c.getStanzaIniziale();
		this.stanzaVincente=c.getStanzaVincente();
	}
	
	public Labirinto creaLabirintoDefault() {
		
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
		this.stanzaVincente = biblioteca;
		this.stanzaIniziale = atrio;  
		return this;
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
	
	public Stanza putStanza(Stanza stanza) {
		return this.mappaStanze.put(stanza.getNome(), stanza);
	}

	public Map<String, Stanza> getMappaStanze() {
		return this.mappaStanze;
	}
	
	
}
