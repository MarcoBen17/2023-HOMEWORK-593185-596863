package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LabirintoTest {
	private Labirinto labirinto;
	private Stanza uscita;
	private Stanza entrata;

	@BeforeEach
	public void setUp() throws FileNotFoundException, FormatoFileNonValidoException {
		this.labirinto= new Labirinto("testCaricatoreDueStanze.txt");
		this.uscita= new Stanza("Uscita");
		this.entrata= new Stanza("Entrata");
	}
	@Test
	void testGetUscita() {
		assertEquals("N11", this.labirinto.getStanzaVincente().getNome());
	}

	@Test
	void testSetUscita() {
		this.labirinto.setStanzaVincente(this.uscita);
		assertSame(this.uscita, this.labirinto.getStanzaVincente());
	}

	@Test
	void testGetEntrata() {
		assertEquals("N10", this.labirinto.getStanzaIniziale().getNome());
	}

	@Test
	void testSetEntrata() {
		this.labirinto.setStanzaIniziale(this.entrata);
		assertSame(this.entrata, this.labirinto.getStanzaIniziale());
	}

}
