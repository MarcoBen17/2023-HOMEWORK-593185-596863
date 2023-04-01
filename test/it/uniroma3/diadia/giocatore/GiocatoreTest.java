package it.uniroma3.diadia.giocatore;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GiocatoreTest {
	private Giocatore giocatore;
	private Borsa borsa;

	@BeforeEach
	public void setUp() {
		this.giocatore= new Giocatore();
		this.giocatore.setCfu(20);
		this.borsa= new Borsa();
	}

	@Test
	void testGetBorsa() {
		this.giocatore.setBorsa(borsa);
		assertSame(borsa, this.giocatore.getBorsa());
	}

	@Test
	void testGetCfu() {
		assertSame(20, this.giocatore.getCfu());
	}

	@Test
	void testSetCfu() {
		this.giocatore.setCfu(10);
		assertSame(10, this.giocatore.getCfu());
	}

}
