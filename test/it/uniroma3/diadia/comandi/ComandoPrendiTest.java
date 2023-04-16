package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.comandi.Comando;
import it.uniroma3.diadia.comandi.FabbricaDiComandiFisarmonica;

class ComandoPrendiTest {
	private Partita partita;
	private Attrezzo attrezzo;
	private Comando prendi;

	@BeforeEach
	public void setUp() {
		this.partita= new Partita(new IOConsole());
		this.attrezzo= new Attrezzo("spada", 1);
		this.prendi= new ComandoPrendi();
		this.partita.getStanzaCorrente().addAttrezzo(this.attrezzo);
	}
	
	
	@Test
	void testEseguiStanzaVuota() {
		assertTrue(this.partita.getStanzaCorrente().hasAttrezzo("spada"));
	}

	@Test
	void testEsegui() {
		this.prendi.setParametro("spada");
		this.prendi.esegui(this.partita);
		assertFalse(this.partita.getStanzaCorrente().hasAttrezzo("spada"));
	}
}
