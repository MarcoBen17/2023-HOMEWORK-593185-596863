package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.comandi.ComandoVai;

class StanzaBloccataTest{

	private StanzaBloccata stanzaBloccata;
	private Attrezzo osso;
	private Stanza stanzaNord;
	private Stanza stanzaSud;
	private ComandoVai comandoVai;

	@BeforeEach
	public void setUp() {
		this.stanzaBloccata= new StanzaBloccata("N11", "nord", "osso");
		this.stanzaNord= new Stanza("N10");
		this.stanzaSud= new Stanza("N12");
		this.stanzaBloccata.impostaStanzaAdiacente("nord", this.stanzaNord);
		this.stanzaBloccata.impostaStanzaAdiacente("sud", this.stanzaSud);
		this.osso= new Attrezzo("osso", 1);
		this.comandoVai= new ComandoVai();
		
	}
	
	@Test
	void testDirezioneBloccata() {
		
	}

}
