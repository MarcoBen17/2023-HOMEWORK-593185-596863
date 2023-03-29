package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class PartitaTest {

	private Partita partita;
	
	
	@BeforeEach
	public void setUp() {
		this.partita= new Partita();
	}

	@Test
	void testgetCfu() {
		assertEquals(20, this.partita.getGiocatore().getCfu());
	}
	
	@Test
	void testVinta() {
		assertFalse(this.partita.vinta());
	}

	@Test
	void testGetStanzaCorrente() {
		
		assertSame("Atrio", this.partita.getStanzaCorrente().getNome());
	}

}
