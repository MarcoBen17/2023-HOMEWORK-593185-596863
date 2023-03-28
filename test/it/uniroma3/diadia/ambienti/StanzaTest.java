package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

class StanzaTest {
	
	private Stanza vuota;
	private Stanza nonvuota;
	private Attrezzo osso;

	@BeforeEach
	public void setUp() {
		
		this.vuota= new Stanza("vuota");
		//System.out.println("sto eseguendo il setup");
		this.nonvuota= new Stanza("nonvuota");
		this.osso = new Attrezzo("osso", 1);
		this.nonvuota.addAttrezzo(this.osso);
	}
	
	
	@Test
	void testAddAttrezzo() {
		this.vuota.addAttrezzo(osso);
		assertTrue(this.vuota.hasAttrezzo("osso"));
		
	}

	@Test
	void testHasAttrezzo() {
		assertFalse(this.vuota.hasAttrezzo("osso"));
	}

	@Test
	void testRemoveAttrezzo() {
		assertTrue(this.nonvuota.removeAttrezzo(this.osso));
		assertFalse(this.vuota.removeAttrezzo(this.osso));
		
	}

	@Test
	void testGetAttrezzi() {
		assertSame(this.osso, this.nonvuota.getAttrezzo("osso"));
	}
	
}
