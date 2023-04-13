package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.comandi.Comando;
import it.uniroma3.diadia.comandi.FabbricaDiComandiFisarmonica;

class ComandoPosaTest {
	
	private Partita partita;
	private Attrezzo attrezzo;
	private FabbricaDiComandiFisarmonica factory;
	private Comando posa;

	@BeforeEach
	public void setUp() {
		this.partita= new Partita(new IOConsole());
		this.attrezzo= new Attrezzo("spada", 1);
		this.factory= new FabbricaDiComandiFisarmonica();
		this.posa= factory.costruisciComando("posa");
	}

	@Test
	void testEseguiStanzaVuota() {
		assertFalse(this.partita.getStanzaCorrente().hasAttrezzo("spada"));
	}

	@Test
	void testEsegui() {
		this.partita.getGiocatore().getBorsa().addAttrezzo(this.attrezzo);
		this.posa.setParametro("spada");
		this.posa.esegui(this.partita);
		assertTrue(this.partita.getStanzaCorrente().hasAttrezzo("spada"));
	}
}
