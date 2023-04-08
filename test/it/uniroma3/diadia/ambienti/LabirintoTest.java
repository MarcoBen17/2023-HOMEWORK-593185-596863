package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LabirintoTest {
	private Labirinto labirinto;
	private Stanza uscita;
	private Stanza entrata;

	@BeforeEach
	public void setUp() {
		this.labirinto= new Labirinto();
		this.uscita= new Stanza("Uscita");
		this.entrata= new Stanza("Entrata");
	}
	@Test
	void testGetUscita() {
		assertSame("Biblioteca", this.labirinto.getUscita().getNome());
	}

	@Test
	void testSetUscita() {
		this.labirinto.setUscita(this.uscita);
		assertSame(this.uscita, this.labirinto.getUscita());
	}

	@Test
	void testGetEntrata() {
		assertSame("Atrio", this.labirinto.getEntrata().getNome());
	}

	@Test
	void testSetEntrata() {
		this.labirinto.setEntrata(this.entrata);
		assertSame(this.entrata, this.labirinto.getEntrata());
	}

}