package it.uniroma3.diadia.ambienti;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.uniroma3.diadia.attrezzi.*;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;
import it.uniroma3.diadia.personaggi.Cane;
import it.uniroma3.diadia.personaggi.Mago;
import it.uniroma3.diadia.personaggi.Strega;

public class Labirinto {

	private Stanza stanzaVincente;
	private Stanza stanzaIniziale;
	private Map<String, Stanza> mappaStanze;

	/**
	 * Crea tutte le stanze e le porte di collegamento
	 */
	public Labirinto() {
		this.mappaStanze= new HashMap<String, Stanza>();
	}
	
	public Labirinto(String nomeFile) throws FileNotFoundException, FormatoFileNonValidoException {
		CaricatoreLabirinto c= new CaricatoreLabirinto(nomeFile);
		c.carica();
		this.stanzaIniziale= c.getStanzaIniziale();
		this.stanzaVincente= c.getStanzaVincente();
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
		Stanza mensa= new StanzaBloccata("Mensa", Direzione.est, "osso");

		/* collega le stanze */
		atrio.impostaStanzaAdiacente(Direzione.nord, biblioteca);
		atrio.impostaStanzaAdiacente(Direzione.est, aulaN11);
		atrio.impostaStanzaAdiacente(Direzione.sud, aulaN10);
		atrio.impostaStanzaAdiacente(Direzione.ovest, laboratorio);
		aulaN11.impostaStanzaAdiacente(Direzione.est, laboratorio);
		aulaN11.impostaStanzaAdiacente(Direzione.ovest, atrio);
		aulaN10.impostaStanzaAdiacente(Direzione.nord, atrio);
		aulaN10.impostaStanzaAdiacente(Direzione.est, aulaN11);
		aulaN10.impostaStanzaAdiacente(Direzione.ovest, laboratorio);
		laboratorio.impostaStanzaAdiacente(Direzione.est, atrio);
		laboratorio.impostaStanzaAdiacente(Direzione.ovest, aulaN11);
		laboratorio.impostaStanzaAdiacente(Direzione.nord, mensa);
		biblioteca.impostaStanzaAdiacente(Direzione.sud, atrio);
		biblioteca.impostaStanzaAdiacente(Direzione.ovest, mensa);
		mensa.impostaStanzaAdiacente(Direzione.sud, laboratorio);
		mensa.impostaStanzaAdiacente(Direzione.est, biblioteca);
		

		/* pone gli attrezzi nelle stanze */
		aulaN10.addAttrezzo(lanterna);
		atrio.addAttrezzo(osso);
		
		/* Aggiungo i personaggi nelle stanze */
		atrio.setPersonaggio(new Cane("Fido", "BAU BAU", new Attrezzo("chiave", 1)));
		laboratorio.setPersonaggio(new Mago("Arcibald", "Sono stato mandato in questo labirinto dal rettore"
				+ "prova ad interagire con me magari posso esserti utile ;).", osso));
		mensa.setPersonaggio(new Strega("Fatima", "Sono la strega più temuta di tutto l'ateneo! Ma sopratutto"
				+ " odio i maleducati!"));
		

		/* setta l'entrata e l'uscita del labirinto */
		this.stanzaVincente = biblioteca;
		this.stanzaIniziale = atrio;  
		return this;
	}


	public Stanza getStanzaVincente() {
		return this.stanzaVincente;
	}

	public void setStanzaVincente(Stanza uscita) {
		this.stanzaVincente = uscita;
	}

	public Stanza getStanzaIniziale() {
		return this.stanzaIniziale;
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
	
	public static class LabirintoBuilder extends Labirinto{
		
		private Stanza stanzaCorrente;
		private Labirinto labirinto;
		static final private List<String> DIREZIONI= new ArrayList<String>(List.of("nord", "sud", "est", "ovest"));
		
	
		public LabirintoBuilder() {
			this.labirinto= new Labirinto();
		}
		
		public Stanza getStanzaCorrente() {
			return stanzaCorrente;
		}
		
		public void setStanzaCorrente(Stanza stanzaCorrente) {
			this.stanzaCorrente = stanzaCorrente;
		}
		
		public void setLabirinto(Labirinto labirinto) {
			this.labirinto = labirinto;
		}
		public Labirinto getLabirinto() {
			return this.labirinto;
		}
		
		public LabirintoBuilder addAttrezzo(String nome, int peso) {
			Attrezzo item= new Attrezzo(nome, peso);
			this.stanzaCorrente.addAttrezzo(item);
			return this;
		}
		
		
		//-------------------------Funzioni per inserire i personaggi--------------------------------------//
		
		
		public LabirintoBuilder addMago(String nome, String descrizione, String nomeAttrezzo, int peso) {
			Attrezzo att= new Attrezzo(nomeAttrezzo, peso);
			AbstractPersonaggio mago= new Mago(nome, descrizione, att);
			this.stanzaCorrente.setPersonaggio(mago);
			return this;
		}
		
		public LabirintoBuilder addStrega(String nome, String descrizione) {
			AbstractPersonaggio strega= new Strega(nome, descrizione);
			this.stanzaCorrente.setPersonaggio(strega);
			return this;
		}
		
		public LabirintoBuilder addCane(String nome, String descrizione, String nomeAttrezzo, int peso) {
			Attrezzo att= new Attrezzo(nomeAttrezzo, peso);
			AbstractPersonaggio cane= new Cane(nome, descrizione, att);
			this.stanzaCorrente.setPersonaggio(cane);
			return this;
		}
		
		
		//-------------------------Funzioni per la creazione delle stanze-----------------------------------//
		
		public LabirintoBuilder addStanza(String stanza) {
			Stanza room= new Stanza(stanza);
			this.labirinto.putStanza(room);
			this.stanzaCorrente=room;
			return this;
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
		
		public LabirintoBuilder addStanzaBloccata(String nome, String direzione, String nomeAttrezzo) {
			Stanza bloccata= new StanzaBloccata(nome, Direzione.valueOf(direzione), nomeAttrezzo);
			this.labirinto.putStanza(bloccata);
			this.stanzaCorrente= bloccata;
			return this;
		}
		
		public LabirintoBuilder addStanzaMagica(String nome, int soglia) {
			Stanza magica= new StanzaMagica(nome, soglia);
			this.labirinto.putStanza(magica);
			this.stanzaCorrente= magica;
			return this;
		}
		
		
		public LabirintoBuilder addStanzaMagica(String nome) {
			Stanza magica= new StanzaMagica(nome);
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
		
		public LabirintoBuilder addAdiacenza(String stanza1, String stanza2, String direzione) {
			Stanza room1 = this.labirinto.getStanza(stanza1);
			Stanza room2 = this.labirinto.getStanza(stanza2);
			if (DIREZIONI.contains(direzione))
				room1.impostaStanzaAdiacente(Direzione.valueOf(direzione), room2);  
			return this;
		}
		
		public Map<String, Stanza> getMappaStanze(){
			return this.labirinto.getMappaStanze();
		}
	}

	
}
