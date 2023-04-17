package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

class StanzaBuiaTest {
	
	private StanzaBuia stanzaBuia;
	private Attrezzo lanterna;
	private Stanza copiaStanza;
	private Attrezzo osso;

	@BeforeEach
	public void setUp() {
		this.stanzaBuia= new StanzaBuia("n11", "lanterna");
		this.copiaStanza= new Stanza("n11");
		this.lanterna= new Attrezzo("lanterna", 1);
		this.osso= new Attrezzo("osso", 1);
		this.stanzaBuia.addAttrezzo(this.osso);
	}

	@Test
	void testBuia() {
		assertEquals(this.stanzaBuia.getDescrizione(), "qui c'è buio pesto");
	}
	
	@Test
	void testAccesa() {
		this.stanzaBuia.addAttrezzo(this.lanterna);
		this.copiaStanza.addAttrezzo(this.osso);
		this.copiaStanza.addAttrezzo(this.lanterna);
		assertEquals(this.stanzaBuia.getDescrizione(), this.copiaStanza.getDescrizione() );
	}

}
