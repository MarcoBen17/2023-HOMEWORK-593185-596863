package it.uniroma3.diadia.giocatore;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

class BorsaTest {
	
	private Borsa borsavuota;
	private Attrezzo osso;
	private Borsa borsa;

	@BeforeEach
	public void setUp() {
		this.borsavuota = new Borsa();
		this.osso= new Attrezzo("osso", 1);
		this.borsa= new Borsa();
		this.borsa.addAttrezzo(this.osso);
	}

	@Test
	void testAddAttrezzo() {
		this.borsavuota.addAttrezzo(osso);
		assertTrue(this.borsavuota.hasAttrezzo("osso"));
	}

	@Test
	void testGetAttrezzo() {
		assertSame(this.osso, this.borsa.getAttrezzo("osso"));
	}
	@Test
	void testHasAttrezzo() {
		assertTrue(this.borsa.hasAttrezzo("osso"));
		assertFalse(this.borsavuota.hasAttrezzo("osso"));
	}

	@Test
	void testRemoveAttrezzo() {
		this.borsa.removeAttrezzo("osso");
		assertFalse(this.borsa.hasAttrezzo("osso"));
	}

}
