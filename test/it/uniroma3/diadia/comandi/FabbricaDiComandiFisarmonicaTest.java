package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class FabbricaDiComandiFisarmonicaTest {

	private FabbricaDiComandiFisarmonica factory;

	@BeforeEach
	public void setUp() {
		this.factory= new FabbricaDiComandiFisarmonica();
	}
	
	@Test
	void testCostruisciComandoPosa() {
		Comando posa;
		posa= this.factory.costruisciComando("posa osso");
		assertEquals("posa", posa.getNome());
		assertEquals("osso", posa.getParametro());
	}

	@Test
	void testCostruisciComandoAiuto() {
		Comando aiuto;
		aiuto= this.factory.costruisciComando("aiuto");
		assertEquals("aiuto", aiuto.getNome());
	}
	
	@Test
	void testCostruisciComandoVai() {
		Comando vai;
		vai= this.factory.costruisciComando("vai nord");
		assertEquals("vai", vai.getNome());
		assertEquals("nord", vai.getParametro());
	}
}
