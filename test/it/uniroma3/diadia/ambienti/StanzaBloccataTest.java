package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

class StanzaBloccataTest{

	private StanzaBloccata stanzaBloccata;
	private Attrezzo osso;
	private Stanza stanzaNord;
	private Stanza stanzaSud;
	

	@BeforeEach
	public void setUp() {
		this.stanzaBloccata= new StanzaBloccata("N11", "nord", "osso");
		this.stanzaNord= new Stanza("N10");
		this.stanzaSud= new Stanza("N12");
		this.stanzaBloccata.impostaStanzaAdiacente("nord", this.stanzaNord);
		this.stanzaBloccata.impostaStanzaAdiacente("sud", this.stanzaSud);
		this.osso= new Attrezzo("osso", 1);
		
	}
	
	@Test
	void testDirezioneBloccata() {
		assertSame(this.stanzaBloccata, this.stanzaBloccata.getStanzaAdiacente("nord"));
		assertSame(this.stanzaSud, this.stanzaBloccata.getStanzaAdiacente("sud"));
	}

	@Test 
	void testDirezioneSbloccata() {
		this.stanzaBloccata.addAttrezzo(this.osso);
		assertSame(this.stanzaNord, this.stanzaBloccata.getStanzaAdiacente("nord"));
	}
	
}